import java.io.*;

/**
 * Class to create Customer's IDs
 * @author Alex, Helina, Huy
 *
 */
public class CustomerIdServer implements Serializable{
	private int idCounter;
	private static CustomerIdServer server;
	
	/**
	 * Private for singleton pattern
	 */
	private  CustomerIdServer() {
		idCounter = 1;
	}
	
	/**
	 * Singleton Pattern
	 * @return singleton object
	 */
	public static CustomerIdServer Instance() {
		if (server == null) {
			return (server = new CustomerIdServer());
		} else {
			return server;
		}
	}
	
	/**
	 * Getter for the id
	 * @return idCounter : Customer's ID
	 */
	public int getId() {
		return idCounter++;
	}
	
	@Override 
	public String toString() {
		return ("IdServer" + idCounter);
	}
	
	/**
	 * Retrieve server object
	 * @param input : Stream to deserialize from
	 */
	public static void retrieve(ObjectInputStream input) {
		try {
			server = (CustomerIdServer) input.readObject();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(Exception cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	/**
	 * Serialization
	 * @param output : Stream to output to
	 * @throws IOException
	 */
	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		try {
			output.defaultWriteObject();
			output.writeObject(server);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Serialization
	 * @param input : Stream to read from
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
		try {
			input.defaultReadObject();
			if (server == null) {
				server = (CustomerIdServer) input.readObject();
			} else {
				input.readObject();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
