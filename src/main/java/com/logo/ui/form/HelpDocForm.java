package com.logo.ui.form;

import org.vaadin.dialogs.ConfirmDialog;

import com.github.appreciated.material.MaterialTheme;
import com.logo.data.entity.ReHelpDocs;
import com.logo.data.repository.ReHelpDocsRep;
import com.logo.ui.components.ButtonGenerator;
import com.logo.ui.components.SpellChecComboBox;
import com.logo.ui.components.SpellChecTextField;
import com.logo.ui.view.ReHelpDocsView;
import com.logo.util.LogoResConstants;
import com.logo.util.enums.DocType;
import com.vaadin.data.Binder;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

import eu.michaelvogt.vaadin.attribute.Attribute;

@SpringComponent
@UIScope
public class HelpDocForm extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private final SpellChecTextField helpDocName = new SpellChecTextField("Help docname");
	private final SpellChecTextField title = new SpellChecTextField("Title");
	private final SpellChecComboBox<DocType> docTypeCombo = new SpellChecComboBox<>("Type");
	private final TextArea txtArea = new TextArea("Body");
	private final Attribute spellcheckAttr = new Attribute("spellcheck", "false");

	private final Button save = new ButtonGenerator(LogoResConstants.SAVESTR);
	private final Button close = new ButtonGenerator(LogoResConstants.CLOSELSTR);
	private final Button delete = new ButtonGenerator(LogoResConstants.DELETESTR);

	private final Label formName = new Label();
	private ReHelpDocsRep reHelpDocsRep;
	private ReHelpDocs reHelpDocs;
	private ReHelpDocsView reHelpDocsView;
	private Binder<ReHelpDocs> binder = new Binder<>(ReHelpDocs.class);

	public HelpDocForm(ReHelpDocsView reHelpDocsView, ReHelpDocsRep reHelpDocsRep) {
		this.reHelpDocsView = reHelpDocsView;
		this.reHelpDocsRep = reHelpDocsRep;

		setSizeFull();
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		setHeight("100%");

		helpDocName.setWidth("100%");
		helpDocName.setHeight("30px");
		title.setWidth("50%");
		title.setHeight("30px");
		docTypeCombo.setWidth("50%");
		docTypeCombo.setHeight("30px");
		txtArea.addStyleName(MaterialTheme.TEXTAREA_LARGE);
		txtArea.setWordWrap(true);
		spellcheckAttr.extend(txtArea);

		txtArea.setSizeFull();
		txtArea.setWidth("100%");
		txtArea.setHeight("500px");

		save.setHeight("30px");
		close.setHeight("30px");
		close.addStyleName(MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_BORDERLESS + " "
				+ LogoResConstants.STYLE_CUSTOM_WHITE);

		docTypeCombo.setItems(DocType.LOCALIZABLEHELP, DocType.NONLOCALIZABLEHELP, DocType.REUSABLEHELP,
				DocType.LOCALIZABLEJAVA);

		docTypeCombo.setEmptySelectionAllowed(false);

		docTypeCombo.setSelectedItem(DocType.LOCALIZABLEHELP);

		binder.forField(helpDocName).asRequired("username can not be empty").bind(ReHelpDocs::getDocname,
				ReHelpDocs::setDocname);

		binder.forField(title).asRequired("name can not be empty").bind(ReHelpDocs::getDoctitle,
				ReHelpDocs::setDoctitle);
		binder.bind(docTypeCombo, ReHelpDocs::getDoctype, ReHelpDocs::setDoctype);

		RichTextArea rText = new RichTextArea();
		rText.setSizeFull();
		rText.setWidth("100%");
		rText.setHeight("100%");

		binder.bind(txtArea, ReHelpDocs::getDocbody, ReHelpDocs::setDocbody);

		HorizontalLayout labelLayout = new HorizontalLayout();
		labelLayout.setWidth("100%");
		labelLayout.addStyleName("card-hoverable-material-default-primary-color");

		labelLayout.addComponent(formName);
		labelLayout.addComponent(close);
		labelLayout.setComponentAlignment(formName, Alignment.TOP_LEFT);
		labelLayout.setComponentAlignment(close, Alignment.TOP_RIGHT);

		delete.setVisible(false);
		HorizontalLayout buttonLayout = new HorizontalLayout(save, delete);

		helpDocName.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		title.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		docTypeCombo.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		addComponent(labelLayout);
		FormLayout col01 = new FormLayout();
		col01.setSizeFull();
		col01.setSpacing(true);
		col01.setMargin(true);
		col01.setWidth("100%");
		col01.setHeight("100%");

		FormLayout col02 = new FormLayout();
		col02.setSizeFull();
		col02.setSpacing(true);
		col02.setMargin(true);
		col02.setWidth("100%");
		col02.setHeight("100%");

		col01.addComponent(helpDocName);
		col01.addComponent(title);
		col01.addComponent(docTypeCombo);

		addComponentsAndExpand(col01);
		col02.addComponent(txtArea);
		addComponentsAndExpand(col02);
		setExpandRatio(col01, 1.0f);
		setExpandRatio(col02, 4.0f);

		save.addClickListener(e -> save());
		close.addClickListener(e -> cancel());
		delete.addClickListener(e -> delete());

		addComponents(buttonLayout);
		setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);
		addStyleName(MaterialTheme.CARD_1);
	}

	public void setReHelpDoc(ReHelpDocs reHelpDocs) {
		this.reHelpDocs = reHelpDocs;
		delete.setVisible(false);
		formName.setValue(reHelpDocs.getDocname() + " ");
		binder.setBean(reHelpDocs);
		if (reHelpDocs.isPersisted())
			delete.setVisible(true);
		setVisible(true);
	}

	private void delete() {
		ConfirmDialog.show(getUI(), "Dikkat !", reHelpDocs.getDocname() + " mesaj silinecektir onaylıyor musunuz ?",
				"Evet", "Hayır", new ConfirmDialog.Listener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							reHelpDocsRep.delete(reHelpDocs);
							Notification.show(reHelpDocs.getDocname() + " help silindi");
							setVisible(false);
							reHelpDocsView.updateHelpListForDocName("");
						}
					}
				});
	}

	private void cancel() {
		setVisible(false);
		reHelpDocsView.refreshGrid();
	}

	private void save() {
		boolean isPersisted = reHelpDocs.isPersisted();
		try {
			reHelpDocsRep.save(reHelpDocs);
		} catch (Exception e) {
			Notification notification = new Notification("<p>Caption</p>",
					"<div style='display:inline-block;'><h1>Description</h1>"
							+ "<p class='tested-p'>tested p</p></div>");
			notification.setHtmlContentAllowed(true);
			notification.setDelayMsec(50000);
			notification.show(Page.getCurrent());
		}
		if (isPersisted)
			reHelpDocsView.refreshGrid();
		else
			reHelpDocsView.updateHelpListForDocName("");
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
