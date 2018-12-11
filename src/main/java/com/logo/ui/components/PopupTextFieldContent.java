package com.logo.ui.components;

import java.time.format.DateTimeFormatter;

import com.logo.LogoresMainUI;
import com.logo.data.entity.ReResourceitem;
import com.logo.data.repository.ReResourceitemRep;
import com.logo.data.repository.ReUserRep;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.vaadin.data.Binder;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PopupTextFieldContent implements PopupView.Content {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final VerticalLayout layout;

	private final TextField textField1 = new TextField(LangHelper.getLocalizableMessage(LogoResConstants.ORDERNRSTR));
	private final TextField textField2 = new TextField(LangHelper.getLocalizableMessage(LogoResConstants.TAGNRSTR));
	private final TextField textField3 = new TextField(LangHelper.getLocalizableMessage(LogoResConstants.PREFIXSTR));
	private final SpellChecTextField createdUser = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.CREATED_USER_LABEL));
	private final SpellChecTextField modifiedUser = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.MODIFIED_USER_LABEL));
	private final SpellChecTextField createdTime = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.CREATED_TIME_LABEL));
	private final SpellChecTextField modifiedTime = new SpellChecTextField(
			LangHelper.getLocalizableMessage(LogoResConstants.MODIFIED_TIME_LABEL));

	GridLayout resourceInfo = new GridLayout();
	private transient ReResourceitemRep reResourceitemRep;
	private transient ReResourceitem reResourceitem;
	private transient ReUserRep userRepo;

	private final Button btSave = new ButtonGenerator(LogoResConstants.SAVESTR);
	private final Button btCancel = new ButtonGenerator(LogoResConstants.CANCELSTR);

	PopupTextFieldContent(ReResourceitem reResourceitem, ReResourceitemRep reResourceitemRep) {
		this.reResourceitemRep = reResourceitemRep;
		this.reResourceitem = reResourceitem;
		this.userRepo = LogoresMainUI.getMrepositorycontainer().getReUserRep();

		Binder<ReResourceitem> binder = new Binder<>(ReResourceitem.class);
		binder.setBean(this.reResourceitem);

		binder.forField(textField1)
				.withConverter(new NoThousandsStringToIntegerConverter(
						LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReResourceitem::getOrdernr, ReResourceitem::setOrdernr);
		binder.forField(textField2)
				.withConverter(new NoThousandsStringToIntegerConverter(
						LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				.bind(ReResourceitem::getTagnr, ReResourceitem::setTagnr);
		binder.forField(textField3).bind(ReResourceitem::getPrefixstr, ReResourceitem::setPrefixstr);

		if (reResourceitem.getCreatedby() != null || reResourceitem.getCreatedby() != 0) {
			createdUser.setValue(getUserName(reResourceitem.getCreatedby()));
			createdUser.setEnabled(false);
		} else {
			createdUser.setVisible(false);
		}

		if (reResourceitem.getModifiedby() != null || reResourceitem.getCreatedby() != 0) {
			modifiedUser.setValue(getUserName(reResourceitem.getModifiedby()));
			modifiedUser.setEnabled(false);
		} else {
			modifiedUser.setVisible(false);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		createdTime.setValue(reResourceitem.getCreatedon().format(formatter));
		createdTime.setEnabled(false);

		modifiedTime.setValue(reResourceitem.getModifiedon().format(formatter));
		modifiedTime.setEnabled(false);

		HorizontalLayout buttonLayout = new HorizontalLayout(btSave, btCancel);

		layout = new VerticalLayout(textField1, textField2, textField3, createdUser, modifiedUser, createdTime,
				modifiedTime, buttonLayout);
		layout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);
	}

	public Button getBtSave() {
		return btSave;
	}

	public Button getBtCancel() {
		return btCancel;
	}

	public void save() {
		reResourceitemRep.save(reResourceitem);
	}

	@Override
	public final Component getPopupComponent() {
		return layout;
	}

	@Override
	public final String getMinimizedValueAsHTML() {
		if (textField3.getValue().equals(""))
			return textField1.getValue() + "-" + textField2.getValue();
		else
			return textField1.getValue() + "-" + textField2.getValue() + "-" + textField3.getValue();
	}

	private String getUserName(int userId) {
		String username = userRepo.findByid(userId).getUsername();
		if (username != null) {
			return username;
		} else {
			return "";
		}
	}

}
