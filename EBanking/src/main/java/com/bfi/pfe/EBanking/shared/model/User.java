package com.bfi.pfe.EBanking.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bfi.pfe.EBanking.shared.model.dto.AccountDTO;
import com.bfi.pfe.EBanking.shared.model.dto.UserDTO;

@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = -1131052238165485492L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 50, nullable = false, unique = true, name = "ID_USER")
	private Long id;

	@Column(length = 50, nullable = false, unique = true, name = "LOGIN")
	private String login;

	@Column(length = 50, nullable = false, unique = false, name = "PASSWORD")
	private String password;

	@Column(length = 50, nullable = false, unique = false, name = "FULLNAME")
	private String fullName;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Account.class)
	private List<Account> accounts;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public User(UserDTO userDto) {
		id = userDto.getId();
		fullName = userDto.getFullName();
		login = userDto.getLogin();
		password = userDto.getPassword();
		List<AccountDTO> accountsDTOs = userDto.getAccounts();
		if (accountsDTOs != null) {
			List<Account> accounts = new ArrayList<Account>();
			for (AccountDTO accountsDTO : accountsDTOs) {
				accounts.add(new Account(accountsDTO));
			}
		}

	}

}
