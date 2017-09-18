package com.phincon.talents.gateways.adapter;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface InterfaceAdapter {

	void sendNewData();
	void sendUpdatedData();
	void receive(String query,boolean isInit);
	void send(List<Map<String,Object>> listData, boolean isAckSend);
	void sendDataAckSync();
	void updateAckSyncStatus(boolean status,String string);
	void updateAckSyncStatus(boolean status,Set<String> string);
}
