/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
/**
 * Represents the state of the Refrigerator when the door is open. When the
 * Refrigerator has its door opened, the run method of this class is called. After
 * that, when an event occurs, the handle method is invoked.
 *
 */
public class FreezerDoorOpenedState extends FreezerState {
	private static FreezerDoorOpenedState instance;
	static {
		instance = new FreezerDoorOpenedState();
	}

	/**
	 * For the singleton pattern
	 * @return the object
	 */
	public static FreezerDoorOpenedState instance() {
		return instance;
	}

	/**
	 * Handle door closed event
	 */
	@Override
	public void handle(Object event) {
		if (event.equals(FreezerContext.Events.FREEZR_DOOR_CLOSED_EVENT)) {
			processDoorClose();
		}
	}

	/**
	 * Process door closed event
	 */
	public void processDoorClose() {
		context.changeCurrentState(FreezerDoorClosedState.instance());
	}

	/**
	 * Initialize the state
	 */
	@Override
	public void run() {
		display.turnLightOn(RefrigeratorDisplay.Type.FREEZR);
		display.compressorOff(RefrigeratorDisplay.Type.FREEZR);
		display.doorOpened(RefrigeratorDisplay.Type.FREEZR);
		display.displayTemperature(50,RefrigeratorDisplay.Type.FREEZR);
	}
}