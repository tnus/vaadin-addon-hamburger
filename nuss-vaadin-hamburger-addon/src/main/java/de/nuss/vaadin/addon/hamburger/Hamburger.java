package de.nuss.vaadin.addon.hamburger;

import com.vaadin.shared.MouseEventDetails;

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
		System.out.println("Hamburger clicked");

		// Send nag message every 5:th click with ClientRpc
		// if (++clickCount % 5 == 0) {
		// getRpcProxy(MyComponentClientRpc.class).alert("Ok, that's enough!");
		// }

		getState().active = !getState().active;

		// Update shared state. This state update is automatically
		// sent to the client.
		// getState().text = "You have clicked " + clickCount + " times";
	}

	/**
	 * Checks if the menu is active or not.
	 * 
	 * @return true when active, otherwise false
	 */
	public boolean isActive() {
		return getState().active;
	}
}
