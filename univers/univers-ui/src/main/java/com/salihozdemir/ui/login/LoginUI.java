package com.salihozdemir.ui.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI(path=LoginUI.PATH)
@Theme("valo")
public class LoginUI extends UI{

	public static final String PATH = "/login";

	@Autowired
	private LoginFormFactory loginFormFactory;
	
	@Override
	protected void init(VaadinRequest request) {
		
		setContent(loginFormFactory.createComponent());
	}
}
