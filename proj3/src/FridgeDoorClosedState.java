
public class FridgeDoorClosedState extends FridgeState {
	private static FridgeDoorClosedState instance;
	static {
		instance = new FridgeDoorClosedState();
	}

	/**
	 * returns the instance
	 * @return this object
	 */
	public static FridgeDoorClosedState instance() {
		return instance;
	}

	/**
	 * Handle cook request and door open events
	 */
	@Override
	public void handle(Object event) {
		if (event.equals(FridgeContext.Events.FRIDGE_DOOR_OPENED_EVENT)) {
			processDoorOpen();
		}
	}

	/**
	 * handle door open event
	 * 
	 */
	public void processDoorOpen() {
		context.changeCurrentState(FridgeDoorOpenedState.instance());
	}

	/**
	 * handle cook request
	 * 
	 */
	public void processCompressorOnRequest() {
		context.changeCurrentState(FridgeCompressorOnState.instance());
	}

	/**
	 * initialize the state
	 * 
	 */
	@Override
	public void run() {
		display.doorClosed(RefrigeratorDisplay.Type.FRIDGE);
		display.turnLightOff(RefrigeratorDisplay.Type.FRIDGE);
		display.compressorOff(RefrigeratorDisplay.Type.FRIDGE);
		display.displayTemperature(50,RefrigeratorDisplay.Type.FRIDGE);
		context.setTemperature(0);
	}
}