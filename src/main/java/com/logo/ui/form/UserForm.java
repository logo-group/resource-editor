package com.logo.ui.form;

import org.vaadin.dialogs.ConfirmDialog;

import com.github.appreciated.material.MaterialTheme;
import com.logo.data.entity.ReResourceGroup;
import com.logo.data.entity.ReUser;
import com.logo.data.repository.ReResourceGroupRep;
import com.logo.data.repository.ReUserRep;
import com.logo.ui.components.ButtonGenerator;
import com.logo.ui.components.LangLayout;
import com.logo.ui.components.SpellChecComboBox;
import com.logo.ui.components.SpellChecTextField;
import com.logo.ui.components.SwitchWithTextBox;
import com.logo.ui.view.UserView;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.converter.StrToIntegerConverter;
import com.logo.util.enums.UserLanguage;
import com.logo.util.enums.UserLayoutType;
import com.logo.util.enums.UserType;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class UserForm extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private SpellChecTextField username = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.USERNAME));
	private SpellChecTextField name = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.NAMESTR));
	private SpellChecTextField surname = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.SURNAMESTR));
	private SpellChecTextField department = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.DEPARTMENTSTR));
	private SpellChecTextField email = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.EMAILSTR));
	private SpellChecTextField altEmail = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.ALTEMAILSTR));
	private PasswordField password = new PasswordField(LangHelper.getLocalizableMessage(LogoResConstants.PASS_WORDSTR));
	private SpellChecTextField invalidLoginCount = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.INVALIDLOGINCOUNTSTR));
	private SpellChecTextField nrOfPassRequests = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.REQPASSCOUNTSTR));

	private final SpellChecComboBox<UserLayoutType> userLayoutTypeCombo = new SpellChecComboBox<>(
			LangHelper.getLocalizableMessage(LogoResConstants.DEFAULTORIENTSTR));
	private final SpellChecComboBox<UserType> userTypeCombo = new SpellChecComboBox<>(
			LangHelper.getLocalizableMessage(LogoResConstants.GENERALACCESRIGHTDSTR));
	private final SpellChecComboBox<ReResourceGroup> resourceGroupCombo = new SpellChecComboBox<>(
			LangHelper.getLocalizableMessage(LogoResConstants.RESGRPSTR));
	private final SpellChecComboBox<UserLanguage> languageCombo = new SpellChecComboBox<>("Default Language");
	private SwitchWithTextBox displayedSwitch = new SwitchWithTextBox(
			LangHelper.getLocalizableMessage(LogoResConstants.DISPLAYEDSTR), 0);
	private SwitchWithTextBox enabledSwitch = new SwitchWithTextBox(
			LangHelper.getLocalizableMessage(LogoResConstants.ENABLEDSTR), 0);
	private SwitchWithTextBox selectedSwitch = new SwitchWithTextBox(
			LangHelper.getLocalizableMessage(LogoResConstants.SELECTEDSTR), 0);
	private SwitchWithTextBox temppasswordSwitch = new SwitchWithTextBox(
			LangHelper.getLocalizableMessage(LogoResConstants.TEMPPASS_WORD), 0);

	private final Button save = new ButtonGenerator(LogoResConstants.SAVESTR);
	private final Button close = new ButtonGenerator(LogoResConstants.CLOSELSTR);
	private final Button delete = new ButtonGenerator(LogoResConstants.DELETESTR);

	private final Label formName = new Label();
	private TabSheet tabsheet = new TabSheet();
	private VerticalLayout settings = new VerticalLayout();
	private GridLayout userDetalLayout = new GridLayout(2, 6);
	private HorizontalLayout switchLayout = new HorizontalLayout();
	private boolean isPreferences = false;

	public Button getSaveButton() {
		return save;
	}

	public Button getCloseButton() {
		return close;
	}

	public TabSheet getTabsheet() {
		return tabsheet;
	}

	public ReUserRep userRepo;

	private ReResourceGroupRep resourceGroupRepo;

	private ReUser user;
	private UserView userView;
	private Binder<ReUser> binder = new Binder<>(ReUser.class);

	private LangLayout turkey;
	private LangLayout unitedStates;
	private LangLayout germany;
	private LangLayout iran;
	private LangLayout azerbaijan;
	private LangLayout russia;
	private LangLayout bulgaria;
	private LangLayout romania;
	private LangLayout georgia;
	private LangLayout jordan;
	private LangLayout france;
	private LangLayout kosovo;
	private LangLayout turkmenistan;
	private LangLayout egypt;
	private LangLayout saudiArabia;

	public UserForm(UserView userView, ReUserRep userRepo, ReResourceGroupRep resourceGroupRepo) {
		this.userView = userView;
		tabsheet = new TabSheet();
		this.userRepo = userRepo;
		this.resourceGroupRepo = resourceGroupRepo;
		isPreferences = false;
		initialize();
	}

	public UserForm(ReUserRep userRepo) {
		tabsheet = new TabSheet();
		this.userRepo = userRepo;
		isPreferences = true;
		initialize();
	}

	public void initialize() {
		GridLayout userMainLayout = new GridLayout(2, 6);

		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		setHeight("100%");

		userMainLayout.setSpacing(true);
		userMainLayout.setMargin(true);
		userMainLayout.setWidth("100%");
		userMainLayout.setHeight("100%");

		switchLayout.setSpacing(true);
		switchLayout.setMargin(true);
		switchLayout.setWidth("100%");
		switchLayout.setHeight("100%");

		userDetalLayout.setSpacing(true);
		userDetalLayout.setMargin(true);
		userDetalLayout.setWidth("100%");
		userDetalLayout.setHeight("100%");

		username.setWidth("100%");
		username.setHeight("30px");

		name.setWidth("100%");
		name.setHeight("30px");

		surname.setWidth("100%");
		surname.setHeight("30px");

		department.setWidth("100%");
		department.setHeight("30px");

		password.setWidth("100%");
		password.setHeight("30px");

		email.setWidth("100%");
		email.setHeight("30px");

		altEmail.setWidth("100%");
		altEmail.setHeight("30px");

		invalidLoginCount.setWidth("100%");
		invalidLoginCount.setHeight("30px");

		nrOfPassRequests.setWidth("100%");
		nrOfPassRequests.setHeight("30px");

		userLayoutTypeCombo.setWidth("100%");
		userLayoutTypeCombo.setHeight("30px");

		userTypeCombo.setWidth("100%");
		userTypeCombo.setHeight("30px");

		resourceGroupCombo.setWidth("100%");
		resourceGroupCombo.setHeight("30px");

		languageCombo.setWidth("100%");
		languageCombo.setHeight("30px");

		save.setHeight("30px");
		close.setHeight("30px");
		close.addStyleName(MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_BORDERLESS + " "
				+ LogoResConstants.STYLE_CUSTOM_WHITE);

		userLayoutTypeCombo.setItems(UserLayoutType.V, UserLayoutType.H);
		userTypeCombo.setItems(UserType.ADMINISTRATOR, UserType.PROGRAMMER, UserType.INTERNALLOCALIZER,
				UserType.EXTERNALLOCALIZER);
		resourceGroupCombo.setItems(resourceGroupRepo.findAll());
		languageCombo.setItems(UserLanguage.TRTR, UserLanguage.ENUS, UserLanguage.DEDE, UserLanguage.FAIR,
				UserLanguage.AZAZ, UserLanguage.RURU, UserLanguage.BGBG, UserLanguage.RORO, UserLanguage.KAGE,
				UserLanguage.ARJO, UserLanguage.FRFR, UserLanguage.SQKV, UserLanguage.TKTM, UserLanguage.AREG,
				UserLanguage.ARSA);

		binder.forField(username).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.USERNAMENOTEMTYSTR))
				.bind(ReUser::getUsername, ReUser::setUsername);

		binder.forField(name).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.NAMENOTEMTYSTR))
				.bind(ReUser::getName, ReUser::setName);

		binder.forField(surname).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.SURNAMENOTEMTYSTR))
				.bind(ReUser::getSurname, ReUser::setSurname);

		binder.forField(department).bind(ReUser::getDepartment, ReUser::setDepartment);
		binder.forField(password).bind(ReUser::getPassword, ReUser::setPassword);
		binder.forField(email).bind(ReUser::getEmail, ReUser::setEmail);
		binder.forField(altEmail).bind(ReUser::getAltemail, ReUser::setAltemail);
		binder.forField(invalidLoginCount)
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getInvalidlogincount, ReUser::setInvalidlogincount);
		binder.forField(nrOfPassRequests)
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getPasswordrequest, ReUser::setPasswordrequest);

		binder.bind(userLayoutTypeCombo, ReUser::getDefaultorientation, ReUser::setDefaultorientation);
		binder.bind(userTypeCombo, ReUser::getGeneralaccessrights, ReUser::setGeneralaccessrights);
		binder.bind(resourceGroupCombo, ReUser::getDefaultresourcegroup, ReUser::setDefaultresourcegroup);
		binder.bind(languageCombo, ReUser::getDefaultlanguage, ReUser::setDefaultlanguage);

		binder.forField(displayedSwitch.getSwcTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getDisplayed, ReUser::setDisplayed);

		binder.forField(enabledSwitch.getSwcTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getEnabled, ReUser::setEnabled);

		binder.forField(selectedSwitch.getSwcTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getSelected, ReUser::setSelected);

		binder.forField(temppasswordSwitch.getSwcTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getTemppassword, ReUser::setTemppassword);

		HorizontalLayout labelLayout = new HorizontalLayout();
		labelLayout.setWidth("100%");
		labelLayout.addStyleName(LogoResConstants.STYLE_CARD_HOVER_DEFULT_PRIMARY);

		labelLayout.addComponent(formName);
		labelLayout.addComponent(close);
		labelLayout.setComponentAlignment(formName, Alignment.TOP_LEFT);
		labelLayout.setComponentAlignment(close, Alignment.TOP_RIGHT);

		delete.setVisible(false);
		HorizontalLayout buttonLayout = new HorizontalLayout(save, delete);

		userMainLayout.addComponent(username, 0, 0);
		userMainLayout.addComponent(password, 1, 0);
		userMainLayout.addComponent(name, 0, 1);
		userMainLayout.addComponent(surname, 1, 1);
		userMainLayout.addComponent(email, 0, 2);
		userMainLayout.addComponent(altEmail, 1, 2);
		userMainLayout.addComponent(department, 0, 3);

		userLayoutTypeCombo.setEmptySelectionAllowed(false);
		userTypeCombo.setEmptySelectionAllowed(false);
		resourceGroupCombo.setEmptySelectionAllowed(false);
		languageCombo.setEmptySelectionAllowed(false);

		userDetalLayout.addComponent(userLayoutTypeCombo, 0, 0);

		if (!isPreferences)
			userDetalLayout.addComponent(userTypeCombo, 1, 0);
		if (!isPreferences)
			userDetalLayout.addComponent(resourceGroupCombo, 0, 1);

		if (!isPreferences)
			userDetalLayout.addComponent(languageCombo, 1, 1);
		else
			userDetalLayout.addComponent(languageCombo, 0, 1);

		if (!isPreferences)
			userDetalLayout.addComponent(invalidLoginCount, 0, 2);
		if (!isPreferences)
			userDetalLayout.addComponent(nrOfPassRequests, 1, 2);

		if (!isPreferences)
			userDetalLayout.addComponent(switchLayout, 0, 3, 1, 3);

		switchLayout.addComponent(displayedSwitch);
		switchLayout.addComponent(enabledSwitch);
		switchLayout.addComponent(selectedSwitch);
		switchLayout.addComponent(temppasswordSwitch);

		turkey = new LangLayout(LogoResConstants.RE_TURKISHTR_NAME, 0);
		unitedStates = new LangLayout(LogoResConstants.RE_ENGLISHUS_NAME, 0);
		germany = new LangLayout(LogoResConstants.RE_GERMANDE_NAME, 0);
		iran = new LangLayout(LogoResConstants.RE_PERSIANIR_NAME, 0);
		azerbaijan = new LangLayout(LogoResConstants.RE_AZERBAIJANIAZ_NAME, 0);
		russia = new LangLayout(LogoResConstants.RE_RUSSIANRU_NAME, 0);
		bulgaria = new LangLayout(LogoResConstants.RE_BULGARIANBG_NAME, 0);
		romania = new LangLayout(LogoResConstants.RE_ROMANIANRO_NAME, 0);
		georgia = new LangLayout(LogoResConstants.RE_GEORGIANGE_NAME, 0);
		jordan = new LangLayout(LogoResConstants.RE_ARABICJO_NAME, 0);
		france = new LangLayout(LogoResConstants.RE_FRENCHFR_NAME, 0);
		kosovo = new LangLayout(LogoResConstants.RE_ALBANIANKV_NAME, 0);
		turkmenistan = new LangLayout(LogoResConstants.RE_TURKMENTM_NAME, 0);
		egypt = new LangLayout(LogoResConstants.RE_ARABICEG_NAME, 0);
		saudiArabia = new LangLayout(LogoResConstants.RE_ARABICSA_NAME, 0);

		binder.forField(turkey.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getTrtraccessrights, ReUser::setTrtraccessrights);

		binder.forField(unitedStates.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getEnusaccessrights, ReUser::setEnusaccessrights);

		binder.forField(germany.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getDedeaccessrights, ReUser::setDedeaccessrights);

		binder.forField(iran.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getFairaccessrights, ReUser::setFairaccessrights);

		binder.forField(azerbaijan.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getAzazaccessrights, ReUser::setAzazaccessrights);

		binder.forField(russia.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getRuruaccessrights, ReUser::setRuruaccessrights);

		binder.forField(bulgaria.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getBgbgaccessrights, ReUser::setBgbgaccessrights);

		binder.forField(romania.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getRoroaccessrights, ReUser::setRoroaccessrights);

		binder.forField(georgia.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getKageaccessrights, ReUser::setKageaccessrights);

		binder.forField(jordan.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getArjoaccessrights, ReUser::setArjoaccessrights);

		binder.forField(france.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getFrfraccessrights, ReUser::setFrfraccessrights);

		binder.forField(kosovo.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getSqkvaccessrights, ReUser::setSqkvaccessrights);

		binder.forField(turkmenistan.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getTktmaccessrights, ReUser::setTktmaccessrights);

		binder.forField(egypt.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getAregaccessrights, ReUser::setAregaccessrights);

		binder.forField(saudiArabia.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getArsaaccessrights, ReUser::setArsaaccessrights);

		settings.addComponent(turkey);
		settings.addComponent(unitedStates);
		settings.addComponent(germany);
		settings.addComponent(iran);
		settings.addComponent(azerbaijan);
		settings.addComponent(russia);
		settings.addComponent(bulgaria);
		settings.addComponent(romania);
		settings.addComponent(georgia);
		settings.addComponent(jordan);
		settings.addComponent(france);
		settings.addComponent(kosovo);
		settings.addComponent(turkmenistan);
		settings.addComponent(egypt);
		settings.addComponent(saudiArabia);

		tabsheet.addTab(userMainLayout, LangHelper.getLocalizableMessage(LogoResConstants.GENERALINFOSTR))
				.setIcon(VaadinIcons.USER);
		tabsheet.addTab(userDetalLayout, LangHelper.getLocalizableMessage(LogoResConstants.SETTINGSSTR))
				.setIcon(VaadinIcons.WRENCH);
		if (!isPreferences)
			tabsheet.addTab(settings, LangHelper.getLocalizableMessage(LogoResConstants.LANGAUTHSSTR))
					.setIcon(VaadinIcons.GLOBE);

		save.addClickListener(e -> save());
		close.addClickListener(e -> cancel());
		delete.addClickListener(e -> delete());

		addComponent(labelLayout);
		addComponentsAndExpand(tabsheet);
		addComponent(buttonLayout);
		setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);
		addStyleName(MaterialTheme.LAYOUT_CARD);
	}

	public void setUser(ReUser user) {
		this.user = user;
		delete.setVisible(false);
		formName.setValue(user.getName() + " " + user.getSurname());
		binder.setBean(user);
		if (!isPreferences) {
			if (user.isPersisted())
				delete.setVisible(true);
		}
		prepareLangLayout(user);
		setVisible(true);
	}

	private void prepareLangLayout(ReUser user) {
		settings.removeComponent(turkey);
		settings.removeComponent(unitedStates);
		settings.removeComponent(germany);
		settings.removeComponent(iran);
		settings.removeComponent(azerbaijan);
		settings.removeComponent(russia);
		settings.removeComponent(bulgaria);
		settings.removeComponent(romania);
		settings.removeComponent(georgia);
		settings.removeComponent(jordan);
		settings.removeComponent(france);
		settings.removeComponent(kosovo);
		settings.removeComponent(turkmenistan);
		settings.removeComponent(egypt);
		settings.removeComponent(saudiArabia);

		switchLayout.removeComponent(displayedSwitch);
		switchLayout.removeComponent(enabledSwitch);
		switchLayout.removeComponent(selectedSwitch);
		switchLayout.removeComponent(temppasswordSwitch);

		turkey = new LangLayout(LogoResConstants.RE_TURKISHTR_NAME, user.getTrtraccessrights());
		unitedStates = new LangLayout(LogoResConstants.RE_ENGLISHUS_NAME, user.getEnusaccessrights());
		germany = new LangLayout(LogoResConstants.RE_GERMANDE_NAME, user.getDedeaccessrights());
		iran = new LangLayout(LogoResConstants.RE_PERSIANIR_NAME, user.getFairaccessrights());
		azerbaijan = new LangLayout(LogoResConstants.RE_AZERBAIJANIAZ_NAME, user.getAzazaccessrights());
		russia = new LangLayout(LogoResConstants.RE_RUSSIANRU_NAME, user.getRuruaccessrights());
		bulgaria = new LangLayout(LogoResConstants.RE_BULGARIANBG_NAME, user.getBgbgaccessrights());
		romania = new LangLayout(LogoResConstants.RE_ROMANIANRO_NAME, user.getRoroaccessrights());
		georgia = new LangLayout(LogoResConstants.RE_GEORGIANGE_NAME, user.getKageaccessrights());
		jordan = new LangLayout(LogoResConstants.RE_ARABICJO_NAME, user.getArjoaccessrights());
		france = new LangLayout(LogoResConstants.RE_FRENCHFR_NAME, user.getFrfraccessrights());
		kosovo = new LangLayout(LogoResConstants.RE_ALBANIANKV_NAME, user.getSqkvaccessrights());
		turkmenistan = new LangLayout(LogoResConstants.RE_TURKMENTM_NAME, user.getTktmaccessrights());
		egypt = new LangLayout(LogoResConstants.RE_ARABICEG_NAME, user.getAregaccessrights());
		saudiArabia = new LangLayout(LogoResConstants.RE_ARABICSA_NAME, user.getArsaaccessrights());

		displayedSwitch = new SwitchWithTextBox(LangHelper.getLocalizableMessage(LogoResConstants.DISPLAYEDSTR),
				user.getDisplayed());
		enabledSwitch = new SwitchWithTextBox(LangHelper.getLocalizableMessage(LogoResConstants.ENABLEDSTR),
				user.getEnabled());
		selectedSwitch = new SwitchWithTextBox(LangHelper.getLocalizableMessage(LogoResConstants.SELECTEDSTR),
				user.getSelected());
		temppasswordSwitch = new SwitchWithTextBox(LangHelper.getLocalizableMessage(LogoResConstants.TEMPPASS_WORD),
				user.getTemppassword());

		setvalueChange(turkey);
		setvalueChange(unitedStates);
		setvalueChange(germany);
		setvalueChange(iran);
		setvalueChange(azerbaijan);
		setvalueChange(russia);
		setvalueChange(bulgaria);
		setvalueChange(romania);
		setvalueChange(georgia);
		setvalueChange(jordan);
		setvalueChange(france);
		setvalueChange(kosovo);
		setvalueChange(turkmenistan);
		setvalueChange(egypt);
		setvalueChange(saudiArabia);

		binder.forField(turkey.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getTrtraccessrights, ReUser::setTrtraccessrights);

		binder.forField(unitedStates.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getEnusaccessrights, ReUser::setEnusaccessrights);

		binder.forField(germany.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getDedeaccessrights, ReUser::setDedeaccessrights);

		binder.forField(iran.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getFairaccessrights, ReUser::setFairaccessrights);

		binder.forField(azerbaijan.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getAzazaccessrights, ReUser::setAzazaccessrights);

		binder.forField(russia.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getRuruaccessrights, ReUser::setRuruaccessrights);

		binder.forField(bulgaria.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getBgbgaccessrights, ReUser::setBgbgaccessrights);

		binder.forField(romania.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getRoroaccessrights, ReUser::setRoroaccessrights);

		binder.forField(georgia.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getKageaccessrights, ReUser::setKageaccessrights);

		binder.forField(jordan.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getArjoaccessrights, ReUser::setArjoaccessrights);

		binder.forField(france.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getFrfraccessrights, ReUser::setFrfraccessrights);

		binder.forField(kosovo.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getSqkvaccessrights, ReUser::setSqkvaccessrights);

		binder.forField(turkmenistan.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getTktmaccessrights, ReUser::setTktmaccessrights);

		binder.forField(egypt.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getAregaccessrights, ReUser::setAregaccessrights);

		binder.forField(saudiArabia.getLangTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getArsaaccessrights, ReUser::setArsaaccessrights);

		settings.addComponent(turkey);
		settings.addComponent(unitedStates);
		settings.addComponent(germany);
		settings.addComponent(iran);
		settings.addComponent(azerbaijan);
		settings.addComponent(russia);
		settings.addComponent(bulgaria);
		settings.addComponent(romania);
		settings.addComponent(georgia);
		settings.addComponent(jordan);
		settings.addComponent(france);
		settings.addComponent(kosovo);
		settings.addComponent(turkmenistan);
		settings.addComponent(egypt);
		settings.addComponent(saudiArabia);

		binder.forField(displayedSwitch.getSwcTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getDisplayed, ReUser::setDisplayed);
		binder.forField(enabledSwitch.getSwcTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getEnabled, ReUser::setEnabled);
		binder.forField(selectedSwitch.getSwcTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getSelected, ReUser::setSelected);
		binder.forField(temppasswordSwitch.getSwcTxtField())
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReUser::getTemppassword, ReUser::setTemppassword);

		switchLayout.addComponent(displayedSwitch);
		switchLayout.addComponent(enabledSwitch);
		switchLayout.addComponent(selectedSwitch);
		switchLayout.addComponent(temppasswordSwitch);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setvalueChange(LangLayout langLayout) {
		langLayout.getSRead().addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Boolean value = (Boolean) event.getValue();
				langLayout.setChecked(value, langLayout.getSWrite().getValue(), langLayout.getSDelete().getValue());
			}
		});

		langLayout.getSWrite().addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Boolean value = (Boolean) event.getValue();
				langLayout.setChecked(langLayout.getSRead().getValue(), value, langLayout.getSDelete().getValue());
			}
		});

		langLayout.getSDelete().addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Boolean value = (Boolean) event.getValue();
				langLayout.setChecked(langLayout.getSRead().getValue(), langLayout.getSWrite().getValue(), value);
			}
		});
	}

	private void delete() {
		ConfirmDialog.show(getUI(), "Dikkat !", user.getUsername() + " adlı kullanıcı silinecektir onaylıyor musunuz ?",
				"Evet", "Hayır", new ConfirmDialog.Listener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							userRepo.delete(user);
							Notification.show(user.getUsername() + " adlı kullanıcı silindi");
							setVisible(false);
							userView.updateUserList("");
						}
					}
				});
	}

	private void cancel() {
		setVisible(false);
		if (!isPreferences)
			userView.refreshGrid();
	}

	private void save() {
		boolean isPersisted = user.isPersisted();
		try {
			userRepo.save(user);
		} catch (Exception e) {
			Notification notification = new Notification("<p>Caption</p>",
					"<div style='display:inline-block;'><h1>Description</h1>"
							+ "<p class='tested-p'>tested p</p></div>");
			notification.setHtmlContentAllowed(true);
			notification.setDelayMsec(50000);
			notification.show(Page.getCurrent());
		}
		if (!isPreferences) {
			if (isPersisted)
				userView.refreshGrid();
			else
				userView.updateUserList("");
		}
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
