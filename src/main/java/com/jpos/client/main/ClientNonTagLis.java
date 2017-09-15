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

public class ClientNonTagLis {
	private static final String ECHO_TEST = "301";
	private static final String SIGN_ON = "001";
	private static final String NETWORK_REQUEST = "0800";
	private static final String INQUIRY_REQUEST = "0200";
	private static final String REVERSAL_REQUEST = "0400";
	private static final String INQUIRY_PROCESSING_CODE = "380000";
	private static final String PAYMENT_PROCESSING_CODE = "180000";
	private static final String MERCHANT_TYPE = "6021"; // bit 18
	private static final String BANK_ANDARA = "000730";// bit 32
	private static final String PRODUCT_NON_TAGLIST_PLN = "2114";
	private static final String CURRENCY_CODE = "360"; // bit 49
	private static final String BILLING_PROVIDER_CODE = "214"; // bit 63
	private static final String CA_INFO_DATA = "0ADR21";
	private static final String TRANSACTION_CODE = "000";

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

	private static ISOMsg createNonTagListInquiryRequest(final String idPel, final String cardAcceptorId, final String stan) {
		ISOMsg inqRequest = new ISOMsg();
		DateTime dateNow = new DateTime();
		DateTime setelmentDate = dateNow.plusDays(1);
		DateTime dateNowGMT = new DateTime(DateTimeZone.UTC);

		DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");
		DateTimeFormatter formatterBit12 = DateTimeFormat.forPattern("hhmmss");
		DateTimeFormatter formatterBit13 = DateTimeFormat.forPattern("MMdd");

		String pan = StringUtils.leftPad(BANK_ANDARA + formatterBit7.print(dateNowGMT), 16, "0");

		try {
			inqRequest.setMTI(INQUIRY_REQUEST);
			inqRequest.set(2, pan);
			inqRequest.set(3, INQUIRY_PROCESSING_CODE);
			inqRequest.set(4, StringUtils.leftPad("0", 12, "0"));
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

			StringBuilder sb = new StringBuilder();
			sb.append(PRODUCT_NON_TAGLIST_PLN);// produk indikator
			sb.append(StringUtils.rightPad(idPel, 13, " "));// identitas pelanggan
			sb.append(TRANSACTION_CODE);
			inqRequest.set(48, sb.toString());

			inqRequest.set(49, CURRENCY_CODE);
			inqRequest.set(63, BILLING_PROVIDER_CODE);
			return inqRequest;
		} catch (Exception ex) {
			Logger.getLogger(ClientPrePaid.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	private static String convertToProduct(String bit48Inq) {
		String produkIndikator = bit48Inq.substring(0, 4).trim();
		System.out.println(produkIndikator);
		String nomorRegistrasi = bit48Inq.substring(4, 17).trim();
		System.out.println(nomorRegistrasi);
		String transactionCode = bit48Inq.substring(17, 20).trim();
		System.out.println(transactionCode);
		String transactionName = bit48Inq.substring(20, 45).trim();
		System.out.println(transactionName);
		String registrationDate = bit48Inq.substring(45, 53).trim();
		System.out.println(registrationDate);
		String expirationDate = bit48Inq.substring(53, 61).trim();
		System.out.println(expirationDate);
		String idPel = bit48Inq.substring(61, 74).trim();
		System.out.println(idPel);
		String nama = bit48Inq.substring(74, 104).trim();
		System.out.println(nama);
		String kodeReferensiTransaksi = bit48Inq.substring(104, 136).trim();
		System.out.println(kodeReferensiTransaksi);
		String bankReceiptReferenceNumber = bit48Inq.substring(136, 168).trim();
		System.out.println(bankReceiptReferenceNumber);
		String serviceUnit = bit48Inq.substring(168, 173).trim();
		System.out.println(serviceUnit);
		String alamatServiceUnit = bit48Inq.substring(173, 208).trim();
		System.out.println(alamatServiceUnit);
		String phoneServiceUnit = bit48Inq.substring(208, 223).trim();
		System.out.println(phoneServiceUnit);
		String nilaiTagihan = bit48Inq.substring(223, 235).trim();
		System.out.println(nilaiTagihan);
		String jumlahAngkaDesimal = bit48Inq.substring(235, 236).trim();
		System.out.println(jumlahAngkaDesimal);
		String mataUangTagihan = bit48Inq.substring(236, 239).trim();
		System.out.println(mataUangTagihan);
		String minorUnitTotalAmount = bit48Inq.substring(239, 240).trim();
		System.out.println(minorUnitTotalAmount);
		String totalAmount = bit48Inq.substring(240, 257).trim();
		System.out.println(totalAmount);
		String minorUnitRpTag = bit48Inq.substring(257, 258).trim();
		System.out.println(minorUnitRpTag);
		String rpTag = bit48Inq.substring(258, 275).trim();
		System.out.println(rpTag);
		String minorUnitAdmin = bit48Inq.substring(275, 276).trim();
		System.out.println(minorUnitAdmin);
		String adminCharge = bit48Inq.substring(276, 286).trim();
		System.out.println(adminCharge);
		String billComponentType = bit48Inq.substring(286, 288).trim();
		System.out.println(billComponentType);
		String minorUnitBillComponent = bit48Inq.substring(288, 289).trim();
		System.out.println(minorUnitBillComponent);
		String billComponent = bit48Inq.substring(289, 306).trim();
		System.out.println(billComponent);

		return nilaiTagihan;
	}

	private static ISOMsg createNonTagListPaymentRequest(ISOMsg inqResponse, String idPel) {
		ISOMsg paymentRequest = (ISOMsg) inqResponse.clone();
		DateTime dateNow = new DateTime();
		DateTime dateNowGMT = new DateTime(DateTimeZone.UTC);

		DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");
		DateTimeFormatter formatterBit12 = DateTimeFormat.forPattern("hhmmss");

		String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");

		try {
			paymentRequest.setMTI(INQUIRY_REQUEST);
			paymentRequest.set(3, PAYMENT_PROCESSING_CODE);
			paymentRequest.set(4, convertToProduct(inqResponse.getString(48)));
			paymentRequest.set(7, formatterBit7.print(dateNowGMT));
			paymentRequest.set(11, stan);
			paymentRequest.set(12, formatterBit12.print(dateNow));
			paymentRequest.set(37, stan + idPel.substring(idPel.length() - 6, idPel.length()));
			paymentRequest.set(48, setDataBillingProviderNonTagList(inqResponse.getString(48)));

			paymentRequest.unset(39);

			return paymentRequest;

		} catch (Exception ex) {
			Logger.getLogger(ClientPrePaid.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	private static String setDataBillingProviderNonTagList(String bit48Inq) {
		StringBuilder builder = new StringBuilder();
		builder.append(bit48Inq.substring(0, 136).trim());
		builder.append(StringUtils.rightPad(CA_INFO_DATA, 32, " ")); // CA_INFO_DATA
		builder.append(bit48Inq.substring(168, 306).trim());
		return builder.toString();
	}

	private static void convertToProductPlnNonTaglistPayment(ISOMsg paymentResp) {
		String bit48Inq = paymentResp.getString(48);

		String produkIndikator = bit48Inq.substring(0, 4).trim();
		System.out.println(produkIndikator);
		String nomorRegistrasi = bit48Inq.substring(4, 17).trim();
		System.out.println(nomorRegistrasi);
		String transactionCode = bit48Inq.substring(17, 20).trim();
		System.out.println(transactionCode);
		String transactionName = bit48Inq.substring(20, 45).trim();
		System.out.println(transactionName);
		String registrationDate = bit48Inq.substring(45, 53).trim();
		System.out.println(registrationDate);
		String expirationDate = bit48Inq.substring(53, 61).trim();
		System.out.println(expirationDate);
		String idPel = bit48Inq.substring(61, 74).trim();
		System.out.println(idPel);
		String nama = bit48Inq.substring(74, 104).trim();
		System.out.println(nama);
		String kodeReferensiTransaksi = bit48Inq.substring(104, 136).trim();
		System.out.println(kodeReferensiTransaksi);
		String bankReceiptReferenceNumber = bit48Inq.substring(136, 168).trim();
		System.out.println(bankReceiptReferenceNumber);
		String serviceUnit = bit48Inq.substring(168, 173).trim();
		System.out.println(serviceUnit);
		String alamatServiceUnit = bit48Inq.substring(173, 208).trim();
		System.out.println(alamatServiceUnit);
		String phoneServiceUnit = bit48Inq.substring(208, 223).trim();
		System.out.println(phoneServiceUnit);
		String nilaiTagihan = bit48Inq.substring(223, 235).trim();
		System.out.println(nilaiTagihan);
		String jumlahAngkaDesimal = bit48Inq.substring(235, 236).trim();
		System.out.println(jumlahAngkaDesimal);
		String mataUangTagihan = bit48Inq.substring(236, 239).trim();
		System.out.println(mataUangTagihan);
		String minorUnitTotalAmount = bit48Inq.substring(239, 240).trim();
		System.out.println(minorUnitTotalAmount);
		String totalAmount = bit48Inq.substring(240, 257).trim();
		System.out.println(totalAmount);
		String minorUnitRpTag = bit48Inq.substring(257, 258).trim();
		System.out.println(minorUnitRpTag);
		String rpTag = bit48Inq.substring(258, 275).trim();
		System.out.println(rpTag);
		String minorUnitAdmin = bit48Inq.substring(275, 276).trim();
		System.out.println(minorUnitAdmin);
		String adminCharge = bit48Inq.substring(276, 286).trim();
		System.out.println(adminCharge);
		String billComponentType = bit48Inq.substring(286, 288).trim();
		System.out.println(billComponentType);
		String minorUnitBillComponent = bit48Inq.substring(288, 289).trim();
		System.out.println(minorUnitBillComponent);
		String billComponent = bit48Inq.substring(289, 306).trim();
		System.out.println(billComponent);
		String panjangTeksInformasi = bit48Inq.substring(306, 309).trim();
		System.out.println(panjangTeksInformasi);
		String teksInformasi = bit48Inq.substring(309).trim();
		System.out.println(teksInformasi);
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

				String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
				String idPel = "975615013272";
				String cardAcceptorId = StringUtils.rightPad("02906123", 15, " ");

				ISOMsg inquiryRequest = createNonTagListInquiryRequest(idPel, cardAcceptorId, stan);
				response = channelManager.sendRequest(inquiryRequest);

				if (response == null) {
					System.out.println("Inquiry failed");
				} else {
					System.out.println("Response inquiry: " + new String(response.pack()));
					if (response.getString(39).equals("00")) {
						convertToProduct(response.getString(48));

						ISOMsg paymentRequest = createNonTagListPaymentRequest(response, idPel);
						ISOMsg paymentResponse = channelManager.sendRequest(paymentRequest);
						if (paymentResponse == null) {
							System.out.println("Payment response is null");
						} else {
							System.out.println("Response payment: " + new String(paymentResponse.pack()));
							if (paymentResponse.getString(39).equals("00")) {
								convertToProductPlnNonTaglistPayment(paymentResponse);
							}
						}
					}
				}
			}
		}
	}

}
