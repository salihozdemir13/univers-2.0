package com.salihozdemir.service.universitystatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salihozdemir.repository.university.UniversityRepository;

@Service
@Transactional(readOnly=true)
public class UniversityStatisticsServiceImpl implements UniversityStatisticsService{

	@Autowired
	private UniversityRepository universityRepository;
	
	public Integer getNumOfStudentsForUniversity(Integer universityId) {

		return universityRepository.getNumOfStudentsForUniversity(universityId);
	}

}
