
//stores data for position
//in case user wants to edit temperature in a weather record, 
//the position will stay the same.

public abstract class Position {

	private int degree;
	private int minute;
	
	public Position(int degree, int minute){
		this.degree = degree;
		this.minute = minute;
	}
	
	public int getDegree() {
		return degree;
	}
	public int getMinute() {
		return minute;
	}	
}
