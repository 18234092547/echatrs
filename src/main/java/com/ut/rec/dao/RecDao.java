package com.ut.rec.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ut.rec.entity.Record;


public interface RecDao{
	
	//look the year;
	List<String> getValidMonth(@Param("year")String year);
	List<Integer> getValidMonthStatis(@Param("year")String year);
	
	List<String> getValidSpeMonth(@Param("year")String year);
	List<Integer> getValidSpeMonthStatis(@Param("year")String year);
	
	
	List<String> getValidDay(@Param("yearMonth")String yearMonth);
	List<Integer> getValidDayStatis(@Param("yearMonth")String yearMonth);
	
	List<String> getValidSpeDay(@Param("yearMonth")String yearMonth);
	List<Integer> getValidSpeDayStatis(@Param("yearMonth")String yearMonth);
	
	List<Record> getOneDayDetail(@Param("yearMonthDay")String yearMonthDay);
	
	//@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	
}