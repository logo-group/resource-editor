package com.logo.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "RE_RESOURCEGROUP")
@EntityListeners(AuditingEntityListener.class)
public class ReResourceGroup {

	@Id
	@Column(name = "ID", nullable = false, updatable = false, unique = true)
	private String ID;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "resourcegroup")
	private List<ReResource> resourceList = new ArrayList<ReResource>();

	@OneToMany(mappedBy = "defaultresourcegroup")
	private List<ReUser> userList = new ArrayList<ReUser>();

	@OneToMany(mappedBy = "resgroup")
	private List<ReMessage> messageList = new ArrayList<ReMessage>();

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ReResource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<ReResource> resourceList) {
		this.resourceList = resourceList;
	}

	public List<ReUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ReUser> userList) {
		this.userList = userList;
	}

	public List<ReMessage> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<ReMessage> messageList) {
		this.messageList = messageList;
	}

	@Override
	public String toString() {
		return name;
	}

}
