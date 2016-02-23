package com.bfi.pfe.EBanking.shared.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSFER_REQUEST")
public class TransferRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8837917263871603343L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 50, nullable = false, unique = true, name = "ID_VIREMENT")
	private Long id;

	@Column(length = 50, nullable = false, unique = false, name = "STATUS")
	private String status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_ACCOUNT", nullable = false)
	private Account account;

	@ManyToOne
	@JoinColumn(name = "ID_BENIFICIARY", nullable = false)
	private Benificiary benificiary;

	@Column(length = 50, nullable = false, unique = false)
	private Date transferDate;

	@Column(length = 50, nullable = false, unique = false, name = "SOLDE")
	private Double solde;

	public TransferRequest() {
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Benificiary getBenificiary() {
		return benificiary;
	}

	public void setBenificiary(Benificiary benificiary) {
		this.benificiary = benificiary;
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
