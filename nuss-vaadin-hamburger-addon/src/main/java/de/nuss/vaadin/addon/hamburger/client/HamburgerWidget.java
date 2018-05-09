package de.nuss.vaadin.addon.hamburger.client;

import com.google.gwt.user.client.ui.Label;

// Extend any GWT Widget
public class HamburgerWidget extends Label {

	private static final String PRIMARY_STYLE_NAME = "nuss-vaadin-hamburger";

	public HamburgerWidget() {
		setStyleName(PRIMARY_STYLE_NAME);
	}

}