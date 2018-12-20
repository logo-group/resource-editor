package com.logo.ui.view;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.github.appreciated.material.MaterialTheme;
import com.logo.data.entity.ReMessage;
import com.logo.data.repository.ReMessageRep;
import com.logo.ui.components.TextFieldWithButton;
import com.logo.ui.form.MessageForm;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;

@Scope("prototype")
@SpringView(name = ReMessageView.VIEW_NAME)
public class ReMessageView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "ReMessageView";

	private ReMessageRep reMessageRep;

	private Grid<ReMessage> messageGrid = new Grid<>(ReMessage.class);

	private MessageForm messageForm;

	@Autowired
	public ReMessageView(ReMessageRep reMessageRep) {
		this.reMessageRep = reMessageRep;
		this.messageForm = new MessageForm(this, reMessageRep);
		removeAllComponents();
		init();
	}

	@PostConstruct
	void init() {
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		setHeight("100%");
		addStyleName(MaterialTheme.LAYOUT_CARD);

		this.setDescription(LangHelper.getLocalizableMessage(LogoResConstants.CLICKTOEDITSTR));
		messageForm.setVisible(false);

		messageGrid.setColumns("mtype", "consId", "module");

		HeaderRow row = messageGrid.appendHeaderRow();
		row.join("consId").setHtml("<b>consId</b>");

		HorizontalLayout header = new HorizontalLayout();

		HorizontalLayout grid = new HorizontalLayout();
		grid.addStyleName(MaterialTheme.CARD_HOVERABLE);

		messageGrid.setWidth("50%");
		messageGrid.setHeight("100%");
		grid.setWidth("100%");
		grid.setHeight("100%");

		Button backButton = new Button("Home");
		backButton.setIcon(VaadinIcons.ARROW_BACKWARD);
		backButton.addStyleName(MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " "
				+ MaterialTheme.BUTTON_BORDERLESS_COLORED);

		Button addNewMsg = new Button("Add New Message");
		addNewMsg.setIcon(VaadinIcons.PLUS);
		addNewMsg.addStyleName(MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);

		backButton.addClickListener(e -> getUI().getNavigator().navigateTo(ResourceViewNew.VIEW_NAME));

		header.addComponent(addNewMsg);

		addNewMsg.addClickListener(e -> {
			messageForm.setVisible(true);
			messageForm.setReMessage(new ReMessage());
		});

		addComponent(header);
		grid.addComponentsAndExpand(messageGrid);
		grid.addComponentsAndExpand(messageForm);
		addComponentsAndExpand(grid);
		addGridFilters();
		updateMessageListForCons("");

		messageGrid.setSelectionMode(SelectionMode.SINGLE);

		messageGrid.addSelectionListener(new SelectionListener<ReMessage>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectionChange(SelectionEvent<ReMessage> event) {
				Optional<ReMessage> value = event.getFirstSelectedItem();
				if (value.isPresent()) {
					messageForm.setVisible(true);
					messageForm.setReMessage(value.get());
				}
			}
		});

	}

	public void updateMessageListForCons(String value) {
		String likeFilter = "%" + value + "%";
		List<ReMessage> message = reMessageRep.findByConsIdLikeIgnoreCase(likeFilter);
		messageGrid.setItems(message);
	}

	public void updateMessageListForModule(String value) {
		String likeFilter = "%" + value + "%";
		List<ReMessage> message = reMessageRep.findByModuleLikeIgnoreCase(likeFilter);
		messageGrid.setItems(message);
	}

	public void refreshGrid() {
		messageGrid.clearSortOrder();
	}

	private void addGridFilters() {
		if (messageGrid.getHeaderRowCount() > 1) {
			HeaderRow filterRow1 = messageGrid.getHeaderRow(1);
			messageGrid.removeHeaderRow(filterRow1);
		}
		final HeaderRow filterRow = messageGrid.appendHeaderRow();
		for (final Column<ReMessage, ?> column : messageGrid.getColumns()) {
			final HeaderCell headerCell = filterRow.getCell(column);
			if ("id".equals(column.getId())) {
				headerCell.setComponent(createFilterTextFieldForID());
			} else if ("consId".equals(column.getId())) {
				headerCell.setComponent(createFilterTextFieldForConsId());
			} else if ("module".equals(column.getId())) {
				headerCell.setComponent(createFilterTextFieldForModule());
			}
		}
	}

	private HorizontalLayout createFilterTextFieldForID() {
		TextFieldWithButton searcField1 = new TextFieldWithButton("", VaadinIcons.CLOSE);
		searcField1.getButton().addClickListener(e -> searcField1.getTextField().clear());

		searcField1.getTextField().addValueChangeListener(event -> {
			Notification.show(event.getValue());
		});

		return searcField1;
	}

	private HorizontalLayout createFilterTextFieldForConsId() {
		TextFieldWithButton searcField2 = new TextFieldWithButton("", VaadinIcons.CLOSE);
		searcField2.getButton().addClickListener(e -> searcField2.getTextField().clear());
		searcField2.getTextField().addValueChangeListener(event -> {
			Notification.show(event.getValue());
			updateMessageListForCons(event.getValue());
		});
		return searcField2;
	}

	private HorizontalLayout createFilterTextFieldForModule() {
		TextFieldWithButton searcField2 = new TextFieldWithButton("", VaadinIcons.CLOSE);
		searcField2.getButton().addClickListener(e -> searcField2.getTextField().clear());
		searcField2.getTextField().addValueChangeListener(event -> {
			Notification.show(event.getValue());
			updateMessageListForCons(event.getValue());
		});
		return searcField2;
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