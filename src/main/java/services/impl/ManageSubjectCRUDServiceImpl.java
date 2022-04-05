package services.impl;

import services.*;
import entities.*;
import java.util.List;
import java.util.LinkedList;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.Arrays;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import org.apache.commons.lang3.SerializationUtils;
import java.util.Iterator;
import org.hyperledger.fabric.shim.*;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.contract.*;
import com.owlike.genson.Genson;

@Contract
public class ManageSubjectCRUDServiceImpl implements ManageSubjectCRUDService, Serializable, ContractInterface {
	private static final Genson genson = new Genson();
	
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public ManageSubjectCRUDServiceImpl() {
		services = new ThirdPartyServicesImpl();
	}

	
	//Shared variable from system services
	
	/* Shared variable from system services and get()/set() methods */
			
	/* all get and set functions for temp property*/
				
	
	
	/* Generate inject for sharing temp variables between use cases in system service */
	public void refresh() {
		LibraryManagementSystemSystem librarymanagementsystemsystem_service = (LibraryManagementSystemSystem) ServiceManager.getAllInstancesOf(LibraryManagementSystemSystem.class).get(0);
	}
	
	/* Generate buiness logic according to functional requirement */
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean createSubject(final Context ctx, String name) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = createSubject(name);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean createSubject(String name) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get subject
		Subject subject = null;
		//no nested iterator --  iterator: any previous:any
		for (Subject sub : (List<Subject>)EntityManager.getAllInstancesOf(Subject.class))
		{
			if (sub.getName().equals(name))
			{
				subject = sub;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(subject) == true) 
		{ 
			/* Logic here */
			Subject sub = null;
			sub = (Subject) EntityManager.createObject("Subject");
			sub.setName(name);
			EntityManager.addObject("Subject", sub);
			
			
			refresh();
			// post-condition checking
			if (!(true && 
			sub.getName() == name
			 && 
			StandardOPs.includes(((List<Subject>)EntityManager.getAllInstancesOf(Subject.class)), sub)
			 && 
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			refresh();				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [name]
		//all relevant vars : sub
		//all relevant entities : Subject
	} 
	 
	static {opINVRelatedEntity.put("createSubject", Arrays.asList("Subject"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public Subject querySubject(final Context ctx, String name) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = querySubject(name);
		return res;
	}

	@SuppressWarnings("unchecked")
	public Subject querySubject(String name) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get subject
		Subject subject = null;
		//no nested iterator --  iterator: any previous:any
		for (Subject sub : (List<Subject>)EntityManager.getAllInstancesOf(Subject.class))
		{
			if (sub.getName().equals(name))
			{
				subject = sub;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(subject) == false) 
		{ 
			/* Logic here */
			
			
			refresh();
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			refresh(); return subject;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [name]
	} 
	 
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean modifySubject(final Context ctx, String name) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = modifySubject(name);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean modifySubject(String name) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get subject
		Subject subject = null;
		//no nested iterator --  iterator: any previous:any
		for (Subject sub : (List<Subject>)EntityManager.getAllInstancesOf(Subject.class))
		{
			if (sub.getName().equals(name))
			{
				subject = sub;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(subject) == false) 
		{ 
			/* Logic here */
			subject.setName(name);
			
			
			refresh();
			// post-condition checking
			if (!(subject.getName() == name
			 && 
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			refresh();				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [name]
		//all relevant vars : subject
		//all relevant entities : Subject
	} 
	 
	static {opINVRelatedEntity.put("modifySubject", Arrays.asList("Subject"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean deleteSubject(final Context ctx, String name) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = deleteSubject(name);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean deleteSubject(String name) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get subject
		Subject subject = null;
		//no nested iterator --  iterator: any previous:any
		for (Subject sub : (List<Subject>)EntityManager.getAllInstancesOf(Subject.class))
		{
			if (sub.getName().equals(name))
			{
				subject = sub;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(subject) == false && StandardOPs.includes(((List<Subject>)EntityManager.getAllInstancesOf(Subject.class)), subject)) 
		{ 
			/* Logic here */
			EntityManager.deleteObject("Subject", subject);
			
			
			refresh();
			// post-condition checking
			if (!(StandardOPs.excludes(((List<Subject>)EntityManager.getAllInstancesOf(Subject.class)), subject)
			 && 
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			refresh();				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [name]
		//all relevant vars : subject
		//all relevant entities : Subject
	} 
	 
	static {opINVRelatedEntity.put("deleteSubject", Arrays.asList("Subject"));}
	
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
