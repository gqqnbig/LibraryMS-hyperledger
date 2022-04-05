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
import converters.*;
import com.owlike.genson.annotation.*;

@DataType()
public class Loan implements Serializable {
	
	/* all primary attributes */
	@Property()
	private LocalDate loanDate;
	@Property()
	private LocalDate renewDate;
	@Property()
	private LocalDate dueDate;
	@Property()
	private LocalDate returnDate;
	@Property()
	private int renewedTimes;
	@Property()
	private boolean isReturned;
	@Property()
	private float overDueFee;
	@Property()
	private boolean overDue3Days;
	@Property()
	private boolean overDue10Days;
	@Property()
	private boolean overDue17Days;
	@Property()
	private boolean overDue31Days;
	
	/* all references */
	private User LoanedUser; 
	private BookCopy LoanedCopy; 
	private Librarian LoanLibrarian; 
	private Librarian ReturnLibrarian; 
	
	/* all get and set functions */
	@JsonConverter(LocalDateConverter.class)
	public LocalDate getLoanDate() {
		return loanDate;
	}	
	
	@JsonConverter(LocalDateConverter.class)
	public void setLoanDate(LocalDate loandate) {
		this.loanDate = loandate;
	}
	@JsonConverter(LocalDateConverter.class)
	public LocalDate getRenewDate() {
		return renewDate;
	}	
	
	@JsonConverter(LocalDateConverter.class)
	public void setRenewDate(LocalDate renewdate) {
		this.renewDate = renewdate;
	}
	@JsonConverter(LocalDateConverter.class)
	public LocalDate getDueDate() {
		return dueDate;
	}	
	
	@JsonConverter(LocalDateConverter.class)
	public void setDueDate(LocalDate duedate) {
		this.dueDate = duedate;
	}
	@JsonConverter(LocalDateConverter.class)
	public LocalDate getReturnDate() {
		return returnDate;
	}	
	
	@JsonConverter(LocalDateConverter.class)
	public void setReturnDate(LocalDate returndate) {
		this.returnDate = returndate;
	}
	public int getRenewedTimes() {
		return renewedTimes;
	}	
	
	public void setRenewedTimes(int renewedtimes) {
		this.renewedTimes = renewedtimes;
	}
	public boolean getIsReturned() {
		return isReturned;
	}	
	
	public void setIsReturned(boolean isreturned) {
		this.isReturned = isreturned;
	}
	public float getOverDueFee() {
		return overDueFee;
	}	
	
	public void setOverDueFee(float overduefee) {
		this.overDueFee = overduefee;
	}
	public boolean getOverDue3Days() {
		return overDue3Days;
	}	
	
	public void setOverDue3Days(boolean overdue3days) {
		this.overDue3Days = overdue3days;
	}
	public boolean getOverDue10Days() {
		return overDue10Days;
	}	
	
	public void setOverDue10Days(boolean overdue10days) {
		this.overDue10Days = overdue10days;
	}
	public boolean getOverDue17Days() {
		return overDue17Days;
	}	
	
	public void setOverDue17Days(boolean overdue17days) {
		this.overDue17Days = overdue17days;
	}
	public boolean getOverDue31Days() {
		return overDue31Days;
	}	
	
	public void setOverDue31Days(boolean overdue31days) {
		this.overDue31Days = overdue31days;
	}
	
	/* all functions for reference*/
	public User getLoanedUser() {
		return LoanedUser;
	}	
	
	public void setLoanedUser(User user) {
		this.LoanedUser = user;
	}			
	public BookCopy getLoanedCopy() {
		return LoanedCopy;
	}	
	
	public void setLoanedCopy(BookCopy bookcopy) {
		this.LoanedCopy = bookcopy;
	}			
	public Librarian getLoanLibrarian() {
		return LoanLibrarian;
	}	
	
	public void setLoanLibrarian(Librarian librarian) {
		this.LoanLibrarian = librarian;
	}			
	public Librarian getReturnLibrarian() {
		return ReturnLibrarian;
	}	
	
	public void setReturnLibrarian(Librarian librarian) {
		this.ReturnLibrarian = librarian;
	}			
	

	/* invarints checking*/
	public boolean Loan_OverDueFeeGreatThanEqualZero() {
		
		if (overDueFee >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean Loan_RenewedTimesLessThanEqualSix() {
		
		if (renewedTimes >= 0 && renewedTimes <= 6) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean Loan_LoanOverDueFeeGreatThanEqualZero() {
		
		if (overDueFee >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean Loan_RenewDataAfterLoanDate() {
		
		if (((StandardOPs.oclIsundefined(renewDate) == false) ? renewDate.isAfter(loanDate) : true)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean Loan_DueDateAfterLoanDate() {
		
		if (dueDate.isAfter(loanDate)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean Loan_ReturnDateAfterORSameLoanDate() {
		
		if (((StandardOPs.oclIsundefined(returnDate) == false) ? returnDate.isAfter(loanDate) || returnDate.isEqual(loanDate) : true)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean Loan_DueDateAfterORSameRenewDate() {
		
		if (((StandardOPs.oclIsundefined(renewDate) == false) ? dueDate.isAfter(renewDate) || dueDate.isEqual(renewDate) : true)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean Loan_ReturnDateSameORAfterRenewDate() {
		
		if (((StandardOPs.oclIsundefined(renewDate) == false && StandardOPs.oclIsundefined(returnDate) == false) ? returnDate.isAfter(renewDate) || returnDate.isEqual(renewDate) : true)) {
			return true;
		} else {
			return false;
		}
	}
	
	//check all invariants
	public boolean checkAllInvairant() {
		
		if (Loan_OverDueFeeGreatThanEqualZero() && Loan_RenewedTimesLessThanEqualSix() && Loan_LoanOverDueFeeGreatThanEqualZero() && Loan_RenewDataAfterLoanDate() && Loan_DueDateAfterLoanDate() && Loan_ReturnDateAfterORSameLoanDate() && Loan_DueDateAfterORSameRenewDate() && Loan_ReturnDateSameORAfterRenewDate()) {
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
	
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList("Loan_OverDueFeeGreatThanEqualZero","Loan_RenewedTimesLessThanEqualSix","Loan_LoanOverDueFeeGreatThanEqualZero","Loan_RenewDataAfterLoanDate","Loan_DueDateAfterLoanDate","Loan_ReturnDateAfterORSameLoanDate","Loan_DueDateAfterORSameRenewDate","Loan_ReturnDateSameORAfterRenewDate"));

}
