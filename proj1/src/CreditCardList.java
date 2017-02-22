import java.util.*;

import javax.rmi.CORBA.Util;

import java.lang.*;
import java.io.*;

public class CreditCardList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List creditCard = new LinkedList();
	private static CreditCardList creditCardList;
	
	private CreditCardList() {
		
	}
	
	public static CreditCardList instance() {
		if (creditCardList == null) {
			return (creditCardList = new CreditCardList());
		} else {
			return creditCardList;
		}
	}
	
	public CreditCard search(String creditCardName) {
		for (Iterator iterator = creditCard.iterator(); iterator.hasNext();) {
			CreditCard creditCard = (CreditCard) iterator.next();
			if (creditCard.getName().equals(creditCardName)) {
				return creditCard;
			}
		}
		return null;
	}
	
	public boolean removeCard(String creditCardName) {
		CreditCard card = search(creditCardName);
		if (card == null) {
			return false;
		} else {
			return creditCard.remove(card);
		}
	}
	
	public boolean insertCard(CreditCard card) {
		return creditCard.add(card);
	}
	
	public Iterator getCreditCards() {
		return creditCard.listIterator();
	}
	
	private void writeObjet(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(creditCardList);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
	
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
	
	public String toString() {
		return Utili.listToString(creditCard);
	}

}
