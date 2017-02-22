
public class FreezerCompressorOnState extends FreezerState {
	private static FreezerCompressorOnState instance;
	static {
		instance = new FreezerCompressorOnState();
	}

	/**
	 * For singleton
	 * @return the object
	 */
	public static FreezerCompressorOnState instance() {
		return instance;
	}

	/**
	 * Handle the event
	 * @param event  the event to be processed
	 */
	@Override
	public void handle(Object event) {
		if (event.equals(FreezerContext.Events.FREEZR_COMPRESSOR_ON_EVENT)) {
			processCompressorOnRequest();
		} else if (event.equals(FreezerContext.Events.FREEZR_DOOR_OPENED_EVENT)) {
			processDoorOpen();
		} else if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)) {
			processClockTick();
		}
	}

	/**
	 * Process compressor request
	 */
	public void processCompressorOnRequest() {
		
		display.displayTemperature(context.getTemperature(),RefrigeratorDisplay.Type.FREEZR);
	}

	/**
	 * Process door open request
	 */
	public void processDoorOpen() {
		context.changeCurrentState(FreezerDoorOpenedState.instance());
	}

	/**
	 * Process clock ticks Generates the timer runs out event
	 */
	public void processClockTick() {
		context.setTemperature(context.getTemperature() - 1);
		display.displayTemperature(context.getTemperature(),RefrigeratorDisplay.Type.FREEZR);
		
		if (context.getTemperature() == 0) {
			context.changeCurrentState(FreezerDoorClosedState.instance());
		}
	}

	/**
	 * Initializes the state
	 */
	@Override
	public void run() {
		display.turnLightOn(RefrigeratorDisplay.Type.FREEZR);
		context.setTemperature(10);
		display.compressorOn(RefrigeratorDisplay.Type.FREEZR);
		display.displayTemperature(context.getTemperature(),RefrigeratorDisplay.Type.FREEZR);
	}
}