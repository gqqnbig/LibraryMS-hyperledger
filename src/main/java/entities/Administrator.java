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
public class Administrator implements Serializable {
	
	/* all primary attributes */
	@Property()
	private String adminID;
	@Property()
	private String userName;
	@Property()
	private String password;
	
	/* all references */
	
	/* all get and set functions */
	public String getAdminID() {
		return adminID;
	}	
	
	public void setAdminID(String adminid) {
		this.adminID = adminid;
	}
	public String getUserName() {
		return userName;
	}	
	
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getPassword() {
		return password;
	}	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	/* all functions for reference*/
	

	/* invarints checking*/
	public boolean Administrator_AdministratorIDUnique() {
		
		if (StandardOPs.isUnique(((List<Administrator>)EntityManager.getAllInstancesOf("Administrator")), "AdminID")) {
			return true;
		} else {
			return false;
		}
	}
	
	//check all invariants
	public boolean checkAllInvairant() {
		
		if (Administrator_AdministratorIDUnique()) {
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
	
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList("Administrator_AdministratorIDUnique"));

}
