package com.salihozdemir.ui.universities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.salihozdemir.model.entity.University;
import com.salihozdemir.service.showalluniversities.ShowAllUniversitiesService;
import com.salihozdemir.ui.commons.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class ShowUniversitiesLayoutFactory implements UIComponentBuilder {

	private List<University> universities;
	private BeanItemContainer<University> container;

	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;

	private class ShowUniversitiesLayout extends VerticalLayout {

		private Grid universityTable;

		public ShowUniversitiesLayout init() {

			setMargin(true);
			container = new BeanItemContainer<University>(University.class, universities);

			universityTable = new Grid(container);
			universityTable.setColumnOrder("universityName", "universityCountry", "universityCity");
			universityTable.removeColumn("id");
			universityTable.setImmediate(true);

			return this;
		}

		public ShowUniversitiesLayout load() {

			universities = showAllUniversitiesService.getAllUniversities();
			return this;
		}

		public ShowUniversitiesLayout layout() {

			addComponent(universityTable);
			return this;
		}
	}

	public void refreshTable() {

		universities = showAllUniversitiesService.getAllUniversities();
		container.removeAllItems();
		container.addAll(universities);
	}

	public Component createComponent() {

		return new ShowUniversitiesLayout().load().init().layout();
	}

}
