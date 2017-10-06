package com.salihozdemir.service.editstudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salihozdemir.model.entity.Student;
import com.salihozdemir.repository.student.StudentRepository;

@Service
public class EditStudentServiceImpl implements EditStudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	public void editStudent(Student student) {

//		studentRepository.edit(student);
	}

	
}
