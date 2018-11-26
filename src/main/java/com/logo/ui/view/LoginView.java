package com.logo.ui.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.appreciated.material.MaterialTheme;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveLayout.ContainerType;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.logo.LogoresMainUI;
import com.logo.data.entity.ReUser;
import com.logo.data.repository.ReUserRep;
import com.logo.ui.components.SpellChecComboBox;
import com.logo.ui.components.SpellChecTextField;
import com.logo.util.Authentication;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;

import eu.maxschuster.vaadin.famfamflags.FamFamFlags;

@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends VerticalLayout implements View {

	public static final String VIEW_NAME = "LoginView";
	private static final long serialVersionUID = 1L;
	
	SpellChecComboBox<LangData> langCombo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(LogoResConstants.SELECTLANG));
	SpellChecTextField username;
	PasswordField password; 
	String title;
	String version;
	Button send;
	Button sendEmail;
	Label lab1 = new Label();
	Label lab2 = new Label();
	String lab1Caption;
	String lab2Caption;
	
	@Autowired(required=true)
	public Authentication auth;
	
	private Locale appLocale;
	
	@Autowired
	public transient ReUserRep userRepo;
	
	public static class LangData {
		String name;
		FamFamFlags icon;

		public LangData() {
			/**/
		}

		public FamFamFlags getIcon() {
			return icon;
		}

		public void setIcon(FamFamFlags icon) {
			this.icon = icon;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public static Collection<LangData> generate() {
			Collection<LangData> items = new ArrayList<>();
			LangData demoItem1 = new LangData();
			 
			demoItem1.setIcon(FamFamFlags.TURKEY);
			demoItem1.setName("Turkish");
			items.add(demoItem1);

			LangData demoItem2 = new LangData();
			demoItem2.setIcon(FamFamFlags.UNITED_STATES);
			demoItem2.setName("English");
			items.add(demoItem2);

			return items;
		}
	}

    @PostConstruct
    void init() {
    	appLocale = (Locale) VaadinSession.getCurrent().getAttribute("appLocale");
        setStyleName("backgroundimagemain");
        setSizeFull();
        setWidth("100%");
        setHeight("100%");
        ResponsiveLayout hLayout = loginLayout();
        addComponent(hLayout);
        
        setComponentAlignment(hLayout, Alignment.MIDDLE_CENTER);
    }

	ResponsiveLayout loginLayout() {
		ResponsiveLayout hlayout = new ResponsiveLayout();
		hlayout.addStyleName(MaterialTheme.CARD_5);
		hlayout.addStyleName(MaterialTheme.CARD_HOVERABLE);
		hlayout.addStyleName("csstag2");
		hlayout.setWidth("750px");
		hlayout.setHeight("350px");
		hlayout.setContainerType(ContainerType.FLUID);
		
		VerticalLayout left = new VerticalLayout();
		left.setSizeFull();

		
		title = LangHelper.getLocalizableMessage(LogoResConstants.LOGORESOURCEEDITOR);
    	lab1Caption = prepareCaption(title,"center","35");
    	lab1.setCaption(lab1Caption);
    	lab1.setCaptionAsHtml(true);

    	version = LangHelper.getLocalizableMessage(LogoResConstants.VERSIONSTR);
    	lab2Caption = prepareCaption(version,"top","20");
    	lab2.setCaption(lab2Caption);
    	lab2.setCaptionAsHtml(true);

    	left.addComponents(lab1,lab2);
    	left.setComponentAlignment(lab1, Alignment.MIDDLE_CENTER);
    	left.setComponentAlignment(lab2, Alignment.TOP_CENTER);
    	//hlayout.addComponent(left);
    	ResponsiveRow mainRow = hlayout.addRow();
    	mainRow.withDefaultRules(12, 12, 6, 6).withComponents(left);
    	//hlayout.setComponentAlignment(left, Alignment.MIDDLE_CENTER);
        
		VerticalLayout layout = new VerticalLayout();
		
        layout.setSizeFull();
        layout.setWidth("100%");
        layout.setHeight("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        
    	FormLayout content = new FormLayout();

    	username = new SpellChecTextField(LangHelper.getLocalizableMessage(LogoResConstants.USERNAME));
		content.addComponent(username);
		content.setSizeUndefined();
		content.setMargin(true);
		password = new PasswordField(LangHelper.getLocalizableMessage(LogoResConstants.PASS_WORDSTR));
		content.addComponent(password);
		username.focus();
		username.setValue("ahmete");

        Collection<LangData> data = LangData.generate();
        langCombo.setItems(data);
        langCombo.setItemCaptionGenerator(LangData::getName);
        langCombo.setItemIconGenerator(LangData::getIcon);
		langCombo.setValue(data.iterator().next());
		langCombo.setEmptySelectionAllowed(false);
		langCombo.setTextInputAllowed(false);
		content.addComponent(langCombo);

        
        send = new Button(LangHelper.getLocalizableMessage(LogoResConstants.LOGINSTR));
        send.setWidth("100%");
        send.addStyleName(MaterialTheme.BUTTON_ROUND+ " "+ MaterialTheme.BUTTON_FLAT+ " "+ MaterialTheme.BUTTON_CUSTOM);
        send.setIcon(VaadinIcons.SIGN_IN);
        send.setDescription(LangHelper.getLocalizableMessage(LogoResConstants.LOGINSTR));
        send.setTabIndex(2);
        
        sendEmail = new Button(LangHelper.getLocalizableMessage(LogoResConstants.FORGATPASS_WORDSTR));
        sendEmail.setWidth("100%");
        sendEmail.addStyleName("link");

        sendEmail.setIcon(VaadinIcons.ENVELOPE_O);
        sendEmail.setDescription(LangHelper.getLocalizableMessage(LogoResConstants.FORGATPASS_WORDSTR));
        
        send.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if(auth.authenticate(username.getValue(), password.getValue())){
					ReUser reUser = auth.getReUser(username.getValue()); 
					VaadinSession.getCurrent().setAttribute("user", reUser);
					VaadinSession.getCurrent().setAttribute("userrepo", userRepo);
					changeLocale();
					VaadinSession.getCurrent().setAttribute("appLocale",  appLocale);
					LogoresMainUI.getMrepositorycontainer().setAppLocale(appLocale);
					getUI().getNavigator().addView(ResourceViewNew.VIEW_NAME, ResourceViewNew.class);
					getUI().getNavigator().navigateTo(ResourceViewNew.VIEW_NAME);
				}else{
					Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
				}
			}
		});        
		send.setClickShortcut(KeyCode.ENTER);
        
		VerticalLayout buttonLayout = new VerticalLayout();
		
		buttonLayout.addComponents(sendEmail,send);
		
		username.setTabIndex(1);
		password.setTabIndex(2);
		langCombo.setTabIndex(3);
		send.setTabIndex(4);
		
        layout.addComponent(content);
		layout.addComponent(buttonLayout);
		layout.setComponentAlignment(content,Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(buttonLayout,Alignment.BOTTOM_CENTER);
		layout.setStyleName("card-hoverable-material-light-primary-color");
        //hlayout.addComponent(layout);
        mainRow.withDefaultRules(12, 12, 6, 6).withComponents(layout);
        
        //hlayout.setExpandRatio(left, 2.0F);
        //hlayout.setExpandRatio(layout, 2.0F);
        
		langCombo.addValueChangeListener(event -> {
			changeLocale();
			LogoresMainUI.getMrepositorycontainer().setAppLocale(appLocale);
			
			username.setCaption(LangHelper.getLocalizableMessage(LogoResConstants.USERNAME));
			password.setCaption(LangHelper.getLocalizableMessage(LogoResConstants.PASS_WORDSTR));
			send.setCaption(LangHelper.getLocalizableMessage(LogoResConstants.LOGINSTR));
			sendEmail.setCaption(LangHelper.getLocalizableMessage(LogoResConstants.FORGATPASS_WORDSTR));
			langCombo.setCaption(LangHelper.getLocalizableMessage(LogoResConstants.SELECTLANG));
			
			title = LangHelper.getLocalizableMessage(LogoResConstants.LOGORESOURCEEDITOR);
	    	lab1Caption = prepareCaption(title,"center","35");
	    	lab1.setCaption(lab1Caption);
	    	
	    	version = LangHelper.getLocalizableMessage(LogoResConstants.VERSIONSTR);
	    	lab2Caption = prepareCaption(version,"top","20");
	    	lab2.setCaption(lab2Caption);

		});
        
		return hlayout;
	}

	public void changeLocale() {
		Optional<LangData> lnData = langCombo.getOptionalValue();

		if (lnData.isPresent()) {
			String stringValue = lnData.get().name;

			if ("Turkish".equals(stringValue))
				appLocale = new Locale("tr-TR");
			else
				appLocale = new Locale("en_US");
		}
	}

	private String prepareCaption(String content,String align, String size)
	{
		String cap = "<p style="
				+ "background-color:white;font-style:bold;text-align:"+align+";font-size:"+size+"px;"
				+ "color:red" + ">" + content + "</p>";	
		return cap;
	}
	
    @Override
    public void enter(ViewChangeEvent event) {
    	appLocale = (Locale) VaadinSession.getCurrent().getAttribute("appLocale");
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
