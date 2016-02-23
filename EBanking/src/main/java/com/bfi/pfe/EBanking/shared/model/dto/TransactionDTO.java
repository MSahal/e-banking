package com.bfi.pfe.EBanking.shared.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class TransactionDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3251065369645660865L;

	private Long id;
	private Date date;
	private Double montant;
	private String ordonnateur;
	private String benificiaire;
	private String type;
	private AccountDTO accountDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getOrdonnateur() {
		return ordonnateur;
	}

	public void setOrdonnateur(String ordonnateur) {
		this.ordonnateur = ordonnateur;
	}

	public String getBenificiaire() {
		return benificiaire;
	}

	public void setBenificiaire(String benificiaire) {
		this.benificiaire = benificiaire;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TransactionDTO(Long id, Date date, Double montant,
			String ordonnateur, String benificiaire, String type,
			AccountDTO account) {
		this.id = id;
		this.date = date;
		this.montant = montant;
		this.ordonnateur = ordonnateur;
		this.benificiaire = benificiaire;
		this.type = type;
		this.accountDto = account;
	}

	public TransactionDTO() {
		// TODO Auto-generated constructor stub
	}

	public AccountDTO getAccountDto() {
		return accountDto;
	}

	public void setAccountDto(AccountDTO accountDto) {
		this.accountDto = accountDto;
	}

}
