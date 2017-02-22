
import java.util.*;
import java.io.*;
/**
 * The collection class for client objects
 * @author Helina Belay, Huy, Alexander
 *
 */
public class TicketList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List tickets = new LinkedList();
  
  private static TicketList ticketList;
  
  /*
   * Private constructor for singleton pattern
   * 
   */
  private TicketList() {
  }
  /**
   * Supports the singleton pattern 
   * @return the singleton object
   */
  public static TicketList instance() {
    if (ticketList == null) {
      return (ticketList = new TicketList());
    } else {
      return ticketList;
    }
  }
  

  /**
   * Checks whether a ticket with a given serial number.
   * @param serialNumber the serial number of the ticket
   * @return Ticket object if ticket exists, returns null otherwise
   * 
   */
  public Ticket search(String serialNumber) {
    for (Iterator iterator = tickets.iterator(); iterator.hasNext(); ) {
      Ticket ticket = (Ticket) iterator.next();
      if (ticket.getSerialNumber().equalsIgnoreCase(serialNumber)) {
        return ticket;
      }
    }
    return null;
  }
  

  /**
   * Inserts a ticket into the collection
   * @param ticket the ticket objetc to be inserted
   */
  public void insertTicket(Ticket ticket) {
	  tickets.add(ticket);
  }
  

  /*
   * Supports serialization
   * @param output the stream to be written to
   */
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(ticketList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  /*
   * Supports serialization
   *  @param input the stream to be read from
   */
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (ticketList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (ticketList == null) {
        	ticketList = (TicketList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  

  /** 
   * String form of the collection
   */
  @Override
  public String toString() {
	  return Util.listToString(tickets);
  }

}