package com.logo.ui.view;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addons.autocomplete.AutocompleteExtension;
import org.vaadin.marcus.MouseEvents;

import com.github.appreciated.material.MaterialTheme;
import com.jarektoro.responsivelayout.ResponsiveColumn.ColumnComponentAlignment;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.logo.LogoresMainUI;
import com.logo.data.entity.ReResource;
import com.logo.data.entity.ReUser;
import com.logo.data.repository.ReResourceRep;
import com.logo.ui.components.NotificationsButton;
import com.logo.ui.components.PaginationItemLayout;
import com.logo.ui.components.SearchLayout;
import com.logo.ui.components.SearchLayoutBoard;
import com.logo.ui.form.NewResourceForm;
import com.logo.ui.window.ResourceItemWindow;
import com.logo.ui.window.ResourceWindow;
import com.logo.ui.window.UserWindow;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.search.SearchParam;
import com.vaadin.annotations.Push;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import eu.maxschuster.vaadin.famfamflags.FamFamFlags;

@UIScope
@SpringView(name = ResourceViewNew.VIEW_NAME)
@Push(transport = Transport.WEBSOCKET_XHR)
public class ResourceViewNew extends VerticalLayout implements View {

	private static final long serialVersionUID = 1342110846527246562L;
	public static final String VIEW_NAME = "ResourceViewNew";
	
	private HorizontalLayout rootLayout = new HorizontalLayout();
	private VerticalLayout contentLayout = new VerticalLayout();
	private VerticalLayout menuLayout = new VerticalLayout();
	private Panel menuPanel = new Panel();
	
	private Panel searchLayout = new Panel();

	private UserView userView = new UserView();
	private ReMessageView messageView = new ReMessageView();
	private ReHelpDocsView helpDocsView = new ReHelpDocsView();
	private HomeView homeView = new HomeView(this);
	private TransferView transferView = new TransferView();
	private SearchLayout srcLayout =  new SearchLayout(this);
	private PaginationItemLayout gridContentItem = null;
	private Panel newResourcePanel = null;
	private Window notificationsWindow;
	private Button filter = new Button(LangHelper.getLocalizableMessage(LogoResConstants.ADVANCESEARCHSTR));
	
	//@Autowired
	//SpringNavigator navigator;

	@Autowired
	public transient ReResourceRep resRepo;
 
	
	@PostConstruct
	void init() {
		setStyleName(MaterialTheme.CARD_HOVERABLE);
		setSizeUndefined();
		setSizeFull();

		ResponsiveLayout headerLayout = generateHeader();
		headerLayout.addStyleName("card-hoverable-material-dark-primary-color");
		addComponent(headerLayout);
		
		searchLayout.setVisible(false);
		rootLayout.setSizeFull();
		addComponentsAndExpand(rootLayout);
		
		generateMenu();
		rootLayout.addComponent(contentLayout);
		rootLayout.setExpandRatio(contentLayout, 4.0f);
		contentLayout.addComponentsAndExpand(homeView);

		homeView.getAddNewResource().addClickListener(e -> {
			
			ReResource reResource = new ReResource();
			final  ResourceWindow window = new ResourceWindow(reResource,this,true);
		        this.getUI().getCurrent().addWindow(window);

			/*
			contentLayout.removeAllComponents();
			newResourcePanel = resourceFormLayout();
			contentLayout.addComponent(newResourcePanel);
			contentLayout.setExpandRatio(newResourcePanel, 4.0f);
			*/
		});

	}

