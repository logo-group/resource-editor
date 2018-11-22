package com.logo.form;

import com.logo.components.RowLayout;
import com.logo.data.ReResource;
import com.logo.data.ReResourceitem;
import com.logo.data.ReUser;
import com.logo.util.OwnerProduct;
import com.logo.util.ResourceCase;
import com.logo.util.ResourceGroup;
import com.logo.util.ResourceType;
import com.logo.util.UserLayoutType;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class ResourceItemPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private final transient ReUser reUser;
	VerticalLayout panelContent = new VerticalLayout();
	
	public ResourceItemPanel() {
		this.reUser = (ReUser) VaadinSession.getCurrent().getAttribute("user");
		ReResource resource = new ReResource();
		resource.setResourcegroup(ResourceGroup.UN);
		resource.setResourcetype(ResourceType.LOCALIZABLE);
		resource.setResourcecase(ResourceCase.NORESTRICTION);
		resource.setOwnerproduct(OwnerProduct.INFRASTRUCTURE);

		setWidth("100%");
		setHeight("100%");
		
		panelContent.setWidth("100%");

		setContent(panelContent);
		
		ReResourceitem reResourceitem = new ReResourceitem();
		reResourceitem.setOrdernr(1);
		reResourceitem.setTagnr(1);

		/**
		String panelCaption = "<p style="
				+ "background-color:white;border-style:solid;border-color:green;border-width:2px;padding:4px;font-style:bold;text-align:center;"
				+ "color:green" + ">Resource Item List</p>";
		setCaption(panelCaption);
		setCaptionAsHtml(true);
		**/
	}

	public void addItem(ReResource reResource, ReResourceitem reResourceitem) {
		boolean isVertical = reUser.getDefaultorientation() == UserLayoutType.V;
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setWidth("100%");
		vLayout.addComponent(new RowLayout(reResource, reResourceitem, true, isVertical, null, true, reUser));
		panelContent.addComponent(vLayout);
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
