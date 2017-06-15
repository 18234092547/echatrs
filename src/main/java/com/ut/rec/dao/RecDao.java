package com.ut.rec.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ut.rec.entity.Record;


public interface RecDao{
	
	
	List<Record> getOneDayDetailByPage(@Param("yearMonthDay")String yearMonthDay, @Param("passNRecord")Integer passNRecord);
	
	
	List<Integer> getStatisInMonth(@Param("yearMonth")String yearMonth, @Param("spe")String spe);
	
	List<Integer> getStatisInYear(@Param("year")String year, @Param("spe")String spe);
	//返回以年为单位的数据(2007 - 2020)
	List<Integer> getStatisAll( @Param("spe")String spe);
	
	
	
	
	
}