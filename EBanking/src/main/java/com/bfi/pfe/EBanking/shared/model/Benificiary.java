package com.bfi.pfe.EBanking.shared.model;

import java.io.Serializable;
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

import com.bfi.pfe.EBanking.shared.model.dto.BenificiaryDTO;

@Entity
@Table(name = "BENIFICIARY")
public class Benificiary implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2586398186595877079L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 50, nullable = false, unique = true, name = "ID_BENIFICIARY")
	private Long id;

	@Column(length = 50, nullable = false, unique = false, name = "FULLNAME")
	private String fullName;

	@Column(length = 50, nullable = false, unique = false, name = "RIB")
	private String rib;

	@Column(length = 50, nullable = false, unique = false, name = "STATUS")
	private String status;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = TransferRequest.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<TransferRequest> demandeVirement;

	@ManyToOne
	@JoinColumn(name = "ID_ACCOUNT", nullable = false)
	private Account account;

	public Benificiary() {
		// TODO Auto-generated constructor stub
	}

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

	public List<TransferRequest> getDemandeVirement() {
		return demandeVirement;
	}

	public void setDemandeVirement(List<TransferRequest> demandeVirement) {
		this.demandeVirement = demandeVirement;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Benificiary(BenificiaryDTO benificiaryDTO) {
		this.id = benificiaryDTO.getId();
		this.rib = benificiaryDTO.getRib();
		this.fullName = benificiaryDTO.getFullName();
		this.status = benificiaryDTO.getStatus();
		this.account = new Account(benificiaryDTO.getAccount());

	}
}
