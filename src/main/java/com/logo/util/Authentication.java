package com.logo.util;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.logo.data.entity.ReUser;
import com.logo.data.repository.ReUserRep;
import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
@Scope("prototype")
public class Authentication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public ReUserRep userRepo;

	private transient List<ReUser> userList = null;

	public Authentication() {
		if ((userList == null) && (userRepo != null)) {
			userList = userRepo.findAll();
		}
	}

	public Boolean authenticate(String username, String password) {
		return validUser(username, password);
	}

	private boolean validUser(String username, String password) {
		boolean find = false;
		if ((userList == null) && (userRepo != null)) {
			userList = userRepo.findAll();
			if (userList == null)
				return false;
		}
		Predicate<ReUser> p1 = e -> e.getUsername().equalsIgnoreCase(username)
				&& e.getPassword().equalsIgnoreCase(password);
		if (userList != null)
			find = userList.stream().anyMatch(p1);

		return find;
	}

	public List<String> getUserList(String username) {
		List<String> intList = null;
		Predicate<ReUser> p1 = e -> e.getUsername().contains(username);
		if ((userList == null) && (userRepo != null)) {
			userList = userRepo.findAll();
			intList = userList.stream().filter(p1).map(elem -> elem.getUsername()).collect(Collectors.toList());
		}
		if (userList != null)
			intList = userList.stream().filter(p1).map(elem -> elem.getUsername()).collect(Collectors.toList());
		return intList;
	}

	public ReUser getReUser(String username) {
		return userList.stream().filter(x -> x.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);
	}
}
