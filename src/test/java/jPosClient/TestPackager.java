package jPosClient;

import org.apache.commons.lang.StringUtils;

public class TestPackager {

	private static void convertToProduct(String bit48Inq) {
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
	}

	private static void setBillingDataProvider(String bit48Inq) {
		StringBuilder builder = new StringBuilder();
		builder.append(bit48Inq.substring(0, 18).trim());
		builder.append(bit48Inq.substring(17, 18).trim());
		builder.append(bit48Inq.substring(18, 52));
		builder.append(StringUtils.rightPad("0ADR21", 32, " "));
		builder.append(bit48Inq.substring(84));
		System.out.println(builder.toString());
	}

	private static void convertToProductPayment(String bit48Inq) {
		String produkIndikator = bit48Inq.substring(0, 4).trim();
		System.out.println(produkIndikator);
		String idPelanggan = bit48Inq.substring(4, 17).trim();
		System.out.println(idPelanggan);
		String billStatus = bit48Inq.substring(17, 18).trim();
		System.out.println(billStatus);
		String statusPembayaran = bit48Inq.substring(18, 19).trim();
		System.out.println(statusPembayaran);
		String jmlTagihanBelumLunas = bit48Inq.substring(19, 21).trim();
		System.out.println(jmlTagihanBelumLunas);
		String kodeReferensiPLN = bit48Inq.substring(21, 53).trim();
		System.out.println(kodeReferensiPLN);
		String switcherRefNumber = bit48Inq.substring(53, 85).trim();
		System.out.println(switcherRefNumber);
		String nama = bit48Inq.substring(85, 115).trim();
		System.out.println(nama);
		String serviceUnit = bit48Inq.substring(115, 120).trim();
		System.out.println(serviceUnit);
		String teleponServiceUnit = bit48Inq.substring(120, 135).trim();
		System.out.println(teleponServiceUnit);
		String golonganPelanggan = bit48Inq.substring(135, 139).trim();
		System.out.println(golonganPelanggan);
		String kategoriPenggunaan = bit48Inq.substring(139, 148).trim();
		System.out.println(kategoriPenggunaan);
		String biayaAdmin = bit48Inq.substring(148, 157).trim();
		System.out.println(biayaAdmin);
		String nilaiTagihan = bit48Inq.substring(157, 169).trim();
		System.out.println(nilaiTagihan);
		String jumlahAngkaDesimal = bit48Inq.substring(169, 170).trim();
		System.out.println(jumlahAngkaDesimal);
		String mataUangTagihan = bit48Inq.substring(170, 173).trim();
		System.out.println(mataUangTagihan);

		String detailTagihan = bit48Inq.substring(173).trim();
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

		String PanjangTeksInformasi = detailTagihan.substring(j, j = j + 3).trim();
		System.out.println(PanjangTeksInformasi);
		String TeksInformasi = detailTagihan.substring(j).trim();
		System.out.println(TeksInformasi);
	}
	
