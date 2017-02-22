/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010

 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * Theatre class to create client and customer.
 * @author Huy Trinh
 */
public class Theatre implements Serializable{
	private CustomerList customerList;
	private ClientList clientList;
	private TicketList ticketList;
	
	private ShowList showList;
	private static Theatre theatre;

	private Theatre(){
		customerList = CustomerList.instance();
		clientList = ClientList.instance();
		showList = ShowList.instance();  //need this to keep track of date
		ticketList = TicketList.instance();
		
	}
	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */

	public static Theatre instance() {
		if (theatre == null) {
			ClientIdServer.instance(); // instantiate all singletons
			TicketSnServer.instance();
			return theatre = new Theatre();
		} else {
			return theatre;
		}
	}
	/**
	 * Serializes the Theatre object
	 * @return true iff the data could be saved
	 */
	public static  boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("TheatreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(theatre);
			output.writeObject(ClientIdServer.instance());
			output.writeObject(CustomerIdServer.Instance());
			return true;
		} catch(IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
	/**
	 * Retrieves a deserialized version of the Theatre from disk
	 * @return a Theatre object
	 */
	public static Theatre retrieve() {
		try {
			FileInputStream file = new FileInputStream("TheatreData");
			ObjectInputStream input = new ObjectInputStream(file);
			theatre =(Theatre) input.readObject();
			ClientIdServer.retrieve(input);
			CustomerIdServer.retrieve(input);
			
			return theatre;
		} catch(IOException ioe) {
			System.out.println("enter IO exception");
			ioe.printStackTrace();  
			return null;
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * adding a Client
	 * @param name
	 * @param address
	 * @param phone
	 * @return indication on the outcome
	 */
	public Client addClient(String name, String address, String phone) {
		Client client = new Client(name, address, phone);
		clientList.insertClient(client);
		return client;
	}

	

	/**
	 * check client id , if valid then remove the id
	 * @param id
	 * @return T/F depend on condition
	 */
	public boolean removeClient(String id){

	    return clientList.removeClient(id);
     }


	/**
	 * method to list all client
	 */
	public void listClient(){
		System.out.println(clientList.toString());
	}

	/**
	 * add customer 
	 * @param name
	 * @param address
	 * @param phone
	 * @return indication on the outcome
	 */
	public Object addCustomer(String name, String address, String phone,String cardNum, String expNum){
		CreditCard card = new CreditCard(cardNum, expNum);
		Object customer = new Customer(name,address,phone,card);
		if (customerList.insertCustomer(customer)) {
			return (customer);
		}
		return null;
	}

	/**
	 * remove Customer
	 * @param id
	 * @return
	 */
	public boolean removeCustomer(String id){
		if(customerList.removeCustomer(id)){
			System.out.println("customer removed");
			return true;
		}
		else{
			System.out.println("invalid id");
			return false;
		}
	}

	/**
	 * method to search for customer id
	 * @param id
	 * @return
	 */
	public Customer checkCustomer(String id){
		Customer customer = customerList.search(id);
		if(customer==null){
			System.out.println("invalid id, please try again");
			System.out.println(customer);
			return null;
		}
		else 
			return customer;
	}
	
	/**
	 * Method to search for client id
	 * @param id
	 * @return
	 */
	public Client checkClient(String id){
		Client client = clientList.search(id);
		if(client!=null){
			System.out.println(client);
			return client;
		}
		else {
			System.out.println("invalid id, please try again");
			return null;
		}
	}

	/**
	 * add credit card onto customer
	 * @param customer
	 * @param cardNum
	 * @param expNum
	 */
	public boolean addCard(Customer customer, String cardNum, String expNum){
		CreditCard card = new CreditCard(cardNum, expNum);
		if(customer.addCard(card)){
			return true;
		}
		else{
			System.out.println("Unable to add Credit Card");
			return false;
		}
	}
	
	/**
	 * Remove Credit card from Customer
	 * @param id
	 * @return True if successfully removed a card
	 */
	public boolean removeCard(Customer customer, String cardNum){
		if(customer.removeCard(cardNum)){
			return true;
		}
		return false;
	}
	
	/**
	 * method to list all shows
	 */
	public void listShow(){
		System.out.println(showList.toString());
	}
	
	/**
	 * method to list all customers
	 */
	public void listCustomer(){
		System.out.println(customerList);
	}
	
	/**
	 * method to add Show
	 * @param id
	 * @param showName
	 * @param startDate
	 * @param duration
	 */
	public void addShow(Client client, String showName, String startDate, String duration, String price){
		int durationPeriod = Integer.parseInt(duration);
		double ShowPrice = Double.parseDouble(price);
		
		Show show = new Show(showName, client.getId(), startDate, durationPeriod,ShowPrice);
		
		//assign the show to the client found above
		show.assignShowToClient(client);
		if (showList.insertShow(show))
			client.addShow(show);
		else {
			System.out.printf("The Date: %s has already been assigned. Try another date\n",show.getShowDate());
		}
	}
	
	/**
	 * method to sell regular tickets
	 * 
	 * @param customer
	 * @param quantity
	 * @param creditNum
	 * @param date
	 */
	public double sellTicket(Customer customer,String quantity, String creditNum, String date, String type) {
		
		int amount = Integer.parseInt(quantity);//NOTE i think we need to add a for loop for this
		double totalPrice = 0;
		
		//search a show by date. date must be in dd/mm/yyyy format
		Show selectedShow = showList.searchShowByDate(date);
		customer.addTickets(selectedShow.getName(), date, selectedShow.getPrice(), amount);
		for (int i = 0; i < amount; i++) 
		{
			//create the ticket object 
			Ticket ticket = new Ticket(date, type);
			//calculate price and set price
			totalPrice =+ ticket.sellTicket(selectedShow);
			//add ticket to collection
			ticketList.insertTicket(ticket);
		
		}
		return totalPrice;
	}
	
	/**
	 * method to pay client
	 * 
	 * @param client
	 * @param payment
	 * @return String statement depending on the outcome
	 */
	public String payClient(Client client, String payment) {
		int amount = Integer.parseInt(payment);
		if(client.getBalance()<amount){
			return "Insufficient fund in balance";
		}
		client.setBalance(client.getBalance()-amount);
		return "Withdraw done, Updated client new balance";
		
	}
	
	public void printTicket() {
	}
}
