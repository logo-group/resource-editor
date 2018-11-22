package com.logo.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RE_TURKISHTR", indexes = { @Index(name = "I_TURKISHTR_INFO", columnList = "INFO,ID", unique = true),
		@Index(name = "I_TURKISHTR_RESITEMREF", columnList = "RESOURCEITEMREF", unique = false) })
public class ReTurkishtr extends ReLanguageTable implements Serializable {

	private static final long serialVersionUID = 1L;

	public ReTurkishtr() {
		/* */
	}
	
}
