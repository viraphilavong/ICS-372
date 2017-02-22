/**
 * Created by AlexPhilavong on 5/16/2016.
 * subclass of position
 * stores data for longitude
 */
public class Longitude extends Position {
	
	private String northOrSouth;
	
    public Longitude(int degree, int minute, String northOrSouth){
    	
    	//super because it is inheriting these 2 varibles from position
    	
    	super(degree, minute);
    	this.northOrSouth = northOrSouth;
    }
    
	public String getNorthOrSouth() {
		return northOrSouth;
	}
	
	//calling the getter methods from abstract class position
	//again this helps prevent any issues when printing a string out in the driver
	//since this will return a line string instead of having to deal with other 
	//variables like int
	
	@Override
	public String toString() {
		return "Longitude [degree=" + getDegree() + ", minute=" + getMinute() + ", northOrSouth=" + northOrSouth + "]";
	}   
}
