package com.logo.data.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RE_ARABICEG", indexes = { @Index(name = "I_ARABICEG_INFO", columnList = "INFO,ID", unique = true),
		@Index(name = "I_ARABICEG_RESITEMREF", columnList = "RESOURCEITEMREF", unique = false) })
public class ReArabiceg extends ReLanguageTable implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID", insertable = false, updatable = false)
	public ReResourceitem reResourceitem;

	public ReArabiceg() {
		/* */
	}

	public ReArabiceg cloneArabiceg(ReResourceitem item) {
		ReArabiceg arabiceg = new ReArabiceg();
		arabiceg = cloneLanguage(item, arabiceg);
		return arabiceg;
	}

}
