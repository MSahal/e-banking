package com.bfi.pfe.EBanking.shared.model.dto;

import java.io.Serializable;
import java.util.Date;

public class CreditCardDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1295411379944073492L;
	private Long id;
	private String number;
	private String status;
	private String type;
	private Date dateEnd;
	private AccountDTO account;

	public CreditCardDTO() {
		// TODO Auto-generated constructor stub
	}

	public CreditCardDTO(Long id, String number, String status, Date dateEnd,
			AccountDTO account, String type) {
		super();
		this.id = id;
		this.number = number;
		this.status = status;
		this.dateEnd = dateEnd;
		this.account = account;
		this.type = type;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the dateEnd
	 */
	public Date getDateEnd() {
		return dateEnd;
	}

	/**
	 * @param dateEnd
	 *            the dateEnd to set
	 */
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * @return the account
	 */
	public AccountDTO getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
