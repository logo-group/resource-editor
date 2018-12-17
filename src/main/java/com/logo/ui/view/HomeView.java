package com.logo.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.config.PieChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.PieDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.logo.data.repository.ReResourceRep;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = HomeView.VIEW_NAME)
public class HomeView extends HorizontalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "HomeView";

	private ReResourceRep resRepo;

	@Autowired
	public HomeView(ReResourceRep resRepo) {
		this.resRepo = resRepo;
		setSizeFull();

		VerticalLayout chartLayout = new VerticalLayout();
		chartLayout.setSizeFull();
		chartLayout.setWidth("100%");
		chartLayout.setHeight("100%");

		HorizontalLayout main = new HorizontalLayout();
		main.setSizeUndefined();
		main.setWidth("100%");

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

		chartLayout.addComponents(main);
		chartLayout.setComponentAlignment(main, Alignment.TOP_RIGHT);
		addComponentsAndExpand(chartLayout);

		setExpandRatio(chartLayout, 1.0f);
		setComponentAlignment(chartLayout, Alignment.TOP_RIGHT);
	}

	private ChartJs createResCountChart() {
		PieChartConfig resCountConfig = new PieChartConfig();

		resCountConfig.options().responsive(true).title().display(true)
				.text(LangHelper.getLocalizableMessage(LogoResConstants.LOGORESCOUNTSTR)).fontSize(20)
				.position(Position.TOP).fontColor("#a50000").and().animation().animateScale(true).animateRotate(true)
				.and().legend().position(Position.LEFT).and();

		getCountForResCountChart(resCountConfig);
		ChartJs resCountChart = new ChartJs(resCountConfig);
		return resCountChart;
	}

	private ChartJs createLangCountChart() {
		BarChartConfig barConfig = new BarChartConfig();
		barConfig.options().responsive(true).title().display(true)
				.text(LangHelper.getLocalizableMessage(LogoResConstants.LOGOLANGCOUNTSTR)).fontSize(20)
				.position(Position.TOP).fontColor("#a50000").and().animation().and().legend().position(Position.TOP)
				.display(false).and();

		getCountForLangCountChart(barConfig);
		ChartJs langCountChart = new ChartJs(barConfig);
		return langCountChart;
	}

	private void getCountForResCountChart(PieChartConfig resCountConfig) {
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

	private void getCountForLangCountChart(BarChartConfig resCountConfig) {
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
