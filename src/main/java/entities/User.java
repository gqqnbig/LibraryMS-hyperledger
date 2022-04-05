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
import com.owlike.genson.annotation.*;
import java.util.stream.*;

@DataType()
public class User implements Serializable {
	public Object getPK() {
		return getUserID();
	}
	
	/* all primary attributes */
	@Property()
	private String userID;
	@Property()
	private String name;
	@Property()
	private Sex sex;
	@Property()
	private String password;
	@Property()
	private String email;
	@Property()
	private String faculty;
	@Property()
	private int loanedNumber;
	@Property()
	private BorrowStatus borrowStatus;
	@Property()
	private int suspensionDays;
	@Property()
	private float overDueFee;
	
	/* all references */
	@JsonProperty
	private List<Object> LoanedBookPKs = new LinkedList<>();
	private List<Loan> LoanedBook = new LinkedList<Loan>(); 
	@JsonProperty
	private List<Object> ReservedBookPKs = new LinkedList<>();
	private List<Reserve> ReservedBook = new LinkedList<Reserve>(); 
	@JsonProperty
	private List<Object> RecommendedBookPKs = new LinkedList<>();
	private List<RecommendBook> RecommendedBook = new LinkedList<RecommendBook>(); 
	
	/* all get and set functions */
	public String getUserID() {
		return userID;
	}	
	
	public void setUserID(String userid) {
		this.userID = userid;
	}
	public String getName() {
		return name;
	}	
	
	public void setName(String name) {
		this.name = name;
	}
	public Sex getSex() {
		return sex;
	}	
	
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}	
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}	
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFaculty() {
		return faculty;
	}	
	
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public int getLoanedNumber() {
		return loanedNumber;
	}	
	
	public void setLoanedNumber(int loanednumber) {
		this.loanedNumber = loanednumber;
	}
	public BorrowStatus getBorrowStatus() {
		return borrowStatus;
	}	
	
	public void setBorrowStatus(BorrowStatus borrowstatus) {
		this.borrowStatus = borrowstatus;
	}
	public int getSuspensionDays() {
		return suspensionDays;
	}	
	
	public void setSuspensionDays(int suspensiondays) {
		this.suspensionDays = suspensiondays;
	}
	public float getOverDueFee() {
		return overDueFee;
	}	
	
	public void setOverDueFee(float overduefee) {
		this.overDueFee = overduefee;
	}
	
	/* all functions for reference*/
	@JsonIgnore
	public List<Loan> getLoanedBook() {
		if (LoanedBook == null)
			LoanedBook = LoanedBookPKs.stream().map(EntityManager::getLoanByPK).collect(Collectors.toList());
		return LoanedBook;
	}	
	
	public void addLoanedBook(Loan loan) {
		getLoanedBook();
		this.LoanedBookPKs.add(loan.getPK());
		this.LoanedBook.add(loan);
	}
	
	public void deleteLoanedBook(Loan loan) {
		getLoanedBook();
		this.LoanedBookPKs.remove(loan.getPK());
		this.LoanedBook.remove(loan);
	}
	@JsonIgnore
	public List<Reserve> getReservedBook() {
		if (ReservedBook == null)
			ReservedBook = ReservedBookPKs.stream().map(EntityManager::getReserveByPK).collect(Collectors.toList());
		return ReservedBook;
	}	
	
	public void addReservedBook(Reserve reserve) {
		getReservedBook();
		this.ReservedBookPKs.add(reserve.getPK());
		this.ReservedBook.add(reserve);
	}
	
	public void deleteReservedBook(Reserve reserve) {
		getReservedBook();
		this.ReservedBookPKs.remove(reserve.getPK());
		this.ReservedBook.remove(reserve);
	}
	@JsonIgnore
	public List<RecommendBook> getRecommendedBook() {
		if (RecommendedBook == null)
			RecommendedBook = RecommendedBookPKs.stream().map(EntityManager::getRecommendBookByPK).collect(Collectors.toList());
		return RecommendedBook;
	}	
	
	public void addRecommendedBook(RecommendBook recommendbook) {
		getRecommendedBook();
		this.RecommendedBookPKs.add(recommendbook.getPK());
		this.RecommendedBook.add(recommendbook);
	}
	
	public void deleteRecommendedBook(RecommendBook recommendbook) {
		getRecommendedBook();
		this.RecommendedBookPKs.remove(recommendbook.getPK());
		this.RecommendedBook.remove(recommendbook);
	}
	

	/* invarints checking*/
	public boolean User_UniqueUserID() {
		
		if (StandardOPs.isUnique(((List<User>)EntityManager.getAllInstancesOf("User")), "UserID")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean User_OverDueFeeGreatThanEqualZero() {
		
		if (overDueFee >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean User_LoanedNumberGreatThanEqualZero() {
		
		if (loanedNumber >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean User_SuspensionDaysGreatThanEqualZero() {
		
		if (suspensionDays >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//check all invariants
	public boolean checkAllInvairant() {
		
		if (User_UniqueUserID() && User_OverDueFeeGreatThanEqualZero() && User_LoanedNumberGreatThanEqualZero() && User_SuspensionDaysGreatThanEqualZero()) {
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
	
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList("User_UniqueUserID","User_OverDueFeeGreatThanEqualZero","User_LoanedNumberGreatThanEqualZero","User_SuspensionDaysGreatThanEqualZero"));

}
