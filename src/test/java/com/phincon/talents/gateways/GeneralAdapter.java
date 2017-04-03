package com.phincon.talents.gateways;

import java.util.List;

public class GeneralAdapter<E> {
	protected String adapterName = "default";
	protected String dataSource = "self";
	protected String module = "";

	/*
	 * get data with modified date > last sent in sync and 
	 * ready to send 
	 */
	protected List<E> getData() {
		return null;
	}

	/*
	 * send data to third party 
	 */
	protected void sendToThirdParty() {
		if (this.dataSource.equals("salesforce"))
			sendToSalesforce();
		else if (this.dataSource.equals("spice"))
			sendToSpice();
		else
			sendToOthers();
	}

	/*
	 * send to spice
	 */
	private void sendToSpice() {
		// TODO Auto-generated method stub
		System.out.println("Send to spice");
	}

	/*
	 * send to other
	 */
	private void sendToOthers() {
		// TODO Auto-generated method stub
		System.out.println("Send to Others");
	}

	/*
	 * send to salesforce
	 */
	private void sendToSalesforce() {
		// TODO Auto-generated method stub
		System.out.println("Send to Salesforce");
	}

	protected void receiveFromThirdParty() {

	}

}
