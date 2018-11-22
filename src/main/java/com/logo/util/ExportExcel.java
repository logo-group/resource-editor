package com.logo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.logo.LogoresMainUI;
import com.logo.data.ReEnglishus;
import com.logo.data.ReEnglishusRep;
import com.logo.data.ReResourceitemShort;
import com.logo.data.ReResourceitemShortRep;
import com.logo.data.ReTurkishtr;
import com.logo.data.ReTurkishtrRep;
import com.vaadin.spring.annotation.UIScope;

@UIScope
public class ExportExcel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ExportExcel.class.getName());

	private final transient ReResourceitemShortRep reResourceitemShortRep;
	private final transient ReTurkishtrRep reTurkishtrRep;
	private final transient ReEnglishusRep reEnglishusRep;
	private final transient String fileName;
	private final transient boolean isTurkishWrite;
	private final transient boolean isEnglishWrite;
	
	private final transient SearchParam sParam;

	public ExportExcel(SearchParam sParam, String fileName, boolean isTurkishWrite, boolean isEnglishWrite) {
		this.reResourceitemShortRep = LogoresMainUI.getMrepositorycontainer().getReResourceitemShortRep();
		this.reTurkishtrRep = LogoresMainUI.getMrepositorycontainer().getReTurkishtrRep();
		this.reEnglishusRep = LogoresMainUI.getMrepositorycontainer().getReEnglishusRep();
		this.sParam = sParam;
		this.fileName = fileName;
		this.isTurkishWrite = isTurkishWrite;
		this.isEnglishWrite = isEnglishWrite;
	}

	public void export() throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Datatypes in Java");

		logger.log(Level.WARNING, "Creating excel");

		Page<com.logo.data.ReResourceitemShort> resourceItems = searchByresourceParamAll(0, 1000, sParam);
		ArrayDeque<ReResourceitemShort> newList1 = new ArrayDeque<>();
		newList1.addAll(resourceItems.getContent());

		AtomicInteger counter = new AtomicInteger(0);
		newList1.stream().forEach(item -> createRow(sheet, item, counter));

		long totalPage = resourceItems.getTotalElements() / 1000;

		for (int i = 1; i < totalPage + 1; i++) {
			resourceItems = searchByresourceParamAll(i, 1000, sParam);
			ArrayDeque<ReResourceitemShort> newList = new ArrayDeque<>();
			newList.addAll(resourceItems.getContent());
			newList.stream().forEach(item -> createRow(sheet, item, counter));
		}

		File file = new File(fileName);
		if (file.createNewFile()) {
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
		}
		workbook.close();
		logger.log(Level.WARNING, "excel done");
	}

	private void createRow(XSSFSheet sheet, ReResourceitemShort item, AtomicInteger counter) {
		Row row = sheet.createRow(counter.incrementAndGet());
		int colNum = 0;

		Cell cell1 = row.createCell(colNum++);
		cell1.setCellValue((String) item.reResource.getResourcegroup().name());

		Cell cell2 = row.createCell(colNum++);
		cell2.setCellValue((String) item.reResource.getResourcetype().name());

		Cell cell3 = row.createCell(colNum++);
		cell3.setCellValue((Integer) item.reResource.getResourcenr());

		Cell cell4 = row.createCell(colNum++);
		cell4.setCellValue((Integer) item.getOrdernr());

		Cell cell5 = row.createCell(colNum++);
		cell5.setCellValue((Integer) item.getTagnr());

		if(isTurkishWrite){
			ReTurkishtr reTurkishtr = reTurkishtrRep.getresourceitemrefEquals(item.getId());
			if (reTurkishtr != null) {
				Cell cell6 = row.createCell(colNum++);
				cell6.setCellValue((String) reTurkishtr.getResourcestr());
			}
		}
		if(isEnglishWrite){
			ReEnglishus reEnglishus = reEnglishusRep.getresourceitemrefEquals(item.getId());
			if (reEnglishus != null) {
				Cell cell7 = row.createCell(colNum++);
				cell7.setCellValue((String) reEnglishus.getResourcestr());
			}
		}
	}

	private Page<com.logo.data.ReResourceitemShort> searchByresourceParamAll(int page, int size, SearchParam sParam) {
		Pageable pageable = new PageRequest(page, size, null);
		return reResourceitemShortRep.searchByParamAll(pageable, sParam);
	}

}
