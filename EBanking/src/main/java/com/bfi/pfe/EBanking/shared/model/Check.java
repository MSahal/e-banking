package com.bfi.pfe.EBanking.shared.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHECK")
public class Check implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 50, nullable = false, unique = true, name = "ID_CHEQUE")
	private Long id;

	@Column(length = 50, nullable = false, unique = true, name = "NUMBER")
	private String number;

	@Column(length = 50, nullable = false, unique = true, name = "TYPE")
	private String type;

	@Column(length = 50, nullable = false, unique = false, name = "DATE_REMISE")
	private Date dateRemise;

	@Column(length = 50, nullable = false, unique = false, name = "CROSSED")
	private Boolean isCrossed;

	@Column(length = 50, nullable = false, unique = false, name = "AMOUNT")
	private Double amount;

	@ManyToOne
	@JoinColumn(name = "ID_ACCOUNT", nullable = false)
	private Account account;

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
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

}
