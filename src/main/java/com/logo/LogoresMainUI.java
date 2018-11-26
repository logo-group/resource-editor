package com.logo;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.logo.data.repository.ReAlbaniankvRep;
import com.logo.data.repository.ReArabicegRep;
import com.logo.data.repository.ReArabicjoRep;
import com.logo.data.repository.ReArabicsaRep;
import com.logo.data.repository.ReAzerbaijaniazRep;
import com.logo.data.repository.ReEnglishusRep;
import com.logo.data.repository.ReFrenchfrRep;
import com.logo.data.repository.ReGeorgiangeRep;
import com.logo.data.repository.ReGermandeRep;
import com.logo.data.repository.ReHelpDocsRep;
import com.logo.data.repository.ReMessageRep;
import com.logo.data.repository.RePersianirRep;
import com.logo.data.repository.ReProjectVerisonRep;
import com.logo.data.repository.ReResourceRep;
import com.logo.data.repository.ReResourceitemRep;
import com.logo.data.repository.ReResourceitemShortRep;
import com.logo.data.repository.ReRomanianroRep;
import com.logo.data.repository.ReRussianruRep;
import com.logo.data.repository.ReTurkishtrRep;
import com.logo.data.repository.ReTurkmentmRep;
import com.logo.data.repository.ReUserRep;
import com.logo.ui.view.LoginView;
import com.logo.ui.view.ReHelpDocsView;
import com.logo.ui.view.ReMessageView;
import com.logo.ui.view.ResourceViewNew;
import com.logo.ui.view.TransferView;
import com.logo.ui.view.UserView;
import com.logo.util.ApplicationContextLocator;
import com.logo.util.RepositoryContainer;
import com.logo.util.UISessionState;
import com.vaadin.addon.responsive.Responsive;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Theme("demo")
@SpringUI
@SpringViewDisplay
@Title("Logo Resource Editor")
@Viewport("user-scalable=no,width=device-width,height=device-height,initial-scale=0.50,maximum-scale=1")
@Push
public class LogoresMainUI extends UI {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SpringViewProvider viewProvider;

	@Autowired
	private transient UISessionState sessionState;

	@Autowired
	private transient ReUserRep reUserRep;
	
	@Autowired
	private transient ReResourceRep resRepo;
	
	@Autowired
	private transient ReResourceitemRep reResourceitemRep;	

	@Autowired
	private transient ReResourceitemShortRep reResourceitemShortRep;	

	@Autowired
	private transient ReTurkishtrRep reTurkishtrRep;

	@Autowired
	private transient ReEnglishusRep reEnglishusRep;

	@Autowired
	private transient ReGermandeRep reGermandeRep;

	@Autowired
	private transient RePersianirRep rePersianirRep;

	@Autowired
	private transient ReAzerbaijaniazRep reAzerbaijaniazRep;

	@Autowired
	private transient ReRussianruRep reRussianruRep;

	@Autowired
	private transient ReMessageRep reMessageRep;

	@Autowired
	private transient ReHelpDocsRep reHelpDocsRep;

	@Autowired
	private transient ReRomanianroRep reRomanianroRep;
	
	@Autowired
	private transient ReGeorgiangeRep reGeorgiangeRep;

	@Autowired
	private transient ReArabicjoRep reArabicjoRep;

	@Autowired
	private transient ReFrenchfrRep reFrenchfrRep;

	@Autowired
	private transient ReAlbaniankvRep reAlbaniankvRep;

	@Autowired
	private transient ReTurkmentmRep reTurkmentmRep;

	@Autowired
	private transient ReArabicegRep  reArabicegRep;

	@Autowired
	private transient ReArabicsaRep  reArabicsaRep;

	@Autowired
	private transient ReProjectVerisonRep reProjectVerisonRep;
	
	private static final RepositoryContainer mRepositoryContainer = new RepositoryContainer();
	
	@Override
	protected void refresh(VaadinRequest request) {
		for (Window w : getWindows()) {
			w.close();
		}
		init(request);
		super.refresh(request);
	}

