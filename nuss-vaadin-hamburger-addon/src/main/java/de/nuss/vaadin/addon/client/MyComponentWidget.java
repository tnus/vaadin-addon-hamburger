package de.nuss.vaadin.addon.client;

import com.google.gwt.user.client.ui.Label;

// Extend any GWT Widget
public class MyComponentWidget extends Label {

    public MyComponentWidget() {

        // CSS class-name should not be v- prefixed
        setStyleName("nuss-vaadin-hamburger");

        // State is set to widget in MyComponentConnector
    }

}