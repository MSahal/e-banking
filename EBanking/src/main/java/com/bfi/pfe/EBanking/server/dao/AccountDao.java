package com.bfi.pfe.EBanking.server.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bfi.pfe.EBanking.shared.model.Account;
import com.bfi.pfe.EBanking.shared.model.Benificiary;
import com.bfi.pfe.EBanking.shared.model.Check;
import com.bfi.pfe.EBanking.shared.model.CheckRequest;
import com.bfi.pfe.EBanking.shared.model.CreditCard;
import com.bfi.pfe.EBanking.shared.model.CreditCardRequest;
import com.bfi.pfe.EBanking.shared.model.Transaction;
import com.bfi.pfe.EBanking.shared.model.TransferRequest;
import com.bfi.pfe.EBanking.shared.model.dto.AccountDTO;
import com.bfi.pfe.EBanking.shared.model.dto.BenificiaryDTO;
import com.bfi.pfe.EBanking.shared.model.dto.CheckDTO;
import com.bfi.pfe.EBanking.shared.model.dto.CheckRequestDTO;
import com.bfi.pfe.EBanking.shared.model.dto.CreditCardDTO;
import com.bfi.pfe.EBanking.shared.model.dto.CreditCardRequestDTO;
import com.bfi.pfe.EBanking.shared.model.dto.TransactionDTO;
import com.bfi.pfe.EBanking.shared.model.dto.TransferRequestDTO;
import com.bfi.pfe.EBanking.shared.model.dto.UserDTO;

