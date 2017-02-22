
import java.util.*;
import java.io.*;
/**
 * The collection class for client objects
 * @author Helina Belay, Huy, Alexander
 *
 */
public class ClientList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List clients = new LinkedList();
  
  private static ClientList clientList;
  
  /*
   * Private constructor for singleton pattern
   * 
   */
  private ClientList() {
  }
  /**
   * Supports the singleton pattern 
   * @return the singleton object
   */
  public static ClientList instance() {
    if (clientList == null) {
      return (clientList = new ClientList());
    } else {
      return clientList;
    }
  }
  

  /**
   * Checks whether a client with a given client id exists.
   * @param clientId the id of the client
   * @return Client object if client exists, returns null otherwise
   * 
   */
  public Client search(String clientId) {
    for (Iterator iterator = clients.iterator(); iterator.hasNext(); ) {
      Client client = (Client) iterator.next();
      if (client.getId().equalsIgnoreCase(clientId)) {
        return client;
      }
    }
    return null;
  }
  

  /**
   * Inserts a client into the collection
   * @param client the client to be inserted
   */
  public void insertClient(Client client) {
	  clients.add(client);
  }
  
  /**
   * Removes a client from the collection
   * @param clientId the client to be removed
   * @return true if the client was successfully removed
   */
  public boolean removeClient(String clientId) {
	    Client client = search(clientId);
	    if (client == null) {
          System.out.printf("ERR: Can not delete Client=%s - No Such Client\n", clientId);	    		
	      return false;
	    }
	    else if (client.hasShows())
	    {
	          System.out.printf("ERR: Can not delete Client=%s - Has Show Reserved\n", clientId);	    		
		      return false;  
	    }
  		else 
	      return clients.remove(client);
	    
  }
  /*
   * Supports serialization
   * @param output the stream to be written to
   */
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(clientList);
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
      if (clientList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (clientList == null) {
        	clientList = (ClientList) input.readObject();
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
	 * Returns iterator to list of Clients
	 * @return list of clients
	 */
	public Iterator getClientList() {
		return clients.listIterator();

	}
  /** 
   * String form of the collection
   */
  @Override
  public String toString() {
	  return Util.listToString(clients);
  }

}