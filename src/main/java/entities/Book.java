package entities;

import org.json.JSONPropertyIgnore;
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
public class Book implements Serializable {
	public Object getPK() {
		return getCallNo();
	}
	
	/* all primary attributes */
	@Property()
	private String callNo;
	@Property()
	private String title;
	@Property()
	private String edition;
	@Property()
	private String author;
	@Property()
	private String publisher;
	@Property()
	private String description;
	@Property()
	private String isbn;
	@Property()
	private int copyNum;
	
	/* all references */
	@JsonProperty
	private List<Object> CopysPKs = new ArrayList<>();
	private List<BookCopy> Copys;
	@JsonProperty
	private List<Object> SubjectPKs = new LinkedList<>();
	private List<Subject> Subject;

	/* all get and set functions */
	public String getCallNo() {
		return callNo;
	}	
	
	public void setCallNo(String callno) {
		this.callNo = callno;
	}
	public String getTitle() {
		return title;
	}	
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEdition() {
		return edition;
	}	
	
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getAuthor() {
		return author;
	}	
	
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}	
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getDescription() {
		return description;
	}	
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsbn() {
		return isbn;
	}	
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getCopyNum() {
		return copyNum;
	}	
	
	public void setCopyNum(int copynum) {
		this.copyNum = copynum;
	}
	
	/* all functions for reference*/
	@JsonIgnore
	@JSONPropertyIgnore
	public List<BookCopy> getCopys() {
		if (Copys == null)
			Copys = CopysPKs.stream().map(EntityManager::getBookCopyByPK).collect(Collectors.toList());
		return Copys;
	}	
	
	public void addCopys(BookCopy bookcopy) {
		getCopys();
		this.CopysPKs.add(bookcopy.getPK());
		this.Copys.add(bookcopy);
	}
	
	public void deleteCopys(BookCopy bookcopy) {
		getCopys();
		this.CopysPKs.remove(bookcopy.getPK());
		this.Copys.remove(bookcopy);
	}
	@JsonIgnore
	@JSONPropertyIgnore
	public List<Subject> getSubject() {
		if (Subject == null)
			Subject = SubjectPKs.stream().map(EntityManager::getSubjectByPK).collect(Collectors.toList());
		return Subject;
	}	
	
	public void addSubject(Subject subject) {
		getSubject();
		this.SubjectPKs.add(subject.getPK());
		this.Subject.add(subject);
	}
	
	public void deleteSubject(Subject subject) {
		getSubject();
		this.SubjectPKs.remove(subject.getPK());
		this.Subject.remove(subject);
	}
	

	/* invarints checking*/
	public boolean Book_BookCallNoUnique() {
		
		if (StandardOPs.isUnique(((List<Book>)EntityManager.getAllInstancesOf("Book")), "CallNo")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean Book_BookISBNUnique() {
		
		if (StandardOPs.isUnique(((List<Book>)EntityManager.getAllInstancesOf("Book")), "ISBn")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean Book_BookCopyNumGreatThanEqualZero() {
		
		if (copyNum >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//check all invariants
	public boolean checkAllInvairant() {
		
		if (Book_BookCallNoUnique() && Book_BookISBNUnique() && Book_BookCopyNumGreatThanEqualZero()) {
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
	
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList("Book_BookCallNoUnique","Book_BookISBNUnique","Book_BookCopyNumGreatThanEqualZero"));

}
