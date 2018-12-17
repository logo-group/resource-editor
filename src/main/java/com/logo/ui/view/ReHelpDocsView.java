package com.logo.ui.view;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.appreciated.material.MaterialTheme;
import com.logo.data.entity.ReHelpDocs;
import com.logo.data.repository.ReHelpDocsRep;
import com.logo.ui.components.TextFieldWithButton;
import com.logo.ui.form.HelpDocForm;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;

@UIScope
@SpringView(name = ReHelpDocsView.VIEW_NAME)
public class ReHelpDocsView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "ReHelpDocsView";

	public ReHelpDocsRep reHelpDocsRep;

	private Grid<ReHelpDocs> helpdocsGrid = new Grid<>(ReHelpDocs.class);

	private HelpDocForm helpDocForm = new HelpDocForm(this);

	@Autowired
	public ReHelpDocsView(ReHelpDocsRep reHelpDocsRep) {
		this.reHelpDocsRep = reHelpDocsRep;
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
		helpDocForm.setVisible(false);

		helpdocsGrid.setColumns("docname", "doctitle", "doctype");

		HeaderRow row = helpdocsGrid.appendHeaderRow();
		row.join("docname").setHtml("<b>docname</b>");

		HorizontalLayout header = new HorizontalLayout();

		HorizontalLayout grid = new HorizontalLayout();
		grid.addStyleName(MaterialTheme.CARD_HOVERABLE);

		helpdocsGrid.setWidth("50%");
		helpdocsGrid.setHeight("100%");
		grid.setWidth("100%");
		grid.setHeight("100%");

		Button backButton = new Button("Home");
		backButton.setIcon(VaadinIcons.ARROW_BACKWARD);
		backButton.addStyleName(MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " "
				+ MaterialTheme.BUTTON_BORDERLESS_COLORED);

		Button addNewMsg = new Button("Add New Helpdoc");
		addNewMsg.setIcon(VaadinIcons.PLUS);
		addNewMsg.addStyleName(MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);

		backButton.addClickListener(e -> getUI().getNavigator().navigateTo(ResourceViewNew.VIEW_NAME));

		header.addComponent(addNewMsg);

		addNewMsg.addClickListener(e -> {
			helpDocForm.setVisible(true);
			helpDocForm.setReHelpDoc(new ReHelpDocs());
		});

		addComponent(header);
		grid.addComponentsAndExpand(helpdocsGrid);
		grid.addComponentsAndExpand(helpDocForm);
		addComponentsAndExpand(grid);
		addGridFilters();
		updateHelpListForDocName("");

		helpdocsGrid.setSelectionMode(SelectionMode.SINGLE);

		helpdocsGrid.addSelectionListener(new SelectionListener<ReHelpDocs>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectionChange(SelectionEvent<ReHelpDocs> event) {
				Optional<ReHelpDocs> value = event.getFirstSelectedItem();
				if (value.isPresent()) {
					helpDocForm.setVisible(true);
					helpDocForm.setReHelpDoc(value.get());
				}
			}
		});

	}

	public void updateHelpListForDocName(String value) {
		String likeFilter = "%" + value + "%";
		List<ReHelpDocs> reHelpDocs = reHelpDocsRep.findByDocnameLikeIgnoreCase(likeFilter);
		helpdocsGrid.setItems(reHelpDocs);
	}

	public void updateMessageListForDocTitle(String value) {
		String likeFilter = "%" + value + "%";
		List<ReHelpDocs> reHelpDocs = reHelpDocsRep.findByDoctitleLikeIgnoreCase(likeFilter);
		helpdocsGrid.setItems(reHelpDocs);
	}

	public void refreshGrid() {
		helpdocsGrid.clearSortOrder();
	}

	private void addGridFilters() {
		if (helpdocsGrid.getHeaderRowCount() > 1) {
			HeaderRow filterRow1 = helpdocsGrid.getHeaderRow(1);
			helpdocsGrid.removeHeaderRow(filterRow1);
		}
		final HeaderRow filterRow = helpdocsGrid.appendHeaderRow();
		for (final Column<ReHelpDocs, ?> column : helpdocsGrid.getColumns()) {
			final HeaderCell headerCell = filterRow.getCell(column);
			if ("docname".equals(column.getId())) {
				headerCell.setComponent(createFilterTextFieldForDocName());
			} else if ("doctitle".equals(column.getId())) {
				headerCell.setComponent(createFilterTextFieldForTitle());
			} else if ("doctype".equals(column.getId())) {
			}
		}
	}

	private HorizontalLayout createFilterTextFieldForDocName() {
		TextFieldWithButton searcField1 = new TextFieldWithButton("", VaadinIcons.CLOSE);
		searcField1.getButton().addClickListener(e -> searcField1.getTextField().clear());

		searcField1.getTextField().addValueChangeListener(event -> {
			Notification.show(event.getValue());
			updateHelpListForDocName(event.getValue());
		});

		return searcField1;
	}

	private HorizontalLayout createFilterTextFieldForTitle() {
		TextFieldWithButton searcField2 = new TextFieldWithButton("", VaadinIcons.CLOSE);
		searcField2.getButton().addClickListener(e -> searcField2.getTextField().clear());
		searcField2.getTextField().addValueChangeListener(event -> {
			Notification.show(event.getValue());
			updateMessageListForDocTitle(event.getValue());
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