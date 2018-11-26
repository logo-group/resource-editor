package com.logo.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.logo.util.converter.OwnerProductConverter;
import com.logo.util.converter.ResourceCaseConverter;
import com.logo.util.enums.OwnerProduct;
import com.logo.util.enums.ResourceCase;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class ReResourceitemBase implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	@Id
	@Column(name = "ID", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "ACTIVE")
	private Integer active;

	@LastModifiedDate
	@Type(type = "java.time.LocalDateTime")
	@Column(name = "AUTOMODIFIEDON", columnDefinition = "datetime")
	@DateTimeFormat
	private LocalDateTime automodifiedon;

	@Column(name = "CREATEDBY")
	private Integer createdby;

	@CreatedDate
	@Type(type = "java.time.LocalDateTime")
	@Column(name = "CREATEDON", columnDefinition = "datetime")
	@DateTimeFormat
	private LocalDateTime createdon;

	@Column(name = "INFO", columnDefinition = "nvarchar(20)")
	private String info;

	@Column(name = "LEVELNR")
	private Integer levelnr;

	@Column(name = "LOCALIZATIONAFFECTED")
	private Integer localizationaffected;

	@Column(name = "MODIFIEDBY")
	private Integer modifiedby;

	@LastModifiedDate
	@Type(type = "java.time.LocalDateTime")
	@Column(name = "MODIFIEDON", columnDefinition = "datetime")
	@DateTimeFormat
	private LocalDateTime modifiedon;

	@Column(name = "ORDERNR")
	private Integer ordernr;

	@Convert(converter = OwnerProductConverter.class)
	@Column(name = "OWNERPRODUCT")
	private OwnerProduct ownerproduct;

	@Column(name = "PREFIXSTR", columnDefinition = "nvarchar(30)")
	private String prefixstr;

	@Column(name = "REQUESTED")
	private Integer requested;

	@Convert(converter = ResourceCaseConverter.class)
	@Column(name = "RESOURCECASE")
	private ResourceCase resourcecase;

	@Column(name = "RESOURCECATEGORY")
	private Integer resourcecategory;

	@Column(name = "RESOURCEREF")
	private Integer resourceref;

	@Column(name = "RESOURCETYPE")
	private Integer resourcetype;

	@Column(name = "RIGHTLEVELNR")
	private Integer rightlevelnr;

	@Column(name = "RIGHTPARENTID")
	private Integer rightparentid;

	@Column(name = "TAGNR")
	private Integer tagnr;

	@Column(name = "VERSION")
	private Integer version;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final Integer getActive() {
		return active;
	}

	public final void setActive(Integer active) {
		this.active = active;
	}

	public final LocalDateTime getAutomodifiedon() {
		return automodifiedon;
	}

	public final void setAutomodifiedon(LocalDateTime automodifiedon) {
		this.automodifiedon = automodifiedon;
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

	public final Integer getLevelnr() {
		return levelnr;
	}

	public final void setLevelnr(Integer levelnr) {
		this.levelnr = levelnr;
	}

	public final Integer getLocalizationaffected() {
		return localizationaffected;
	}

	public final void setLocalizationaffected(Integer localizationaffected) {
		this.localizationaffected = localizationaffected;
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

	public final Integer getOrdernr() {
		return ordernr;
	}

	public final void setOrdernr(Integer ordernr) {
		this.ordernr = ordernr;
	}

	public final OwnerProduct getOwnerproduct() {
		return ownerproduct;
	}

	public final void setOwnerproduct(OwnerProduct ownerproduct) {
		this.ownerproduct = ownerproduct;
	}

	public final String getPrefixstr() {
		return prefixstr;
	}

	public final void setPrefixstr(String prefixstr) {
		this.prefixstr = prefixstr;
	}

	public final Integer getRequested() {
		return requested;
	}

	public final void setRequested(Integer requested) {
		this.requested = requested;
	}

	public final ResourceCase getResourcecase() {
		return resourcecase;
	}

	public final void setResourcecase(ResourceCase resourcecase) {
		this.resourcecase = resourcecase;
	}

	public final Integer getResourcecategory() {
		return resourcecategory;
	}

	public final void setResourcecategory(Integer resourcecategory) {
		this.resourcecategory = resourcecategory;
	}

	public final Integer getResourceref() {
		return resourceref;
	}

	public final void setResourceref(Integer resourceref) {
		this.resourceref = resourceref;
	}

	public final Integer getResourcetype() {
		return resourcetype;
	}

	public final void setResourcetype(Integer resourcetype) {
		this.resourcetype = resourcetype;
	}

	public final Integer getRightlevelnr() {
		return rightlevelnr;
	}

	public final void setRightlevelnr(Integer rightlevelnr) {
		this.rightlevelnr = rightlevelnr;
	}

	public final Integer getRightparentid() {
		return rightparentid;
	}

	public final void setRightparentid(Integer rightparentid) {
		this.rightparentid = rightparentid;
	}

	public final Integer getTagnr() {
		return tagnr;
	}

	public final void setTagnr(Integer tagnr) {
		this.tagnr = tagnr;
	}

	public final Integer getVersion() {
		return version;
	}

	public final void setVersion(Integer version) {
		this.version = version;
	}

	public ReResourceitemBase() {
		/* */
	}

	@ManyToOne
	@JoinColumn(name = "resourceref", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	public ReResource reResource;

	@PrePersist
	@PreUpdate
	protected void beforeInsertOrUpdate() {
		automodifiedon = LocalDateTime.now();
		modifiedon = LocalDateTime.now();
	}

	/**
	 * @PostRemove protected void afterDelete() { ReTurkishtrRep reTurkishtrRep =
	 *             LogoresMainUI.getMrepositorycontainer().getReTurkishtrRep();
	 *             List<ReTurkishtr> reTurkishtrs =
	 *             reTurkishtrRep.findByresourceitemrefEqualsForDelete(getId());
	 *             reTurkishtrs.forEach(item -> reTurkishtrRep.delete(item)); }
	 **/
}
