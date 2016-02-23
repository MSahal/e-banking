package com.bfi.pfe.EBanking.client.ui;

import com.bfi.pfe.EBanking.client.service.EbankingService;
import com.bfi.pfe.EBanking.client.service.EbankingServiceAsync;
import com.bfi.pfe.EBanking.shared.model.dto.UserDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class Top {

	private final EbankingServiceAsync ebankingService = GWT
			.create(EbankingService.class);

	public HLayout createForm(final UserDTO user) {
		HLayout topLayout = new HLayout();
		Image img = new Image("icons/bfi.gif");
		// img.setWidth("191px");
		// img.setHeight("63px");
		VLayout image = new VLayout();
		VLayout i = new VLayout();
		i.addMember(img);
		i.setWidth(191);
		i.setLayoutAlign(Alignment.CENTER);

		image.setMembers(i);

		image.setWidth(258);
		image.setHeight(63);
		image.setStyleName("top");
		// Image img2 = new
		// Image("resourcesLinedata/LinedataCollaborateur.jpg");

		// img2.setWidth("238px");
		// img2.setHeight("50px");
		VLayout image2 = new VLayout();
		// image2.addMember(img2);
		image2.setWidth(238);
		image2.setHeight(50);

		image.setLayoutAlign(Alignment.CENTER);
		image2.setLayoutAlign(Alignment.CENTER);
		final Label nom = new Label();
		nom.setLayoutAlign(Alignment.RIGHT);

		ebankingService.getTime(new AsyncCallback<String>() {

			public void onSuccess(String result) {
				nom.setContents("<font size=1><nobr><b><center>"
						+ user.getFullName() + " "
						+ "</center></b></nobr><nobr><center>" + result
						+ "</center></nobr></font>");

			}

			public void onFailure(Throwable caught) {
				nom.setContents("<font size=1><nobr><b><center>"
						+ user.getFullName()
						+ " "
						+ "</center></b></nobr><nobr><center></center></nobr></font>");

			}
		});

		nom.setAutoWidth();
		nom.setAutoHeight();
		Button logout = new Button("Logout");
		logout.setLayoutAlign(Alignment.RIGHT);
		logout.setStyleName("newLook");
		logout.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				Window.Location.reload();

			}
		});
		HLayout info = new HLayout();
		info.setAutoWidth();
		System.out.print("*********" + nom.getContents());
		info.setMembers(nom, logout);
		info.setMembersMargin(10);
		info.setLayoutAlign(Alignment.RIGHT);
		VLayout infos = new VLayout();
		infos.setAutoHeight();
		infos.setMembers(info);
		infos.setLayoutAlign(Alignment.CENTER);
		topLayout.setMembers(image, image2, infos);
		topLayout.setWidth(481);
		topLayout.setHeight(63);

		return topLayout;
	}

}
