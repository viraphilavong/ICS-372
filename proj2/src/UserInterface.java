import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * UI class to interact with user,
 * @author Huy Trinh
 */
public class UserInterface {
	private static UserInterface userInterface;
	private static Theatre theatre;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static final int EXIT = 0;
	private static final int ADD_CLIENT = 1;
	private static final int REMOVE_CLIENT = 2;
	private static final int LIST_CLIENT = 3;
	private static final int ADD_CUSTOMER = 4;
	private static final int REMOVE_COSTUMER = 5;
	private static final int ADD_CARD = 6;
	private static final int REMOVE_CARD = 7;
	private static final int LIST_CUSTOMER = 8;
	private static final int ADD_SHOW = 9;
	private static final int LIST_SHOW = 10;
	private static final int SAVE = 11;
	private static final int RETRIEVE = 12;
	private static final int SELL_REGULAR = 13;
	private static final int SELL_ADVANCE = 14;
	private static final int SELL_STUDENT = 15;
	private static final int PAY_CLIENT = 16;
	private static final int PRINT_TICKET = 17;
	private static final int HELP = 18;
	
	/**
	 * Made private for singleton pattern.
	 * Conditionally looks for any saved data. Otherwise, it gets
	 * a singleton Theatre object.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			theatre = Theatre.instance();
		}
	}
	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}
	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}
	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}
	public void help() {
		System.out.println("\nEnter a number between 0 and 12 as explained below:");
		System.out.println(EXIT + "  to   Exit");
		System.out.println(ADD_CLIENT + "  to   Add a Client");
		System.out.println(REMOVE_CLIENT + "  to   Remove Client");
		System.out.println(LIST_CLIENT + "  to   List Client");
		System.out.println(ADD_CUSTOMER + "  to   Add Customer");
		System.out.println(REMOVE_COSTUMER + "  to   Remove Customer");
		System.out.println(ADD_CARD + "  to   Add Card");
		System.out.println(REMOVE_CARD + "  to   Remove Card");
		System.out.println(LIST_CUSTOMER + "  to   List Customer");
		System.out.println(ADD_SHOW + "  to   Add Show");
		System.out.println(LIST_SHOW + " to   List Show");
		System.out.println(SAVE + " to   Save Data");
		System.out.println(RETRIEVE + " to   Retrieve");
		System.out.println(SELL_REGULAR + " to   Sell Regular Tickets");
		System.out.println(SELL_ADVANCE + " to   Sell Advance Tickets");
		System.out.println(SELL_STUDENT + " to   Sell Student Tickets");
		System.out.println(PAY_CLIENT + " to   Pay Client");
		System.out.println(PRINT_TICKET + " to   Print All Ticketa");
		System.out.println(HELP + " for  Help");
	}
	/**
	 * Method to be called for saving the Theatre object.
	 * Uses the appropriate Theatre method for saving.
	 *  
	 */
	private void save() {
		if (theatre.save()) {
			System.out.println(" The Theatre has been successfully saved in the file TheatreData \n" );
		} else {
			System.out.println(" There has been an error in saving \n" );
		}
	}

