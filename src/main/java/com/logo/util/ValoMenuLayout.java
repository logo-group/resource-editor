package com.logo.util;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;


public class ValoMenuLayout extends HorizontalLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6837189394600297087L;

	HorizontalLayout contentArea = new HorizontalLayout();

    CssLayout menuArea = new CssLayout();

    public ValoMenuLayout() {
        setSizeFull();
        setMargin(true);
        
        menuArea.setPrimaryStyleName(ValoTheme.MENU_ROOT);
        menuArea.setResponsive(true);
        
        contentArea.setPrimaryStyleName("valo-content");
        contentArea.addStyleName("v-scrollable");
        contentArea.setSizeFull();
        
        addComponents(menuArea, contentArea);
        setExpandRatio(contentArea, 1);
    }

    public HorizontalLayout getContentContainer() {
        return contentArea;
    }

    public void addMenu(Component menu) {
        menu.addStyleName(ValoTheme.MENU_PART);
        menuArea.addComponent(menu);
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
