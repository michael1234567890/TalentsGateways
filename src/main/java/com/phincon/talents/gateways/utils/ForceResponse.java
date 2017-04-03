package com.phincon.talents.gateways.utils;

import java.util.List;
import java.util.Map;

public class ForceResponse {
	private int totalSize;
	private List<Map<String, Object>> records;
	private boolean done;

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public List<Map<String, Object>> getRecords() {
		return records;
	}

	public void setRecords(List<Map<String, Object>> records) {
		this.records = records;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
