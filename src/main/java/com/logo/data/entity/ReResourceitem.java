package com.logo.data.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "RE_RESOURCEITEMS", indexes = {
		@Index(name = "I_RESOURCEITEMS_INFO", columnList = "INFO,ID", unique = true),
		@Index(name = "I_RESOURCEITEMS_ORDERNR_TAGNR", columnList = "RESOURCEREF,ORDERNR,TAGNR", unique = true),
		@Index(name = "I_RESOURCEITEMS_TAGNR", columnList = "RESOURCEREF,TAGNR", unique = true) })
@EntityListeners(AuditingEntityListener.class)
public class ReResourceitem extends ReResourceitemBase implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";

	public ReResourceitem() {
		/* */
	}

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReTurkishtr reTurkishtr;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReEnglishus reEnglishus;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReGermande reGermande;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private RePersianir rePersianir;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReAzerbaijaniaz reAzerbaijaniaz;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReBulgarianbg reBulgarianbg;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReRussianru reRussianru;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReRomanianro reRomanianro;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReGeorgiange reGeorgiange;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReArabicjo reArabicjo;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReFrenchfr reFrenchfr;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReAlbaniankv reAlbaniankv;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReTurkmentm reTurkmentm;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReArabiceg reArabiceg;

	@OneToOne(mappedBy = "reResourceitem", orphanRemoval = true, fetch = FetchType.LAZY)
	private ReArabicsa reArabicsa;

	public ReTurkishtr getReTurkishtr() {
		return reTurkishtr;
	}

	public void setReTurkishtr(ReTurkishtr reTurkishtr) {
		this.reTurkishtr = reTurkishtr;
	}

	public ReEnglishus getReEnglishus() {
		return reEnglishus;
	}

	public void setReEnglishus(ReEnglishus reEnglishus) {
		this.reEnglishus = reEnglishus;
	}

	public ReGermande getReGermande() {
		return reGermande;
	}

	public void setReGermande(ReGermande reGermande) {
		this.reGermande = reGermande;
	}

	public RePersianir getRePersianir() {
		return rePersianir;
	}

	public void setRePersianir(RePersianir rePersianir) {
		this.rePersianir = rePersianir;
	}

	public ReAzerbaijaniaz getReAzerbaijaniaz() {
		return reAzerbaijaniaz;
	}

	public void setReAzerbaijaniaz(ReAzerbaijaniaz reAzerbaijaniaz) {
		this.reAzerbaijaniaz = reAzerbaijaniaz;
	}

	public ReBulgarianbg getReBulgarianbg() {
		return reBulgarianbg;
	}

	public void setReBulgarianbg(ReBulgarianbg reBulgarianbg) {
		this.reBulgarianbg = reBulgarianbg;
	}

	public ReRussianru getReRussianru() {
		return reRussianru;
	}

	public void setReRussianru(ReRussianru reRussianru) {
		this.reRussianru = reRussianru;
	}

	public ReRomanianro getReRomanianro() {
		return reRomanianro;
	}

	public void setReRomanianro(ReRomanianro reRomanianro) {
		this.reRomanianro = reRomanianro;
	}

	public ReGeorgiange getReGeorgiange() {
		return reGeorgiange;
	}

	public void setReGeorgiange(ReGeorgiange reGeorgiange) {
		this.reGeorgiange = reGeorgiange;
	}

	public ReArabicjo getReArabicjo() {
		return reArabicjo;
	}

	public void setReArabicjo(ReArabicjo reArabicjo) {
		this.reArabicjo = reArabicjo;
	}

	public ReFrenchfr getReFrenchfr() {
		return reFrenchfr;
	}

	public void setReFrenchfr(ReFrenchfr reFrenchfr) {
		this.reFrenchfr = reFrenchfr;
	}

	public ReAlbaniankv getReAlbaniankv() {
		return reAlbaniankv;
	}

	public void setReAlbaniankv(ReAlbaniankv reAlbaniankv) {
		this.reAlbaniankv = reAlbaniankv;
	}

	public ReTurkmentm getReTurkmentm() {
		return reTurkmentm;
	}

	public void setReTurkmentm(ReTurkmentm reTurkmentm) {
		this.reTurkmentm = reTurkmentm;
	}

	public ReArabiceg getReArabiceg() {
		return reArabiceg;
	}

	public void setReArabiceg(ReArabiceg reArabiceg) {
		this.reArabiceg = reArabiceg;
	}

	public ReArabicsa getReArabicsa() {
		return reArabicsa;
	}

	public void setReArabicsa(ReArabicsa reArabicsa) {
		this.reArabicsa = reArabicsa;
	}

	public ReResourceitem copyResourceItem(ReResource resource) {
		ReResourceitem copiedResourceItem = new ReResourceitem();
		copiedResourceItem.setActive(getActive());
		copiedResourceItem.setInfo(getInfo());
		copiedResourceItem.setLevelnr(getLevelnr());
		copiedResourceItem.setLocalizationaffected(getLocalizationaffected());
		copiedResourceItem.setOrdernr(getOrdernr());
		copiedResourceItem.setOwnerproduct(getOwnerproduct());
		copiedResourceItem.setPrefixstr(getPrefixstr());
		copiedResourceItem.setRequested(getRequested());
		copiedResourceItem.setResourcecase(resource.getResourcecase());
		copiedResourceItem.setResourcecategory(resource.getResourcecategory());
		copiedResourceItem.setResourceref(resource.getId());
		copiedResourceItem.setResourcetype(resource.getResourcetype().getTyp());
		copiedResourceItem.setRightlevelnr(getRightlevelnr());
		copiedResourceItem.setRightparentid(getRightparentid());
		copiedResourceItem.setTagnr(getTagnr());
		copiedResourceItem.setVersion(getVersion());
		copiedResourceItem.setReTurkishtr(getReTurkishtr());
		copiedResourceItem.setReEnglishus(getReEnglishus());
		copiedResourceItem.setReGermande(getReGermande());
		copiedResourceItem.setRePersianir(getRePersianir());
		copiedResourceItem.setReAzerbaijaniaz(getReAzerbaijaniaz());
		copiedResourceItem.setReBulgarianbg(getReBulgarianbg());
		copiedResourceItem.setReRussianru(getReRussianru());
		copiedResourceItem.setReRomanianro(getReRomanianro());
		copiedResourceItem.setReGeorgiange(getReGeorgiange());
		copiedResourceItem.setReArabicjo(getReArabicjo());
		copiedResourceItem.setReFrenchfr(getReFrenchfr());
		copiedResourceItem.setReAlbaniankv(getReAlbaniankv());
		copiedResourceItem.setReTurkmentm(getReTurkmentm());
		copiedResourceItem.setReArabiceg(getReArabiceg());
		copiedResourceItem.setReArabicsa(getReArabicsa());
		return copiedResourceItem;
	}

	/**
	 * @PostRemove protected void afterDelete() { ReTurkishtrRep reTurkishtrRep =
	 *             LogoresMainUI.getMrepositorycontainer().getReTurkishtrRep();
	 *             List<ReTurkishtr> reTurkishtrs =
	 *             reTurkishtrRep.findByresourceitemrefEqualsForDelete(getId());
	 *             reTurkishtrs.forEach(item -> reTurkishtrRep.delete(item)); }
	 **/
}
