import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CreditCard implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String custId;
	private String cardNum;
	private String expDate;
	private Customer person;
	
	public CreditCard(String name, String custId, String cardNum, String expDate) {
		this.name = name;
		this.custId = custId;
		this.cardNum = cardNum;
		this.expDate = expDate;
	}
	
	public void assignCardToCustomer(Customer customer) {
		person = customer;
	}
	
	public Customer removeCard() {
		if (person == null) {
			return null;
		} else {
			Customer customer = person;
			person = null;
			return customer;
		}
	}

	public String getName() {
		return name;
	}
	
	public String getCustomerId() {
		return custId;
	}
	
	public String getCardNum() {
		return cardNum;
	}
	
	public String getExpDate() {
		return expDate;
	}	
	
	public String toString() {
		return "Name: " + name + " Card Number: " + cardNum + " Card Expiration Date: " + expDate + " Customer ID: " + custId;  
	}

}