	@Override
	protected void init(VaadinRequest request) {
		Locale locale = VaadinService.getCurrentRequest().getLocale();
		VaadinSession.getCurrent().setAttribute("appLocale",  locale);
		if (sessionState == null) {
			// This initialises the Spring Context from
			// ContextConfiguration.java
			ApplicationContextLocator.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(this);
		}

		if (sessionState.isReplaceSession()) {
			// We've just been redirected back on session expiry
			sessionState.setVaadinSession(VaadinSession.getCurrent());
			sessionState.setReplaceSession(false);
		}

		if (sessionState.staleSessionCheck(VaadinSession.getCurrent(), request)) {
			return;
		}

		final VerticalLayout root = new VerticalLayout();
		root.setSizeFull();

		root.setMargin(true);
		root.setSpacing(true);

		setContent(root);
		Panel springViewDisplay = new Panel();
		springViewDisplay.setSizeFull();
		root.addComponent(springViewDisplay);
		root.setExpandRatio(springViewDisplay, 1.0f);

		getNavigator().addView(LoginView.VIEW_NAME, LoginView.class);

		getNavigator().addProvider(viewProvider);
		initialize();
		router("");
		 new Responsive(this);
	}

	private void router(String route) {
		if (getSession().getAttribute("user") != null) {
			getNavigator().addView(LoginView.VIEW_NAME, LoginView.class);
			getNavigator().addView(ResourceViewNew.VIEW_NAME, ResourceViewNew.class);
			getNavigator().addView(UserView.VIEW_NAME, UserView.class);
			getNavigator().addView(ReMessageView.VIEW_NAME, ReMessageView.class);
			getNavigator().addView(ReHelpDocsView.VIEW_NAME, ReHelpDocsView.class);
			getNavigator().addView(TransferView.VIEW_NAME,TransferView.class);
			if (route.equals("ResourceViewNew")) {
				getNavigator().navigateTo(ResourceViewNew.VIEW_NAME);
			} else {
				getNavigator().navigateTo(LoginView.VIEW_NAME);
			}
		} else {
			getNavigator().navigateTo(LoginView.VIEW_NAME);
		}
	}
	
	private void initialize()
	{
		mRepositoryContainer.setResRepo(resRepo);
		mRepositoryContainer.setReUserRep(reUserRep);
		mRepositoryContainer.setReResourceitemRep(reResourceitemRep);
		mRepositoryContainer.setReTurkishtrRep(reTurkishtrRep);
		mRepositoryContainer.setReEnglishusRep(reEnglishusRep);
		mRepositoryContainer.setReGermandeRep(reGermandeRep);
		mRepositoryContainer.setRePersianirRep(rePersianirRep);
		mRepositoryContainer.setReAzerbaijaniazRep(reAzerbaijaniazRep);
		mRepositoryContainer.setReRussianruRep(reRussianruRep);
		mRepositoryContainer.setReRomanianroRep(reRomanianroRep);
		mRepositoryContainer.setReGeorgiangeRep(reGeorgiangeRep);
		mRepositoryContainer.setReArabicjoRep(reArabicjoRep);
		mRepositoryContainer.setReFrenchfrRep(reFrenchfrRep);
		mRepositoryContainer.setReAlbaniankvRep(reAlbaniankvRep);
		mRepositoryContainer.setReTurkmentmRep(reTurkmentmRep);
		mRepositoryContainer.setReArabicegRep(reArabicegRep);
		mRepositoryContainer.setReArabicsaRep(reArabicsaRep);
		
		mRepositoryContainer.setReResourceitemShortRep(reResourceitemShortRep);
		
		mRepositoryContainer.setReMessageRep(reMessageRep);
		mRepositoryContainer.setReHelpDocsRep(reHelpDocsRep);
		mRepositoryContainer.setMessageProvider(ApplicationContextLocator.messageProvider());
		mRepositoryContainer.setReProjectVerisonRep(reProjectVerisonRep);
	}
	
	public static RepositoryContainer getMrepositorycontainer() {
		return mRepositoryContainer;
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
