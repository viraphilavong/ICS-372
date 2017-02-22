import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Helina Belay
 *
 */

public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String showDate;
	private String ticketType;
	private double ticketPrice;
	private String serialNumber;

	
	private static final String TICKET_STRING = "T#";
	
	public Ticket(String showDate, String type){
		this.showDate = showDate;
		this.ticketType = type;
		this.serialNumber = TICKET_STRING + (TicketSnServer.instance()).getId();
	
	}
	
	public double sellTicket(Show show){
		switch (ticketType){
			case "regular":
				ticketPrice = show.getPrice();
				break;
			case "advance":
				ticketPrice = show.getPrice() - show.getPrice()* 0.03;
			case "student":
				ticketPrice = show.getPrice()* 0.02;
		}
		
		return ticketPrice;
	}

	public double getPrice() {
		return ticketPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.ticketPrice = finalPrice;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	

}
