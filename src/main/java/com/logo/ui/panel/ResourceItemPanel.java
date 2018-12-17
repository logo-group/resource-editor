package com.logo.ui.panel;

import com.logo.data.entity.ReResource;
import com.logo.data.entity.ReResourceitem;
import com.logo.data.entity.ReUser;
import com.logo.data.repository.ReResourceRep;
import com.logo.data.repository.ReResourceitemRep;
import com.logo.data.repository.ReUserRep;
import com.logo.ui.components.RowLayout;
import com.logo.util.enums.OwnerProduct;
import com.logo.util.enums.ResourceCase;
import com.logo.util.enums.ResourceGroup;
import com.logo.util.enums.ResourceType;
import com.logo.util.enums.UserLayoutType;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class ResourceItemPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private final transient ReUser reUser;
	private ReResourceRep reResourceRep;
	private ReResourceitemRep reResourceitemRep;
	private ReUserRep userRepo;
	VerticalLayout panelContent = new VerticalLayout();

	public ResourceItemPanel(ReResourceRep reResourceRep, ReResourceitemRep reResourceitemRep, ReUserRep userRepo) {
		this.reUser = (ReUser) VaadinSession.getCurrent().getAttribute("user");
		this.reResourceRep = reResourceRep;
		this.reResourceitemRep = reResourceitemRep;
		this.userRepo = userRepo;
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
	}

	public void addItem(ReResource reResource, ReResourceitem reResourceitem) {
		boolean isVertical = reUser.getDefaultorientation() == UserLayoutType.V;
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setWidth("100%");
		vLayout.addComponent(new RowLayout(reResource, reResourceitem, true, isVertical, null, true, reUser,
				reResourceRep, reResourceitemRep, userRepo));
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
