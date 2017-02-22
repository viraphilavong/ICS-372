import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * The collection class for CreditCard objects
 * @author Alex, Helina, Huy
 *
 */
public class CreditCardList implements Serializable {
	private static final long serialVersionUId = 1L;
	private List creditCard = new LinkedList();
	private static CreditCardList creditCardList;
	
	private CreditCardList() {
		
	}
	
	/**
	 * Singleton pattern
	 * @return singleton object
	 */
	public static CreditCardList instance() {
		if (creditCardList == null) {
			return (creditCardList = new CreditCardList());
		} else {
			return creditCardList;
		}
	}
	

	/**
	 * This method inserts the card into the list
	 * @param card : The CreditCard to insert
	 * @return boolean
	 */
	public boolean insertCard(CreditCard card) {
		if ( card != null) {
		creditCard.add(card);
		return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Returns iterator to get creditCard
	 * @return creditCard
	 */
	public Iterator getCreditCards() {
		return creditCard.iterator();
	}
	
	/**
	 * Serialization
	 * @param output : Stream to write to
	 */
	private void writeObjet(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(creditCardList);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	/**
	 * Serialization
	 * @param input : Stream to read from 
	 */
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (creditCardList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (creditCardList == null) {
					creditCardList = (CreditCardList) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch(IOException ioe) {
			System.out.println("in CreditCardList readObject \n" + ioe);
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	/**
	 * Return the entire list to a string
	 */
	public String toString() {
		return Util.listToString(creditCard);
	}

}
