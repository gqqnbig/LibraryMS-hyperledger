#!/bin/bash

oneTimeSetUp() {
	export FABRIC_CFG_PATH=$PWD/../config/

	if [[ -z "$GITHUB_WORKSPACE" ]]; then
		GITHUB_WORKSPACE=~/LibraryMS-hyperledger/
	fi

	source $GITHUB_WORKSPACE/src/test/shell/as-org1.sh

	rm -f $GITHUB_WORKSPACE/library.tar.gz
	rm -rf "$GITHUB_WORKSPACE/build/install/"
	pushd "$GITHUB_WORKSPACE"
	./gradlew installDist
	popd
	peer lifecycle chaincode package $GITHUB_WORKSPACE/library.tar.gz --path $GITHUB_WORKSPACE/build/install/library --lang java --label library_1.0

}

setUp() {
	./network.sh down
	./network.sh up createChannel

	# start a subshell due to export variables.
	(
		export CORE_PEER_TLS_ENABLED=true
		export CORE_PEER_LOCALMSPID="Org1MSP"
		export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
		export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp
		export CORE_PEER_ADDRESS=localhost:7051
		# The package ID is the combination of the chaincode label and a hash of the chaincode binaries. Every peer will generate the same package ID.
		packageId=$(peer lifecycle chaincode install $GITHUB_WORKSPACE/library.tar.gz 2>&1 | grep -o -P '(?<=identifier:\s).+:[\da-f]+$')
		if [[ -z "$packageId" ]]; then
			fail "Failed to install chaincode."
			return
		fi
		peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --channelID mychannel --name library --version 1.0 --package-id $packageId --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem

		export CORE_PEER_LOCALMSPID="Org2MSP"
		export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt
		export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org2.example.com/users/Admin@org2.example.com/msp
		export CORE_PEER_ADDRESS=localhost:9051
		peer lifecycle chaincode install $GITHUB_WORKSPACE/library.tar.gz

		peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --channelID mychannel --name library --version 1.0 --package-id $packageId --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem

		peer lifecycle chaincode commit -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --channelID mychannel --name library --version 1.0 --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem --peerAddresses localhost:7051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt --peerAddresses localhost:9051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt
	)
}

getBlockInfo() {
	peer channel fetch newest mychannel.block -c mychannel -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem
	configtxlator proto_decode --type common.Block --input mychannel.block
}

testManageUser() {
	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:createUser","Args":["8","Joe Biden","M","1942","jbiden@whitehouse.gov","Executive","0","NORMAL", "0", "0"]}' || fail || return

	docker stop "$(docker ps -n 1 --filter 'name=dev' --format '{{.ID}}')"

	if peer chaincode query -C mychannel -n library -c '{"function":"ManageUserCRUDServiceImpl:queryUser","Args":["2"]}'; then
		fail || return
	fi

	output=$(peer chaincode query -C mychannel -n library -c '{"function":"ManageUserCRUDServiceImpl:queryUser","Args":["8"]}')
	assertContains "$output" "Joe Biden"
	assertContains "$output" "1942"
	assertContains "$output" "jbiden@whitehouse.gov"
	assertContains "$output" "Executive"
	assertContains "$output" "NORMAL"
	assertContains "$output" "0"

	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:modifyUser","Args":["8","Donald Trump","F","1946","dtrump@whitehouse.gov","Legislature","0","SUSPEND", "0", "0"]}' || fail || return

	docker stop "$(docker ps -n 1 --filter 'name=dev' --format '{{.ID}}')"

	output=$(peer chaincode query -C mychannel -n library -c '{"function":"ManageUserCRUDServiceImpl:queryUser","Args":["8"]}')
	assertContains "$output" "Donald Trump"
	assertContains "$output" "1946"
	assertContains "$output" "dtrump@whitehouse.gov"
	assertContains "$output" "Legislature"
	assertContains "$output" "SUSPEND"
	assertContains "$output" "0"

	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:deleteUser","Args":["8"]}'

	docker stop "$(docker ps -n 1 --filter 'name=dev' --format '{{.ID}}')"

	if pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:deleteUser","Args":["8"]}'; then
		fail || return
	fi

	if pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:deleteUser","Args":["1"]}'; then
		fail || return
	fi
}

testCreateDeleteStudent() {
	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:createStudent","Args":["2","hello","F","123","aa","bb","cc","BACHELOR", "PROGRAMMING"]}' || fail || return
	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:deleteUser","Args":["2"]}'
}

