package com.logo.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedHttpSession;

public class UISessionState {

	private static Logger logger = Logger.getLogger(UISessionState.class.getName());

	private VaadinSession vaadinSession;

	private boolean replaceSession = true;

	public boolean staleSessionCheck(VaadinSession inSession, VaadinRequest request) {
		if (!vaadinSession.equals(inSession)) {
			logger.log(Level.INFO, "Stale session detected, invalidating HTTPSession and reloading!");
			HttpSession httpSession = ((WrappedHttpSession) request.getWrappedSession()).getHttpSession();
			httpSession.invalidate();
			Page.getCurrent().setLocation("/");
			replaceSession = true;
			return true;
		}
		return false;
	}

	public void setVaadinSession(VaadinSession vaadinSession) {
		this.vaadinSession = vaadinSession;
	}

	public boolean isReplaceSession() {
		return replaceSession;
	}

	public void setReplaceSession(boolean replaceSession) {
		this.replaceSession = replaceSession;
	}
}
