package com.salihozdemir.ui.students;

import org.springframework.beans.factory.annotation.Autowired;

import com.salihozdemir.ui.commons.UniversMainUI;
import com.salihozdemir.utils.StringUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SpringView(name=StudentLayoutFactory.NAME, ui=UniversMainUI.class)
public class StudentLayoutFactory extends VerticalLayout implements View, StudentSavedListener{

	public static final String NAME = "addstudent";
	
	@Autowired
	private AddStudentMainLayoutFactory mainLayoutFactory;
	
	@Autowired
	private ShowAllStudentsLayoutFactory showStudentsLayoutFactory;
	
	private TabSheet tabSheet;

	private void addLayout() {

		setMargin(true);

		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component addStudentMainTab = mainLayoutFactory.createComponent(this);
		Component showStudentsTab = showStudentsLayoutFactory.createComponent();
		
		tabSheet.addTab(addStudentMainTab, StringUtils.MAIN_MENU.getString());
		tabSheet.addTab(showStudentsTab, StringUtils.SHOW_ALL_STUDENTS.getString());
		
		addComponent(tabSheet);
	}

	public void studentSaved() {

		showStudentsLayoutFactory.refreshTable();
	}
	
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
	}

}
