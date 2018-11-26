package com.logo.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "RE_RESOURCEITEMSHORT")
public class ReResourceitemShort extends ReResourceitemBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReResourceitemShort() {
		/* */
	}

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReTurkishtr> reTurkishtr = new ArrayList<>();

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReEnglishus> reEnglishus;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReGermande> reGermande;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<RePersianir> rePersianir;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReAzerbaijaniaz> reAzerbaijaniaz;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReBulgarianbg> reBulgarianbg;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReRussianru> reRussianru;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReRomanianro> reRomanianro;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReGeorgiange> reGeorgiange;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReArabicjo> reArabicjo;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReFrenchfr> reFrenchfr;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReAlbaniankv> reAlbaniankv;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReTurkmentm> reTurkmentm;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReArabiceg> reArabiceg;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID")
	private List<ReArabicsa> reArabicsa;

}
