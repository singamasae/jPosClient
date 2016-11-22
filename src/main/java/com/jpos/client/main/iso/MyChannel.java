package com.jpos.client.main.iso;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOException;

public class MyChannel extends BaseChannel {
	@Override
	protected void sendMessageLength(int len) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Length of outgoing message [int] = " + len);
		String l = Integer.toHexString(len);
		String lengthMsg = StringUtils.leftPad(l, 4, "0");
		System.out.println("Length of outgoing message [hex] = " + lengthMsg);
		System.out.println("Length of outgoing message [byte] = " + Arrays.toString(lengthMsg.getBytes()));
		serverOut.write(lengthMsg.getBytes());
	}

	@Override
	protected int getMessageLength() throws IOException, ISOException {
		// TODO Auto-generated method stub
		int l = 0;
		byte[] b = new byte[4];
		while (l == 0) {
			serverIn.readFully(b, 0, 4);
			System.out.println("Length of incoming message [byte] = " + Arrays.toString(b));
			System.out.println("Length of incoming message [hex] = " + new String(b));
			l = Integer.parseInt(new String(b), 16);
			System.out.println("Length of incoming message [int] = " + l);			
			if (l == 0) {
				serverOut.write(b);
				serverOut.flush();
			}
		}
		return l;
	}
}
