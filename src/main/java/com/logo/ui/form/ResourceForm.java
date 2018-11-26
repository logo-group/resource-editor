package com.logo.ui.form;

import java.util.logging.Level;
import java.util.logging.Logger;


import com.github.appreciated.material.MaterialTheme;
import com.logo.LogoresMainUI;
import com.logo.data.entity.ReResource;
import com.logo.data.entity.ReUser;
import com.logo.data.repository.ReResourceRep;
import com.logo.ui.components.ButtonGenerator;
import com.logo.ui.components.SpellChecComboBox;
import com.logo.ui.components.SpellChecTextField;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.converter.StrToIntegerConverter;
import com.logo.util.enums.OwnerProduct;
import com.logo.util.enums.ResourceCase;
import com.logo.util.enums.ResourceGroup;
import com.logo.util.enums.ResourceState;
import com.logo.util.enums.ResourceType;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;


public class ResourceForm extends Panel {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(NewResourceForm.class.getName());

	private ReResource resource;
	private final Binder<ReResource> binder = new Binder<>(ReResource.class);
	private final SpellChecTextField resourcenr = new SpellChecTextField(LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));
	private final SpellChecTextField description = new SpellChecTextField(LangHelper.getLocalizableMessage(LogoResConstants.DESCSTR));
	private final Button save = new ButtonGenerator(LogoResConstants.SAVESTR);
	private final Button cancel = new ButtonGenerator(LogoResConstants.CANCELSTR);
	private final Button edit = new ButtonGenerator(LogoResConstants.EDITSTR);
	
	private final SpellChecComboBox<ResourceGroup> resourceGroupCombo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(LogoResConstants.RESGRPSTR));
	private final SpellChecComboBox<ResourceType> resourceTypeCombo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(LogoResConstants.RESTYPESTR));
	private final SpellChecComboBox<ResourceCase> resourceCaseCombo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(LogoResConstants.RESCASESTR));
	private final SpellChecComboBox<OwnerProduct> ownerProductCombo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(LogoResConstants.OWNERPRODUCT));
	private final HorizontalLayout buttonLayout = new HorizontalLayout(save, cancel, edit);

	private final transient ReResourceRep resRepo;
	private final transient ReUser reUser;
	
	public ResourceForm(ReResource resource) {
		this.resRepo = LogoresMainUI.getMrepositorycontainer().getResRepo();
		this.resource = resource;
		binder.setBean(resource);
		
		this.reUser = (ReUser) VaadinSession.getCurrent().getAttribute("user");
		resource.setActive(ResourceState.ACTIVE);
		setWidth("100%");
		setHeight("100%");
		
		if(resource.getCreatedby() == 0)
			resource.setCreatedby(reUser.getId());
		
		resource.setModifiedby(reUser.getId());
		
		addStyleName(MaterialTheme.CARD_HOVERABLE);
		
		GridLayout gridLayout = new GridLayout(2,7);
		gridLayout.addStyleName(LogoResConstants.STYLE_GRID);
		gridLayout.setWidth("100%");
		gridLayout.setSpacing(true);
		gridLayout.setMargin(true);
		
		resourcenr.setWidth("100%");
		resourcenr.setHeight("30px");
		resourcenr.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		description.setWidth("250%");
		description.setHeight("30px");
		description.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		
		save.setWidth("80px");
		cancel.setWidth("80px");
		edit.setWidth("80px");
		edit.setVisible(false);
		cancel.setVisible(false);
		
		binder.forField(resourcenr).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.RESNRNOTEMTYSTR))
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				//.withValidator(number -> number <= 0, "Person must be born in the 20th century")
				.bind(ReResource::getResourcenr, ReResource::setResourcenr);

		binder.forField(description).bind(ReResource::getDescription, ReResource::setDescription);

		resourceGroupCombo.setWidth("100%");
		resourceGroupCombo.setHeight("30px");
		resourceTypeCombo.setWidth("100%");
		resourceTypeCombo.setHeight("30px");
		resourceCaseCombo.setWidth("100%");
		resourceCaseCombo.setHeight("30px");
		ownerProductCombo.setWidth("100%");
		ownerProductCombo.setHeight("30px");
		
		resourceGroupCombo.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		resourceTypeCombo.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		resourceCaseCombo.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		ownerProductCombo.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		

		resourceGroupCombo.setItems(ResourceGroup.UN, ResourceGroup.HR, ResourceGroup.UNRP, ResourceGroup.HRRP,
				ResourceGroup.SS, ResourceGroup.HELP, ResourceGroup.MISC);
		resourceTypeCombo.setItems(ResourceType.LOCALIZABLE, ResourceType.NONLOCALIZABLE);
		resourceCaseCombo.setItems(ResourceCase.NORESTRICTION, ResourceCase.LOWERCASE, ResourceCase.UPPERCASE,
				ResourceCase.TITLECASE, ResourceCase.SENTENCECASE);
		ownerProductCombo.setItems(OwnerProduct.INFRASTRUCTURE, OwnerProduct.APPLICATION);

		resourceGroupCombo.setEmptySelectionAllowed(false);
		resourceTypeCombo.setEmptySelectionAllowed(false);
		resourceCaseCombo.setEmptySelectionAllowed(false);
		ownerProductCombo.setEmptySelectionAllowed(false);

		binder.bind(resourceGroupCombo, ReResource::getResourcegroup, ReResource::setResourcegroup);
		binder.bind(resourceTypeCombo, ReResource::getResourcetype, ReResource::setResourcetype);
		binder.bind(resourceCaseCombo, ReResource::getResourcecase, ReResource::setResourcecase);
		binder.bind(ownerProductCombo, ReResource::getOwnerproduct, ReResource::setOwnerproduct);

		resourceGroupCombo.setSelectedItem(ResourceGroup.UN);
		resourceTypeCombo.setSelectedItem(ResourceType.LOCALIZABLE);
		resourceCaseCombo.setSelectedItem(ResourceCase.NORESTRICTION);
		ownerProductCombo.setSelectedItem(OwnerProduct.INFRASTRUCTURE);

		save.setClickShortcut(KeyCode.ENTER);

		resourcenr.setValue(generateResourceNr().toString());
		
		FormLayout col01 = new FormLayout();
		col01.setSizeFull();
		col01.setSpacing(true);
		col01.setMargin(true);
		col01.setWidth("100%");
		col01.setHeight("100%");
		col01.addComponent(resourcenr);
		col01.addComponent(description);
		col01.addComponent(resourceGroupCombo);
		col01.addComponent(resourceCaseCombo);
		col01.addComponent(resourceTypeCombo);
		col01.addComponent(ownerProductCombo);
		
		gridLayout.addComponent(col01, 0, 1, 0, 2);
		
		/**
		Label hSep0 = createSeperator();
		gridLayout.addComponent(hSep0, 0, 4, 1, 4);
		**/
		
		gridLayout.addComponent(buttonLayout, 1, 5);
		Label hSep1 = createSeperator();
		gridLayout.addComponent(hSep1, 0, 6, 1, 6);

		gridLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);

		setContent(gridLayout);
		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public void error(com.vaadin.server.ErrorEvent event) {
				StringBuilder cause = new StringBuilder("<b>The click failed because:</b><br/>");
				for (Throwable t = event.getThrowable(); t != null; t = t.getCause())
					if (t.getCause() == null) // We're at final cause
						cause.append(t.getClass().getName()).append("<br/>");

				logger.log(Level.SEVERE, event.getThrowable().getMessage(), event.getThrowable());
				doDefault(event);
			}
		});
	}

	Label createSeperator() {
		Label hSep = new Label();
		hSep.addStyleName("horizontal-separator2");
		hSep.setHeight("20px");
		hSep.setWidth("100%");
		return hSep;

	}
	
	private Integer generateResourceNr() {
		Integer resNr = 0;
		Integer max = resRepo.getMaxResourceNr();
		if (max == null)
			return 0;
		else
			resNr = ++max;
		return resNr;
	}
	
	public ReResource getResource() {
		return resource;
	}
	
	public Button getSaveButton() {
		return save;
	}

	public Button getEditButton() {
		return edit;
	}

	public Button getCancelButton() {
		return cancel;
	}

	public TextField getResourcenrField() {
		return resourcenr;
	}
	 
	public TextField getDescriptionField() {
		return description;
	}

	public SpellChecComboBox<ResourceGroup> getResourceGroupCombo() {
		return resourceGroupCombo;
	}
	
	public SpellChecComboBox<ResourceCase> getResourceCaseCombo() {
		return resourceCaseCombo;
	}

	public SpellChecComboBox<ResourceType> getResourceTypeCombo() {
		return resourceTypeCombo;
	}

	public SpellChecComboBox<OwnerProduct> getOwnerProductCombo() {
		return ownerProductCombo;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}	
	
	public void persist()
	{
		resRepo.save(resource);
	}

}
