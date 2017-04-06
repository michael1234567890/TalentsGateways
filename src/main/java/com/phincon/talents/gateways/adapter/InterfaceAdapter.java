package com.phincon.talents.gateways.adapter;

import java.util.List;
import java.util.Map;

public interface InterfaceAdapter {

	void sendNewData();
	void sendUpdatedData();
	void receive();
	void send(List<Map<String,Object>> listData);
}
