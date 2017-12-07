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

public class Client {
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
	public static final String PRODUCT_POSTPAID_PLN = "2112";
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
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
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
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
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
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

	private static ISOMsg createPostpaidInquiryRequest() {
		ISOMsg inqRequest = new ISOMsg();
		DateTime dateNow = new DateTime();
		DateTime dateNowGMT = new DateTime(DateTimeZone.UTC);
		DateTime setelmentDate = dateNow.plusDays(1);
		DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");
		DateTimeFormatter formatterBit12 = DateTimeFormat.forPattern("hhmmss");
		DateTimeFormatter formatterBit13 = DateTimeFormat.forPattern("MMdd");

		String pan = StringUtils.leftPad(BANK_ANDARA + formatterBit7.print(dateNowGMT), 16, "0"); 
		String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
		String idPel = "539507764112"; //"530170958192";
		String cardAcceptorId = StringUtils.rightPad("02906123", 15, " ");

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
			sb.append(PRODUCT_POSTPAID_PLN);// produk indikator
			sb.append(StringUtils.rightPad(idPel, 13, " "));// identitas pelanggan
			inqRequest.set(48, sb.toString());

			inqRequest.set(49, CURRENCY_CODE);
			inqRequest.set(63, BILLING_PROVIDER_CODE);
			return inqRequest;
		} catch (Exception ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public static ISOMsg createPostpaidPaymentRequest(ISOMsg inqResponse) {
		ISOMsg paymentRequest = (ISOMsg) inqResponse.clone();
		DateTime dateNow = new DateTime();
		DateTime dateNowGMT = new DateTime(DateTimeZone.UTC);
		DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");
		DateTimeFormatter formatterBit12 = DateTimeFormat.forPattern("hhmmss");
		
		String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
		String idPel = "539507764112"; //"530170958192";
		
		try {
			paymentRequest.setMTI(INQUIRY_REQUEST);
			paymentRequest.set(3, PAYMENT_PROCESSING_CODE);
			paymentRequest.set(4, convertToProduct(inqResponse.getString(48)));
			paymentRequest.set(7, formatterBit7.print(dateNowGMT));
			paymentRequest.set(11, stan);
			paymentRequest.set(12, formatterBit12.print(dateNow));
			paymentRequest.set(37, stan + idPel.substring(idPel.length() - 6, idPel.length()));
			paymentRequest.set(48, setDataBillingProvider(inqResponse.getString(48)));

			paymentRequest.unset(39);

			return paymentRequest;

		} catch (Exception ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public static ISOMsg createReversalRequest(ISOMsg paymentReq) {
		ISOMsg reversalReq = (ISOMsg) paymentReq.clone();
		DateTime dateNow = new DateTime();
		DateTime dateNowGMT = new DateTime(DateTimeZone.UTC);
		
		DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");
		DateTimeFormatter formatterBit12 = DateTimeFormat.forPattern("hhmmss");
		try {
			reversalReq.setMTI(REVERSAL_REQUEST);

			reversalReq.set(7, formatterBit7.print(dateNowGMT));
			reversalReq.set(12, formatterBit12.print(dateNow));

			final StringBuilder bit90 = new StringBuilder();
			bit90.append(paymentReq.getMTI());
			bit90.append(paymentReq.getString(11));
			bit90.append(paymentReq.getString(7));
			bit90.append(StringUtils.leftPad(paymentReq.getString(32), 11, "0"));
			bit90.append(StringUtils.rightPad("0", 11, "0"));

			reversalReq.set(90, bit90.toString());

			reversalReq.unset(39);
			return reversalReq;

		} catch (Exception ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	private static String setDataBillingProvider(String bit48Inq) {
		StringBuilder builder = new StringBuilder();
		builder.append(bit48Inq.substring(0, 18).trim());
		builder.append(bit48Inq.substring(17, 18).trim());
		builder.append(bit48Inq.substring(18, 52));
		builder.append(StringUtils.rightPad(CA_INFO_DATA, 32, " "));
		builder.append(bit48Inq.substring(84));
		return builder.toString();
	}
	
	private static String convertToProduct(String bit48Inq) {
		String produkIndikator = bit48Inq.substring(0, 4).trim();
		System.out.println(produkIndikator);
		String idPelanggan = bit48Inq.substring(4, 17).trim();
		System.out.println(idPelanggan);
		String billStatus = bit48Inq.substring(17, 18).trim();
		System.out.println(billStatus);
		String jmlTagihanBelumLunas = bit48Inq.substring(18, 20).trim();
		System.out.println(jmlTagihanBelumLunas);
		String kodeReferensiPLN = bit48Inq.substring(20, 52).trim();
		System.out.println(kodeReferensiPLN);
		String switcherRefNumber = bit48Inq.substring(52, 84).trim();
		System.out.println(switcherRefNumber);
		String nama = bit48Inq.substring(84, 114).trim();
		System.out.println(nama);
		String serviceUnit = bit48Inq.substring(114, 119).trim();
		System.out.println(serviceUnit);
		String teleponServiceUnit = bit48Inq.substring(119, 134).trim();
		System.out.println(teleponServiceUnit);
		String golonganPelanggan = bit48Inq.substring(134, 138).trim();
		System.out.println(golonganPelanggan);
		String kategoriPenggunaan = bit48Inq.substring(138, 147).trim();
		System.out.println(kategoriPenggunaan);
		String biayaAdmin = bit48Inq.substring(147, 156).trim();
		System.out.println(biayaAdmin);
		String nilaiTagihan = bit48Inq.substring(156, 168).trim();
		System.out.println(nilaiTagihan);
		String jumlahAngkaDesimal = bit48Inq.substring(168, 169).trim();
		System.out.println(jumlahAngkaDesimal);
		String mataUangTagihan = bit48Inq.substring(169, 172).trim();
		System.out.println(mataUangTagihan);

		String detailTagihan = bit48Inq.substring(172).trim();
		System.out.println(detailTagihan);

		int j = 0;
		for (int i = 0; i < Integer.parseInt(billStatus); i++) {
			String PeriodeTagihan = detailTagihan.substring(j, j = j + 6).trim();
			System.out.println((i + 1) + "-" + PeriodeTagihan);
			String JatuhTempo = detailTagihan.substring(j, j = j + 8).trim();
			System.out.println((i + 1) + "-" + JatuhTempo);
			String TglBacaMeteran = detailTagihan.substring(j, j = j + 8).trim();
			System.out.println((i + 1) + "-" + TglBacaMeteran);
			String TagihanListrik = detailTagihan.substring(j, j = j + 12).trim();
			System.out.println((i + 1) + "-" + TagihanListrik);
			String KodeInsentif = detailTagihan.substring(j, j = j + 1).trim();
			System.out.println((i + 1) + "-" + KodeInsentif);
			String NilaiInsentif = detailTagihan.substring(j, j = j + 10).trim();
			System.out.println((i + 1) + "-" + NilaiInsentif);
			String PajakNilaiTambah = detailTagihan.substring(j, j = j + 10).trim();
			System.out.println((i + 1) + "-" + PajakNilaiTambah);
			String Denda = detailTagihan.substring(j, j = j + 12).trim();
			System.out.println((i + 1) + "-" + Denda);
			String SLALWBP = detailTagihan.substring(j, j = j + 8).trim();
			System.out.println((i + 1) + "-" + SLALWBP);
			String SAHLWBP = detailTagihan.substring(j, j = j + 8).trim();
			System.out.println((i + 1) + "-" + SAHLWBP);
			String SLAWBP = detailTagihan.substring(j, j = j + 8).trim();
			System.out.println((i + 1) + "-" + SLAWBP);
			String SAHWBP = detailTagihan.substring(j, j = j + 8).trim();
			System.out.println((i + 1) + "-" + SAHWBP);
			String SLAKVARH = detailTagihan.substring(j, j = j + 8).trim();
			System.out.println((i + 1) + "-" + SLAKVARH);
			String SAHKVARH = detailTagihan.substring(j, j = j + 8).trim();
			System.out.println((i + 1) + "-" + SAHKVARH);
		}
		
		return nilaiTagihan;
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

				/*
				ISOMsg inquiryRequest = createPostpaidInquiryRequest();
				response = channelManager.sendRequest(inquiryRequest);

				if (response == null) {
					System.out.println("Inquiry failed");
				} else {
					System.out.println("Response inquiry: " + new String(response.pack()));
					// do payment here
					
					if (response.getString(39).equals("00")) {
						ISOMsg paymentRequest = createPostpaidPaymentRequest(response);
						response = channelManager.sendRequest(paymentRequest);			
												
						if (response == null) {
							boolean reversed = false;
							for (int i = 0; i < 4; i++) {
								ISOMsg reversalRequest = createReversalRequest(paymentRequest);
								ISOMsg responseReversal = channelManager.sendRequest(reversalRequest);
								
								if (responseReversal == null) {
									System.out.println("Response reversal " + i + " timeout");
									
									continue;
								}							
								
								if (responseReversal.getString(39).equals("00") || responseReversal.getString(39).equals("94")) {
									System.out.println("Response reversal " + i +  ": " + new String(responseReversal.pack()));
									reversed = true;
									break;
								}
								
								if (responseReversal.getString(39).equals("12") || responseReversal.getString(39).equals("63")) {
									System.out.println("Response reversal " + i +  ": " + new String(responseReversal.pack()));
									reversed = false;
									break;
								}
									
							}
							System.out.println("FUCKING RESULT------------------------>" + reversed);		
							
						}
						
						//System.out.println("Response payment: " + new String(response.pack()));						
					}				
				} */

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
