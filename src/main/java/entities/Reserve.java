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
	
	/* all primary attributes */
	@Property()
	private LocalDate reserveDate;
	@Property()
	private boolean isReserveClosed;
	
	/* all references */
	private BookCopy ReservedCopy; 
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
	public BookCopy getReservedCopy() {
		return ReservedCopy;
	}	
	
	public void setReservedCopy(BookCopy bookcopy) {
		this.ReservedCopy = bookcopy;
	}			
	public User getReservedUser() {
		return ReservedUser;
	}	
	
	public void setReservedUser(User user) {
		this.ReservedUser = user;
	}			
	


}
