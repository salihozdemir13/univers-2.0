package com.salihozdemir.utils;

public enum NotificationMessages {

	STUDENT_SAVE_ERROR_TITLE("ERROR"), //
	STUDENT_SAVE_ERROR_DESCRIPTION("Must have at least one university!"), //
	
	STUDENT_SAVE_SUCCESS_TITLE("SAVE"), //
	STUDENT_SAVE_SUCCESS_DESCRIPTION("Student successfully saved!"), //
	
	STUDENT_SAVE_VALIDATION_ERROR_TITLE("ERROR"), //
	STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION("Fields must be filled!"), //
	
	STUDENT_REMOVE_SUCCESS_TITLE("REMOVE"), //
	STUDENT_REMOVE_SUCCESS_DESCRIPTION("Student(s) successfully removed!");
	
	private final String string;
	
	private NotificationMessages(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}
}
