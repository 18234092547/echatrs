package com.ut.rec.entity;

import java.util.List;

public class ChartModel {

	List<BarModel> list;
	
	String chartDataSum;
	
	

	public ChartModel(List<BarModel> list, String chartDataSum) {
		super();
		this.list = list;
		this.chartDataSum = chartDataSum;
	}

	public List<BarModel> getList() {
		return list;
	}

	public void setList(List<BarModel> list) {
		this.list = list;
	}

	public String getChartDataSum() {
		return chartDataSum;
	}

	public void setChartDataSum(String chartDataSum) {
		this.chartDataSum = chartDataSum;
	}
	
	

}
