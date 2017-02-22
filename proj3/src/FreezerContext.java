import java.util.Observable;
import java.util.Observer;

/**
 * The context is an observer for the clock and stores the context info for
 * states
 */
public class FreezerContext implements Observer 
{
	public static enum Events {
		FREEZR_DOOR_CLOSED_EVENT,
		FREEZR_DOOR_OPENED_EVENT, 
		FREEZR_COMPRESSOR_ON_EVENT,
		FREEZR_COMPRESSOR_OFF_EVENT,
	};
	
 
	private static RefrigeratorDisplay freezerDisplay;
	
	private int temperature;
	private FreezerState currentState;
	
	private static FreezerContext instance;
	static {
		instance = new FreezerContext();
		freezerDisplay = RefrigeratorDisplay.instance();
	}
	


	/**
	 * Make it a singleton
	 */
	private FreezerContext() {
	}

	/**
	 * Return the instance
	 * @return the object
	 */
	public static FreezerContext instance() {
		if (instance == null) {
			instance = new FreezerContext();
		}
		return instance;
	}

	/**
	 * lets door closed state be the starting state adds the object as an
	 * observable for clock
	 */
	public void initialize() {
		instance.changeCurrentState(FreezerDoorClosedState.instance());
		Clock.instance().addObserver(instance);
	}

	/**
	 * For observer
	 * @param observable will be the clock
	 * @param arg the event that clock has ticked
	 */
	@Override
	public void update(Observable observable, Object arg) {
		currentState.handle(arg);
	}

	/**
	 * handle one of the several other events such as door close
	 * @param arg the event from the GUI
	 */
	public void processEvent(Object arg) {
		currentState.handle(arg);
	}

	/**
	 * Called from the states to change the current state
	 * @param nextState  the next state
	 */
	public void changeCurrentState(FreezerState nextState) {
		currentState = nextState;
		nextState.run();
	}

	/**
	 * Gets the display
	 * @return the display
	 */
	public RefrigeratorDisplay getDisplay() {
		return freezerDisplay;
	}

	/**
	 * get the temperature
	 * @return current freezer temperature
	 */
	public int getTemperature() {
		return temperature;
	}

	/**
	 * Sets the temperature
	 * @param temp internal temperature
	 */
	public void setTemperature(int temp) {
		this.temperature = temp;
	}
}