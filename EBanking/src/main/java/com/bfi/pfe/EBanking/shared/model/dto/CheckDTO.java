package com.bfi.pfe.EBanking.shared.model.dto;

import java.io.Serializable;
import java.util.Date;

public class CheckDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2809601994981804403L;
	private Long id;
	private String number;
	private String type;
	private Date dateRemise;
	private Boolean isCrossed;
	private Double amount;
	private AccountDTO account;

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the dateRemise
	 */
	public Date getDateRemise() {
		return dateRemise;
	}

	/**
	 * @param dateRemise
	 *            the dateRemise to set
	 */
	public void setDateRemise(Date dateRemise) {
		this.dateRemise = dateRemise;
	}

	/**
	 * @return the isCrossed
	 */
	public Boolean getIsCrossed() {
		return isCrossed;
	}

	/**
	 * @param isCrossed
	 *            the isCrossed to set
	 */
	public void setIsCrossed(Boolean isCrossed) {
		this.isCrossed = isCrossed;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
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

	public CheckDTO() {
		// TODO Auto-generated constructor stub
	}

	public CheckDTO(Long id, String number, String type, Date dateRemise,
			Boolean isCrossed, Double amount, AccountDTO account) {
		this.id = id;
		this.number = number;
		this.type = type;
		this.dateRemise = dateRemise;
		this.isCrossed = isCrossed;
		this.amount = amount;
		this.account = account;
	}

}
