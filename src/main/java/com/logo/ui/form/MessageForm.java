package com.logo.ui.form;

import org.vaadin.dialogs.ConfirmDialog;

import com.github.appreciated.material.MaterialTheme;
import com.logo.data.entity.ReMessage;
import com.logo.data.repository.ReMessageRep;
import com.logo.ui.components.ButtonGenerator;
import com.logo.ui.components.SpellChecComboBox;
import com.logo.ui.components.SpellChecTextField;
import com.logo.ui.view.ReMessageView;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.converter.StrToIntegerConverter;
import com.logo.util.enums.MessageType;
import com.logo.util.enums.ResourceGroup;
import com.vaadin.data.Binder;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SpringComponent
@UIScope
public class MessageForm extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private final SpellChecTextField javaConstId = new SpellChecTextField("Java Constant ID");
	private final SpellChecTextField module = new SpellChecTextField("Module");
	private final SpellChecTextField resourceNumber = new SpellChecTextField("Resource Number");
	private final SpellChecTextField tagNr = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.TAGNRSTR));
	private final SpellChecComboBox<ResourceGroup> resourceGroupCombo = new SpellChecComboBox<>("Resource Group");
	private final SpellChecComboBox<MessageType> messageTypeCombo = new SpellChecComboBox<>("Type");

	private final Button save = new ButtonGenerator(LogoResConstants.SAVESTR);
	private final Button close = new ButtonGenerator(LogoResConstants.CLOSELSTR);
	private final Button delete = new ButtonGenerator(LogoResConstants.DELETESTR);

	private final Label formName = new Label();

	public ReMessageRep reMessageRepo;
	private ReMessage reMessage;
	private ReMessageView reMessageView;
	private Binder<ReMessage> binder = new Binder<>(ReMessage.class);

	public MessageForm(ReMessageView reMessageView, ReMessageRep reMessageRepo) {
		this.reMessageView = reMessageView;
		this.reMessageRepo = reMessageRepo;
		GridLayout userMainLayout = new GridLayout(2, 6);

		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		setHeight("100%");

		userMainLayout.setSpacing(true);
		userMainLayout.setMargin(true);
		userMainLayout.setWidth("100%");
		userMainLayout.setHeight("100%");

		javaConstId.setWidth("100%");
		javaConstId.setHeight("30px");
		module.setWidth("50%");
		module.setHeight("30px");
		resourceNumber.setWidth("50%");
		resourceNumber.setHeight("30px");
		tagNr.setWidth("50%");
		tagNr.setHeight("30px");
		messageTypeCombo.setWidth("50%");
		messageTypeCombo.setHeight("30px");
		resourceGroupCombo.setWidth("50%");
		resourceGroupCombo.setHeight("30px");

		save.setHeight("30px");
		close.setHeight("30px");
		close.addStyleName(MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_BORDERLESS + " "
				+ LogoResConstants.STYLE_CUSTOM_WHITE);

		messageTypeCombo.setItems(MessageType.ERROR, MessageType.INFO, MessageType.WARN, MessageType.SELECTION);
		resourceGroupCombo.setItems(ResourceGroup.UN, ResourceGroup.HR, ResourceGroup.UNRP, ResourceGroup.HRRP,
				ResourceGroup.SS, ResourceGroup.HELP, ResourceGroup.MISC);

		messageTypeCombo.setEmptySelectionAllowed(false);
		resourceGroupCombo.setEmptySelectionAllowed(false);

		messageTypeCombo.setSelectedItem(MessageType.ERROR);
		resourceGroupCombo.setSelectedItem(ResourceGroup.UN);

		binder.forField(javaConstId).asRequired("username can not be empty").bind(ReMessage::getConsId,
				ReMessage::setConsId);

		binder.forField(module).asRequired("name can not be empty").bind(ReMessage::getModule, ReMessage::setModule);
		binder.forField(tagNr)
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReMessage::getStrtag, ReMessage::setStrtag);
		binder.bind(messageTypeCombo, ReMessage::getMtype, ReMessage::setMtype);
		binder.bind(resourceGroupCombo, ReMessage::getResgroup, ReMessage::setResgroup);
		binder.forField(resourceNumber).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.RESNRNOTEMTYSTR))
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReMessage::getListid, ReMessage::setListid);

		HorizontalLayout labelLayout = new HorizontalLayout();
		labelLayout.setWidth("100%");
		labelLayout.addStyleName("card-hoverable-material-default-primary-color");

		labelLayout.addComponent(formName);
		labelLayout.addComponent(close);
		labelLayout.setComponentAlignment(formName, Alignment.TOP_LEFT);
		labelLayout.setComponentAlignment(close, Alignment.TOP_RIGHT);

		delete.setVisible(false);
		HorizontalLayout buttonLayout = new HorizontalLayout(save, delete);

		messageTypeCombo.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		javaConstId.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		module.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		resourceGroupCombo.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		resourceNumber.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		tagNr.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		FormLayout col01 = new FormLayout();
		col01.setSizeFull();
		col01.setSpacing(true);
		col01.setMargin(true);
		col01.setWidth("100%");
		col01.setHeight("100%");
		col01.addComponent(messageTypeCombo);
		col01.addComponent(javaConstId);
		col01.addComponent(module);
		col01.addComponent(resourceGroupCombo);
		col01.addComponent(resourceNumber);
		col01.addComponent(tagNr);

		userMainLayout.addComponent(col01, 0, 0, 1, 0);

		save.addClickListener(e -> save());
		close.addClickListener(e -> cancel());
		delete.addClickListener(e -> delete());

		addComponent(labelLayout);
		addComponentsAndExpand(userMainLayout);
		addComponent(buttonLayout);
		setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);
		addStyleName(MaterialTheme.LAYOUT_CARD);
	}

	public void setReMessage(ReMessage reMessage) {
		this.reMessage = reMessage;
		delete.setVisible(false);
		formName.setValue(reMessage.getConsId() + " ");
		binder.setBean(reMessage);
		if (reMessage.isPersisted())
			delete.setVisible(true);
		setVisible(true);
	}

	private void delete() {
		ConfirmDialog.show(getUI(), "Dikkat !", reMessage.getConsId() + " mesaj silinecektir onaylıyor musunuz ?",
				"Evet", "Hayır", new ConfirmDialog.Listener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							reMessageRepo.delete(reMessage);
							Notification.show(reMessage.getConsId() + " mesaj silindi");
							setVisible(false);
							reMessageView.updateMessageListForCons("");
						}
					}
				});
	}

	private void cancel() {
		setVisible(false);
		reMessageView.refreshGrid();
	}

	private void save() {
		boolean isPersisted = reMessage.isPersisted();
		try {
			reMessageRepo.save(reMessage);
		} catch (Exception e) {
			Notification notification = new Notification("<p>Caption</p>",
					"<div style='display:inline-block;'><h1>Description</h1>"
							+ "<p class='tested-p'>tested p</p></div>");
			notification.setHtmlContentAllowed(true);
			notification.setDelayMsec(50000);
			notification.show(Page.getCurrent());
		}
		if (isPersisted)
			reMessageView.refreshGrid();
		else
			reMessageView.updateMessageListForCons("");
		setVisible(false);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
