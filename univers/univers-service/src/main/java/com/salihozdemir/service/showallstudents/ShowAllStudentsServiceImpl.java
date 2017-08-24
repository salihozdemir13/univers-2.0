package com.salihozdemir.service.showallstudents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salihozdemir.model.entity.Student;
import com.salihozdemir.repository.student.StudentRepository;

@Service
@Transactional(readOnly = true)
public class ShowAllStudentsServiceImpl implements ShowAllStudentsService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> getAllStudents() {

		return studentRepository.getAllStudents();
	}

}
