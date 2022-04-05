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
public class LibraryManagementSystemSystemImpl implements LibraryManagementSystemSystem, Serializable, ContractInterface {
	private static final Genson genson = new Genson();
	
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public LibraryManagementSystemSystemImpl() {
		services = new ThirdPartyServicesImpl();
	}

				
	
	/* Generate buiness logic according to functional requirement */
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean recommendBook(final Context ctx, String uid, String callNo, String title, String edition, String author, String publisher, String description, String isbn) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = recommendBook(uid, callNo, title, edition, author, publisher, description, isbn);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean recommendBook(String uid, String callNo, String title, String edition, String author, String publisher, String description, String isbn) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
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
		//Get rb
		RecommendBook rb = null;
		//no nested iterator --  iterator: any previous:any
		for (RecommendBook r : (List<RecommendBook>)EntityManager.getAllInstancesOf(RecommendBook.class))
		{
			if (r.getCallNo().equals(callNo))
			{
				rb = r;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false && StandardOPs.oclIsundefined(rb) == true) 
		{ 
			/* Logic here */
			RecommendBook r = null;
			r = (RecommendBook) EntityManager.createObject("RecommendBook");
			r.setCallNo(callNo);
			r.setTitle(title);
			r.setEdition(edition);
			r.setAuthor(author);
			r.setPublisher(publisher);
			r.setDescription(description);
			r.setIsbn(isbn);
			r.setRecommendDate(LocalDate.now());
			r.setRecommendUser(user);
			user.addRecommendedBook(r);
			EntityManager.addObject("RecommendBook", r);
			
			
			;
			// post-condition checking
			if (!(true && 
			r.getCallNo() == callNo
			 && 
			r.getTitle() == title
			 && 
			r.getEdition() == edition
			 && 
			r.getAuthor() == author
			 && 
			r.getPublisher() == publisher
			 && 
			r.getDescription() == description
			 && 
			r.getIsbn() == isbn
			 && 
			r.getRecommendDate().equals(LocalDate.now())
			 && 
			r.getRecommendUser() == user
			 && 
			StandardOPs.includes(user.getRecommendedBook(), r)
			 && 
			StandardOPs.includes(((List<RecommendBook>)EntityManager.getAllInstancesOf(RecommendBook.class)), r)
			 && 
			EntityManager.saveModified(User.class)
			 &&
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			;				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [uid, callNo, title, edition, author, publisher, description, isbn]
		//all relevant vars : r
		//all relevant entities : RecommendBook
	} 
	 
	static {opINVRelatedEntity.put("recommendBook", Arrays.asList("RecommendBook"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public BookCopy queryBookCopy(final Context ctx, String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = queryBookCopy(barcode);
		return res;
	}

	@SuppressWarnings("unchecked")
	public BookCopy queryBookCopy(String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get bookcopy
		BookCopy bookcopy = null;
		//no nested iterator --  iterator: any previous:any
		for (BookCopy boo : (List<BookCopy>)EntityManager.getAllInstancesOf(BookCopy.class))
		{
			if (boo.getBarcode().equals(barcode))
			{
				bookcopy = boo;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(bookcopy) == false) 
		{ 
			/* Logic here */
			
			
			;
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			; return bookcopy;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [barcode]
	} 
	 
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean addBookCopy(final Context ctx, String callNo, String barcode, String location) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = addBookCopy(callNo, barcode, location);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean addBookCopy(String callNo, String barcode, String location) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get book
		Book book = null;
		//no nested iterator --  iterator: any previous:any
		for (Book b : (List<Book>)EntityManager.getAllInstancesOf(Book.class))
		{
			if (b.getCallNo().equals(callNo))
			{
				book = b;
				break;
			}
				
			
		}
		//Get bc
		BookCopy bc = null;
		//no nested iterator --  iterator: any previous:any
		for (BookCopy c : book.getCopys())
		{
			if (c.getBarcode().equals(barcode))
			{
				bc = c;
				break;
			}
				
			
		}
		/* previous state in post-condition*/
		/* service reference */
		/* service temp attribute */
		/* objects in definition */
		Book Pre_book = SerializationUtils.clone(book);

		/* check precondition */
		if (StandardOPs.oclIsundefined(book) == false && StandardOPs.oclIsundefined(bc) == true) 
		{ 
			/* Logic here */
			BookCopy copy = null;
			copy = (BookCopy) EntityManager.createObject("BookCopy");
			copy.setBarcode(barcode);
			copy.setStatus(CopyStatus.AVAILABLE);
			copy.setLocation(location);
			copy.setIsReserved(false);
			book.addCopys(copy);
			copy.setBookBelongs(book);
			book.setCopyNum(book.getCopyNum()+1);
			EntityManager.addObject("BookCopy", copy);
			
			
			;
			// post-condition checking
			if (!(true && 
			copy.getBarcode() == barcode
			 && 
			copy.getStatus() == CopyStatus.AVAILABLE
			 && 
			copy.getLocation() == location
			 && 
			copy.getIsReserved() == false
			 && 
			StandardOPs.includes(book.getCopys(), copy)
			 && 
			copy.getBookBelongs() == book
			 && 
			book.getCopyNum() == Pre_book.getCopyNum()+1
			 && 
			StandardOPs.includes(((List<BookCopy>)EntityManager.getAllInstancesOf(BookCopy.class)), copy)
			 && 
			EntityManager.saveModified(Book.class)
			 &&
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			;				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [callNo, barcode, location]
		//all relevant vars : book copy
		//all relevant entities : Book BookCopy
	} 
	 
	static {opINVRelatedEntity.put("addBookCopy", Arrays.asList("Book","BookCopy"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean deleteBookCopy(final Context ctx, String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = deleteBookCopy(barcode);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean deleteBookCopy(String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get bookcopy
		BookCopy bookcopy = null;
		//no nested iterator --  iterator: any previous:any
		for (BookCopy boo : (List<BookCopy>)EntityManager.getAllInstancesOf(BookCopy.class))
		{
			if (boo.getBarcode().equals(barcode))
			{
				bookcopy = boo;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(bookcopy) == false && StandardOPs.includes(((List<BookCopy>)EntityManager.getAllInstancesOf(BookCopy.class)), bookcopy)) 
		{ 
			/* Logic here */
			EntityManager.deleteObject("BookCopy", bookcopy);
			
			
			;
			// post-condition checking
			if (!(StandardOPs.excludes(((List<BookCopy>)EntityManager.getAllInstancesOf(BookCopy.class)), bookcopy)
			 && 
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			;				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [barcode]
		//all relevant vars : bookcopy
		//all relevant entities : BookCopy
	} 
	 
	static {opINVRelatedEntity.put("deleteBookCopy", Arrays.asList("BookCopy"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean makeReservation(final Context ctx, String uid, String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = makeReservation(uid, barcode);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean makeReservation(String uid, String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
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
		//Get copy
		BookCopy copy = null;
		//no nested iterator --  iterator: any previous:any
		for (BookCopy bc : (List<BookCopy>)EntityManager.getAllInstancesOf(BookCopy.class))
		{
			if (bc.getBarcode().equals(barcode))
			{
				copy = bc;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false && StandardOPs.oclIsundefined(copy) == false && copy.getStatus() == CopyStatus.LOANED && copy.getIsReserved() == false) 
		{ 
			/* Logic here */
			Reserve res = null;
			res = (Reserve) EntityManager.createObject("Reserve");
			copy.setIsReserved(true);
			res.setIsReserveClosed(false);
			res.setReserveDate(LocalDate.now());
			res.setReservedUser(user);
			res.setReservedCopy(copy);
			user.addReservedBook(res);
			copy.addReservationRecord(res);
			EntityManager.addObject("Reserve", res);
			
			
			;
			// post-condition checking
			if (!(true && 
			copy.getIsReserved() == true
			 && 
			res.getIsReserveClosed() == false
			 && 
			res.getReserveDate().isEqual(LocalDate.now())
			 && 
			res.getReservedUser() == user
			 && 
			res.getReservedCopy() == copy
			 && 
			StandardOPs.includes(user.getReservedBook(), res)
			 && 
			StandardOPs.includes(copy.getReservationRecord(), res)
			 && 
			StandardOPs.includes(((List<Reserve>)EntityManager.getAllInstancesOf(Reserve.class)), res)
			 && 
			EntityManager.saveModified(BookCopy.class) && EntityManager.saveModified(User.class)
			 &&
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			;				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [uid, barcode]
		//all relevant vars : res copy
		//all relevant entities : Reserve BookCopy
	} 
	 
	static {opINVRelatedEntity.put("makeReservation", Arrays.asList("Reserve","BookCopy"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean cancelReservation(final Context ctx, String uid, String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = cancelReservation(uid, barcode);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean cancelReservation(String uid, String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
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
		//Get copy
		BookCopy copy = null;
		//no nested iterator --  iterator: any previous:any
		for (BookCopy bc : (List<BookCopy>)EntityManager.getAllInstancesOf(BookCopy.class))
		{
			if (bc.getBarcode().equals(barcode))
			{
				copy = bc;
				break;
			}
				
			
		}
		//Get res
		Reserve res = null;
		//no nested iterator --  iterator: any previous:any
		for (Reserve r : (List<Reserve>)EntityManager.getAllInstancesOf(Reserve.class))
		{
			if (r.getReservedCopy() == copy && r.getReservedUser() == user)
			{
				res = r;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false && StandardOPs.oclIsundefined(copy) == false && copy.getStatus() == CopyStatus.LOANED && copy.getIsReserved() == true && StandardOPs.oclIsundefined(res) == false && res.getIsReserveClosed() == false) 
		{ 
			/* Logic here */
			copy.setIsReserved(false);
			res.setIsReserveClosed(true);
			
			
			;
			// post-condition checking
			if (!(copy.getIsReserved() == false
			 && 
			res.getIsReserveClosed() == true
			 && 
			EntityManager.saveModified(Reserve.class) && EntityManager.saveModified(BookCopy.class)
			 &&
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			;				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [uid, barcode]
		//all relevant vars : res copy
		//all relevant entities : Reserve BookCopy
	} 
	 
	static {opINVRelatedEntity.put("cancelReservation", Arrays.asList("Reserve","BookCopy"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean borrowBook(final Context ctx, String uid, String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = borrowBook(uid, barcode);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean borrowBook(String uid, String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
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
		//Get stu
		Student stu = null;
		//no nested iterator --  iterator: any previous:any
		for (Student s : (List<Student>)EntityManager.getAllInstancesOf(Student.class))
		{
			if (s.getUserID().equals(uid))
			{
				stu = s;
				break;
			}
				
			
		}
		//Get fac
		Faculty fac = null;
		//no nested iterator --  iterator: any previous:any
		for (Faculty f : (List<Faculty>)EntityManager.getAllInstancesOf(Faculty.class))
		{
			if (f.getUserID().equals(uid))
			{
				fac = f;
				break;
			}
				
			
		}
		//Get copy
		BookCopy copy = null;
		//no nested iterator --  iterator: any previous:any
		for (BookCopy bc : (List<BookCopy>)EntityManager.getAllInstancesOf(BookCopy.class))
		{
			if (bc.getBarcode().equals(barcode))
			{
				copy = bc;
				break;
			}
				
			
		}
		//Get res
		Reserve res = null;
		//no nested iterator --  iterator: any previous:any
		for (Reserve r : (List<Reserve>)EntityManager.getAllInstancesOf(Reserve.class))
		{
			if (r.getReservedCopy() == copy && r.getReservedUser() == user && r.getIsReserveClosed() == false)
			{
				res = r;
				break;
			}
				
			
		}
		/* previous state in post-condition*/
		/* service reference */
		/* service temp attribute */
		/* objects in definition */
		BookCopy Pre_copy = SerializationUtils.clone(copy);
		User Pre_user = SerializationUtils.clone(user);

		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false && StandardOPs.oclIsundefined(copy) == false && user.getBorrowStatus() == BorrowStatus.NORMAL && user.getSuspensionDays() == 0 && (user instanceof Student ? (stu.getProgramme() == Programme.BACHELOR ? stu.getLoanedNumber() < 20 : (stu.getProgramme() == Programme.MASTER ? stu.getLoanedNumber() < 40 : stu.getLoanedNumber() < 60)) : fac.getLoanedNumber() < 60) && (copy.getStatus() == CopyStatus.AVAILABLE || (copy.getStatus() == CopyStatus.ONHOLDSHELF && copy.getIsReserved() == true && StandardOPs.oclIsundefined(res) == false && res.getIsReserveClosed() == false))) 
		{ 
			/* Logic here */
			Loan loan = null;
			loan = (Loan) EntityManager.createObject("Loan");
			loan.setLoanedUser(user);
			loan.setLoanedCopy(copy);
			loan.setIsReturned(false);
			loan.setLoanDate(LocalDate.now());
			user.setLoanedNumber(user.getLoanedNumber()+1);
			user.addLoanedBook(loan);
			copy.addLoanedRecord(loan);
			if (user instanceof Student)
			{
				loan.setDueDate(LocalDate.now().plusDays(30));
			}
			else
			{
			 	loan.setDueDate(LocalDate.now().plusDays(60));
			}
			if (copy.getStatus() == CopyStatus.ONHOLDSHELF)
			{
				copy.setIsReserved(false);
				res.setIsReserveClosed(true);
			}
			copy.setStatus(CopyStatus.LOANED);
			loan.setOverDue3Days(false);
			loan.setOverDue10Days(false);
			loan.setOverDue17Days(false);
			loan.setOverDue31Days(false);
			EntityManager.addObject("Loan", loan);
			
			
			;
			// post-condition checking
			if (!(true && 
			loan.getLoanedUser() == user
			 && 
			loan.getLoanedCopy() == copy
			 && 
			loan.getIsReturned() == false
			 && 
			loan.getLoanDate().equals(LocalDate.now())
			 && 
			user.getLoanedNumber() == Pre_user.getLoanedNumber()+1
			 && 
			StandardOPs.includes(user.getLoanedBook(), loan)
			 && 
			StandardOPs.includes(copy.getLoanedRecord(), loan)
			 && 
			(user instanceof Student ? loan.getDueDate().equals(LocalDate.now().plusDays(30)) : loan.getDueDate().equals(LocalDate.now().plusDays(60)))
			 && 
			(Pre_copy.getStatus() == CopyStatus.ONHOLDSHELF ? copy.getIsReserved() == false
			 && 
			res.getIsReserveClosed() == true : true)
			 && 
			copy.getStatus() == CopyStatus.LOANED
			 && 
			loan.getOverDue3Days() == false
			 && 
			loan.getOverDue10Days() == false
			 && 
			loan.getOverDue17Days() == false
			 && 
			loan.getOverDue31Days() == false
			 && 
			StandardOPs.includes(((List<Loan>)EntityManager.getAllInstancesOf(Loan.class)), loan)
			 && 
			EntityManager.saveModified(Reserve.class) && EntityManager.saveModified(BookCopy.class) && EntityManager.saveModified(User.class)
			 &&
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			;				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [uid, barcode]
		//all relevant vars : res loan copy user
		//all relevant entities : Reserve Loan BookCopy User
	} 
	 
	static {opINVRelatedEntity.put("borrowBook", Arrays.asList("Reserve","Loan","BookCopy","User"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean renewBook(final Context ctx, String uid, String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = renewBook(uid, barcode);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean renewBook(String uid, String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
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
		//Get stu
		Student stu = null;
		//no nested iterator --  iterator: any previous:any
		for (Student s : (List<Student>)EntityManager.getAllInstancesOf(Student.class))
		{
			if (s.getUserID().equals(uid))
			{
				stu = s;
				break;
			}
				
			
		}
		//Get fac
		Faculty fac = null;
		//no nested iterator --  iterator: any previous:any
		for (Faculty f : (List<Faculty>)EntityManager.getAllInstancesOf(Faculty.class))
		{
			if (f.getUserID().equals(uid))
			{
				fac = f;
				break;
			}
				
			
		}
		//Get copy
		BookCopy copy = null;
		//no nested iterator --  iterator: any previous:any
		for (BookCopy bc : (List<BookCopy>)EntityManager.getAllInstancesOf(BookCopy.class))
		{
			if (bc.getBarcode().equals(barcode) && bc.getStatus() == CopyStatus.LOANED)
			{
				copy = bc;
				break;
			}
				
			
		}
		//Get loan
		Loan loan = null;
		//no nested iterator --  iterator: any previous:any
		for (Loan l : (List<Loan>)EntityManager.getAllInstancesOf(Loan.class))
		{
			if (l.getLoanedUser() == user && l.getLoanedCopy() == copy)
			{
				loan = l;
				break;
			}
				
			
		}
		/* previous state in post-condition*/
		/* service reference */
		/* service temp attribute */
		/* objects in definition */
		Loan Pre_loan = SerializationUtils.clone(loan);

		/* check precondition */
		if (user.getBorrowStatus() == BorrowStatus.NORMAL && StandardOPs.oclIsundefined(user) == false && StandardOPs.oclIsundefined(copy) == false && StandardOPs.oclIsundefined(loan) == false && copy.getIsReserved() == false && loan.getDueDate().isAfter(LocalDate.now()) && (user instanceof Student ? loan.getRenewedTimes() < 3 : loan.getRenewedTimes() < 6) && loan.getOverDueFee() == 0) 
		{ 
			/* Logic here */
			loan.setRenewedTimes(loan.getRenewedTimes()+1);
			loan.setRenewDate(LocalDate.now());
			if (user instanceof Student)
			{
				if (stu.getProgramme() == Programme.BACHELOR)
				{
					loan.setDueDate(loan.getDueDate().plusDays(20));
				}
				else
				{
				 	if (stu.getProgramme() == Programme.MASTER)
				 	{
				 		loan.setDueDate(loan.getDueDate().plusDays(40));
				 	}
				 	else
				 	{
				 	 	loan.setDueDate(loan.getDueDate().plusDays(60));
				 	}
				}
			}
			else
			{
			 	loan.setDueDate(loan.getDueDate().plusDays(60));
			}
			
			
			;
			// post-condition checking
			if (!(loan.getRenewedTimes() == Pre_loan.getRenewedTimes()+1
			 && 
			loan.getRenewDate().equals(LocalDate.now())
			 && 
			(user instanceof Student ? (stu.getProgramme() == Programme.BACHELOR ? loan.getDueDate() == loan.getDueDate().plusDays(20) : (stu.getProgramme() == Programme.MASTER ? loan.getDueDate() == loan.getDueDate().plusDays(40) : loan.getDueDate() == loan.getDueDate().plusDays(60))) : loan.getDueDate() == loan.getDueDate().plusDays(60))
			 && 
			EntityManager.saveModified(Loan.class)
			 &&
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			;				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [uid, barcode]
		//all relevant vars : loan
		//all relevant entities : Loan
	} 
	 
	static {opINVRelatedEntity.put("renewBook", Arrays.asList("Loan"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean returnBook(final Context ctx, String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = returnBook(barcode);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean returnBook(String barcode) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get copy
		BookCopy copy = null;
		//no nested iterator --  iterator: any previous:any
		for (BookCopy bc : (List<BookCopy>)EntityManager.getAllInstancesOf(BookCopy.class))
		{
			if (bc.getBarcode().equals(barcode) && bc.getStatus() == CopyStatus.LOANED)
			{
				copy = bc;
				break;
			}
				
			
		}
		//Get loan
		Loan loan = null;
		//no nested iterator --  iterator: any previous:any
		for (Loan l : (List<Loan>)EntityManager.getAllInstancesOf(Loan.class))
		{
			if (l.getLoanedCopy() == copy && l.getIsReturned() == false)
			{
				loan = l;
				break;
			}
				
			
		}
		//Get loans
		List<Loan> loans = new LinkedList<>();
		//no nested iterator --  iterator: select previous:select
		for (Loan l : (List<Loan>)EntityManager.getAllInstancesOf(Loan.class))
		{
			if (l.getLoanedUser() == loan.getLoanedUser() && l.getIsReturned() == false && l.getDueDate().isAfter(LocalDate.now()))
			{
				loans.add(l);
			}
				
			
		}
		//Get res
		Reserve res = null;
		//no nested iterator --  iterator: any previous:any
		for (Reserve r : copy.getReservationRecord())
		{
			if (r.getReservedCopy() == copy)
			{
				res = r;
				break;
			}
				
			
		}
		/* previous state in post-condition*/
		/* service reference */
		/* service temp attribute */
		/* objects in definition */
		Loan Pre_loan = SerializationUtils.clone(loan);

		/* check precondition */
		if (StandardOPs.oclIsundefined(copy) == false && StandardOPs.oclIsundefined(loan) == false) 
		{ 
			/* Logic here */
			loan.getLoanedUser().setLoanedNumber(loan.getLoanedUser().getLoanedNumber()-1);
			loan.setIsReturned(true);
			loan.setReturnDate(LocalDate.now());
			if (copy.getIsReserved() == true)
			{
				copy.setStatus(CopyStatus.ONHOLDSHELF);
				services.sendNotificationEmail(res.getReservedUser().getEmail());
			}
			else
			{
			 	copy.setStatus(CopyStatus.AVAILABLE);
			}
			
			
			;
			// post-condition checking
			if (!(loan.getLoanedUser().getLoanedNumber() == Pre_loan.getLoanedUser().getLoanedNumber()-1
			 && 
			loan.getIsReturned() == true
			 && 
			loan.getReturnDate().equals(LocalDate.now())
			 && 
			(copy.getIsReserved() == true ? copy.getStatus() == CopyStatus.ONHOLDSHELF
			 && 
			true : copy.getStatus() == CopyStatus.AVAILABLE)
			 && 
			EntityManager.saveModified(BookCopy.class) && EntityManager.saveModified(Loan.class)
			 &&
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			;				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [barcode]
		//all relevant vars : loan copy
		//all relevant entities : Loan BookCopy
	} 
	 
	static {opINVRelatedEntity.put("returnBook", Arrays.asList("Loan","BookCopy"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean payOverDueFee(final Context ctx, String uid, float fee) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = payOverDueFee(uid, fee);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean payOverDueFee(String uid, float fee) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
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
		for (Loan l : (List<Loan>)EntityManager.getAllInstancesOf(Loan.class))
		{
			if (l.getLoanedUser() == user && l.getDueDate().isBefore(LocalDate.now()) && l.getIsReturned() == true && l.getOverDueFee() > 0)
			{
				loans.add(l);
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false && StandardOPs.notEmpty(loans) && fee >= user.getOverDueFee()) 
		{ 
			/* Logic here */
			user.setOverDueFee(0);
			//no nested iterator --  iterator: forAll
			for (Loan l : loans)
			{
				l.setOverDueFee(0);
			}
			
			
			;
			// post-condition checking
			if (!(user.getOverDueFee() == 0
			 && 
			((BooleanSupplier) () -> {							
				for (Loan l : loans) {
					if (!(l.getOverDueFee() == 0)) {
						return false;
					}
				}
				return true;
			}).getAsBoolean()
			 && 
			EntityManager.saveModified(User.class)
			 &&
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			;				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [uid]
		//all relevant vars : l user
		//all relevant entities : Loan User
	} 
	 
	static {opINVRelatedEntity.put("payOverDueFee", Arrays.asList("Loan","User"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public void checkOverDueandComputeOverDueFee(final Context ctx) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		checkOverDueandComputeOverDueFee();
	}

	@SuppressWarnings("unchecked")
	public void checkOverDueandComputeOverDueFee() throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get loans
		List<Loan> loans = new LinkedList<>();
		//no nested iterator --  iterator: select previous:select
		for (Loan loan : (List<Loan>)EntityManager.getAllInstancesOf(Loan.class))
		{
			if (loan.getIsReturned() == false && loan.getDueDate().isBefore(LocalDate.now()))
			{
				loans.add(loan);
			}
				
			
		}
		/* previous state in post-condition*/
		/* service reference */
		/* service temp attribute */
		/* objects in definition */
		List<Loan> Pre_loans = new LinkedList<>();
		for (Loan temp : loans) {
			Pre_loans.add(SerializationUtils.clone(temp));
		}									

		/* check precondition */
		if (true) 
		{ 
			/* Logic here */
			//no nested iterator --  iterator: forAll
			for (Loan loan : loans)
			{
				loan.setIsReturned(false);
				if (LocalDate.now().minusDays(3).isAfter(loan.getDueDate()) && loan.getOverDue3Days() == false)
				{
					loan.getLoanedUser().setBorrowStatus(BorrowStatus.SUSPEND);
					services.sendNotificationEmail(loan.getLoanedUser().getEmail());
					loan.setOverDue3Days(true);
				}
				if (LocalDate.now().minusDays(10).isAfter(loan.getDueDate()) && loan.getOverDue10Days() == false)
				{
					loan.getLoanedUser().setSuspensionDays(loan.getLoanedUser().getSuspensionDays()+14);
					services.sendNotificationEmail(loan.getLoanedUser().getEmail());
					loan.setOverDue10Days(true);
				}
				if (LocalDate.now().minusDays(17).isAfter(loan.getDueDate()) && loan.getOverDue17Days() == false)
				{
					loan.getLoanedUser().setSuspensionDays(loan.getLoanedUser().getSuspensionDays()+30);
					services.sendNotificationEmail(loan.getLoanedUser().getEmail());
					loan.setOverDue17Days(true);
				}
				if (LocalDate.now().minusDays(31).isAfter(loan.getDueDate()) && loan.getOverDue31Days() == false)
				{
					loan.setOverDueFee(60);
					services.sendNotificationEmail(loan.getLoanedUser().getEmail());
					loan.setOverDue31Days(true);
					loan.getLoanedUser().setOverDueFee(loan.getLoanedUser().getOverDueFee()+loan.getOverDueFee());
				}
			}
			
			
			;
			// post-condition checking
			if (!(((Predicate<List>) (list) -> {	
				Iterator<Loan> loanIt =  list.iterator();
				Iterator<Loan> Pre_loanIt =  Pre_loans.iterator();
				Loan loan = null;
				Loan Pre_loan = null;
					while (loanIt.hasNext() && Pre_loanIt.hasNext()) {
					loan = loanIt.next();
					Pre_loan = Pre_loanIt.next();
					if (!(loan.getIsReturned() == false
					 && 
					(LocalDate.now().minusDays(3).isAfter(loan.getDueDate())
					 && 
					loan.getOverDue3Days() == false ? loan.getLoanedUser().getBorrowStatus() == BorrowStatus.SUSPEND
					 && 
					true
					 && 
					loan.getOverDue3Days() == true : true)
					 && 
					(LocalDate.now().minusDays(10).isAfter(loan.getDueDate())
					 && 
					loan.getOverDue10Days() == false ? loan.getLoanedUser().getSuspensionDays() == Pre_loan.getLoanedUser().getSuspensionDays()+14
					 && 
					true
					 && 
					loan.getOverDue10Days() == true : true)
					 && 
					(LocalDate.now().minusDays(17).isAfter(loan.getDueDate())
					 && 
					loan.getOverDue17Days() == false ? loan.getLoanedUser().getSuspensionDays() == Pre_loan.getLoanedUser().getSuspensionDays()+30
					 && 
					true
					 && 
					loan.getOverDue17Days() == true : true)
					 && 
					(LocalDate.now().minusDays(31).isAfter(loan.getDueDate())
					 && 
					loan.getOverDue31Days() == false ? loan.getOverDueFee() == 60
					 && 
					true
					 && 
					loan.getOverDue31Days() == true
					 && 
					loan.getLoanedUser().getOverDueFee() == Pre_loan.getLoanedUser().getOverDueFee()+loan.getOverDueFee() : true))) {
						return false;
					}
				}
				return true;
			}).test(loans)
			)) {
				throw new PostconditionException();
			}
			
			; //no return type 
		}
		else
		{
			throw new PreconditionException();
		}
		//all relevant vars : loan
		//all relevant entities : Loan
	} 
	 
	static {opINVRelatedEntity.put("checkOverDueandComputeOverDueFee", Arrays.asList("Loan"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public void dueSoonNotification(final Context ctx) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		dueSoonNotification();
	}

	@SuppressWarnings("unchecked")
	public void dueSoonNotification() throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get users
		List<User> users = new LinkedList<>();
		//has nested iterator	
		for (User user : (List<User>)EntityManager.getAllInstancesOf(User.class))
		{
			//nested for here:
			//no nested iterator --  iterator: exists previous:select
			for (Loan loan : user.getLoanedBook())
			{
				if (loan.getIsReturned() == false && LocalDate.now().plusDays(3).isAfter(loan.getDueDate()))
				{
					users.add(user);
				}
					
				
			}
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (true) 
		{ 
			/* Logic here */
			//no nested iterator --  iterator: forAll
			for (User u : users)
			{
				services.sendNotificationEmail(u.getEmail());
			}
			
			
			;
			// post-condition checking
			if (!(((BooleanSupplier) () -> {							
				for (User u : users) {
					if (!(true)) {
						return false;
					}
				}
				return true;
			}).getAsBoolean()
			)) {
				throw new PostconditionException();
			}
			
			; //no return type 
		}
		else
		{
			throw new PreconditionException();
		}
		//all relevant vars : u
		//all relevant entities : User
	} 
	 
	static {opINVRelatedEntity.put("dueSoonNotification", Arrays.asList("User"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public void countDownSuspensionDay(final Context ctx) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		countDownSuspensionDay();
	}

	@SuppressWarnings("unchecked")
	public void countDownSuspensionDay() throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get users
		List<User> users = new LinkedList<>();
		//no nested iterator --  iterator: select previous:select
		for (User u : (List<User>)EntityManager.getAllInstancesOf(User.class))
		{
			if (u.getSuspensionDays() > 0)
			{
				users.add(u);
			}
				
			
		}
		/* previous state in post-condition*/
		/* service reference */
		/* service temp attribute */
		/* objects in definition */
		List<User> Pre_users = new LinkedList<>();
		for (User temp : users) {
			Pre_users.add(SerializationUtils.clone(temp));
		}									

		/* check precondition */
		if (true) 
		{ 
			/* Logic here */
			//no nested iterator --  iterator: forAll
			for (User u : users)
			{
				u.setSuspensionDays(u.getSuspensionDays()-1);
				if (u.getBorrowStatus() == BorrowStatus.SUSPEND && u.getOverDueFee() == 0 && u.getSuspensionDays() == 0)
				{
					u.setBorrowStatus(BorrowStatus.NORMAL);
				}
			}
			
			
			;
			// post-condition checking
			if (!(((Predicate<List>) (list) -> {	
				Iterator<User> uIt =  list.iterator();
				Iterator<User> Pre_uIt =  Pre_users.iterator();
				User u = null;
				User Pre_u = null;
					while (uIt.hasNext() && Pre_uIt.hasNext()) {
					u = uIt.next();
					Pre_u = Pre_uIt.next();
					if (!(u.getSuspensionDays() == Pre_u.getSuspensionDays()-1
					 && 
					(u.getBorrowStatus() == BorrowStatus.SUSPEND
					 && 
					u.getOverDueFee() == 0
					 && 
					u.getSuspensionDays() == 0 ? u.getBorrowStatus() == BorrowStatus.NORMAL : true))) {
						return false;
					}
				}
				return true;
			}).test(users)
			)) {
				throw new PostconditionException();
			}
			
			; //no return type 
		}
		else
		{
			throw new PreconditionException();
		}
		//all relevant vars : u
		//all relevant entities : User
	} 
	 
	static {opINVRelatedEntity.put("countDownSuspensionDay", Arrays.asList("User"));}
	
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
