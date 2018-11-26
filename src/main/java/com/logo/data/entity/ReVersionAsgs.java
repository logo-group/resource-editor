package com.logo.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "re_versionasgs")
@Entity
public class ReVersionAsgs implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "VERSIONID")
	private int versionid = 0;	

	@Column(name = "RESOURCEID")
	private int resourceid = 0;	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getVersionid() {
		return versionid;
	}
	
	public void setVersionid(int versionid) {
		this.versionid = versionid;
	}
	
	public int getResourceid() {
		return resourceid;
	}
	
	public void setResourceid(int resourceid) {
		this.resourceid = resourceid;
	}
	
	public boolean isPersisted() {
		return id != null;
	}

	public ReVersionAsgs() {
		/* */
	}

}
