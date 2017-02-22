import java.io.Serializable;

/**
 * This class is used to create an object CreditCard. 
 * CreditCard is an object that gets serialized.
 * @author Alex, Helina, Huy
 */
public class CreditCard implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cardNum;
	private String expDate;
	
	/**
	 * Represents a single CreditCard
	 * @param cardNum : Card number of the CreditCard
	 * @param expDate : Expiration date of the CreditCard
	 */
	public CreditCard(String cardNum, String expDate) {
		this.cardNum = cardNum;
		this.expDate = expDate;
	}
	
	/**
	 * Getter for the card number of the CreditCard
	 * @return cardNum : Card number of the CreditCard
	 */
	public String getCardNum() {
		return cardNum;
	}
	
	/**
	 * Getter for the expiration date of the CreditCard
	 * @return expDate : Expiration date of the CreditCard
	 */
	public String getExpDate() {
		return expDate;
	}	
	
	public String toString() {
		return " Card Number: " + cardNum + " Card Expiration Date: " + expDate;  
	}

}
