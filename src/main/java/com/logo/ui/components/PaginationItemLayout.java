package com.logo.ui.components;

import static com.vaadin.ui.themes.ValoTheme.MENUBAR_BORDERLESS;
import static com.vaadin.ui.themes.ValoTheme.MENUBAR_SMALL;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.github.appreciated.material.MaterialTheme;
import com.jarektoro.responsivelayout.ResponsiveColumn.ColumnComponentAlignment;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.logo.LogoresMainUI;
import com.logo.data.entity.ReProjectVersion;
import com.logo.data.entity.ReResource;
import com.logo.data.entity.ReResourceitem;
import com.logo.data.entity.ReUser;
import com.logo.data.repository.ReProjectVerisonRep;
import com.logo.data.repository.ReResourceRep;
import com.logo.data.repository.ReResourceitemRep;
import com.logo.ui.view.ResourceViewNew;
import com.logo.ui.window.ResourceItemWindow;
import com.logo.ui.window.ResourceWindow;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.converter.StrToIntegerConverter;
import com.logo.util.enums.OwnerProduct;
import com.logo.util.enums.ResourceCase;
import com.logo.util.enums.ResourceGroup;
import com.logo.util.enums.ResourceType;
import com.logo.util.enums.UserLayoutType;
import com.logo.util.search.SearchParam;
import com.vaadin.addon.pagination.Pagination;
import com.vaadin.addon.pagination.PaginationChangeListener;
import com.vaadin.addon.pagination.PaginationResource;
import com.vaadin.annotations.Viewport;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import eu.michaelvogt.vaadin.attribute.Attribute;

@Viewport("width=device-width, initial-scale=1") 
public class PaginationItemLayout extends ResponsiveLayout{

	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(PaginationItemLayout.class.getName());
	private static final long serialVersionUID = 1L;
	private int searchBy;
	private String searchFilter;
	private static Window menuWindow;
	private int x = 0;
	private int y = 100;
	private int width = 300;
	private int height = 500;

	VerticalLayout layoutPPP = new VerticalLayout();
	private final transient ReResourceRep resRepo;

	private final transient ReResourceitemRep reResourceitemRep;
	private final transient ReProjectVerisonRep reProjectVerisonRep;
	private final transient ReUser reUser;
	private ResourceViewNew resView;
	private VerticalLayout content;
	private SearchParam sParam;
	private boolean add;
	private List<ReProjectVersion> versionList;
	private List<String> versionStringList;
	
	public PaginationItemLayout(int searchBy, SearchParam sParam, String searchFilter, ResourceViewNew view, boolean add) {
		this.resRepo = LogoresMainUI.getMrepositorycontainer().getResRepo();
		this.searchBy = searchBy;
		this.searchFilter = searchFilter;
		this.reResourceitemRep = LogoresMainUI.getMrepositorycontainer().getReResourceitemRep();
		this.reProjectVerisonRep = LogoresMainUI.getMrepositorycontainer().getReProjectVerisonRep();
		this.reUser = (ReUser) VaadinSession.getCurrent().getAttribute("user");
		this.resView = view;
		this.add = add;
		this.sParam = sParam;
		setWidth("100%");
		//setHeight("95%");
		
		setScrollable(true);
		//withSpacing();
		withFullSize();
		
		content = generatePanelLayout();
		versionList = reProjectVerisonRep.findAll();
		versionStringList = versionList.stream().map(elem -> elem.getVersionnr()).collect(Collectors.toList());
		if (add) {
			ReResource reResource = findByResNr(searchFilter);
			addHeader(this, reResource, add);
		}

		addComponent(content);
		//setExpandRatio(content, 12.0f);
		getUI();
		UI.getCurrent().addClickListener(e -> {
			if ((!(e.getRelativeX() >= x && e.getRelativeX() < (x + width) && e.getRelativeY() >= y
					&& e.getRelativeY() < (y + height))) && (menuWindow != null)) {
				menuWindow.close();
			}
		});

		/**
		 * DataSource ds =
		 * LogoresMainUI.getMrepositorycontainer().getDataSource(); try { String
		 * query = "select createdon,resourcenr  from re_resources "; Connection
		 * con = ds.getConnection(); ResultSet rs =
		 * con.createStatement().executeQuery(query); while (rs.next()) { int
		 * coffeeName = rs.getInt("resourcenr"); Date createdon =
		 * rs.getDate("createdon"); System.out.println(coffeeName + "\t" +
		 * createdon); }
		 * 
		 * } catch (SQLException e1) { 
		 * e1.printStackTrace(); }
		 **/
	}

