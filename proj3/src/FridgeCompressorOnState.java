
public class FridgeCompressorOnState extends FridgeState {
	private static FridgeCompressorOnState instance;
	static {
		instance = new FridgeCompressorOnState();
	}

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static FridgeCompressorOnState instance() {
		return instance;
	}

	/**
	 * Handle the event
	 * @param event  the event to be processed
	 * 
	 */
	@Override
	public void handle(Object event) {
		//TODO
	}

	/**
	 * Process Cook request
	 */
	public void processCookRequest() {
		//TODO
	}

	/**
	 * Process door open request
	 */
	public void processDoorOpen() {
		context.changeCurrentState(FridgeDoorOpenedState.instance());
	}

	/**
	 * Process clock ticks Generates the timer runs out event
	 */
	public void processClockTick() {
		//TODO
	}

	/**
	 * Initializes the state
	 */
	@Override
	public void run() {
		display.turnLightOn(RefrigeratorDisplay.Type.FRIDGE);
		context.setTemperature(10);
		display.compressorOn(RefrigeratorDisplay.Type.FRIDGE);
		display.displayTemperature(context.getTemperature(),RefrigeratorDisplay.Type.FREEZR);
	}
}