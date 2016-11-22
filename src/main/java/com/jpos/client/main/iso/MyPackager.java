package com.jpos.client.main.iso;

import org.jpos.iso.*;

public class MyPackager extends ISOBasePackager {

	public MyPackager() {
		setFieldPackager(new ISOFieldPackager[] {
		/* 000 */new IFA_NUMERIC(4, "Message Type Indicator"),
		/* 001 */new IFA_BITMAP(64, "Bitmap"),
		/* 002 */new IFA_LLNUM(19, "Primary Account Number"),
		/* 003 */new IFA_NUMERIC(6, "Processing Code"),
		/* 004 */new IFA_AMOUNT(12, "Amount, Transaction"),
		/* 005 */new IFA_NUMERIC(10, "Transmission Date and Time"),
		/* 006 */new IFA_NUMERIC(10, "Transmission Date and Time"),
		/* 007 */new IFA_NUMERIC(10, "Transmission Date and Time"),
		/* 008 */new IFA_NUMERIC(10, "Transmission Date and Time"),
		/* 009 */new IFA_NUMERIC(10, "Transmission Date and Time"),
		/* 010 */new IFA_NUMERIC(10, "Transmission Date and Time"),
		/* 011 */new IFA_NUMERIC(6, "System Trace Audit Number"),
		/* 012 */new IFA_NUMERIC(6, "Time, Local Transaction"),
		/* 013 */new IFA_NUMERIC(4, "Date, Local Transaction"),
		/* 014 */new IFA_NUMERIC(4, "Date, Expiration"),
		/* 015 */new IFA_NUMERIC(4, "Date, Settlement"),
		/* 016 */new IFA_NUMERIC(4, "Date, Settlement"),
		/* 017 */new IFA_NUMERIC(4, "Date, Settlement"),
		/* 018 */new IFA_NUMERIC(4, "Merchant Type"),
		/* 019 */new IFA_NUMERIC(4, "Merchant Type"),
		/* 020 */new IFA_NUMERIC(4, "Merchant Type"),
		/* 021 */new IFA_NUMERIC(4, "Merchant Type"),
		/* 022 */new IFA_NUMERIC(3, "Point of Service Entry Mode"),
		/* 023 */new IFA_NUMERIC(3, "Point of Service Entry Mode"),
		/* 024 */new IFA_NUMERIC(3, "Point of Service Entry Mode"),
		/* 025 */new IFA_NUMERIC(2, "Point-Of-Service Condition Code"),
		/* 026 */new IFA_NUMERIC(2, "Point-Of-Service PIN Capture Code"),
		/* 027 */new IFA_NUMERIC(1, "Authorization Institution Identification Code"),
		/* 028 */new IFA_NUMERIC(1, "Authorization Institution Identification Code"),
		/* 029 */new IFA_NUMERIC(1, "Authorization Institution Identification Code"),
		/* 030 */new IFA_NUMERIC(1, "Authorization Institution Identification Code"),
		/* 031 */new IFA_NUMERIC(1, "Authorization Institution Identification Code"),
		/* 032 */new IFA_LLNUM(11, "Acquiring Institution Identification Code"),
		/* 033 */new IFA_LLNUM(11, "Forwarding Institution Identification Code"),
		/* 034 */new IFA_LLNUM(11, "Forwarding Institution Identification Code"),
		/* 035 */new IFA_LLCHAR(37, "TRACK 2 DATA"),
		/* 036 */new IFA_LLNUM(37, "TRACK 2 DATA"),
		/* 037 */new IF_CHAR(12, "Retrieval Reference Number"),
		/* 038 */new IF_CHAR(6, "Authorization Identification Response"),
		/* 039 */new IF_CHAR(2, "Response Code"),
		/* 040 */new IF_CHAR(2, "Response Code"),
		/* 041 */new IF_CHAR(8, "Card Acceptor Terminal Identification"),
		/* 042 */new IF_CHAR(15, "Card Acceptor Identification"),
		/* 043 */new IF_CHAR(40, "Card Acceptor Name and Location"),
		/* 044 */new IF_CHAR(40, "Card Acceptor Name and Location"),
		/* 045 */new IF_CHAR(40, "Card Acceptor Name and Location"),
		/* 046 */new IF_CHAR(40, "Card Acceptor Name and Location"),
		/* 047 */new IF_CHAR(40, "Card Acceptor Name and Location"),
		/* 048 */new IFA_LLLCHAR(999, "Additional Data - Private"),
		/* 049 */new IFA_NUMERIC(3, "Transaction Currency Code"),
		/* 050 */new IFA_NUMERIC(3, "Transaction Currency Code"),
		/* 051 */new IFA_NUMERIC(3, "Transaction Currency Code"),
		/* 052 */new IF_CHAR(16, "Personal Identification Number"),
		/* 053 */new IF_CHAR(16, "Personal Identification Number"),
		/* 054 */new IFA_LLLCHAR(120, "Amount, Additional"),
		/* 055 */new IFA_LLLCHAR(120, "Amount, Additional"),
		/* 056 */new IFA_LLLCHAR(120, "Amount, Additional"),
		/* 057 */new IFA_LLLCHAR(120, "Amount, Additional"),
		/* 058 */new IFA_LLLCHAR(120, "Amount, Additional"),
		/* 059 */new IFA_LLLCHAR(120, "Amount, Additional"),
		/* 060 */new IFA_LLLCHAR(15, "Reserved Private F60"),
		/* 061 */new IFA_LLLCHAR(999, "Reserved Private F61"),
		/* 062 */new IFA_LLLCHAR(999, "Reserved Private F61"),
		/* 063 */new IFA_LLLCHAR(999, "Reserved Private F61"),
		/* 064 */new IFA_LLLCHAR(999, "Reserved Private F61"),
		/* 065 */new IFA_LLLCHAR(999, "Reserved Private F61"),
		/* 066 */new IFA_LLLCHAR(999, "Reserved Private F61"),
		/* 067 */new IFA_LLLCHAR(999, "Reserved Private F61"),
		/* 068 */new IFA_LLLCHAR(999, "Reserved Private F61"),
		/* 069 */new IFA_LLLCHAR(999, "Reserved Private F61"),
		/* 070 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 071 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 072 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 073 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 074 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 075 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 076 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 077 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 078 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 079 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 080 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 081 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 082 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 083 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 084 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 085 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 086 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 087 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 088 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 089 */new IFA_NUMERIC(3, "Network Management Information Code"),
		/* 090 */new IFA_NUMERIC(42, "Original Data Element"),
		/* 091 */new IFA_NUMERIC(42, "Original Data Element"),
		/* 092 */new IFA_NUMERIC(42, "Original Data Element"),
		/* 093 */new IFA_NUMERIC(42, "Original Data Element"),
		/* 094 */new IFA_NUMERIC(42, "Original Data Element"),
		/* 095 */new IFA_NUMERIC(42, "Original Data Element"),
		/* 096 */new IFA_NUMERIC(42, "Original Data Element"),
		/* 097 */new IFA_NUMERIC(42, "Original Data Element"),
		/* 098 */new IFA_NUMERIC(42, "Original Data Element"),
		/* 099 */new IFA_NUMERIC(42, "Original Data Element"),
		/* 100 */new IFA_NUMERIC(42, "Original Data Element"),
		/* 101 */new IFA_NUMERIC(42, "Original Data Element"),
		/* 102 */new IFA_LLNUM(30, "Source Account"),
		/* 103 */new IFA_LLNUM(30, "Destination Account"),
		/* 104 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 105 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 106 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 107 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 108 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 109 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 110 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 111 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 112 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 113 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 114 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 115 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 116 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 117 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 118 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 119 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 120 */new IFA_LLCHAR(32, "Switcher Reference Key"),
		/* 121 */new IFA_LLLCHAR(999, "Footer Text"),
		/* 122 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 123 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 124 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 125 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 126 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 127 */new IFA_LLCHAR(28, "Account Identification 2"),
		/* 128 */new IFA_LLCHAR(28, "Account Identification 2"),

		});
	}
}