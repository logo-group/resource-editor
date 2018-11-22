package com.logo.components;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.github.appreciated.material.MaterialTheme;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveLayout.ContainerType;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.logo.LogoresMainUI;
import com.logo.ResourceViewNew;
import com.logo.data.ReUserRep;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.ResourceCase;
import com.logo.util.ResourceGroup;
import com.logo.util.ResourceState;
import com.logo.util.ResourceType;
import com.logo.util.SearchByAll;
import com.logo.util.SearchByResourceNr;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class SearchLayout extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final List<String> descList = Arrays.asList("Is empty", "Is not empty", "Is not empty or dash",
			"Contains", "Does not contain", "Begin with", "Does not begin with", "End with", "Does not end with",
			"Is equal to", "Is not equal to");

	private static final List<String> resourceGroupList = Arrays.asList(ResourceGroup.UN.toString(),
			ResourceGroup.HR.toString(), ResourceGroup.UNRP.toString(), ResourceGroup.HRRP.toString(),
			ResourceGroup.SS.toString(), ResourceGroup.HELP.toString(), ResourceGroup.MISC.toString());

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

	private Panel  contentLayout = new Panel();
	private VerticalLayout contentForResNr = new VerticalLayout();
	private VerticalLayout contentForAll = new VerticalLayout();
	private VerticalLayout contentForModified = new VerticalLayout();
	private VerticalLayout contentForModifiedResMe = new VerticalLayout();

	private static transient ReUserRep userRepo;
	private static List<String> userList;
	private ResourceViewNew resView;

	private static final Set<String> DESCLIST_IGNORE = new HashSet<>();
	static {
		DESCLIST_IGNORE.add(descList.get(0));
		DESCLIST_IGNORE.add(descList.get(1));
		DESCLIST_IGNORE.add(descList.get(2));
	}
	static {
		userRepo = (ReUserRep) VaadinSession.getCurrent().getAttribute("userrepo");
		userList = userRepo.findAllByUserName();
	}

	public SearchLayout(ResourceViewNew resView) {
		
		setSizeFull();
		setWidth("100%");
		setHeight("100%");
		this.resView = resView;
		
		ResponsiveLayout mainLayout = new ResponsiveLayout();
		mainLayout.setContainerType(ContainerType.FLUID);
		setContent(mainLayout);

		
		ResponsiveRow rootRow = mainLayout.addRow().withDefaultRules(12, 12, 12, 12);
		rootRow.setSizeFull();
		rootRow.setWidth("100%");
		

		addStyleName("in-slide-fade");
		VerticalLayout buttonLayout = new VerticalLayout();
		buttonLayout.setSizeFull();
		buttonLayout.setWidth("100%");
		buttonLayout.setHeight("100%");

		//contentLayout.setSizeFull();
		contentLayout.setWidth("100%");
		createContents();

		//buttonLayout.addStyleName(LogoResConstants.STYLE_CARD_BLACK);
		//contentLayout.addStyleName(LogoResConstants.STYLE_CARD_BLACK);
		
		VerticalLayout btn = createButtonLayout();
		buttonLayout.addComponentsAndExpand(btn);
		contentLayout.setContent(contentForResNr);
		
		rootRow.addColumn().withDisplayRules(12, 12, 3, 3).withComponent(buttonLayout);
		rootRow.addColumn().withDisplayRules(12, 12, 9, 9).withComponent(contentLayout);

	}

	public VerticalLayout createButtonLayout() {
		VerticalLayout vert = new VerticalLayout();
		vert.setSizeFull();
		vert.setMargin(true);
		vert.setSpacing(true);
		vert.setHeight("100%");
		vert.setWidth("100%");
		Button btn1 = createMenuButton(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYRESNRSTR));
		Button btn2 = createMenuButton(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYALLSTR));
		Button btn3 = createMenuButton(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYMODSTR));
		Button btn4 = createMenuButton(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYMODMESTR));
		Button btn5 = createMenuButton(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYMENUSTR));
		Button btn6 = createMenuButton(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYDBDICSTR));
		Button btn7 = createMenuButton(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYBODICSTR));
		Button btn8 = createMenuButton(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYBUDGETSTR));
		Button btn9 = createMenuButton(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYHELPTSTR));
		Button btn10 = createMenuButton(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYMSGSTR));
		Button btn11 = createMenuButton(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYABBRSTR));

		vert.addComponentsAndExpand(btn1);
		vert.addComponentsAndExpand(btn2);
		vert.addComponentsAndExpand(btn3);
		vert.addComponentsAndExpand(btn4);
		vert.addComponentsAndExpand(btn5);
		vert.addComponentsAndExpand(btn6);
		vert.addComponentsAndExpand(btn7);
		vert.addComponentsAndExpand(btn8);
		vert.addComponentsAndExpand(btn9);
		vert.addComponentsAndExpand(btn10);
		vert.addComponentsAndExpand(btn11);

		
		btn1.addClickListener(e -> {
			//contentLayout.removeAllComponents();
			contentLayout.setContent(contentForResNr);
		});

		btn2.addClickListener(e -> {
			//contentLayout.removeAllComponents();
			contentLayout.setContent(contentForAll);
		});
		btn3.addClickListener(e -> {
			//contentLayout.removeAllComponents();
			contentLayout.setContent(contentForModified);
		});
		btn4.addClickListener(e -> {
			//contentLayout.removeAllComponents();
			contentLayout.setContent(contentForModifiedResMe);
		});
		
		btn9.addClickListener(e -> {
			contentForHelpDocs();
		});

		btn10.addClickListener(e -> {
			contentForMessage();
		});

		return vert;
	}

	private Button createMenuButton(String caption) {
		/**
		 * #fab331-orange #006940-green String cap = "<b style=
		 * text-align:justify;color:#fab331;text-decoration:none;text-decoration-color:#fab331>
		 * " + caption + "</b>";
		 **/
		Button btn = new Button(caption);
		btn.addStyleName(MaterialTheme.BUTTON_BORDERLESS+ " "+ MaterialTheme.BUTTON_ROUND+ " "+ MaterialTheme.BUTTON_LARGE+ " " + LogoResConstants.STYLE_CUSTOM_ORANGE);
		btn.setSizeFull();
		btn.setWidth("100%");
		return btn;

	}

	private void createContents() {
		contentForResNr = createContent(LogoResConstants.SEARCHBY_RESNR);
		contentForAll = createContent(LogoResConstants.SEARCHBY_ALL);
		contentForModified = createContent(LogoResConstants.SEARCHBY_MODIFIED);
		contentForModifiedResMe = createContent(LogoResConstants.SEARCHBY_MODIFIED_ME);
	}

	private VerticalLayout createContent(int searchType) {
		VerticalLayout content1 = new VerticalLayout();
		content1.addStyleName(LogoResConstants.STYLE_CARD_BLACK);
		content1.setSizeFull();
		content1.setWidth("100%");
		content1.setHeight("100%");
		switch (searchType) {
		case LogoResConstants.SEARCHBY_RESNR:
			contentForResNr(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYRESNRSTR), content1);
			break;
		case LogoResConstants.SEARCHBY_ALL:
			contentForAll(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYALLSTR), content1);
			break;
		case LogoResConstants.SEARCHBY_MODIFIED:
			contentForModifiedRes(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYMODSTR), content1);
			break;
		case LogoResConstants.SEARCHBY_MODIFIED_ME:
			contentForModifiedResMe(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHBYMODMESTR), content1);
			break;
		case LogoResConstants.SEARCHBY_MENU:
			break;
		case LogoResConstants.SEARCHBY_DBDIC:
			break;
		case LogoResConstants.SEARCHBY_BODIC:
			break;
		case LogoResConstants.SEARCHBY_BUDGET:
			break;
		case LogoResConstants.SEARCHBY_HELP:
			break;
		case LogoResConstants.SEARCHBY_MESSAGE:
			contentForMessage();
			break;
		case LogoResConstants.SEARCHBY_ABBREVIATIONS:
			break;
		default:
			break;
		}
		return content1;
	}

	private void contentForResNr(String caption, VerticalLayout content) {
		VerticalLayout mainLayout = new VerticalLayout();

		Label label = new Label(caption);
		label.setSizeUndefined();

		mainLayout.addComponent(label);
		mainLayout.setComponentAlignment(label, Alignment.TOP_CENTER);

		HorizontalLayout contentForResNrL = new HorizontalLayout();
		contentForResNrL.addStyleName(LogoResConstants.STYLE_CARD_BLACK);
		contentForResNrL.setWidth("100%");
		mainLayout.addComponent(contentForResNrL);

		GridLayout searchGrid = new GridLayout(5, 10);
		searchGrid.setSizeFull();
		searchGrid.setWidth("100%");
		searchGrid.addStyleName(LogoResConstants.STYLE_GRID);

		searchGrid.setSpacing(false);
		searchGrid.setMargin(false);

		TextField resourceNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));
		TextField resourceNr2 = createTextField("");

		Button searchButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHSTR));
		//searchButton.setWidth("100%");
		//searchButton.setSizeFull();

		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addStyleName(
				MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);

		SpellChecComboBox<String> descCombo = createComboBox(LogoResConstants.DESCSTR, descList, descList.get(3));
		TextField descComboText = createTextField("");

		SpellChecComboBox<String> resourceGroupCombo = createComboBox(LogoResConstants.RESGRPSTR, resourceGroupList,
				resourceGroupList.get(0));
		SpellChecComboBox<String> resourceTypeCombo = createComboBox(LogoResConstants.RESTYPESTR, resourceTypeList, null);
		SpellChecComboBox<String> resourceCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList, null);
		SpellChecComboBox<String> resourceStateCombo = createComboBox(LogoResConstants.RESSTATESTR, resourceStateList, null);

		Label hSep0 = createSeperator();

		FormLayout col01 = new FormLayout();
		col01.setSizeUndefined();
		col01.setSpacing(false);
		col01.setMargin(false);
		col01.setWidth("100%");
		col01.addComponent(resourceNr1);
		col01.addComponent(descCombo);
		col01.addComponent(resourceGroupCombo);
		col01.addComponent(resourceTypeCombo);
		col01.addComponent(resourceCaseCombo);
		col01.addComponent(resourceStateCombo);
		col01.setComponentAlignment(resourceNr1, Alignment.TOP_LEFT);
		col01.setComponentAlignment(descCombo, Alignment.TOP_LEFT);
		col01.setComponentAlignment(resourceGroupCombo, Alignment.TOP_LEFT);
		col01.setComponentAlignment(resourceTypeCombo, Alignment.TOP_LEFT);
		col01.setComponentAlignment(resourceCaseCombo, Alignment.TOP_LEFT);
		col01.setComponentAlignment(resourceStateCombo, Alignment.TOP_LEFT);

		FormLayout col11 = new FormLayout();
		col11.setSizeUndefined();
		col11.setSpacing(false);
		col11.setMargin(false);
		col11.setWidth("100%");
		col11.addComponent(resourceNr2);
		col11.addComponent(descComboText);
		descComboText.setWidth("150%");
		col11.setComponentAlignment(resourceNr2, Alignment.TOP_LEFT);
		col11.setComponentAlignment(descComboText, Alignment.TOP_RIGHT);

		searchGrid.addComponent(hSep0, 0, 0, 4, 0);
		searchGrid.addComponent(col01, 0, 1, 1, 1);
		searchGrid.addComponent(col11, 2, 1, 3, 1);

		Label hSep1 = createSeperator();
		searchGrid.addComponent(hSep1, 0, 7, 4, 7);

		SwitchWithTextBox matchCase = new SwitchWithTextBox("Match Case", 0);
		matchCase.setHeight("25px");

		FormLayout col04 = new FormLayout();
		col04.setSizeUndefined();
		col04.setSpacing(false);
		col04.setMargin(false);
		col04.setWidth("100%");
		col04.addComponent(matchCase);
		col04.setComponentAlignment(matchCase, Alignment.TOP_LEFT);

		searchGrid.addComponent(col04, 0, 8, 1, 8);

		descCombo
				.addValueChangeListener(event -> descComboText.setVisible(!DESCLIST_IGNORE.contains(event.getValue())));

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

		resourceNr1.setTabIndex(1);
		resourceNr2.setTabIndex(2);
		descCombo.setTabIndex(3);
		descComboText.setTabIndex(4);
		resourceGroupCombo.setTabIndex(5);
		resourceTypeCombo.setTabIndex(6);
		resourceCaseCombo.setTabIndex(7);
		resourceStateCombo.setTabIndex(8);
		matchCase.setTabIndex(9);
		searchButton.setTabIndex(10);

		contentForResNrL.addComponents(searchGrid);
		mainLayout.addComponent(searchButton);
		mainLayout.setComponentAlignment(searchButton, Alignment.BOTTOM_RIGHT);
		content.addComponents(mainLayout);
	}

	private void contentForAll(String caption, VerticalLayout content) {
		VerticalLayout mainLayout = new VerticalLayout();

		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);

		Label label = new Label(caption);
		label.setSizeUndefined();

		mainLayout.addComponent(label);
		mainLayout.setComponentAlignment(label, Alignment.TOP_CENTER);

		HorizontalLayout contentForAllL = new HorizontalLayout();
		contentForAllL.addStyleName(LogoResConstants.STYLE_CARD_BLACK);
		contentForAllL.setWidth("100%");
		mainLayout.addComponent(contentForAllL);

		GridLayout searchGrid = new GridLayout(5, 20);
		searchGrid.setSizeFull();
		searchGrid.setWidth("100%");
		searchGrid.addStyleName(LogoResConstants.STYLE_GRID);

		searchGrid.setSpacing(false);
		searchGrid.setMargin(false);

		TextField resourceNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));
		TextField resourceNr2 = createTextField("");

		Button searchButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHSTR));

		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addStyleName(
				MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);

		SpellChecComboBox<String> descCombo = createComboBox(LogoResConstants.DESCSTR, descList, descList.get(3));
		TextField descComboText = createTextField("");

		SpellChecComboBox<String> resourceGroupCombo = createComboBox(LogoResConstants.RESGRPSTR, resourceGroupList,
				resourceGroupList.get(0));
		SpellChecComboBox<String> resourceTypeCombo = createComboBox(LogoResConstants.RESTYPESTR, resourceTypeList, null);
		SpellChecComboBox<String> resourceCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList, null);
		SpellChecComboBox<String> resourceStateCombo = createComboBox(LogoResConstants.RESSTATESTR, resourceStateList, null);

		searchGrid.addComponent(createSeperator(), 0, 0, 4, 0);

		FormLayout colLeft = new FormLayout();
		colLeft.setSizeUndefined();
		colLeft.setSpacing(false);
		colLeft.setMargin(false);
		colLeft.setWidth("100%");
		colLeft.addComponent(resourceNr1);
		colLeft.addComponent(descCombo);
		colLeft.addComponent(resourceGroupCombo);
		colLeft.addComponent(resourceTypeCombo);
		colLeft.addComponent(resourceCaseCombo);
		colLeft.addComponent(resourceStateCombo);
		colLeft.setComponentAlignment(resourceNr1, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(descCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceGroupCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceTypeCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceCaseCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceStateCombo, Alignment.TOP_LEFT);

		FormLayout colRight = new FormLayout();
		colRight.setSizeUndefined();
		colRight.setSpacing(false);
		colRight.setMargin(false);
		colRight.setWidth("100%");
		colRight.addComponent(resourceNr2);
		colRight.addComponent(descComboText);
		descComboText.setWidth("150%");
		colRight.setComponentAlignment(resourceNr2, Alignment.TOP_LEFT);
		colRight.setComponentAlignment(descComboText, Alignment.TOP_RIGHT);

		
		searchGrid.addComponent(colLeft, 0, 1, 1, 1);
		searchGrid.addComponent(colRight, 2, 1, 3, 1);

		searchGrid.addComponent(createSeperator(), 0, 7, 4, 7);

		TextField orderNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.ORDERNRSTR));
		TextField orderNr2 = createTextField("");

		TextField tagNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.TAGNRSTR));
		TextField tagNr2 = createTextField("");

		TextField levelNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.LEVELNRSTR));
		TextField levelNr2 = createTextField("");

		SpellChecComboBox<String> resourceItemCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList, null);
		SpellChecComboBox<String> prefixCombo = createComboBox(LogoResConstants.PREFIXSTR, descList, descList.get(3));
		SpellChecComboBox<String> infoCombo = createComboBox(LogoResConstants.INFOSTR, descList, descList.get(3));

		TextField prefixComboText = createTextField("");
		TextField infoComboText = createTextField("");

		SpellChecComboBox<String> descComboTR = createComboBox(LogoResConstants.RE_TURKISHTR_NAME, descList, descList.get(3));
		TextField comboTextTR = createTextField("");

		SpellChecComboBox<String> descComboEN = createComboBox(LogoResConstants.RE_ENGLISHUS_NAME, descList, descList.get(3));
		TextField comboTextEN = createTextField("");


		colLeft.addComponent(createSeperator());
		colLeft.addComponent(orderNr1);
		colLeft.addComponent(tagNr1);
		colLeft.addComponent(levelNr1);
		colLeft.addComponent(resourceItemCaseCombo);
		colLeft.addComponent(prefixCombo);
		colLeft.addComponent(infoCombo);
		colLeft.setComponentAlignment(orderNr1, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(tagNr1, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(levelNr1, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceItemCaseCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(prefixCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(infoCombo, Alignment.TOP_LEFT);

		colRight.addComponent(new Label());
		colRight.addComponent(new Label());
		colRight.addComponent(new Label());
		colRight.addComponent(new Label());
		colRight.addComponent(createSeperator());

		colRight.addComponent(orderNr2);
		colRight.addComponent(tagNr2);
		colRight.addComponent(levelNr2);
		colRight.addComponent(new Label());
		colRight.addComponent(prefixComboText);
		colRight.addComponent(infoComboText);
		colRight.setComponentAlignment(orderNr2, Alignment.TOP_RIGHT);
		colRight.setComponentAlignment(tagNr2, Alignment.TOP_RIGHT);
		colRight.setComponentAlignment(levelNr2, Alignment.TOP_RIGHT);
		colRight.setComponentAlignment(prefixComboText, Alignment.TOP_RIGHT);
		colRight.setComponentAlignment(infoComboText, Alignment.TOP_RIGHT);

		colLeft.addComponent(createSeperator());
		colLeft.addComponent(descComboTR);
		colLeft.addComponent(descComboEN);
		colLeft.setComponentAlignment(descComboTR, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(descComboEN, Alignment.TOP_LEFT);

		colRight.addComponent(createSeperator());
		colRight.addComponent(comboTextTR);
		colRight.addComponent(comboTextEN);
		colRight.setComponentAlignment(comboTextTR, Alignment.TOP_LEFT);
		colRight.setComponentAlignment(comboTextEN, Alignment.TOP_LEFT);

		SwitchWithTextBox matchCase = new SwitchWithTextBox("Match Case", 0);
		matchCase.setHeight("25px");

		FormLayout col04 = new FormLayout();
		col04.setSizeUndefined();
		col04.setSpacing(false);
		col04.setMargin(false);
		col04.setWidth("100%");
		col04.addComponent(matchCase);
		col04.setComponentAlignment(matchCase, Alignment.TOP_LEFT);

		searchGrid.addComponent(col04, 0, 19, 1, 19);

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

		resourceNr1.setTabIndex(1);
		resourceNr2.setTabIndex(2);
		descCombo.setTabIndex(3);
		descComboText.setTabIndex(4);
		resourceGroupCombo.setTabIndex(5);
		resourceTypeCombo.setTabIndex(6);
		resourceCaseCombo.setTabIndex(7);
		resourceStateCombo.setTabIndex(8);

		orderNr1.setTabIndex(11);
		orderNr2.setTabIndex(12);
		tagNr1.setTabIndex(13);
		tagNr2.setTabIndex(14);
		levelNr1.setTabIndex(15);
		levelNr2.setTabIndex(16);
		resourceItemCaseCombo.setTabIndex(17);
		prefixCombo.setTabIndex(18);
		prefixComboText.setTabIndex(19);
		infoCombo.setTabIndex(20);
		infoComboText.setTabIndex(21);
		descComboTR.setTabIndex(22);
		descComboEN.setTabIndex(23);
		comboTextTR.setTabIndex(24);

		matchCase.setTabIndex(25);
		searchButton.setTabIndex(26);

		contentForAllL.addComponents(searchGrid);
		mainLayout.addComponent(searchButton);
		mainLayout.setComponentAlignment(searchButton, Alignment.BOTTOM_RIGHT);
		content.addComponents(mainLayout);
	}

	private void contentForModifiedRes(String caption, VerticalLayout content) {
		VerticalLayout mainLayout = new VerticalLayout();

		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);

		Label label = new Label(caption);
		label.setSizeUndefined();

		mainLayout.addComponent(label);
		mainLayout.setComponentAlignment(label, Alignment.TOP_CENTER);

		HorizontalLayout contentForAllL = new HorizontalLayout();
		contentForAllL.addStyleName(LogoResConstants.STYLE_CARD_BLACK);
		contentForAllL.setWidth("100%");
		mainLayout.addComponent(contentForAllL);

		GridLayout searchGrid = new GridLayout(5, 26);
		searchGrid.setSizeFull();
		searchGrid.setWidth("100%");
		searchGrid.addStyleName(LogoResConstants.STYLE_GRID);

		searchGrid.setSpacing(false);
		searchGrid.setMargin(false);

		TextField resourceNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));
		TextField resourceNr2 = createTextField("");

		Button searchButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHSTR));
		//searchButton.setWidth("100%");
		//searchButton.setSizeFull();

		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addStyleName(
				MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);

		SpellChecComboBox<String> descCombo = createComboBox(LogoResConstants.DESCSTR, descList, descList.get(3));
		TextField descComboText = createTextField("");

		SpellChecComboBox<String> resourceGroupCombo = createComboBox(LogoResConstants.RESGRPSTR, resourceGroupList,
				resourceGroupList.get(0));
		SpellChecComboBox<String> resourceTypeCombo = createComboBox(LogoResConstants.RESTYPESTR, resourceTypeList, null);
		SpellChecComboBox<String> resourceCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList, null);
		SpellChecComboBox<String> resourceStateCombo = createComboBox(LogoResConstants.RESSTATESTR, resourceStateList, null);


		FormLayout colLeft = new FormLayout();
		colLeft.setSizeUndefined();
		colLeft.setSpacing(false);
		colLeft.setMargin(false);
		colLeft.setWidth("100%");
		colLeft.addComponent(resourceNr1);
		colLeft.addComponent(descCombo);
		colLeft.addComponent(resourceGroupCombo);
		colLeft.addComponent(resourceTypeCombo);
		colLeft.addComponent(resourceCaseCombo);
		colLeft.addComponent(resourceStateCombo);
		colLeft.setComponentAlignment(resourceNr1, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(descCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceGroupCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceTypeCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceCaseCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceStateCombo, Alignment.TOP_LEFT);

		FormLayout colRight = new FormLayout();
		colRight.setSizeUndefined();
		colRight.setSpacing(false);
		colRight.setMargin(false);
		colRight.setWidth("100%");
		colRight.addComponent(resourceNr2);
		colRight.addComponent(descComboText);
		descComboText.setWidth("150%");
		colRight.setComponentAlignment(resourceNr2, Alignment.TOP_LEFT);
		colRight.setComponentAlignment(descComboText, Alignment.TOP_RIGHT);

		searchGrid.addComponent(createSeperator(), 0, 0, 4, 0);
		searchGrid.addComponent(colLeft, 0, 1, 1, 1);
		searchGrid.addComponent(colRight, 2, 1, 3, 1);

		searchGrid.addComponent(createSeperator(), 0, 7, 4, 7);

		TextField orderNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.ORDERNRSTR));
		TextField orderNr2 = createTextField("");

		TextField tagNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.TAGNRSTR));
		TextField tagNr2 = createTextField("");

		TextField levelNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.LEVELNRSTR));
		TextField levelNr2 = createTextField("");

		SpellChecComboBox<String> resourceItemCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList, null);
		SpellChecComboBox<String> prefixCombo = createComboBox(LogoResConstants.PREFIXSTR, descList, descList.get(3));
		SpellChecComboBox<String> infoCombo = createComboBox(LogoResConstants.INFOSTR, descList, descList.get(3));

		TextField prefixComboText = createTextField("");
		TextField infoComboText = createTextField("");

		SpellChecComboBox<String> descComboTR = createComboBox(LogoResConstants.RE_TURKISHTR_NAME, descList, descList.get(3));
		TextField comboTextTR = createTextField("");

		SpellChecComboBox<String> descComboEN = createComboBox(LogoResConstants.RE_ENGLISHUS_NAME, descList, descList.get(3));
		TextField comboTextEN = createTextField("");

		colLeft.addComponent(createSeperator());
		colLeft.addComponent(orderNr1);
		colLeft.addComponent(tagNr1);
		colLeft.addComponent(levelNr1);
		colLeft.addComponent(resourceItemCaseCombo);
		colLeft.addComponent(prefixCombo);
		colLeft.addComponent(infoCombo);
		colLeft.setComponentAlignment(orderNr1, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(tagNr1, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(levelNr1, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceItemCaseCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(prefixCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(infoCombo, Alignment.TOP_LEFT);

		colRight.addComponent(new Label());
		colRight.addComponent(new Label());
		colRight.addComponent(new Label());
		colRight.addComponent(new Label());
		colRight.addComponent(createSeperator());

		colRight.addComponent(orderNr2);
		colRight.addComponent(tagNr2);
		colRight.addComponent(levelNr2);
		colRight.addComponent(new Label());
		colRight.addComponent(prefixComboText);
		colRight.addComponent(infoComboText);
		colRight.setComponentAlignment(orderNr2, Alignment.TOP_RIGHT);
		colRight.setComponentAlignment(tagNr2, Alignment.TOP_RIGHT);
		colRight.setComponentAlignment(levelNr2, Alignment.TOP_RIGHT);
		colRight.setComponentAlignment(prefixComboText, Alignment.TOP_RIGHT);
		colRight.setComponentAlignment(infoComboText, Alignment.TOP_RIGHT);

		colLeft.addComponent(createSeperator());
		colLeft.addComponent(descComboTR);
		colLeft.addComponent(descComboEN);
		colLeft.setComponentAlignment(descComboTR, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(descComboEN, Alignment.TOP_LEFT);

		colRight.addComponent(createSeperator());
		colRight.addComponent(comboTextTR);
		colRight.addComponent(comboTextEN);
		colRight.setComponentAlignment(comboTextTR, Alignment.TOP_LEFT);
		colRight.setComponentAlignment(comboTextEN, Alignment.TOP_LEFT);


		SpellChecComboBox<String> langCombo = createComboBox(LogoResConstants.SELECTLANG, langList, langList.get(0));

		TextField createdDuring = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.CREATEDBYDURINGSTR));
		SpellChecComboBox<String> duringByCombo = createComboBox("", duringByList, null);
		SpellChecComboBox<String> userByCombo = createComboBox("by", userList, null);

		TextField modifiedDuring = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.MODBYDURINGSTR));
		SpellChecComboBox<String> duringByComboMod = createComboBox("", duringByList, null);
		SpellChecComboBox<String> userByComboMod = createComboBox("by", userList, null);

		DateField modDate1 = createDateField("Modification Date");
		DateField modDate2 = createDateField("");

		FormLayout col04 = new FormLayout();
		col04.setSizeUndefined();
		col04.setSpacing(false);
		col04.setMargin(false);
		col04.setWidth("100%");
		col04.addComponent(langCombo);
		col04.addComponent(createdDuring);
		col04.addComponent(modifiedDuring);
		col04.addComponent(modDate1);
		col04.setComponentAlignment(langCombo, Alignment.TOP_LEFT);
		col04.setComponentAlignment(createdDuring, Alignment.TOP_LEFT);
		col04.setComponentAlignment(modifiedDuring, Alignment.TOP_LEFT);
		col04.setComponentAlignment(modDate1, Alignment.TOP_LEFT);

		FormLayout col14 = new FormLayout();
		col14.setSizeUndefined();
		col14.setSpacing(false);
		col14.setMargin(false);
		col14.setWidth("100%");
		col14.addComponent(new Label());
		col14.addComponent(duringByCombo);
		col14.addComponent(duringByComboMod);
		col14.addComponent(modDate2);
		col14.setComponentAlignment(duringByCombo, Alignment.TOP_LEFT);
		col14.setComponentAlignment(duringByComboMod, Alignment.TOP_LEFT);
		col14.setComponentAlignment(modDate2, Alignment.TOP_LEFT);

		FormLayout col24 = new FormLayout();
		col24.setSizeUndefined();
		col24.setSpacing(false);
		col24.setMargin(false);
		col24.setWidth("100%");
		col24.addComponent(new Label());
		col24.addComponent(userByCombo);
		col24.addComponent(userByComboMod);
		col24.setComponentAlignment(userByCombo, Alignment.TOP_LEFT);
		col24.setComponentAlignment(userByComboMod, Alignment.TOP_LEFT);

		searchGrid.addComponent(col04, 0, 19, 1, 19);
		searchGrid.addComponent(col14, 2, 19, 2, 19);
		searchGrid.addComponent(col24, 3, 19, 3, 19);

		searchGrid.addComponent(createSeperator(), 0, 24, 4, 24);

		SwitchWithTextBox matchCase = new SwitchWithTextBox("Match Case", 0);
		matchCase.setHeight("25px");

		FormLayout colMatch = new FormLayout();
		colMatch.setSizeUndefined();
		colMatch.setSpacing(false);
		colMatch.setMargin(false);
		colMatch.setWidth("100%");
		colMatch.addComponent(matchCase);
		colMatch.setComponentAlignment(matchCase, Alignment.TOP_LEFT);

		searchGrid.addComponent(colMatch, 0, 25, 1, 25);

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

		searchButton.addClickListener(e -> {
		});

		contentForAllL.addComponents(searchGrid);
		mainLayout.addComponent(searchButton);
		mainLayout.setComponentAlignment(searchButton, Alignment.BOTTOM_RIGHT);
		content.addComponents(mainLayout);
	}

	private void contentForModifiedResMe(String caption, VerticalLayout content) {
		VerticalLayout mainLayout = new VerticalLayout();

		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);

		Label label = new Label(caption);
		label.setSizeUndefined();

		mainLayout.addComponent(label);
		mainLayout.setComponentAlignment(label, Alignment.TOP_CENTER);

		HorizontalLayout contentForAllL = new HorizontalLayout();
		contentForAllL.addStyleName(LogoResConstants.STYLE_CARD_BLACK);
		contentForAllL.setWidth("100%");
		mainLayout.addComponent(contentForAllL);

		GridLayout searchGrid = new GridLayout(5, 26);
		searchGrid.setSizeFull();
		searchGrid.setWidth("100%");
		searchGrid.addStyleName(LogoResConstants.STYLE_GRID);

		searchGrid.setSpacing(false);
		searchGrid.setMargin(false);

		TextField resourceNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));
		TextField resourceNr2 = createTextField("");

		Button searchButton = new Button(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHSTR));
		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addStyleName(
				MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);

		SpellChecComboBox<String> descCombo = createComboBox(LogoResConstants.DESCSTR, descList, descList.get(3));
		TextField descComboText = createTextField("");

		SpellChecComboBox<String> resourceGroupCombo = createComboBox(LogoResConstants.RESGRPSTR, resourceGroupList,
				resourceGroupList.get(0));
		SpellChecComboBox<String> resourceTypeCombo = createComboBox(LogoResConstants.RESTYPESTR, resourceTypeList, null);
		SpellChecComboBox<String> resourceCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList, null);
		SpellChecComboBox<String> resourceStateCombo = createComboBox(LogoResConstants.RESSTATESTR, resourceStateList, null);


		FormLayout colLeft = new FormLayout();
		colLeft.setSizeUndefined();
		colLeft.setSpacing(false);
		colLeft.setMargin(false);
		colLeft.setWidth("100%");
		colLeft.addComponent(resourceNr1);
		colLeft.addComponent(descCombo);
		colLeft.addComponent(resourceGroupCombo);
		colLeft.addComponent(resourceTypeCombo);
		colLeft.addComponent(resourceCaseCombo);
		colLeft.addComponent(resourceStateCombo);
		colLeft.setComponentAlignment(resourceNr1, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(descCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceGroupCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceTypeCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceCaseCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceStateCombo, Alignment.TOP_LEFT);

		FormLayout colRight = new FormLayout();
		colRight.setSizeUndefined();
		colRight.setSpacing(false);
		colRight.setMargin(false);
		colRight.setWidth("100%");
		colRight.addComponent(resourceNr2);
		colRight.addComponent(descComboText);
		descComboText.setWidth("150%");
		colRight.setComponentAlignment(resourceNr2, Alignment.TOP_LEFT);
		colRight.setComponentAlignment(descComboText, Alignment.TOP_RIGHT);

		searchGrid.addComponent(createSeperator(), 0, 0, 4, 0);
		searchGrid.addComponent(colLeft, 0, 1, 1, 1);
		searchGrid.addComponent(colRight, 2, 1, 3, 1);

		searchGrid.addComponent(createSeperator(), 0, 7, 4, 7);

		TextField orderNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.ORDERNRSTR));
		TextField orderNr2 = createTextField("");

		TextField tagNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.TAGNRSTR));
		TextField tagNr2 = createTextField("");

		TextField levelNr1 = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.LEVELNRSTR));
		TextField levelNr2 = createTextField("");

		SpellChecComboBox<String> resourceItemCaseCombo = createComboBox(LogoResConstants.RESCASESTR, resourceCaseList, null);
		SpellChecComboBox<String> prefixCombo = createComboBox(LogoResConstants.PREFIXSTR, descList, descList.get(3));
		SpellChecComboBox<String> infoCombo = createComboBox(LogoResConstants.INFOSTR, descList, descList.get(3));

		TextField prefixComboText = createTextField("");
		TextField infoComboText = createTextField("");

		SpellChecComboBox<String> descComboTR = createComboBox(LogoResConstants.RE_TURKISHTR_NAME, descList, descList.get(3));
		TextField comboTextTR = createTextField("");

		SpellChecComboBox<String> descComboEN = createComboBox(LogoResConstants.RE_ENGLISHUS_NAME, descList, descList.get(3));
		TextField comboTextEN = createTextField("");

		colLeft.addComponent(createSeperator());
		colLeft.addComponent(orderNr1);
		colLeft.addComponent(tagNr1);
		colLeft.addComponent(levelNr1);
		colLeft.addComponent(resourceItemCaseCombo);
		colLeft.addComponent(prefixCombo);
		colLeft.addComponent(infoCombo);
		colLeft.setComponentAlignment(orderNr1, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(tagNr1, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(levelNr1, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(resourceItemCaseCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(prefixCombo, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(infoCombo, Alignment.TOP_LEFT);

		colRight.addComponent(new Label());
		colRight.addComponent(new Label());
		colRight.addComponent(new Label());
		colRight.addComponent(new Label());
		colRight.addComponent(createSeperator());

		colRight.addComponent(orderNr2);
		colRight.addComponent(tagNr2);
		colRight.addComponent(levelNr2);
		colRight.addComponent(new Label());
		colRight.addComponent(prefixComboText);
		colRight.addComponent(infoComboText);
		colRight.setComponentAlignment(orderNr2, Alignment.TOP_RIGHT);
		colRight.setComponentAlignment(tagNr2, Alignment.TOP_RIGHT);
		colRight.setComponentAlignment(levelNr2, Alignment.TOP_RIGHT);
		colRight.setComponentAlignment(prefixComboText, Alignment.TOP_RIGHT);
		colRight.setComponentAlignment(infoComboText, Alignment.TOP_RIGHT);

		colLeft.addComponent(createSeperator());
		colLeft.addComponent(descComboTR);
		colLeft.addComponent(descComboEN);
		colLeft.setComponentAlignment(descComboTR, Alignment.TOP_LEFT);
		colLeft.setComponentAlignment(descComboEN, Alignment.TOP_LEFT);

		colRight.addComponent(createSeperator());
		colRight.addComponent(comboTextTR);
		colRight.addComponent(comboTextEN);
		colRight.setComponentAlignment(comboTextTR, Alignment.TOP_LEFT);
		colRight.setComponentAlignment(comboTextEN, Alignment.TOP_LEFT);


		SpellChecComboBox<String> langCombo = createComboBox(LogoResConstants.SELECTLANG, langList, langList.get(0));

		TextField createdDuring = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.CREATEDBYDURINGSTR));
		SpellChecComboBox<String> duringByCombo = createComboBox("", duringByList, null);

		TextField modifiedDuring = createTextField(LangHelper.getLocalizableMessage(LogoResConstants.MODBYDURINGSTR));
		SpellChecComboBox<String> duringByComboMod = createComboBox("", duringByList, null);

		DateField modDate1 = createDateField("Modification Date");
		DateField modDate2 = createDateField("");

		FormLayout col04 = new FormLayout();
		col04.setSizeUndefined();
		col04.setSpacing(false);
		col04.setMargin(false);
		col04.setWidth("100%");
		col04.addComponent(langCombo);
		col04.addComponent(createdDuring);
		col04.addComponent(modifiedDuring);
		col04.addComponent(modDate1);
		col04.setComponentAlignment(langCombo, Alignment.TOP_LEFT);
		col04.setComponentAlignment(createdDuring, Alignment.TOP_LEFT);
		col04.setComponentAlignment(modifiedDuring, Alignment.TOP_LEFT);
		col04.setComponentAlignment(modDate1, Alignment.TOP_LEFT);

		FormLayout col14 = new FormLayout();
		col14.setSizeUndefined();
		col14.setSpacing(false);
		col14.setMargin(false);
		col14.setWidth("100%");
		col14.addComponent(new Label());
		col14.addComponent(duringByCombo);
		col14.addComponent(duringByComboMod);
		col14.addComponent(modDate2);
		col14.setComponentAlignment(duringByCombo, Alignment.TOP_LEFT);
		col14.setComponentAlignment(duringByComboMod, Alignment.TOP_LEFT);
		col14.setComponentAlignment(modDate2, Alignment.TOP_LEFT);

		searchGrid.addComponent(col04, 0, 19, 1, 19);
		searchGrid.addComponent(col14, 2, 19, 2, 19);

		searchGrid.addComponent(createSeperator(), 0, 24, 4, 24);

		SwitchWithTextBox matchCase = new SwitchWithTextBox("Match Case", 0);
		matchCase.setHeight("25px");

		FormLayout colMatch = new FormLayout();
		colMatch.setSizeUndefined();
		colMatch.setSpacing(false);
		colMatch.setMargin(false);
		colMatch.setWidth("100%");
		colMatch.addComponent(matchCase);
		colMatch.setComponentAlignment(matchCase, Alignment.TOP_LEFT);

		searchGrid.addComponent(colMatch, 0, 25, 1, 25);

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

		searchButton.addClickListener(e -> {
		});

		contentForAllL.addComponents(searchGrid);
		mainLayout.addComponent(searchButton);
		mainLayout.setComponentAlignment(searchButton, Alignment.BOTTOM_RIGHT);
		content.addComponents(mainLayout);
	}
	
	private void contentForMessage()
	{
		resView.getFilter().setIcon(VaadinIcons.ANGLE_DOWN);
		resView.createMessageLayout();
		resView.getSearchLayoutForView().setVisible(false);
	}

	private void contentForHelpDocs()
	{
		resView.getFilter().setIcon(VaadinIcons.ANGLE_DOWN);
		resView.createHelpDocsLayout();
		resView.getSearchLayoutForView().setVisible(false);
	}

	<T> SpellChecComboBox<T> createComboBox(String name, Collection<T> items, T value) {
		SpellChecComboBox<T> combo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(name));
		combo.setDescription(LangHelper.getLocalizableMessage(name));
		combo.setPlaceholder(LangHelper.getLocalizableMessage(name));
		if (items != null)
			combo.setItems(items);
		combo.addStyleName(MaterialTheme.COMBOBOX_FLOATING);
		combo.setHeight("25px");
		combo.setWidth("100%");
		combo.setValue(value);
		combo.setEmptySelectionAllowed(false);

		return combo;
	}

	TextField createTextField(String caption) {
		/**
		 * String cap = "<b style=text-align:left;margin-right:" + 0 +
		 * "px;color:#fab331;text-decoration:none;text-decoration-color:#fab331>"
		 * + caption + "</b>";
		 **/
		TextField textField = new TextField(caption);
		textField.setCaptionAsHtml(true);
		textField.addStyleName(MaterialTheme.TEXTFIELD_FLOATING);
		textField.setHeight("25px");
		textField.setWidth("100%");
		return textField;
	}

	DateField createDateField(String caption) {
		/**
		 * String cap = "<b style=text-align:left;margin-right:" + 0 +
		 * "px;color:#fab331;text-decoration:none;text-decoration-color:#fab331>"
		 * + caption + "</b>";
		 **/
		DateField dateField = new DateField(caption);
		dateField.setCaptionAsHtml(true);
		dateField.addStyleName(MaterialTheme.TEXTFIELD_FLOATING);
		dateField.setValue(LocalDate.now());
		dateField.setDateFormat("dd-MM-yyyy");
		Locale trlocale = Locale.forLanguageTag(LogoresMainUI.getMrepositorycontainer().getAppLocale().getLanguage());
		dateField.setLocale(trlocale); 
		dateField.setHeight("25px");
		dateField.setWidth("100%");
		dateField.addValueChangeListener(
				event -> Notification.show("Value changed:", String.valueOf(event.getValue()), Type.HUMANIZED_MESSAGE));
		return dateField;
	}

	Label createSeperator() {
		Label hSep = new Label();
		hSep.addStyleName("horizontal-separator");
		hSep.setHeight("3px");
		hSep.setWidth("100%");
		return hSep;

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
