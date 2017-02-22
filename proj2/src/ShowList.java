import java.util.*;
import java.lang.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
/**
 * The collection class for show objects
 * @author Helina Belay
 *
 */
public class ShowList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List shows = new LinkedList();
  private static ShowList showList;
  
  /*
   * Private constructor for singleton pattern
   */
  private ShowList() {
  }
  /**
   * Supports the singleton pattern
   * @return the singleton object
   */
  public static ShowList instance() {
    if (showList == null) {
      return (showList = new ShowList());
    } else {
      return showList;
    }
  }
  /**
   * Checks whether a show with a given name exists.
   * @param showName the name of the show
   * @return true if the show exists
   * 
   */
  public Show search(String showName) {
    for (Iterator iterator = shows.iterator(); iterator.hasNext(); ) {
      Show show = (Show) iterator.next();
      if (show.getName().equals(showName)) {
        return show;
      }
    }
    return null;
  }
  
  /**
   * Checks whether a show with a given show date exists.
   * @param startDate 
   * @param endDate 
   * @return true if the show date does not exists
   * 
   */
  public boolean dateValid(Date startDate, Date endDate) {
    for (Iterator iterator = shows.iterator(); iterator.hasNext(); ) {
      Show show = (Show) iterator.next();
      if (dateOverlap(show.getStartDate(),show.getEndDate(), startDate, endDate)) {
        return false;
      }
    }
    return true;
  }
  
  boolean dateOverlap(Date start1, Date end1, Date start2, Date end2){
	    return start1.getTime() <= end2.getTime() && start2.getTime() <= end1.getTime(); 
	}
  
  //Search the show by date, must enter show date in form of dd/mm/yyyy
  public Show searchShowByDate(String showDate) {
	  
		//conver the show date
		SimpleDateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dateOfShow = new GregorianCalendar();
		try {
			dateOfShow.setTime(inputFormatter.parse(showDate));
		} catch (ParseException e) {
			return null;
		}
		
	    for (Iterator iterator = shows.iterator(); iterator.hasNext(); ) {
	      Show show = (Show) iterator.next();
	      if (dateWithIn(show.getStartDate(),show.getEndDate(), dateOfShow.getTime())) {
	        return show;
	      }
	    }
	    return null;
	  }
  
  boolean dateWithIn(Date start1, Date end1, Date date2Check){
	    return date2Check.getTime() >= start1.getTime() && date2Check.getTime() <= end1.getTime(); 
	}
  /**
   * Removes a show from the show list
   * @param showName name of the show
   * @return true if show could be removed
   */
  public boolean removeShow(String showName) {
    Show show = search(showName);
    if (show == null) {
      System.out.printf("ERR: Can not delete Show=%s - Not Found\n", showName);	 
      return false;
    } 
    else 
    {
      return shows.remove(show);
    }
  }
  /**
   * Inserts a show into the collection
   * @param show the show to be inserted
   * @return true if the show could be inserted.
   */
  public boolean insertShow(Show show) {
	  if(dateValid(show.getStartDate(),show.getEndDate()))
	  {
		  shows.add(show);
		  return true;
	  }	  
    return false;
  }
  /**
   * Returns an iterator to all shows
   * @return iterator to the collection
   */
  public Iterator getShows() {
    return shows.iterator();
  }
  /*
   * Supports serialization
   * @param output the stream to be written to
   */
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(showList);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  /*
   * Supports serialization
   *  @param input the stream to be read from
   */
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (showList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (showList == null) {
          showList = (ShowList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      System.out.println("in ShowList readObject \n" + ioe);
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  /** 
   * String form of the collection
   */
  public String toString() {
      return Util.listToString(shows);
  }
}
