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
public class Subject implements Serializable {
	public Object getPK() {
		return getName();
	}
	
	/* all primary attributes */
	@Property()
	private String name;
	
	/* all references */
	private Subject SuperSubject; 
	private List<Subject> SubSubject = new LinkedList<Subject>(); 
	
	/* all get and set functions */
	public String getName() {
		return name;
	}	
	
	public void setName(String name) {
		this.name = name;
	}
	
	/* all functions for reference*/
	public Subject getSuperSubject() {
		return SuperSubject;
	}	
	
	public void setSuperSubject(Subject subject) {
		this.SuperSubject = subject;
	}			
	public List<Subject> getSubSubject() {
		return SubSubject;
	}	
	
	public void addSubSubject(Subject subject) {
		this.SubSubject.add(subject);
	}
	
	public void deleteSubSubject(Subject subject) {
		this.SubSubject.remove(subject);
	}
	


}
