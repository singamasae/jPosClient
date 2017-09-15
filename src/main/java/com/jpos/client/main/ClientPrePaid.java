package com.jpos.client.main;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.q2.Q2;
import org.jpos.util.NameRegistrar;

import com.jpos.client.main.iso.ChannelManager;

public class ClientPrePaid {
	private static final String ECHO_TEST = "301";
	private final static String SIGN_ON = "001";
	private final static String SIGN_OFF = "002";
	private static final String NETWORK_REQUEST = "0800";
	public static final String INQUIRY_REQUEST = "0200";
	public static final String REVERSAL_REQUEST = "0400";
	public static final String INQUIRY_PROCESSING_CODE = "380000";
	public static final String PAYMENT_PROCESSING_CODE = "180000";
	public static final String MERCHANT_TYPE = "6021"; // bit 18
	public static final String BANK_ANDARA = "000730";// bit 32
	public static final String PRODUCT_PREPAID_PLN = "2111";
	public static final String CURRENCY_CODE = "360"; // bit 49
	public static final String BILLING_PROVIDER_CODE = "214"; // bit 63
	private static final String CA_INFO_DATA = "0ADR21";

	private static ISOMsg createNetworkRequest() {
		ISOMsg inqRequest = new ISOMsg();
		DateTime dateNow = new DateTime(DateTimeZone.UTC);

		String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
		DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");

		try {
			inqRequest.setMTI(NETWORK_REQUEST);
			inqRequest.set(7, formatterBit7.print(dateNow));
			inqRequest.set(11, stan);
			inqRequest.set(70, ECHO_TEST);
			return inqRequest;
		} catch (Exception e) {
			Logger.getLogger(ClientPrePaid.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

	private static ISOMsg createSingOn() {
		ISOMsg inqRequest = new ISOMsg();
		DateTime dateNow = new DateTime(DateTimeZone.UTC);

		String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
		DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");

		try {
			inqRequest.setMTI(NETWORK_REQUEST);
			inqRequest.set(7, formatterBit7.print(dateNow));
			inqRequest.set(11, stan);
			inqRequest.set(70, SIGN_ON);
			return inqRequest;
		} catch (Exception e) {
			Logger.getLogger(ClientPrePaid.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

	public static ISOMsg createSingOff() {
		ISOMsg inqRequest = new ISOMsg();
		DateTime dateNow = new DateTime(DateTimeZone.UTC);

		String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
		DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");

		try {
			inqRequest.setMTI(NETWORK_REQUEST);
			inqRequest.set(7, formatterBit7.print(dateNow));
			inqRequest.set(11, stan);
			inqRequest.set(70, SIGN_OFF);
			return inqRequest;
		} catch (Exception e) {
			Logger.getLogger(ClientPrePaid.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

	private static ISOMsg createPrePaidInquiryRequest() {
		ISOMsg inqRequest = new ISOMsg();
		DateTime dateNow = new DateTime();
		DateTime dateNowGMT = new DateTime(DateTimeZone.UTC);
		DateTime setelmentDate = dateNow.plusDays(1);
		DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");
		DateTimeFormatter formatterBit12 = DateTimeFormat.forPattern("hhmmss");
		DateTimeFormatter formatterBit13 = DateTimeFormat.forPattern("MMdd");

		String pan = StringUtils.leftPad(BANK_ANDARA + formatterBit7.print(dateNowGMT), 16, "0"); 
		String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
		String idPel = "15278290438";//"69363628156";
		String cardAcceptorId = StringUtils.rightPad("02906123", 15, " ");
		String denom = "50000";

		try {
			inqRequest.setMTI(INQUIRY_REQUEST);
			inqRequest.set(2, pan);
			inqRequest.set(3, INQUIRY_PROCESSING_CODE);
			inqRequest.set(4, StringUtils.leftPad(denom, 12, "0")); // denom
			inqRequest.set(7, formatterBit7.print(dateNowGMT));
			inqRequest.set(11, stan);
			inqRequest.set(12, formatterBit12.print(dateNow));
			inqRequest.set(13, formatterBit13.print(dateNow));
			inqRequest.set(14, formatterBit13.print(dateNow));
			inqRequest.set(15, formatterBit13.print(setelmentDate));
			inqRequest.set(18, MERCHANT_TYPE);
			inqRequest.set(32, StringUtils.leftPad(BANK_ANDARA, 6, "0")); 		
			inqRequest.set(37, stan + idPel.substring(idPel.length() - 6, idPel.length()));
			inqRequest.set(42, cardAcceptorId);

			// cek ke dokumen spesifikasi
			StringBuilder sb = new StringBuilder();
			sb.append(PRODUCT_PREPAID_PLN);// produk indikator
			if (idPel.length() > 11) {
				sb.append(StringUtils.rightPad(" ", 11, " "));// nomor meter
				sb.append(StringUtils.rightPad(idPel, 13, " "));// identitas pelanggan
				sb.append("1");
			} else {
				sb.append(StringUtils.rightPad(idPel, 11, " "));// nomor meter
				sb.append(StringUtils.rightPad(" ", 13, " "));// identitas pelanggan
				sb.append("0");
			}

			inqRequest.set(48, sb.toString());
			inqRequest.set(49, CURRENCY_CODE);
			inqRequest.set(63, BILLING_PROVIDER_CODE);
			return inqRequest;
		} catch (Exception ex) {
			Logger.getLogger(ClientPrePaid.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public static ISOMsg createPrepaidPurchaseRequest(ISOMsg inqResponse, int unSoldToken) {
		ISOMsg paymentRequest = (ISOMsg) inqResponse.clone();
		DateTime dateNow = new DateTime();
		DateTime dateNowGMT = new DateTime(DateTimeZone.UTC);
		DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");
		DateTimeFormatter formatterBit12 = DateTimeFormat.forPattern("hhmmss");
		
		String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
		String idPel = "15278290438";//"69363628156";		
		
		try {
			paymentRequest.setMTI(INQUIRY_REQUEST);
			paymentRequest.set(3, PAYMENT_PROCESSING_CODE);			
			paymentRequest.set(7, formatterBit7.print(dateNowGMT));
			paymentRequest.set(11, stan);
			paymentRequest.set(12, formatterBit12.print(dateNow));
			paymentRequest.set(37, stan + idPel.substring(idPel.length() - 6, idPel.length()));
			paymentRequest.set(48, setDataBillingProviderPrepaid(inqResponse.getString(48), unSoldToken));

			paymentRequest.unset(39);

			return paymentRequest;

		} catch (Exception ex) {
			Logger.getLogger(ClientPrePaid.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	private static String setDataBillingProviderPrepaid(String bit48Inq, int unsoldToken) {
		StringBuilder builder = new StringBuilder();
		builder.append(bit48Inq.substring(0, 149).trim());
		builder.append(StringUtils.rightPad(CA_INFO_DATA, 32, " ")); //CA_INFO_DATA
		
		if (unsoldToken > 0) {
			builder.append("1"); //Buying Flag unsold token			
		} else {
			builder.append("0"); //Buying Flag new token
		}
		
		builder.append("P"); // jenis transaksi	payment		
		
		builder.append(StringUtils.leftPad(String.valueOf(unsoldToken), 2, "0"));		
		builder.append(StringUtils.rightPad("", 6, " ")); //NFC_USAGE_PERIOD
		builder.append(StringUtils.leftPad("", 6, "0"));  //NFC_TOTAL_KWH
		builder.append(StringUtils.leftPad("", 6, "0"));  //NFC_KWH_BALANCE
		builder.append(StringUtils.leftPad("", 3, "0"));  //NFC_MINIMUM_VOLTAGE
		builder.append(StringUtils.leftPad("", 2, "0"));  //NFC_TEMPER_CODE
		return builder.toString();
	}
	
	private static ISOMsg createPaymentAdviceRequest(ISOMsg paymentRequest) {
		ISOMsg paymentAdviceRequest = (ISOMsg) paymentRequest.clone();
		DateTime dateNow = new DateTime();
		DateTime dateNowGMT = new DateTime(DateTimeZone.UTC);		
		DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");
		DateTimeFormatter formatterBit12 = DateTimeFormat.forPattern("hhmmss");
		
		String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
		String idPel = "15278290438";//"69363628156";
		try {
			paymentAdviceRequest.setMTI(INQUIRY_REQUEST);
			paymentAdviceRequest.set(3, PAYMENT_PROCESSING_CODE);
			paymentAdviceRequest.set(7, formatterBit7.print(dateNowGMT));
			paymentAdviceRequest.set(11, stan);
			paymentAdviceRequest.set(12, formatterBit12.print(dateNow));
			paymentAdviceRequest.set(37, stan + idPel.substring(idPel.length() - 6, idPel.length()));
			
			StringBuilder billingDataProvider = new StringBuilder();
			String bit48 = paymentRequest.getString("48");
			billingDataProvider.append(bit48.substring(0, 182));
			billingDataProvider.append("A");
			billingDataProvider.append(bit48.substring(183));
			
			System.out.println(billingDataProvider.toString());
			
			paymentAdviceRequest.set(48, billingDataProvider.toString());

			paymentAdviceRequest.unset(39);

			return paymentAdviceRequest;

		} catch (Exception ex) {
			Logger.getLogger(ClientPrePaid.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	private static void convertToProduct(String bit48Inq) {
		String produkIndikator = bit48Inq.substring(0, 4).trim();
		System.out.println(produkIndikator);
		String meterSerialNumber = bit48Inq.substring(4, 15).trim();
		System.out.println(meterSerialNumber);
		String idPelanggan = bit48Inq.substring(15, 28).trim();
		System.out.println(idPelanggan);
		String flag = bit48Inq.substring(28, 29).trim();
		System.out.println(flag);
		String subscriberName = bit48Inq.substring(29, 54).trim();
		System.out.println(subscriberName);
		String subscriberSegmentation = bit48Inq.substring(54, 58).trim();
		System.out.println(subscriberSegmentation);
		String powerConsumingCategory = bit48Inq.substring(58, 67).trim();
		System.out.println(powerConsumingCategory);
		String distributionCode = bit48Inq.substring(67, 69).trim();
		System.out.println(distributionCode);
		String serviceUnit = bit48Inq.substring(69, 74).trim();
		System.out.println(serviceUnit);
		String phoneServiceUnit = bit48Inq.substring(74, 89).trim();
		System.out.println(phoneServiceUnit);
		String maxKwh = bit48Inq.substring(89, 94).trim();
		System.out.println(maxKwh);
		String totalRepeat = bit48Inq.substring(94, 95).trim();
		System.out.println(totalRepeat);
		String powerPurchaseUnsold = bit48Inq.substring(95, 117).trim();
		System.out.println(powerPurchaseUnsold);
		String plnReferenceNumber = bit48Inq.substring(117, 149).trim();
		System.out.println(plnReferenceNumber);
		String bankReceiptReferenceNumber = bit48Inq.substring(149, 181).trim();
		System.out.println(bankReceiptReferenceNumber);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Q2 client = new Q2();
		client.start();

		// wait until connection established
		ISOUtil.sleep(3000);

		ISOMsg nmmRequest = createNetworkRequest();

		ChannelManager channelManager = (ChannelManager) NameRegistrar.get("jpos-client-manager");

		ISOMsg response = channelManager.sendRequest(nmmRequest);
		if (response == null) {
			System.out.println("Server is not responding");
		} else {
			System.out.println("Response : " + new String(response.pack()));

			ISOMsg signOnReq = createSingOn();
			response = channelManager.sendRequest(signOnReq);

			if (response == null) {
				System.out.println("Sign On failed");
			} else {
				System.out.println("Response sign on: " + new String(response.pack()));

				ISOMsg inquiryRequest = createPrePaidInquiryRequest();
				response = channelManager.sendRequest(inquiryRequest);

				if (response == null) {
					System.out.println("Inquiry failed");
				} else {
					System.out.println("Response inquiry: " + new String(response.pack()));
					convertToProduct(response.getString(48));
					// do purchase here					
					if (response.getString(39).equals("00")) {
						String bit48Inq = response.getString("48");
						String totalRepeat = bit48Inq.substring(94, 95).trim();
						System.out.println("total repeat unsold token : " + totalRepeat);
						
						ISOMsg purchaseRequest = createPrepaidPurchaseRequest(response, Integer.parseInt(totalRepeat));
						ISOMsg purchaseResponse = channelManager.sendRequest(purchaseRequest);
						ISOMsg adviceResponse = null;
						
						if (purchaseResponse == null) {
							for (int i = 0; i < 3; i++) {
								ISOMsg adviceRequest = createPaymentAdviceRequest(purchaseRequest);
								adviceResponse = channelManager.sendRequest(adviceRequest);
								if (adviceResponse == null) {
									continue;
								}
								
								if (adviceResponse != null) {
									System.out.println("Response advice " + i + ": " + new String(adviceResponse.pack()));
									break;
								}
									
							}
							
						}
						
						if (adviceResponse == null) {
							ISOMsg manualAdviceRequest = createPaymentAdviceRequest(purchaseRequest);
							ISOMsg manualAdviceResponse = channelManager.sendRequest(manualAdviceRequest);
							System.out.println("Response manual advice : " + new String(manualAdviceResponse.pack()));
						}						
						
					}
					
				}

			}
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
