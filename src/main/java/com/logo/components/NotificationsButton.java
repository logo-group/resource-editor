package com.logo.components;

import com.github.appreciated.material.MaterialTheme;
import com.logo.util.LogoResConstants;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;

public class NotificationsButton extends Button {
	private static final long serialVersionUID = 1L;
	private static final String STYLE_UNREAD = "unread";
	public static final String ID = "valo-menu-badge";
	private int unreadcount;
	
	public NotificationsButton() {
		setIcon(VaadinIcons.BELL_O);
		addStyleName("notifications");
	}

	public void setUnreadCount(final int count) {
		setCaption(String.valueOf(count));
		setUnreadCounter(count);
		String description = "Notifications";
		if (count > 0) {
			addStyleName(STYLE_UNREAD);
			addStyleName(MaterialTheme.BUTTON_BORDERLESS+" "+MaterialTheme.BUTTON_ROUND + " " + LogoResConstants.STYLE_CUSTOM_WHITE);
			description += " (" + count + " unread)";
		} else {
			removeStyleName(STYLE_UNREAD);
			addStyleName(MaterialTheme.BUTTON_BORDERLESS+" "+MaterialTheme.BUTTON_ROUND + " " + LogoResConstants.STYLE_CUSTOM_WHITE);
			description += "";
			setCaption("");
		}
		setDescription(description);
	}

	public int getUnreadCounter() {
		return unreadcount;
	}
	
	public void setUnreadCounter(int count) {
		unreadcount = count;
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
