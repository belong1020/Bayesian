package com.bayes.domain;

import javax.swing.JOptionPane;

public class Bayesian {

	

	public String[]   name;        // 大表
	public double[]   trait;      // 小表
	public byte[][]   num;
	
	
    public void setInfo(String[] name,double[] trait,byte[][] num){
    	this.name = name;
    	this.num = num;
    	this.trait = trait;
    }
    public String[] getName() {
		return name;
	}
	public void setName(String[] name) {
		this.name = name;
	}
	public double[] getTraits() {
		return trait;
	}
	public void setTraits(double[] traits) {
		this.trait = traits;
	}
	public byte[][] getNum() {
		return num;
	}
	public void setNum(byte[][] num) {
		this.num = num;
	}   
    
}