	public static void convertToProductPlnPostapidReversal(String bit48Inq) {
		String produkIndikator = bit48Inq.substring(0, 4).trim();
		System.out.println(produkIndikator);
		String idPelanggan = bit48Inq.substring(4, 17).trim();
		System.out.println(idPelanggan);
		String billStatus = bit48Inq.substring(17, 18).trim();
		System.out.println(billStatus);
		String statusPembayaran = bit48Inq.substring(18, 19).trim();
		System.out.println(statusPembayaran);
		String jmlTagihanBelumLunas = bit48Inq.substring(19, 21).trim();
		System.out.println(jmlTagihanBelumLunas);
		String kodeReferensiPLN = bit48Inq.substring(21, 53).trim();
		System.out.println(kodeReferensiPLN);
		String switcherRefNumber = bit48Inq.substring(53, 85).trim();
		System.out.println(switcherRefNumber);
		String nama = bit48Inq.substring(85, 115).trim();
		System.out.println(nama);
		String serviceUnit = bit48Inq.substring(115, 120).trim();
		System.out.println(serviceUnit);
		String teleponServiceUnit = bit48Inq.substring(120, 135).trim();
		System.out.println(teleponServiceUnit);
		String golonganPelanggan = bit48Inq.substring(135, 139).trim();
		System.out.println(golonganPelanggan);
		String kategoriPenggunaan = bit48Inq.substring(139, 148).trim();
		System.out.println(kategoriPenggunaan);
		String biayaAdmin = bit48Inq.substring(148, 157).trim();
		System.out.println(biayaAdmin);
		String nilaiTagihan = bit48Inq.substring(157, 169).trim();
		System.out.println(nilaiTagihan);
		String jumlahAngkaDesimal = bit48Inq.substring(169, 170).trim();
		System.out.println(jumlahAngkaDesimal);
		String mataUangTagihan = bit48Inq.substring(170, 173).trim();
		System.out.println(mataUangTagihan);

		String detailTagihan = bit48Inq.substring(173).trim();
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
	}	

