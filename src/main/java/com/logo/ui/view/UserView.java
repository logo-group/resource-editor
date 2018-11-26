package com.logo.ui.view;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.appreciated.material.MaterialTheme;
import com.logo.data.entity.ReUser;
import com.logo.data.repository.ReUserRep;
import com.logo.ui.components.TextFieldWithButton;
import com.logo.ui.form.UserForm;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;

@UIScope
@SpringView(name = UserView.VIEW_NAME)
public class UserView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "UserView";

	@Autowired(required = true)
	public transient ReUserRep userRepo;

	private Grid<ReUser> userGrid = new Grid<>(ReUser.class);

	private UserForm userForm = new UserForm(this);

	public UserView() {
		removeAllComponents();
		init();
	}

	@PostConstruct
	void init() {
		this.userRepo = (ReUserRep) VaadinSession.getCurrent().getAttribute("userrepo");
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		setHeight("100%");
		addStyleName(MaterialTheme.LAYOUT_CARD);
		
		this.setDescription(LangHelper.getLocalizableMessage(LogoResConstants.CLICKTOEDITSTR));
		userForm.setVisible(false);
		
		userGrid.setColumns("id", "username", "email", "department");
		
		HeaderRow row = userGrid.appendHeaderRow();
		row.join("username").setHtml("<b>Full name</b>");
		
		HorizontalLayout header = new HorizontalLayout();
		
		HorizontalLayout grid = new HorizontalLayout();
		grid.addStyleName(MaterialTheme.CARD_HOVERABLE);

		userGrid.setWidth("50%");
		userGrid.setHeight("100%");
		grid.setWidth("100%");
		grid.setHeight("100%");
		

		Button addNewUser = new Button(LangHelper.getLocalizableMessage(LogoResConstants.ADDNEWUSERSTR));
		addNewUser.setIcon(VaadinIcons.PLUS);
		addNewUser.addStyleName(MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);
		
		header.addComponent(addNewUser);
		
		addNewUser.addClickListener(e -> {
			userForm.setVisible(true);
			userForm.setUser(new ReUser());
		});
		
		addComponent(header);
		grid.addComponentsAndExpand(userGrid);
		grid.addComponentsAndExpand(userForm);
		addComponentsAndExpand(grid);
		addGridFilters();
		updateUserList("");
		
		userGrid.setStyleGenerator(item -> (item.getEnabled() == 1) ? null : "dead");

		userGrid.setSelectionMode(SelectionMode.SINGLE);
		userGrid.addSelectionListener(new SelectionListener<ReUser>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectionChange(SelectionEvent<ReUser> event) {
				Optional<ReUser> value = event.getFirstSelectedItem();
				if (value.isPresent()) {
					userForm.setVisible(true);
					userForm.setUser(value.get());
				}
			}
		});
	}

	public void updateUserList(String value) {
		String likeFilter = "%" + value + "%";
		List<ReUser> users = userRepo.findByusernameLikeIgnoreCase(likeFilter);
		userGrid.setItems(users);
	}

	public void updateUserListForId(Integer value) {
		List<ReUser> users = userRepo.findByidList(value);
		userGrid.setItems(users);
	}

	public void refreshGrid() {
		userGrid.clearSortOrder();	
	}
	
	private void addGridFilters() {
		if (userGrid.getHeaderRowCount() > 1) {
			HeaderRow filterRow1 = userGrid.getHeaderRow(1);
			userGrid.removeHeaderRow(filterRow1);
		}
		final HeaderRow filterRow = userGrid.appendHeaderRow();
		for (final Column<ReUser, ?> column : userGrid.getColumns()) {
			final HeaderCell headerCell = filterRow.getCell(column);
			if ("id".equals(column.getId())) {
				headerCell.setComponent(createFilterTextFieldForID());
			} else if ("username".equals(column.getId())) {
				headerCell.setComponent(createFilterTextFieldForResName());
			}
		}
	}

	private HorizontalLayout createFilterTextFieldForID() {
		TextFieldWithButton searcField1 = new TextFieldWithButton("", VaadinIcons.CLOSE);
		searcField1.getButton().addClickListener(e -> searcField1.getTextField().clear());

		searcField1.getTextField().addValueChangeListener(event -> {
			Notification.show(event.getValue());
			if(event.getValue() != null && !event.getValue().isEmpty())
				updateUserListForId(Integer.valueOf(event.getValue()));
			else
				updateUserList("");
		});

		return searcField1;
	}

	private HorizontalLayout createFilterTextFieldForResName() {
		TextFieldWithButton searcField2 = new TextFieldWithButton("", VaadinIcons.CLOSE);
		searcField2.getButton().addClickListener(e -> searcField2.getTextField().clear());

		searcField2.getTextField().addValueChangeListener(event -> {
			Notification.show(event.getValue());
			updateUserList(event.getValue());
		});

		return searcField2;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		this.userRepo = (ReUserRep) VaadinSession.getCurrent().getAttribute("userrepo");
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}