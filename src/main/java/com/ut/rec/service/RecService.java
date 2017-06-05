package com.ut.rec.service;

import java.util.List;

import com.ut.rec.entity.BarModel;
import com.ut.rec.entity.Record;


public interface RecService {
	List<BarModel> getEchartStatis(String date);
	
	String addEchartFromat(List<Integer> normaList, List<Integer> specList);
	
	List<Integer> fullList(List<String> indexList, List<Integer> valueList, Integer n);
	
	
	List<Record> getList(String yearMonthDay);
	
	
}
