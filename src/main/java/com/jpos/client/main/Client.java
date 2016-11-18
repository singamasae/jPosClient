package com.jpos.client.main;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.q2.Q2;
import org.jpos.util.NameRegistrar;

import com.jpos.client.main.iso.ChannelManager;

public class Client {
	private static final String ECHO_TEST = "301";
	private static final String NETWORK_REQUEST = "0800";
	public static final Integer RESPONSE_CODE = 39;

	private static ISOMsg createNetworkRequest() {
		ISOMsg inqRequest = new ISOMsg();
		DateTime dateNow = new DateTime();

		String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
		DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");

		try {
			inqRequest.setMTI(NETWORK_REQUEST);
			inqRequest.set(7, formatterBit7.print(dateNow));
			inqRequest.set(11, stan);
			inqRequest.set(70, ECHO_TEST);
			return inqRequest;
		} catch (Exception e) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Q2 client = new Q2();
		client.start();

		// wait until connection established
		ISOUtil.sleep(3000);

		ISOMsg inquiryRequest = createNetworkRequest();

		ChannelManager channelManager = (ChannelManager) NameRegistrar.get("jpos-client-manager");

		ISOMsg response = channelManager.sendRequest(inquiryRequest);
		if (response == null) {
			System.out.println("Server is not responding");
		} else {
			System.out.println("Response : " + new String(response.pack()));
		}

		// simple sending iso message using qmux without Channel Manager
		// remove 30_client_channel_manager.xml
		/*
		 * MUX mux = (MUX) NameRegistrar.get ("mux.jpos-client-mux"); ISOMsg
		 * response = mux.request(inquiryRequest, 30000);
		 * System.out.println("Response : "+new String(response.pack()));
		 */

	}

}
