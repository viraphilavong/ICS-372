/**
 * Represents the state of the fridge when the door is closed. When the
 * Fridge has its door closed, the run method of this class is called. After
 * that, when an event occurs, the handle method is invoked.
 *
 */
public class FreezerDoorClosedState extends FreezerState {
	private static FreezerDoorClosedState instance;
	static {
		instance = new FreezerDoorClosedState();
	}

	/**
	 * returns the instance
	 * @return this object
	 */
	public static FreezerDoorClosedState instance() {
		return instance;
	}

	/**
	 * Handle door open events
	 */
	@Override
	public void handle(Object event) {
		if (event.equals(FreezerContext.Events.FREEZR_DOOR_OPENED_EVENT)) {
			processDoorOpen();
		}
	}

	/**
	 * handle door open event
	 * 
	 */
	public void processDoorOpen() {
		context.changeCurrentState(FreezerDoorOpenedState.instance());
	}

	/**
	 * handle compressor request
	 */
	public void processCompressorOnRequest() {
		//todo
	}

	/**
	 * initialize the state
	 */
	@Override
	public void run() {
		display.doorClosed(RefrigeratorDisplay.Type.FREEZR);
		display.turnLightOff(RefrigeratorDisplay.Type.FREEZR);
		display.compressorOff(RefrigeratorDisplay.Type.FREEZR);
		display.displayTemperature(50,RefrigeratorDisplay.Type.FREEZR);
		context.setTemperature(50);
	}
}