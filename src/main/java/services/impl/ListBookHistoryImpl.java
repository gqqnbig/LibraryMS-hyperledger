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
public class ListBookHistoryImpl implements ListBookHistory, Serializable, ContractInterface {
	private static final Genson genson = new Genson();
	
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public ListBookHistoryImpl() {
		services = new ThirdPartyServicesImpl();
	}

	
	//Shared variable from system services
	
	/* Shared variable from system services and get()/set() methods */
			
	/* all get and set functions for temp property*/
				
	
	
	/* Generate inject for sharing temp variables between use cases in system service */
	
	
	/* Generate buiness logic according to functional requirement */
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public Loan[] listBorrowHistory(final Context ctx, String uid) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = listBorrowHistory(uid);
		return res.toArray(Loan[]::new);
	}

	@SuppressWarnings("unchecked")
	public List<Loan> listBorrowHistory(String uid) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get user
		User user = null;
		//no nested iterator --  iterator: any previous:any
		for (User u : (List<User>)EntityManager.getAllInstancesOf(User.class))
		{
			if (u.getUserID().equals(uid))
			{
				user = u;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false) 
		{ 
			/* Logic here */
			
			
			;
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			; return user.getLoanedBook();
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [uid]
		//all relevant vars : user
		//all relevant entities : User
	} 
	 
	static {opINVRelatedEntity.put("listBorrowHistory", Arrays.asList("User"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public Loan[] listHodingBook(final Context ctx, String uid) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = listHodingBook(uid);
		return res.toArray(Loan[]::new);
	}

	@SuppressWarnings("unchecked")
	public List<Loan> listHodingBook(String uid) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get user
		User user = null;
		//no nested iterator --  iterator: any previous:any
		for (User u : (List<User>)EntityManager.getAllInstancesOf(User.class))
		{
			if (u.getUserID().equals(uid))
			{
				user = u;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false) 
		{ 
			/* Logic here */
			List<Loan> tempsl = new LinkedList<>();
			//no nested iterator --  iterator: select
			for (Loan l : user.getLoanedBook())
			{
				if (l.getIsReturned() == false)
				{
					tempsl.add(l);
				} 
			}
			
			
			;
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			; return tempsl;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [uid]
		//all relevant vars : user
		//all relevant entities : User
	} 
	 
	static {opINVRelatedEntity.put("listHodingBook", Arrays.asList("User"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public BookCopy[] listOverDueBook(final Context ctx, String uid) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = listOverDueBook(uid);
		return res.toArray(BookCopy[]::new);
	}

	@SuppressWarnings("unchecked")
	public List<BookCopy> listOverDueBook(String uid) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get user
		User user = null;
		//no nested iterator --  iterator: any previous:any
		for (User u : (List<User>)EntityManager.getAllInstancesOf(User.class))
		{
			if (u.getUserID().equals(uid))
			{
				user = u;
				break;
			}
				
			
		}
		//Get loans
		List<Loan> loans = new LinkedList<>();
		//no nested iterator --  iterator: select previous:select
		for (Loan l : user.getLoanedBook())
		{
			if (l.getIsReturned() == false && l.getOverDueFee() > 0)
			{
				loans.add(l);
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false && StandardOPs.oclIsundefined(loans) == false) 
		{ 
			/* Logic here */
			List temps = new LinkedList<>();
			//no nested iterator --  iterator: collect
			for (Loan l : loans)
			{
				temps.add(l.getLoanedCopy());
			}
			
			
			;
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			; return temps;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [uid]
	} 
	 
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public BookCopy[] listReservationBook(final Context ctx, String uid) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = listReservationBook(uid);
		return res.toArray(BookCopy[]::new);
	}

	@SuppressWarnings("unchecked")
	public List<BookCopy> listReservationBook(String uid) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get user
		User user = null;
		//no nested iterator --  iterator: any previous:any
		for (User u : (List<User>)EntityManager.getAllInstancesOf(User.class))
		{
			if (u.getUserID().equals(uid))
			{
				user = u;
				break;
			}
				
			
		}
		//Get res
		List<Reserve> res = new LinkedList<>();
		res = user.getReservedBook();
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false && StandardOPs.oclIsundefined(res) == false) 
		{ 
			/* Logic here */
			List temps = new LinkedList<>();
			//no nested iterator --  iterator: collect
			for (Reserve r : res)
			{
				temps.add(r.getReservedCopy());
			}
			
			
			;
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			; return temps;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [uid]
	} 
	 
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public RecommendBook[] listRecommendBook(final Context ctx, String uid) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = listRecommendBook(uid);
		return res.toArray(RecommendBook[]::new);
	}

	@SuppressWarnings("unchecked")
	public List<RecommendBook> listRecommendBook(String uid) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get user
		User user = null;
		//no nested iterator --  iterator: any previous:any
		for (User u : (List<User>)EntityManager.getAllInstancesOf(User.class))
		{
			if (u.getUserID().equals(uid))
			{
				user = u;
				break;
			}
				
			
		}
		//Get rBooks
		List<RecommendBook> rBooks = new LinkedList<>();
		rBooks = user.getRecommendedBook();
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false && StandardOPs.oclIsundefined(rBooks) == false) 
		{ 
			/* Logic here */
			
			
			;
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			; return rBooks;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [uid]
	} 
	 
	
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
