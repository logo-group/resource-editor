package com.logo.ui.view;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.config.PieChartConfig;
import com.byteowls.vaadin.chartjs.config.PolarAreaChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.PieDataset;
import com.byteowls.vaadin.chartjs.data.PolarAreaDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.scale.RadialLinearScale;
import com.github.appreciated.material.MaterialTheme;
import com.logo.LogoresMainUI;
import com.logo.data.entity.ReResource;
import com.logo.data.entity.ReUser;
import com.logo.data.repository.ReResourceRep;
import com.logo.data.repository.ReUserRep;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.vaadin.data.Binder;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class ReportView extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "ReportView";

	@Autowired
	public transient ReUserRep userRepo;

	@Autowired
	public transient ReResourceRep resRepo;

	private ResourceViewNew resView;

	public ReportView(ResourceViewNew resView) {
		userRepo = LogoresMainUI.getMrepositorycontainer().getReUserRep();
		resRepo = LogoresMainUI.getMrepositorycontainer().getResRepo();
		this.resView = resView;
		setSizeFull();
		setWidth("100%");
		setHeight("100%");

		GridLayout coot = new GridLayout(5, 3);
		coot.setSizeUndefined();
		coot.setWidth("1400px");

		HorizontalLayout main = new HorizontalLayout();
		main.setSizeUndefined();
		main.setWidth("100%");

		setContent(main);

		VerticalLayout leftL = new VerticalLayout();
		leftL.setSizeFull();
		leftL.setWidth("100%");
		leftL.setHeight("100%");

		VerticalLayout rightL = new VerticalLayout();
		rightL.setSizeFull();
		rightL.setWidth("100%");
		rightL.setHeight("100%");

		main.addComponent(leftL);
		main.addComponent(rightL);

		main.setExpandRatio(leftL, 1.0f);
		main.setExpandRatio(rightL, 1.0f);

		ChartJs resCountChart = createResCountChart();
		ChartJs langCountChart = createLangCountChart();
		langCountChart.setSizeFull();

		leftL.addComponents(resCountChart, langCountChart);
		leftL.setComponentAlignment(resCountChart, Alignment.TOP_CENTER);

		List<ReResource> resList = resRepo.getByresourceTop3();

		ReResource reResource0 = resList.get(0);
		ReResource reResource1 = resList.get(1);
		ReResource reResource2 = resList.get(2);
		HorizontalLayout resForm1 = panelContentVer(reResource0);
		HorizontalLayout resForm2 = panelContentVer(reResource1);
		HorizontalLayout resForm3 = panelContentVer(reResource2);

		rightL.addComponents(resForm1, resForm2, resForm3);
		rightL.setComponentAlignment(resForm1, Alignment.TOP_CENTER);
		rightL.setComponentAlignment(resForm2, Alignment.TOP_CENTER);
		rightL.setComponentAlignment(resForm3, Alignment.TOP_CENTER);

	}

	public ChartJs createResCountChart() {
		PieChartConfig resCountConfig = new PieChartConfig();

		resCountConfig.options().responsive(true).title().display(true)
				.text(LangHelper.getLocalizableMessage(LogoResConstants.LOGORESCOUNTSTR)).fontSize(20)
				.position(Position.TOP).fontColor("#a50000").and().animation().animateScale(true).animateRotate(true)
				.and().legend().position(Position.LEFT).and();

		getCountForResCountChart(resCountConfig);
		ChartJs resCountChart = new ChartJs(resCountConfig);
		return resCountChart;
	}

	public ChartJs createLangCountChart() {
		BarChartConfig barConfig = new BarChartConfig();
		barConfig.options().responsive(true).title().display(true)
				.text(LangHelper.getLocalizableMessage(LogoResConstants.LOGOLANGCOUNTSTR)).fontSize(20)
				.position(Position.TOP).fontColor("#a50000").and().animation().and().legend().position(Position.TOP)
				.display(false).and();

		getCountForLangCountChart(barConfig);
		ChartJs langCountChart = new ChartJs(barConfig);
		return langCountChart;
	}

	public ChartJs createMiscCountChart() {
		PolarAreaChartConfig config1 = new PolarAreaChartConfig();
		config1.data().labels("Red", "Green", "Yellow", "Grey", "Dark Grey")
				.addDataset(new PolarAreaDataset().label("My dataset").backgroundColor("#455A64", "#FFC107", "#FFFFFF",
						"#757575", "#CFD8DC", "#F0DC9D", "#5BB1C9"))
				.and();

		config1.options().responsive(true).title().display(true).text("Chart.js Polar Area Chart").fontSize(20)
				.position(Position.TOP).fontColor("#a50000").and()
				.scale(new RadialLinearScale().ticks().beginAtZero(true).and().reverse(false)).animation()
				.animateScale(true).animateRotate(false).and().legend().position(Position.LEFT).and();

		PolarAreaDataset lds3 = (PolarAreaDataset) config1.data().getDatasets().get(0);
		List<Double> data3 = new ArrayList<>();
		data3.add((double) (Math.round(1000)));
		data3.add((double) (Math.round(900)));
		data3.add((double) (Math.round(800)));
		data3.add((double) (Math.round(700)));
		data3.add((double) (Math.round(600)));
		lds3.dataAsList(data3);
		ChartJs chart3 = new ChartJs(config1);
		return chart3;
	}

	public HorizontalLayout panelContentVer(ReResource resource) {
		HorizontalLayout header = new HorizontalLayout();

		header.setSizeFull();
		header.setWidth("100%");
		Binder<ReResource> binder = new Binder<>(ReResource.class);
		binder.setBean(resource);

		VerticalLayout innerForm = new VerticalLayout();
		innerForm.addStyleName(MaterialTheme.CARD_HOVERABLE);

		HorizontalLayout resHedLayout = new HorizontalLayout();
		resHedLayout.setWidth("100%");
		resHedLayout.setHeight("70px");
		resHedLayout.addStyleName("card-hoverable-material-darkBlue-primary-color");

		Label resHedlabel = new Label("");
		resHedlabel.setCaption(resource.getResourcegroup().name() + "-" + Integer.toString(resource.getResourcenr()));
		resHedlabel.setCaptionAsHtml(true);
		resHedLayout.addComponents(resHedlabel);
		resHedLayout.setComponentAlignment(resHedlabel, Alignment.MIDDLE_CENTER);

		Panel form = new Panel();
		form.addStyleName(LogoResConstants.STYLE_IN_SLIDE_FADE);
		form.setHeight(80.0f, Unit.PERCENTAGE);
		form.setWidth(100.0f, Unit.PERCENTAGE);

		Label description = new Label();
		description.setWidth("100%");

		description.setValue("Description: " + resource.getDescription());

		Label createdBy = new Label();
		createdBy.setWidth("100%");

		Label modifieddBy = new Label();
		modifieddBy.setWidth("100%");

		ReUser user = getUser(resource.getCreatedby());
		ReUser userMod = getUser(resource.getModifiedby());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm:ss");
		createdBy.setValue("CreatedBy -> " + user.getName() + " " + user.getSurname() + "----" + "CreatedOn -> "
				+ resource.getCreatedon().format(formatter));
		modifieddBy.setValue("ModifiedBy -> " + userMod.getName() + " " + userMod.getSurname() + "----"
				+ "ModifiedOn -> " + resource.getModifiedon().format(formatter));

		FormLayout col01 = new FormLayout();
		col01.setSizeFull();
		col01.setSpacing(true);
		col01.setMargin(true);
		col01.setWidth("100%");
		col01.setHeight("100%");
		col01.addComponent(description);
		col01.addComponent(createdBy);
		col01.addComponent(modifieddBy);

		innerForm.addComponent(resHedLayout);
		innerForm.addComponent(col01);

		header.removeAllComponents();
		header.addComponent(innerForm);

		resHedLayout.addLayoutClickListener(e -> {
			String filter = resource.getResourcegroup().name() + "->" + Integer.toString(resource.getResourcenr());
			resView.createResoucePage(filter, true);
		});
		return header;
	}

	public ReUser getUser(int id) {
		return userRepo.findByid(id);
	}

	public void getCountForResCountChart(PieChartConfig resCountConfig) {
		List<Object> lst = resRepo.getResGroupCount();
		List<String> labelLst = new ArrayList<>();
		List<Double> dataLst = new ArrayList<>();

		if (lst != null) {
			for (int i = 0; i < lst.size(); i++) {
				Object[] queryObject = (Object[]) lst.get(i);
				String desc = (String) queryObject[0];
				double cnt = (double) queryObject[1];
				labelLst.add(desc);
				dataLst.add(cnt);
			}
		}
		resCountConfig.data().labelsAsList(labelLst).addDataset(new PieDataset().label("")).and();
		PieDataset resCountLds = (PieDataset) resCountConfig.data().getDatasets().get(0);
		resCountLds.dataAsList(dataLst);
		String[] colors = new String[] { "#455A64", "#FFC107", "#FFFFFF", "#757575", "#CFD8DC", "#F0DC9D", "#5BB1C9" };
		resCountLds.backgroundColor(colors);
	}

	public void getCountForLangCountChart(BarChartConfig resCountConfig) {
		List<Object> lst = resRepo.getResLangCount();
		List<String> labelLst = new ArrayList<>();
		List<Double> dataLst = new ArrayList<>();

		labelLst.add(LogoResConstants.RE_TURKISHTR_NAME);
		labelLst.add(LogoResConstants.RE_ALBANIANKV_NAME);
		labelLst.add(LogoResConstants.RE_ARABICEG_NAME);
		labelLst.add(LogoResConstants.RE_ARABICJO_NAME);
		labelLst.add(LogoResConstants.RE_ARABICSA_NAME);
		labelLst.add(LogoResConstants.RE_AZERBAIJANIAZ_NAME);
		labelLst.add(LogoResConstants.RE_BULGARIANBG_NAME);
		labelLst.add(LogoResConstants.RE_ENGLISHUS_NAME);
		labelLst.add(LogoResConstants.RE_FRENCHFR_NAME);
		labelLst.add(LogoResConstants.RE_GEORGIANGE_NAME);
		labelLst.add(LogoResConstants.RE_GERMANDE_NAME);
		labelLst.add(LogoResConstants.RE_PERSIANIR_NAME);
		labelLst.add(LogoResConstants.RE_ROMANIANRO_NAME);
		labelLst.add(LogoResConstants.RE_RUSSIANRU_NAME);
		labelLst.add(LogoResConstants.RE_TURKMENTM_NAME);

		if (lst != null) {
			for (int i = 0; i < lst.size(); i++) {
				double cnt = (double) lst.get(i);
				dataLst.add(cnt);
			}
		}
		resCountConfig.data().labelsAsList(labelLst).addDataset(new BarDataset().label("")).and();
		BarDataset resCountLds = (BarDataset) resCountConfig.data().getDatasets().get(0);
		resCountLds.dataAsList(dataLst);
		String[] colors = new String[] { "#455A64", "#FFC107", "#757575", "#CFD8DC", "#F0DC9D", "black", "#5BB1C9",
				"#8f422c", "#2c8f42", "#8f2c48", "#2c7a8f", "#9bb638", "#9238b6", "#38b653", "#b6385c" };
		resCountLds.backgroundColor(colors);
	}

}
