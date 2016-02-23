package com.bfi.pfe.EBanking.shared.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bfi.pfe.EBanking.shared.model.dto.TransactionDTO;

@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6603793651037560566L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 50, nullable = false, unique = true, name = "ID_TRANSACTION")
	private Long id;

	@Column(length = 50, nullable = false, unique = true, name = "DATE")
	private Date date;

	@Column(length = 50, nullable = false, unique = false, name = "MONTANT")
	private Double montant;

	@Column(length = 50, nullable = false, unique = false, name = "ORDONNATEUR")
	private String ordonnateur;

	@Column(length = 50, nullable = false, unique = false, name = "BENIFICIAIRE")
	private String benificiaire;

	@Column(length = 50, nullable = false, unique = false, name = "TYPE")
	private String type;

	@ManyToOne
	@JoinColumn(name = "ID_ACCOUNT", nullable = false)
	private Account account;

	public Transaction() {
	}

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

	public Transaction(TransactionDTO transactionDTO) {
		id = transactionDTO.getId();
		date = transactionDTO.getDate();
		montant = transactionDTO.getMontant();
		ordonnateur = transactionDTO.getOrdonnateur();
		benificiaire = transactionDTO.getBenificiaire();
		type = transactionDTO.getType();

	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
