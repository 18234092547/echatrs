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
public class RecServiceImpl implements RecService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RecDao recDao;

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BarModel> getEchartStatis(String date) {
		// TODO Auto-generated method stub
		List<BarModel> list = new ArrayList<>();
	
		BarModel bar1 = new BarModel();
		bar1.setName("normal");
		BarModel bar2 = new BarModel();
		bar2.setName("spec");
		try{
			List<Integer> normaList = null;
			List<Integer> specList = null;

			//查询年为单位
			if(date == "all"){
				normaList = recDao.getStatisAll(new String("1"));
				specList = recDao.getStatisAll("rc.spe");
				
			}//查询以月为单位
			else if(date.indexOf("-") == -1){	
				normaList = recDao.getStatisInYear(date, new String("1"));
				specList = recDao.getStatisInYear(date, "rc.spe");
			}
			//查询以日为单位
			else{
				normaList = recDao.getStatisInMonth(date, new String("1"));
				specList = recDao.getStatisInMonth(date, new String("rc.spe"));
			}		
			
			bar1.setData(normaList);
			
			bar2.setData(specList);
			
			list.add(bar1);
			
			list.add(bar2);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;

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
		
		return null;
	}


}
