package com.logo.ui.window;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.logo.LogoresMainUI;
import com.logo.data.entity.ReProjectVersion;
import com.logo.data.entity.ReResource;
import com.logo.data.entity.ReUser;
import com.logo.data.repository.ReProjectVerisonRep;
import com.logo.data.repository.ReResourceRep;
import com.logo.data.repository.ReUserRep;
import com.logo.ui.components.ButtonGenerator;
import com.logo.ui.components.SpellChecComboBox;
import com.logo.ui.components.SpellChecTextArea;
import com.logo.ui.components.SpellChecTextField;
import com.logo.ui.view.ResourceViewNew;
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
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
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

public class ResourceWindow extends Window {

	private static final long serialVersionUID = 1L;

	private final Binder<ReResource> binder = new Binder<>(ReResource.class);
	private final SpellChecTextField resourcenr = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));
	private final SpellChecTextArea description = new SpellChecTextArea(
			LangHelper.getLocalizableMessage(LogoResConstants.DESCSTR));
	private final Button save = new ButtonGenerator(LogoResConstants.SAVESTR);
	private final Button cancel = new ButtonGenerator(LogoResConstants.CANCELSTR);
	private final Button edit = new ButtonGenerator(LogoResConstants.EDITSTR);

	private final SpellChecComboBox<ResourceGroup> resourceGroupCombo = new SpellChecComboBox<>(
			LangHelper.getLocalizableMessage(LogoResConstants.RESGRPSTR));
	private final SpellChecComboBox<ResourceType> resourceTypeCombo = new SpellChecComboBox<>(
			LangHelper.getLocalizableMessage(LogoResConstants.RESTYPESTR));
	private final SpellChecComboBox<ResourceCase> resourceCaseCombo = new SpellChecComboBox<>(
			LangHelper.getLocalizableMessage(LogoResConstants.RESCASESTR));
	private final SpellChecComboBox<OwnerProduct> ownerProductCombo = new SpellChecComboBox<>(
			LangHelper.getLocalizableMessage(LogoResConstants.OWNERPRODUCT));

	private final HorizontalLayout buttonLayout = new HorizontalLayout(save, cancel);

	private final transient ReResourceRep resRepo;
	private final transient ReUserRep userRepo;
	private final transient ReUser reUser;
	private TabSheet tabsheet = new TabSheet();
	private final Label formName = new Label();
	private final transient ReProjectVerisonRep reProjectVerisonRep;
	private List<ReProjectVersion> versionList;
	private List<String> versionStringList;

	public TabSheet getTabsheet() {
		return tabsheet;
	}

	public ResourceWindow(ReResource resource, ResourceViewNew resView, boolean isNew) {
		this.resRepo = LogoresMainUI.getMrepositorycontainer().getResRepo();
		this.userRepo = LogoresMainUI.getMrepositorycontainer().getReUserRep();
		this.reUser = (ReUser) VaadinSession.getCurrent().getAttribute("user");
		this.reProjectVerisonRep = LogoresMainUI.getMrepositorycontainer().getReProjectVerisonRep();
		versionList = reProjectVerisonRep.findAll();
		versionStringList = versionList.stream().map(elem -> elem.getVersionnr()).collect(Collectors.toList());

		this.binder.setBean(resource);
		tabsheet = new TabSheet();
		tabsheet.setSizeFull();
		tabsheet.setWidth("100%");
		tabsheet.setHeight("100%");

		if (resource.getCreatedby() == 0)
			resource.setCreatedby(reUser.getId());

		resource.setModifiedby(reUser.getId());

		if (isNew)
			resource.setActive(ResourceState.ACTIVE);

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

		resourcenr.setWidth("100%");
		resourcenr.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		description.setWidth("100%");
		description.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		save.setWidth("80px");
		cancel.setWidth("80px");
		edit.setWidth("80px");

		binder.forField(resourcenr).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.RESNRNOTEMTYSTR))
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReResource::getResourcenr, ReResource::setResourcenr);

		binder.forField(description).bind(ReResource::getDescription, ReResource::setDescription);

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

		formName.setValue(resource.getResourcegroup().name() + "-" + resource.getResourcenr());

		HorizontalLayout labelLayout = new HorizontalLayout();
		labelLayout.setWidth("100%");
		labelLayout.addStyleName("card-hoverable-material-darkBlue-primary-color");
		labelLayout.setDescription(LangHelper.getLocalizableMessage(LogoResConstants.CLOSELSTR));

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
		col01.addComponent(resourcenr);
		col01.addComponent(description);
		col01.addComponent(resourceGroupCombo);
		col01.addComponent(resourceCaseCombo);
		col01.addComponent(resourceTypeCombo);
		col01.addComponent(ownerProductCombo);
		gridLayout.addComponent(col01, 0, 0, 1, 2);
		gridLayout.addComponent(buttonLayout, 1, 6);
		gridLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);

		VerticalLayout vers = new VerticalLayout();
		TwinColSelect<String> twinColl = new TwinColSelect<>();
		twinColl.setItems(versionStringList);
		List<String> selectedStringList = resource.getReProjectVersion().stream().map(elem -> elem.getVersionnr())
				.collect(Collectors.toList());
		Set<String> selectedSet = null;
		if (selectedStringList.size() > 0) {
			selectedSet = new HashSet<>(selectedStringList.subList(0, selectedStringList.size()));
			twinColl.setValue(selectedSet);
		}
		twinColl.setSizeFull();

		vers.addComponentsAndExpand(twinColl);

		save.setClickShortcut(KeyCode.ENTER);
		twinColl.setLeftColumnCaption("Version");
		twinColl.setRightColumnCaption("Selected Version");

		tabsheet.addTab(gridLayout, LangHelper.getLocalizableMessage(LogoResConstants.INFOSTR))
				.setIcon(VaadinIcons.USER);
		tabsheet.addTab(twinColl, LangHelper.getLocalizableMessage(LogoResConstants.RESVERSIONSTR))
				.setIcon(VaadinIcons.TWIN_COL_SELECT);
		mainLayout.addComponentsAndExpand(tabsheet);

		if (!isNew) {
			final SpellChecTextField createdUser = new SpellChecTextField(
					LangHelper.getLocalizableMessage(LogoResConstants.CREATED_USER_LABEL));
			final SpellChecTextField modifiedUser = new SpellChecTextField(
					LangHelper.getLocalizableMessage(LogoResConstants.MODIFIED_USER_LABEL));
			final SpellChecTextField createdTime = new SpellChecTextField(
					LangHelper.getLocalizableMessage(LogoResConstants.CREATED_TIME_LABEL));
			final SpellChecTextField modifiedTime = new SpellChecTextField(
					LangHelper.getLocalizableMessage(LogoResConstants.MODIFIED_TIME_LABEL));
			GridLayout resourceInfo = new GridLayout();
			FormLayout infoColumn = new FormLayout();
			infoColumn.setSizeFull();
			infoColumn.setSpacing(true);
			infoColumn.setMargin(true);
			infoColumn.setWidth("100%");
			infoColumn.setHeight("100%");
			createdUser.setValue(getUserName(resource.getCreatedby()));
			createdUser.setEnabled(false);
			modifiedUser.setValue(getUserName(resource.getModifiedby()));
			modifiedUser.setEnabled(false);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
			createdTime.setValue(resource.getCreatedon().format(formatter));
			createdTime.setEnabled(false);
			modifiedTime.setValue(resource.getModifiedon().format(formatter));
			modifiedTime.setEnabled(false);
			infoColumn.addComponents(createdUser, modifiedUser, createdTime, modifiedTime);
			resourceInfo.addComponent(infoColumn);
			tabsheet.addTab(resourceInfo, LangHelper.getLocalizableMessage(LogoResConstants.RESOURCE_INFORMATIONS))
					.setIcon(VaadinIcons.INFO_CIRCLE);
		}

		cancel.addClickListener(event -> close());

		save.addClickListener(event -> {
			Set<ReProjectVersion> reProjectVersions = resource.getReProjectVersion();

			Set<String> selectedSetVal = twinColl.getValue();
			Iterator<String> it = selectedSetVal.iterator();

			while (it.hasNext()) {
				String versNr = it.next();
				ReProjectVersion version = findByVersNr(versionList, versNr);

				if (!inList(reProjectVersions, version))
					reProjectVersions.add(version);
			}

			resource.setReProjectVersion(reProjectVersions);
			resRepo.save(resource);
			close();
			if (isNew) {
				String filter = resource.getResourcegroup().name() + "->" + Integer.toString(resource.getResourcenr());
				resView.createResoucePage(filter, true);
			}
		});
		setContent(mainLayout);
	}

	public static boolean inList(Set<ReProjectVersion> set1, ReProjectVersion version) {

		if (set1 == null || version == null) {
			return false;
		}
		for (int i = 0; i < set1.size(); i++) {
			ReProjectVersion vrs = set1.iterator().next();
			if (vrs.getVersionnr().equals(version.getVersionnr()))
				return true;
		}
		return false;
	}

	public ReProjectVersion findByVersNr(List<ReProjectVersion> versionList, String versNr) {
		return versionList.stream().filter(x -> x.getVersionnr().equalsIgnoreCase(versNr)).findFirst().orElse(null);
	}

	private String getUserName(int userId) {
		return userRepo.findByid(userId).getUsername();
	}

}
