
import java.util.*;
import java.io.*;

/**
 * @author Helina Belay
 *
 */
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String phone;
	private String id;
	private int balance;
	private List shows = new LinkedList();
	
	private static final String CLIENT_STRING = "C#";

	/**
	 * Represents a single client
	 * @param name  client name
	 * @param address  address of the client
	 * @param phone  client phone number
	 */
	public Client(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.id = CLIENT_STRING + (ClientIdServer.instance()).getId();
		this.balance = 0;
	}

	/**
	 * Stores the show as assigned to the client
	 * @param show to be added to client
	 */
	public void addShow(Show show) {
		shows.add(show);
	}
	
	/**
	 * Marks the show as not assigned to the client
	 * @param show the show to be removed
	 * @return true if the show could be marked removed
	 */
	public boolean removeShow(Show show) {
		if (shows.remove(show)) {
			return true;
		}
		return false;
	}

	/**
	 * Gets an iterator to the shows assigned to client
	 * @return the iterator to the collection of shows
	 */
	public Iterator getShows() {
		return shows.listIterator();
	}
	
	/**
	 * Returns true if Client has shows reserved
	 * @return true/false
	 */
	public boolean hasShows() {
		return shows.size()>0;
	}

	/**
	 * Getter for the id of client
	 * @return id client id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Getter for the Client name
	 * @return clients name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * getter for Client balance
	 * @return
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * setter for client balance
	 * @param balance
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * Checks to see if the id passed matches the client id
	 * @param id of the client to check for equality
	 * @return true if the client id matches
	 */
	public boolean equals(String id) {
		return this.id.equalsIgnoreCase(id);
	}

	public String toString() {
		String info ="Name:" + name + "     Address:" + address + "    Phone:" + phone + "    ID: " + id + "    Balance:" + balance;
		info += Util.listToString(shows,"    ");
		return info;
	}
}
