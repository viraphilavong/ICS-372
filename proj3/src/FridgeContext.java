import java.util.Observable;
import java.util.Observer;

/**
 * The context is an observer for the clock and stores the context info for
 * states
 */
public class FridgeContext implements Observer {
	
	public static enum Events {
		FRIDGE_DOOR_CLOSED_EVENT,
		FRIDGE_DOOR_OPENED_EVENT, 
		FRIDGE_COMPRESSOR_ON_EVENT,
		FRIDGE_COMPRESSOR_OFF_EVENT,
	};
    
	private static RefrigeratorDisplay fridgeDisplay;
	
	private int temperature;
	private FridgeState currentState;
	
	private static FridgeContext instance;
	static {
		instance = new FridgeContext();
		fridgeDisplay = RefrigeratorDisplay.instance();
	}

	/**
	 * Make it a singleton
	 */
	private FridgeContext() {
	}

	/**
	 * Return the instance
	 * @return the object
	 */
	public static FridgeContext instance() {
		if (instance == null) {
			instance = new FridgeContext();
		}
		return instance;
	}

	/**
	 * lets door closed state be the starting state adds the object as an
	 * observable for clock
	 */
	public void initialize() {
		instance.changeCurrentState(FridgeDoorClosedState.instance());
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
	public void changeCurrentState(FridgeState nextState) {
		currentState = nextState;
		nextState.run();
	}

	/**
	 * Gets the display
	 * @return the display
	 */
	public RefrigeratorDisplay getDisplay() {
		return fridgeDisplay;
	}

	/**
	 * get the cooking time remaining
	 * @return remaining cook time
	 */
	public int getTemperature() {
		return temperature;
	}

	/**
	 * Sets the cooking time
	 * @param timeRemaining time needed to cook
	 */
	public void setTemperature(int temp) {
		this.temperature = temp;
	}

}