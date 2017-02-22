import java.util.*;
import java.io.*;

/**
 * This class creates a serializable object Customer
 * @author Alex, Helina, Huy
 *
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String phone;
	private String id;
	private List<CreditCard> creditCard = new LinkedList<CreditCard>();	//Refactored the new LinkedList to be of object CreditCard [Generic Type Argument]
	private ArrayList<String> tickets = new ArrayList<String>();
	private final int MINIMUM_NUM_CARD=1;
	
	private static final String CUSTOMER_STRING = "Cu#";
	
	/**
	 * This is a customer.
	 * @param name : Customer's name
	 * @param address : Customer's address
	 * @param phone : Customer's phone number
	 * @param card : Customer's credit cards
	 */
	public Customer(String name, String address, String phone, CreditCard card){
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.id = CUSTOMER_STRING + (CustomerIdServer.Instance()).getId();
		creditCard.add(card);
	}
	
	/**
	 * Stores the card to the customer
	 * @param card : CreditCard to add
	 * @return boolean 
	 */
	public boolean addCard(CreditCard card){
		String cardNum=card.getCardNum();
		Iterator<CreditCard> cardIter = cardIterator();
		
		while(cardIter.hasNext()){
			CreditCard tempCard = cardIter.next();
			if(tempCard.getCardNum().equals(cardNum)){
				return false;
			}
		}
		creditCard.add(card);
		return true;
	}
	
	/**
	 * The cardIterator to iterate the collection
	 * @return iterator of the creditcard collection
	 */
	public Iterator<CreditCard> cardIterator(){
		Iterator<CreditCard> cardIter = creditCard.listIterator();
		return cardIter;
	}
	
	/**
	 * Method to remove the creditcard from the customer
	 * @param cardNum : Card number of the creditcard to be removed
	 * @return boolean
	 */
	public boolean removeCard(String cardNum){
		if(creditCard.size()>MINIMUM_NUM_CARD){
			Iterator<CreditCard> cardIter = cardIterator();
			while(cardIter.hasNext()){
				CreditCard tempCard = cardIter.next();
				if(tempCard.getCardNum().equals(cardNum)){
					System.out.println("found credit card");
					cardIter.remove();
					break;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * method to add string of tickets to customer
	 * @param showName
	 * @param quantity
	 */
	public void addTickets(String showName, String date, double price ,int quantity){
		String ticket= "Show: "+showName+", Showing Date: "+date+" Price/ticket: "+price+"Qty: "+quantity;
		tickets.add(ticket);
	}
	
	/**
	 * Getter for the Customer's name
	 * @return name : Customer's name
	 */
	public String getName(){
		return name;
		
	}
	
	/**
	 * Getter for the Customer's address
	 * @return address : Customer's address
	 */
	public String getAddress(){
		return address;
		
	}
	
	/**
	 * Getter for the Customer's phone number
	 * @return phone : Customer's phone number
	 */
	public String getPhone(){
		return phone;
		
	}
	
	/**
	 * Getter for the customer's ID
	 * @return id : Customer's ID
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Shows the customer's creditcard 
	 * @return boolean
	 */
	public boolean showCard(){
		if(creditCard.size()==MINIMUM_NUM_CARD){
			System.out.println("Cannot remove card, must have minimum of 1 card");
			return false;
		}
		else{
			Util.listToString(creditCard);
			return true;
		}
	}
	
	/**
	 * Method to return everything in a String data type.
	 */
	public String toString() {
		String info = "Name: " + name + " Address: " + address + " Phone: " + phone + " ID: " + id;
		info += Util.listToString(creditCard,"    ");
		return info;
	}
}
