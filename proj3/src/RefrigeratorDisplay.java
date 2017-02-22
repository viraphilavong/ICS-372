
import java.util.Observable;

/**
 * Specifies what the display system should do. Note that the implementation has
 * a lot of freedom to choose its display.
 */
public abstract class RefrigeratorDisplay extends Observable {
	protected static FridgeContext fridgeContext;
	protected static FreezerContext freezerContext;
	protected static RefrigeratorDisplay instance;
	
	public enum Type {FRIDGE,FREEZR};
	
	/**
	 * Initializes the context and instance
	 */
	protected RefrigeratorDisplay() {
		instance = this;
		fridgeContext = FridgeContext.instance();
		freezerContext = FreezerContext.instance();
	}

	/**
	 * For singleton
	 * @return the object
	 */
	public static RefrigeratorDisplay instance() {
		return instance;
	}

	/**
	 * Do the initializations to make the context an observer
	 */
	public void initialize() {
		instance().addObserver(fridgeContext);
		fridgeContext.initialize();
		freezerContext.initialize();
	}

	public abstract void displayTemperature(int tempVal, Type type);

	/**
	 * Indicate that the light is on
	 */
	public abstract void turnLightOn(Type type);

	/**
	 * Indicate that the light is off
	 */
	public abstract void turnLightOff(Type type);

	/**
	 * Indicate that the door is now closed
	 */
	public abstract void doorClosed(Type type);

	/**
	 * Indicate that the door is now open
	 */
	public abstract void doorOpened(Type type);

	/**
	 * indicate that compressor is running and cooling
	 */
	public abstract void compressorOn(Type type);

	/**
	 * indicate that cooking has ended
	 */
	public abstract void compressorOff(Type type);
	
	
}