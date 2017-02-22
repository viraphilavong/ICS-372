/**
 * Created by AlexPhilavong on 5/16/2016.
 * 
 * This class implements the interface WeatherRecord
 * where methods setMaxTemperature and setMinTemperature are overriden
 * 
 */
public class WeatherInformation implements WeatherRecord {
	
	private double minTemperature;
	private double maxTemperature; 

	private Latitude latitude;
	private Longitude longitude;

	public WeatherInformation (Latitude latitude, Longitude longitude, double
                               minTemperature, double maxTemperature){	
		this.latitude = latitude;
		this.longitude = longitude;
		this.maxTemperature = maxTemperature;
		this.minTemperature = minTemperature;
    }
	
	//need these methods because of the interface weatherrecord
	//in order to edit these two variables
	
    public void setMaxTemperature(double maxTemperature) {
    	this.maxTemperature = maxTemperature;
    }

    public void setMinTemperature(double minTemperature) {
    	this.minTemperature = minTemperature;
    }

    //using toString method so there isn't any issues with printing out anything that isn't a string
	@Override
	public String toString() {
		return "WeatherInformation [minTemperature=" + minTemperature + ", maxTemperature=" + maxTemperature
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
