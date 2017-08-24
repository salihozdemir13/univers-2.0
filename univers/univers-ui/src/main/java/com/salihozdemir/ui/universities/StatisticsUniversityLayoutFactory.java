package com.salihozdemir.ui.universities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.salihozdemir.model.entity.University;
import com.salihozdemir.service.showalluniversities.ShowAllUniversitiesService;
import com.salihozdemir.service.universitystatistics.UniversityStatisticsService;
import com.salihozdemir.ui.commons.UIComponentBuilder;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class StatisticsUniversityLayoutFactory implements UIComponentBuilder {

	private List<University> universities;
	private StatisticsUniversityLayout statisticsLayout;

	@Autowired
	private UniversityStatisticsService universityStatisticsService;

	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;

	private class StatisticsUniversityLayout extends VerticalLayout {

		public StatisticsUniversityLayout load() {

			universities = showAllUniversitiesService.getAllUniversities();
			return this;
		}

		public StatisticsUniversityLayout layout() {

			setMargin(true);

			for (University university : universities) {

				int numOfStudents = universityStatisticsService.getNumOfStudentsForUniversity(university.getId());
				Label label = new Label("<p><b>" + university.getUniversityName() + "</b>" + " - " + numOfStudents
						+ " student(s)" + "</p>", ContentMode.HTML);
				addComponent(label);
			}

			return this;
		}
	}

	public void refresh() {

		if( statisticsLayout == null) return;
		statisticsLayout.removeAllComponents();
		statisticsLayout.load();
		statisticsLayout.layout();
	}

	public Component createComponent() {
		
		statisticsLayout = new StatisticsUniversityLayout();
		return statisticsLayout.load().layout();
	}

}
