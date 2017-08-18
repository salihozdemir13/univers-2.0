package com.salihozdemir.utils;

public enum StringUtils {

	MAIN_MENU("MAIN MENU"), //
	SHOW_ALL_STUDENTS("SHOW ALL STUDENTS"), //
	FIRST_NAME("First name"), //
	LAST_NAME("Last name"),//
	AGE("Age"),//
	UNIVERSITY("University"),//
	GENDER("Gender"), //
	SAVE("Save"),//
	CANCEL("Cancel"),//
	REMOVE_STUDENT("Remove"),//
	
	MENU_STUDENTS("STUDENTS"),//
	MENU_UNIVERSITIES("UNIVERSITIES"),//
	MENU_LOGOUT("LOGOUT"),//
	MENU_ADD_STUDENT("Add student"),//
	MENU_REMOVE_STUDENT("Remove student"),//
	MENU_OPERATIONS_UNIVERSITY("Operations"),//
	MENU_LOGOUT_LABEL("Logout"), //
	
	MENU_ADD_UNIVERSITY("ADD UNIVERSITY"),//
	MENU_SHOW_UNIVERSITY("SHOW ALL UNIVERSITIES"),//
	MENU_CHART_UNIVERSITY("STATISTICS"),//
	
	;
	
	private final String string;
	
	private StringUtils(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}
}
