package com.ut.rec.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ut.rec.dao.RecDao;
import com.ut.rec.entity.BarModel;
import com.ut.rec.entity.Record;
import com.ut.rec.service.RecService;


@Service
public class RecServiceImpl implements RecService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RecDao recDao;
	
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BarModel> getEchartStatis(String date) {
		// TODO Auto-generated method stub
		List<BarModel> list = new ArrayList<>();
		String retString = "";
		BarModel bar1 = new BarModel();
		bar1.setName("normal");
		BarModel bar2 = new BarModel();
		bar2.setName("spec");
		try{
		
			//date解析
			//查询以月为单位
			if(date.indexOf("-") == -1){
				List<String> validMonth = null;
				List<Integer> validMonthStatis = null;
				
				
				validMonth = recDao.getValidMonth(date);
				validMonthStatis = recDao.getValidMonthStatis(date);
				List<Integer> normaList = null;
				normaList = this.fullList(validMonth, validMonthStatis, 12);
				validMonth = recDao.getValidSpeMonth(date);
				validMonthStatis = recDao.getValidSpeMonthStatis(date);
				List<Integer> specList = null;
				specList = this.fullList(validMonth, validMonthStatis, 12);
				
//				retString = addEchartFromat(normaList, specList);
				// 封装数据
				bar1.setData(normaList);
				bar2.setData(specList);
				list.add(bar1);
				list.add(bar2);
			}
			else{
				List<String> validDay = null;
				List<Integer> validDayStatis = null;
				
				
				validDay = recDao.getValidDay(date);
				validDayStatis = recDao.getValidDayStatis(date);
				List<Integer> normaList = null;
				normaList = this.fullList(validDay, validDayStatis, 31);
				
				
				
				validDay = recDao.getValidSpeDay(date);
				validDayStatis = recDao.getValidSpeDayStatis(date);
				List<Integer> specList = null;
				specList = this.fullList(validDay, validDayStatis, 31);
				
//				retString = addEchartFromat(normaList, specList);
				// 封装数据
				bar1.setData(normaList);
				bar2.setData(specList);
				list.add(bar1);
				list.add(bar2);
				
			//查询以日为单位	
				
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
		//返回值
		/*
		List<String> stringList = null;
		
		
		
		//String retString = "";
		String year = "2017";
		stringList = recDao.getValidMonth("2017");
		for(String t:stringList){
			retString += t + ",";
		}
		List<Integer> intList= recDao.getValidMonthStatis("2017");
		for(int i:intList)
			retString += String.valueOf(i) + ";";
		
		*/
		//return retString;
		//return "123";
		
		//return null;
	}


	@Override
	public String addEchartFromat(List<Integer> normaList, List<Integer> specList) {
		// TODO Auto-generated method stub
		
		String series = "";
	    series = "series:[ { name:'normal', type:'bar',"
				+ "data:[ ";
		
		
		
		
		String date1 = "", date2 = "";
		
		for(int i = 0; i < normaList.size(); i++){
			if(i > 0){
				date1 += ", ";
				date2 += ", ";
			}
			date1 += String.valueOf(normaList.get(i));
			date2 += String.valueOf(specList.get(i));
		
		}
		series = "series:[ { name:'normal', type:'bar',"
				+ "data:[ " + date1;
		
		//series += date1;
		series += "],}, "
				+ "{ name: 'spec', type: 'bar', "
				+ "data:[ " + date2 + "],"
				+ "} ]";
		
		
		
		return series;
	}


	@Override
	public List<Integer> fullList(List<String> indexList, List<Integer> valueList, Integer n) {
		// TODO Auto-generated method stub
		List<Integer> retList = new ArrayList<Integer>();
		try{
		
			for(int i = 0; i < n; i++)
				retList.add(0);
			
			
			for(int i = 0; i < indexList.size(); i++){
				String idx = "";
				idx = indexList.get(i);
				int ix = new Integer(idx)-1;
				retList.set(ix, valueList.get(i) );			
			}
			return retList;
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}


	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Record> getList(String yearMonthDay) {
		List<Record> retList = null;
		
		try{
			retList = recDao.getOneDayDetail(yearMonthDay);
			return retList;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//
		return null;
	}

}
