package com.bfi.pfe.EBanking.shared.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class TransferRequestDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7041625404545452976L;
	private Long id;
	private String status;
	private AccountDTO account;
	private BenificiaryDTO benificiary;
	private Date transferDate;
	private Double solde;

	public TransferRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BenificiaryDTO getBenificiary() {
		return benificiary;
	}

	public void setBenificiary(BenificiaryDTO benificiary) {
		this.benificiary = benificiary;
	}

	public TransferRequestDTO(Long id, String status, AccountDTO account,
			BenificiaryDTO benificiary, Double solde, Date transferDate) {
		super();
		this.id = id;
		this.status = status;
		this.account = account;
		this.benificiary = benificiary;
		this.solde = solde;
		this.transferDate = transferDate;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

}