	private void generateMenu() {
		menuLayout.setSizeUndefined();
		menuLayout.setWidth("100%");
		
		menuLayout.addStyleName(MaterialTheme.CARD_NO_PADDING);
		
		Embedded reindeerImage = new Embedded( null, new ThemeResource( "images/icon1.png" ) );
		reindeerImage.setWidth( "100%" ); 
		reindeerImage.setHeight( "130px" );
		
		Button menuImage = new Button(new ThemeResource("images/icon2.png"));
		Button close = new Button();
		close.setIcon(VaadinIcons.ANGLE_LEFT);
		close.addStyleName(ValoTheme.BUTTON_BORDERLESS +" " + LogoResConstants.STYLE_CUSTOM_WHITE);
		menuImage.addStyleName(ValoTheme.BUTTON_BORDERLESS +" " + LogoResConstants.STYLE_CUSTOM_WHITE);
		HorizontalLayout imageL = new HorizontalLayout();
		imageL.setSpacing(false);
		imageL.setMargin(true);
		imageL.setWidth("100%");
		imageL.addComponents(reindeerImage);

		imageL.setComponentAlignment(reindeerImage, Alignment.TOP_CENTER);
		
		Button home = new Button(LangHelper.getLocalizableMessage(LogoResConstants.HOMESTR));
		home.addStyleName(MaterialTheme.BUTTON_BORDERLESS +" " + LogoResConstants.STYLE_CUSTOM_WHITE);
		home.setIcon(VaadinIcons.HOME);

		Button addNewResource = new Button(LangHelper.getLocalizableMessage(LogoResConstants.ADDNEWRESOURCESTR));
		addNewResource.setIcon(VaadinIcons.PLUS);
		addNewResource.addStyleName(MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);
		
		Button allResources = new Button(LangHelper.getLocalizableMessage(LogoResConstants.ALLRESOURCESSTR));
		allResources.addStyleName(MaterialTheme.BUTTON_BORDERLESS +" " + LogoResConstants.STYLE_CUSTOM_WHITE);
		allResources.setIcon(VaadinIcons.DESKTOP);
		
		Button usersButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.USERSSTR));
		usersButton.addStyleName(MaterialTheme.BUTTON_BORDERLESS +" " + LogoResConstants.STYLE_CUSTOM_WHITE);
		usersButton.setIcon(VaadinIcons.USER);

		Button transferButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.EXPORTIMPORTSTR));
		transferButton.addStyleName(MaterialTheme.BUTTON_BORDERLESS +" " + LogoResConstants.STYLE_CUSTOM_WHITE);
		transferButton.setIcon(VaadinIcons.TRUCK);

		Button toolsButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.TOOLSSTR));
		toolsButton.addStyleName(MaterialTheme.BUTTON_BORDERLESS + " " + MaterialTheme.BUTTON_ICON_ALIGN_RIGHT +" " + LogoResConstants.STYLE_CUSTOM_WHITE);
		toolsButton.setIcon(VaadinIcons.ANGLE_DOWN);

		Button documentsButton = new Button("Belgeler");
		documentsButton.addStyleName(MaterialTheme.BUTTON_BORDERLESS + " " + MaterialTheme.BUTTON_ICON_ALIGN_RIGHT +" " + LogoResConstants.STYLE_CUSTOM_WHITE);
		documentsButton.setIcon(VaadinIcons.ANGLE_DOWN);
		
		Button convertToNonLoc = new Button("Convert to Non-Loc.");
		convertToNonLoc.addStyleName(MaterialTheme.BUTTON_BORDERLESS +" " + LogoResConstants.STYLE_CUSTOM_ORANGE);
		convertToNonLoc.setIcon(VaadinIcons.FILE_TEXT);

		Button copyResource = new Button("Copy Resource");
		copyResource.addStyleName(MaterialTheme.BUTTON_BORDERLESS +" " + LogoResConstants.STYLE_CUSTOM_ORANGE);
		copyResource.setIcon(VaadinIcons.FILE_TEXT);

		Button insertResource = new Button("Insert Multiple Res's");
		insertResource.addStyleName(MaterialTheme.BUTTON_BORDERLESS +" " + LogoResConstants.STYLE_CUSTOM_ORANGE);
		insertResource.setIcon(VaadinIcons.FILE_TEXT);
		
		Button usersManul = new Button("User's Manual");
		usersManul.addStyleName(MaterialTheme.BUTTON_BORDERLESS +" " + LogoResConstants.STYLE_CUSTOM_ORANGE);
		usersManul.setIcon(VaadinIcons.FILE_TEXT);

		Button translatorManual = new Button("Translator's Manual");
		translatorManual.addStyleName(MaterialTheme.BUTTON_BORDERLESS +" " + LogoResConstants.STYLE_CUSTOM_ORANGE);
		translatorManual.setIcon(VaadinIcons.FILE_TEXT);

		Button reports = new Button("Reports");
		reports.addStyleName(MaterialTheme.BUTTON_BORDERLESS +" " + LogoResConstants.STYLE_CUSTOM_ORANGE);
		reports.setIcon(VaadinIcons.FILE_TEXT);
		
		VerticalLayout menuButtonLayout = new VerticalLayout();
		menuButtonLayout.addComponent(home);
		//menuButtonLayout.addComponent(addNewResource);
		menuButtonLayout.addComponent(allResources);
		menuButtonLayout.addComponent(usersButton);
		
		menuButtonLayout.addComponent(transferButton);
		VerticalLayout submenuTransfer = new VerticalLayout();
		submenuTransfer.setVisible(false);
		menuButtonLayout.addComponent(submenuTransfer);
		
		menuButtonLayout.addComponent(toolsButton);
		VerticalLayout submenuTools = new VerticalLayout();
		submenuTools.setVisible(false);
		submenuTools.addComponent(convertToNonLoc);
		submenuTools.addComponent(copyResource);
		submenuTools.addComponent(insertResource);
		menuButtonLayout.addComponent(submenuTools);

		menuButtonLayout.addComponent(documentsButton);
		VerticalLayout submenuDocuments = new VerticalLayout();
		submenuDocuments.setVisible(false);
		submenuDocuments.addComponent(usersManul);
		submenuDocuments.addComponent(translatorManual);
		submenuDocuments.addComponent(reports);
		menuButtonLayout.addComponent(submenuDocuments);
		
		/**menuButtonLayout.setComponentAlignment(imageL, Alignment.TOP_CENTER);**/
		menuButtonLayout.setComponentAlignment(home, Alignment.TOP_LEFT);
		menuButtonLayout.setComponentAlignment(usersButton, Alignment.TOP_LEFT);
		menuButtonLayout.setComponentAlignment(transferButton, Alignment.TOP_LEFT);
		menuButtonLayout.setComponentAlignment(allResources, Alignment.TOP_LEFT);
		menuButtonLayout.setComponentAlignment(toolsButton, Alignment.TOP_LEFT);
		menuButtonLayout.setComponentAlignment(documentsButton, Alignment.TOP_LEFT);
		
		transferButton.addClickListener(e -> {
			contentLayout.removeAllComponents();
			contentLayout.addComponent(transferView);
			contentLayout.setExpandRatio(transferView, 4.0f);
			close.click();
		});

		toolsButton.addClickListener(e -> {
			if (submenuTools.isVisible()) {
				submenuTools.setVisible(false);
				toolsButton.setIcon(VaadinIcons.ANGLE_DOWN);
			} else {
				submenuTools.setVisible(true);
				toolsButton.setIcon(VaadinIcons.ANGLE_RIGHT);
			}
		});

		documentsButton.addClickListener(e -> {
			if (submenuDocuments.isVisible()) {
				submenuDocuments.setVisible(false);
				documentsButton.setIcon(VaadinIcons.ANGLE_DOWN);
			} else {
				submenuDocuments.setVisible(true);
				documentsButton.setIcon(VaadinIcons.ANGLE_RIGHT);
			}
		});

		menuPanel.setSizeFull();
		menuPanel.setHeight("100%");
		menuPanel.setWidth("100%");
		menuPanel.addStyleName("in-slide-right");
		menuPanel.addStyleName("customStyle");

		menuPanel.setVisible(false);
		
		menuPanel.setContent(menuLayout);
		
		rootLayout.addComponent(menuPanel);
		rootLayout.setExpandRatio(menuPanel, 1.25f);

		menuLayout.addComponent(menuButtonLayout);

		close.addClickListener(e -> {
			if (menuPanel.isVisible())
				menuPanel.setVisible(false);
			else
				menuPanel.setVisible(true);
		});

		usersButton.addClickListener(e -> {
			contentLayout.removeAllComponents();
			contentLayout.addComponent(userView);
			contentLayout.setExpandRatio(userView, 4.0f);
			close.click();
		});
		
		reports.addClickListener(e -> {
			contentLayout.removeAllComponents();
			contentLayout.addComponent(messageView);
			contentLayout.setExpandRatio(messageView, 4.0f);
			close.click();
		});

		home.addClickListener(e -> {
			contentLayout.removeAllComponents();
			contentLayout.addComponent(homeView);
			contentLayout.setExpandRatio(homeView, 4.0f);
			searchLayout.setVisible(false);
			filter.setIcon(VaadinIcons.ANGLE_DOWN);
			close.click();
		});

		allResources.addClickListener(e -> {
			contentLayout.removeAllComponents();
			gridContentItem = new PaginationItemLayout(LogoResConstants.SEARCH_ALL, null, "", this, false);
			contentLayout.addComponent(gridContentItem);
			contentLayout.setExpandRatio(gridContentItem, 4.0f);
			close.click();
		});
		
		addNewResource.addClickListener(e -> homeView.getAddNewResource().click());
		
		MouseEvents mouseEvents = MouseEvents.enableFor(menuLayout);
		mouseEvents.addMouseOutListener(() -> close.click());
	}
	
	public void createResoucePageForAll()
	{
		contentLayout.removeAllComponents();
		gridContentItem = new PaginationItemLayout(LogoResConstants.SEARCH_ALL, null, "", this, false);
		contentLayout.addComponent(gridContentItem);
		contentLayout.setExpandRatio(gridContentItem, 4.0f);
	}

	public void createResoucePage(String resNr, boolean add)
	{
		contentLayout.removeAllComponents();
		gridContentItem = new PaginationItemLayout(LogoResConstants.SEARCH_RESOURCENR, null, resNr, this, add);
		contentLayout.addComponent(gridContentItem);
		contentLayout.setExpandRatio(gridContentItem, 4.0f);
	}

	public void createResoucePageForParam(SearchParam sParam, boolean add)
	{
		contentLayout.removeAllComponents();
		gridContentItem = new PaginationItemLayout(LogoResConstants.SEARCH_RESOURCEITEM, sParam, "", this, add);
		contentLayout.addComponent(gridContentItem);
		contentLayout.setExpandRatio(gridContentItem, 4.0f);
	}
	
	public void createMessageLayout()
	{
		contentLayout.removeAllComponents();
		contentLayout.addComponent(messageView);
		contentLayout.setExpandRatio(messageView, 4.0f);
	}

	public void createHelpDocsLayout()
	{
		contentLayout.removeAllComponents();
		contentLayout.addComponent(helpDocsView);
		contentLayout.setExpandRatio(helpDocsView, 4.0f);
	}

	public void createResoucePageForParamAll(SearchParam sParam, boolean add)
	{
		contentLayout.removeAllComponents();
		gridContentItem = new PaginationItemLayout(LogoResConstants.SEARCH_RESOURCEITEMALL, sParam, "", this, add);
		contentLayout.addComponent(gridContentItem);
		contentLayout.setExpandRatio(gridContentItem, 4.0f);
	}

	private ResponsiveLayout generateHeader() {
		Resource res1 = new ClassResource("/img/Search_16.png");
		Image image1 = new Image(null, res1);
		image1.setStyleName("img-rounded");
		image1.setHeight("16px");
		image1.setWidth("16px");

		ResponsiveLayout headLayout = new ResponsiveLayout();
		
		//headLayout.setWidth("100%");
		//headLayout.setHeight("150px");

		
		Button menuButton = new Button("");
		menuButton.setIcon(VaadinIcons.MENU);
		menuButton.addStyleName(MaterialTheme.BUTTON_BORDERLESS + " " + MaterialTheme.BUTTON_ROUND + " " + LogoResConstants.STYLE_CUSTOM_WHITE);
		
		//menuButton.setHeight("100%");		
		
		menuButton.addClickListener(e -> {
			if (menuPanel.isVisible()) {
				menuPanel.setVisible(false);
			} else {
				menuPanel.setVisible(true);
			}
			if(searchLayout.isVisible())
			{
				searchLayout.setVisible(false);
				filter.setIcon(VaadinIcons.ANGLE_DOWN);
			}
		});
		
		MouseEvents mouseEvents = MouseEvents.enableFor(menuButton);
		mouseEvents.addMouseOverListener(() ->  menuButton.click());

		ReUser reUser = (ReUser) VaadinSession.getCurrent().getAttribute("user");
		String userName = reUser.getName();

		
		MenuBar userButton = getMenuForUser(userName, false);
		HorizontalLayout userLayout = new HorizontalLayout();
		userLayout.setHeight("100%");
		
		NotificationsButton notifyButton = buildNotificationsButton();
		notifyButton.setUnreadCount(4);
		notifyButton.addStyleName("unread");

		notifyButton.addClickListener(event -> notifyButton.setUnreadCount(0));
		userLayout.setSpacing(false);

		
		HorizontalLayout flagLayout = new HorizontalLayout();
		Locale appLocale = LogoresMainUI.getMrepositorycontainer().getAppLocale();
		if(appLocale.getLanguage().equalsIgnoreCase("tr-TR"))
		{
			flagLayout.setIcon(FamFamFlags.TURKEY);	
		}
		else
			flagLayout.setIcon(FamFamFlags.UNITED_STATES);
		

		userLayout.addComponents(flagLayout,notifyButton,userButton);
		userLayout.setSpacing(true);
		//userLayout.setComponentAlignment(userButton, Alignment.MIDDLE_CENTER);
		//userLayout.setComponentAlignment(notifyButton, Alignment.MIDDLE_CENTER);
		//userLayout.setExpandRatio(userButton, 1.0f);
		//userLayout.setExpandRatio(notifyButton, 2.0f);

		
		Button logoLabel = new Button(LangHelper.getLocalizableMessage(LogoResConstants.LOGORESOURCEEDITOR));
		logoLabel.addStyleName(MaterialTheme.BUTTON_BORDERLESS + " " + MaterialTheme.BUTTON_ROUND + " " + LogoResConstants.STYLE_CUSTOM_WHITE);

		filter.setIcon(VaadinIcons.ANGLE_DOWN);
		filter.setStyleName(MaterialTheme.BUTTON_BORDERLESS + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_ICON_ALIGN_RIGHT + " " + LogoResConstants.STYLE_CUSTOM_WHITE);
		
		logoLabel.addClickListener(e -> {
			contentLayout.removeAllComponents();
			contentLayout.addComponent(homeView);
			contentLayout.setExpandRatio(homeView, 4.0f);
			menuPanel.setVisible(false);
			searchLayout.setVisible(false);
			filter.setIcon(VaadinIcons.ANGLE_DOWN);
		});

		filter.addClickListener(e -> {
			
			if (searchLayout.isVisible())
			{
				logoLabel.click();
				srcLayout =  new SearchLayout(this);
				srcLayout.setSizeFull();
				srcLayout.setHeight("100%");
				srcLayout.setWidth("100%");
				searchLayout.setVisible(false);
				filter.setIcon(VaadinIcons.ANGLE_DOWN);
			}
			else
			{
				contentLayout.removeAllComponents();
				srcLayout =  new SearchLayout(this);
				srcLayout.setSizeFull();
				srcLayout.setHeight("100%");
				srcLayout.setWidth("100%");
				contentLayout.addComponent(srcLayout);
				contentLayout.setExpandRatio(srcLayout, 4.0f);
				searchLayout.setVisible(true);
				filter.setIcon(VaadinIcons.ANGLE_UP);
			}
		});

		HorizontalLayout scLayout = getSearchLayout();
		
		HorizontalLayout leftLayout1 = new HorizontalLayout();
		HorizontalLayout rightLayout2 = new HorizontalLayout();
		
		leftLayout1.setSpacing(false);
		leftLayout1.addComponents(menuButton, flagLayout, logoLabel, filter);
		leftLayout1.setComponentAlignment(menuButton, Alignment.MIDDLE_LEFT);
		leftLayout1.setComponentAlignment(flagLayout, Alignment.MIDDLE_LEFT);
		leftLayout1.setComponentAlignment(logoLabel, Alignment.MIDDLE_LEFT);
		leftLayout1.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
		
		rightLayout2.addComponents(notifyButton,userButton);
		
		ResponsiveRow row = headLayout.addRow().withHorizontalSpacing(true);
		
		row.addColumn().withDisplayRules(12, 12, 12, 4).withComponent(leftLayout1).setAlignment(ColumnComponentAlignment.LEFT);
		row.addColumn().withDisplayRules(12, 12, 12, 4).withComponent(scLayout).setAlignment(ColumnComponentAlignment.CENTER);
		row.addColumn().withDisplayRules(12, 12, 12, 4).withComponent(rightLayout2).setAlignment(ColumnComponentAlignment.RIGHT);

		return headLayout;
	}

	private HorizontalLayout getSearchLayout()
	{
		HorizontalLayout schLayout = new HorizontalLayout();
		TextField searchField = new TextField();
		searchField.setSizeUndefined();
		searchField.addStyleName(MaterialTheme.TEXTFIELD_CUSTOM);
		searchField.addStyleName(LogoResConstants.STYLE_TEXTFIEL_SEARCH);
		searchField.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		searchField.setIcon(VaadinIcons.SEARCH);
		searchField.setWidth("100px");

		
		MouseEvents mouseEvents = MouseEvents.enableFor(searchField);

		mouseEvents.addMouseOutListener(() -> {
			searchField.setWidth("100px");
			searchField.removeStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
			searchField.addStyleName(LogoResConstants.STYLE_TEXTFIEL_SEARCH);
		});

		schLayout.addLayoutClickListener(event -> {
			if (event.getChildComponent() == searchField) {
				searchField.setWidth("300px");
				searchField.removeStyleName(LogoResConstants.STYLE_TEXTFIEL_SEARCH);
				searchField.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
			}
		});
		
		Button searchButton = new Button("");
		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addStyleName(MaterialTheme.BUTTON_BORDER+ " "+ MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);
		

		Button addNewResource = new Button(LangHelper.getLocalizableMessage(LogoResConstants.ADDNEWRESOURCESTR));
		addNewResource.setIcon(VaadinIcons.PLUS);
		addNewResource.addStyleName(MaterialTheme.BUTTON_BORDER + " " +  MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);
		
		AutocompleteExtension<String> resNrExtension = new AutocompleteExtension<>(searchField);

		resNrExtension.setSuggestionGenerator(this::suggestResource);

		resNrExtension.addSuggestionSelectListener(event ->{
			event.getSelectedItem().ifPresent(Notification::show);
			searchField.setValue(event.getSelectedValue());
			searchButton.click();
		});

		searchButton.addClickListener(event -> {
			createResoucePage(searchField.getValue(),true);
			searchField.setValue("");
		});

		addNewResource.addClickListener(e -> homeView.getAddNewResource().click());
		
		searchButton.setVisible(false);
		schLayout.addComponent(searchField);
		schLayout.addComponent(addNewResource);
		//searchLayout.addComponent(addNewResource);

		schLayout.setComponentAlignment(searchField, Alignment.MIDDLE_LEFT);
		schLayout.setComponentAlignment(addNewResource, Alignment.MIDDLE_LEFT);
		//searchLayout.setComponentAlignment(addNewResource, Alignment.MIDDLE_RIGHT);
		return schLayout;

		
	}

	private String getSuggestValue(String value)
	{
		String resStr = "";
		String[] parts = value.split("->");
		if (parts.length > 0) {
			resStr = parts[1];
		}
		return resStr;
	}

	private List<String> suggestResource(String query, int cap) {
		if(query.length()>2 && StringUtils.isNumeric(query))
			return getResources(query).stream().filter(p -> p.contains(query)).limit(cap).collect(Collectors.toList());
		else
			return Arrays.asList("","");
	}

	public  List<String> getResources(String resNr) {
		return findByResNrLike(resNr);
	}

	public List<String> findByResNrLike(String resNrFilter) {
		return resRepo.findByresourceNrLike(Integer.parseInt(resNrFilter));
	}
	@Override
	public void enter(ViewChangeEvent event) {
		/*    */
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	private static Panel resourceFormLayout() {
		Panel pn = new Panel();
		pn.setSizeFull();

		final VerticalLayout mainLayoutIn = new VerticalLayout();
		mainLayoutIn.setSizeFull();
		mainLayoutIn.setMargin(false);
		mainLayoutIn.setSpacing(false);

		HorizontalLayout contentLayoutMain = new HorizontalLayout();
		contentLayoutMain.setSpacing(false);
		contentLayoutMain.setMargin(true);
		contentLayoutMain.setSizeFull();
		contentLayoutMain.setWidth("100%");
		contentLayoutMain.setHeight("100%");

		NewResourceForm gridContent = new NewResourceForm();

		contentLayoutMain.addComponent(gridContent);
		contentLayoutMain.setComponentAlignment(gridContent, Alignment.TOP_CENTER);
		contentLayoutMain.setExpandRatio(gridContent, 1);

		mainLayoutIn.addComponent(contentLayoutMain);
		mainLayoutIn.setComponentAlignment(contentLayoutMain, Alignment.TOP_CENTER);
		mainLayoutIn.setExpandRatio(contentLayoutMain, 1);

		HorizontalLayout wrapper = new HorizontalLayout(mainLayoutIn);
		wrapper.setSizeFull();
		wrapper.setExpandRatio(mainLayoutIn, 1);
		wrapper.setComponentAlignment(mainLayoutIn, Alignment.TOP_CENTER);
		wrapper.setSpacing(false);

		pn.setContent(wrapper);
		return pn;
	}

	private MenuBar getMenuForUser(String caption, boolean splitButton) {
		@SuppressWarnings("unused")
		Command click = new Command() {
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				if (selectedItem.getText().equals("Ayarlar")) {
						final UserWindow window = new UserWindow();
						getUI().getCurrent().addWindow(window);
					
					Notification.show("Clicked " + selectedItem.getText());
				}
				if (selectedItem.getText().equals("Çıkış")) {
					getUI().getPage().setLocation(getLogoutPath());
				}
			}
		};

		MenuBar split = new MenuBar();
		split.addStyleName(MaterialTheme.MENUBAR_BORDERLESS);
		
		MenuBar.MenuItem dropdown = split.addItem(caption, VaadinIcons.USER, null);
		
		
		if (splitButton) {
			dropdown = split.addItem("", click);
		}
		dropdown.addItem("Ayarlar", VaadinIcons.COG, click);
		dropdown.addItem("Çıkış", VaadinIcons.SIGN_OUT, click);

		return split;
	}

	private String getLogoutPath() {
		return getUI().getPage().getLocation().getPath();
	}
	
	private NotificationsButton buildNotificationsButton() {
		NotificationsButton result = new NotificationsButton();
		result.addClickListener(event -> {
			openNotificationsPopup(event);
			if (result.getUnreadCounter() != 0)
				result.setUnreadCounter(0);
			else
				result.setUnreadCounter(4);
		});
		return result;
	}

	private void openNotificationsPopup(final ClickEvent event) {
		VerticalLayout notificationsLayout = new VerticalLayout();
		notificationsLayout.setMargin(true);
		notificationsLayout.setSpacing(true);
		notificationsLayout.setStyleName(MaterialTheme.WINDOW_TOP_TOOLBAR);

		Label title = new Label("Notifications");
		title.addStyleName(ValoTheme.LABEL_H3);
		title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		notificationsLayout.addComponent(title);

		VerticalLayout notificationLayout = new VerticalLayout();
		notificationLayout.addStyleName("notification-item");

		Label titleLabel = new Label("notification1");
		titleLabel.addStyleName("notification-title");

		Label timeLabel = new Label("notification2");
		timeLabel.addStyleName("notification-time");

		Label contentLabel = new Label("notification3");
		contentLabel.addStyleName("notification-content");

		notificationLayout.addComponents(titleLabel, timeLabel, contentLabel);
		notificationsLayout.addComponent(notificationLayout);

		HorizontalLayout footer = new HorizontalLayout();
		footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
		footer.setWidth("100%");
		Button showAll = new Button("View All Notifications");
		showAll.addClickListener(evnt -> {});
		
		showAll.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		showAll.addStyleName(ValoTheme.BUTTON_LINK);
		showAll.addStyleName(ValoTheme.BUTTON_SMALL);
		footer.addComponent(showAll);
		footer.setComponentAlignment(showAll, Alignment.TOP_CENTER);
		notificationsLayout.addComponent(footer);

		if (notificationsWindow == null) {
			notificationsWindow = new Window();
			notificationsWindow.setWidth(300.0f, Unit.PIXELS);
			notificationsWindow.addStyleName(MaterialTheme.WINDOW_TOP_TOOLBAR);
			notificationsWindow.setClosable(false);
			notificationsWindow.setResizable(false);
			notificationsWindow.setDraggable(false);
			notificationsWindow.setContent(notificationsLayout);
		}

		if (!notificationsWindow.isAttached()) {
			notificationsWindow.setPositionY(event.getClientY() - event.getRelativeY() + 40);
			notificationsWindow.setPositionX(event.getClientX() - 280);

			notificationsWindow.setParent(null);
			getUI().addWindow(notificationsWindow);

			notificationsWindow.focus();
		} else {
			notificationsWindow.close();
		}
		notificationsWindow.addBlurListener(e -> notificationsWindow.close());
	}
	
	public Button getFilter() {
		return filter;
	}

	public Panel getSearchLayoutForView() {
		return this.searchLayout;
	}

	

}
