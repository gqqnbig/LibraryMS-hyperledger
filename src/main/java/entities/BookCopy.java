package entities;

import services.impl.StandardOPs;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.Serializable;
import java.lang.reflect.Method;
import org.hyperledger.fabric.contract.annotation.*;

@DataType()
public class BookCopy implements Serializable {
	
	/* all primary attributes */
	@Property()
	private String barcode;
	@Property()
	private CopyStatus status;
	@Property()
	private String location;
	@Property()
	private boolean isReserved;
	
	/* all references */
	private Book BookBelongs; 
	private List<Loan> LoanedRecord = new LinkedList<Loan>(); 
	private List<Reserve> ReservationRecord = new LinkedList<Reserve>(); 
	
	/* all get and set functions */
	public String getBarcode() {
		return barcode;
	}	
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public CopyStatus getStatus() {
		return status;
	}	
	
	public void setStatus(CopyStatus status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}	
	
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean getIsReserved() {
		return isReserved;
	}	
	
	public void setIsReserved(boolean isreserved) {
		this.isReserved = isreserved;
	}
	
	/* all functions for reference*/
	public Book getBookBelongs() {
		return BookBelongs;
	}	
	
	public void setBookBelongs(Book book) {
		this.BookBelongs = book;
	}			
	public List<Loan> getLoanedRecord() {
		return LoanedRecord;
	}	
	
	public void addLoanedRecord(Loan loan) {
		this.LoanedRecord.add(loan);
	}
	
	public void deleteLoanedRecord(Loan loan) {
		this.LoanedRecord.remove(loan);
	}
	public List<Reserve> getReservationRecord() {
		return ReservationRecord;
	}	
	
	public void addReservationRecord(Reserve reserve) {
		this.ReservationRecord.add(reserve);
	}
	
	public void deleteReservationRecord(Reserve reserve) {
		this.ReservationRecord.remove(reserve);
	}
	

	/* invarints checking*/
	public boolean BookCopy_BarCodeUnique() {
		
		if (StandardOPs.isUnique(((List<BookCopy>)EntityManager.getAllInstancesOf("BookCopy")), "Barcode")) {
			return true;
		} else {
			return false;
		}
	}
	
	//check all invariants
	public boolean checkAllInvairant() {
		
		if (BookCopy_BarCodeUnique()) {
			return true;
		} else {
			return false;
		}
	}	
	
	//check invariant by inv name
	public boolean checkInvariant(String INVname) {
		
		try {
			Method m = this.getClass().getDeclaredMethod(INVname);
			return (boolean) m.invoke(this);
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	
	}	
	
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList("BookCopy_BarCodeUnique"));

}
