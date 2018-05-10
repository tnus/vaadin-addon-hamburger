package de.nuss.vaadin.addon.hamburger.client;

import com.google.gwt.user.client.ui.Button;

// Extend any GWT Widget
public class HamburgerWidget extends Button {

	// private static final String PRIMARY_STYLE_NAME = "nuss-vaadin-hamburger";
	private static final String PRIMARY_STYLE_NAME = "hamburger";

	public HamburgerWidget() {
		setStyleName(PRIMARY_STYLE_NAME);

		// addStyleName("hamburger--3dx");
		setHTML("<span class=\"hamburger-box\"><span class=\"hamburger-inner\"></span></span>");
	}

}