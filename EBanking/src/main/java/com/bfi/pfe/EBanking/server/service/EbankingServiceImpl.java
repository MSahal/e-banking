package com.bfi.pfe.EBanking.server.service;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfi.pfe.EBanking.client.service.EbankingService;
import com.bfi.pfe.EBanking.server.dao.AccountDao;
import com.bfi.pfe.EBanking.server.dao.UserDao;
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
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service("ebankingService")
public class EbankingServiceImpl extends RemoteServiceServlet implements
		EbankingService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private UserDao dao;

	@Autowired
	private AccountDao accountDao;

	@Override
	@Transactional(readOnly = true)
	public UserDTO authenticate(UserDTO user) {
		return dao.get(user);
	}

	@Override
	public String getTime() {
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		return dateformat.format(new Date());
	}

	@Override
	public List<AccountDTO> getAccounts(UserDTO user) {
		return dao.getAccounts(user);
	}

	@Override
	public AccountDTO getAccountByRib(String rib) {
		return accountDao.getAccountByRib(rib);
	}

	@Override
	public List<TransactionDTO> getTransactions(AccountDTO account,
			String start, String end) throws Exception {
		return accountDao.getTransactions(account, start, end);
	}

	@Override
	public void createTransferRequest(TransferRequest transferRequest) {
		accountDao.createTransferRequest(transferRequest);
	}

	@Override
	public List<BenificiaryDTO> getAllBenificiariesByAccount(Long id) {

		return accountDao.getAllBenificiaries(id);

	}

	@Override
	public List<BenificiaryDTO> getAllBenificiaries() {

		return accountDao.getAllBenificiarie();

	}

	@Override
	public BenificiaryDTO getBenificiaryByRib(String rib) {
		return accountDao.getBenificiaryByRib(rib);
	}

	@Override
	public List<TransferRequestDTO> getTransferListByAccount(Long accountId) {
		return accountDao.getTransferListByAccount(accountId);
	}

	@Override
	public void createTransferRequest(Benificiary benificiary) {
		dao.createBenificiary(benificiary);
	}

	@Override
	public List<CreditCardDTO> getListofCreditCardByAccountId(Long accountId) {
		return accountDao.getListofCreditCardByAccountId(accountId);
	}

	@Override
	public List<CreditCardRequestDTO> getListofCreditCardRequestByAccountId(
			Long accountId) {
		return accountDao.getListofCreditCardRequestByAccountId(accountId);
	}

	@Override
	public AccountDTO getAccountById(Long accountId) {
		// TODO Auto-generated method stub
		return accountDao.getAccountById(accountId);
	}

	@Override
	public void createCreditCardRequest(CreditCardRequest creditCardRequest) {
		accountDao.createCreditCardRequest(creditCardRequest);
	}

	@Override
	public List<CheckDTO> getListofChecksByAccountId(Long accountId) {
		// TODO Auto-generated method stub
		return accountDao.getListofChecksByAccountId(accountId);
	}

	@Override
	public List<CheckRequestDTO> getListofCheckRequestByAccountId(Long accountId) {
		// TODO Auto-generated method stub
		return accountDao.getListofCheckRequestByAccountId(accountId);
	}

	@Override
	public void createCheckRequest(CheckRequest checkRequest) {
		// TODO Auto-generated method stub
		accountDao.createCheckRequest(checkRequest);
	}

	@Override
	public void printRapport(UserDTO user, String dateD, String dateF,
			String account, List<TransactionDTO> transactions) {
		// TODO Auto-generated method stub
		Document document = new Document(PageSize.A4, 0, 0, 0, 0);
		String filename = "C:/Users/lolita/Desktop/Releve.pdf";

		try {
			PdfWriter.getInstance(document, new FileOutputStream(filename));
			document.open();
			Image image = Image
					.getInstance("C:/Users/lolita/Desktop/bfi.gif");
			image = Image
					.getInstance("C:/Users/lolita/Desktop/bfi.gif");
			image.setAlignment(Element.ALIGN_LEFT);
			PdfPTable table = new PdfPTable(4);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			// PdfPCell cell = new PdfPCell(new Paragraph(new Chunk(
			// String.valueOf(transactions.size()), new Font(
			// BaseFont.createFont(), 14, 0))));
			// cell.setColspan(6);
			// cell.setHorizontalAlignment(Element.ALIGN_CENTER);

			// table.addCell(cell);
			table.addCell("Date");
			table.addCell("Balance");
			table.addCell("Benificiary");
			table.addCell("Type");

			for (TransactionDTO transaction : transactions) {
				table.addCell(String.valueOf(transaction.getDate()));
				table.addCell(String.valueOf(transaction.getMontant()));
				table.addCell(transaction.getBenificiaire());
				table.addCell(transaction.getType());

				// table.addCell(new Phrase(new Chunk("7h30", new
				// Font(BaseFont.createFont(), 12, 0, BaseColor.RED))));
			}

			Paragraph title = new Paragraph();
			title.add(new Chunk("Transaction list from " + dateD + " to "
					+ dateF, new Font(FontFamily.TIMES_ROMAN, 18, 1,
					new BaseColor(118, 103, 80))));
			title.setAlignment(Element.ALIGN_CENTER);
			title.setSpacingAfter(30);
			title.setSpacingBefore(20);
			document.add(image);
			document.add(title);
			document.add(table);
			document.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
