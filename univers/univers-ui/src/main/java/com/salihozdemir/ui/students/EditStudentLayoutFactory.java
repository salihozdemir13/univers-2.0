package com.salihozdemir.ui.students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.salihozdemir.model.entity.Student;
import com.salihozdemir.model.entity.University;
import com.salihozdemir.repository.student.StudentRepository;
import com.salihozdemir.service.showallstudents.ShowAllStudentsService;
import com.salihozdemir.service.showalluniversities.ShowAllUniversitiesService;
import com.salihozdemir.ui.commons.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class EditStudentLayoutFactory implements UIComponentBuilder {

	private BeanItemContainer<Student> container;

	@Autowired
	EditStudentEditorLayout editor;

	@Autowired
	private StudentRepository repository;

	private List<Student> students;

	private TextField filter;

	private Grid studentGrid;

	private class EditStudentLayout extends VerticalLayout {

		private static final long serialVersionUID = 1L;

		public EditStudentLayout init() {

			container = new BeanItemContainer<Student>(Student.class, students);
			studentGrid = new Grid(container);
			filter = new TextField();

			studentGrid.setHeight(300, Unit.PIXELS);
			studentGrid.setColumnOrder("firstName", "lastName", "gender", "age", "university");
			studentGrid.removeColumn("id");
			studentGrid.setImmediate(true);

			filter.setInputPrompt("Filter by Last Name");

			// Replace listing with filtered content when user changes filter
			filter.addTextChangeListener(e -> listStudents(e.getText()));

			// Connect selected Customer to editor or hide if none is selected
			studentGrid.addSelectionListener(e -> {

				if (e.getSelected().isEmpty()) {
					editor.setVisible(false);
				} else {
					editor.editStudent((Student) e.getSelected().iterator().next());
				}
			});
			// End Filter Line //

			editor.setChangeHandler(() -> {
				editor.setVisible(false);
				listStudents(filter.getValue());
			});

			return this;
		}

		// tag::listStudents[]
		private void listStudents(String filterText) {
			if (org.springframework.util.StringUtils.isEmpty(filterText)) {
				studentGrid.setContainerDataSource(new BeanItemContainer<Student>(Student.class, students));
			} else {
				studentGrid.setContainerDataSource(new BeanItemContainer<Student>(Student.class,
						repository.findByLastNameStartsWithIgnoreCase(filterText)));
			}
		}
		// end::listStudents[]

		public EditStudentLayout load() {

			students = allStudentsService.getAllStudents();

			List<University> universities = showAllUniversitiesService.getAllUniversities();
			editor.university.addItems(universities);

			return this;
		}

		public EditStudentLayout layout() {

			HorizontalLayout actions = new HorizontalLayout(filter);
			VerticalLayout mainLayout = new VerticalLayout(actions, studentGrid, editor);

			actions.setSpacing(true);
			mainLayout.setSpacing(true);
			mainLayout.setMargin(true);

			addComponent(mainLayout);

			return this;

		}
	}

	public void refreshTable() { //observer pattern

		students = allStudentsService.getAllStudents();
		container.removeAllItems();
		container.addAll(students);
	}

	@Autowired
	private ShowAllStudentsService allStudentsService;

	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;

	public Component createComponent() {

		return new EditStudentLayout().load().init().layout();
	}

}
