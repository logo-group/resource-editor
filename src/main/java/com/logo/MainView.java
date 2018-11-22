package com.logo;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.logo.data.Project;
import com.logo.data.ProjectRep;
import com.logo.data.ReUser;
import com.logo.data.ReUserRep;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.ValoMenuLayout;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@SpringView(name = MainView.VIEW_NAME)
public class MainView extends HorizontalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "MainView";
	private Label currentUser;

	@Autowired(required = true)
	public transient ReUserRep userRepo;

	@Autowired(required = true)
	public transient ProjectRep projectRepo;

	final Label foo = new Label();

	CssLayout menuItemsLayout = new CssLayout();
	CssLayout menu = new CssLayout();
	ValoMenuLayout root = new ValoMenuLayout();

	private Grid<ReUser> userGrid = new Grid<>(ReUser.class);
	private Grid<Project> projectGrid = new Grid<>(Project.class);
	private HorizontalLayout mainContainer = null;
	private CssLayout searching = new CssLayout();
	private HorizontalLayout searchLayout = new HorizontalLayout();
	private VerticalLayout userMainLayout = null;
	private VerticalLayout projectMainLayout = null;
	
	private Button addUserBtn = new Button("Add new user");
	private Button addProjectBtn = new Button("Add new project");
	private TextField filterByUserName = new TextField("Filter by username");
	private TextField filterByProjectName = new TextField("Filter by projectname");


	@PostConstruct
	void init() {
		this.userRepo = (ReUserRep) VaadinSession.getCurrent().getAttribute("userrepo");
		currentUser = new Label("Current User");
		mainContainer = root.getContentContainer();
		userMainLayout = new VerticalLayout();
		projectMainLayout = new VerticalLayout();

		
		setSizeFull();

		addComponent(root);
		addStyleName(ValoTheme.UI_WITH_MENU);

		TextField searchRnr = new TextField();
		searchRnr.setPlaceholder("Search resource nr");
		searchRnr.addStyleName(ValoTheme.TEXTFIELD_LARGE);
		searchRnr.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		searchRnr.setIcon(VaadinIcons.SEARCH);
		searchRnr.setWidth("160px");
		
		TextField searchName = new TextField();
		searchName.setPlaceholder("Search resource name");
		searchName.addStyleName(ValoTheme.TEXTFIELD_LARGE);
		searchName.setWidth("260px");
		
		Button searchButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHSTR));
		searchButton.addStyleName(ValoTheme.BUTTON_LARGE);
		searchButton.addStyleName(ValoTheme.BUTTON_PRIMARY);


		Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE_SMALL);
		clearFilterTextBtn.setDescription("Clear the current filter");
		clearFilterTextBtn.addStyleName(ValoTheme.BUTTON_LARGE);
		clearFilterTextBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		clearFilterTextBtn.addClickListener(e -> {searchRnr.clear(); searchName.clear();});

		searching.addComponents(searchRnr, searchName, searchButton, clearFilterTextBtn);
		searching.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		
		
		searchLayout.setSizeFull();
		searchLayout.setSizeUndefined();
		searchLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		
		
		searchLayout.addComponent(searching);
		
		filterByUserName.addValueChangeListener(e -> updateUserList());
		addUserBtn.setIcon(VaadinIcons.PLUS_CIRCLE_O);

		filterByProjectName.addValueChangeListener(e -> updateProjectList());
		addProjectBtn.setIcon(VaadinIcons.PLUS_CIRCLE_O);

		searchButton.addClickListener(e -> {
			String sTxt = (searchRnr.getValue() == null || searchRnr.getValue().equals("")) ? "0" : searchRnr.getValue();
			VaadinSession.getCurrent().setAttribute("searchText", sTxt);
			getUI().getNavigator().removeView(MainView.VIEW_NAME);
			getUI().getNavigator().navigateTo(ResourceViewNew.VIEW_NAME);
		});

		generateMainLayout();
	}

	private void generateMainLayout() {
		mainContainer.removeComponent(userMainLayout);
		mainContainer.removeComponent(projectMainLayout);
		mainContainer.addComponent(searchLayout);
		mainContainer.setComponentAlignment(searchLayout, Alignment.TOP_CENTER);
		searchLayout.setComponentAlignment(searching, Alignment.MIDDLE_CENTER);
	}

	public void updateUserList() {
		String likeFilter = "%" + filterByUserName.getValue() + "%";
		List<ReUser> users = userRepo.findByusernameLikeIgnoreCase(likeFilter);
		userGrid.setItems(users);
	}

	public void updateProjectList() {
		String likeFilter = "%" + filterByUserName.getValue() + "%";
		List<Project> projects = projectRepo.findByprojectNameLikeIgnoreCase(likeFilter);
		projectGrid.setItems(projects);
	}

	 
	public MenuBar getMenuBar() {
		Command click = new Command() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				if (selectedItem.getText().equals("Undo")) {
					getUI().getNavigator().removeView(MainView.VIEW_NAME);
					VaadinSession.getCurrent().setAttribute("user", null);
					getUI().getPage().setLocation(getLogoutPath());
					getSession().close();
				}
			}
		};

		MenuBar menubar = new MenuBar();
		menubar.setWidth("100%");
		final MenuBar.MenuItem file = menubar.addItem("File", null);
		final MenuBar.MenuItem newItem = file.addItem("New", null);
		file.setIcon(VaadinIcons.FILE);
		file.addItem("Open file...", click);
		file.addSeparator();

		newItem.addItem("File", click);
		newItem.addItem("Folder", click);
		newItem.addItem("Project...", click);

		file.addItem("Close", click);
		file.addItem("Close All", click);
		file.addSeparator();

		file.addItem("Kaydet", click);
		file.addItem("Save As...", click);
		file.addItem("Save All", click);

		final MenuBar.MenuItem edit = menubar.addItem("Değiştir", null);
		edit.addItem("Undo", click);
		edit.addItem("Redo", click).setEnabled(false);
		edit.addSeparator();

		edit.addItem("Cut", click);
		edit.addItem("Copy", click);
		edit.addItem("Paste", click);
		edit.addSeparator();

		final MenuBar.MenuItem find = edit.addItem("Find/Replace", null);

		find.addItem("Google Search", click);
		find.addSeparator();
		find.addItem("Find/Replace...", click);
		find.addItem("Find Next", click);
		find.addItem("Find Previous", click);

		Command check = new Command() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Notification.show(selectedItem.isChecked() ? "Checked" : "Unchecked");
			}
		};

		final MenuBar.MenuItem view = menubar.addItem("View", null);
		view.addItem("Show Status Bar", check).setCheckable(true);
		MenuItem title = view.addItem("Show Title Bar", check);
		title.setCheckable(true);
		title.setChecked(true);
		view.addItem("Customize Toolbar...", click);
		view.addSeparator();

		view.addItem("Actual Size", click);
		view.addItem("Zoom In", click);
		view.addItem("Zoom Out", click);

		MenuItem fav = menubar.addItem("", check);
		fav.setIcon(VaadinIcons.ABACUS);
		fav.setStyleName("icon-only");
		fav.setCheckable(true);
		fav.setChecked(true);

		fav = menubar.addItem("", check);
		fav.setIcon(VaadinIcons.ABSOLUTE_POSITION);
		fav.setStyleName("icon-only");
		fav.setCheckable(true);
		fav.setCheckable(true);

		menubar.addItem("Attach", click).setIcon(VaadinIcons.PAPERCLIP);
		menubar.addItem("Undo", click).setIcon(VaadinIcons.ROTATE_LEFT);
		MenuItem redo = menubar.addItem("Redo", click);
		redo.setIcon(VaadinIcons.ROTATE_RIGHT);
		redo.setEnabled(false);
		menubar.addItem("Upload", click).setIcon(VaadinIcons.UPLOAD);

		return menubar;
	}

	private String getLogoutPath() {
		return getUI().getPage().getLocation().getPath();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		this.userRepo = (ReUserRep) VaadinSession.getCurrent().getAttribute("userrepo");
		currentUser.setCaption("Current user : " + VaadinSession.getCurrent().getAttribute("user").toString());
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