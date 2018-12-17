package com.logo.util;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReAlbaniankv;
import com.logo.data.entity.ReArabiceg;
import com.logo.data.entity.ReArabicjo;
import com.logo.data.entity.ReArabicsa;
import com.logo.data.entity.ReAzerbaijaniaz;
import com.logo.data.entity.ReBulgarianbg;
import com.logo.data.entity.ReEnglishus;
import com.logo.data.entity.ReFrenchfr;
import com.logo.data.entity.ReGermande;
import com.logo.data.entity.ReLanguageTable;
import com.logo.data.entity.RePersianir;
import com.logo.data.entity.ReRomanianro;
import com.logo.data.entity.ReRussianru;
import com.logo.data.entity.ReStandard;
import com.logo.data.entity.ReTurkishtr;
import com.logo.data.entity.ReTurkmentm;
import com.logo.data.repository.ReAlbaniankvRep;
import com.logo.data.repository.ReArabicegRep;
import com.logo.data.repository.ReArabicjoRep;
import com.logo.data.repository.ReArabicsaRep;
import com.logo.data.repository.ReAzerbaijaniazRep;
import com.logo.data.repository.ReBulgarianbgRep;
import com.logo.data.repository.ReEnglishusRep;
import com.logo.data.repository.ReFrenchfrRep;
import com.logo.data.repository.ReGermandeRep;
import com.logo.data.repository.RePersianirRep;
import com.logo.data.repository.ReRomanianroRep;
import com.logo.data.repository.ReRussianruRep;
import com.logo.data.repository.ReStandardRep;
import com.logo.data.repository.ReTurkishtrRep;
import com.logo.data.repository.ReTurkmentmRep;

