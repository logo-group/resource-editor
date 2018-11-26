package com.logo.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.logo.util.converter.DocTypeConverter;
import com.logo.util.enums.DocType;

@Entity
@Table(name = "RE_HELPDOCS", indexes = {
		@Index(name = "I_HELPDOCS_01", columnList = "DOCNAME,DOCTITLE,DOCTYPE", unique = true) })
@EntityListeners(AuditingEntityListener.class)
public class ReHelpDocs implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, updatable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "DOCNAME", columnDefinition = "nvarchar(200)", nullable = false)
	private String docname;

	@Column(name = "DOCTITLE", columnDefinition = "nvarchar(200)", nullable = false)
	private String doctitle;

	@Convert(converter = DocTypeConverter.class)
	@Column(name = "DOCTYPE", nullable = false)
	private DocType doctype;

	@Column(name = "DOCBODY", columnDefinition = "varchar(8000)", nullable = false)
	private String docbody;

	@Column(name = "CREATEDBY")
	private int createdby;

	@CreatedDate
	@Type(type = "java.time.LocalDateTime")
	@Column(name = "CREATEDON", columnDefinition = "datetime")
	@DateTimeFormat
	private LocalDateTime createdon;

	@Column(name = "MODIFIEDBY", nullable = false)
	private int modifiedby = 0;

	@Type(type = "java.time.LocalDateTime")
	@Column(name = "MODIFIEDON", columnDefinition = "datetime", nullable = false)
	@DateTimeFormat
	private LocalDateTime modifiedon;

	@Column(name = "VERSION")
	private int version;

	public int getId() {
		return this.id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getDocname() {
		return docname;
	}

	public final void setDocname(String docname) {
		this.docname = docname;
	}

	public final String getDoctitle() {
		return doctitle;
	}

	public final void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}

	public final DocType getDoctype() {
		return doctype;
	}

	public final void setDoctype(DocType doctype) {
		this.doctype = doctype;
	}

	public final String getDocbody() {
		return docbody;
	}

	public final void setDocbody(String docbody) {
		this.docbody = docbody;
	}

	public final int getVersion() {
		return version;
	}

	public final void setVersion(int version) {
		this.version = version;
	}

	public final int getCreatedby() {
		return createdby;
	}

	public final void setCreatedby(int createdby) {
		this.createdby = createdby;
	}

	public final LocalDateTime getCreatedon() {
		return createdon;
	}

	public final void setCreatedon(LocalDateTime createdon) {
		this.createdon = createdon;
	}

	public final int getModifiedby() {
		return this.modifiedby;
	}

	public final void setModifiedby(int modifiedby) {
		this.modifiedby = modifiedby;
	}

	public final LocalDateTime getModifiedon() {
		return this.modifiedon;
	}

	public final void setModifiedon(LocalDateTime modifiedon) {
		this.modifiedon = modifiedon;
	}

	public final boolean isPersisted() {
		return id != null;
	}

	public ReHelpDocs() {
		/**
		 * */
	}

}
