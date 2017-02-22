/**
 * Created by AlexPhilavong on 5/16/2016.
 * subclass of position
 * stores data for latitude
 */
public class Latitude extends Position {
	
	private String eastOrWest;
	
    public Latitude(int degree, int minute, String eastOrWest){
    	
    	//super because it is inheriting these 2 varibles from position
    	
    	super(degree, minute);
    	this.eastOrWest = eastOrWest;	
    }

	public String getEastOrWest() {
		return eastOrWest;
	}
	
	//calling the getter methods from abstract class position
	//again this helps prevent any issues when printing a string out in the driver
	//since this will return a line string instead of having to deal with other 
	//variables like int
	
	@Override
	public String toString(){
		return "Latitude [degree=" + getDegree() + ", minute=" + getMinute() + ", eastOrWest=" + eastOrWest + "]";
	}
}