@Component
@Scope("prototype")
public class CustomLayoutUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String langTo;
	private String lang;
	private Integer itemId;

	@Autowired
	private ReTurkishtrRep reTurkishtrRep;

	@Autowired
	private ReEnglishusRep reEnglishusRep;

	@Autowired
	private ReGermandeRep reGermandeRep;

	@Autowired
	private RePersianirRep rePersianirRep;

	@Autowired
	private ReAzerbaijaniazRep reAzerbaijaniazRep;

	@Autowired
	private ReBulgarianbgRep reBulgarianbgRep;

	@Autowired
	private ReRussianruRep reRussianruRep;

	@Autowired
	private ReRomanianroRep reRomanianroRep;

	@Autowired
	private ReArabicjoRep reArabicjoRep;

	@Autowired
	private ReFrenchfrRep reFrenchfrRep;

	@Autowired
	private ReAlbaniankvRep reAlbaniankvRep;

	@Autowired
	private ReTurkmentmRep reTurkmentmRep;

	@Autowired
	private ReArabicegRep reArabicegRep;

	@Autowired
	private ReArabicsaRep reArabicsaRep;

	@Autowired
	private ReStandardRep reStandardRep;

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void persist(ReLanguageTable table) {
		if (table == null)
			return;
		if (table instanceof ReTurkishtr) {
			ReTurkishtr reTurkishtr = (ReTurkishtr) table;
			reTurkishtrRep.save(reTurkishtr);
		}
		if (table instanceof ReEnglishus) {
			ReEnglishus reEnglishus = (ReEnglishus) table;
			reEnglishusRep.save(reEnglishus);
		}
		if (table instanceof RePersianir) {
			RePersianir rePersianir = (RePersianir) table;
			rePersianirRep.save(rePersianir);
		}
		if (table instanceof ReGermande) {
			ReGermande reGermande = (ReGermande) table;
			reGermandeRep.save(reGermande);
		}
		if (table instanceof ReAzerbaijaniaz) {
			ReAzerbaijaniaz reAzerbaijaniaz = (ReAzerbaijaniaz) table;
			reAzerbaijaniazRep.save(reAzerbaijaniaz);
		}
		if (table instanceof ReBulgarianbg) {
			ReBulgarianbg reBulgarianbg = (ReBulgarianbg) table;
			reBulgarianbgRep.save(reBulgarianbg);
		}
		if (table instanceof ReRussianru) {
			ReRussianru reRussianru = (ReRussianru) table;
			reRussianruRep.save(reRussianru);
		}
		if (table instanceof ReRomanianro) {
			ReRomanianro reRomanianro = (ReRomanianro) table;
			reRomanianroRep.save(reRomanianro);
		}
		if (table instanceof ReArabicjo) {
			ReArabicjo reArabicjo = (ReArabicjo) table;
			reArabicjoRep.save(reArabicjo);
		}
		if (table instanceof ReFrenchfr) {
			ReFrenchfr reFrenchfr = (ReFrenchfr) table;
			reFrenchfrRep.save(reFrenchfr);
		}
		if (table instanceof ReAlbaniankv) {
			ReAlbaniankv reAlbaniankv = (ReAlbaniankv) table;
			reAlbaniankvRep.save(reAlbaniankv);
		}
		if (table instanceof ReTurkmentm) {
			ReTurkmentm reTurkmentm = (ReTurkmentm) table;
			reTurkmentmRep.save(reTurkmentm);
		}
		if (table instanceof ReArabiceg) {
			ReArabiceg reArabiceg = (ReArabiceg) table;
			reArabicegRep.save(reArabiceg);
		}
		if (table instanceof ReArabicsa) {
			ReArabicsa reArabicsa = (ReArabicsa) table;
			reArabicsaRep.save(reArabicsa);
		}
	}

	public void persistStandard(ReStandard reStandard) {
		if (reStandard == null) {
			return;
		}
		reStandard.setResourceitemref(itemId);
		reStandardRep.save(reStandard);
	}

	public void setLangTo() {
		switch (this.lang) {
		case LogoResConstants.RE_ENGLISHUS_NAME:
			langTo = LogoResConstants.RE_ENGLISHUS;
			break;
		case LogoResConstants.RE_GERMANDE_NAME:
			langTo = LogoResConstants.RE_GERMANDE;
			break;
		case LogoResConstants.RE_PERSIANIR_NAME:
			langTo = LogoResConstants.RE_PERSIANIR;
			break;
		case LogoResConstants.RE_AZERBAIJANIAZ_NAME:
			langTo = LogoResConstants.RE_AZERBAIJANIAZ;
			break;
		case LogoResConstants.RE_BULGARIANBG_NAME:
			langTo = LogoResConstants.RE_BULGARIANBG;
			break;
		case LogoResConstants.RE_RUSSIANRU_NAME:
			langTo = LogoResConstants.RE_RUSSIANRU;
			break;
		case LogoResConstants.RE_ROMANIANRO_NAME:
			langTo = LogoResConstants.RE_ROMANIANRO;
			break;
		case LogoResConstants.RE_ARABICJO_NAME:
			langTo = LogoResConstants.RE_ARABICJO;
			break;
		case LogoResConstants.RE_FRENCHFR_NAME:
			langTo = LogoResConstants.RE_FRENCHFR;
			break;
		case LogoResConstants.RE_ALBANIANKV_NAME:
			langTo = LogoResConstants.RE_ALBANIANKV;
			break;
		case LogoResConstants.RE_TURKMENTM_NAME:
			langTo = LogoResConstants.RE_TURKMENTM;
			break;
		case LogoResConstants.RE_ARABICEG_NAME:
			langTo = LogoResConstants.RE_ARABICEG;
			break;
		case LogoResConstants.RE_ARABICSA_NAME:
			langTo = LogoResConstants.RE_ARABICSA;
			break;
		default:
			langTo = LogoResConstants.RE_ENGLISHUS;
			break;
		}
	}

	public ReLanguageTable prepareTable(String resourceStr) {
		ReLanguageTable langTable = null;
		switch (this.lang) {
		case LogoResConstants.RE_TURKISHTR_NAME:
			langTable = new ReTurkishtr();
			break;
		case LogoResConstants.RE_ENGLISHUS_NAME:
			langTable = new ReEnglishus();
			break;
		case LogoResConstants.RE_GERMANDE_NAME:
			langTable = new ReGermande();
			break;
		case LogoResConstants.RE_PERSIANIR_NAME:
			langTable = new RePersianir();
			break;
		case LogoResConstants.RE_AZERBAIJANIAZ_NAME:
			langTable = new ReAzerbaijaniaz();
			break;
		case LogoResConstants.RE_BULGARIANBG_NAME:
			langTable = new ReBulgarianbg();
			break;
		case LogoResConstants.RE_RUSSIANRU_NAME:
			langTable = new ReRussianru();
			break;
		case LogoResConstants.RE_ROMANIANRO_NAME:
			langTable = new ReRomanianro();
			break;
		case LogoResConstants.RE_ARABICJO_NAME:
			langTable = new ReArabicjo();
			break;
		case LogoResConstants.RE_FRENCHFR_NAME:
			langTable = new ReFrenchfr();
			break;
		case LogoResConstants.RE_ALBANIANKV_NAME:
			langTable = new ReAlbaniankv();
			break;
		case LogoResConstants.RE_TURKMENTM_NAME:
			langTable = new ReTurkmentm();
			break;
		case LogoResConstants.RE_ARABICEG_NAME:
			langTable = new ReArabiceg();
			break;
		case LogoResConstants.RE_ARABICSA_NAME:
			langTable = new ReArabicsa();
			break;
		default:
			break;
		}
		if (langTable != null) {
			langTable.setResourceitemref(itemId);
			langTable.setResourcestr(resourceStr);
		}
		return langTable;
	}

	public String getLangTo() {
		return langTo;
	}

	public String getLang() {
		return lang;
	}
}
