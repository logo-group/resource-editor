package com.logo.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RE_ALBANIANKV", indexes = { @Index(name = "I_ALBANIANKV_INFO", columnList = "INFO,ID", unique = true),
		@Index(name = "I_ALBANIANKV_RESITEMREF", columnList = "RESOURCEITEMREF", unique = false) })
public class ReAlbaniankv extends ReLanguageTable implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne  
	@JoinColumn(name="resourceitemref", referencedColumnName="id",insertable=false, updatable=false)
	public ReResourceitem reResourceitem;

	public ReAlbaniankv() {
		/* */
	}

}
