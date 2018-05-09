package de.nuss.vaadin.addon.hamburger;

import com.vaadin.shared.MouseEventDetails;

import de.nuss.vaadin.addon.hamburger.client.HamburgerServerRpc;
import de.nuss.vaadin.addon.hamburger.client.HamburgerState;

public class Hamburger extends com.vaadin.ui.AbstractComponent {

	public Hamburger() {

		// To receive events from the client, we register ServerRpc
		HamburgerServerRpc rpc = this::handleClick;
		registerRpc(rpc);
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

		// Update shared state. This state update is automatically
		// sent to the client.
		// getState().text = "You have clicked " + clickCount + " times";
	}
}
