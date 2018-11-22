package com.logo.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "re_resourceitems", indexes = {
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

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReTurkishtr> reTurkishtr = new ArrayList<>();

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReEnglishus> reEnglishus;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReGermande> reGermande;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<RePersianir> rePersianir;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReAzerbaijaniaz> reAzerbaijaniaz;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReBulgarianbg> reBulgarianbg;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReRussianru> reRussianru;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReRomanianro> reRomanianro;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReGeorgiange> reGeorgiange;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReArabicjo> reArabicjo;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReFrenchfr> reFrenchfr;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReAlbaniankv> reAlbaniankv;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReTurkmentm> reTurkmentm;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReArabiceg> reArabiceg;

	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReArabicsa> reArabicsa;

	public List<ReTurkishtr> getReTurkishtr()
	{
		return reTurkishtr;
	}
	
	public void setReTurkishtr(List<ReTurkishtr> reTurkishtr)
	{
		this.reTurkishtr = reTurkishtr;
	}
	
	public List<ReEnglishus> getReEnglishus() {
		return reEnglishus;
	}
	
	public void setReEnglishus(List<ReEnglishus> reEnglishus) {
		this.reEnglishus = reEnglishus;
	}

	public List<ReGermande> getReGermande() {
		return reGermande;
	}
	
	public void setReGermande(List<ReGermande> reGermande) {
		this.reGermande = reGermande;
	}
	
	public List<RePersianir> getRePersianir() {
		return rePersianir;
	}
	
	public void setRePersianir(List<RePersianir> rePersianir) {
		this.rePersianir = rePersianir;
	}
	
	public List<ReAzerbaijaniaz> getReAzerbaijaniaz() {
		return reAzerbaijaniaz;
	}
	
	public void setReAzerbaijaniaz(List<ReAzerbaijaniaz> reAzerbaijaniaz) {
		this.reAzerbaijaniaz = reAzerbaijaniaz;
	}
	
	public List<ReBulgarianbg> getReBulgarianbg() {
		return reBulgarianbg;
	}
	
	public void setReBulgarianbg(List<ReBulgarianbg> reBulgarianbg) {
		this.reBulgarianbg = reBulgarianbg;
	}
	
	public List<ReRussianru> getReRussianru() {
		return reRussianru;
	}
	
	public void setReRussianru(List<ReRussianru> reRussianru) {
		this.reRussianru = reRussianru;
	}
	
	public List<ReRomanianro> getReRomanianro() {
		return reRomanianro;
	}
	
	public void setReRomanianro(List<ReRomanianro> reRomanianro) {
		this.reRomanianro = reRomanianro;
	}
	
	public List<ReGeorgiange> getReGeorgiange() {
		return reGeorgiange;
	}
	
	public void setReGeorgiange(List<ReGeorgiange> reGeorgiange) {
		this.reGeorgiange = reGeorgiange;
	}
	
	public List<ReArabicjo> getReArabicjo() {
		return reArabicjo;
	}
	
	public void setReArabicjo(List<ReArabicjo> reArabicjo) {
		this.reArabicjo = reArabicjo;
	}

	public List<ReFrenchfr> getReFrenchfr() {
		return reFrenchfr;
	}
	
	public void setReFrenchfr(List<ReFrenchfr> reFrenchfr) {
		this.reFrenchfr = reFrenchfr;
	}

	public List<ReAlbaniankv> getReAlbaniankv() {
		return reAlbaniankv;
	}
	
	public void setReAlbaniankv(List<ReAlbaniankv> reAlbaniankv) {
		this.reAlbaniankv = reAlbaniankv;
	}

	public List<ReTurkmentm> getReTurkmentm() {
		return reTurkmentm;
	}
	
	public void setReTurkmentm(List<ReTurkmentm> reTurkmentm) {
		this.reTurkmentm = reTurkmentm;
	}
	
	public List<ReArabiceg> getReArabiceg() {
		return reArabiceg;
	}
	
	public void setReArabiceg(List<ReArabiceg> reArabiceg) {
		this.reArabiceg = reArabiceg;
	}
	
	public List<ReArabicsa> getReArabicsa() {
		return reArabicsa;
	}
	
	public void setReArabicsa(List<ReArabicsa> reArabicsa) {
		this.reArabicsa = reArabicsa;
	}
	
	public ReResource getReResource() {
		return reResource;
	}
	/**
	@PostRemove
	protected void afterDelete() {
		ReTurkishtrRep reTurkishtrRep = LogoresMainUI.getMrepositorycontainer().getReTurkishtrRep();
		List<ReTurkishtr> reTurkishtrs = reTurkishtrRep.findByresourceitemrefEqualsForDelete(getId());
		reTurkishtrs.forEach(item -> reTurkishtrRep.delete(item));
	}
	**/
}