testManageStudent() {
	if pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:deleteUser","Args":["2"]}'; then
		fail || return
	fi

	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:createStudent","Args":["2","hello","F","123","aa","bb","cc","BACHELOR", "PROGRAMMING"]}' || fail || return

	docker stop "$(docker ps -n 1 --filter 'name=dev' --format '{{.ID}}')"

	if pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:createStudent","Args":["2","hello","F","123","aa","bb","cc","BACHELOR", "PROGRAMMING"]}'; then
		fail || return
	fi

	output=$(peer chaincode query -C mychannel -n library -c '{"function":"ManageUserCRUDServiceImpl:queryUser","Args":["2"]}')
	assertContains "$output" "hello"
	assertContains "$output" "123"
	assertContains "$output" "aa"
	assertContains "$output" "bb"

	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:modifyStudent","Args":["2","world","M","456","dd","ee","ff","PHD", "GRADUATED"]}' || fail || return

	docker stop "$(docker ps -n 1 --filter 'name=dev' --format '{{.ID}}')"
	output=$(peer chaincode query -C mychannel -n library -c '{"function":"ManageUserCRUDServiceImpl:queryUser","Args":["2"]}')
	assertContains "$output doesn't contain required string." "$output" "world"
	assertContains "$output" "M"
	assertContains "$output" "456"
	assertContains "$output" "dd"
	assertContains "$output" "ee"

	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:deleteUser","Args":["2"]}'

	if pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:deleteUser","Args":["2"]}'; then
		fail || return
	fi
}

testManageFaculty() {
	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:createFaculty","Args":["100","Eggert","M","998","eggert@ucla.edu","engineering","CHAIRPROFESSOR","INPOSITION"]}' || fail || return
	docker stop "$(docker ps -n 1 --filter 'name=dev' --format '{{.ID}}')"

	if pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:createFaculty","Args":["100","Eggert","M","998","eggert@ucla.edu","engineering","CHAIRPROFESSOR","INPOSITION"]}'; then
		fail || return
	fi

	output=$(peer chaincode query -C mychannel -n library -c '{"function":"ManageUserCRUDServiceImpl:queryUser","Args":["100"]}')
	assertContains "$output" "Eggert"
	assertContains "$output" "998"
	assertContains "$output" "eggert@ucla.edu"
	assertContains "$output" "engineering"

	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:modifyFaculty","Args":["100","Paul Eggert","F","998","paul@ucla.edu","arts", "_", "ASSOCIATEPROFESSOR","HASRETIRED"]}' || fail || return
	docker stop "$(docker ps -n 1 --filter 'name=dev' --format '{{.ID}}')"

	output=$(peer chaincode query -C mychannel -n library -c '{"function":"ManageUserCRUDServiceImpl:queryUser","Args":["100"]}')
	assertContains "$output" "Paul"
	assertContains "$output" "paul@ucla.edu"
	assertContains "$output" "arts"

	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:deleteUser","Args":["100"]}'

	if pci -C mychannel -n library --waitForEvent -c '{"function":"ManageUserCRUDServiceImpl:deleteUser","Args":["100"]}'; then
		fail || return
	fi
}

testManageBook() {
	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageBookCRUDServiceImpl:createBook","Args":["1","Harry Potter","special","J. K. Rowling","Bloomsbury","fantasy novel","0-545-01022-5","2"]}' || fail || return
	docker stop "$(docker ps -n 1 --filter 'name=dev' --format '{{.ID}}')"

	if pci -C mychannel -n library --waitForEvent -c '{"function":"ManageBookCRUDServiceImpl:createBook","Args":["1","Harry Potter","special","J. K. Rowling","Bloomsbury","fantasy novel","0-545-01022-5","2"]}'; then
		fail || return
	fi

	output=$(peer chaincode query -C mychannel -n library -c '{"function":"ManageBookCRUDServiceImpl:queryBook","Args":["1"]}')
	assertContains "$output" "Harry Potter"
	assertContains "$output" "special"
	assertContains "$output" "J. K. Rowling"
	assertContains "$output" "Bloomsbury"
	assertContains "$output" "fantasy novel"
	assertContains "$output" "0-545-01022-5"

	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageBookCRUDServiceImpl:modifyBook","Args":["1","The Lord of the Rings","paperback","J. R. R. Tolkien","Allen & Unwin","epic novel","9780007117116","10"]}'
	docker stop "$(docker ps -n 1 --filter 'name=dev' --format '{{.ID}}')"

	output=$(peer chaincode query -C mychannel -n library -c '{"function":"ManageBookCRUDServiceImpl:queryBook","Args":["1"]}')
	assertContains "$output" "The Lord of the Rings"
	assertContains "$output" "paperback"
	assertContains "$output" "J. R. R. Tolkien"
	assertContains "$output" "Allen & Unwin"
	assertContains "$output" "epic novel"
	assertContains "$output" "9780007117116"
	assertContains "$output" "10"

	pci -C mychannel -n library --waitForEvent -c '{"function":"ManageBookCRUDServiceImpl:deleteBook","Args":["1"]}' || fail || return
	docker stop "$(docker ps -n 1 --filter 'name=dev' --format '{{.ID}}')"

	if pci -C mychannel -n library --waitForEvent -c '{"function":"ManageBookCRUDServiceImpl:deleteBook","Args":["1"]}'; then
		fail || return
	fi
}

source shunit2
