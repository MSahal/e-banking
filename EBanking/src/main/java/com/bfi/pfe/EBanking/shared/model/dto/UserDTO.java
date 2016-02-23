package com.bfi.pfe.EBanking.shared.model.dto;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5415168746016669120L;

	private Long id;

	private String login;
	private String password;
	private String fullName;
	private List<AccountDTO> accounts;

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

	public List<AccountDTO> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDTO> accounts) {
		this.accounts = accounts;
	}

	public UserDTO(Long id, String login, String password, String fullName,
			List<AccountDTO> accounts) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.fullName = fullName;
		this.accounts = accounts;
	}

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

}
