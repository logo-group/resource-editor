package com.logo.ui.window;

import com.logo.data.entity.ReUser;
import com.logo.ui.form.UserForm;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Window;

public class UserWindow extends Window {

	private static final long serialVersionUID = 1L;

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
