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
public class SearchBookImpl implements SearchBook, Serializable, ContractInterface {
	private static final Genson genson = new Genson();
	
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public SearchBookImpl() {
		services = new ThirdPartyServicesImpl();
	}

	
	//Shared variable from system services
	
	/* Shared variable from system services and get()/set() methods */
			
	/* all get and set functions for temp property*/
				
	
	
	/* Generate inject for sharing temp variables between use cases in system service */
	
	
	/* Generate buiness logic according to functional requirement */
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public Book[] searchBookByBarCode(final Context ctx, String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = searchBookByBarCode(barcode);
		return res.toArray(Book[]::new);
	}

	@SuppressWarnings("unchecked")
	public List<Book> searchBookByBarCode(String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* previous state in post-condition*/

		/* check precondition */
		if (barcode instanceof String) 
		{ 
			/* Logic here */
			List<Book> tempsbook = new LinkedList<>();
			//has nested iterator	
			for (Book book : ((List<Book>)EntityManager.getAllInstancesOf(Book.class)))
			{
				//nested for here:
				for (BookCopy c : book.getCopys()) //generated in pre generator
				{			
					if (c.getBarcode().equals(barcode))
					{
						tempsbook.add(book);
					}
				}
			}
			
			
			;
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			; return tempsbook;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [barcode]
	} 
	 
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public Book[] searchBookByTitle(final Context ctx, String title) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = searchBookByTitle(title);
		return res.toArray(Book[]::new);
	}

	@SuppressWarnings("unchecked")
	public List<Book> searchBookByTitle(String title) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* previous state in post-condition*/

		/* check precondition */
		if (!title.equals("")) 
		{ 
			/* Logic here */
			List<Book> tempsbook = new LinkedList<>();
			//no nested iterator --  iterator: select
			for (Book book : ((List<Book>)EntityManager.getAllInstancesOf(Book.class)))
			{
				if (book.getTitle().equals(title))
				{
					tempsbook.add(book);
				} 
			}
			
			
			;
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			; return tempsbook;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [title]
	} 
	 
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public Book[] searchBookByAuthor(final Context ctx, String authorname) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = searchBookByAuthor(authorname);
		return res.toArray(Book[]::new);
	}

	@SuppressWarnings("unchecked")
	public List<Book> searchBookByAuthor(String authorname) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* previous state in post-condition*/

		/* check precondition */
		if (!authorname.equals("")) 
		{ 
			/* Logic here */
			List<Book> tempsbook = new LinkedList<>();
			//no nested iterator --  iterator: select
			for (Book book : ((List<Book>)EntityManager.getAllInstancesOf(Book.class)))
			{
				if (book.getAuthor().equals(authorname))
				{
					tempsbook.add(book);
				} 
			}
			
			
			;
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			; return tempsbook;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [authorname]
	} 
	 
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public Book[] searchBookByISBN(final Context ctx, String iSBNnumber) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = searchBookByISBN(iSBNnumber);
		return res.toArray(Book[]::new);
	}

	@SuppressWarnings("unchecked")
	public List<Book> searchBookByISBN(String iSBNnumber) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* previous state in post-condition*/

		/* check precondition */
		if (iSBNnumber instanceof String) 
		{ 
			/* Logic here */
			List<Book> tempsbook = new LinkedList<>();
			//no nested iterator --  iterator: select
			for (Book book : ((List<Book>)EntityManager.getAllInstancesOf(Book.class)))
			{
				if (book.getIsbn().equals(iSBNnumber))
				{
					tempsbook.add(book);
				} 
			}
			
			
			;
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			; return tempsbook;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [iSBNnumber]
	} 
	 
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public Book[] searchBookBySubject(final Context ctx, String subject) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = searchBookBySubject(subject);
		return res.toArray(Book[]::new);
	}

	@SuppressWarnings("unchecked")
	public List<Book> searchBookBySubject(String subject) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* previous state in post-condition*/

		/* check precondition */
		if (subject instanceof String) 
		{ 
			/* Logic here */
			List<Book> tempsbook = new LinkedList<>();
			//has nested iterator	
			for (Book book : ((List<Book>)EntityManager.getAllInstancesOf(Book.class)))
			{
				//nested for here:
				for (Subject s : book.getSubject()) //generated in pre generator
				{			
					if (s.getName().equals(subject))
					{
						tempsbook.add(book);
					}
				}
			}
			
			
			;
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			; return tempsbook;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [subject]
	} 
	 
	
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
