package com.logo.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.logo.util.ResourceGroup;
import com.logo.util.UserLanguage;
import com.logo.util.UserLayoutType;
import com.logo.util.UserType;
import com.logo.util.UserTypeConverter;

@Entity
@Table(name="RE_USERS",indexes = { @Index(name = "I_USERS_02", columnList = "USERNAME", unique = true) })
@EntityListeners(AuditingEntityListener.class)
public class ReUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name="ABBRACCESSRIGHTS")
	private Integer abbraccessrights = 0;

	@Column(name="ALTEMAIL", columnDefinition = "nvarchar(100)")
	private String altemail = "";

	@Column(name="ARAEACCESSRIGHTS")
	private Integer araeaccessrights = 0;

	@Column(name="AREGACCESSRIGHTS")
	private Integer aregaccessrights = 0;

	@Column(name="ARJOACCESSRIGHTS")
	private Integer arjoaccessrights = 0;

	@Column(name="ARSAACCESSRIGHTS")
	private Integer arsaaccessrights = 0;

	@Column(name="AZAZACCESSRIGHTS")
	private Integer azazaccessrights = 0;

	@Column(name="BGBGACCESSRIGHTS")
	private Integer bgbgaccessrights = 0;

	@CreatedBy
	@Column(name="CREATEDBY")
	private Integer createdby;

	@CreatedDate
	@Type(type = "java.time.LocalDateTime")
	@Column(name = "CREATEDON", columnDefinition = "datetime")
	@DateTimeFormat
	private transient LocalDateTime createdon;

	@Column(name="DEDEACCESSRIGHTS")
	private Integer dedeaccessrights = 0;

	@Enumerated(EnumType.STRING)
	@Column(name="DEFAULTLANGUAGE", columnDefinition = "nvarchar(8)")
	private UserLanguage defaultlanguage = UserLanguage.TRTR;

	@Enumerated(EnumType.STRING)
	@Column(name="DEFAULTORIENTATION", columnDefinition = "nvarchar(8)")
	private UserLayoutType defaultorientation = UserLayoutType.V;

	@Enumerated(EnumType.STRING)
	@Column(name="DEFAULTRESOURCEGROUP", columnDefinition = "nvarchar(8)")
	private ResourceGroup defaultresourcegroup = ResourceGroup.UN;

	@Column(name="DEPARTMENT", columnDefinition = "nvarchar(60)")
	private String department = "";

	@Column(name="DISPLAYED")
	private Integer displayed = 0;

	@Column(name="EMAIL", columnDefinition = "nvarchar(100)")
	private String email = "";

	@Column(name="ENABLED")
	private Integer enabled = 0;

	@Column(name="ENUSACCESSRIGHTS")
	private Integer enusaccessrights = 0;

	@Column(name="FAIRACCESSRIGHTS")
	private Integer fairaccessrights = 0;

	@Column(name="FRFRACCESSRIGHTS")
	private Integer frfraccessrights = 0;

	@Convert(converter = UserTypeConverter.class)
	@Column(name="GENERALACCESSRIGHTS")
	private UserType generalaccessrights = UserType.PROGRAMMER;

	@Column(name="INVALIDLOGINCOUNT")
	private Integer invalidlogincount = 0;

	@Column(name="KAGEACCESSRIGHTS")
	private Integer kageaccessrights = 0;

	@LastModifiedBy
	@Column(name="MODIFIEDBY")
	private Integer modifiedby;

	@LastModifiedDate
	@Type(type = "java.time.LocalDateTime")
	@Column(name = "MODIFIEDON", columnDefinition = "datetime")
	@DateTimeFormat
	private transient LocalDateTime modifiedon;
	
	@Column(name="NAME", columnDefinition = "nvarchar(40)")
	private String name = "";

	@Column(name="PASSWORD", columnDefinition = "nvarchar(80)")
	private String password;

	@Column(name="PASSWORDREQUEST")
	private Integer passwordrequest = 0;

	@Column(name="REORDERACCESSRIGHTS")
	private Integer reorderaccessrights = 0;

	@Column(name="ROROACCESSRIGHTS")
	private Integer roroaccessrights = 0;

	@Column(name="RURUACCESSRIGHTS")
	private Integer ruruaccessrights = 0;

	@Column(name="SELECTED")
	private Integer selected = 0;

	@Column(name="SQKVACCESSRIGHTS")
	private Integer sqkvaccessrights = 0;

	@Column(name="SRRSACCESSRIGHTS")
	private Integer srrsaccessrights = 0;

	@Column(name="STDACCESSRIGHTS")
	private Integer stdaccessrights = 0;

	@Column(name="SURNAME", columnDefinition = "nvarchar(60)")
	private String surname = "";

	@Column(name="TEMPPASSWORD")
	private Integer temppassword = 0;

	@Column(name="THTHACCESSRIGHTS")
	private Integer ththaccessrights = 0;

	@Column(name="TKTMACCESSRIGHTS")
	private Integer tktmaccessrights = 0;

	@Column(name="TRTRACCESSRIGHTS")
	private Integer trtraccessrights = 0;

	@Column(name="UKUAACCESSRIGHTS")
	private Integer ukuaaccessrights = 0;

	@Column(name="USERNAME", columnDefinition = "nvarchar(40)")
	private String username = "";

	@Column(name="[VERSION]")
	private Integer version = 0;

	@Column(name="ZHCNACCESSRIGHTS")
	private Integer zhcnaccessrights = 0;

	public ReUser() {
		/**/
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAbbraccessrights() {
		return this.abbraccessrights;
	}

	public void setAbbraccessrights(Integer abbraccessrights) {
		this.abbraccessrights = abbraccessrights;
	}

	public String getAltemail() {
		return this.altemail;
	}

	public void setAltemail(String altemail) {
		this.altemail = altemail;
	}

	public Integer getAraeaccessrights() {
		return this.araeaccessrights;
	}

	public void setAraeaccessrights(Integer araeaccessrights) {
		this.araeaccessrights = araeaccessrights;
	}

	public Integer getAregaccessrights() {
		return this.aregaccessrights;
	}

	public void setAregaccessrights(Integer aregaccessrights) {
		this.aregaccessrights = aregaccessrights;
	}

	public Integer getArjoaccessrights() {
		return this.arjoaccessrights;
	}

	public void setArjoaccessrights(Integer arjoaccessrights) {
		this.arjoaccessrights = arjoaccessrights;
	}

	public Integer getArsaaccessrights() {
		return this.arsaaccessrights;
	}

	public void setArsaaccessrights(Integer arsaaccessrights) {
		this.arsaaccessrights = arsaaccessrights;
	}

	public Integer getAzazaccessrights() {
		return this.azazaccessrights;
	}

	public void setAzazaccessrights(Integer azazaccessrights) {
		this.azazaccessrights = azazaccessrights;
	}

	public Integer getBgbgaccessrights() {
		return this.bgbgaccessrights;
	}

	public void setBgbgaccessrights(Integer bgbgaccessrights) {
		this.bgbgaccessrights = bgbgaccessrights;
	}

	public Integer getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	public LocalDateTime getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(LocalDateTime createdon) {
		this.createdon = createdon;
	}

	public Integer getDedeaccessrights() {
		return this.dedeaccessrights;
	}

	public void setDedeaccessrights(Integer dedeaccessrights) {
		this.dedeaccessrights = dedeaccessrights;
	}

	public UserLanguage getDefaultlanguage() {
		return this.defaultlanguage;
	}

	public void setDefaultlanguage(UserLanguage defaultlanguage) {
		this.defaultlanguage = defaultlanguage;
	}

	public UserLayoutType getDefaultorientation() {
		return this.defaultorientation;
	}

	public void setDefaultorientation(UserLayoutType defaultorientation) {
		this.defaultorientation = defaultorientation;
	}

	public ResourceGroup getDefaultresourcegroup() {
		return this.defaultresourcegroup;
	}

	public void setDefaultresourcegroup(ResourceGroup defaultresourcegroup) {
		this.defaultresourcegroup = defaultresourcegroup;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getDisplayed() {
		return this.displayed;
	}

	public void setDisplayed(Integer displayed) {
		this.displayed = displayed;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getEnusaccessrights() {
		return this.enusaccessrights;
	}

	public void setEnusaccessrights(Integer enusaccessrights) {
		this.enusaccessrights = enusaccessrights;
	}

	public Integer getFairaccessrights() {
		return this.fairaccessrights;
	}

	public void setFairaccessrights(Integer fairaccessrights) {
		this.fairaccessrights = fairaccessrights;
	}

	public Integer getFrfraccessrights() {
		return this.frfraccessrights;
	}

	public void setFrfraccessrights(Integer frfraccessrights) {
		this.frfraccessrights = frfraccessrights;
	}

	public UserType getGeneralaccessrights() {
		return this.generalaccessrights;
	}

	public void setGeneralaccessrights(UserType generalaccessrights) {
		this.generalaccessrights = generalaccessrights;
	}

	public Integer getInvalidlogincount() {
		return this.invalidlogincount;
	}

	public void setInvalidlogincount(Integer invalidlogincount) {
		this.invalidlogincount = invalidlogincount;
	}

	public Integer getKageaccessrights() {
		return this.kageaccessrights;
	}

	public void setKageaccessrights(Integer kageaccessrights) {
		this.kageaccessrights = kageaccessrights;
	}

	public Integer getModifiedby() {
		return this.modifiedby;
	}

	public void setModifiedby(Integer modifiedby) {
		this.modifiedby = modifiedby;
	}

	public LocalDateTime getModifiedon() {
		return this.modifiedon;
	}

	public void setModifiedon(LocalDateTime modifiedon) {
		this.modifiedon = modifiedon;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPasswordrequest() {
		return this.passwordrequest;
	}

	public void setPasswordrequest(Integer passwordrequest) {
		this.passwordrequest = passwordrequest;
	}

	public Integer getReorderaccessrights() {
		return this.reorderaccessrights;
	}

	public void setReorderaccessrights(Integer reorderaccessrights) {
		this.reorderaccessrights = reorderaccessrights;
	}

	public Integer getRoroaccessrights() {
		return this.roroaccessrights;
	}

	public void setRoroaccessrights(Integer roroaccessrights) {
		this.roroaccessrights = roroaccessrights;
	}

	public Integer getRuruaccessrights() {
		return this.ruruaccessrights;
	}

	public void setRuruaccessrights(Integer ruruaccessrights) {
		this.ruruaccessrights = ruruaccessrights;
	}

	public Integer getSelected() {
		return this.selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public Integer getSqkvaccessrights() {
		return this.sqkvaccessrights;
	}

	public void setSqkvaccessrights(Integer sqkvaccessrights) {
		this.sqkvaccessrights = sqkvaccessrights;
	}

	public Integer getSrrsaccessrights() {
		return this.srrsaccessrights;
	}

	public void setSrrsaccessrights(Integer srrsaccessrights) {
		this.srrsaccessrights = srrsaccessrights;
	}

	public Integer getStdaccessrights() {
		return this.stdaccessrights;
	}

	public void setStdaccessrights(Integer stdaccessrights) {
		this.stdaccessrights = stdaccessrights;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getTemppassword() {
		return this.temppassword;
	}

	public void setTemppassword(Integer temppassword) {
		this.temppassword = temppassword;
	}

	public Integer getThthaccessrights() {
		return this.ththaccessrights;
	}

	public void setThthaccessrights(Integer ththaccessrights) {
		this.ththaccessrights = ththaccessrights;
	}

	public Integer getTktmaccessrights() {
		return this.tktmaccessrights;
	}

	public void setTktmaccessrights(Integer tktmaccessrights) {
		this.tktmaccessrights = tktmaccessrights;
	}

	public Integer getTrtraccessrights() {
		return this.trtraccessrights;
	}

	public void setTrtraccessrights(Integer trtraccessrights) {
		this.trtraccessrights = trtraccessrights;
	}

	public Integer getUkuaaccessrights() {
		return this.ukuaaccessrights;
	}

	public void setUkuaaccessrights(Integer ukuaaccessrights) {
		this.ukuaaccessrights = ukuaaccessrights;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getZhcnaccessrights() {
		return this.zhcnaccessrights;
	}

	public void setZhcnaccessrights(Integer zhcnaccessrights) {
		this.zhcnaccessrights = zhcnaccessrights;
	}

	public boolean isPersisted() {
		return id != null;
	}

}