@Repository
@Transactional
public class AccountDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	UserDao userDao;

	protected Session getSession() {

		return sessionFactory.getCurrentSession();
	}

	public List<TransactionDTO> getTransactions(AccountDTO user, String start,
			String end) throws ParseException {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startDate = format.parse(start);
		Date endDate = format.parse(end);

		@SuppressWarnings("unchecked")
		List<Transaction> tansactionList = new ArrayList<Transaction>(
				getSession().createQuery(
						"from Transaction where id_account=" + user.getId())
						.list());

		List<TransactionDTO> tansactionListDto = new ArrayList<TransactionDTO>(
				tansactionList != null ? tansactionList.size() : 0);

		if (tansactionList != null) {
			for (Transaction transaction : tansactionList) {
				if (transaction.getDate() != null
						&& transaction.getDate().after(startDate)
						&& transaction.getDate().before(endDate)) {
					tansactionListDto.add(new TransactionDTO(transaction
							.getId(), transaction.getDate(), transaction
							.getMontant(), transaction.getOrdonnateur(),
							transaction.getBenificiaire(), transaction
									.getType(), null));
				}
			}
		}

		return tansactionListDto;
	}

	public AccountDTO getAccountByRib(String rib) {
		Criteria criteria = getSession().createCriteria(Account.class);
		criteria.add(Restrictions.eq("rib", rib));
		Account accountToReturn = (Account) criteria.uniqueResult();

		AccountDTO accountDto = new AccountDTO();
		accountDto.setSolde(accountToReturn.getSolde());
		accountDto.setId(accountToReturn.getId());
		accountDto.setOpeningDate(accountToReturn.getOpeningDate());
		accountDto.setRib(accountToReturn.getRib());
		accountDto.setType(accountToReturn.getType());

		UserDTO user = userDao.getUserById(accountToReturn.getUser().getId());
		accountDto.setUser(user);
		return accountDto;

	}

	public TransferRequestDTO createTransferRequest(
			TransferRequest transferRequest) {

		getSession().save(transferRequest);
		return null;
	}

	public List<BenificiaryDTO> getAllBenificiaries(Long id) {
		Criteria criteria = getSession().createCriteria(Benificiary.class);
		criteria.setFetchMode("Account", FetchMode.JOIN);
		@SuppressWarnings("unchecked")
		List<Benificiary> benificiariesList = new ArrayList<Benificiary>(
				criteria.list());

		List<BenificiaryDTO> benificiariesListDto = new ArrayList<BenificiaryDTO>(
				benificiariesList != null ? benificiariesList.size() : 0);

		if (benificiariesList != null && !benificiariesList.isEmpty()) {
			for (Benificiary benificiary : benificiariesList) {
				benificiariesListDto.add(new BenificiaryDTO(
						benificiary.getId(), benificiary.getFullName(),
						benificiary.getRib(), benificiary.getStatus(), null,
						new AccountDTO(benificiary.getAccount().getId(),
								benificiary.getAccount().getRib(), benificiary
										.getAccount().getOpeningDate(),
								benificiary.getAccount().getSolde(),
								benificiary.getAccount().getType(), null, null,
								null, null, null)));

			}
		}

		return benificiariesListDto;
	}

	public BenificiaryDTO getBenificiaryByRib(String rib) {
		Criteria criteria = getSession().createCriteria(Benificiary.class);
		criteria.add(Restrictions.eq("rib", rib));
		Benificiary benificiaryToReturn = (Benificiary) criteria.uniqueResult();

		BenificiaryDTO benificiaryDto = new BenificiaryDTO();
		benificiaryDto.setFullName(benificiaryToReturn.getFullName());
		benificiaryDto.setId(benificiaryToReturn.getId());
		benificiaryDto.setStatus(benificiaryToReturn.getStatus());

		return benificiaryDto;
	}

	public List<TransferRequestDTO> getTransferListByAccount(Long accountId) {

		Criteria criteria = getSession().createCriteria(TransferRequest.class);
		criteria.setFetchMode("Account", FetchMode.JOIN);
		@SuppressWarnings("unchecked")
		List<TransferRequest> transferList = new ArrayList<TransferRequest>(
				criteria.list());

		List<TransferRequestDTO> transferListDto = new ArrayList<TransferRequestDTO>(
				transferList != null ? transferList.size() : 0);

		if (transferList != null && !transferList.isEmpty()) {
			for (TransferRequest transfer : transferList) {

				transferListDto.add(new TransferRequestDTO(transfer.getId(),
						transfer.getStatus(), new AccountDTO(transfer
								.getAccount()), new BenificiaryDTO(transfer
								.getBenificiary()), transfer.getSolde(),
						transfer.getTransferDate()));
			}

		}

		return transferListDto;
	}

	public List<BenificiaryDTO> getAllBenificiarie() {
		Criteria criteria = getSession().createCriteria(Benificiary.class);
		@SuppressWarnings("unchecked")
		List<Benificiary> benificiariesList = new ArrayList<Benificiary>(
				criteria.list());

		List<BenificiaryDTO> benificiariesListDto = new ArrayList<BenificiaryDTO>(
				benificiariesList != null ? benificiariesList.size() : 0);

		if (benificiariesList != null && !benificiariesList.isEmpty()) {
			for (Benificiary benificiary : benificiariesList) {
				benificiariesListDto.add(new BenificiaryDTO(
						benificiary.getId(), benificiary.getFullName(),
						benificiary.getRib(), benificiary.getStatus(), null,
						null));

			}
		}

		return benificiariesListDto;
	}

	public List<CreditCardDTO> getListofCreditCardByAccountId(Long accountId) {

		Criteria criteria = getSession().createCriteria(CreditCard.class);
		criteria.setFetchMode("Account", FetchMode.JOIN);
		@SuppressWarnings("unchecked")
		List<CreditCard> creditCardList = new ArrayList<CreditCard>(
				criteria.list());

		List<CreditCardDTO> creditCardListDto = new ArrayList<CreditCardDTO>(
				creditCardList != null ? creditCardList.size() : 0);

		if (creditCardList != null && !creditCardList.isEmpty()) {
			for (CreditCard credit : creditCardList) {
				AccountDTO account = new AccountDTO();
				account.setRib(this.getAccountById(credit.getId()).getRib());
				creditCardListDto.add(new CreditCardDTO(credit.getId(), credit
						.getNumber(), credit.getStatus(), credit.getDateEnd(),
						account, credit.getType()));
			}

		}

		return creditCardListDto;

	}

	public List<CreditCardRequestDTO> getListofCreditCardRequestByAccountId(
			Long accountId) {
		Criteria criteria = getSession()
				.createCriteria(CreditCardRequest.class);
		criteria.setFetchMode("Account", FetchMode.JOIN);
		@SuppressWarnings("unchecked")
		List<CreditCardRequest> creditCardList = new ArrayList<CreditCardRequest>(
				criteria.list());

		List<CreditCardRequestDTO> creditCardListDto = new ArrayList<CreditCardRequestDTO>(
				creditCardList != null ? creditCardList.size() : 0);

		if (creditCardList != null && !creditCardList.isEmpty()) {
			for (CreditCardRequest credit : creditCardList) {
				AccountDTO account = getAccountById(credit.getAccount().getId());
				// account.setRib(this.getAccountById(credit.getId()).getRib());

				creditCardListDto.add(new CreditCardRequestDTO(credit.getId(),
						credit.getDateDemande(), credit.getStatus(), account,
						credit.getType()));
			}

		}

		return creditCardListDto;

	}

	public AccountDTO getAccountById(Long accountId) {
		Criteria criteria = getSession().createCriteria(Account.class);
		criteria.add(Restrictions.eq("id", accountId));
		Account accountToReturn = (Account) criteria.uniqueResult();

		AccountDTO accountDto = new AccountDTO();
		accountDto.setSolde(accountToReturn.getSolde());
		accountDto.setId(accountToReturn.getId());
		accountDto.setOpeningDate(accountToReturn.getOpeningDate());
		accountDto.setRib(accountToReturn.getRib());
		accountDto.setType(accountToReturn.getType());

		UserDTO user = userDao.getUserById(accountToReturn.getUser().getId());
		accountDto.setUser(user);
		return accountDto;
	}

	public CreditCardRequestDTO createCreditCardRequest(
			CreditCardRequest creditCardRequest) {
		// TODO Auto-generated method stub
		getSession().save(creditCardRequest);
		return null;
	}

	public List<CheckDTO> getListofChecksByAccountId(Long accountId) {
		Criteria criteria = getSession().createCriteria(Check.class);
		criteria.setFetchMode("Account", FetchMode.JOIN);
		@SuppressWarnings("unchecked")
		List<Check> creditCardList = new ArrayList<Check>(criteria.list());

		List<CheckDTO> creditCardListDto = new ArrayList<CheckDTO>(
				creditCardList != null ? creditCardList.size() : 0);

		if (creditCardList != null && !creditCardList.isEmpty()) {
			for (Check credit : creditCardList) {
				AccountDTO account = getAccountById(credit.getAccount().getId());

				creditCardListDto.add(new CheckDTO(credit.getId(), credit
						.getNumber(), credit.getType(), credit.getDateRemise(),
						credit.getIsCrossed(), credit.getAmount(), account));
			}

		}

		return creditCardListDto;
	}

	public List<CheckRequestDTO> getListofCheckRequestByAccountId(Long accountId) {
		Criteria criteria = getSession().createCriteria(CheckRequest.class);
		criteria.setFetchMode("Account", FetchMode.JOIN);
		@SuppressWarnings("unchecked")
		List<CheckRequest> creditCardList = new ArrayList<CheckRequest>(
				criteria.list());

		List<CheckRequestDTO> creditCardListDto = new ArrayList<CheckRequestDTO>(
				creditCardList != null ? creditCardList.size() : 0);

		if (creditCardList != null && !creditCardList.isEmpty()) {
			for (CheckRequest credit : creditCardList) {
				AccountDTO account = getAccountById(credit.getAccount().getId());

				creditCardListDto.add(new CheckRequestDTO(credit.getId(),
						credit.getType(), credit.getDateRemise(), credit
								.getSolde(), credit.getStatus(), account,
						credit.getBenificiary()));
			}

		}

		return creditCardListDto;
	}

	public CheckRequestDTO createCheckRequest(CheckRequest checkRequest) {
		getSession().save(checkRequest);
		return null;
	}

}
