import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Represents a single show 
 * @author Helina Belay
 *
 */
public class Show implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String clientId;
	private int duration;
	private Client showedBy;
	private String StartDate; //user input start date  in dd/mm/yyyy format
	
	private double price;
	
	private Calendar dateStart; 
	private Calendar dateEnd;
	
	private String ShowPeriod;   //holds string formated start to end date

	

	/**
	 * Creates a show with the given show name and client id
	 * @param name  show name
	 * @param clientId  the clients id
	 */
	public Show(String name, String clientId, String startDate , int showDuration,double price) {
		this.name = name;
		this.clientId = clientId;
		this.StartDate=startDate;
		this.duration=showDuration;
		this.price = price;

		this.dateStart=new GregorianCalendar();
		this.dateEnd=new GregorianCalendar();

	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Create a show for a client
	 * @param client to get assigned a show
	 */
	public void assignShowToClient(Client client) {
		showedBy = client;
		
		//Formatter object to convert date object to date  string form of month, day and year string
		DateFormat outputFormatter = new SimpleDateFormat("EEE, d MMM yyyy");

		
		try {
			//set the start date
			SimpleDateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
			
			dateStart.setTime(inputFormatter.parse(StartDate));
			dateEnd.setTime(inputFormatter.parse(StartDate));
			
			//format date string to month, day and year string
			ShowPeriod = outputFormatter.format(dateStart.getTime());
			
		} catch (ParseException e) {
			System.out.println("Invalid date format enterd: expected format = dd/MM/yyyy");
		}
		
		dateEnd.add(Calendar.DAY_OF_WEEK, this.duration-1);
		ShowPeriod += " - " + outputFormatter.format(dateEnd.getTime());
	}
	

	/**
	 * Marks the show as removed 
	 * @return The client who had reserved the show room
	 */
	public Client removeShow() {
		if (showedBy == null) {
			return null;
		} else {
			Client shower = showedBy;
			showedBy = null;
			return shower;
		}
	}


	/**
	 * Getter for show name	 
	 * @return name show title
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the client Id 
	 * @return id of the client
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Getter for show duration 
	 * @return duration of show
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Getter for shower client 
	 * @return the client who reserved the show
	 */
	public Client getShowedBy() {
		return showedBy;
	}

	/**
	 * Getter for show schedule 
	 * @return the show date
	 */
	public String getShowDate() {
		return (ShowPeriod);
	}
	
	/**
	 * Getter for show start Date 
	 * @return the show  start date
	 */
	public Date getStartDate() {
		return dateStart.getTime();
	}
	
	/**
	 * Getter for show end Date 
	 * @return the show  end date
	 */
	public Date getEndDate() {
		return dateEnd.getTime();
	}

	/**
	 * String form of the show class
	 */
	public String toString() {
	    return "Show:\""+ name + "\"  Shown by: " + showedBy.getName()+ " | Price: $"+price+"/ticket | Client ID:"+showedBy.getId()
	    	+" | Show Date: "+getShowDate();
	}

}
