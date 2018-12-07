package com.logo.ui.components;

import org.vaadin.marcus.MouseEvents;

import com.github.appreciated.material.MaterialTheme;
import com.logo.data.entity.ReStandard;
import com.logo.util.CustomLayoutUtil;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

public class CustomHorizontalStandardLayout extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private boolean isBlank = false;

	private String caption;
	private String pStyle = "<p style=";
	private String pBackgroundGreen = "background-color:white;color:green;font-size:15px;font-style:bold";
	private String pBackgroundRed = "background-color:white;color:red;border-color:red;font-size:15px;font-style:bold;font-style:italic";
	private String pBackgroundColor = "";
	private CustomLayoutUtil customLayoutUtil;
	private transient ReStandard reStandard;

	public CustomHorizontalStandardLayout(Integer itemId, ReStandard reStandard, String word, String txtValue,
			String name) {
		setSpacing(true);
		setMargin(true);
		setSizeFull();
		setHeight("70px");

		if (!txtValue.equals("")) {
			pBackgroundColor = pBackgroundGreen;
			caption = pStyle.concat(pBackgroundColor).concat(">" + name + "</p>");
		} else {
			pBackgroundColor = pBackgroundRed;
			caption = pStyle.concat(pBackgroundColor).concat(">" + name + "</p>");
		}
		this.reStandard = reStandard;

		customLayoutUtil = new CustomLayoutUtil(itemId, name);

		setCaption(caption);
		setCaptionAsHtml(true);

		CustomLayoutStandardObjects layoutObjects = new CustomLayoutStandardObjects();

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

	}

	private void edit(CustomLayoutStandardObjects layoutObjects) {
		layoutObjects.txtArea.removeStyleName("v-textarea2");
		setHeight("100%");
		layoutObjects.txtArea.setReadOnly(false);
		layoutObjects.txtArea.removeStyleName(MaterialTheme.TEXTAREA_BORDERLESS);
		layoutObjects.txtArea.setHeight("100%");
		layoutObjects.txtArea.focus();
		layoutObjects.txtArea.setSelection(0, 0);

		layoutObjects.buttonLayoyt.setWidth("160px");
		layoutObjects.buttonLayoyt.addComponentsAndExpand(layoutObjects.saveButtonForEdit);
		layoutObjects.buttonLayoyt.addComponentsAndExpand(layoutObjects.cancelButtonForEdit);
		layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.cancelButtonForEdit, Alignment.BOTTOM_RIGHT);
		layoutObjects.buttonLayoyt.setComponentAlignment(layoutObjects.saveButtonForEdit, Alignment.BOTTOM_RIGHT);

		layoutObjects.binder.setBean(reStandard);
		layoutObjects.binder.forField(layoutObjects.txtArea).bind(ReStandard::getResourceStr,
				ReStandard::setResourceStr);

		layoutObjects.saveButtonForEdit.addClickListener(event -> save(layoutObjects, false));
		layoutObjects.cancelButtonForEdit.addClickListener(event -> cancel(layoutObjects, false));
	}

	private void save(CustomLayoutStandardObjects layoutObjects, boolean added) {
		layoutObjects.txtArea.setReadOnly(true);

		layoutObjects.buttonLayoyt.removeComponent(layoutObjects.saveButtonForEdit);
		layoutObjects.buttonLayoyt.removeComponent(layoutObjects.cancelButtonForEdit);
		layoutObjects.txtArea.addStyleName(MaterialTheme.TEXTAREA_BORDERLESS);

		if (!layoutObjects.txtArea.getValue().equals("")) {
			customLayoutUtil.persistStandard(reStandard);
		}

		if (added) {
			setCaption(pStyle.concat(pBackgroundGreen).concat(">" + customLayoutUtil.getLang() + "</p>"));
		}
		addStyleName("");
		setHeight("70px");
	}

	private void cancel(CustomLayoutStandardObjects layoutObjects, boolean added) {
		layoutObjects.txtArea.setReadOnly(true);
		layoutObjects.buttonLayoyt.removeComponent(layoutObjects.saveButtonForEdit);
		layoutObjects.buttonLayoyt.removeComponent(layoutObjects.cancelButtonForEdit);
		layoutObjects.txtArea.addStyleName(MaterialTheme.TEXTAREA_BORDERLESS);
		setHeight("70px");
		addStyleName("");
	}

}
