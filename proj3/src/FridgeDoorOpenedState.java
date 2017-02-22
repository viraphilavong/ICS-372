
public class FridgeDoorOpenedState extends FridgeState {
	private static FridgeDoorOpenedState instance;
	static {
		instance = new FridgeDoorOpenedState();
	}

	/**
	 * For the singleton pattern
	 * @return the object
	 */
	public static FridgeDoorOpenedState instance() {
		return instance;
	}

	/**
	 * Handle door closed event
	 */
	@Override
	public void handle(Object event) {
		if (event.equals(FridgeContext.Events.FRIDGE_DOOR_CLOSED_EVENT)) {
			processDoorClose();
		}
	}

	/**
	 * Process door closed event
	 */
	public void processDoorClose() {
		context.changeCurrentState(FridgeDoorClosedState.instance());
	}

	/**
	 * Initialize the state
	 */
	@Override
	public void run() {
		display.turnLightOn(RefrigeratorDisplay.Type.FRIDGE);
		display.compressorOff(RefrigeratorDisplay.Type.FRIDGE);
		display.doorOpened(RefrigeratorDisplay.Type.FRIDGE);
		display.displayTemperature(50,RefrigeratorDisplay.Type.FRIDGE);
	}
}