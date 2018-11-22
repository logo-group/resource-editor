package com.logo.form;

import java.util.logging.Logger;

import com.logo.data.ReUser;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Window;

public class UserWindow extends Window {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(NewResourceForm.class.getName());
	private final transient ReUser reUser;
	private final transient UserForm userForm;

	public UserWindow() {
		this.reUser = (ReUser) VaadinSession.getCurrent().getAttribute("user");
		this.userForm = new UserForm();
		userForm.setUser(reUser);
		initialize();
	}

	public void initialize() {
		center();
		setClosable(false);
		setModal(true);
		setWidth(50.0f, Unit.PERCENTAGE);
		setHeight(60.0f, Unit.PERCENTAGE);
		setResizable(false);
		setResponsive(true);
		setContent(userForm);
		userForm.getSaveButton().addClickListener(e -> close());
		userForm.getCloseButton().addClickListener(e -> close());
	}


}
