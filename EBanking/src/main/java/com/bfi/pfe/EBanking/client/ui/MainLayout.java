package com.bfi.pfe.EBanking.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class MainLayout {
	private Center center;
	private HLayout mainLayout;
	private TransferForm newTransfer;
	private BenificiaryForm newBenificiary;
	private AccountsList accounts;
	private BenificiaryList benificiaryList;
	private TransactionList transactions;
	private TransferList transfers;
	private SimulatorForm simulatorForm;
	private CreditCardList creditCardList;
	private CreditCardRequestList creditCardRequestList;
	private CreateCreditCardRequestForm createCreditCardRequestForm;
	private CheckList checkList;
	private CheckRequestList checkRequestList;
	private CreateCheckRequestForm checkRequestForm;

	public MainLayout(AccountsList accounts, TransactionList transactions,
			TransferForm transferForm, TransferList transfers,
			BenificiaryForm benificiaryForm, BenificiaryList benificiaryList,
			SimulatorForm simulatorForm, CreditCardList creditCardList,
			CreditCardRequestList creditCardRequestList,
			CreateCreditCardRequestForm createCreditCardRequestForm,
			CheckList checkList, CheckRequestList checkRequestList,
			CreateCheckRequestForm checkRequestForm) {
		mainLayout = new HLayout();
		mainLayout.setHeight("84%");
		mainLayout.setWidth(1024);
		mainLayout.setMembersMargin(4);
		this.checkRequestList = checkRequestList;
		this.simulatorForm = simulatorForm;
		this.createCreditCardRequestForm = createCreditCardRequestForm;
		this.accounts = accounts;
		this.transactions = transactions;
		this.center = new Center();
		this.newTransfer = transferForm;
		this.transfers = transfers;
		this.newBenificiary = benificiaryForm;
		this.creditCardList = creditCardList;
		this.benificiaryList = benificiaryList;
		this.creditCardRequestList = creditCardRequestList;
		this.checkList = checkList;
		this.checkRequestForm = checkRequestForm;
	}

	private SectionStack sectionStack;

	public HLayout createForm() {
		return createadminForm();
	}

	public HLayout createadminForm() {

		IButton consultPt = new IButton("Credit card list");
		consultPt.setTitleStyle("sectionTitle");
		consultPt.setWidth("100%");
		consultPt.setHeight("30");
		consultPt.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				creditCardList.createForm();
				center.createForm(creditCardList);
			}
		});

		IButton newCard = new IButton("New credit card request");
		newCard.setTitleStyle("sectionTitle");
		newCard.setWidth("100%");
		newCard.setHeight("30");
		newCard.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				createCreditCardRequestForm.createForm();
				center.createForm(createCreditCardRequestForm);
			}
		});

		IButton creditCardRequestListe = new IButton("Credit card request list");
		creditCardRequestListe.setTitleStyle("sectionTitle");
		creditCardRequestListe.setWidth("100%");
		creditCardRequestListe.setHeight("30");
		creditCardRequestListe.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				creditCardRequestList.createForm();
				center.createForm(creditCardRequestList);

			}
		});
		IButton transactionList = new IButton("Transaction List");
		transactionList.setTitleStyle("sectionTitle");
		transactionList.setWidth("100%");
		transactionList.setHeight("30");
		transactionList.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				transactions.createForm();

				center.createForm(transactions);
			}
		});

		final IButton accountList = new IButton("Account List");
		accountList.setTitleStyle("sectionTitle");
		accountList.setWidth("100%");
		accountList.setHeight("30");
		accountList.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				accounts.createForm();
				center.createForm(accounts);

			}
		});

		IButton bCollaborateur = new IButton("Transfer request");
		bCollaborateur.setTitleStyle("sectionTitle");
		bCollaborateur.setWidth("100%");
		bCollaborateur.setHeight("30");
		bCollaborateur.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				newTransfer.createForm();
				center.createForm(newTransfer);
			}
		});

		IButton bEquipe = new IButton("Transfer list");
		bEquipe.setTitleStyle("sectionTitle");
		bEquipe.setWidth("100%");
		bEquipe.setHeight("30");
		bEquipe.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				transfers.createForm();
				center.createForm(transfers);
			}
		});

		IButton newBenif = new IButton("New Beneficiary");
		newBenif.setTitleStyle("sectionTitle");
		newBenif.setWidth("100%");
		newBenif.setHeight("30");
		newBenif.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				newBenificiary.createForm();
				center.createForm(newBenificiary);
			}
		});

		IButton benifList = new IButton("Beneficiary List");
		benifList.setTitleStyle("sectionTitle");
		benifList.setWidth("100%");
		benifList.setHeight("30");
		benifList.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				benificiaryList.createForm();
				center.createForm(benificiaryList);
			}
		});

		IButton infopersonnel = new IButton("Loan simulation");
		infopersonnel.setWidth("100%");
		infopersonnel.setHeight("30");
		infopersonnel.setTitleStyle("sectionTitle");
		infopersonnel.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				simulatorForm.createForm();
				center.createForm(simulatorForm);

			}
		});

		IButton checkList1 = new IButton("Check list");
		checkList1.setWidth("100%");
		checkList1.setHeight("30");
		checkList1.setTitleStyle("sectionTitle");
		checkList1.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				checkList.createForm();
				center.createForm(checkList);

			}
		});

		IButton checkRequestList1 = new IButton("Check request list");
		checkRequestList1.setWidth("100%");
		checkRequestList1.setHeight("30");
		checkRequestList1.setTitleStyle("sectionTitle");
		checkRequestList1.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				checkRequestList.createForm();
				center.createForm(checkRequestList);

			}
		});

		IButton checkRequestForm1 = new IButton("New check request");
		checkRequestForm1.setWidth("100%");
		checkRequestForm1.setHeight("30");
		checkRequestForm1.setTitleStyle("sectionTitle");
		checkRequestForm1.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				checkRequestForm.createForm();
				center.createForm(checkRequestForm);

			}
		});

		sectionStack = new SectionStack();

		SectionStackSection section0 = new SectionStackSection();
		SectionStackSection section1 = new SectionStackSection();
		SectionStackSection section2 = new SectionStackSection();
		SectionStackSection section3 = new SectionStackSection();
		SectionStackSection section4 = new SectionStackSection();
		SectionStackSection section5 = new SectionStackSection();

		section5.setTitle("Check management");
		section5.setExpanded(false);
		section5.setResizeable(false);
		VLayout bsection5 = new VLayout();
		bsection5.setAutoHeight();
		bsection5.setMembers(checkRequestForm1, checkList1, checkRequestList1);
		section5.setItems(bsection5);

		section1.setTitle("Transfer management");
		section1.setExpanded(false);
		section1.setResizeable(false);
		VLayout bsection1 = new VLayout();
		bsection1.setAutoHeight();
		bsection1.setMembers(bCollaborateur, bEquipe, newBenif, benifList);
		section1.setItems(bsection1);

		section2.setTitle("Credit card management");
		section2.setExpanded(false);
		section2.setResizeable(false);
		VLayout bsection2 = new VLayout();
		bsection2.setAutoHeight();
		bsection2.setMembers(newCard, consultPt, creditCardRequestListe);
		section2.setItems(bsection2);
		section3.setTitle("Account Management");
		section3.setExpanded(false);
		section3.setResizeable(false);
		VLayout bsection3 = new VLayout();
		bsection3.setAutoHeight();
		bsection3.setMembers(transactionList);

		section3.setItems(accountList, bsection3);

		section4.setTitle("Simulation");
		section4.setExpanded(false);
		section4.setResizeable(false);
		VLayout bsection4 = new VLayout();
		bsection4.setAutoHeight();
		bsection4.setMembers(infopersonnel);
		section4.setItems(bsection4);

		DynamicForm form = new DynamicForm();

		VLayout theform = new VLayout();
		theform.setMembers(form);
		theform.setAutoHeight();
		section0.setItems(theform);

		section0.setExpanded(true);

		sectionStack.setSections(section3, section1, section2, section5,
				section4);
		sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sectionStack.setAnimateSections(true);
		sectionStack.setWidth(300);
		sectionStack.setAutoHeight();
		sectionStack.setOverflow(Overflow.HIDDEN);
		sectionStack.setEdgeMarginSize(15);
		sectionStack.setBorder("2px solid white");
		sectionStack.setShowResizeBar(true);

		sectionStack.setHeight("100%");
		sectionStack.setWidth(256);
		mainLayout.setMembers(sectionStack, center);
		mainLayout.setLayoutAlign(Alignment.CENTER);
		return mainLayout;

	}

}
