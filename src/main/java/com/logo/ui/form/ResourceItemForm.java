package com.logo.ui.form;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.vaadin.marcus.MouseEvents;

import com.github.appreciated.material.MaterialTheme;
import com.logo.LogoresMainUI;
import com.logo.data.entity.ReResource;
import com.logo.data.entity.ReResourceitem;
import com.logo.data.entity.ReTurkishtr;
import com.logo.data.repository.ReResourceitemRep;
import com.logo.data.repository.ReTurkishtrRep;
import com.logo.ui.components.ButtonGenerator;
import com.logo.ui.components.SpellChecComboBox;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.converter.StrToIntegerConverter;
import com.logo.util.enums.OwnerProduct;
import com.logo.util.enums.ResourceCase;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import eu.michaelvogt.vaadin.attribute.Attribute;

public class ResourceItemForm extends Panel {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(NewResourceForm.class.getName());

	private ReResource resource = new ReResource();
	private ReResourceitem resourceItem = new ReResourceitem();
	private final Binder<ReResourceitem> binder = new Binder<>(ReResourceitem.class);
	private final TextField ordernr = new TextField(LangHelper.getLocalizableMessage(LogoResConstants.ORDERNRSTR));
	private final TextField tagnr = new TextField(LangHelper.getLocalizableMessage(LogoResConstants.TAGNRSTR));
	private final TextField levelnr = new TextField(LangHelper.getLocalizableMessage(LogoResConstants.LEVELNRSTR));
	private final TextField prefix = new TextField(LangHelper.getLocalizableMessage(LogoResConstants.PREFIXSTR));
	private final TextField info = new TextField(LangHelper.getLocalizableMessage(LogoResConstants.INFOSTR));
	private final TextArea description = new TextArea(LogoResConstants.RE_TURKISHTR_NAME);
	
	private final Button save = new ButtonGenerator(LogoResConstants.SAVESTR);
	private final Button cancel = new ButtonGenerator(LogoResConstants.CANCELSTR);
	private final Button add = new ButtonGenerator(LogoResConstants.ADDNEWSTR);
	private final Button saveAndNew = new ButtonGenerator(LogoResConstants.SAVEANDNEWSTR);
	
