package com.logo.ui.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Panel;

public class PanelCaptionBarToggler<T extends Panel> {
	private T panel;
	private Resource cacheIcon;
	private static final int CAPTION_BAR_HEIGHT = 60;

	public PanelCaptionBarToggler(T panel) {
		this.panel = panel;
		panel.addClickListener((clickEvent -> {
			if (clickEvent.getRelativeY() < CAPTION_BAR_HEIGHT) {
				toggleContent();
			}
		}));
	}

	private void toggleContent() {
		panel.getContent().setVisible(!panel.getContent().isVisible());
		if (!panel.getContent().isVisible()) {
			cacheIcon = panel.getIcon();
			panel.setIcon(VaadinIcons.ANGLE_DOWN);
			String panelCaption = "<p style="
					+ "background-color:white;border-style:solid;border-color:green;border-width:2px;padding:4px;font-style:bold;text-align:center;"
					+ "color:green; <span style='cursor:pointer;'>New Resource Form</span> </p>";
			panel.setCaption(panelCaption);
			panel.setCaptionAsHtml(true);
			panel.setHeight("100px");
		} else {
			panel.setIcon(cacheIcon);
			panel.setHeight(-1, Unit.PIXELS);
		}
	}

}
