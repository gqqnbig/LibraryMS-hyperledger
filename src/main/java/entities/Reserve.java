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
public class Reserve implements Serializable {

	// Without @JsonProperty, genson will not set this field during deserialization.
	@JsonProperty
	private final String guid = EntityManager.getGuid();
	public Object getPK() {
		return guid;
	}
	
	/* all primary attributes */
	@Property()
	private LocalDate reserveDate;
	@Property()
	private boolean isReserveClosed;
	
	/* all references */
	@JsonProperty
	private Object ReservedCopyPK;
	private BookCopy ReservedCopy; 
	@JsonProperty
	private Object ReservedUserPK;
	private User ReservedUser; 
	
	/* all get and set functions */
	@JsonConverter(LocalDateConverter.class)
	public LocalDate getReserveDate() {
		return reserveDate;
	}	
	
	@JsonConverter(LocalDateConverter.class)
	public void setReserveDate(LocalDate reservedate) {
		this.reserveDate = reservedate;
	}
	public boolean getIsReserveClosed() {
		return isReserveClosed;
	}	
	
	public void setIsReserveClosed(boolean isreserveclosed) {
		this.isReserveClosed = isreserveclosed;
	}
	
	/* all functions for reference*/
	@JsonIgnore
	public BookCopy getReservedCopy() {
		if (ReservedCopy == null)
			ReservedCopy = EntityManager.getBookCopyByPK(ReservedCopyPK);
		return ReservedCopy;
	}	
	
	public void setReservedCopy(BookCopy bookcopy) {
		this.ReservedCopy = bookcopy;
		this.ReservedCopyPK = bookcopy.getPK();
	}			
	@JsonIgnore
	public User getReservedUser() {
		if (ReservedUser == null)
			ReservedUser = EntityManager.getUserByPK(ReservedUserPK);
		return ReservedUser;
	}	
	
	public void setReservedUser(User user) {
		this.ReservedUser = user;
		this.ReservedUserPK = user.getPK();
	}			
	


}
