package com.logo.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "RE_STANDARD", indexes = { @Index(name = "I_STANDARD_INFO", columnList = "INFO,ID", unique = true),
		@Index(name = "I_STANDARD_RESITEMREF", columnList = "RESOURCEITEMREF", unique = true) })
@EntityListeners(AuditingEntityListener.class)
public class ReStandard implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";

	@Id
	@Column(name = "ID", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "RESOURCESTR")
	private String resourceStr;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESOURCEITEMREF", referencedColumnName = "ID", insertable = false, updatable = false)
	public ReResourceitem resourceItem;

	@Column(name = "RESOURCEITEMREF")
	private Integer resourceitemref;

	@Column(name = "CREATEDBY")
	private Integer createdby;

	@CreatedDate
	@Type(type = "java.time.LocalDateTime")
	@Column(name = "CREATEDON", columnDefinition = "datetime")
	@DateTimeFormat
	private LocalDateTime createdon;

	@Column(name = "INFO", columnDefinition = "nvarchar(20)")
	private String info;

	@Column(name = "MODIFIEDBY")
	private Integer modifiedby;

	@LastModifiedDate
	@Type(type = "java.time.LocalDateTime")
	@Column(name = "MODIFIEDON", columnDefinition = "datetime")
	@DateTimeFormat
	private LocalDateTime modifiedon;

	@Column(name = "VERSION")
	private Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourceStr() {
		return resourceStr;
	}

	public void setResourceStr(String resourceStr) {
		this.resourceStr = resourceStr;
	}

	public ReResourceitem getResourceItem() {
		return resourceItem;
	}

	public void setResourceItem(ReResourceitem resourceItem) {
		this.resourceItem = resourceItem;
	}

	public Integer getResourceitemref() {
		return resourceitemref;
	}

	public void setResourceitemref(Integer resourceitemref) {
		this.resourceitemref = resourceitemref;
	}

	public Integer getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	public LocalDateTime getCreatedon() {
		return createdon;
	}

	public void setCreatedon(LocalDateTime createdon) {
		this.createdon = createdon;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(Integer modifiedby) {
		this.modifiedby = modifiedby;
	}

	public LocalDateTime getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(LocalDateTime modifiedon) {
		this.modifiedon = modifiedon;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ReStandard cloneStandard(Integer itemId) {
		ReStandard reStandard = new ReStandard();
		reStandard.setResourceStr(resourceStr);
		reStandard.setInfo(info);
		reStandard.setVersion(version);
		reStandard.setResourceitemref(itemId);
		return reStandard;
	}

}