	private Pagination createPagination(long total, int page, int limit, long count) {
		final PaginationResource paginationResource = PaginationResource.newBuilder().setTotal(total).setPage(page)
				.setLimit(limit).build();
		final Pagination pagination = new Pagination(paginationResource);
		HorizontalLayout hLayout = (HorizontalLayout) pagination.getComponent(0);
		Label lab = (Label) hLayout.getComponent(0);
		lab.setValue("");
		pagination.setItemsPerPage(10, 20, 30, 50);
		pagination.addComponent(new Label(Long.toString(count)));
		return pagination;
	}

	public VerticalLayout generatePanelLayout() {
		final int page = 1;
		final int limit = 10;

		Page<com.logo.data.entity.ReResourceitem> resourceItems;
		resourceItems = preareCondition(0, 10);
		long totalCount = 0;
		if (resourceItems != null) {
			totalCount = resourceItems.getTotalElements();

			final long total = totalCount;

			ArrayDeque<ReResourceitem> newList = new ArrayDeque<>();
			newList.addAll(resourceItems.getContent());
			final Panel panel = createPanel(newList);
			final Pagination pagination = createPagination(total, page, limit, totalCount);
			pagination.addPageChangeListener(new PaginationChangeListener() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void changed(PaginationResource event) {
					Page<com.logo.data.entity.ReResourceitem> resourceItems;
					resourceItems = preareCondition(event.pageIndex(), event.limit());
					if (resourceItems != null) {
						pagination.setTotalCount(total);
						ArrayDeque<ReResourceitem> newList = new ArrayDeque<>();
						newList.addAll(resourceItems.getContent());
						GridLayout layout = changeContent(newList);
						panel.setContent(layout);
						panel.setScrollTop(0);
						newList.clear();
					}
				}
			});
			newList.clear();
			return createContentForPanel(panel, pagination);
		} else
			return new VerticalLayout();
	}

	private Page<com.logo.data.entity.ReResourceitem> preareCondition(int page, int size) {
		String nameilter = "";
		Page<com.logo.data.entity.ReResourceitem> resourceItems = null;
		if(searchBy == LogoResConstants.SEARCH_ALL)
		{
			nameilter = "%";
			resourceItems = getAllResItems(page, size, nameilter);
		}
		if(searchBy == LogoResConstants.SEARCH_RESOURCENR)
		{
			nameilter = searchFilter;
			resourceItems = searchByresourceNr(page, size, getPartValue(nameilter,1), getPartValue(nameilter,0));
		}
		if(searchBy == LogoResConstants.SEARCH_RESOURCEITEM)
		{
			resourceItems = searchByresourceParam(page, size, sParam);
		}
		if(searchBy == LogoResConstants.SEARCH_RESOURCEITEMALL)
		{
			resourceItems = searchByresourceParamAll(page, size, sParam);
		}

		return resourceItems;
	}

	private String getPartValue(String value, int part)
	{
		String resStr = "";
		String[] parts = value.split("->");
		if (parts.length > 0) {
			resStr = parts[part];
		}
		return resStr;
	}

	private VerticalLayout createContentForPanel(Panel panel, Pagination pagination) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setSpacing(true);
		layout.addComponents(panel, pagination);
		layout.setExpandRatio(panel, 2f);
		return layout;
	}

	private Panel createPanel(ArrayDeque<com.logo.data.entity.ReResourceitem> resourceitems) {
		final Panel panel = new Panel();
		panel.setWidth("100%");
		panel.setHeight("100%");
		GridLayout layout = changeContent(resourceitems);
		panel.setContent(layout);
		return panel;
	}

	private GridLayout changeContent(ArrayDeque<com.logo.data.entity.ReResourceitem> resourceitems) {
		GridLayout layout2 = new GridLayout();
		layout2.setWidth("100%");
		long start = System.currentTimeMillis();
		resourceitems.parallelStream().forEachOrdered(item -> generateLayout(layout2, item));
		long elapsed = System.currentTimeMillis() - start;
		String message = String.format("%4.2fs", elapsed / 1000.0);
		logger.log(Level.WARNING, "changeContent---->".concat(message), "");
		return layout2;
	}

	private void generateLayout(GridLayout detailLayout, ReResourceitem resourceitem) {
		VerticalLayout formLayout = new VerticalLayout();
		VerticalLayout gridLayout = new VerticalLayout();

		gridLayout.setSizeFull();

		createRow(gridLayout, resourceitem);

		formLayout.addComponents(gridLayout);
		detailLayout.addComponent(formLayout);
	}

	private void addHeader(ResponsiveLayout formLayout, ReResource reResource, boolean add) {
		if (add) {
			ResponsiveRow header = formLayout.addRow();
			header.withMargin(true).setHorizontalSpacing(true);
			header.setStyleName("card-hoverable-material-light-primary-color");
			header.setWidth("100%");
			
			
			Button addNewResource = new Button(LangHelper.getLocalizableMessage(LogoResConstants.ADDNEWRESOURCESTR));
			addNewResource.setIcon(VaadinIcons.PLUS);
			addNewResource.addStyleName(MaterialTheme.BUTTON_CUSTOM);

			Label text1 = new Label(reResource.getResourcegroup().name());
			Label text2 = new Label(Integer.toString(reResource.getResourcenr()));

			Button edit = new ButtonGenerator(LogoResConstants.EDITSTR);
			Button delete = new ButtonGenerator(LogoResConstants.DELETESTR);
			Button addNewResourceItem = new ButtonGenerator(LogoResConstants.ADDSTR);
			addNewResourceItem.setDescription(LangHelper.getLocalizableMessage(LogoResConstants.ADDNEWRESOURCEITMSTR));

			MenuBar headerMenuButton = new MenuBar();
			headerMenuButton.addStyleName(MENUBAR_BORDERLESS);
			headerMenuButton.addStyleName(MENUBAR_SMALL);
			headerMenuButton.addStyleName("no-indicator");
			MenuItem addItem = headerMenuButton.addItem("", VaadinIcons.ELLIPSIS_DOTS_V, null);
			addItem.addItem("Export resource", null);
			addItem.addItem("Import resource", null);
			addItem.addSeparator();
			addItem.addItem("Copy resource", null);

			edit.addClickListener(e -> {
				final  ResourceWindow window = new ResourceWindow(reResource,resView,false);
			        this.getUI().getCurrent().addWindow(window);
			});
			
			addNewResourceItem.addClickListener(e -> {
				final ResourceItemWindow window2 = new ResourceItemWindow(reResource, resView);
				this.getUI().getCurrent().addWindow(window2);
			});
			
			delete.addClickListener(e -> delete(reResource));

			HorizontalLayout buttonLayout = new HorizontalLayout();
			HorizontalLayout textLayout = new HorizontalLayout();

			
			int[] countArr = getCountForChart(reResource.getResourcenr(), reResource.getResourcegroup().name());
			LocChart chart = new LocChart(countArr[0], countArr[1]);
			
			buttonLayout.addComponents(edit, delete, addNewResourceItem, headerMenuButton);
			textLayout.addComponents(text1, text2, chart);
			
			header.addColumn().withDisplayRules(12, 10, 6, 6).withComponent(textLayout).setAlignment(ColumnComponentAlignment.LEFT);;
			header.addColumn().withDisplayRules(12, 10, 6, 6).withComponent(buttonLayout).setAlignment(ColumnComponentAlignment.RIGHT);;
			
			//header.setDefaultComponentAlignment(Alignment.TOP_LEFT);
		}
	}

	
	private void createResourceForm(HorizontalLayout header, ReResource resource) {
		Binder<ReResource> binder = new Binder<>(ReResource.class);
		binder.setBean(resource);

		List<ReResourceitem> items = reResourceitemRep.findByresourcerefEquals(resource.getId());
		GridLayout innerForm = new GridLayout(4, 6);
		innerForm.addStyleName(LogoResConstants.STYLE_GRID);
		innerForm.setWidth("100%");
		innerForm.setSpacing(false);
		innerForm.setMargin(false);
		
		HorizontalLayout resHedLayout = new HorizontalLayout();
		resHedLayout.setWidth("100%");
		resHedLayout.setHeight("70px");
		resHedLayout.addStyleName("card-hoverable-material-darkBlue-primary-color");

		Label resHedlabel = new Label("");
		resHedlabel.setCaption(Integer.toString(resource.getResourcenr()));
		resHedlabel.setCaptionAsHtml(true);
		resHedLayout.addComponents(resHedlabel);
		resHedLayout.setComponentAlignment(resHedlabel, Alignment.MIDDLE_CENTER);

		
		Panel form = new Panel();
		form.addStyleName(LogoResConstants.STYLE_IN_SLIDE_FADE);
		form.setHeight(100.0f, Unit.PERCENTAGE);
		form.setWidth(100.0f, Unit.PERCENTAGE);
		
		TextField resourcenr = new TextField(LangHelper.getLocalizableMessage(LogoResConstants.RESNRSTR));
		resourcenr.setWidth("200%");
		resourcenr.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		
		TextField description = new TextField(LangHelper.getLocalizableMessage(LogoResConstants.DESCSTR));
		description.setWidth("500%");
		description.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);

		Button save = new ButtonGenerator(LogoResConstants.SAVESTR);
		Button close = new ButtonGenerator(LogoResConstants.CANCELSTR);

		binder.forField(resourcenr).asRequired(LangHelper.getLocalizableMessage(LogoResConstants.RESNRNOTEMTYSTR))
				.withConverter(new StrToIntegerConverter(LangHelper.getLocalizableMessage(LogoResConstants.MUSTNUMBER)))
				// .withValidator(number -> number <= 0, "Person must be born in
				// the 20th century")
				.bind(ReResource::getResourcenr, ReResource::setResourcenr);

		binder.forField(description).bind(ReResource::getDescription, ReResource::setDescription);

		SpellChecComboBox<ResourceGroup> resourceGroupCombo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(LogoResConstants.RESGRPSTR));
		SpellChecComboBox<ResourceType> resourceTypeCombo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(LogoResConstants.RESTYPESTR));
		SpellChecComboBox<ResourceCase> resourceCaseCombo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(LogoResConstants.RESCASESTR));
		SpellChecComboBox<OwnerProduct> ownerProductCombo = new SpellChecComboBox<>(LangHelper.getLocalizableMessage(LogoResConstants.OWNERPRODUCT));

		resourceGroupCombo.setWidth("300%");
		resourceTypeCombo.setWidth("300%");
		resourceCaseCombo.setWidth("300%");
		ownerProductCombo.setWidth("300%");
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

		Attribute spellcheckAttr1 = new Attribute(LogoResConstants.SPELLCHECK, LogoResConstants.FALSESTR);
		Attribute spellcheckAttr2 = new Attribute(LogoResConstants.SPELLCHECK, LogoResConstants.FALSESTR);
		Attribute spellcheckAttr3 = new Attribute(LogoResConstants.SPELLCHECK, LogoResConstants.FALSESTR);
		Attribute spellcheckAttr4 = new Attribute(LogoResConstants.SPELLCHECK, LogoResConstants.FALSESTR);

		spellcheckAttr1.extend(resourceGroupCombo);
		spellcheckAttr2.extend(resourceTypeCombo);
		spellcheckAttr3.extend(resourceCaseCombo);
		spellcheckAttr4.extend(ownerProductCombo);

		binder.bind(resourceGroupCombo, ReResource::getResourcegroup, ReResource::setResourcegroup);
		binder.bind(resourceTypeCombo, ReResource::getResourcetype, ReResource::setResourcetype);
		binder.bind(resourceCaseCombo, ReResource::getResourcecase, ReResource::setResourcecase);
		binder.bind(ownerProductCombo, ReResource::getOwnerproduct, ReResource::setOwnerproduct);

		TwinColSelect<String> twinColl = new TwinColSelect<>();

		twinColl.setItems(versionStringList); 
		
		List<String> selectedStringList = resource.getReProjectVersion().stream().map(elem -> elem.getVersionnr()).collect(Collectors.toList());
		Set<String> selectedSet = null; 
		if(selectedStringList.size() >0 )
		{
			selectedSet = new HashSet<>(selectedStringList.subList(0, selectedStringList.size()));
			twinColl.setValue(selectedSet);
		}
		
		save.setClickShortcut(KeyCode.ENTER);
		twinColl.setLeftColumnCaption("Version");
		twinColl.setRightColumnCaption("Selected Version");
		twinColl.setSizeFull();
		HorizontalLayout buttons = new HorizontalLayout(save, close);

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
		
		innerForm.addComponent(resHedLayout, 0, 0, 3, 0);
		innerForm.addComponent(col01, 0, 1, 0, 4);
		innerForm.addComponent(twinColl, 3, 2, 3, 4);
		innerForm.addComponent(buttons, 3, 5, 3, 5);

		form.setContent(innerForm);

		innerForm.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);

		List<Component> components = new ArrayList<>();
		for (int i = 0; i < header.getComponentCount(); i++) {
			Component comp = header.getComponent(i);
			components.add(comp);
		}

		header.removeAllComponents();
		header.addComponent(form);
		header.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
		header.setExpandRatio(form, 1.0f);

		resHedLayout.addLayoutClickListener(e -> close.click());
		
		close.addClickListener(event -> {
			Label label1 = (Label) components.get(0);
			Label label2 = (Label) components.get(1);
			LocChart gLayout = (LocChart) components.get(2);
			HorizontalLayout buttonLayout = (HorizontalLayout) components.get(3);
			label1.setValue(resource.getResourcegroup().toString());
			label2.setValue(Integer.toString(resource.getResourcenr()));
			header.removeComponent(form);
			for (x = 0; x < components.size(); x++) {
				header.addComponent(components.get(x));
			}
			header.setComponentAlignment(gLayout, Alignment.MIDDLE_CENTER);
			header.setComponentAlignment(buttonLayout, Alignment.MIDDLE_RIGHT);
		});
		save.addClickListener(event -> {
			resource.setReResourceitems(items);
			Set<ReProjectVersion> reProjectVersions = resource.getReProjectVersion();
			
			Set<String> selectedSetVal = twinColl.getValue();
			Iterator<String> it = selectedSetVal.iterator();

			while (it.hasNext()) {
				String versNr = it.next();
				ReProjectVersion version = findByVersNr(versNr);
				
				if (!inList(reProjectVersions,version))
					reProjectVersions.add(version);
			}
			
			resource.setReProjectVersion(reProjectVersions);
			resRepo.save(resource);
			Label label1 = (Label) components.get(0);
			Label label2 = (Label) components.get(1);
			LocChart gLayout = (LocChart) components.get(2);
			HorizontalLayout buttonLayout = (HorizontalLayout) components.get(3);
			label1.setValue(resource.getResourcegroup().toString());
			label2.setValue(Integer.toString(resource.getResourcenr()));
			header.removeComponent(form);
			for (int i = 0; i < components.size(); i++) {
				header.addComponent(components.get(i));
			}
			header.setComponentAlignment(gLayout, Alignment.MIDDLE_CENTER);
			header.setComponentAlignment(buttonLayout, Alignment.MIDDLE_RIGHT);
		});
		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void error(com.vaadin.server.ErrorEvent event) {
				StringBuilder cause = new StringBuilder("<b>The click failed because:</b><br/>");
				for (Throwable t = event.getThrowable(); t != null; t = t.getCause())
					if (t.getCause() == null) // We're at final cause
						cause.append(t.getClass().getName()).append("<br/>");

				logger.log(Level.SEVERE, event.getThrowable().getMessage(), event.getThrowable());
				doDefault(event);
			}
		});
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

	Label createSeperator() {
		Label hSep = new Label("%57");
		hSep.addStyleName("horizontal-separator4");
		hSep.setHeight("20px");
		hSep.setWidth("100%");
		return hSep;

	}

	
	public void delete(ReResource reResource) {
		resRepo.delete(reResource);
		resView.createResoucePageForAll();
	}

	public ReResource findByResNr(String resNr) {
		String rNr = getPartValue(resNr, 1);
		String rGroup = getPartValue(resNr, 0);
		return resRepo.findByresourceNr(Integer.parseInt(rNr),rGroup);
	}

	public ReProjectVersion findByVersNr(String versNr)
	{
		return versionList.stream().filter(x -> x.getVersionnr().equalsIgnoreCase(versNr)).findFirst().orElse(null);
	}
	
	private void createRow(VerticalLayout gridLayout, ReResourceitem reResourceitem) {
		boolean isVertical = reUser.getDefaultorientation() == UserLayoutType.V;
		if (add)
			gridLayout.addComponent(new RowLayout(null, reResourceitem, false, isVertical, resView, true, reUser));
		else
			gridLayout.addComponent(new RowLayout(null, reResourceitem, false, isVertical, resView, false, reUser));
	}

	@SuppressWarnings("unchecked")
	public Page<com.logo.data.entity.ReResourceitem> searchByresourcereTR(int page, int size, String nameFilter) {
		Pageable pageable = new PageRequest(page, size, null);
		return reResourceitemRep.searchByresourcereTR(nameFilter, pageable);
	}

	public Page<com.logo.data.entity.ReResourceitem> searchByresourceParam(int page, int size, SearchParam sParam) {
		Pageable pageable = new PageRequest(page, size, null);
		return reResourceitemRep.searchByParam(pageable, sParam);
	}

	public Page<com.logo.data.entity.ReResourceitem> searchByresourceParamAll(int page, int size, SearchParam sParam) {
		Pageable pageable = new PageRequest(page, size, null);
		return reResourceitemRep.searchByParamAll(pageable, sParam);
	}

	public Page<com.logo.data.entity.ReResourceitem> searchByresourceNr(int page, int size, String nameFilter, String resourcegroup) {
		Pageable pageable = new PageRequest(page, size, null);
		return reResourceitemRep.searchByresourceNr(pageable,nameFilter,resourcegroup);
	}

	@SuppressWarnings("unchecked")
	public Page<com.logo.data.entity.ReResourceitem> getAllResItems(int page, int size, String nameFilter) {
		Pageable pageable = new PageRequest(page, size, null);
		return reResourceitemRep.getAllResItems(nameFilter, pageable);
	}

	public int[] getCountForChart(int resNr, String resGrp) {
		int[] countArr = new int[] { 0, 0 };
		List<Object> lst = reResourceitemRep.getCountForChart(resNr, resGrp);
		if (lst != null) {
			Object[] countObj = (Object[]) lst.get(0);
			countArr[0] = (int) countObj[1];
			countArr[1] = (int) countObj[2];
		}
		return countArr;
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
