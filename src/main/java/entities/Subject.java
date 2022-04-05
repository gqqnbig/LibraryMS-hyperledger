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
public class Subject implements Serializable {
	public Object getPK() {
		return getName();
	}
	
	/* all primary attributes */
	@Property()
	private String name;
	
	/* all references */
	@JsonProperty
	private Object SuperSubjectPK;
	private Subject SuperSubject; 
	@JsonProperty
	private List<Object> SubSubjectPKs = new LinkedList<>();
	private List<Subject> SubSubject = new LinkedList<Subject>(); 
	
	/* all get and set functions */
	public String getName() {
		return name;
	}	
	
	public void setName(String name) {
		this.name = name;
	}
	
	/* all functions for reference*/
	@JsonIgnore
	public Subject getSuperSubject() {
		if (SuperSubject == null)
			SuperSubject = EntityManager.getSubjectByPK(SuperSubjectPK);
		return SuperSubject;
	}	
	
	public void setSuperSubject(Subject subject) {
		this.SuperSubject = subject;
		this.SuperSubjectPK = subject.getPK();
	}			
	@JsonIgnore
	public List<Subject> getSubSubject() {
		if (SubSubject == null)
			SubSubject = SubSubjectPKs.stream().map(EntityManager::getSubjectByPK).collect(Collectors.toList());
		return SubSubject;
	}	
	
	public void addSubSubject(Subject subject) {
		getSubSubject();
		this.SubSubjectPKs.add(subject.getPK());
		this.SubSubject.add(subject);
	}
	
	public void deleteSubSubject(Subject subject) {
		getSubSubject();
		this.SubSubjectPKs.remove(subject.getPK());
		this.SubSubject.remove(subject);
	}
	


}
