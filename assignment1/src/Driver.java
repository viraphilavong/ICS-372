/**
 * Created by AlexPhilavong on 5/16/2016.
 * 
 * Create 10 different latitude objects
 * Create 10 different longitude objects
 * half north half south
 * half west/east prime meridian
 * create 10 weather record for 10 locations
 * max must be more than min every loca
 * half min temp need to be nega
 * print string rep of records
 * change max/min of one loca and print new info
 */
public class Driver {
	
	public static WeatherInformation weatherinfo;
	public static Longitude longi;
	public static Latitude lati;
	private static double maxTemp;
	private static double minTemp;
	private static int degree;
	private static int minute;
	private static String direction;
	private static String direction2;
	
	public static void main(String[] args){
		
		//create 10 objects
		
		for (int i = 0; i < 10; i++){
			
			//satisfies the following 
			//minTemp is always < maxTemp
			//half of objects position will be north, west
			
			if (i < 5){
			minTemp = (Math.random()*30);
			maxTemp = (Math.random()*90)+minTemp;
			degree = (int) (Math.random()*5)*10;
			minute = (int) (Math.random()*4)*10;
			direction = "N";
			direction2 = "W";
			}
			
			//satisfies the following
			//minTemp is always < maxTemp
			//minTemp is negative at least half the time of all objects created
			//at least half the objects are south, east
			
			else {
				minTemp = (Math.random()*30)-31;
				maxTemp = (Math.random()*90)+minTemp;				
				degree = (int) (Math.random()*10)*10;
				minute = (int) (Math.random()*7)*10;
				direction = "S";
				direction2 = "E";
			}
			
			//create longi and lati objects with parameters of degree, minute, direction
			
			longi = new Longitude(degree, minute, direction);
			lati = new Latitude(degree, minute, direction2);
			
			//create weatherinfo object with parameters lati, longi, minTemp, maxTemp
			
			weatherinfo = new WeatherInformation(lati, longi, minTemp, maxTemp);	
			
			//print out weatherinfo objects in format 
			
			System.out.println(weatherinfo);
		}
		
		//edits the last object's min and max temperature with the same position.
		
		weatherinfo.setMaxTemperature(10);
		weatherinfo.setMinTemperature(0);
		System.out.println("New Record: " + weatherinfo);		
	}
}
