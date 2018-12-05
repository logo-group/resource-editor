package com.logo.data.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RE_ARABICJO", indexes = { @Index(name = "I_ARABICJO_INFO", columnList = "INFO,ID", unique = true),
		@Index(name = "I_ARABICJO_RESITEMREF", columnList = "RESOURCEITEMREF", unique = false) })
public class ReArabicjo extends ReLanguageTable implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID", insertable = false, updatable = false)
	public ReResourceitem reResourceitem;

	public ReArabicjo() {
		/* */
	}

	public ReArabicjo cloneArabicjo(ReResourceitem item) {
		ReArabicjo arabicjo = new ReArabicjo();
		arabicjo = cloneLanguage(item, arabicjo);
		return arabicjo;
	}
}
