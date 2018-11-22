package com.logo.util;

import java.io.Serializable;
import java.util.Locale;

import javax.sql.DataSource;
import org.springframework.stereotype.Component;
import org.vaadin.spring.i18n.MessageProvider;

import com.logo.ContextConfiguration;
import com.logo.data.ReAlbaniankvRep;
import com.logo.data.ReArabicegRep;
import com.logo.data.ReArabicjoRep;
import com.logo.data.ReArabicsaRep;
import com.logo.data.ReAzerbaijaniazRep;
import com.logo.data.ReBulgarianbgRep;
import com.logo.data.ReEnglishusRep;
import com.logo.data.ReFrenchfrRep;
import com.logo.data.ReGeorgiangeRep;
import com.logo.data.ReGermandeRep;
import com.logo.data.ReHelpDocsRep;
import com.logo.data.ReMessageRep;
import com.logo.data.RePersianirRep;
import com.logo.data.ReProjectVerisonRep;
import com.logo.data.ReResourceRep;
import com.logo.data.ReResourceitemRep;
import com.logo.data.ReResourceitemShortRep;
import com.logo.data.ReRomanianroRep;
import com.logo.data.ReRussianruRep;
import com.logo.data.ReTurkishtrRep;
import com.logo.data.ReTurkmentmRep;
import com.logo.data.ReUserRep;

