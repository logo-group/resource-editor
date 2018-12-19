package com.logo.ui.components;

import static com.vaadin.ui.themes.ValoTheme.MENUBAR_BORDERLESS;
import static com.vaadin.ui.themes.ValoTheme.MENUBAR_SMALL;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.jarektoro.responsivelayout.ResponsiveColumn.ColumnComponentAlignment;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.logo.LogoresApplication;
import com.logo.LogoresMainUI;
import com.logo.data.entity.ReProjectVersion;
import com.logo.data.entity.ReResource;
import com.logo.data.entity.ReResourceitem;
import com.logo.data.entity.ReUser;
import com.logo.data.repository.ReProjectVerisonRep;
import com.logo.data.repository.ReResourceRep;
import com.logo.data.repository.ReResourceitemRep;
import com.logo.data.repository.ReUserRep;
import com.logo.ui.view.ResourceViewNew;
import com.logo.ui.window.ResourceCopyWindow;
import com.logo.ui.window.ResourceItemWindow;
import com.logo.ui.window.ResourceWindow;
import com.logo.util.ConfirmationListener;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.logo.util.ResourceEditorDialog;
import com.logo.util.enums.UserLayoutType;
import com.logo.util.search.SearchParam;
import com.vaadin.addon.pagination.Pagination;
import com.vaadin.addon.pagination.PaginationChangeListener;
import com.vaadin.addon.pagination.PaginationResource;
import com.vaadin.annotations.Viewport;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Viewport("width=device-width, initial-scale=1")
public class PaginationItemLayout extends ResponsiveLayout {

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
	private ReResourceRep resRepo;
	private ReResourceitemRep reResourceitemRep;
	private ReProjectVerisonRep reProjectVerisonRep;
	private ReUserRep userRepo;
	private final transient ReUser reUser;
	private ResourceViewNew resView;
	private VerticalLayout content;
	private SearchParam sParam;
	private boolean add;
	private List<ReProjectVersion> versionList;

	public PaginationItemLayout(int searchBy, SearchParam sParam, String searchFilter, ResourceViewNew view,
			boolean add, ReResourceRep resRepo, ReResourceitemRep reResourceitemRep,
			ReProjectVerisonRep reProjectVerisonRep, ReUserRep userRepo) {
		this.resRepo = resRepo;
		this.reResourceitemRep = reResourceitemRep;
		this.reProjectVerisonRep = reProjectVerisonRep;
		this.userRepo = userRepo;
		this.searchBy = searchBy;
		this.searchFilter = searchFilter;
		this.reUser = (ReUser) VaadinSession.getCurrent().getAttribute("user");
		this.resView = view;
		this.add = add;
		this.sParam = sParam;
		setWidth("100%");

		setScrollable(true);
		withFullSize();

		content = generatePanelLayout();
		versionList = reProjectVerisonRep.findAll();
		if (add) {
			ReResource reResource = findByResNr(searchFilter);
			addHeader(this, reResource, add);
		}

		addComponent(content);
		getUI();
		UI.getCurrent().addClickListener(e -> {
			if ((!(e.getRelativeX() >= x && e.getRelativeX() < (x + width) && e.getRelativeY() >= y
					&& e.getRelativeY() < (y + height))) && (menuWindow != null)) {
				menuWindow.close();
			}
		});

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
		if (searchBy == LogoResConstants.SEARCH_ALL) {
			nameilter = "%";
			resourceItems = getAllResItems(page, size, nameilter);
		}
		if (searchBy == LogoResConstants.SEARCH_RESOURCENR) {
			nameilter = searchFilter;
			resourceItems = searchByresourceNr(page, size, getPartValue(nameilter, 1), getPartValue(nameilter, 0));
		}
		if (searchBy == LogoResConstants.SEARCH_RESOURCEITEM) {
			resourceItems = searchByresourceParam(page, size, sParam);
		}
		if (searchBy == LogoResConstants.SEARCH_RESOURCEITEMALL) {
			resourceItems = searchByresourceParamAll(page, size, sParam);
		}

		return resourceItems;
	}

