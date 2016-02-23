package com.bfi.pfe.EBanking.shared.model.dto;

import java.io.Serializable;
import java.util.Date;

public class CreditCardRequestDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dateDemande;
	private String status;
	private String type;
	private AccountDTO account;

	public CreditCardRequestDTO() {
		// TODO Auto-generated constructor stub
	}

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

	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	public CreditCardRequestDTO(Long id, Date dateDemande, String status,
			AccountDTO account, String type) {
		super();
		this.id = id;
		this.dateDemande = dateDemande;
		this.status = status;
		this.account = account;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
