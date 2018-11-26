package com.logo.data.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "RE_ENGLISHUS", indexes = { @Index(name = "I_ENGLISHUS_INFO", columnList = "INFO,ID", unique = true),
		@Index(name = "I_ENGLISHUS_RESITEMREF", columnList = "RESOURCEITEMREF", unique = false) })
public class ReEnglishus extends ReLanguageTable implements Serializable {

	private static final long serialVersionUID = 1L;

	public ReEnglishus() {
		/* */
	}

}
