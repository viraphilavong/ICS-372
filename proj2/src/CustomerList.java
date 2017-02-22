import java.util.*;
import java.io.*;

/**
 * This class holds the customer objects
 * This class is serializable
 * @author Alex, Helina, Huy
 *
 */
public class CustomerList implements Serializable {
	
	private static final long serialVersionUId = 1L;
	private List customers = new LinkedList();
	private static CustomerList customerList;
	
	/**
	 * Singleton Pattern
	 */
	private CustomerList() {
		
	}
	
	/**
	 * Singleton Pattern
	 * @return singleton object customerList
	 */
	public static CustomerList instance() {
		if (customerList == null) {
			return (customerList = new CustomerList());
		} else {
			return customerList;
		}
	}
	
	/**
	 * Checks to see if the customerID matches a customer
	 * @param custID : Customer's ID
	 * @return Customer object if the customer exists
	 */
	public Customer search(String custId){
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			if (customer.getId().equalsIgnoreCase(custId)) {
				return customer;
			}
		}
		return null;
		
	}
	
	/**
	 * Inserts customer into customerList
	 * @param customer : The customer to insert
	 * @return boolean
	 */
	public boolean insertCustomer(Object customer){
		customers.add(customer);
		return true;
		
	}
	
	/**
	 * Removes the customer from customerList
	 * @param custId : The customer to remove based on ID
	 * @return boolean
	 */
	public boolean removeCustomer(String custId) {
		Object customer = search(custId);
		if (customer == null) {
			System.out.printf("ERR: Can not delete Client=%s - No Such Client\n", custId);
			return false;
		} else {
			return customers.remove(customer);
		}
		/*for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			if (customer.getId().equalsIgnoreCase(custId)) {
				return true;
			}
		}
		return false; */
	}
	
	/**
	 * Serialization
	 * @param output : The stream to output to
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try { 
			output.defaultWriteObject();
			output.writeObject(customerList);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * The iterator to list the customers
	 * @return The list of customers
	 */
	public Iterator getCustomerList(){
		return customers.listIterator();
	}
	
	@Override
	public String toString() {
		return Util.listToString(customers);
	}
}
