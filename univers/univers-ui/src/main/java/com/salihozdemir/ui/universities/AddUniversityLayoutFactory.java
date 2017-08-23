package com.salihozdemir.ui.universities;


import org.springframework.beans.factory.annotation.Autowired;

import com.salihozdemir.model.entity.University;
import com.salihozdemir.service.adduniversity.AddUniversityService;
import com.salihozdemir.utils.NotificationMessages;
import com.salihozdemir.utils.StringUtils;
import com.salihozdemir.utils.UniversityStringUtils;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class AddUniversityLayoutFactory {

	@Autowired
	private AddUniversityService addUniversityService;
	
	private class AddUniversityLayout extends VerticalLayout implements Button.ClickListener {

		private TextField universityName;
		private TextField universityCountry;
		private TextField universityCity;
		private Button saveButton;
		private University university;
		private BeanFieldGroup<University> fieldGroup;
		private UniversitySavedListener universitySavedListener;
		
		public AddUniversityLayout(UniversitySavedListener universitySavedListener) {
			this.universitySavedListener = universitySavedListener;
		}

		public AddUniversityLayout init() {

			university = new University();
			
			universityName = new TextField(UniversityStringUtils.UNIVERSITY_NAME.getString());
			universityCountry = new TextField(UniversityStringUtils.UNIVERSITY_COUNTRY.getString());
			universityCity = new TextField(UniversityStringUtils.UNIVERSITY_CITY.getString());

			saveButton = new Button(StringUtils.SAVE.getString(), this);
			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

			universityName.setNullRepresentation("");
			universityCountry.setNullRepresentation("");
			universityCity.setNullRepresentation("");

			return this;
		}

		public AddUniversityLayout bind() {

			fieldGroup = new BeanFieldGroup<University>(University.class);
			fieldGroup.bindMemberFields(this);
			fieldGroup.setItemDataSource(university);
			
			return this;
		}
		
		public Component layout() {

			setWidth("100%");
			
			GridLayout grid = new GridLayout(1, 4);
			grid.setHeightUndefined();
			grid.setSpacing(true);
			
			grid.addComponent(universityName, 0, 0);
			grid.addComponent(universityCountry, 0, 1);
			grid.addComponent(universityCity, 0, 2);
			grid.addComponent(saveButton, 0, 3);
			
			return grid;
		}

		public void buttonClick(ClickEvent event) {

			try {
				fieldGroup.commit();
			} catch (CommitException e) {
				
				Notification.show(NotificationMessages.UNIVERSITY_SAVED_VALIDATION_ERROR_TITLE.getString(),
						NotificationMessages.UNIVERSITY_SAVED_VALIDATION_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
				return;
			}
			
			clearFields();
			addUniversityService.addUniversity(university);
			universitySavedListener.universitySaved();
			Notification.show(NotificationMessages.UNIVERSITY_SAVE_SUCCESS_TITLE.getString(),
					NotificationMessages.UNIVERSITY_SAVE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);
		}

		private void clearFields() {

			universityName.setValue(null);
			universityCountry.setValue(null);
			universityCity.setValue(null);
		}

	}

	public Component createComponent(UniversitySavedListener universitySavedListener) {

		return new AddUniversityLayout(universitySavedListener).init().bind().layout();
	}
}
