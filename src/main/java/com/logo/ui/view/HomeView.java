package com.logo.ui.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addons.autocomplete.AutocompleteExtension;

import com.github.appreciated.material.MaterialTheme;
import com.logo.LogoresMainUI;
import com.logo.data.repository.ReResourceRep;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = HomeView.VIEW_NAME)
public class HomeView extends HorizontalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "HomeView";

	@Autowired
	public transient ReResourceRep resRepo;

	private Button addNewResource = new Button(LangHelper.getLocalizableMessage(LogoResConstants.ADDNEWRESOURCESTR));
	public Button getAddNewResource() {
		return addNewResource;
	}

	public HomeView(ResourceViewNew resView) {
		resRepo = LogoresMainUI.getMrepositorycontainer().getResRepo();
		setSizeFull();
		HorizontalLayout searchLayout = new HorizontalLayout();
		
		Panel resPanel = new Panel();
		resPanel.setSizeUndefined();
		
		VerticalLayout resListLayout = new VerticalLayout();
		resListLayout.setSizeUndefined();
		resListLayout.setWidth("100%");

		TextField searchField = new TextField();
		searchField.setSizeUndefined();
		searchField.setPlaceholder("search...");
		searchField.addStyleName(LogoResConstants.STYLE_TEXTFIEL_SEARCH);
		searchField.addStyleName(ValoTheme.TEXTFIELD_LARGE);
		searchField.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		searchField.setIcon(VaadinIcons.SEARCH);
		searchField.setWidth("100%");
		
		Button searchButton = new Button("");
		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addStyleName(MaterialTheme.BUTTON_BORDER+ " "+ MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);
		
		AutocompleteExtension<String> resNrExtension = new AutocompleteExtension<>(searchField);

		resNrExtension.setSuggestionGenerator(this::suggestResource);

		resNrExtension.addSuggestionSelectListener(event ->{
			event.getSelectedItem().ifPresent(Notification::show);
			searchField.setValue(event.getSelectedValue());
			searchButton.click();
		});

		searchButton.addClickListener(event -> {
			resView.createResoucePage(searchField.getValue(),true);
			searchField.setValue("");
		});
		
		addNewResource.setIcon(VaadinIcons.PLUS);
		addNewResource.addStyleName(MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);
		
		VerticalLayout chartLayout = new VerticalLayout();
		chartLayout.setSizeFull();
		chartLayout.setWidth("100%");
		chartLayout.setHeight("100%");

		ReportView reportView = new ReportView(resView);
		chartLayout.addComponents(reportView);
		chartLayout.setComponentAlignment(reportView, Alignment.TOP_RIGHT);
		searchLayout.addComponents(chartLayout);
		addComponentsAndExpand(chartLayout);

		setExpandRatio(chartLayout, 1.0f);
		setComponentAlignment(chartLayout, Alignment.TOP_RIGHT);
	}

	private String getSuggestValue(String value)
	{
		String resStr = "";
		String[] parts = value.split("->");
		if (parts.length > 0) {
			resStr = parts[1];
		}
		return resStr;
	}

	private List<String> suggestResource(String query, int cap) {
		if(query.length()>2 && StringUtils.isNumeric(query))
			return getResources(query).stream().filter(p -> p.contains(query)).limit(cap).collect(Collectors.toList());
		else
			return Arrays.asList("","");
	}

	public  List<String> getResources(String resNr) {
		return findByResNrLike(resNr);
	}

	public List<String> findByResNrLike(String resNrFilter) {
		return resRepo.findByresourceNrLike(Integer.parseInt(resNrFilter));
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
