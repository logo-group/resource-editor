package com.logo.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.logo.util.converter.MessageTypeConverter;
import com.logo.util.converter.ResourceStateConverter;
import com.logo.util.enums.MessageType;
import com.logo.util.enums.ResourceState;

@Entity
@Table(name = "RE_MESSAGES", indexes = { @Index(name = "I_MESSAGES_CONSID", columnList = "CONS_ID,ID", unique = true) })
@EntityListeners(AuditingEntityListener.class)
public class ReMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, updatable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Convert(converter = ResourceStateConverter.class)
	@Column(name = "ACTIVE")
	private ResourceState active;

	@Column(name = "BUTTONS", nullable = false)
	private int buttons = 0;

	@Column(name = "CONS_ID", columnDefinition = "nvarchar(200)", nullable = false)
	private String consId = "";

	@Column(name = "DEF_BUTTON", nullable = false)
	private int defButton = 0;

	@Column(name = "DETAIL_LISTID")
	private int detailListid = 0;

	@Column(name = "DETAIL_RESGROUP", columnDefinition = "nvarchar(20)")
	private String detailResgroup = "";

	@Column(name = "DETAIL_TAGNR")
	private int detailTagnr = 0;

	@Column(name = "LISTID", nullable = false)
	private int listid = 0;

	@Column(name = "MODIFIEDBY", nullable = false)
	private int modifiedby = 0;

	@Type(type = "java.time.LocalDateTime")
	@Column(name = "MODIFIEDON", columnDefinition = "datetime", nullable = false)
	@DateTimeFormat
	private LocalDateTime modifiedon;

	@Column(name = "[MODULE]", columnDefinition = "nvarchar(20)", nullable = false)
	private String module = "";

	@Convert(converter = MessageTypeConverter.class)
	@Column(name = "MTYPE", nullable = false)
	private MessageType mtype = MessageType.ERROR;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESGROUP", nullable = false)
	private ReResourceGroup resgroup;

	@Column(name = "STRTAG", nullable = false)
	private int strtag = 0;

	public int getId() {
		return this.id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final ResourceState getActive() {
		return this.active;
	}

	public final void setActive(ResourceState active) {
		this.active = active;
	}

	public final int getButtons() {
		return this.buttons;
	}

	public final void setButtons(int buttons) {
		this.buttons = buttons;
	}

	public final String getConsId() {
		return this.consId;
	}

	public final void setConsId(String consId) {
		this.consId = consId;
	}

	public final int getDefButton() {
		return this.defButton;
	}

	public final void setDefButton(int defButton) {
		this.defButton = defButton;
	}

	public final int getDetailListid() {
		return this.detailListid;
	}

	public final void setDetailListid(int detailListid) {
		this.detailListid = detailListid;
	}

	public final Object getDetailResgroup() {
		return this.detailResgroup;
	}

	public final void setDetailResgroup(String detailResgroup) {
		this.detailResgroup = detailResgroup;
	}

	public final int getDetailTagnr() {
		return this.detailTagnr;
	}

	public final void setDetailTagnr(int detailTagnr) {
		this.detailTagnr = detailTagnr;
	}

	public final int getListid() {
		return this.listid;
	}

	public final void setListid(int listid) {
		this.listid = listid;
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

	public final String getModule() {
		return this.module;
	}

	public final void setModule(String module) {
		this.module = module;
	}

	public final MessageType getMtype() {
		return this.mtype;
	}

	public final void setMtype(MessageType mtype) {
		this.mtype = mtype;
	}

	public ReResourceGroup getResgroup() {
		return resgroup;
	}

	public void setResgroup(ReResourceGroup resgroup) {
		this.resgroup = resgroup;
	}

	public final int getStrtag() {
		return this.strtag;
	}

	public final void setStrtag(int strtag) {
		this.strtag = strtag;
	}

	public final boolean isPersisted() {
		return id != null;
	}

	public ReMessage() {
		/**
		 * */
	}

}
