package com.logo.ui.window;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.logo.LogoresMainUI;
import com.logo.data.entity.ReAlbaniankv;
import com.logo.data.entity.ReArabiceg;
import com.logo.data.entity.ReArabicjo;
import com.logo.data.entity.ReArabicsa;
import com.logo.data.entity.ReAzerbaijaniaz;
import com.logo.data.entity.ReBulgarianbg;
import com.logo.data.entity.ReEnglishus;
import com.logo.data.entity.ReFrenchfr;
import com.logo.data.entity.ReGeorgiange;
import com.logo.data.entity.ReGermande;
import com.logo.data.entity.RePersianir;
import com.logo.data.entity.ReResource;
import com.logo.data.entity.ReResourceitem;
import com.logo.data.entity.ReRomanianro;
import com.logo.data.entity.ReRussianru;
import com.logo.data.entity.ReStandard;
import com.logo.data.entity.ReTurkishtr;
import com.logo.data.entity.ReTurkmentm;
import com.logo.data.entity.ReUser;
import com.logo.data.repository.ReAlbaniankvRep;
import com.logo.data.repository.ReArabicegRep;
import com.logo.data.repository.ReArabicjoRep;
import com.logo.data.repository.ReArabicsaRep;
import com.logo.data.repository.ReAzerbaijaniazRep;
import com.logo.data.repository.ReBulgarianbgRep;
import com.logo.data.repository.ReEnglishusRep;
import com.logo.data.repository.ReFrenchfrRep;
import com.logo.data.repository.ReGeorgiangeRep;
import com.logo.data.repository.ReGermandeRep;
import com.logo.data.repository.RePersianirRep;
import com.logo.data.repository.ReResourceRep;
import com.logo.data.repository.ReRomanianroRep;
import com.logo.data.repository.ReRussianruRep;
import com.logo.data.repository.ReStandardRep;
import com.logo.data.repository.ReTurkishtrRep;
import com.logo.data.repository.ReTurkmentmRep;
import com.logo.ui.components.ButtonGenerator;
import com.logo.ui.components.SpellChecComboBox;
import com.logo.ui.components.SpellChecTextField;
import com.logo.ui.components.SwitchWithTextBox;
import com.logo.ui.view.ResourceViewNew;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.enums.ResourceGroup;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ResourceCopyWindow extends Window {

	private static final long serialVersionUID = 1L;

	private final Binder<ReResource> binder = new Binder<>(ReResource.class);

	private final SpellChecTextField tagStart = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.TAGNRSTART));
	private final SpellChecTextField tagEnd = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.TAGNREND));

	private final SwitchWithTextBox tagAll = new SwitchWithTextBox(
			LangHelper.getLocalizableMessage(LogoResConstants.TAGALL), 1);

	private final SpellChecComboBox<ResourceGroup> resourceGroupCombo = new SpellChecComboBox<>(
			LangHelper.getLocalizableMessage(LogoResConstants.RESGRPSTR));

	private final SpellChecTextField resourcenr = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));

	private final Button save = new ButtonGenerator(LogoResConstants.SAVESTR);
	private final Button cancel = new ButtonGenerator(LogoResConstants.CANCELSTR);

	private final HorizontalLayout buttonLayout = new HorizontalLayout(save, cancel);

	private final Label formName = new Label();

	private final transient ReResourceRep resRepo;

	private final transient ReUser reUser;

	private final transient ReTurkishtrRep turkishRep;

	private final transient ReEnglishusRep englishRep;

	private final transient ReGermandeRep germanRep;

	private final transient RePersianirRep persianRep;

	private final transient ReAzerbaijaniazRep azerbaijaniRep;

	private final transient ReBulgarianbgRep bulgarianRep;

	private final transient ReRussianruRep russianRep;

	private final transient ReRomanianroRep romanianRep;

	private final transient ReGeorgiangeRep georgianRep;

	private final transient ReArabicjoRep arabicjoRep;

	private final transient ReFrenchfrRep frenchRep;

	private final transient ReAlbaniankvRep albanianRep;

	private final transient ReTurkmentmRep turkmenRep;

	private final transient ReArabicegRep arabicegRep;

	private final transient ReArabicsaRep arabicsaRep;

	private final transient ReStandardRep standardRep;

	public ResourceCopyWindow(ReResource resource, ResourceViewNew resView) {
		this.resRepo = LogoresMainUI.getMrepositorycontainer().getResRepo();
		this.reUser = (ReUser) VaadinSession.getCurrent().getAttribute("user");
		this.turkishRep = LogoresMainUI.getMrepositorycontainer().getReTurkishtrRep();
		this.englishRep = LogoresMainUI.getMrepositorycontainer().getReEnglishusRep();
		this.germanRep = LogoresMainUI.getMrepositorycontainer().getReGermandeRep();
		this.persianRep = LogoresMainUI.getMrepositorycontainer().getRePersianirRep();
		this.azerbaijaniRep = LogoresMainUI.getMrepositorycontainer().getReAzerbaijaniazRep();
		this.bulgarianRep = LogoresMainUI.getMrepositorycontainer().getReBulgarianbgRep();
		this.russianRep = LogoresMainUI.getMrepositorycontainer().getReRussianruRep();
		this.romanianRep = LogoresMainUI.getMrepositorycontainer().getReRomanianroRep();
		this.georgianRep = LogoresMainUI.getMrepositorycontainer().getReGeorgiangeRep();
		this.arabicjoRep = LogoresMainUI.getMrepositorycontainer().getReArabicjoRep();
		this.frenchRep = LogoresMainUI.getMrepositorycontainer().getReFrenchfrRep();
		this.albanianRep = LogoresMainUI.getMrepositorycontainer().getReAlbaniankvRep();
		this.turkmenRep = LogoresMainUI.getMrepositorycontainer().getReTurkmentmRep();
		this.arabicegRep = LogoresMainUI.getMrepositorycontainer().getReArabicegRep();
		this.arabicsaRep = LogoresMainUI.getMrepositorycontainer().getReArabicsaRep();
		this.standardRep = LogoresMainUI.getMrepositorycontainer().getReStandardRep();

		this.binder.setBean(resource);

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
		setHeight(60.0f, Unit.PERCENTAGE);
		setResizable(false);
		setResponsive(true);

		GridLayout gridLayout = new GridLayout(2, 7);
		gridLayout.setSizeFull();
		gridLayout.setSpacing(true);
		gridLayout.setMargin(true);
		gridLayout.setWidth("100%");
		gridLayout.setHeight("100%");

		tagStart.setWidth("100%");
		tagStart.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		tagStart.setEnabled(false);

		tagEnd.setWidth("100%");
		tagEnd.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		tagEnd.setEnabled(false);

		tagAll.setHeight("22px");

		resourceGroupCombo.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		resourceGroupCombo.setItems(ResourceGroup.UN, ResourceGroup.HR, ResourceGroup.UNRP, ResourceGroup.HRRP,
				ResourceGroup.SS, ResourceGroup.HELP, ResourceGroup.MISC);
		resourceGroupCombo.setEmptySelectionAllowed(false);
		resourceGroupCombo.setSelectedItem(ResourceGroup.UN);

		resourcenr.setWidth("100%");
		resourcenr.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		formName.setValue("Copy: " + resource.getResourcegroup().name() + "-" + resource.getResourcenr());

		HorizontalLayout labelLayout = new HorizontalLayout();
		labelLayout.setWidth("100%");
		labelLayout.addStyleName("card-hoverable-material-darkBlue-primary-color");
		labelLayout.setDescription(LangHelper.getLocalizableMessage(LogoResConstants.CLOSELSTR));

		save.setWidth("80px");
		cancel.setWidth("80px");

		labelLayout.addComponent(formName);
		labelLayout.setComponentAlignment(formName, Alignment.TOP_LEFT);

		labelLayout.addLayoutClickListener(e -> close());

		mainLayout.addComponent(labelLayout);

		FormLayout col01 = new FormLayout();
		col01.setSizeFull();
		col01.setSpacing(true);
		col01.setMargin(true);
		col01.setWidth("100%");
		col01.setHeight("100%");
		col01.addComponent(tagStart);
		col01.addComponent(tagEnd);
		col01.addComponent(tagAll);
		col01.addComponent(resourceGroupCombo);
		col01.addComponent(resourcenr);

		gridLayout.addComponent(col01, 0, 0, 1, 2);
		gridLayout.addComponent(buttonLayout, 1, 6);
		gridLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);

		mainLayout.addComponentsAndExpand(gridLayout);

		tagAll.addValueChangeListener(event -> {
			if (!tagAll.getValue()) {
				tagStart.setEnabled(true);
				tagEnd.setEnabled(true);
			} else {
				tagStart.setEnabled(false);
				tagEnd.setEnabled(false);
			}
		});

		cancel.addClickListener(event -> close());

		save.addClickListener(event -> {
			if (showWarningNotifications()) {
				return;
			}

			ReResource copiedResource = resource.copyResource();
			copiedResource.setResourcenr(Integer.valueOf(resourcenr.getValue()));
			copiedResource.setResourcegroup(resourceGroupCombo.getSelectedItem().get());
			copiedResource.setCreatedby(reUser.getId());
			copiedResource.setModifiedby(reUser.getId());

			List<ReResourceitem> resourceItemList = resource.getReResourceitem();

			for (ReResourceitem resourceItem : resourceItemList) {
				ReResourceitem copiedResourceItem = resourceItem.copyResourceItem(copiedResource);
				if (copiedResourceItem.getCreatedby() == null) {
					copiedResourceItem.setCreatedby(reUser.getId());
				}
				if (!tagAll.getValue()) {
					if (copiedResourceItem.getTagnr() >= Integer.valueOf(tagStart.getValue())
							&& copiedResourceItem.getTagnr() <= Integer.valueOf(tagEnd.getValue())) {
						copiedResource.getReResourceitem().add(copiedResourceItem);
					}
				} else {
					copiedResource.getReResourceitem().add(copiedResourceItem);
				}
			}
			try {
				resRepo.save(copiedResource);
				for (ReResourceitem item : copiedResource.getReResourceitem()) {
					persistItemLanguages(item);
					persistStandard(item);
				}
			} catch (DataIntegrityViolationException e) {
				Notification.show(LangHelper.getLocalizableMessage(LogoResConstants.UNIQUE_FIELD_NOTIFICATION),
						Type.ERROR_MESSAGE);
				return;
			}
			String filter = copiedResource.getResourcegroup().name() + "->"
					+ Integer.toString(copiedResource.getResourcenr());
			resView.createResoucePage(filter, true);
			close();
		});
		setContent(mainLayout);
	}

	private void persistStandard(ReResourceitem item) {
		if (item.getReStandard() != null) {
			ReStandard standard = item.getReStandard().cloneStandard(item.getId());
			standardRep.save(standard);
		}
	}

	private void persistItemLanguages(ReResourceitem item) {
		if (item.getReTurkishtr() != null) {
			ReTurkishtr turkish = item.getReTurkishtr().cloneTurkish(item);
			turkishRep.save(turkish);
		}
		if (item.getReEnglishus() != null) {
			ReEnglishus english = item.getReEnglishus().cloneEnglishus(item);
			englishRep.save(english);
		}
		if (item.getReGermande() != null) {
			ReGermande german = item.getReGermande().cloneGermande(item);
			germanRep.save(german);
		}
		if (item.getRePersianir() != null) {
			RePersianir persian = item.getRePersianir().clonePersianir(item);
			persianRep.save(persian);
		}
		if (item.getReAzerbaijaniaz() != null) {
			ReAzerbaijaniaz azerbaijani = item.getReAzerbaijaniaz().cloneAzerbaijaniaz(item);
			azerbaijaniRep.save(azerbaijani);
		}
		if (item.getReBulgarianbg() != null) {
			ReBulgarianbg bulgarian = item.getReBulgarianbg().cloneBulgarianbg(item);
			bulgarianRep.save(bulgarian);
		}
		if (item.getReRussianru() != null) {
			ReRussianru russian = item.getReRussianru().cloneRussianru(item);
			russianRep.save(russian);
		}
		if (item.getReRomanianro() != null) {
			ReRomanianro romanian = item.getReRomanianro().cloneRomanianro(item);
			romanianRep.save(romanian);
		}
		if (item.getReGeorgiange() != null) {
			ReGeorgiange georgian = item.getReGeorgiange().cloneGeorgiange(item);
			georgianRep.save(georgian);
		}
		if (item.getReArabicjo() != null) {
			ReArabicjo arabicjo = item.getReArabicjo().cloneArabicjo(item);
			arabicjoRep.save(arabicjo);
		}
		if (item.getReFrenchfr() != null) {
			ReFrenchfr french = item.getReFrenchfr().cloneFrench(item);
			frenchRep.save(french);
		}
		if (item.getReAlbaniankv() != null) {
			ReAlbaniankv albanian = item.getReAlbaniankv().cloneAlbaniankv(item);
			albanianRep.save(albanian);
		}
		if (item.getReTurkmentm() != null) {
			ReTurkmentm turkmen = item.getReTurkmentm().cloneTurkmentm(item);
			turkmenRep.save(turkmen);
		}
		if (item.getReArabiceg() != null) {
			ReArabiceg arabiceg = item.getReArabiceg().cloneArabiceg(item);
			arabicegRep.save(arabiceg);
		}
		if (item.getReArabicsa() != null) {
			ReArabicsa arabicsa = item.getReArabicsa().cloneArabicsa(item);
			arabicsaRep.save(arabicsa);
		}
	}

	private boolean showWarningNotifications() {
		boolean result = true;
		try {
			Integer.valueOf(resourcenr.getValue());
			if (!tagAll.getValue()) {
				if ((tagStart.getValue().length() <= 0 || tagEnd.getValue().length() <= 0)) {
					Notification.show(LangHelper.getLocalizableMessage(LogoResConstants.EMPTY_TAGFIELDS_NOTIFICIATION),
							Type.WARNING_MESSAGE);
				} else if (Integer.valueOf(tagStart.getValue()) > Integer.valueOf(tagEnd.getValue())) {
					Notification.show(
							LangHelper.getLocalizableMessage(LogoResConstants.INCONSISTENT_TAGFIELDS_NOTIFICIATION),
							Type.WARNING_MESSAGE);
				} else {
					result = false;
				}
			} else if (resourcenr.getValue().length() <= 0) {
				Notification.show(LangHelper.getLocalizableMessage(LogoResConstants.EMPTY_RESOURCENUMBER_NOTIFICIATION),
						Type.WARNING_MESSAGE);
			} else {
				result = false;
			}

		} catch (NumberFormatException e) {
			Notification.show(LangHelper.getLocalizableMessage(LogoResConstants.NUMERIC_FIELD_NOTIFICATION),
					Type.WARNING_MESSAGE);
		}
		return result;
	}
}