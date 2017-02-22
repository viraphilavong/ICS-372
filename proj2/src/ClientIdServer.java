
import java.io.*;
/**
 * Generates client ids.
 * @author Helina Belay
 *
 */
public class ClientIdServer implements Serializable {
  private  int idCounter;
  private static ClientIdServer server;
  /*
   * Private constructor for singleton pattern
   * 
   */
  private ClientIdServer() {
    idCounter = 1;
  }
  /**
   * Supports the singleton pattern
   * @return the singleton object
   */
  public static ClientIdServer instance() {
    if (server == null) {
      return (server = new ClientIdServer());
    } else {
      return server;
    }
  }
  /**
   * Getter for id
   * @return id of the client
   */
  public int getId() {
    return idCounter++;
  }
  /** 
   * String form of the collection
   * 
  */
  @Override
  public String toString() {
    return ("IdServer" + idCounter);
  }
  /**
   * Retrieves the server object
   * @param input inputstream for deserialization
   */
  public static void retrieve(ObjectInputStream input) {
    try {
      server = (ClientIdServer) input.readObject();
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  /*
   * Supports serialization
   * @param output the stream to be written to
   */
  private void writeObject(java.io.ObjectOutputStream output) throws IOException {
    try {
      output.defaultWriteObject();
      output.writeObject(server);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  /*
   * Supports serialization
   * @param input the stream to be read from
   */
  private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
    try {
      input.defaultReadObject();
      if (server == null) {
        server = (ClientIdServer) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
}