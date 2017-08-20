package com.salihozdemir.service.removestudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salihozdemir.model.entity.Student;
import com.salihozdemir.repository.student.StudentRepository;

@Service
public class RemoveStudentServiceImpl implements RemoveStudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	public void removeStudent(Student student) {
		
		studentRepository.delete(student);
	}	
}
