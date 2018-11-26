package com.logo.ui.form;

import com.logo.data.entity.ReResource;
import com.logo.ui.panel.ResourceItemPanel;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@UIScope
public class NewResourceForm extends HorizontalSplitPanel {

	private static final long serialVersionUID = 1L;

	public NewResourceForm() {
		setSizeFull();
		setWidth("100%");
		setHeight("100%");

		VerticalLayout leftLayout = new VerticalLayout();
		leftLayout.setWidth("100%");
		leftLayout.setHeight("100%");
		leftLayout.setSpacing(true);
		leftLayout.setMargin(true);

		VerticalLayout rightLayout = new VerticalLayout();
		rightLayout.setWidth("100%");
		rightLayout.setHeight("100%");
		rightLayout.setSpacing(true);
		rightLayout.setMargin(true);

		HorizontalLayout resHedLayout = new HorizontalLayout();
		resHedLayout.setWidth("100%");
		resHedLayout.setHeight("70px");
		resHedLayout.addStyleName("card-hoverable-material-darkBlue-primary-color");

		HorizontalLayout resListLayout = new HorizontalLayout();
		resListLayout.setWidth("100%");
		resListLayout.setHeight("70px");
		resListLayout.addStyleName("card-hoverable-material-light-primary-color-pointer");

		HorizontalLayout resItemHedLayout = new HorizontalLayout();
		resItemHedLayout.setWidth("100%");
		resItemHedLayout.setHeight("70px");
		resItemHedLayout.addStyleName("card-hoverable-material-default-primary-color");
		resItemHedLayout.setVisible(false);

		Label resHedlabel = new Label("");
		resHedlabel.setCaption(VaadinIcons.PLUS_CIRCLE_O.getHtml() + " New Resource Form");
		resHedlabel.setCaptionAsHtml(true);

		Label resListlabel = new Label();
		resListlabel.setCaption(VaadinIcons.PLUS_CIRCLE_O.getHtml());
		resListlabel.setCaptionAsHtml(true);
		Label resListHeader = new Label("Resource Item List");

		resHedLayout.addComponents(resHedlabel);
		resHedLayout.setComponentAlignment(resHedlabel, Alignment.BOTTOM_LEFT);

		resListLayout.addComponent(resListlabel);
		resListLayout.addComponent(resListHeader);
		resListLayout.setComponentAlignment(resListlabel, Alignment.BOTTOM_LEFT);
		resListLayout.setComponentAlignment(resListlabel, Alignment.MIDDLE_LEFT);

		leftLayout.addComponents(resHedLayout);
		rightLayout.addComponents(resListLayout);

		ResourceForm resourceForm = new ResourceForm(new ReResource());
		leftLayout.addComponentsAndExpand(resourceForm);
		resHedLayout.addLayoutClickListener(e -> {
			if (resourceForm.isVisible()) {
				resourceForm.setVisible(false);
				resHedlabel.setCaption(VaadinIcons.PLUS_CIRCLE_O.getHtml());
				resHedlabel.setCaptionAsHtml(true);
			} else {
				resourceForm.setVisible(true);
				resHedlabel.setCaption(VaadinIcons.MINUS_CIRCLE_O.getHtml());
				resHedlabel.setCaptionAsHtml(true);
			}
		});

		leftLayout.addComponents(resItemHedLayout);
		ResourceItemForm resourceItemForm = new ResourceItemForm(resourceForm.getResource());
		resourceItemForm.setVisible(false);
		leftLayout.addComponentsAndExpand(resourceItemForm);

		ResourceItemPanel resourceItemPanel = new ResourceItemPanel();
		rightLayout.addComponentsAndExpand(resourceItemPanel);
		resourceItemPanel.setVisible(true);

		resListLayout.addLayoutClickListener(e -> resourceItemPanel.setVisible(!resourceItemPanel.isVisible()));

		addComponents(leftLayout);
		addComponents(rightLayout);

		resourceForm.getSaveButton()
				.addClickListener(e -> saveResource(resourceForm, resourceItemForm, resHedLayout, resItemHedLayout));
		resourceForm.getEditButton().addClickListener(e -> editResource(resourceForm));
		resourceForm.getCancelButton().addClickListener(e -> cancelResource(resourceForm, resourceItemForm));

		resourceItemForm.getAddButton().addClickListener(e -> addResourceItem(resourceForm, resourceItemForm));
		resourceItemForm.getSaveButton().addClickListener(e -> saveResourceItem(resourceItemForm, resourceItemPanel));
		resourceItemForm.getSaveAndNewButton()
				.addClickListener(e -> saveAndNewResourceItem(resourceForm, resourceItemForm, resourceItemPanel));
		resourceItemForm.getCancelButton()
				.addClickListener(e -> cancelResourceItem(resourceItemForm, resourceItemPanel));
	}

