package com.bfi.pfe.EBanking.shared.model.dto;

import java.io.Serializable;
import java.util.Date;

public class CheckRequestDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6973095731952737464L;
	private Long id;
	private String type;
	private Date dateRemise;
	private Double solde;
	private String status;
	private AccountDTO account;
	private String benificiary;

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

	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	public CheckRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public CheckRequestDTO(Long id, String type, Date dateRemise, Double solde,
			String status, AccountDTO account, String benificiary) {
		super();
		this.id = id;
		this.type = type;
		this.dateRemise = dateRemise;
		this.solde = solde;
		this.account = account;
		this.status = status;
		this.setBenificiary(benificiary);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBenificiary() {
		return benificiary;
	}

	public void setBenificiary(String benificiary) {
		this.benificiary = benificiary;
	}

}
