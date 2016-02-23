package com.bfi.pfe.EBanking.client.ui;

import com.bfi.pfe.EBanking.client.service.EbankingService;
import com.bfi.pfe.EBanking.client.service.EbankingServiceAsync;
import com.bfi.pfe.EBanking.shared.model.dto.UserDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class AuthenticationScreen {

	private final EbankingServiceAsync ebankingService = GWT
			.create(EbankingService.class);
	private Window winModal = new Window();

	private IButton save = null;

	private DynamicForm form = null;

	public AuthenticationScreen() {
		super();
	}

	public Window createForm() {
		form = new DynamicForm();
		form.setWidth(500);
		form.setHeight(500);

		save = new IButton("Sign in");
		save.setLayoutAlign(Alignment.RIGHT);

		TextItem login = new TextItem("login", "Login");
		login.setRequired(true);

		PasswordItem pass = new PasswordItem("password",
				"<nobr>Password</nobr>");
		pass.setRequired(true);
		pass.setType("password");

		login.setWidth(140);
		pass.setWidth(140);

		form.setGroupTitle("e-Banking");
		form.setFields(login, pass);
		form.setLayoutAlign(Alignment.CENTER);
		form.setAutoHeight();
		form.setWidth(140);
		form.setTitleAlign(Alignment.LEFT);
		form.setLayoutAlign(Alignment.LEFT);
		save.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

			@Override
			public void onClick(
					com.smartgwt.client.widgets.events.ClickEvent event) {
				save.setDisabled(true);

				final UserDTO user = new UserDTO();
				user.setLogin(form.getValueAsString("login"));
				user.setPassword(form.getValueAsString("password"));

				ebankingService.authenticate(user,
						new AsyncCallback<UserDTO>() {

							public void onFailure(Throwable caught) {
								SC.say("an error occurred while processing your request");
								form.reset();
								save.setDisabled(false);
							}

							public void onSuccess(UserDTO result) {

								if (result != null && result.getId() != null) {

									winModal.hide();

									AccountsList accountsList = new AccountsList(
											result);
									Center center = new Center();
									accountsList.createForm();
									center.createForm(accountsList);

									BenificiaryForm benificiaryForm = new BenificiaryForm(
											result);
									TransferForm transferForm = new TransferForm(
											result);

									TransactionList profils = new TransactionList(
											result);
									CreateCreditCardRequestForm createCreditCardRequestForm = new CreateCreditCardRequestForm(
											result);
									TransferList transfers = new TransferList(
											result);
									BenificiaryList benificiaryList = new BenificiaryList(
											result);
									CreditCardList creditCardList = new CreditCardList(
											result);
									CreditCardRequestList creditCardRequestList = new CreditCardRequestList(
											result);

									CheckList checkList = new CheckList(result);
									CheckRequestList CheckRequestList = new CheckRequestList(
											result);
									CreateCheckRequestForm checkRequestForm = new CreateCheckRequestForm(
											result);

									SimulatorForm simulatorForm = new SimulatorForm();
									MainLayout menu = new MainLayout(
											accountsList, profils,
											transferForm, transfers,
											benificiaryForm, benificiaryList,
											simulatorForm, creditCardList,
											creditCardRequestList,
											createCreditCardRequestForm,
											checkList, CheckRequestList,
											checkRequestForm);

									VLayout cover = new VLayout();
									Top topLabel = new Top();
									HLayout topLayout = topLabel
											.createForm(result);
									topLayout.setWidth(1024);
									topLayout.setLayoutAlign(Alignment.CENTER);
									topLayout.setHeight(40);

									Label text = new Label(
											"<nobr>Copy@right BFI</nobr>");
									text.setAutoWidth();
									text.setLayoutAlign(Alignment.CENTER);

									text.setHeight("5%");
									topLayout.setHeight("10%");

									cover.setMembers(topLayout,
											menu.createForm(), text);
									cover.setHeight(com.google.gwt.user.client.Window
											.getClientHeight() - 10);
									cover.setWidth(1024);

									// cover.setBackgroundImage("[SKIN]/back.jpg");
									cover.setStyleName("center");
									cover.setLayoutAlign(Alignment.CENTER);
									VLayout page = new VLayout();
									page.setMembers(cover);
									page.setWidth100();
									page.setHeight100();
									page.setStyleName("backgroundStyle");
									RootLayoutPanel.get().add(page);

								}

								else {
									SC.say("an error occurred while processing your request : Please verify your login and password");
									form.reset();
									save.setDisabled(false);
								}

							}
						});
			}
		});

		winModal.setWidth(280);
		winModal.setHeight(250);
		winModal.setTitle("e-Banking");
		winModal.setShowMinimizeButton(false);
		// winModal.setShowCloseButton(false);
		// winModal.setIsModal(true);
		winModal.centerInPage();
		// winModal.setCanDrag(false);
		winModal.setCanDragReposition(false);
		VLayout layout = new VLayout();
		Image img = new Image("icons/bfi.gif");
		// img.setWidth("212px");
		// img.setHeight("30px");

		VLayout i = new VLayout();

		i.addMember(img);
		i.setWidth(191);
		i.setLayoutAlign(Alignment.CENTER);
		VLayout imag = new VLayout();
		imag.setMembers(i);
		imag.setHeight(63);
		imag.setLayoutAlign(Alignment.CENTER);
		HLayout image = new HLayout();
		image.setMembers(imag);
		layout.setMembers(image, form, save);
		layout.setMembersMargin(5);
		layout.setWidth(140);
		layout.setHeight(190);
		layout.setLayoutAlign(Alignment.CENTER);
		winModal.addItem(layout);
		winModal.setShowCloseButton(false);
		return winModal;
	}

}
