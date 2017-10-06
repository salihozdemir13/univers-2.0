package com.salihozdemir.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "STUDENT")
public class Student {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@NotNull(message = "You have to specify first name!")
	@Column(name = "first_name")
	private String firstName;

	@NotNull(message = "You have to specify last name!")
	@Column(name = "last_name")
	private String lastName;

	@NotNull(message = "You have to specify age!")
	@Column(name = "age")
	private Integer age;

	@NotNull(message = "Gender must be set!")
	@Column(name = "gender")
	private String gender;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "university_id")
	@NotNull(message = "University must not be null!")
	private University university;

	public Student() {

	}

//	public Student(String firstName, String lastName) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return String.format("Student[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}
}
