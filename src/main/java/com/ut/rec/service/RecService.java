package com.ut.rec.service;

import java.util.List;

import com.ut.rec.entity.BarModel;
import com.ut.rec.entity.Record;


public interface RecService {
	List<BarModel> getEchartStatis(String date, Integer[] countAll);
	

	
	List<Record> getList(String yearMonthDay, String currentPage);
	
	
}
