package de.nuss.vaadin.addon.hamburger.demo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.nuss.vaadin.addon.hamburger.Hamburger;

@Theme("demo")
@Title("Hamburger Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		setContent(layout);

		Label lblHeading = new Label("Hamburger Vaadin Add-on Demo");
		lblHeading.addStyleName(ValoTheme.LABEL_H1);
		layout.addComponent(lblHeading);

		// HorizontalLayout hLayout = new HorizontalLayout();
		// layout.addComponent(hLayout);
		//
		// hLayout.addComponent(new Hamburger());
		// hLayout.addComponent(new Hamburger(TYPE.SQUEEZE));

		CssLayout hamburgerLayout = new CssLayout();
		layout.addComponent(hamburgerLayout);
		layout.setExpandRatio(hamburgerLayout, 1f);

		int bgIndex = 0;
		for (Hamburger.TYPE type : Hamburger.TYPE.values()) {
			VerticalLayout hamburgerContainer = createHamburgerContainer(type, false);
			hamburgerContainer.addStyleName("bg-" + (bgIndex % 4));
			hamburgerLayout.addComponent(hamburgerContainer);
			bgIndex++;

			if (Hamburger.supportsRevers(type)) {
				VerticalLayout hamburgerContainerReverse = createHamburgerContainer(type, true);
				hamburgerContainerReverse.addStyleName("bg-" + (bgIndex % 4));
				hamburgerLayout.addComponent(hamburgerContainerReverse);
				bgIndex++;
			}
		}

		Hamburger whiteHamburger = new Hamburger();
		whiteHamburger.addStyleName("hamburger-white");
		whiteHamburger.addStyleName("bg-0");
		layout.addComponent(whiteHamburger);
	}

	private VerticalLayout createHamburgerContainer(Hamburger.TYPE type, boolean reverse) {
		VerticalLayout container = new VerticalLayout();
		container.setWidth("150px");
		container.setHeight("150px");
		Hamburger hamburger = new Hamburger(type);
		container.addComponent(hamburger);
		container.setComponentAlignment(hamburger, Alignment.MIDDLE_CENTER);
		// container.setExpandRatio(hamburger, 1f);

		String typeString = type.toString().toLowerCase();
		if (reverse) {
			typeString += "-r";
		}
		Label lblType = new Label(typeString);
		container.addComponent(lblType);
		container.setComponentAlignment(lblType, Alignment.BOTTOM_CENTER);

		return container;
	}
}
