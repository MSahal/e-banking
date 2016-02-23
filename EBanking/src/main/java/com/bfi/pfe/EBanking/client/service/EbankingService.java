package com.bfi.pfe.EBanking.client.service;

import java.util.List;

import com.bfi.pfe.EBanking.shared.model.Benificiary;
import com.bfi.pfe.EBanking.shared.model.CheckRequest;
import com.bfi.pfe.EBanking.shared.model.CreditCardRequest;
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
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/ebankingService")
public interface EbankingService extends RemoteService {

	/**
	 *
	 * @param user
	 * @return
	 */
	public UserDTO authenticate(UserDTO user);

	/**
	 *
	 * @return
	 */
	public String getTime();

	/**
	 *
	 * @param user
	 * @return
	 */
	public List<AccountDTO> getAccounts(UserDTO user);

	/**
	 *
	 * @param user
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */

	public List<TransactionDTO> getTransactions(AccountDTO user,
			String startDate, String endDate) throws Exception;

	/**
	 *
	 * @param rib
	 * @return
	 */
	public AccountDTO getAccountByRib(String rib);

	/**
	 *
	 * @param accountId
	 * @return
	 */
	public AccountDTO getAccountById(Long accountId);

	/**
	 *
	 * @param transferRequestDTO
	 * @return
	 */
	public void createTransferRequest(TransferRequest transferRequest);

	/**
	 *
	 * @return
	 */
	public List<BenificiaryDTO> getAllBenificiariesByAccount(Long accountId);

	/**
	 *
	 * @return
	 */
	public List<BenificiaryDTO> getAllBenificiaries();

	/**
	 *
	 * @param rib
	 * @return
	 */
	public BenificiaryDTO getBenificiaryByRib(String rib);

	/**
	 *
	 * @param accountId
	 * @return
	 */
	public List<TransferRequestDTO> getTransferListByAccount(Long accountId);

	/**
	 *
	 * @param benificiary
	 * @return
	 */
	public void createTransferRequest(Benificiary benificiary);

	/**
	 *
	 * @param accountId
	 * @return
	 */

	public List<CreditCardDTO> getListofCreditCardByAccountId(Long accountId);

	/**
	 *
	 * @param accountId
	 * @return
	 */
	public List<CreditCardRequestDTO> getListofCreditCardRequestByAccountId(
			Long accountId);

	/**
	 *
	 * @param creditCardRequest
	 * @return
	 */
	public void createCreditCardRequest(CreditCardRequest creditCardRequest);

	/**
	 *
	 * @param accountId
	 * @return
	 */
	public List<CheckDTO> getListofChecksByAccountId(Long accountId);

	/**
	 *
	 * @param accountId
	 * @return
	 */
	public List<CheckRequestDTO> getListofCheckRequestByAccountId(Long accountId);

	/**
	 *
	 * @param checkRequest
	 * @return
	 */
	public void createCheckRequest(CheckRequest checkRequest);

	/**
	 *
	 * @param user
	 * @param dateD
	 * @param dateF
	 * @param account
	 * @param transactionListGrid
	 */

	public void printRapport(UserDTO user, String dateD, String dateF,
			String account, List<TransactionDTO> transactions);

}
