package com.logo.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "re_projectversion")
@Entity
public class ReProjectVersion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(name = "versionnr", nullable = false, unique = true)
	@Size(min = 2, max = 50)
	@NotNull
	private String versionnr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersionnr() {
		return versionnr;
	}
	
	public void setVersionnr(String versionnr) {
		this.versionnr = versionnr;
	}
	
	public boolean isPersisted() {
		return id != null;
	}

	public ReProjectVersion() {
		/* */
	}
}
