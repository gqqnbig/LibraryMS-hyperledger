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
public class RecommendBook extends Book  implements Serializable {
	public Object getPK() {
		return getCallNo();
	}
	
	/* all primary attributes */
	@Property()
	private LocalDate recommendDate;
	
	/* all references */
	@JsonProperty
	private Object RecommendUserPK;
	private User RecommendUser; 
	
	/* all get and set functions */
	@JsonConverter(LocalDateConverter.class)
	public LocalDate getRecommendDate() {
		return recommendDate;
	}	
	
	@JsonConverter(LocalDateConverter.class)
	public void setRecommendDate(LocalDate recommenddate) {
		this.recommendDate = recommenddate;
	}
	
	/* all functions for reference*/
	@JsonIgnore
	public User getRecommendUser() {
		if (RecommendUser == null)
			RecommendUser = EntityManager.getUserByPK(RecommendUserPK);
		return RecommendUser;
	}	
	
	public void setRecommendUser(User user) {
		this.RecommendUser = user;
		this.RecommendUserPK = user.getPK();
	}			
	

	/* invarints checking*/
	public boolean RecommendBook_BookCallNoUnique() {
		
		if (StandardOPs.isUnique(((List<Book>)EntityManager.getAllInstancesOf("Book")), "CallNo")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean RecommendBook_BookISBNUnique() {
		
		if (StandardOPs.isUnique(((List<Book>)EntityManager.getAllInstancesOf("Book")), "ISBn")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean RecommendBook_BookCopyNumGreatThanEqualZero() {
		
		if (super.getCopyNum() >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//check all invariants
	public boolean checkAllInvairant() {
		
		if (RecommendBook_BookCallNoUnique() && RecommendBook_BookISBNUnique() && RecommendBook_BookCopyNumGreatThanEqualZero()) {
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
	
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList("RecommendBook_BookCallNoUnique","RecommendBook_BookISBNUnique","RecommendBook_BookCopyNumGreatThanEqualZero"));

}