	private String getPartValue(String value, int part) {
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
			addItem.addItem(LangHelper.getLocalizableMessage(LogoResConstants.COPYSTR), VaadinIcons.COPY,
					copyClick(reResource));
			addItem.addItem(LangHelper.getLocalizableMessage(LogoResConstants.REORDERSTR), VaadinIcons.BULLETS,
					reOrderClick(reResource));
			addItem.addSeparator();
			addItem.addItem("Export resource", null);
			addItem.addItem("Import resource", null);

			edit.addClickListener(e -> {
				final ResourceWindow window = new ResourceWindow(reResource, resView, false, reProjectVerisonRep,
						resRepo, userRepo);
				UI.getCurrent().addWindow(window);
			});

			addNewResourceItem.addClickListener(e -> {
				final ResourceItemWindow window2 = new ResourceItemWindow(reResource, resView, reResourceitemRep);
				UI.getCurrent().addWindow(window2);
			});
			delete.addClickListener(e -> delete(reResource));

			HorizontalLayout buttonLayout = new HorizontalLayout();
			HorizontalLayout textLayout = new HorizontalLayout();

			int[] countArr = getCountForChart(reResource.getResourcenr(), reResource.getResourcegroup().name());
			LocChart chart = new LocChart(countArr[0], countArr[1]);

			buttonLayout.addComponents(edit, delete, addNewResourceItem, headerMenuButton);
			textLayout.addComponents(text1, text2, chart);

			header.addColumn().withDisplayRules(12, 10, 6, 6).withComponent(textLayout)
					.setAlignment(ColumnComponentAlignment.LEFT);
			;
			header.addColumn().withDisplayRules(12, 10, 6, 6).withComponent(buttonLayout)
					.setAlignment(ColumnComponentAlignment.RIGHT);
			;

			// header.setDefaultComponentAlignment(Alignment.TOP_LEFT);
		}
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
		return resRepo.findByresourceNr(Integer.parseInt(rNr), rGroup);
	}

	public ReProjectVersion findByVersNr(String versNr) {
		return versionList.stream().filter(x -> x.getVersionnr().equalsIgnoreCase(versNr)).findFirst().orElse(null);
	}

	private void createRow(VerticalLayout gridLayout, ReResourceitem reResourceitem) {
		boolean isVertical = reUser.getDefaultorientation() == UserLayoutType.V;
		if (add)
			gridLayout.addComponent(new RowLayout(null, reResourceitem, false, isVertical, resView, true, reUser,
					resRepo, reResourceitemRep, userRepo));
		else
			gridLayout.addComponent(new RowLayout(null, reResourceitem, false, isVertical, resView, false, reUser,
					resRepo, reResourceitemRep, userRepo));
	}

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

	public Page<com.logo.data.entity.ReResourceitem> searchByresourceNr(int page, int size, String nameFilter,
			String resourcegroup) {
		Pageable pageable = new PageRequest(page, size, null);
		return reResourceitemRep.searchByresourceNr(pageable, nameFilter, resourcegroup);
	}

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

	private void reOrderItems(ReResource resource) {
		List<ReResourceitem> itemList = resource.getReResourceitem();
		for (int i = 0; i < itemList.size(); i++) {
			ReResourceitem item = itemList.get(i);
			item.setOrdernr(i + 1);
		}
		resRepo.save(resource);
		refreshLayout();
	}

	private void refreshLayout() {
		VerticalLayout refreshedContent = generatePanelLayout();
		this.replaceComponent(content, refreshedContent);
		content = refreshedContent;
	}

	private Command reOrderClick(ReResource resource) {
		Command reOrderClickCommand = new Command() {
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				ResourceEditorDialog.confirm(LogoresMainUI.getCurrent(), new ConfirmationListener() {

					@Override
					public void onConfirm() {
						reOrderItems(resource);
					}

					@Override
					public void onCancel() {
					}
				}, LangHelper.getLocalizableMessage(LogoResConstants.REORDER_NOTIFICATION),
						LangHelper.getLocalizableMessage(LogoResConstants.DIALOG_OK),
						LangHelper.getLocalizableMessage(LogoResConstants.DIALOG_CANCEL));
			}
		};
		return reOrderClickCommand;
	}

	private Command copyClick(ReResource resource) {
		Command copyClickCommand = new Command() {
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				final ResourceCopyWindow window = LogoresApplication.getBeanFactory().getBean(ResourceCopyWindow.class,
						resource, LogoresApplication.getBeanFactory().getBean(ResourceViewNew.class));
				UI.getCurrent().addWindow(window);
			}
		};
		return copyClickCommand;
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