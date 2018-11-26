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

@Table(name = "PROJECT")
@Entity
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "projectnr", unique = true)
	@NotNull
	private Integer projectNr;
	
	@Column(name = "projectname", nullable = false, unique = true)
	@Size(min = 2, max = 50)
	@NotNull
	private String projectName;

	@Column(name = "projectdef", nullable = false, unique = false)
	@Size(min = 2, max = 100)
	@NotNull
	private String projectDef;

	public Integer getProjectnr() {
		return projectNr;
	}
	
	public void setProjectnr(Integer projectNr) {
		this.projectNr = projectNr;
	}

	public String getProjectname() {
		return projectName;
	}
	
	public void setProjectname(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProjectdef() {
		return projectDef;
	}
	
	public void setProjectdef(String projectDef) {
		this.projectDef = projectDef;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPersisted() {
		return id != null;
	}

	public Project() {
		/* */
	}

}
