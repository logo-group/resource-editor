package com.logo.ui.components;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.appreciated.material.MaterialTheme;
import com.jarektoro.responsivelayout.ResponsiveColumn;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveLayout.ContainerType;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.logo.LogoresMainUI;
import com.logo.data.entity.ReResourceGroup;
import com.logo.data.repository.ReResourceGroupRep;
import com.logo.data.repository.ReUserRep;
import com.logo.ui.view.ResourceViewNew;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.enums.ResourceCase;
import com.logo.util.enums.ResourceState;
import com.logo.util.enums.ResourceType;
import com.logo.util.search.SearchByAll;
import com.logo.util.search.SearchByResourceNr;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

public class SearchLayoutBoard extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final List<String> descList = Arrays.asList("Is empty", "Is not empty", "Is not empty or dash",
			"Contains", "Does not contain", "Begin with", "Does not begin with", "End with", "Does not end with",
			"Is equal to", "Is not equal to");

	private static List<String> resourceGroupList = new ArrayList<>();

	private SpellChecComboBox<String> resourceGroupCombo = new SpellChecComboBox<>(
			LangHelper.getLocalizableMessage(LogoResConstants.RESGRPSTR));

	private static final List<String> resourceTypeList = Arrays.asList(ResourceType.LOCALIZABLE.toString(),
			ResourceType.NONLOCALIZABLE.toString());

	private static final List<String> resourceCaseList = Arrays.asList(ResourceCase.NORESTRICTION.toString(),
			ResourceCase.LOWERCASE.toString(), ResourceCase.UPPERCASE.toString(), ResourceCase.TITLECASE.toString(),
			ResourceCase.SENTENCECASE.toString());

	private static final List<String> resourceStateList = Arrays.asList(ResourceState.ACTIVE.toString(),
			ResourceState.INACTIVE.toString());

	private static final List<String> langList = Arrays.asList(LogoResConstants.RE_TURKISHTR_NAME,
			LogoResConstants.RE_ENGLISHUS_NAME);
	private static final List<String> duringByList = Arrays.asList("Minutes", "Hours", "Days", "Weeks", "Months",
			"Years");

	private static final Set<String> DESCLIST_IGNORE = new HashSet<>();

	private static transient ReUserRep userRepo;

	@Autowired
	private ReResourceGroupRep resourceGroupRep;

	private static List<String> userList;
	private ResourceViewNew resView;

	static {
		DESCLIST_IGNORE.add(descList.get(0));
		DESCLIST_IGNORE.add(descList.get(1));
		DESCLIST_IGNORE.add(descList.get(2));
	}
	static {
		userRepo = (ReUserRep) VaadinSession.getCurrent().getAttribute("userrepo");
		userList = userRepo.findAllByUserName();
	}

	public SearchLayoutBoard(ResourceViewNew resView) {
		initialize();
		this.resView = resView;
	}

	@PostConstruct
	private void init() {
		for (ReResourceGroup group : resourceGroupRep.findAll()) {
			resourceGroupList.add(group.getName());
		}
		resourceGroupCombo = createComboBox(LogoResConstants.RESGRPSTR, resourceGroupList, resourceGroupList.get(0));
	}

	public void initialize() {
		setSizeFull();
		setWidth("100%");
		ResponsiveLayout mainLayout = new ResponsiveLayout();

		mainLayout.setContainerType(ContainerType.FLUID);
		setContent(mainLayout);

		Page.getCurrent().getStyles()
				.add(".img-rounded { border-radius: 50%; width:100% } .bg-dark-grey { background-color: #F0F0F0;}");

		ResponsiveRow rootResponsiveRow = mainLayout.addRow();
		rootResponsiveRow.setHeight("100%");
		rootResponsiveRow.setWidth("100%");

		rootResponsiveRow.withMargin(true).setHorizontalSpacing(true);

		rootResponsiveRow.withAlignment(Alignment.MIDDLE_CENTER);
		rootResponsiveRow.withMargin(true);

		ResponsiveColumn mainCol = rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12);

		ResponsiveLayout mainSectionLayout = new ResponsiveLayout();
		mainSectionLayout.setContainerType(ContainerType.FLUID);
		mainSectionLayout.setWidth("100%");
		mainSectionLayout.setHeight("100%");

		mainCol.setComponent(mainSectionLayout);

		ResponsiveRow searchResponsiveRow = mainSectionLayout.addRow();

		searchResponsiveRow.setMargin(ResponsiveRow.MarginSize.SMALL, ResponsiveLayout.DisplaySize.XS);
		searchResponsiveRow.setSpacing(ResponsiveRow.SpacingSize.SMALL, true);

		searchResponsiveRow.withAlignment(Alignment.MIDDLE_CENTER);

		SearchMemberView searchMemberView1 = new SearchMemberView("/imgSearch/002-map.png",
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYRESNRSTR));
		searchMemberView1.setStyleName(LogoResConstants.STYLE_CARD_HOVER_LIGHT);
		searchResponsiveRow.addColumn().withDisplayRules(12, 6, 6, 6).withComponent(searchMemberView1);
		searchMemberView1.addClickListener(event -> prepareSearch(
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYRESNRSTR), LogoResConstants.SEARCHBY_RESNR));

		SearchMemberView searchMemberView2 = new SearchMemberView("/imgSearch/001-eye-on-magnifying-glass.png",
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYALLSTR));
		searchMemberView2.setStyleName(LogoResConstants.STYLE_CARD_HOVER_LIGHT);
		searchResponsiveRow.addColumn().withDisplayRules(12, 6, 6, 6).withComponent(searchMemberView2);
		searchMemberView2.addClickListener(event -> prepareSearch(
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYALLSTR), LogoResConstants.SEARCHBY_ALL));

		SearchMemberView searchMemberView3 = new SearchMemberView("/imgSearch/003-location.png",
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYMODSTR));
		searchMemberView3.setStyleName(LogoResConstants.STYLE_CARD_HOVER_LIGHT);
		searchResponsiveRow.addColumn().withDisplayRules(12, 6, 6, 6).withComponent(searchMemberView3);
		searchMemberView3.addClickListener(event -> prepareSearch(
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYMODSTR), LogoResConstants.SEARCHBY_MODIFIED));

		SearchMemberView searchMemberView4 = new SearchMemberView("/imgSearch/004-find.png",
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYMODMESTR));
		searchMemberView4.setStyleName(LogoResConstants.STYLE_CARD_HOVER_LIGHT);
		searchResponsiveRow.addColumn().withDisplayRules(12, 6, 6, 6).withComponent(searchMemberView4);
		searchMemberView4.addClickListener(
				event -> prepareSearch(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYMODMESTR),
						LogoResConstants.SEARCHBY_MODIFIED_ME));

		SearchMemberView searchMemberView5 = new SearchMemberView("/imgSearch/005-analysis.png",
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYMENUSTR));
		searchMemberView5.setStyleName(LogoResConstants.STYLE_CARD_HOVER_LIGHT);
		searchResponsiveRow.addColumn().withDisplayRules(12, 6, 6, 6).withComponent(searchMemberView5);

		SearchMemberView searchMemberView6 = new SearchMemberView("/imgSearch/006-binoculars.png",
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYDBDICSTR));
		searchMemberView6.setStyleName(LogoResConstants.STYLE_CARD_HOVER_LIGHT);
		searchResponsiveRow.addColumn().withDisplayRules(12, 6, 6, 6).withComponent(searchMemberView6);

		SearchMemberView searchMemberView7 = new SearchMemberView("/imgSearch/007-loupe.png",
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYBODICSTR));
		searchMemberView7.setStyleName(LogoResConstants.STYLE_CARD_HOVER_LIGHT);
		searchResponsiveRow.addColumn().withDisplayRules(12, 6, 6, 6).withComponent(searchMemberView7);

		SearchMemberView searchMemberView8 = new SearchMemberView("/imgSearch/008-find-on-map.png",
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYBUDGETSTR));
		searchMemberView8.setStyleName(LogoResConstants.STYLE_CARD_HOVER_LIGHT);
		searchResponsiveRow.addColumn().withDisplayRules(12, 6, 6, 6).withComponent(searchMemberView8);

		SearchMemberView searchMemberView9 = new SearchMemberView("/imgSearch/009-searching.png",
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYHELPTSTR));
		searchMemberView9.setStyleName(LogoResConstants.STYLE_CARD_HOVER_LIGHT);
		searchResponsiveRow.addColumn().withDisplayRules(12, 6, 6, 6).withComponent(searchMemberView9);

		SearchMemberView searchMemberView10 = new SearchMemberView("/imgSearch/010-find-1.png",
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYMSGSTR));
		searchMemberView10.setStyleName(LogoResConstants.STYLE_CARD_HOVER_LIGHT);
		searchResponsiveRow.addColumn().withDisplayRules(12, 6, 6, 6).withComponent(searchMemberView10);

		SearchMemberView searchMemberView11 = new SearchMemberView("/imgSearch/011-lens.png",
				LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYABBRSTR));
		searchMemberView11.setStyleName(LogoResConstants.STYLE_CARD_HOVER_LIGHT);
		searchResponsiveRow.addColumn().withDisplayRules(12, 6, 6, 6).withComponent(searchMemberView11);

	}

	public void prepareSearch(String name, int searchType) {
		ResponsiveLayout mainLayout = new ResponsiveLayout();
		mainLayout.setSizeUndefined();
		mainLayout.setWidth("100%");

		setContent(mainLayout);
		mainLayout.setContainerType(ContainerType.FLUID);

		ResponsiveRow rootResponsiveRowButton = mainLayout.addRow();
		rootResponsiveRowButton.withDefaultRules(12, 12, 6, 6);

		rootResponsiveRowButton.setSpacing(true);
		rootResponsiveRowButton.setMargin(false);

		Button backButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.BACKBUTTONSTR));
		backButton.setIcon(VaadinIcons.ARROW_BACKWARD);
		backButton.addStyleName(MaterialTheme.BUTTON_BORDERLESS + " " + MaterialTheme.BUTTON_ROUND + " "
				+ MaterialTheme.BUTTON_FRIENDLY);

		HorizontalLayout titleLayout = new HorizontalLayout();
		titleLayout.addComponent(backButton);
		rootResponsiveRowButton.addColumn().withComponent(titleLayout).withDisplayRules(6, 6, 6, 6);
		String cap = "<p style=text-align:center;background-color:transparent;color:Maroon;font-size:20px;font-style:bold> "
				+ name + " </p>";
		Label title = new Label();
		title.setCaption(cap);
		title.setCaptionAsHtml(true);
		titleLayout.addComponent(title);
		titleLayout.setComponentAlignment(backButton, Alignment.MIDDLE_LEFT);
		titleLayout.setComponentAlignment(title, Alignment.TOP_CENTER);

		backButton.addClickListener(event -> initialize());
		backButton.addClickListener(event -> this.getScrollTop());
		ResponsiveRow rootResponsiveRow = mainLayout.addRow();
		rootResponsiveRow.setWidth("100%");
		rootResponsiveRow.addStyleName("example");

		switch (searchType) {
		case LogoResConstants.SEARCHBY_RESNR:
			contentForResNr(rootResponsiveRow);
			break;
		case LogoResConstants.SEARCHBY_ALL:
			contentForAll(rootResponsiveRow);
			break;
		case LogoResConstants.SEARCHBY_MODIFIED:
			contentForModifiedRes(rootResponsiveRow);
			break;
		case LogoResConstants.SEARCHBY_MODIFIED_ME:
			contentForModifiedResMe(rootResponsiveRow);
			break;
		default:
			break;
		}
	}

	private void contentForResNr(ResponsiveRow rootResponsiveRow) {
		TextField resourceNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));
		TextField resourceNr2 = createTextField("");
		SpellChecComboBox<String> descCombo = createComboBox(LogoResConstants.DESCSTR, descList, descList.get(3));
		TextField descComboText = createTextField("");

		SpellChecComboBox<String> resourceTypeCombo = createComboBox(LogoResConstants.RESTYPESTR, resourceTypeList,
				null);
		SpellChecComboBox<String> resourceCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList,
				null);
		SpellChecComboBox<String> resourceStateCombo = createComboBox(LogoResConstants.RESSTATESTR, resourceStateList,
				null);
		SwitchWithTextBox matchCase = new SwitchWithTextBox("Match Case", 0);
		Button searchButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHSTR));
		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addStyleName(
				MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);

		descCombo
				.addValueChangeListener(event -> descComboText.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		Label line1 = createSeperator();

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceNr1, resourceNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceGroupCombo, resourceTypeCombo }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { descCombo, descComboText }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceCaseCombo, resourceStateCombo }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { new Label() }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { line1 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { matchCase, new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { searchButton }));

		searchButton.addClickListener(e -> {
			SearchByResourceNr searchByResourceNr = new SearchByResourceNr.Builder().setResNrBegin(resourceNr1)
					.setResNrEnd(resourceNr2).setDescCombo(descCombo).setDescComboText(descComboText)
					.setResourceCaseCombo(resourceCaseCombo).setResourceGroupCombo(resourceGroupCombo)
					.setResourceStateCombo(resourceStateCombo).setResourceTypeCombo(resourceTypeCombo).build();
			searchByResourceNr.generateSearchParam();
			resView.getFilter().setIcon(VaadinIcons.ANGLE_DOWN);
			resView.createResoucePageForParam(searchByResourceNr.getScParam(), false);
			resView.getSearchLayoutForView().setVisible(false);
		});

	}

	private void contentForAll(ResponsiveRow rootResponsiveRow) {
		TextField resourceNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));
		TextField resourceNr2 = createTextField("");

		SwitchWithTextBox matchCase = new SwitchWithTextBox("Match Case", 0);
		Button searchButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHSTR));
		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addStyleName(
				MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);

		SpellChecComboBox<String> descCombo = createComboBox(LogoResConstants.DESCSTR, descList, descList.get(3));
		TextField descComboText = createTextField("");

		SpellChecComboBox<String> resourceTypeCombo = createComboBox(LogoResConstants.RESTYPESTR, resourceTypeList,
				null);
		SpellChecComboBox<String> resourceCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList,
				null);
		SpellChecComboBox<String> resourceStateCombo = createComboBox(LogoResConstants.RESSTATESTR, resourceStateList,
				null);

		TextField orderNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.ORDERNRSTR));
		TextField orderNr2 = createTextField("");

		TextField tagNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.TAGNRSTR));
		TextField tagNr2 = createTextField("");

		TextField levelNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.LEVELNRSTR));
		TextField levelNr2 = createTextField("");

		SpellChecComboBox<String> resourceItemCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList,
				null);
		SpellChecComboBox<String> prefixCombo = createComboBox(LogoResConstants.PREFIXSTR, descList, descList.get(3));
		SpellChecComboBox<String> infoCombo = createComboBox(LogoResConstants.INFOSTR, descList, descList.get(3));

		TextField prefixComboText = createTextField("");
		TextField infoComboText = createTextField("");

		SpellChecComboBox<String> descComboTR = createComboBox(LogoResConstants.RE_TURKISHTR_NAME, descList,
				descList.get(3));
		TextField comboTextTR = createTextField("");

		SpellChecComboBox<String> descComboEN = createComboBox(LogoResConstants.RE_ENGLISHUS_NAME, descList,
				descList.get(3));
		TextField comboTextEN = createTextField("");

		Label line1 = createSeperator();
		Label line2 = createSeperator();
		Label line3 = createSeperator();

		descCombo
				.addValueChangeListener(event -> descComboText.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		prefixCombo.addValueChangeListener(
				event -> prefixComboText.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		infoCombo
				.addValueChangeListener(event -> infoComboText.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		descComboTR
				.addValueChangeListener(event -> comboTextTR.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		descComboEN
				.addValueChangeListener(event -> comboTextEN.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceNr1, resourceNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceGroupCombo, resourceTypeCombo }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { descCombo, descComboText }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceCaseCombo, resourceStateCombo }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { line1 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { orderNr1, orderNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { tagNr1, tagNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { levelNr1, levelNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { prefixCombo, prefixComboText }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { infoCombo, infoComboText }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceItemCaseCombo, new Label("") }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { line2 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { descComboTR, comboTextTR }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { descComboEN, comboTextEN }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { line3 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { matchCase, new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { searchButton }));

		searchButton.addClickListener(e -> {
			SearchByResourceNr searchByResourceNr = new SearchByResourceNr.Builder().setResNrBegin(resourceNr1)
					.setResNrEnd(resourceNr2).setDescCombo(descCombo).setDescComboText(descComboText)
					.setResourceCaseCombo(resourceCaseCombo).setResourceGroupCombo(resourceGroupCombo)
					.setResourceStateCombo(resourceStateCombo).setResourceTypeCombo(resourceTypeCombo).build();
			SearchByAll searchByAll = new SearchByAll.Builder().setSearchByResourceNr(searchByResourceNr)
					.setOrderNrBegin(orderNr1).setOrderNrEnd(orderNr2).setTagNrBegin(tagNr1).setTagNrEnd(tagNr2)
					.setLevelNrBegin(levelNr1).setLevelNrEnd(levelNr2).setResourceItemCaseCombo(resourceItemCaseCombo)
					.setPrefixCombo(prefixCombo).setInfoCombo(infoCombo).setPrefixComboText(prefixComboText)
					.setInfoComboText(infoComboText).setDescComboTR(descComboTR).setComboTextTR(comboTextTR)
					.setDescComboEN(descComboEN).setComboTextEN(comboTextEN).build();
			searchByAll.generateSearchParam();
			resView.getFilter().setIcon(VaadinIcons.ANGLE_DOWN);
			resView.createResoucePageForParamAll(searchByAll.getSearchByResourceNr().getScParam(), false);
			resView.getSearchLayoutForView().setVisible(false);
		});

	}

	private void contentForModifiedRes(ResponsiveRow rootResponsiveRow) {
		TextField resourceNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));
		TextField resourceNr2 = createTextField("");

		SwitchWithTextBox matchCase = new SwitchWithTextBox("Match Case", 0);
		Button searchButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHSTR));
		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addStyleName(
				MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);

		SpellChecComboBox<String> descCombo = createComboBox(LogoResConstants.DESCSTR, descList, descList.get(3));
		TextField descComboText = createTextField("");

		SpellChecComboBox<String> resourceTypeCombo = createComboBox(LogoResConstants.RESTYPESTR, resourceTypeList,
				null);
		SpellChecComboBox<String> resourceCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList,
				null);
		SpellChecComboBox<String> resourceStateCombo = createComboBox(LogoResConstants.RESSTATESTR, resourceStateList,
				null);

		TextField orderNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.ORDERNRSTR));
		TextField orderNr2 = createTextField("");

		TextField tagNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.TAGNRSTR));
		TextField tagNr2 = createTextField("");

		TextField levelNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.LEVELNRSTR));
		TextField levelNr2 = createTextField("");

		SpellChecComboBox<String> resourceItemCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList,
				null);
		SpellChecComboBox<String> prefixCombo = createComboBox(LogoResConstants.PREFIXSTR, descList, descList.get(3));
		SpellChecComboBox<String> infoCombo = createComboBox(LogoResConstants.INFOSTR, descList, descList.get(3));

		TextField prefixComboText = createTextField("");
		TextField infoComboText = createTextField("");

		SpellChecComboBox<String> descComboTR = createComboBox(LogoResConstants.RE_TURKISHTR_NAME, descList,
				descList.get(3));
		TextField comboTextTR = createTextField("");

		SpellChecComboBox<String> descComboEN = createComboBox(LogoResConstants.RE_ENGLISHUS_NAME, descList,
				descList.get(3));
		TextField comboTextEN = createTextField("");

		SpellChecComboBox<String> langCombo = createComboBox("Language", langList, langList.get(0));

		TextField createdDuring = createTextField("Created during the last");
		SpellChecComboBox<String> duringByCombo = createComboBox("", duringByList, null);
		SpellChecComboBox<String> userByCombo = createComboBox("by", userList, null);

		TextField modifiedDuring = createTextField("Modified during the last");
		SpellChecComboBox<String> duringByComboMod = createComboBox("", duringByList, null);
		SpellChecComboBox<String> userByComboMod = createComboBox("by", userList, null);

		DateField modDate1 = createDateField("Modification Date");
		DateField modDate2 = createDateField("");

		Label line1 = createSeperator();
		Label line2 = createSeperator();
		Label line3 = createSeperator();
		Label line4 = createSeperator();

		descCombo
				.addValueChangeListener(event -> descComboText.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		prefixCombo.addValueChangeListener(
				event -> prefixComboText.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		infoCombo
				.addValueChangeListener(event -> infoComboText.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		descComboTR
				.addValueChangeListener(event -> comboTextTR.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		descComboEN
				.addValueChangeListener(event -> comboTextEN.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceNr1, resourceNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceGroupCombo, resourceTypeCombo }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { descCombo, descComboText }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceCaseCombo, resourceStateCombo }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { line1 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { orderNr1, orderNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { tagNr1, tagNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { levelNr1, levelNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { prefixCombo, prefixComboText }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { infoCombo, infoComboText }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceItemCaseCombo, new Label("") }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { line2 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { descComboTR, comboTextTR }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { descComboEN, comboTextEN }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { line3 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { langCombo, new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { createdDuring, duringByCombo, userByCombo }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12).withComponent(
				new SearchLayoutView(new Component[] { modifiedDuring, duringByComboMod, userByComboMod }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { modDate1, modDate2 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { line4 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { matchCase, new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { searchButton }));

	}

	private void contentForModifiedResMe(ResponsiveRow rootResponsiveRow) {

		TextField resourceNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));
		TextField resourceNr2 = createTextField("");

		SwitchWithTextBox matchCase = new SwitchWithTextBox("Match Case", 0);
		Button searchButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHSTR));
		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addStyleName(
				MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);

		SpellChecComboBox<String> descCombo = createComboBox(LogoResConstants.DESCSTR, descList, descList.get(3));
		TextField descComboText = createTextField("");

		SpellChecComboBox<String> resourceTypeCombo = createComboBox(LogoResConstants.RESTYPESTR, resourceTypeList,
				null);
		SpellChecComboBox<String> resourceCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList,
				null);
		SpellChecComboBox<String> resourceStateCombo = createComboBox(LogoResConstants.RESSTATESTR, resourceStateList,
				null);

		TextField orderNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.ORDERNRSTR));
		TextField orderNr2 = createTextField("");

		TextField tagNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.TAGNRSTR));
		TextField tagNr2 = createTextField("");

		TextField levelNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.LEVELNRSTR));
		TextField levelNr2 = createTextField("");

		SpellChecComboBox<String> resourceItemCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList,
				null);
		SpellChecComboBox<String> prefixCombo = createComboBox(LogoResConstants.PREFIXSTR, descList, descList.get(3));
		SpellChecComboBox<String> infoCombo = createComboBox(LogoResConstants.INFOSTR, descList, descList.get(3));

		TextField prefixComboText = createTextField("");
		TextField infoComboText = createTextField("");

		SpellChecComboBox<String> descComboTR = createComboBox(LogoResConstants.RE_TURKISHTR_NAME, descList,
				descList.get(3));
		TextField comboTextTR = createTextField("");

		SpellChecComboBox<String> descComboEN = createComboBox(LogoResConstants.RE_ENGLISHUS_NAME, descList,
				descList.get(3));
		TextField comboTextEN = createTextField("");

		SpellChecComboBox<String> langCombo = createComboBox("Language", langList, langList.get(0));

		TextField createdDuring = createTextField("Created by me during the last");
		SpellChecComboBox<String> duringByCombo = createComboBox("", duringByList, null);

		TextField modifiedDuring = createTextField("Modified by me during the last");
		SpellChecComboBox<String> duringByComboMod = createComboBox("", duringByList, null);

		DateField modDate1 = createDateField("Modification Date");
		DateField modDate2 = createDateField("");

		Label line1 = createSeperator();
		Label line2 = createSeperator();
		Label line3 = createSeperator();
		Label line4 = createSeperator();

		descCombo
				.addValueChangeListener(event -> descComboText.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		prefixCombo.addValueChangeListener(
				event -> prefixComboText.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		infoCombo
				.addValueChangeListener(event -> infoComboText.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		descComboTR
				.addValueChangeListener(event -> comboTextTR.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));
		descComboEN
				.addValueChangeListener(event -> comboTextEN.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceNr1, resourceNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceGroupCombo, resourceTypeCombo }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { descCombo, descComboText }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceCaseCombo, resourceStateCombo }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { line1 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { orderNr1, orderNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { tagNr1, tagNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { levelNr1, levelNr2 }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { prefixCombo, prefixComboText }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { infoCombo, infoComboText }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { resourceItemCaseCombo, new Label("") }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { line2 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { descComboTR, comboTextTR }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 6, 6)
				.withComponent(new SearchLayoutView(new Component[] { descComboEN, comboTextEN }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { line3 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { langCombo, new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { createdDuring, duringByCombo }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { modifiedDuring, duringByComboMod }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { modDate1, modDate2 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { line4 }));

		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { matchCase, new Label("") }));
		rootResponsiveRow.addColumn().withDisplayRules(12, 12, 12, 12)
				.withComponent(new SearchLayoutView(new Component[] { searchButton }));

	}

	TextField createTextField(String caption) {
		String cap = "";
		if ("".contains(caption)) {
			caption = ".";
			cap = "<p style=background-color:transparent;color:white;font-size:16px;font-style:bold> " + caption
					+ " </p>";
		} else {
			cap = "<p style=background-color:transparent;color:darkBlue;font-size:16px;font-style:bold> " + caption
					+ " </p>";
		}
		// color:#fab331
		SpellChecTextField textField = new SpellChecTextField(cap);
		textField.setCaptionAsHtml(true);
		textField.setWidth("100%");
		textField.setHeight("25px");
		textField.setSizeFull();
		return textField;
	}

	<T> SpellChecComboBox<T> createComboBox(String name, Collection<T> items, T value) {
		String cap = "";
		String nameLoc = LangHelper.getLocalizableMessage(name);
		if ("".contains(nameLoc)) {
			nameLoc = ".";
			cap = "<p style=background-color:transparent;color:white;font-size:16px;font-style:bold> " + nameLoc
					+ " </p>";
		} else {
			cap = "<p style=background-color:transparent;color:darkBlue;font-size:16px;font-style:bold> " + nameLoc
					+ " </p>";
		}

		SpellChecComboBox<T> combo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(cap), true);
		combo.setDescription(LangHelper.getLocalizableMessage(name));
		combo.setPlaceholder(LangHelper.getLocalizableMessage(name));
		combo.setSizeFull();
		if (items != null)
			combo.setItems(items);
		combo.setWidth("100%");
		combo.setValue(value);
		combo.setEmptySelectionAllowed(false);

		return combo;
	}

	DateField createDateField(String caption) {
		/**
		 * String cap = "<b style=text-align:left;margin-right:" + 0 +
		 * "px;color:#fab331;text-decoration:none;text-decoration-color:#fab331>" +
		 * caption + "</b>";
		 **/
		DateField dateField = new DateField(caption);
		dateField.setCaptionAsHtml(true);
		// dateField.addStyleName(MaterialTheme.TEXTFIELD_FLOATING);
		dateField.setValue(LocalDate.now());
		dateField.setDateFormat("dd-MM-yyyy");
		Locale trlocale = Locale.forLanguageTag(LogoresMainUI.getMrepositorycontainer().getAppLocale().getLanguage());
		dateField.setLocale(trlocale);
		dateField.setWidth("100%");
		dateField.setHeight("25px");
		dateField.addValueChangeListener(
				event -> Notification.show("Value changed:", String.valueOf(event.getValue()), Type.HUMANIZED_MESSAGE));
		return dateField;
	}

	Label createSeperator() {
		Label hSep = new Label();
		hSep.addStyleName("horizontal-separator");
		hSep.setHeight("2px");
		hSep.setWidth("100%");
		return hSep;

	}

	public static class SearchLayoutView extends Panel {
		private static final long serialVersionUID = 1L;

		public SearchLayoutView(Component[] comp) {
			ResponsiveLayout responsiveLayout = new ResponsiveLayout();
			responsiveLayout.setContainerType(ContainerType.FLUID);
			responsiveLayout.setWidth("100%");
			responsiveLayout.setHeight("100%");

			ResponsiveRow responsiveRow = new ResponsiveRow();
			responsiveRow.setWidth("100%");
			responsiveRow.setHeight("100%");

			HorizontalLayout h1 = new HorizontalLayout();
			h1.setWidth("100%");
			h1.setHeight("100%");
			// h1.addStyleName(MaterialTheme.CARD_5);
			for (int i = 0; i < comp.length; i++) {
				if (comp[i] instanceof SwitchWithTextBox)
					h1.addComponent(comp[i]);
				else
					h1.addComponentsAndExpand(comp[i]);
				ResponsiveColumn responsiveColumn = new ResponsiveColumn(12, 12, 12, 12);
				responsiveColumn.setWidth("100%");
				responsiveColumn.setHeight("100%");

				responsiveColumn.setComponent(h1);
				responsiveRow.addColumn(responsiveColumn);
			}

			responsiveLayout.addRow(responsiveRow);
			setContent(responsiveLayout);
		}
	}

	public static class SearchMemberView extends Panel {

		private static final long serialVersionUID = 1L;

		public SearchMemberView(String imageName, String searchName) {

			ResponsiveLayout responsiveLayout = new ResponsiveLayout();
			responsiveLayout.setContainerType(ContainerType.FLUID);
			responsiveLayout.setWidth("100%");
			responsiveLayout.setHeight("100%");

			Resource res = new ClassResource(imageName);

			Image image = new Image(null, res);
			image.setStyleName("img-rounded");
			image.setWidth("100%");

			Label searchLabel = new Label(searchName);
			searchLabel.addStyleName(MaterialTheme.LABEL_COLORED);
			searchLabel.addStyleName(MaterialTheme.LABEL_BOLD);

			Label htmlLabel = new Label(
					"<p style=background-color:transparent;color:darkblue;font-size:20px;font-style:bold> " + searchName
							+ " </p>");
			htmlLabel.setContentMode(ContentMode.HTML);

			ResponsiveRow responsiveRow = new ResponsiveRow();

			responsiveRow.setMargin(ResponsiveRow.MarginSize.SMALL, ResponsiveLayout.DisplaySize.XS);
			responsiveRow.setSpacing(ResponsiveRow.SpacingSize.SMALL, true);

			ResponsiveColumn imageCol = new ResponsiveColumn(2, 2, 2, 2);
			imageCol.setComponent(image);
			ResponsiveColumn titleCol = new ResponsiveColumn(3, 3, 3, 3);
			titleCol.setComponent(htmlLabel);

			responsiveRow.addColumn(imageCol);
			responsiveRow.addColumn(titleCol);
			responsiveRow.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

			responsiveLayout.addRow(responsiveRow);
			setContent(responsiveLayout);

		}
	}
}
