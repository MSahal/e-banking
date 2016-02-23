package com.bfi.pfe.EBanking.shared.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.bfi.pfe.EBanking.shared.model.dto.AccountDTO;
import com.bfi.pfe.EBanking.shared.model.dto.TransactionDTO;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

	private static final long serialVersionUID = 8441845742598751399L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 50, nullable = false, unique = true, name = "ID_ACCOUNT")
	private Long id;

	@Column(length = 50, nullable = false, unique = true, name = "RIB")
	private String rib;

	@Column(length = 50, nullable = false, unique = true, name = "OPENINGDATE")
	private Date openingDate;

	@Column(length = 50, nullable = false, unique = false, name = "SOLDE")
	private Double solde;

	@Column(length = 50, nullable = false, unique = false, name = "TYPE")
	private String type;

	@ManyToOne
	@JoinColumn(name = "ID_USER", nullable = false)
	private User user;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Transaction.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Transaction> transactions;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Benificiary.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Benificiary> benificiarys;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = CreditCardRequest.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CreditCardRequest> demandesCarte;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = CheckRequest.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CheckRequest> demandesCheque;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = TransferRequest.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<TransferRequest> transfers;

	public List<CreditCardRequest> getDemandesCarte() {
		return demandesCarte;
	}

	public void setDemandesCarte(List<CreditCardRequest> demandesCarte) {
		this.demandesCarte = demandesCarte;
	}

	public Account() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Account(AccountDTO accountDTO) {
		id = accountDTO.getId();
		rib = accountDTO.getRib();
		solde = accountDTO.getSolde();
		type = accountDTO.getType();
		openingDate = accountDTO.getOpeningDate();
		List<TransactionDTO> transactionsDTOs = accountDTO.getTransactions();
		if (transactionsDTOs != null) {
			List<Transaction> transactions = new ArrayList<Transaction>();
			for (TransactionDTO accountsDTO : transactionsDTOs) {
				transactions.add(new Transaction(accountsDTO));
			}
		}
		user = new User(accountDTO.getUser());

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Benificiary> getBenificiarys() {
		return benificiarys;
	}

	public void setBenificiarys(List<Benificiary> benificiarys) {
		this.benificiarys = benificiarys;
	}

	public List<CheckRequest> getDemandesCheque() {
		return demandesCheque;
	}

	public void setDemandesCheque(List<CheckRequest> demandesCheque) {
		this.demandesCheque = demandesCheque;
	}

	public List<TransferRequest> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<TransferRequest> transfers) {
		this.transfers = transfers;
	}
}