	private final SpellChecComboBox<ResourceCase> resourceItemCaseCombo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(LogoResConstants.RESCASESTR));
	private final SpellChecComboBox<OwnerProduct> ownerProductCombo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(LogoResConstants.OWNERPRODUCT));
	private final HorizontalLayout buttonLayout = new HorizontalLayout(saveAndNew, save, cancel, add);

	private final transient ReResourceitemRep reResourceitemRep;
	private final transient ReTurkishtrRep reTurkishtrRep;
	
	public ResourceItemForm(ReResource resource) {
		this.reResourceitemRep = LogoresMainUI.getMrepositorycontainer().getReResourceitemRep();
		this.reTurkishtrRep = LogoresMainUI.getMrepositorycontainer().getReTurkishtrRep();
		this.resource = resource;
		setWidth("100%");
		setHeight("100%");
		
		
		GridLayout gridLayout = new GridLayout(4,10);
		gridLayout.addStyleName(LogoResConstants.STYLE_GRID);
		gridLayout.setWidth("100%");
		gridLayout.setSpacing(true);
		gridLayout.setMargin(true);
		
		addStyleName(MaterialTheme.CARD_HOVERABLE);
		
		resourceItem.setOrdernr(0);
		resourceItem.setTagnr(0);
		resourceItem.setLevelnr(0);
		resourceItem.setPrefixstr("");
		resourceItem.setInfo("");
		
		binder.setBean(resourceItem);
		ordernr.setWidth("100%");
		ordernr.setHeight("30px");
		
		ordernr.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		tagnr.setWidth("100%");
		tagnr.setHeight("30px");
		tagnr.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		levelnr.setWidth("100%");
		levelnr.setHeight("30px");
		levelnr.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		prefix.setWidth("100%");
		prefix.setHeight("30px");
		prefix.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		
		info.setWidth("100%");
		info.setHeight("30px");
		info.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		description.setWidth("400%");
		description.setHeight("100px");
		description.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		description.setWordWrap(true);
		
		Attribute spellcheckAttr1 = new Attribute(LogoResConstants.SPELLCHECK, LogoResConstants.FALSESTR);
		spellcheckAttr1.extend(description);
		
		save.setWidth("80px");
		cancel.setWidth("80px");
		add.setWidth("80px");
		saveAndNew.setWidth("80px");
		
		saveAndNew.setVisible(false);
		save.setVisible(false);
		cancel.setVisible(false);
		
		buttonLayout.setComponentAlignment(add, Alignment.BOTTOM_RIGHT);
		
		binder.forField(ordernr).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.ORDERNRNOTEMTYSTR))
				.withConverter(new StrToIntegerConverter(LogoResConstants.MUSTNUMBER))
				//.withValidator(number -> number <= 1, "Person must be born in the 20th century")
				.bind(ReResourceitem::getOrdernr, ReResourceitem::setOrdernr);

		binder.forField(tagnr).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.TAGNRNOTEMTYSTR))
		.withConverter(new StrToIntegerConverter(LogoResConstants.MUSTNUMBER))
		//.withValidator(number -> number <= 1, "Person must be born in the 20th century")
		.bind(ReResourceitem::getTagnr, ReResourceitem::setTagnr);

		binder.forField(levelnr).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.LEVELNRNOTEMTYSTR))
		.withConverter(new StrToIntegerConverter(LogoResConstants.MUSTNUMBER))
		//.withValidator(number -> number <= 1, "Person must be born in the 20th century")
		.bind(ReResourceitem::getLevelnr, ReResourceitem::setLevelnr);

		binder.forField(prefix).bind(ReResourceitem::getPrefixstr, ReResourceitem::setPrefixstr);

		binder.forField(info).bind(ReResourceitem::getInfo, ReResourceitem::setInfo);

		resourceItemCaseCombo.setWidth("100%");
		resourceItemCaseCombo.setHeight("30px");
		ownerProductCombo.setWidth("100%");
		ownerProductCombo.setHeight("30px");
		
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

		save.setClickShortcut(KeyCode.ENTER);


		Label label = new Label("New Resource Item Form");
		HorizontalLayout labelLayout = new HorizontalLayout();
		labelLayout.setWidth("100%");
		labelLayout.addStyleName("card-hoverableGreen");
		
		labelLayout.addComponent(label);
		labelLayout.setComponentAlignment(label, Alignment.TOP_CENTER);
		
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
		col01.addComponent(description);
		
		FormLayout col11 = new FormLayout();
		col11.setSizeFull();
		col11.setSpacing(true);
		col11.setMargin(true);
		col11.setWidth("100%");
		col11.setHeight("100%");
		col11.addComponent(resourceItemCaseCombo);
		col11.addComponent(ownerProductCombo);
		
		//gridLayout.addComponent(labelLayout, 0, 0, 3, 0);
		gridLayout.addComponent(col01, 0, 1, 1, 1);
		gridLayout.addComponent(col11, 2, 1, 3, 1);
		
		Label hSep0 = createSeperator();
		//gridLayout.addComponent(hSep0, 0, 6, 3, 6);

		gridLayout.addComponent(buttonLayout, 3, 7);
		
		Label hSep1 = createSeperator();
		gridLayout.addComponent(hSep1, 0, 8, 3, 8);

		gridLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);

		MouseEvents mouseEvents = MouseEvents.enableFor(description);

		mouseEvents.addMouseOverListener(() -> {
			description.removeStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
			description.addStyleName("v-textareaForm");
		});
		
		mouseEvents.addMouseOutListener(() -> {
			description.removeStyleName("v-textareaForm");
			description.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		});
		
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
		hSep.addStyleName("horizontal-separator3");
		hSep.setHeight("20px");
		hSep.setWidth("100%");
		return hSep;

	}

	public Button getSaveButton() {
		return save;
	}
	
	public Button getSaveAndNewButton() {
		return saveAndNew;
	}
	
	public Button getCancelButton() {
		return cancel;
	}
	
	public Button getAddButton() {
		return add;
	}
	
	public TextField getOrdernr() {
		return ordernr;
	}

	public void setOrdernr(Integer value) {
		ordernr.setValue(Integer.toString(value));
	}
	
	public TextField getPrefix() {
		return prefix;
	}
	
	public TextField getTagnr() {
		return tagnr;
	}

	public void setTagnr(Integer value) {
		tagnr.setValue(Integer.toString(value));
	}

	public TextField getInfo() {
		return info;
	}
	
	public TextField getLevelnr() {
		return levelnr;
	}
	
	public SpellChecComboBox<ResourceCase> getResourceItemCaseCombo() {
		return resourceItemCaseCombo;
	}
	
	public SpellChecComboBox<OwnerProduct> getOwnerProductCombo() {
		return ownerProductCombo;
	}
	
	public TextArea getDesc() {
		return description;
	}
	
	public ReResource getResource() {
		return resource;
	}
	
	public ReResourceitem getResourceItem() {
		return resourceItem;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
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
	
	public void persist()
	{
		try 
		{
			resourceItem.setReTurkishtr(null);
			reResourceitemRep.save(resourceItem);	
			
			ReTurkishtr reTurkishtr = new ReTurkishtr();
			reTurkishtr.setResourceitemref(resourceItem.getId());
			reTurkishtr.setResourcestr(getDesc().getValue());
			
			reTurkishtrRep.save(reTurkishtr);
			List<ReTurkishtr> reTurkishtrLst = new ArrayList<>();
			reTurkishtrLst.add(reTurkishtr);
			resourceItem.setReTurkishtr(reTurkishtrLst);
		} 
		catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e.getMessage());
		}
	}
}
