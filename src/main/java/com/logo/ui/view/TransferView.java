package com.logo.ui.view;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.logo.ui.components.TransferLayout;
import com.logo.util.export.ExportExcel;
import com.logo.util.export.ExportTxt;
import com.logo.util.export.ExportXml;
import com.logo.util.search.SearchByAll;
import com.logo.util.search.SearchByResourceNr;
import com.logo.util.LogoResConstants;
import com.vaadin.annotations.Push;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Link;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@Push
@UIScope
@SpringView(name = TransferView.VIEW_NAME)
public class TransferView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(TransferView.class.getName());

	public static final String VIEW_NAME = "TransferView";
	public static final String SLASH = "\\";
	private TransferLayout transferLayout;

	File path;
	ProgressBar bar = new ProgressBar();
	String fileName;
	Link downloadLink = new Link();

	public TransferView() {
		removeAllComponents();
		init();
	}

	@PostConstruct
	void init() {
		transferLayout = new TransferLayout();
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		setHeight("100%");

		addComponent(transferLayout);

		path = VaadinService.getCurrent().getBaseDirectory();

		transferLayout.getExporExceltButton().addClickListener(e -> {
			removeComponent(downloadLink);
			transferLayout.getExporExceltButton().setVisible(false);
			transferLayout.getExportXmlButton().setVisible(false);
			transferLayout.getExportTxtButton().setVisible(false);
			bar.setIndeterminate(true);
			addComponent(bar);
			setComponentAlignment(bar, Alignment.MIDDLE_CENTER);
			new FeederXlsThread().start();
		});
		transferLayout.getExportXmlButton().addClickListener(e -> {
			removeComponent(downloadLink);
			transferLayout.getExporExceltButton().setVisible(false);
			transferLayout.getExportXmlButton().setVisible(false);
			transferLayout.getExportTxtButton().setVisible(false);
			bar.setIndeterminate(true);
			addComponent(bar);
			setComponentAlignment(bar, Alignment.MIDDLE_CENTER);
			new FeederXmlThread().start();
		});
		transferLayout.getExportTxtButton().addClickListener(e -> {
			removeComponent(downloadLink);
			transferLayout.getExporExceltButton().setVisible(false);
			transferLayout.getExportXmlButton().setVisible(false);
			transferLayout.getExportTxtButton().setVisible(false);
			bar.setIndeterminate(true);
			addComponent(bar);
			setComponentAlignment(bar, Alignment.MIDDLE_CENTER);
			new FeederTxtThread().start();
		});

	}

	public void prepareDownloadLink() {
		downloadLink = new Link();
		downloadLink.setCaption("Download link");

		addComponent(downloadLink);
		setComponentAlignment(downloadLink, Alignment.MIDDLE_CENTER);

		Resource res = new FileResource(new File(fileName));
		FileDownloader fd = new FileDownloader(res);
		fd.extend(downloadLink);
	}

	TextField createTextField(String caption) {
		TextField textField = new TextField(caption);
		textField.setCaptionAsHtml(true);
		textField.addStyleName(LogoResConstants.STYLE_TEXTFIEL_FORM);
		textField.setHeight("100%");
		textField.setWidth("100%");
		return textField;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		init();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	class FeederXlsThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				Runnable task1 = () -> {
					try {
						exportExcel();
						transferLayout.getExporExceltButton().setVisible(true);
						transferLayout.getExportXmlButton().setVisible(true);
						transferLayout.getExportTxtButton().setVisible(true);
						removeComponent(bar);
						prepareDownloadLink();
						getUI().push();

					} catch (IOException e) {
						logger.log(Level.SEVERE, e.getMessage(), e.getLocalizedMessage());
					}
				};
				getUI().access(task1);
			} catch (InterruptedException e) {
				logger.log(Level.SEVERE, e.getMessage(), e.getLocalizedMessage());
				Thread.currentThread().interrupt();
			}
		}

		private void exportExcel() throws IOException {
			Calendar cal = Calendar.getInstance();
			String name = Long.toString(cal.getTimeInMillis()) + ".xlsx";

			fileName = path.getPath() + SLASH + name;

			SearchByResourceNr searchByResourceNr = new SearchByResourceNr.Builder()
					.setResNrBegin(transferLayout.getResourceNr1()).setResNrEnd(transferLayout.getResourceNr2())
					.setDescCombo(null).setDescComboText(null).setResourceCaseCombo(null).setResourceGroupCombo(null)
					.setResourceStateCombo(null).setResourceTypeCombo(null).build();
			SearchByAll searchByAll = new SearchByAll.Builder().setSearchByResourceNr(searchByResourceNr)
					.setOrderNrBegin(transferLayout.getOrderNr1()).setOrderNrEnd(transferLayout.getOrderNr2())
					.setTagNrBegin(transferLayout.getTagNr1()).setTagNrEnd(transferLayout.getTagNr2())
					.setLevelNrBegin(transferLayout.getLevelNr1()).setLevelNrEnd(transferLayout.getLevelNr2())
					.setResourceItemCaseCombo(null).setPrefixCombo(null).setInfoCombo(null).setPrefixComboText(null)
					.setInfoComboText(null).setDescComboTR(null).setComboTextTR(null).setDescComboEN(null)
					.setComboTextEN(null).build();
			searchByAll.generateSearchParam();

			ExportExcel excelWrite = new ExportExcel(searchByResourceNr.getScParam(), fileName,
					transferLayout.getTurkishTR().getValue(), transferLayout.getEnglishUS().getValue());
			excelWrite.export();
		}
	}

	class FeederXmlThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				Runnable task1 = () -> {
					try {
						exportXml();
						transferLayout.getExporExceltButton().setVisible(true);
						transferLayout.getExportXmlButton().setVisible(true);
						transferLayout.getExportTxtButton().setVisible(true);
						removeComponent(bar);
						prepareDownloadLink();
						getUI().push();

					} catch (IOException | TransformerException | ParserConfigurationException e) {
						logger.log(Level.SEVERE, e.getMessage(), e.getLocalizedMessage());
					}
				};
				getUI().access(task1);
			} catch (InterruptedException e) {
				logger.log(Level.SEVERE, e.getMessage(), e.getLocalizedMessage());
				Thread.currentThread().interrupt();
			}
		}

		private void exportXml() throws IOException, TransformerException, ParserConfigurationException {
			Calendar cal = Calendar.getInstance();
			String name = Long.toString(cal.getTimeInMillis()) + ".xml";

			fileName = path.getPath() + SLASH + name;

			SearchByResourceNr searchByResourceNr = new SearchByResourceNr.Builder()
					.setResNrBegin(transferLayout.getResourceNr1()).setResNrEnd(transferLayout.getResourceNr2())
					.setDescCombo(null).setDescComboText(null).setResourceCaseCombo(null).setResourceGroupCombo(null)
					.setResourceStateCombo(null).setResourceTypeCombo(null).build();
			SearchByAll searchByAll = new SearchByAll.Builder().setSearchByResourceNr(searchByResourceNr)
					.setOrderNrBegin(transferLayout.getOrderNr1()).setOrderNrEnd(transferLayout.getOrderNr2())
					.setTagNrBegin(transferLayout.getTagNr1()).setTagNrEnd(transferLayout.getTagNr2())
					.setLevelNrBegin(transferLayout.getLevelNr1()).setLevelNrEnd(transferLayout.getLevelNr2())
					.setResourceItemCaseCombo(null).setPrefixCombo(null).setInfoCombo(null).setPrefixComboText(null)
					.setInfoComboText(null).setDescComboTR(null).setComboTextTR(null).setDescComboEN(null)
					.setComboTextEN(null).build();
			searchByAll.generateSearchParam();

			ExportXml xmlWrite = new ExportXml(searchByResourceNr.getScParam(), fileName,
					transferLayout.getTurkishTR().getValue(), transferLayout.getEnglishUS().getValue());
			xmlWrite.export();
		}
	}

	class FeederTxtThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				Runnable task1 = () -> {
					try {
						exportTxt();
						transferLayout.getExporExceltButton().setVisible(true);
						transferLayout.getExportXmlButton().setVisible(true);
						transferLayout.getExportTxtButton().setVisible(true);
						removeComponent(bar);
						prepareDownloadLink();
						getUI().push();

					} catch (IOException | TransformerException | ParserConfigurationException e) {
						logger.log(Level.SEVERE, e.getMessage(), e.getLocalizedMessage());
					}
				};
				getUI().access(task1);
			} catch (InterruptedException e) {
				logger.log(Level.SEVERE, e.getMessage(), e.getLocalizedMessage());
				Thread.currentThread().interrupt();
			}
		}

		private void exportTxt() throws IOException, TransformerException, ParserConfigurationException {
			Calendar cal = Calendar.getInstance();
			String name = Long.toString(cal.getTimeInMillis()) + ".txt";

			fileName = path.getPath() + SLASH + name;

			SearchByResourceNr searchByResourceNr = new SearchByResourceNr.Builder()
					.setResNrBegin(transferLayout.getResourceNr1()).setResNrEnd(transferLayout.getResourceNr2())
					.setDescCombo(null).setDescComboText(null).setResourceCaseCombo(null).setResourceGroupCombo(null)
					.setResourceStateCombo(null).setResourceTypeCombo(null).build();
			SearchByAll searchByAll = new SearchByAll.Builder().setSearchByResourceNr(searchByResourceNr)
					.setOrderNrBegin(transferLayout.getOrderNr1()).setOrderNrEnd(transferLayout.getOrderNr2())
					.setTagNrBegin(transferLayout.getTagNr1()).setTagNrEnd(transferLayout.getTagNr2())
					.setLevelNrBegin(transferLayout.getLevelNr1()).setLevelNrEnd(transferLayout.getLevelNr2())
					.setResourceItemCaseCombo(null).setPrefixCombo(null).setInfoCombo(null).setPrefixComboText(null)
					.setInfoComboText(null).setDescComboTR(null).setComboTextTR(null).setDescComboEN(null)
					.setComboTextEN(null).build();
			searchByAll.generateSearchParam();

			ExportTxt exportTxt = new ExportTxt(searchByResourceNr.getScParam(), fileName,
					transferLayout.getTurkishTR().getValue(), transferLayout.getEnglishUS().getValue());
			exportTxt.export();
		}
	}
}