	/**
	 * Method to be called for retrieving saved data.
	 * Uses the appropriate Theatre method for retrieval.
	 *  
	 */
	private void retrieve() {
		try {
			Theatre tempTheatre = Theatre.retrieve();
			if(tempTheatre==null){
				System.out.println("null theatre");
			}
			if (tempTheatre != null) {
				System.out.println(" The Theatre has been successfully retrieved from the file TheatreData \n" );
				theatre = tempTheatre;
			} else {
				System.out.println("File doesnt exist; creating new Theatre" );
				theatre = Theatre.instance();
			}
		} catch(Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				help();
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Method to be called for adding a Client.
	 * Prompts the user for the appropriate values and
	 * uses the appropriate Theatre method for adding the member.
	 *  
	 */
	public void addClient(){
		Client client;
		String name = getToken("Enter Client Name");
		String address = getToken("Enter Address");
		String phone = getToken("Enter Phone");
		
		client = theatre.addClient(name, address, phone);

		if (client == null) {
			System.out.println("Could not add Client");
		}
		System.out.println(client);
	}

	/**
	 * Method to called to remove a Client
	 * Prompts the user for client id
	 */
	public void removeClient(){
		String id = getToken("Enter Client id");
		if(theatre.removeClient(id)){
			System.out.println(id+ " has been removed");
		}
	}

	/**
	 * Method to called by the user to list all client available
	 */
	public void listClient(){
		theatre.listClient();
	}

	/**
	 * Method to call to add Customer 
	 */
	public void addCustomer(){
		Object customer;
		String name = getToken("Enter Customer Name");
		String address = getToken("Enter Address");
		String phone = getToken("Enter Phone");
		String creditNum = getToken("Enter Credit Card Number");
		String expNum = getToken("Enter Credit Card Expiration Date");
		customer= theatre.addCustomer(name, address, phone,creditNum,expNum);

		if (customer == null) {
			System.out.println("Could not add customer");
		}
		System.out.println(customer);
	}

	/**
	 * method to remove customer
	 */
	public void removeCustomer(){
		String id = getToken("Enter Customer id");
		if(theatre.removeCustomer(id)){
			System.out.println(id+ " has been removed");
		}
	}

	/**
	 * method to add card, check customer id first.
	 */
	public void addCard(){
		String id = getToken("Enter Customer id");
		Customer customer = theatre.checkCustomer(id);
		if(customer!=null){
			String creditNum = getToken("Enter Credit Card Number");
			String expNum = getToken("Enter Credit Card Expiration Date");
			theatre.addCard(customer,creditNum,expNum);
		}
	}

	/**
	 * method to list all show available
	 */
	public void listShow(){
		theatre.listShow();
	}

	/**
	 * Method to remove card
	 */
	public void removeCard(){
		String id = getToken("Enter Customer id");
		Customer customer = theatre.checkCustomer(id);
		if(customer!=null){
			String cardNum = getToken("Enter the card number you want to remove");
			if(theatre.removeCard(customer,cardNum))
				System.out.println("Card successfully removed");
		}
	}
	/**
	 * method to list all customer
	 */
	public void listCustomer(){
		theatre.listCustomer();
	}

	/**
	 * Method to add Show to Client
	 */
	public void addShow(){
		String id = getToken("Enter Client id");
		Client client = theatre.checkClient(id);
		if(client!=null){
		String showName = getToken("Enter The Name of The Show");
		String startDate = getToken("Enter Start Date in dd/mm/yyyy format");
		String duration = getToken("How Many days will the show run for?");
		String price = getToken("Enter the Price of the show");
		theatre.addShow(client, showName, startDate, duration,price); 
		}

	}
	
	/**
	 * 	method to sell regular tickets
	 */
	public void sellRegular() {
		String id = getToken("Enter Customer id");
		Customer customer = theatre.checkCustomer(id);
		if(customer!=null){
			String date = getToken("Enter date of the show in form of dd/mm/yyyy");
			String quantity = getToken("Enter amount of tickets you want to buy");
			String creditNum = getToken("Which credit card number do you want to pay with");
			System.out.println("Total Price Charged: "+theatre.sellTicket(customer, quantity, creditNum, date,"regular")); 
		}
	}
	
	/**
	 * 	method to sell advance tickets
	 */
	public void sellAdvance() {
		String id = getToken("Enter Customer id");
		Customer customer = theatre.checkCustomer(id);
		if(customer!=null){
			String date = getToken("Enter date of the show in form of dd/mm/yyyy");
			String quantity = getToken("Enter amount of tickets you want to buy");
			String creditNum = getToken("Which credit card number do you want to pay with");
			System.out.println("Total Price Charged: "+theatre.sellTicket(customer, quantity, creditNum, date,"advance"));		}
	}
	
	/**
	 * Method to sell student tickets
	 */
	public void sellStudent() {
		String id = getToken("Enter Customer id");
		Customer customer = theatre.checkCustomer(id);
		if(customer!=null){
			String date = getToken("Enter date of the show in form of dd/mm/yyyy");
			String quantity = getToken("Enter amount of tickets you want to buy");
			String creditNum = getToken("Which credit card number do you want to pay with");
			System.out.println("Total Price Charged: "+theatre.sellTicket(customer, quantity, creditNum, date,"student"));		
		}
	}
	
	/**
	 * method to pay client and update client balance
	 */
	public void payClient() {
		String id = getToken("Enter Client id");
		Client client = theatre.checkClient(id);
		String payment = getToken("How much do you want to withdraw?");
		System.out.println(theatre.payClient(client,payment));
	}
	
	public void printTicket() {
		String id = getToken("Enter Customer id");
		Object customer = theatre.checkCustomer(id);
	}
	
	/**
	 * Orchestrates the whole process.
	 * Calls the appropriate method for the different functionalties.
	 *  
	 */
	public void process() {
		int command;
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_CLIENT:            addClient();
			break;
			case REMOVE_CLIENT:         removeClient();
			break;
			case LIST_CLIENT:           listClient();
			break;
			case ADD_CUSTOMER:          addCustomer();
			break;
			case REMOVE_COSTUMER:       removeCustomer();
			break;
			case ADD_CARD:              addCard();
			break;
			case REMOVE_CARD:           removeCard();
			break;
			case LIST_CUSTOMER:         listCustomer();
			break;
			case ADD_SHOW:              addShow();
			break;
			case LIST_SHOW:             listShow();
			break;
			case SAVE:                  save();
			break;
			case RETRIEVE:              retrieve();
			break;
			case SELL_REGULAR:          sellRegular();
			break;
			case SELL_ADVANCE:          sellAdvance();
			break;
			case SELL_STUDENT:          sellStudent();
			break;
			case PAY_CLIENT:            payClient();
			break;
			case PRINT_TICKET:          printTicket();
			break;
			case HELP:                  help();
			break;
			}
		}
	}
	/**
	 *  The method to start the application. Simply calls process().
	 * @param args
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}
