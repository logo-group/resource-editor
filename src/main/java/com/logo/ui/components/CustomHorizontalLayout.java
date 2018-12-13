package com.logo.ui.components;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.vaadin.marcus.MouseEvents;

import com.github.appreciated.material.MaterialTheme;
import com.logo.data.entity.ReLanguageTable;
import com.logo.util.CustomLayoutUtil;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.TranslatorService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

public class CustomHorizontalLayout extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CustomHorizontalLayout.class.getName());

	private boolean isBlank = false;
	private boolean addTranslateButton = false;

	private String word;
	private String caption;
	private String pStyle = "<p style=";
	private String pBackgroundGreen = "background-color:white;color:green;font-size:15px;font-style:bold";
	private String pBackgroundRed = "background-color:white;color:red;border-color:red;font-size:15px;font-style:bold;font-style:italic";
	private String pBackgroundColor = "";
	private CustomLayoutUtil customLayoutUtil;
	private transient ReLanguageTable reLanguageTable;

	public CustomHorizontalLayout(Integer itemId, ReLanguageTable reLanguageTable, String word, String txtValue,
			String name, boolean addTranslateButton) {
		setSpacing(true);
		setMargin(true);
		setSizeFull();
		setHeight("70px");

		if (!txtValue.equals("")) {
			setBlanck(false);
			pBackgroundColor = pBackgroundGreen;
			caption = pStyle.concat(pBackgroundColor).concat(">" + name + "</p>");
		} else {
			setBlanck(true);
			pBackgroundColor = pBackgroundRed;
			caption = pStyle.concat(pBackgroundColor).concat(">" + name + "</p>");
		}
		this.word = word;
		this.reLanguageTable = reLanguageTable;

		customLayoutUtil = new CustomLayoutUtil(itemId, name);
		setAddTranslateButton(addTranslateButton);
		customLayoutUtil.setLangTo();

		setCaption(caption);
		setCaptionAsHtml(true);

		CustomLayoutObjects layoutObjects = new CustomLayoutObjects();

		layoutObjects.txtArea.setSizeFull();
		layoutObjects.txtArea.setWidth("100%");
		layoutObjects.txtArea.setReadOnly(true);
		layoutObjects.txtArea.setValue(txtValue);
		if (txtValue.length() > 100)
			layoutObjects.txtArea.setHeight("200%");

		layoutObjects.txtArea.setDescription(txtValue);
		addComponentsAndExpand(layoutObjects.txtArea);
		setComponentAlignment(layoutObjects.txtArea, Alignment.MIDDLE_LEFT);

		addComponent(layoutObjects.buttonLayoyt);
		setComponentAlignment(layoutObjects.buttonLayoyt, Alignment.BOTTOM_RIGHT);

		MouseEvents mouseEvents = MouseEvents.enableFor(layoutObjects.txtArea);
		MouseEvents mouseEvents2 = MouseEvents.enableFor(this);
		mouseEvents.addMouseOverListener(() -> layoutObjects.txtArea.addStyleName("v-textarea2"));
		mouseEvents.addMouseOutListener(() -> layoutObjects.txtArea.removeStyleName("v-textarea2"));

		mouseEvents2.addMouseOverListener(
				() -> this.setDescription(LangHelper.getLocalizableMessage(LogoResConstants.CLICKTOEDITSTR)));

		addLayoutClickListener(event -> {
			if (event.getChildComponent() == layoutObjects.txtArea) {
				edit(layoutObjects);
				setHeight("100%");
			}
		});

		if (isBlank()) {
			layoutObjects.txtArea.setVisible(false);
			layoutObjects.buttonLayoyt.addComponent(layoutObjects.addButton);
			layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.addButton, Alignment.BOTTOM_RIGHT);
		}

		layoutObjects.addButton.addClickListener(event -> add(layoutObjects));
	}

	public final boolean isBlank() {
		return isBlank;
	}

	public final void setBlanck(boolean isBlank) {
		this.isBlank = isBlank;
	}

	public void setAddTranslateButton(boolean addTranslateButton) {
		this.addTranslateButton = addTranslateButton;
	}

	private void add(CustomLayoutObjects layoutObjects) {
		layoutObjects.txtArea.setVisible(true);
		layoutObjects.txtArea.setReadOnly(false);
		layoutObjects.txtArea.removeStyleName(MaterialTheme.TEXTAREA_BORDERLESS);
		setHeight("100%");
		layoutObjects.txtArea.focus();
		layoutObjects.txtArea.setSelection(0, 0);

		layoutObjects.buttonLayoyt.removeComponent(layoutObjects.addButton);
		if (addTranslateButton) {
			layoutObjects.buttonLayoyt.addComponent(layoutObjects.translateButton);
			layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.translateButton, Alignment.BOTTOM_RIGHT);
		}

		layoutObjects.buttonLayoyt.addComponent(layoutObjects.saveButtonForAdd);
		layoutObjects.buttonLayoyt.addComponent(layoutObjects.cancelButtonForAdd);
		layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.saveButtonForAdd, Alignment.BOTTOM_RIGHT);
		layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.cancelButtonForAdd, Alignment.BOTTOM_RIGHT);

		layoutObjects.binder.setBean(reLanguageTable);
		layoutObjects.binder.forField(layoutObjects.txtArea).bind(ReLanguageTable::getResourcestr,
				ReLanguageTable::setResourcestr);

		layoutObjects.saveButtonForAdd.addClickListener(event -> save(layoutObjects, true));
		layoutObjects.cancelButtonForAdd.addClickListener(event -> cancel(layoutObjects, true));
		layoutObjects.translateButton.addClickListener(event -> translate(layoutObjects));
	}

	private void edit(CustomLayoutObjects layoutObjects) {
		layoutObjects.txtArea.removeStyleName("v-textarea2");
		setHeight("100%");
		layoutObjects.txtArea.setReadOnly(false);
		layoutObjects.txtArea.removeStyleName(MaterialTheme.TEXTAREA_BORDERLESS);
		layoutObjects.txtArea.setHeight("100%");
		layoutObjects.txtArea.focus();
		layoutObjects.txtArea.setSelection(0, 0);

		if (!isBlank()) {
			if (addTranslateButton) {
				layoutObjects.buttonLayoyt.addComponent(layoutObjects.translateButton);
				layoutObjects.buttonLayoyt.addComponentsAndExpand(layoutObjects.saveButtonForEdit);
				layoutObjects.buttonLayoyt.addComponentsAndExpand(layoutObjects.cancelButtonForEdit);
				layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.translateButton, Alignment.BOTTOM_RIGHT);
				layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.cancelButtonForEdit,
						Alignment.BOTTOM_RIGHT);
				layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.saveButtonForEdit,
						Alignment.BOTTOM_RIGHT);
			} else {
				layoutObjects.buttonLayoyt.setWidth("160px");
				layoutObjects.buttonLayoyt.addComponentsAndExpand(layoutObjects.saveButtonForEdit);
				layoutObjects.buttonLayoyt.addComponentsAndExpand(layoutObjects.cancelButtonForEdit);
				layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.cancelButtonForEdit,
						Alignment.BOTTOM_RIGHT);
				layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.saveButtonForEdit,
						Alignment.BOTTOM_RIGHT);
			}
		}

		layoutObjects.binder.setBean(reLanguageTable);
		layoutObjects.binder.forField(layoutObjects.txtArea).bind(ReLanguageTable::getResourcestr,
				ReLanguageTable::setResourcestr);

		layoutObjects.saveButtonForEdit.addClickListener(event -> save(layoutObjects, false));
		layoutObjects.cancelButtonForEdit.addClickListener(event -> cancel(layoutObjects, false));
		layoutObjects.translateButton.addClickListener(event -> translate(layoutObjects));
	}

	private void save(CustomLayoutObjects layoutObjects, boolean added) {
		layoutObjects.txtArea.setReadOnly(true);
		if (added) {
			layoutObjects.txtArea.setVisible(true);
			if (!layoutObjects.txtArea.getValue().equals("")) {
				layoutObjects.buttonLayoyt.removeComponent(layoutObjects.addButton);
				reLanguageTable = customLayoutUtil.prepareTable(layoutObjects.txtArea.getValue());
			} else {
				layoutObjects.txtArea.setVisible(false);
				layoutObjects.buttonLayoyt.addComponent(layoutObjects.addButton);
				layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.addButton, Alignment.BOTTOM_RIGHT);
			}
			layoutObjects.buttonLayoyt.removeComponent(layoutObjects.saveButtonForAdd);
			layoutObjects.buttonLayoyt.removeComponent(layoutObjects.cancelButtonForAdd);
		} else {
			layoutObjects.buttonLayoyt.removeComponent(layoutObjects.saveButtonForEdit);
			layoutObjects.buttonLayoyt.removeComponent(layoutObjects.cancelButtonForEdit);
		}
		layoutObjects.txtArea.addStyleName(MaterialTheme.TEXTAREA_BORDERLESS);

		if (addTranslateButton)
			layoutObjects.buttonLayoyt.removeComponent(layoutObjects.translateButton);

		if (!layoutObjects.txtArea.getValue().equals("")) {
			customLayoutUtil.persist(reLanguageTable);
			setBlanck(false);
		}

		if (added && !isBlank())
			setCaption(pStyle.concat(pBackgroundGreen).concat(">" + customLayoutUtil.getLang() + "</p>"));
		addStyleName("");
		setHeight("70px");
	}

	private void cancel(CustomLayoutObjects layoutObjects, boolean added) {
		layoutObjects.txtArea.setReadOnly(true);
		if (added) {
			layoutObjects.txtArea.setVisible(false);
			layoutObjects.txtArea.setValue("");
			layoutObjects.buttonLayoyt.addComponent(layoutObjects.addButton);
			layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.addButton, Alignment.BOTTOM_RIGHT);
			layoutObjects.buttonLayoyt.removeComponent(layoutObjects.saveButtonForAdd);
			layoutObjects.buttonLayoyt.removeComponent(layoutObjects.cancelButtonForAdd);
		} else {
			layoutObjects.buttonLayoyt.removeComponent(layoutObjects.saveButtonForEdit);
			layoutObjects.buttonLayoyt.removeComponent(layoutObjects.cancelButtonForEdit);
		}
		layoutObjects.txtArea.addStyleName(MaterialTheme.TEXTAREA_BORDERLESS);
		if (addTranslateButton)
			layoutObjects.buttonLayoyt.removeComponent(layoutObjects.translateButton);
		setHeight("70px");
		addStyleName("");
	}

	private void translate(CustomLayoutObjects layoutObjects) {
		TranslatorService translatorService = new TranslatorService();
		try {
			String text = translatorService.translate2(word);
			layoutObjects.txtArea.setValue(text);
			layoutObjects.txtArea.setReadOnly(false);
			setHeight("100%");
			layoutObjects.txtArea.setHeight("100%");
			layoutObjects.txtArea.focus();
			layoutObjects.txtArea.setSelection(0, 0);

		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage());
		}
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
