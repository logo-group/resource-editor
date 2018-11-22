package com.logo.components;

import com.github.appreciated.material.MaterialTheme;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class TransferLayout extends VerticalLayout{

	private static final long serialVersionUID = 1L;

	private final TextField resourceNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));
	private final TextField resourceNr2 = createTextField("");

	private final TextField orderNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.ORDERNRSTR));
	private final TextField orderNr2 = createTextField("");

	private final TextField tagNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.TAGNRSTR));
	private final TextField tagNr2 = createTextField("");

	private final TextField levelNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.LEVELNRSTR));
	private final TextField levelNr2 = createTextField("");
	
	private final SwitchWithTextBox turkishTR = new SwitchWithTextBox(LogoResConstants.RE_TURKISHTR_NAME, 1);
	private final SwitchWithTextBox englishUS = new SwitchWithTextBox(LogoResConstants.RE_ENGLISHUS_NAME, 0);
	
	private final Button exportExcelButton = new Button("Export excel");
	private final Button exportXmlButton = new Button("Export xml");
	private final Button exportTxtButton = new Button("Export txt");
	
	public TransferLayout() {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setWidth("50%");
		mainLayout.setHeight("100%");
		mainLayout.addStyleName(MaterialTheme.CARD_HOVERABLE);

		addStyleName(MaterialTheme.LAYOUT_CARD);
		GridLayout filterLayout = new GridLayout(2, 2);
		filterLayout.setSizeFull();
		filterLayout.setWidth("100%");
		filterLayout.setHeight("100%");

		addComponent(mainLayout);
		setComponentAlignment(mainLayout, Alignment.TOP_CENTER);

		mainLayout.addComponent(filterLayout);
		
		turkishTR.setHeight("100%");
		englishUS.setHeight("100%");

		FormLayout col01 = new FormLayout();
		col01.setSizeUndefined();
		col01.setSpacing(true);
		col01.setMargin(true);
		col01.setWidth("100%");
		col01.addComponent(resourceNr1);
		col01.addComponent(orderNr1);
		col01.addComponent(tagNr1);
		col01.addComponent(levelNr1);
		col01.addComponent(turkishTR);
		col01.addComponent(englishUS);

		exportExcelButton.setIcon(VaadinIcons.TABLE);
		exportExcelButton.addStyleName(
				MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);

		exportXmlButton.setIcon(VaadinIcons.FILE_CODE);
		exportXmlButton.addStyleName(
				MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);

		exportTxtButton.setIcon(VaadinIcons.FILE_CODE);
		exportTxtButton.addStyleName(
				MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);
		
		FormLayout col11 = new FormLayout();
		col11.setSizeUndefined();
		col11.setSpacing(true);
		col11.setMargin(true);
		col11.setWidth("100%");
		col11.addComponent(resourceNr2);
		col11.addComponent(orderNr2);
		col11.addComponent(tagNr2);
		col11.addComponent(levelNr2);

		filterLayout.addComponent(col01, 0, 0, 0, 0);
		filterLayout.addComponent(col11, 1, 0, 1, 1);
		
		resourceNr1.setTabIndex(1);
		resourceNr2.setTabIndex(2);

		orderNr1.setTabIndex(3);
		orderNr2.setTabIndex(4);
		
		tagNr1.setTabIndex(5);
		tagNr2.setTabIndex(6);
		
		levelNr1.setTabIndex(7);
		levelNr2.setTabIndex(8);
		HorizontalLayout buttonLayout = new HorizontalLayout();
		
		buttonLayout.addComponent(exportExcelButton);
		buttonLayout.addComponent(exportXmlButton);
		buttonLayout.addComponent(exportTxtButton);
		mainLayout.addComponent(buttonLayout);
		mainLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);
	}
	
	TextField createTextField(String caption) {
		TextField textField = new TextField(caption);
		textField.setCaptionAsHtml(true);
		textField.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		textField.setHeight("100%");
		textField.setWidth("100%");
		return textField;
	}

	public Button getExporExceltButton() {
		return exportExcelButton;
	}
	
	public Button getExportXmlButton() {
		return exportXmlButton;
	}
	
	public Button getExportTxtButton() {
		return exportTxtButton;
	}
	
	public final TextField getResourceNr1() {
		return resourceNr1;
	}

	public final TextField getResourceNr2() {
		return resourceNr2;
	}

	public final TextField getOrderNr1() {
		return orderNr1;
	}

	public final TextField getOrderNr2() {
		return orderNr2;
	}

	public final TextField getTagNr1() {
		return tagNr1;
	}

	public final TextField getTagNr2() {
		return tagNr2;
	}

	public final TextField getLevelNr1() {
		return levelNr1;
	}

	public final TextField getLevelNr2() {
		return levelNr2;
	}

	public final SwitchWithTextBox getTurkishTR() {
		return turkishTR;
	}

	public final SwitchWithTextBox getEnglishUS() {
		return englishUS;
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
