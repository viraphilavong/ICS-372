import java.util.*;
import java.io.*;
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String phone;
	private String id;
	private List creditCard = new LinkedList();
	
	private static final String CUSTOMER_STRING = "C#";
	
	public Customer(String name, String address, String phone){
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.id = CUSTOMER_STRING + (CustomerIdServer.instance()).getId();
	}
	
	public void addCard(CreditCard card){
		creditCard.add(card);
	}
	
	public boolean removeCard(CreditCard card){
		if (creditCard.remove(card)) {
			return true;
		}
		return false;
	}
	
	public String getName(){
		return name;
		
	}
	
	public String getAddress(){
		return address;
		
	}
	
	public String getPhone(){
		return phone;
		
	}
	
	public String getId() {
		return id;
	}
	
	public Iterator getCreditCard(){
		return creditCard.listIterator();
		
	}
	
	public String toString() {
		String info = "Name: " + name + " Address: " + address + " Phone: " + phone + " ID: " + id;
		info += Utili.listToString(creditCard,"    ");
		return info;
	}
}
