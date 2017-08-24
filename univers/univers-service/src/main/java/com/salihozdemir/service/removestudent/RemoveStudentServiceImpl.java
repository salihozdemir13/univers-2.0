package com.salihozdemir.service.removestudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salihozdemir.model.entity.Student;
import com.salihozdemir.repository.student.StudentRepository;

@Service
@Transactional(readOnly = true)
public class RemoveStudentServiceImpl implements RemoveStudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Transactional
	public void removeStudent(Student student) {

		studentRepository.delete(student);
	}
}
