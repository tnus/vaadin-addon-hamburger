package de.nuss.vaadin.addon.hamburger;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Component;
import com.vaadin.util.ReflectTools;

import de.nuss.vaadin.addon.hamburger.client.HamburgerServerRpc;
import de.nuss.vaadin.addon.hamburger.client.HamburgerState;

public class Hamburger extends com.vaadin.ui.AbstractComponent {

	public enum TYPE {
		_3DX, _3DY, _3DXY, ARROW, ARROWALT, ARROWTURN, BORING, COLLAPSE, ELASTIC, EMPHATIC, MINUS, SLIDER, SPRING, STAND, SQUEEZE, VORTEX
	}

	private TYPE type;

	private boolean reverse;

	public Hamburger() {
		this(TYPE._3DX);
	}

	public Hamburger(TYPE type) {
		this(type, false);
	}

	public Hamburger(TYPE type, boolean reverse) {
		// check if type can be reversed
		if (reverse && !supportsRevers(type)) {
			throw new IllegalArgumentException("hamburger type " + type + " cannot be reversed!");
		}

		this.type = type;
		this.reverse = reverse;

		// To receive events from the client, we register ServerRpc
		HamburgerServerRpc rpc = this::handleClick;
		registerRpc(rpc);

		String typeStyleClass = type.toString().toLowerCase();
		if (reverse) {
			typeStyleClass += "-r";
		}

		typeStyleClass = typeStyleClass.replaceFirst("_", "");
		getState().type = typeStyleClass;

		setWidth("70px");
		setHeight("61px");
	}

	/**
	 * This method checks if the hamburger type can be reversed
	 * 
	 * @param type
	 *            Hamburger type
	 * @return true if the type can be reversed.
	 */
	public static boolean supportsRevers(TYPE type) {
		return type != TYPE.BORING && type != TYPE.MINUS && type != TYPE.SQUEEZE;
	}

	@Override
	protected HamburgerState getState() {
		return (HamburgerState) super.getState();
	}

	private void handleClick(MouseEventDetails mouseDetails) {
		getState().active = !getState().active;
		fireToggle();
	}

	/**
	 * Adds the toggle listener.
	 *
	 * @see Registration
	 *
	 * @param listener
	 *            the Listener to be added.
	 * @return a registration object for removing the listener
	 * @since 8.0
	 */
	public Registration addToggleListener(ToggleListener listener) {
		return addListener(ToggleEvent.class, listener, ToggleListener.TOGGLE_METHOD);
	}

	/**
	 * Checks if the menu is active or not.
	 * 
	 * @return true when active, otherwise false
	 */
	public boolean isActive() {
		return getState().active;
	}

	/**
	 * This method toggles the hamburger menu
	 */
	public void toggle() {
		getState().active = !getState().active;
		fireToggle();
	}

	/**
	 * Fires a toggle event to all listeners without any event details.
	 * <p>
	 */
	protected void fireToggle() {
		fireEvent(new Hamburger.ToggleEvent(this));
	}

	@FunctionalInterface
	public interface ToggleListener extends Serializable {

		public static final Method TOGGLE_METHOD = ReflectTools.findMethod(ToggleListener.class, "hamburgerToggle",
				ToggleEvent.class);

		/**
		 * Called when a {@link Hamburger} menu has been toggled. A reference to the
		 * hamburger is given by {@link ToggleEvent#getHamburger()}.
		 *
		 * @param event
		 *            An event containing information about the toggle.
		 */
		public void hamburgerToggle(ToggleEvent event);
	}

	public static class ToggleEvent extends Component.Event {

		public ToggleEvent(Component source) {
			super(source);
		}

		/**
		 * Gets the Hamburger where the event occurred.
		 *
		 * @return the Source of the event.
		 */
		public Hamburger getHamburger() {
			return (Hamburger) getSource();
		}
	}
}
