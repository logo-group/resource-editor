package com.logo.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class ReLanguageTable {

	@Id
	@Column(name = "ID", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@CreatedBy
	@Column(name = "CREATEDBY")
	private Integer createdby;

	@CreatedDate
	@Type(type = "java.time.LocalDateTime")
	@Column(name = "CREATEDON", columnDefinition = "datetime")
	@DateTimeFormat
	private LocalDateTime createdon;

	@Column(name = "INFO", columnDefinition = "nvarchar(20)")
	private String info;

	@LastModifiedBy
	@Column(name = "MODIFIEDBY")
	private Integer modifiedby;

	@LastModifiedDate
	@Type(type = "java.time.LocalDateTime")
	@Column(name = "MODIFIEDON", columnDefinition = "datetime")
	@DateTimeFormat
	private LocalDateTime modifiedon;

	@Column(name = "RESOURCEITEMREF")
	private Integer resourceitemref;

	@Column(name = "RESOURCESTR", columnDefinition = "nvarchar(900)")
	private String resourcestr;

	@Column(name = "TRKEY", columnDefinition = "nvarchar(40)")
	private String trkey;

	@Column(name = "[VERSION]")
	private Integer version;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final Integer getCreatedby() {
		return createdby;
	}

	public final void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	public final LocalDateTime getCreatedon() {
		return createdon;
	}

	public final void setCreatedon(LocalDateTime createdon) {
		this.createdon = createdon;
	}

	public final String getInfo() {
		return info;
	}

	public final void setInfo(String info) {
		this.info = info;
	}

	public final Integer getModifiedby() {
		return modifiedby;
	}

	public final void setModifiedby(Integer modifiedby) {
		this.modifiedby = modifiedby;
	}

	public final LocalDateTime getModifiedon() {
		return modifiedon;
	}

	public final void setModifiedon(LocalDateTime modifiedon) {
		this.modifiedon = modifiedon;
	}

	public final Integer getResourceitemref() {
		return resourceitemref;
	}

	public final void setResourceitemref(Integer resourceitemref) {
		this.resourceitemref = resourceitemref;
	}

	public final String getResourcestr() {
		return resourcestr;
	}

	public final void setResourcestr(String resourcestr) {
		this.resourcestr = resourcestr;
	}

	public final String getTrkey() {
		return trkey;
	}

	public final void setTrkey(String trkey) {
		this.trkey = trkey;
	}

	public final Integer getVersion() {
		return version;
	}

	public final void setVersion(Integer version) {
		this.version = version;
	}

	public ReLanguageTable() {
		/* */
	}
}
