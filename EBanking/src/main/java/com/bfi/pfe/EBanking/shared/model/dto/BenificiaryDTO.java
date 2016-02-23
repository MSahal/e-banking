package com.bfi.pfe.EBanking.shared.model.dto;

import java.io.Serializable;
import java.util.List;

import com.bfi.pfe.EBanking.shared.model.Benificiary;

public class BenificiaryDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2551749376925844208L;
	private Long id;
	private String fullName;
	private String rib;
	private String status;
	private List<TransferRequestDTO> demandeVirement;
	private AccountDTO account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<TransferRequestDTO> getDemandeVirement() {
		return demandeVirement;
	}

	public void setDemandeVirement(List<TransferRequestDTO> demandeVirement) {
		this.demandeVirement = demandeVirement;
	}

	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	public BenificiaryDTO() {
		// TODO Auto-generated constructor stub
	}

	public BenificiaryDTO(Long id, String fullName, String rib, String status,
			List<TransferRequestDTO> demandeVirement, AccountDTO account) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.rib = rib;
		this.status = status;
		this.demandeVirement = demandeVirement;
		this.account = account;
	}

	public BenificiaryDTO(Benificiary benificiary) {

		this.id = benificiary.getId();
		this.fullName = benificiary.getFullName();
		this.rib = benificiary.getRib();
		this.status = benificiary.getStatus();

	}

}
