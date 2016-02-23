package com.bfi.pfe.EBanking.shared.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.bfi.pfe.EBanking.shared.model.Account;

public class AccountDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6398606185240149306L;

	private Long id;
	private String rib;
	private Date openingDate;
	private Double solde;
	private String type;
	private List<TransactionDTO> transactions;
	private List<BenificiaryDTO> benificiaries;
	private UserDTO user;
	private List<CreditCardRequestDTO> demandesCarte;
	private List<CheckRequestDTO> demandesCheque;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AccountDTO() {
		// TODO Auto-generated constructor stub
	}

	public List<TransactionDTO> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionDTO> transactions) {
		this.transactions = transactions;
	}

	public AccountDTO(Long id, String rib, Date openingDate, Double solde,
			String type, List<TransactionDTO> transactions,
			List<BenificiaryDTO> benificiaries, UserDTO user,
			List<CreditCardRequestDTO> demandesCarte,
			List<CheckRequestDTO> demandesCheque) {
		super();
		this.id = id;
		this.rib = rib;
		this.openingDate = openingDate;
		this.solde = solde;
		this.type = type;
		this.transactions = transactions;
		this.benificiaries = benificiaries;
		this.user = user;
		this.demandesCarte = demandesCarte;
		this.demandesCheque = demandesCheque;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<BenificiaryDTO> getBenificiaries() {
		return benificiaries;
	}

	public void setBenificiaries(List<BenificiaryDTO> benificiaries) {
		this.benificiaries = benificiaries;
	}

	public List<CreditCardRequestDTO> getDemandesCarte() {
		return demandesCarte;
	}

	public void setDemandesCarte(List<CreditCardRequestDTO> demandesCarte) {
		this.demandesCarte = demandesCarte;
	}

	public List<CheckRequestDTO> getDemandesCheque() {
		return demandesCheque;
	}

	public void setDemandesCheque(List<CheckRequestDTO> demandesCheque) {
		this.demandesCheque = demandesCheque;
	}

	public AccountDTO(Account account) {

		id = account.getId();
		rib = account.getRib();
		openingDate = account.getOpeningDate();
		solde = account.getSolde();
		type = account.getType();

	}

}
