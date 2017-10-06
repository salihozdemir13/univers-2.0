package com.salihozdemir.ui.commons;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class UniversLogoLayoutFactory implements UIComponentBuilder{

	private class LogoLayout extends VerticalLayout {
		
		private Embedded logo;
		
		public LogoLayout init() {
			
			logo = new Embedded();
			logo.setSource(new ThemeResource("../../images/universe_2.png"));
			logo.setWidth("398px");
			logo.setHeight("180px");
			
			return this;
		}
		
		public LogoLayout layout() {
			
			addComponent(logo);
			setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
			
			return this;
		}
	}
	
	public Component createComponent() {

		return new LogoLayout().init().layout();
	}

	
}
