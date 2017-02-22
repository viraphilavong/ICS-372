
import java.util.Observable;

/**
 * Specifies what the display system should do. Note that the implementation has
 * a lot of freedom to choose its display.
 */
public abstract class FridgeDisplay extends RefrigeratorDisplay {
	protected static FridgeContext fridgeContext;
	protected static FridgeDisplay instance;

	/**
	 * Initializes the context and instance
	 */
	protected FridgeDisplay() {
		instance = this;
		fridgeContext = FridgeContext.instance();
	}

	/**
	 * For singleton
	 * @return the object
	 */
	public static FridgeDisplay instance() {
		return instance;
	}

	/**
	 * Do the initializations to make the context an observer
	 */
	public void initialize() {
		instance().addObserver(fridgeContext);
		fridgeContext.initialize();
	}
	
}