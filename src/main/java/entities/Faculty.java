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
public class Faculty extends User  implements Serializable {
	
	/* all primary attributes */
	@Property()
	private FacultyPosition position;
	@Property()
	private FacultyStatus status;
	
	/* all references */
	
	/* all get and set functions */
	public FacultyPosition getPosition() {
		return position;
	}	
	
	public void setPosition(FacultyPosition position) {
		this.position = position;
	}
	public FacultyStatus getStatus() {
		return status;
	}	
	
	public void setStatus(FacultyStatus status) {
		this.status = status;
	}
	
	/* all functions for reference*/
	

	/* invarints checking*/
	public boolean Faculty_FacultyLoanLessthanEqualTwentyFour() {
		
		for (Student stu : ((List<Student>)EntityManager.getAllInstancesOf("Student"))) {
			if (!(stu.getLoanedNumber() <= 24)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean Faculty_FacultyLoanedBookAssociationInvariants() {
		
		if (StandardOPs.size(super.getLoanedBook()) <= 24) {
			return true;
		} else {
			return false;
		}
	}
	
	//check all invariants
	public boolean checkAllInvairant() {
		
		if (Faculty_FacultyLoanLessthanEqualTwentyFour() && Faculty_FacultyLoanedBookAssociationInvariants()) {
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
	
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList("Faculty_FacultyLoanLessthanEqualTwentyFour","Faculty_FacultyLoanedBookAssociationInvariants"));

}
