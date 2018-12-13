package com.logo.ui.components;

import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.DonutChartConfig;
import com.byteowls.vaadin.chartjs.data.PieDataset;
import com.byteowls.vaadin.chartjs.options.InteractionMode;
import com.byteowls.vaadin.chartjs.options.Tooltips.PositionMode;
import com.github.appreciated.material.MaterialTheme;

public class LocChart extends ChartJs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocChart(int total, int Localized) {
		List<String> labels = new ArrayList<>();
		labels.add("Not Localized");
		labels.add("Localized");

		DonutChartConfig config = new DonutChartConfig();
		config.data().labelsAsList(labels).addDataset(new PieDataset().label("Dataset 1")).and();

		config.options().tooltips().intersect(true).position(PositionMode.NEAREST).mode(InteractionMode.NEAREST)
				.backgroundColor("black").cornerRadius(10).and().legend().display(false).and().rotation(Math.PI)
				.circumference(Math.PI).responsive(true).cutoutPercentage(60).title().display(false)
				.text("Chart.js Gauge donut chart").and().animation().animateScale(false).animateRotate(false).and()
				.done();

		String[] colors = new String[] { "#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360" };

		PieDataset lds = (PieDataset) config.data().getDatasets().get(0);
		lds.backgroundColor(colors);
		int notloc = total - Localized;
		lds.addData(notloc).addData(Localized);

		this.configure(config);
		this.setWidth("150px");
		this.setHeight("40px");
		this.addStyleName(MaterialTheme.CARD_NO_PADDING);
		this.setJsLoggingEnabled(false);
	}
}
