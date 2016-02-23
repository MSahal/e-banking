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
@Table(name = "CHECK_REQUEST")
public class CheckRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5336863282400903767L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 50, nullable = false, unique = true, name = "ID_CHEQUE")
	private Long id;

	@Column(length = 50, nullable = false, unique = false, name = "TYPE")
	private String type;

	@Column(length = 50, nullable = false, unique = false, name = "BENIFICIARY")
	private String benificiary;

	@Column(length = 50, nullable = false, unique = false, name = "STATUS")
	private String status;

	@Column(length = 50, nullable = false, unique = false, name = "DATE_REMISE")
	private Date dateRemise;

	@Column(length = 50, nullable = false, unique = false, name = "SOLDE")
	private Double solde;

	@ManyToOne
	@JoinColumn(name = "ID_ACCOUNT", nullable = false)
	private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDateRemise() {
		return dateRemise;
	}

	public void setDateRemise(Date dateRemise) {
		this.dateRemise = dateRemise;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public Account getAccount() {
		return account;
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

	public void setAccount(Account account) {
		this.account = account;
	}

	public CheckRequest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the benificiary
	 */
	public String getBenificiary() {
		return benificiary;
	}

	/**
	 * @param benificiary
	 *            the benificiary to set
	 */
	public void setBenificiary(String benificiary) {
		this.benificiary = benificiary;
	}

}
