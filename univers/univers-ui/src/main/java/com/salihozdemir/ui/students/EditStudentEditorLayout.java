package com.salihozdemir.ui.students;

import org.springframework.beans.factory.annotation.Autowired;

import com.salihozdemir.model.entity.Student;
import com.salihozdemir.repository.student.StudentRepository;
import com.salihozdemir.utils.Gender;
import com.salihozdemir.utils.StringUtils;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
public class EditStudentEditorLayout extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	@Autowired
	private StudentRepository repository;

	@Autowired
	EditStudentLayoutFactory layoutFactory;

	private Student student;

	TextField firstName;
	TextField lastName;
	TextField age;
	ComboBox gender;
	ComboBox university;

	/* Action buttons */
	Button save = new Button("Save", FontAwesome.SAVE);
	Button cancel = new Button("Cancel");
	CssLayout actions = new CssLayout(save, cancel);

	public EditStudentEditorLayout() {

		firstName = new TextField(StringUtils.FIRST_NAME.getString());
		lastName = new TextField(StringUtils.LAST_NAME.getString());
		age = new TextField(StringUtils.AGE.getString());
		gender = new ComboBox(StringUtils.GENDER.getString());
		university = new ComboBox(StringUtils.UNIVERSITY.getString());

		GridLayout layout = new GridLayout(2, 4);
		layout.setSizeUndefined();
		layout.setSpacing(true);

		layout.addComponent(firstName, 0, 0);
		layout.addComponent(lastName, 1, 0);

		layout.addComponent(age, 0, 1);
		layout.addComponent(gender, 1, 1);

		layout.addComponent(university, 0, 2, 1, 2);

		gender.addItem(Gender.MALE.getString());
		gender.addItem(Gender.FEMALE.getString());

		university.setWidth("100%");

		firstName.setNullRepresentation("");
		lastName.setNullRepresentation("");
		age.setNullRepresentation("");

		addComponents(layout, actions);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete reset
		save.addClickListener(e -> saveStudent(student));
		cancel.addClickListener(e -> cancelEdit());
		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editStudent(Student c) {

		final boolean persisted = c.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			student = repository.findOne(c.getId());
		} else {
			student = c;
		}
		cancel.setVisible(persisted);

		// Bind customer properties to similarly named fields
		BeanFieldGroup.bindFieldsUnbuffered(student, this);

		setVisible(true);

		save.focus();

		// Select all text in firstName field automatically
		firstName.selectAll();
	}

	public final void cancelEdit() {

		setVisible(false);
	}

	public void saveStudent(Student student) {

		repository.save(student);
		layoutFactory.refreshTable();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
	}
}
