package com.bayes.domain;

public class BayesList{
	public String lname;
	public double ltrait;
	public byte[] lnum;
	
	public BayesList(){};
	public BayesList(String lname, double ltrait, byte[] lnum){
		this.lname = lname ;
		this.ltrait = ltrait ;
		this.lnum = lnum ;
	}
	
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public double getLtrait() {
		return ltrait;
	}
	public void setLtrait(double ltrait) {
		this.ltrait = ltrait;
	}
	public byte[] getLnum() {
		return lnum;
	}
	public void setLnum(byte[] lnum) {
		this.lnum = lnum;
	}
	
}