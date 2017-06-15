package com.ut.rec.entity;

import java.util.List;

public class RecordDetailModel {
	
	List<Record> list;
	
	int totalPages;
	
	

	public RecordDetailModel(List<Record> list, int totalPages) {
		super();
		this.list = list;
		this.totalPages = totalPages;
	}

	public List<Record> getList() {
		return list;
	}

	public void setList(List<Record> list) {
		this.list = list;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	

}
