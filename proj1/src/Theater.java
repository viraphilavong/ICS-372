
import java.util.*;
import java.io.*;

public class Theater implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int CARD_NOT_FOUND = 1;
	public static final int CARD_NOT_ASSIGNED = 2;
	public static final int CARD_ASSIGNED = 3;

	public static final int OPERATION_COMPLETED = 7;
	public static final int OPERATION_FAILED = 8;
	public static final int NO_SUCH_CUSTOMER = 9;

	private CreditCardList listOfCards;
	private CustomerList customerList;

	private static Theater theater;
	
	private Theater() {
		listOfCards = CreditCardList.instance();
		customerList = CustomerList.instance();
	}
	
	public static Theater instance() {
		if (theater == null) {
			CustomerIdServer.instance();
			return (theater = new Theater());
		}
		else {
			return theater;
		}
	}
	
	public int addCreditCard(String name, String custId, String cardNum, String expDate) {
		CreditCard creditCard = new CreditCard(name, custId, cardNum, expDate);
		Customer customer = customerList.search(custId);
		if (customer == null) {
			System.out.printf("FAIL: Customer id=%s not found\n", custId);
			return NO_SUCH_CUSTOMER;
		}
		else {
			creditCard.assignCardToCustomer(customer);
			if (listOfCards.insertCard(creditCard)) {
				customer.addCard(creditCard);
			} 
			else {
				System.out.println("FAIL: Card already assigned.");
				return CARD_NOT_ASSIGNED;
			}
		}
		return OPERATION_COMPLETED;
	}
	
	public void addCustomer(String name, String address, String phone) {
		Customer customer = new Customer(name, address, phone);
		customerList.insertCustomer(customer);
	}
	
	public Customer searchCustomer(String custId){
		return customerList.search(custId);
	}
	
	public Iterator getCustomerCards(String custId) {
		Customer customer = customerList.search(custId);
		if (customer == null) {
			return (null);
		}
		else {
			return (customer.getCreditCard());
		}
	}
	
	public Iterator getCustomerListIterator() {
		return customerList.getCustomerList();
	}
	
	public String getCustomerList() {
		return customerList.toString();
	}
	
	public Iterator getCreditCardListIterator() {
		return listOfCards.getCreditCards();
	}
	
	public String getCardList() {
		return listOfCards.toString();
	}
	
	public boolean removeCard(String name) {
		return listOfCards.removeCard(name);
	}
	
	public boolean removeCustomer(String CustomerId) {
		return customerList.removeCustomer(CustomerId);
	}
	
	  /**
	   * Retrieves a de-serialized version of the library from disk
	   * @return a Library object
	   */
	  public static Theater retrieve() {
	    try {
	      FileInputStream file = new FileInputStream("TheaterData");
	      ObjectInputStream input = new ObjectInputStream(file);
	      input.readObject();
	      CustomerIdServer.retrieve(input);
	      return theater;
	    } catch(IOException ioe) {
	      ioe.printStackTrace();
	      return null;
	    } catch(ClassNotFoundException cnfe) {
	      cnfe.printStackTrace();
	      return null;
	    }
	  }
	  /**
	   * Serializes the Library object
	   * @return true iff the data could be saved
	   */
	  public static  boolean save() {
	    try {
	      FileOutputStream file = new FileOutputStream("TheaterData");
	      ObjectOutputStream output = new ObjectOutputStream(file);
	      output.writeObject(theater);
	      output.writeObject(CustomerIdServer.instance());
	      return true;
	    } catch(IOException ioe) {
	      ioe.printStackTrace();
	      return false;
	    }
	  }
	  /**
	   * Writes the object to the output stream
	   * @param output the stream to be written to
	   */
	  private void writeObject(java.io.ObjectOutputStream output) {
	    try {
	      output.defaultWriteObject();
	      output.writeObject(theater);
	    } catch(IOException ioe) {
	      System.out.println(ioe);
	    }
	  }
	  /**
	   * Reads the object from a given stream
	   * @param input the stream to be read
	   */
	  private void readObject(java.io.ObjectInputStream input) {
	    try {
	      input.defaultReadObject();
	      if (theater == null) {
	        theater = (Theater) input.readObject();
	      } else {
	        input.readObject();
	      }
	    } catch(IOException ioe) {
	      ioe.printStackTrace();
	    } catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
	  /** String form of the library
	  * 
	  */
	  @Override
	  public String toString() {
	    return listOfCards + "\n" + customerList;
	  }
	
}
