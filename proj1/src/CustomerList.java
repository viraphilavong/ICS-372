import java.util.*;
import java.io.*;

public class CustomerList implements Serializable {
	
	private static final long serialVersionUId = 1L;
	private List customers = new LinkedList();
	private static CustomerList customerList;
	
	private CustomerList() {
		
	}
	
	public static CustomerList instance() {
		if (customerList == null) {
			return (customerList = new CustomerList());
		} else {
			return customerList;
		}
	}
	
	public Customer search(String custID){
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			if (customer.getId().equalsIgnoreCase(custID)) {
				return customer;
			}
		}
		return null;
		
	}
	
	public void insertCustomer(Customer customer){
		customers.add(customer);
		
	}
	
	public boolean removeCustomer(String custId) {
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			if (customer.getId().equalsIgnoreCase(custId)) {
				return true;
			}
		}
		return false;
	}
	
	private void writeObject(java.io.ObjectOutputStream output) {
		try { 
			output.defaultWriteObject();
			output.writeObject(customerList);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public Iterator getCustomerList(){
		return customers.listIterator();
	}
	
	@Override
	public String toString() {
		return Utili.listToString(customers);
	}
}
