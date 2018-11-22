package com.logo.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class ReResourceitemShort extends ReResourceitemBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public ReResourceitemShort() {
		/* */
	}

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReTurkishtr> reTurkishtr = new ArrayList<>();

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReEnglishus> reEnglishus;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReGermande> reGermande;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<RePersianir> rePersianir;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReAzerbaijaniaz> reAzerbaijaniaz;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReBulgarianbg> reBulgarianbg;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReRussianru> reRussianru;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReRomanianro> reRomanianro;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReGeorgiange> reGeorgiange;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReArabicjo> reArabicjo;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReFrenchfr> reFrenchfr;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReAlbaniankv> reAlbaniankv;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReTurkmentm> reTurkmentm;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReArabiceg> reArabiceg;

	@OneToMany(orphanRemoval = true,fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="resourceitemref", referencedColumnName="id")
	private List<ReArabicsa> reArabicsa;


}
