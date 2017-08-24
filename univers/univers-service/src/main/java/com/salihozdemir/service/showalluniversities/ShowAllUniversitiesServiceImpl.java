package com.salihozdemir.service.showalluniversities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salihozdemir.model.entity.University;
import com.salihozdemir.repository.university.UniversityRepository;

@Service
@Transactional(readOnly = true)
public class ShowAllUniversitiesServiceImpl implements ShowAllUniversitiesService {

	@Autowired
	private UniversityRepository universityRepository;

	public List<University> getAllUniversities() {

		return universityRepository.getAllUniversities();

	}
}
