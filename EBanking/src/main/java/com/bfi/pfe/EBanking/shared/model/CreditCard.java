package com.bfi.pfe.EBanking.shared.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CREDIT_CARD")
public class CreditCard implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2524081155133341440L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 50, nullable = false, unique = true, name = "ID_CARD")
	private Long id;

	@Column(length = 50, nullable = false, unique = true, name = "NUMBER")
	private String number;

	@Column(length = 50, nullable = true, unique = false, name = "STATUS")
	private String status;

	@Column(length = 50, nullable = true, unique = false, name = "TYPE")
	private String type;

	@Column(length = 50, nullable = false, unique = false, name = "DATE_END")
	private Date dateEnd;

	@OneToOne
	@JoinColumn(name = "ID_ACCOUNT", nullable = false)
	private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
