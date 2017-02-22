import java.io.IOException;
import java.util.Iterator;
import java.util.ListIterator;

public class Driverr {

	private static Theater myTheather;
	
	public static void main(String[] args) {
		myTheather = Theater.instance();
		
		myTheather.addCustomer("James Brown", "1234 Happy Street", "612-466-6778");
		myTheather.addCustomer("John Doe", "1977 Wall Street", "401-466-6778");
		myTheather.addCustomer("Benjamin Franklyn", "9999 First Avenue", "501-466-6778");
		
		System.out.println("Customer List:-\n"+ myTheather.getCustomerList());
		
		
		//name, id, cardnum, exp date
		myTheather.addCreditCard("James Brown", "C#1", "123456789", "05/06/2016");
		myTheather.addCreditCard("John Doe", "C#1", "9876543210", "11/06/2016");
		myTheather.addCreditCard("Benjamin Franklyn", "C#1", "0123456987", "04/06/2016");
		
		myTheather.removeCustomer("C#1");
	
	    System.out.println(myTheather.toString());

	}

}
