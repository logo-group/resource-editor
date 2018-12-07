package com.logo.ui.window;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.logo.LogoresMainUI;
import com.logo.data.entity.ReResource;
import com.logo.data.entity.ReResourceitem;
import com.logo.data.repository.ReResourceitemRep;
import com.logo.ui.components.ButtonGenerator;
import com.logo.ui.components.SpellChecComboBox;
import com.logo.ui.components.SpellChecTextField;
import com.logo.ui.form.NewResourceForm;
import com.logo.ui.view.ResourceViewNew;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.converter.StrToIntegerConverter;
import com.logo.util.enums.OwnerProduct;
import com.logo.util.enums.ResourceCase;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ResourceItemWindow extends Window {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(NewResourceForm.class.getName());

	private ReResource resource = new ReResource();
	private ReResourceitem resourceItem = new ReResourceitem();
	private final Binder<ReResourceitem> binder = new Binder<>(ReResourceitem.class);
	private final SpellChecTextField ordernr = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.ORDERNRSTR));
	private final SpellChecTextField tagnr = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.TAGNRSTR));
	private final SpellChecTextField levelnr = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.LEVELNRSTR));
	private final SpellChecTextField prefix = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.PREFIXSTR));
	private final SpellChecTextField info = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.INFOSTR));

	private final Button save = new ButtonGenerator(LogoResConstants.SAVESTR);
	private final Button cancel = new ButtonGenerator(LogoResConstants.CANCELSTR);
	private final Button saveAndNew = new ButtonGenerator(LogoResConstants.SAVEANDNEWSTR);

	private final SpellChecComboBox<ResourceCase> resourceItemCaseCombo = new SpellChecComboBox<>(
			LangHelper.getLocalizableMessage(LogoResConstants.RESCASESTR));
	private final SpellChecComboBox<OwnerProduct> ownerProductCombo = new SpellChecComboBox<>(
			LangHelper.getLocalizableMessage(LogoResConstants.OWNERPRODUCT));
	private final HorizontalLayout buttonLayout = new HorizontalLayout(saveAndNew, save, cancel);

	private final transient ReResourceitemRep reResourceitemRep;

	private TabSheet tabsheet = new TabSheet();
	private final Label formName = new Label();

	public TabSheet getTabsheet() {
		return tabsheet;
	}

	public ResourceItemWindow(ReResource resource, ResourceViewNew resView) {
		this.resource = resource;
		this.binder.setBean(resourceItem);
		this.reResourceitemRep = LogoresMainUI.getMrepositorycontainer().getReResourceitemRep();

		initialize();

		save.addClickListener(event -> {
			persist();
			close();
			String filter = resource.getResourcegroup().name() + "->" + Integer.toString(resource.getResourcenr());
			resView.createResoucePage(filter, true);
		});
		saveAndNew.addClickListener(event -> {
			persist();
			String filter = resource.getResourcegroup().name() + "->" + Integer.toString(resource.getResourcenr());
			resView.createResoucePage(filter, true);
			initialize();
		});
	}

	public void initialize() {
		this.resourceItem = new ReResourceitem();
		this.binder.setBean(resourceItem);
		tabsheet = new TabSheet();
		tabsheet.setSizeFull();
		tabsheet.setWidth("100%");
		tabsheet.setHeight("100%");

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);

		center();
		setClosable(false);
		setModal(true);
		setWidth(50.0f, Unit.PERCENTAGE);
		setHeight(70.0f, Unit.PERCENTAGE);
		setResizable(false);
		setResponsive(true);

		GridLayout gridLayout = new GridLayout(2, 7);
		gridLayout.setSizeFull();
		gridLayout.setSpacing(true);
		gridLayout.setMargin(true);
		gridLayout.setWidth("100%");
		gridLayout.setHeight("100%");

		save.setWidth("80px");
		cancel.setWidth("80px");
		save.setClickShortcut(KeyCode.ENTER);
		formName.setValue(resource.getResourcegroup().name() + "-" + resource.getResourcenr());

		HorizontalLayout labelLayout = new HorizontalLayout();
		labelLayout.setWidth("100%");
		labelLayout.addStyleName(LogoResConstants.STYLE_CARD_HOVER_DEFULT_PRIMARY);
		labelLayout.setDescription(LangHelper.getLocalizableMessage(LogoResConstants.CLOSELSTR));

		labelLayout.addComponent(formName);
		labelLayout.setComponentAlignment(formName, Alignment.TOP_LEFT);

		labelLayout.addLayoutClickListener(e -> close());

		mainLayout.addComponent(labelLayout);

		generateOrderNr(resource.getId());
		generateTagNr(resource.getId());

		resourceItem.setOrdernr(Integer.valueOf(ordernr.getValue()));
		resourceItem.setTagnr(Integer.valueOf(tagnr.getValue()));
		resourceItem.setLevelnr(0);
		resourceItem.setPrefixstr("");
		resourceItem.setInfo("");

		ordernr.setWidth("100%");

		ordernr.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		tagnr.setWidth("100%");
		tagnr.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		levelnr.setWidth("100%");
		levelnr.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		prefix.setWidth("100%");
		prefix.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		info.setWidth("100%");
		info.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		binder.forField(ordernr).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.ORDERNRNOTEMTYSTR))
				.withConverter(new StrToIntegerConverter(LogoResConstants.MUSTNUMBER))
				// .withValidator(number -> number <= 1, "Person must be born in
				// the 20th century")
				.bind(ReResourceitem::getOrdernr, ReResourceitem::setOrdernr);

		binder.forField(tagnr).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.TAGNRNOTEMTYSTR))
				.withConverter(new StrToIntegerConverter(LogoResConstants.MUSTNUMBER))
				// .withValidator(number -> number <= 1, "Person must be born in
				// the 20th century")
				.bind(ReResourceitem::getTagnr, ReResourceitem::setTagnr);

		binder.forField(levelnr).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.LEVELNRNOTEMTYSTR))
				.withConverter(new StrToIntegerConverter(LogoResConstants.MUSTNUMBER))
				// .withValidator(number -> number <= 1, "Person must be born in
				// the 20th century")
				.bind(ReResourceitem::getLevelnr, ReResourceitem::setLevelnr);

		binder.forField(prefix).bind(ReResourceitem::getPrefixstr, ReResourceitem::setPrefixstr);

		binder.forField(info).bind(ReResourceitem::getInfo, ReResourceitem::setInfo);

		resourceItemCaseCombo.setWidth("100%");
		ownerProductCombo.setWidth("100%");

		resourceItemCaseCombo.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		ownerProductCombo.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		resourceItemCaseCombo.setEmptySelectionAllowed(false);
		ownerProductCombo.setEmptySelectionAllowed(false);

		resourceItemCaseCombo.setItems(ResourceCase.NORESTRICTION, ResourceCase.LOWERCASE, ResourceCase.UPPERCASE,
				ResourceCase.TITLECASE, ResourceCase.SENTENCECASE);

		ownerProductCombo.setItems(OwnerProduct.INFRASTRUCTURE, OwnerProduct.APPLICATION);

		binder.bind(resourceItemCaseCombo, ReResourceitem::getResourcecase, ReResourceitem::setResourcecase);
		binder.bind(ownerProductCombo, ReResourceitem::getOwnerproduct, ReResourceitem::setOwnerproduct);

		resourceItemCaseCombo.setSelectedItem(ResourceCase.NORESTRICTION);
		ownerProductCombo.setSelectedItem(OwnerProduct.INFRASTRUCTURE);

		FormLayout col01 = new FormLayout();
		col01.setSizeFull();
		col01.setSpacing(true);
		col01.setMargin(true);
		col01.setWidth("100%");
		col01.setHeight("100%");

		col01.addComponent(ordernr);
		col01.addComponent(tagnr);
		col01.addComponent(levelnr);
		col01.addComponent(prefix);
		col01.addComponent(info);
		col01.addComponent(resourceItemCaseCombo);
		col01.addComponent(ownerProductCombo);

		gridLayout.addComponent(col01, 0, 0, 1, 2);
		gridLayout.addComponent(buttonLayout, 1, 6);
		gridLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);

		VerticalLayout vers = new VerticalLayout();
		TwinColSelect<String> twinColl = new TwinColSelect<>();

		vers.addComponentsAndExpand(twinColl);

		save.setClickShortcut(KeyCode.ENTER);

		tabsheet.addTab(gridLayout, LangHelper.getLocalizableMessage(LogoResConstants.INFOSTR))
				.setIcon(VaadinIcons.INFO);

		mainLayout.addComponentsAndExpand(tabsheet);
		cancel.addClickListener(event -> close());

		setContent(mainLayout);
	}

	public void generateTagNr(Integer resourceref) {
		Integer max = reResourceitemRep.getMaxTagNr(resourceref);
		if (max == null)
			setTagnr(0);
		else
			setTagnr(++max);
	}

	public void generateOrderNr(Integer resourceref) {
		Integer max = reResourceitemRep.getMaxOrderNr(resourceref);
		if (max == null)
			setOrdernr(0);
		else
			setOrdernr(++max);
	}

	public void setTagnr(Integer value) {
		tagnr.setValue(Integer.toString(value));
	}

	public void setOrdernr(Integer value) {
		ordernr.setValue(Integer.toString(value));
	}

	public void persist() {
		try {
			resourceItem.setResourceref(resource.getId());
			resourceItem.setResourcetype(resource.getResourcetype().getTyp());
			resourceItem.setVersion(resource.getVersion());
			resourceItem.setRequested(resource.getRequested());
			resourceItem.setActive(resource.getActive().getTyp());
			resourceItem.setResourcecategory(resource.getResourcecategory());
			reResourceitemRep.save(resourceItem);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e.getMessage());
		}
	}

	Label createSeperator() {
		Label hSep = new Label();
		hSep.addStyleName("horizontal-separator3");
		hSep.setHeight("20px");
		hSep.setWidth("100%");
		return hSep;

	}

}