	private void editResource(ResourceForm resourceForm) {
		resourceForm.getEditButton().setVisible(false);
		resourceForm.getSaveButton().setVisible(true);
		resourceForm.getCancelButton().setVisible(true);
		resourceForm.getResourcenrField().setEnabled(true);
		resourceForm.getDescriptionField().setEnabled(true);
		resourceForm.getResourceGroupCombo().setEnabled(true);
		resourceForm.getResourceCaseCombo().setEnabled(true);
		resourceForm.getResourceTypeCombo().setEnabled(true);
		resourceForm.getOwnerProductCombo().setEnabled(true);
	}

	private void saveResource(ResourceForm resourceForm, ResourceItemForm resourceItemForm,
			HorizontalLayout resHedLayout, HorizontalLayout resItemHedLayout) {
		resourceForm.persist();
		if (resourceForm.getResource().isPersisted()) {
			resourceItemForm.setVisible(true);
			resItemHedLayout.setVisible(true);
			resourceItemForm.getAddButton().setEnabled(true);
			resourceForm.getEditButton().setVisible(true);

			resourceItemForm.getOrdernr().setEnabled(false);
			resourceItemForm.getPrefix().setEnabled(false);
			resourceItemForm.getTagnr().setEnabled(false);
			resourceItemForm.getInfo().setEnabled(false);
			resourceItemForm.getLevelnr().setEnabled(false);
			resourceItemForm.getResourceItemCaseCombo().setEnabled(false);
			resourceItemForm.getOwnerProductCombo().setEnabled(false);
			resourceItemForm.getDesc().setEnabled(false);
			resourceForm.setVisible(false);
			Label resNr = new Label("resnr1");
			if (resHedLayout.getComponentCount() == 1) {
				resHedLayout.addComponent(resNr);
				resHedLayout.setComponentAlignment(resNr, Alignment.MIDDLE_LEFT);
			}
		} else
			resourceForm.getEditButton().setEnabled(false);
		resourceForm.getSaveButton().setVisible(false);
		resourceForm.getCancelButton().setVisible(false);

		resourceForm.getResourcenrField().setEnabled(false);
		resourceForm.getDescriptionField().setEnabled(false);
		resourceForm.getResourceGroupCombo().setEnabled(false);
		resourceForm.getResourceCaseCombo().setEnabled(false);
		resourceForm.getResourceTypeCombo().setEnabled(false);
		resourceForm.getOwnerProductCombo().setEnabled(false);
	}

	private void cancelResource(ResourceForm resourceForm, ResourceItemForm resourceItemForm) {
		if (resourceForm.getResource().isPersisted()) {
			resourceItemForm.setVisible(true);
			resourceForm.getEditButton().setVisible(true);
		} else
			resourceForm.getEditButton().setVisible(false);
		resourceForm.getSaveButton().setVisible(false);
		resourceForm.getCancelButton().setVisible(false);

		resourceForm.getResourcenrField().setEnabled(false);
		resourceForm.getDescriptionField().setEnabled(false);
		resourceForm.getResourceGroupCombo().setEnabled(false);
		resourceForm.getResourceCaseCombo().setEnabled(false);
		resourceForm.getResourceTypeCombo().setEnabled(false);
		resourceForm.getOwnerProductCombo().setEnabled(false);
	}