@Component
public class RepositoryContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient ReResourceRep resRepo;
	private transient ReResourceitemRep reResourceitemRep;
	private transient ReTurkishtrRep reTurkishtrRep;
	private transient ReEnglishusRep reEnglishusRep;
	private transient ReGermandeRep reGermandeRep;
	private transient RePersianirRep rePersianirRep;
	private transient ReAzerbaijaniazRep reAzerbaijaniazRep;
	private transient ReBulgarianbgRep reBulgarianbgRep;
	private transient ReRussianruRep reRussianruRep;
	private transient ReRomanianroRep reRomanianroRep;  
	private transient ReGeorgiangeRep reGeorgiangeRep;
	private transient ReArabicjoRep reArabicjoRep;
	private transient ReFrenchfrRep reFrenchfrRep;
	private transient ReAlbaniankvRep reAlbaniankvRep;
	private transient ReTurkmentmRep reTurkmentmRep;
	private transient ReArabicegRep reArabicegRep;
	private transient ReArabicsaRep reArabicsaRep;
	private transient MessageProvider messageProvider;
	private transient Locale appLocale;
	
	private transient ReResourceitemShortRep reResourceitemShortRep;
	
	private transient ReUserRep reUserRep;
	
	private transient ReMessageRep reMessageRep;
	
	private transient ReHelpDocsRep reHelpDocsRep;
	
	private transient ReProjectVerisonRep reProjectVerisonRep;
	
	private transient DataSource dataSource;

	public RepositoryContainer() {
		dataSource = ContextConfiguration.getDataSource();
	}

	public ReUserRep getReUserRep() {
		return reUserRep;
	}
	
	public void setReUserRep(ReUserRep reUserRep) {
		this.reUserRep = reUserRep;
	}
	
	public ReResourceRep getResRepo() {
		return resRepo;
	}

	public void setResRepo(ReResourceRep resRepo) {
		this.resRepo = resRepo;
	}

	public ReTurkishtrRep getReTurkishtrRep() {
		return reTurkishtrRep;
	}

	public void setReTurkishtrRep(ReTurkishtrRep reTurkishtrRep) {
		this.reTurkishtrRep = reTurkishtrRep;
	}

	public ReResourceitemRep getReResourceitemRep() {
		return reResourceitemRep;
	}

	public void setReResourceitemRep(ReResourceitemRep reResourceitemRep) {
		this.reResourceitemRep = reResourceitemRep;
	}

	public ReEnglishusRep getReEnglishusRep() {
		return reEnglishusRep;
	}

	public void setReEnglishusRep(ReEnglishusRep reEnglishusRep) {
		this.reEnglishusRep = reEnglishusRep;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public ReGermandeRep getReGermandeRep() {
		return reGermandeRep;
	}
	
	public void setReGermandeRep(ReGermandeRep reGermandeRep) {
		this.reGermandeRep = reGermandeRep;
	}
	
	public RePersianirRep getRePersianirRep() {
		return rePersianirRep;
	}
	
	public void setRePersianirRep(RePersianirRep rePersianirRep) {
		this.rePersianirRep = rePersianirRep;
	}
	
	public ReAzerbaijaniazRep getReAzerbaijaniazRep() {
		return reAzerbaijaniazRep;
	}
	
	public void setReAzerbaijaniazRep(ReAzerbaijaniazRep reAzerbaijaniazRep) {
		this.reAzerbaijaniazRep = reAzerbaijaniazRep;
	}
	
	public ReBulgarianbgRep getReBulgarianbgRep() {
		return reBulgarianbgRep;
	}
	
	public void setReBulgarianbgRep(ReBulgarianbgRep reBulgarianbgRep) {
		this.reBulgarianbgRep = reBulgarianbgRep;
	}
	
	public ReRussianruRep getReRussianruRep() {
		return reRussianruRep;
	}
	
	public void setReRussianruRep(ReRussianruRep reRussianruRep) {
		this.reRussianruRep = reRussianruRep;
	}
	
	public ReMessageRep getReMessageRep() {
		return reMessageRep;
	}
	
	public void setReMessageRep(ReMessageRep reMessageRep) {
		this.reMessageRep = reMessageRep;
	}
	
	public ReHelpDocsRep getReHelpDocsRep() {
		return reHelpDocsRep;
	}
	
	public void setReHelpDocsRep(ReHelpDocsRep reHelpDocsRep) {
		this.reHelpDocsRep = reHelpDocsRep;
	}
	
	public ReRomanianroRep getReRomanianroRep() {
		return reRomanianroRep;
	}
	
	public void setReRomanianroRep(ReRomanianroRep reRomanianroRep) {
		this.reRomanianroRep = reRomanianroRep;
	}
	
	public ReGeorgiangeRep getReGeorgiangeRep() {
		return reGeorgiangeRep;
	}
	
	public void setReGeorgiangeRep(ReGeorgiangeRep reGeorgiangeRep) {
		this.reGeorgiangeRep = reGeorgiangeRep;
	}
	
	public ReArabicjoRep getReArabicjoRep() {
		return reArabicjoRep;
	}
	
	public void setReArabicjoRep(ReArabicjoRep reArabicjoRep) {
		this.reArabicjoRep = reArabicjoRep;
	}
	
	public ReFrenchfrRep getReFrenchfrRep() {
		return reFrenchfrRep;
	}
	
	public void setReFrenchfrRep(ReFrenchfrRep reFrenchfrRep) {
		this.reFrenchfrRep = reFrenchfrRep;
	}
	
	public ReAlbaniankvRep getReAlbaniankvRep() {
		return reAlbaniankvRep;
	}
	
	public void setReAlbaniankvRep(ReAlbaniankvRep reAlbaniankvRep) {
		this.reAlbaniankvRep = reAlbaniankvRep;
	}
	
	public ReTurkmentmRep getReTurkmentmRep() {
		return reTurkmentmRep;
	}
	
	public void setReTurkmentmRep(ReTurkmentmRep reTurkmentmRep) {
		this.reTurkmentmRep = reTurkmentmRep;
	}
	
	public ReArabicegRep getReArabicegRep() {
		return reArabicegRep;
	}
	
	public void setReArabicegRep(ReArabicegRep reArabicegRep) {
		this.reArabicegRep = reArabicegRep;
	}
	
	public ReArabicsaRep getReArabicsaRep() {
		return reArabicsaRep;
	}
	
	public void setReArabicsaRep(ReArabicsaRep reArabicsaRep) {
		this.reArabicsaRep = reArabicsaRep;
	}
	
	public void setReResourceitemShortRep(ReResourceitemShortRep reResourceitemShortRep) {
		this.reResourceitemShortRep = reResourceitemShortRep;
	}
	
	public ReResourceitemShortRep getReResourceitemShortRep() {
		return reResourceitemShortRep;
	}
	
	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}
	
	public MessageProvider getMessageProvider() {
		return messageProvider;
	}
	
	public void setAppLocale(Locale appLocale) {
		this.appLocale = appLocale;
	}
	
	public Locale getAppLocale() {
		return appLocale;
	}
	
	public void setReProjectVerisonRep(ReProjectVerisonRep reProjectVerisonRep) {
		this.reProjectVerisonRep = reProjectVerisonRep;
	}
	
	public ReProjectVerisonRep getReProjectVerisonRep() {
		return reProjectVerisonRep;
	}
}
