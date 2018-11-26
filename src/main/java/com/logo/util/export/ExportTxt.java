package com.logo.util.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.logo.LogoresMainUI;
import com.logo.data.entity.ReEnglishus;
import com.logo.data.entity.ReResourceitemShort;
import com.logo.data.entity.ReTurkishtr;
import com.logo.data.repository.ReEnglishusRep;
import com.logo.data.repository.ReResourceitemShortRep;
import com.logo.data.repository.ReTurkishtrRep;
import com.logo.util.search.SearchParam;
import com.vaadin.spring.annotation.UIScope;

@UIScope
public class ExportTxt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ExportTxt.class.getName());

	private final transient ReResourceitemShortRep reResourceitemShortRep;
	private final transient ReTurkishtrRep reTurkishtrRep;
	private final transient ReEnglishusRep reEnglishusRep;
	private final transient String fileName;
	private final transient boolean isTurkishWrite;
	private final transient boolean isEnglishWrite;
	
	private final transient SearchParam sParam;

	public ExportTxt(SearchParam sParam, String fileName, boolean isTurkishWrite, boolean isEnglishWrite) {
		this.reResourceitemShortRep = LogoresMainUI.getMrepositorycontainer().getReResourceitemShortRep();
		this.reTurkishtrRep = LogoresMainUI.getMrepositorycontainer().getReTurkishtrRep();
		this.reEnglishusRep = LogoresMainUI.getMrepositorycontainer().getReEnglishusRep();
		this.sParam = sParam;
		this.fileName = fileName;
		this.isTurkishWrite = isTurkishWrite;
		this.isEnglishWrite = isEnglishWrite;
	}

	public void export() throws IOException {
		logger.log(Level.WARNING, "Creating txt");

		File file = new File(fileName);
		if (file.createNewFile()) {
			FileOutputStream outputStream = new FileOutputStream(file);
			PrintStream ps = new PrintStream(outputStream);

			Page<com.logo.data.entity.ReResourceitemShort> resourceItems = searchByresourceParamAll(0, 1000, sParam);
			ArrayDeque<ReResourceitemShort> newList1 = new ArrayDeque<>();
			newList1.addAll(resourceItems.getContent());

			AtomicInteger counter = new AtomicInteger(0);
			newList1.stream().forEach(item -> printLine(ps, item, counter));

			long totalPage = resourceItems.getTotalElements() / 1000;

			for (int i = 1; i < totalPage + 1; i++) {
				resourceItems = searchByresourceParamAll(i, 1000, sParam);
				ArrayDeque<ReResourceitemShort> newList = new ArrayDeque<>();
				newList.addAll(resourceItems.getContent());
				newList.stream().forEach(item -> printLine(ps, item, counter));
			}

			ps.close();
			logger.log(Level.WARNING, "txt done");
		}
	}

	private void printLine(PrintStream ps, ReResourceitemShort item, AtomicInteger counter) {
		StringBuilder sb = new StringBuilder();

		sb.append(item.reResource.getResourcegroup().name().toString());
		sb.append(",");
		sb.append((String) item.reResource.getResourcetype().name());
		sb.append(",");
		sb.append((Integer) item.reResource.getResourcenr());
		sb.append(",");
		sb.append((Integer) item.getOrdernr());
		sb.append(",");
		sb.append((Integer) item.getTagnr());
		sb.append(",");
		if(isTurkishWrite){
			ReTurkishtr reTurkishtr = reTurkishtrRep.getresourceitemrefEquals(item.getId());
			if (reTurkishtr != null) {
				sb.append((String) reTurkishtr.getResourcestr());
			}
		}
		if(isEnglishWrite){
			ReEnglishus reEnglishus = reEnglishusRep.getresourceitemrefEquals(item.getId());
			if (reEnglishus != null) {
				sb.append(",");
				sb.append((String) reEnglishus.getResourcestr());
			}
		}
		ps.println(sb.toString());
	}

	private Page<com.logo.data.entity.ReResourceitemShort> searchByresourceParamAll(int page, int size, SearchParam sParam) {
		Pageable pageable = new PageRequest(page, size, null);
		return reResourceitemShortRep.searchByParamAll(pageable, sParam);
	}

}
