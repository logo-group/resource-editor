package com.logo;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

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
		VaadinSession.getCurrent().setAttribute("appLocale", locale);
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
		router(ResourceViewNew.VIEW_NAME);
		new Responsive(this);
	}

	private void router(String route) {
		if (getSession().getAttribute("user") != null) {
			getNavigator().addView(LoginView.VIEW_NAME, LoginView.class);
			getNavigator().addView(ResourceViewNew.VIEW_NAME, ResourceViewNew.class);
			getNavigator().addView(UserView.VIEW_NAME, UserView.class);
			getNavigator().addView(ReMessageView.VIEW_NAME, ReMessageView.class);
			getNavigator().addView(ReHelpDocsView.VIEW_NAME, ReHelpDocsView.class);
			getNavigator().addView(TransferView.VIEW_NAME, TransferView.class);
			if (route.equals(ResourceViewNew.VIEW_NAME)) {
				getNavigator().navigateTo(ResourceViewNew.VIEW_NAME);
				loadSessionViews();
			} else {
				getNavigator().navigateTo(LoginView.VIEW_NAME);
			}
		} else {
			getNavigator().navigateTo(LoginView.VIEW_NAME);
		}
	}

	private void initialize() {
		mRepositoryContainer.setMessageProvider(ApplicationContextLocator.messageProvider());
	}

	private void loadSessionViews() {
		getNavigator().addView(ResourceViewNew.VIEW_NAME, ResourceViewNew.class);
		ResourceViewNew view = (ResourceViewNew) getNavigator().getCurrentView();
		if (getSession().getAttribute("usersTab") != null) {
			view.doUserButtonAction();
		} else if (getSession().getAttribute("reportsTab") != null) {
			view.doReportsButtonAction();
		} else if (getSession().getAttribute("homeTab") != null) {
			view.doHomeButtonAction();
		} else if (getSession().getAttribute("allResourcesTab") != null) {
			view.doAllResourcesButtonAction();
		} else if (getSession().getAttribute("transferTab") != null) {
			view.doTransferButtonAction();
		} else if (getSession().getAttribute("toolsTab") != null) {
			view.doToolsButtonAction();
		} else if (getSession().getAttribute("documentsTab") != null) {
			view.doDocumentsButtonAction();
		} else if (getSession().getAttribute("filtersTab") != null) {
			view.doFilterButtonAction();
		} else if (getSession().getAttribute("pageForAllTab") != null) {
			view.createResoucePageForAll();
		} else if (getSession().getAttribute("messagesTab") != null) {
			view.createMessageLayout();
		} else if (getSession().getAttribute("helpDocsTab") != null) {
			view.createHelpDocsLayout();
		}
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