	public static void convertToProductPrePaidInquiry(String bit48Inq) {
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
	
	public static void convertToProductPrePaidPurchase(String bit48Inq) {
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
		String buyingFlag = bit48Inq.substring(181, 182).trim();
		System.out.println(buyingFlag);
		String jenisTransaksi = bit48Inq.substring(182, 183).trim();
		System.out.println(jenisTransaksi);
		String vendingReceiptNumber = bit48Inq.substring(183, 191).trim();
		System.out.println(vendingReceiptNumber);
		String minorUnitAdmin = bit48Inq.substring(191, 192).trim();
		System.out.println(minorUnitAdmin);
		String adminCharge = bit48Inq.substring(192, 202).trim();
		System.out.println(adminCharge);
		String minorUnitStampDuty = bit48Inq.substring(202, 203).trim();
		System.out.println(minorUnitStampDuty);
		String stampDuty = bit48Inq.substring(203, 213).trim();
		System.out.println(stampDuty);
		String minorUnitValueAdded = bit48Inq.substring(213, 214).trim();
		System.out.println(minorUnitValueAdded);
		String valueAddedTax = bit48Inq.substring(214, 224).trim();
		System.out.println(valueAddedTax);
		String minorUnitPublicLightingTax = bit48Inq.substring(224, 225).trim();
		System.out.println(minorUnitPublicLightingTax);
		String publicLightingTax = bit48Inq.substring(225, 235).trim();
		System.out.println(publicLightingTax);
		String minorUnitCustomerPayablesInstallment = bit48Inq.substring(235, 236).trim();
		System.out.println(minorUnitCustomerPayablesInstallment);
		String customerPayablesInstallment = bit48Inq.substring(236, 246).trim();
		System.out.println(customerPayablesInstallment);
		String minorUnitPowerPurchase = bit48Inq.substring(246, 247).trim();
		System.out.println(minorUnitPowerPurchase);
		String powerPurchase = bit48Inq.substring(247, 259).trim();
		System.out.println(powerPurchase);
		String minorUnitPurchasedKWH = bit48Inq.substring(259, 260).trim();
		System.out.println(minorUnitPurchasedKWH);
		String purchasedKWHUnit = bit48Inq.substring(260, 270).trim();
		System.out.println(purchasedKWHUnit);
		String tokenNumber = bit48Inq.substring(270, 290).trim();
		System.out.println(tokenNumber);
		String panjangTeksInformasi = bit48Inq.substring(290, 293).trim();
		System.out.println(panjangTeksInformasi);
		String teksInformasi = bit48Inq.substring(293).trim();
		System.out.println(teksInformasi);
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String bit48Inq 		= "2112539507764112 404XVW2VXXZV3ZYWV56789AY12VY2WXXV2W                                PEL POSTPAID 088767           535550212337890     R1  00000130000000000000000120000003602017022502201724022017000000300000C000000000000000000000000000000000000010000000100000002000000020000000300000003002017012501201724022017000000300000C000000000000000000000000000000000000010000000100000002000000020000000300000003002016122512201624022017000000300000C000000000000000000000000000000000000010000000100000002000000020000000300000003002016102510201624022017000000300000C00000000000000000000000000000000000001000000010000000200000002000000030000000300";
		//convertToProduct(bit48Inq);
		//setBillingDataProvider(bit48Inq);

		String bit48Resp 		= "2112539507764112 4404XVW2VXXZV3ZYWV56789AY12VY2WXXV2W0ADR21V00KA0002070025LU74BQ64112PEL POSTPAID 088767           535550212337890     R1  00000130000000000000000120000003602017022502201724022017000000300000C000000000000000000000000000000000000010000000100000002000000020000000300000003002017012501201724022017000000300000C000000000000000000000000000000000000010000000100000002000000020000000300000003002016122512201624022017000000300000C000000000000000000000000000000000000010000000100000002000000020000000300000003002016102510201624022017000000300000C00000000000000000000000000000000000001000000010000000200000002000000030000000300073Terima kasih;Informasi Hubungi Call Center 789 etau Hub. PLN Terdekat:999";
		//convertToProductPayment(bit48Resp);
		
		String bit48Reversal 	= "2112539507764112 4404XVW2VXXZV3ZYWV56789AY12VY2WXXV2W0ADR21V00KA0001060011NB13KB64112PEL POSTPAID 088767           535550212337890     R1  00000130000005000000000120000003602017022502201724022017000000300000C000000000000000000000000000000000000010000000100000002000000020000000300000003002017012501201724022017000000300000C000000000000000000000000000000000000010000000100000002000000020000000300000003002016122512201624022017000000300000C000000000000000000000000000000000000010000000100000002000000020000000300000003002016102510201624022017000000300000C00000000000000000000000000000000000001000000010000000200000002000000030000000300";
		//convertToProductPlnPostapidReversal(bit48Reversal);
		
		//String bit48InqPrepaid = "211169363628156699719225434 0PEL PRA BAYAR 042998     R1  00000130053535550212337890     0065000000000000000000000000XVW2VXXZV3ZYWX56789AY0X1Z111ZY31                                ";
		// unsold token
		String bit48InqPrepaid = "211115278290438821323738341 0PEL PRA BAYAR 580006     R1  00000130053535550212337890     0065020000100000000000500000XVW2VZWYW1VVXW56789A3341ZW2XV1100ADR21V00KA00021500680D9010904380P55278380000000000000000000000000000000000000000000000000000000000000001000000000000010050744040904174573123060Informasi Hubungi Call Center 789 etau Hub. PLN Terdekat:999";
		//convertToProductPrePaidInquiry(bit48InqPrepaid);
		
		String bit48PurchasePrepaid = "211169363628156699719225434 0PEL PRA BAYAR 042998     R1  00000130053535550212337890     0065000000000000000000000000XVW2VXXZV3ZYWX56789AY0X1Z111ZY310ADR21V00KA0000710051JD50GQ281560P92163615000000000000000000000000000000000000000000000000000000000000001000000000000010086303176844592474165060Informasi Hubungi Call Center 789 etau Hub. PLN Terdekat:999";
		//convertToProductPrePaidPurchase(bit48PurchasePrepaid);
		
		String bit48PurchaseRequest = "211115278290438821323738341 0PEL PRA BAYAR 580006     R1  00000130053535550212337890     0065020000100000000000500000XVW2VZWYW1VVXW56789A3341ZW2XV1100ADR21                          1P02      00000000000000000";
		StringBuilder billingDataProvider = new StringBuilder();		
		billingDataProvider.append(bit48PurchaseRequest.substring(0, 182));
		billingDataProvider.append("A");
		billingDataProvider.append(bit48PurchaseRequest.substring(183));
		
		System.out.println(billingDataProvider.toString());
	}

}
