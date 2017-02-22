import java.io.*;

public class CustomerIdServer implements Serializable{
	private int idCounter;
	private static CustomerIdServer server;
	
	private  CustomerIdServer() {
		idCounter = 1;
	}
	
	public static CustomerIdServer instance() {
		if (server == null) {
			return (server = new CustomerIdServer());
		} else {
			return server;
		}
	}
	
	public int getId() {
		return idCounter++;
	}
	
	@Override 
	public String toString() {
		return ("IdServer" + idCounter);
	}
	
	public static void retrieve(ObjectInputStream input) {
		try {
			server = (CustomerIdServer) input.readObject();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(Exception cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		try {
			output.defaultWriteObject();
			output.writeObject(server);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
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
