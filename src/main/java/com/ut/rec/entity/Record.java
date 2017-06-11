package com.ut.rec.entity;

import java.util.Date;

public class Record {
	

	private int id;
	private String name;
	private String type;
	private String sign;
	private String mode;
	private String c1;
	private String c2;
	private String c3;
	private Date recTime;
	private int spe;
	private String featureDep;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getC1() {
		return c1;
	}
	public void setC1(String c1) {
		this.c1 = c1;
	}
	public String getC2() {
		return c2;
	}
	public void setC2(String c2) {
		this.c2 = c2;
	}
	public String getC3() {
		return c3;
	}
	public void setC3(String c3) {
		this.c3 = c3;
	}
	public Date getRecTime() {
		return recTime;
	}
	public void setRecTime(Date recTime) {
		this.recTime = recTime;
	}
	public int getSpe() {
		return spe;
	}
	public void setSpe(int spe) {
		this.spe = spe;
	}
	public String getFeatureDep() {
		return featureDep;
	}
	public void setFeatureDep(String featureDep) {
		this.featureDep = featureDep;
	}
	
	
}
