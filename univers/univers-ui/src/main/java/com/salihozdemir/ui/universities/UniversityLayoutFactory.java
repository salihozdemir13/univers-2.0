package com.salihozdemir.ui.universities;

import com.salihozdemir.ui.commons.UniversMainUI;
import com.salihozdemir.utils.StringUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SpringView(name=UniversityLayoutFactory.NAME, ui=UniversMainUI.class)
public class UniversityLayoutFactory extends VerticalLayout implements View {

	public static final String NAME = "operations";
	private TabSheet tabSheet;

	private void addLayout() {

		setMargin(true);

		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		addComponent(tabSheet);
	}

	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
