package com.bfi.pfe.EBanking.shared.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CREDIT_CARD_REQUEST")
public class CreditCardRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4581246486682196754L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 50, nullable = false, unique = true, name = "ID_VIREMENT")
	private Long id;

	@Column(length = 50, nullable = false, unique = false, name = "DATE_DEMANDE")
	private Date dateDemande;

	@Column(length = 50, nullable = false, unique = false, name = "STATUS")
	private String status;

	@Column(length = 50, nullable = false, unique = false, name = "TYPE")
	private String type;

	@OneToOne
	@JoinColumn(name = "ID_ACCOUNT", nullable = false)
	private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public CreditCardRequest() {
		// TODO Auto-generated constructor stub
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

}