	private void addResourceItem(ResourceForm resourceForm, ResourceItemForm resourceItemForm) {
		resourceItemForm.getAddButton().setVisible(false);
		resourceItemForm.getSaveButton().setVisible(true);
		resourceItemForm.getSaveAndNewButton().setVisible(true);
		resourceItemForm.getCancelButton().setVisible(true);

		resourceItemForm.getResourceItem().setId(null);
		resourceItemForm.generateTagNr(resourceForm.getResource().getId());
		resourceItemForm.generateOrderNr(resourceForm.getResource().getId());

		prepareResourceItem(resourceForm, resourceItemForm);

		resourceItemForm.getOrdernr().setEnabled(true);
		resourceItemForm.getPrefix().setEnabled(true);
		resourceItemForm.getTagnr().setEnabled(true);
		resourceItemForm.getInfo().setEnabled(true);
		resourceItemForm.getLevelnr().setEnabled(true);
		resourceItemForm.getResourceItemCaseCombo().setEnabled(true);
		resourceItemForm.getOwnerProductCombo().setEnabled(true);
		resourceItemForm.getDesc().setEnabled(true);
	}

	private void saveResourceItem(ResourceItemForm resourceItemForm, ResourceItemPanel resourceItemPanel) {
		resourceItemForm.persist();
		resourceItemForm.getAddButton().setVisible(true);
		resourceItemForm.getSaveButton().setVisible(false);
		resourceItemForm.getSaveAndNewButton().setVisible(false);
		resourceItemForm.getCancelButton().setVisible(false);

		resourceItemForm.getOrdernr().setEnabled(false);
		resourceItemForm.getPrefix().setEnabled(false);
		resourceItemForm.getTagnr().setEnabled(false);
		resourceItemForm.getInfo().setEnabled(false);
		resourceItemForm.getLevelnr().setEnabled(false);
		resourceItemForm.getResourceItemCaseCombo().setEnabled(false);
		resourceItemForm.getOwnerProductCombo().setEnabled(false);
		resourceItemForm.getDesc().setEnabled(false);

		resourceItemPanel.setVisible(true);
		resourceItemPanel.addItem(resourceItemForm.getResource(), resourceItemForm.getResourceItem());
	}

	private void cancelResourceItem(ResourceItemForm resourceItemForm, ResourceItemPanel resourceItemPanel) {

		resourceItemForm.getAddButton().setVisible(true);
		resourceItemForm.getSaveButton().setVisible(false);
		resourceItemForm.getSaveAndNewButton().setVisible(false);
		resourceItemForm.getCancelButton().setVisible(false);

		resourceItemForm.getOrdernr().setEnabled(false);
		resourceItemForm.getPrefix().setEnabled(false);
		resourceItemForm.getTagnr().setEnabled(false);
		resourceItemForm.getInfo().setEnabled(false);
		resourceItemForm.getLevelnr().setEnabled(false);
		resourceItemForm.getResourceItemCaseCombo().setEnabled(false);
		resourceItemForm.getOwnerProductCombo().setEnabled(false);
		resourceItemForm.getDesc().setEnabled(false);

		resourceItemPanel.setVisible(true);
	}

	private void saveAndNewResourceItem(ResourceForm resourceForm, ResourceItemForm resourceItemForm,
			ResourceItemPanel resourceItemPanel) {
		resourceItemForm.persist();
		addResourceItem(resourceForm, resourceItemForm);

		resourceItemForm.getAddButton().setVisible(false);
		resourceItemForm.getSaveButton().setVisible(true);
		resourceItemForm.getSaveAndNewButton().setVisible(true);
		resourceItemForm.getCancelButton().setVisible(true);

		resourceItemForm.getOrdernr().setEnabled(true);
		resourceItemForm.getPrefix().setEnabled(true);
		resourceItemForm.getTagnr().setEnabled(true);
		resourceItemForm.getInfo().setEnabled(true);
		resourceItemForm.getLevelnr().setEnabled(true);
		resourceItemForm.getResourceItemCaseCombo().setEnabled(true);
		resourceItemForm.getOwnerProductCombo().setEnabled(true);
		resourceItemForm.getDesc().setEnabled(true);
		resourceItemForm.getDesc().setValue("");

		resourceItemPanel.setVisible(true);
		resourceItemPanel.addItem(resourceItemForm.getResource(), resourceItemForm.getResourceItem());
	}

	private void prepareResourceItem(ResourceForm resourceForm, ResourceItemForm resourceItemForm) {

		resourceItemForm.getResourceItem().setResourceref(resourceForm.getResource().getId());
	}
}
