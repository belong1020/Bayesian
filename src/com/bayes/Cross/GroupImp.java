package com.bayes.Cross;

import com.bayes.domain.Bayesian;
  
/**
 *  K折循环检验   矩阵分组部分
 *  
 * @author Asus
 *
 */
public class GroupImp implements Group {  
	/**
	 *  GR	大表训练组
	 *  YR	大表测试组
	 *  GI	小表训练组
	 *  YI	小表测试组  
	 * @author Asus
	 */
	byte[][] GR;
	double[] YR;
	byte[][] GI;
	double[] YI;
	
	public void group(Bayesian bayes,int k,int x){//k 折验证，x 循环次数  取出第几块
		if((bayes.num.length%k) == 0){
			trainCV(bayes,k,x);//训练
			testCV(bayes,k,x);//测试
		}else{
			trainCV(bayes,k,x,true);
			testCV(bayes,k,x,true);
		}	
	}
	
	/** 数据学习 ( 数据可整分 )
	 * @param bayes
	 * @param k
	 * @param x
	 */
	public void trainCV(Bayesian bayes , int k,int x){
		//首尾两组直接copy
		//其他情况前后分别copy
		//
		int length = bayes.num.length/k;	//单折长度
		GR = new byte[bayes.num.length-length][bayes.num[1].length-1];
		YR = new double[bayes.num.length-length];
		
		if(x==1){
			System.arraycopy(bayes.num, length, GR, 0, bayes.num.length-length);
			System.arraycopy(bayes.trait, length, YR, 0, bayes.num.length-length);
		} else if(x==k){
			System.arraycopy(bayes.num, 0, GR, 0, bayes.num.length-length);
			System.arraycopy(bayes.trait, 0, YR, 0, bayes.num.length-length);
		} else {
			System.arraycopy(bayes.num, 0, GR, 0, (x-1)*length);
			System.arraycopy(bayes.trait, 0, YR, 0, (x-1)*length);
			System.arraycopy(bayes.num, x*length, GR, (x-1)*length, bayes.num.length-x*length);
			System.arraycopy(bayes.trait, x*length, YR, (x-1)*length, bayes.num.length-x*length);
		}
	}
	
	/** 数据测试 ( 数据可整分 )
	 * @param bayes
	 * @param k
	 * @param x
	 */
	public void testCV(Bayesian bayes , int k,int x){
		
		int length = bayes.num.length/k;
		GI = new byte[length][bayes.num[1].length];
		YI = new double[length];
		
		System.arraycopy(bayes.num, (x-1)*length, GI, 0, length);
		System.arraycopy(bayes.trait, (x-1)*length, YI, 0, length);
	}
	
	/** 数据学习 ( 数据不可整分 )
	 * @param bayes
	 * @param k
	 * @param x
	 */
	public void trainCV(Bayesian bayes , int k , int x , boolean bool){
		
		int length ;
		int i = (bayes.num.length%k);
		
		//判断bayes.num.length%k 与x 位置关系、 >=x则GI为长矩阵 反之短矩阵、
		if(i >= x ){
			length = bayes.num.length/k +1 ;
			i=0;
		}else{
			length = bayes.num.length/k ;
		}
		GR = new byte[bayes.num.length-length][bayes.num[1].length];
		YR = new double[bayes.num.length-length];
		
		if(x==1){
			System.arraycopy(bayes.num, length, GR, 0, bayes.num.length-length);
			System.arraycopy(bayes.trait, length, YR, 0, bayes.num.length-length);
		} else if(x==k){
			System.arraycopy(bayes.num, 0, GR, 0, bayes.num.length-length);
			System.arraycopy(bayes.trait, 0, YR, 0, bayes.num.length-length);
		} else {
			
			System.arraycopy(bayes.num, 0, GR, 0, (x-1)*length+i);
			System.arraycopy(bayes.trait, 0, YR, 0, (x-1)*length+i);
			System.arraycopy(bayes.num, x*length+ i, GR, (x-1)*length+i, bayes.num.length-x*length- i);
			System.arraycopy(bayes.trait, x*length+ i, YR, (x-1)*length+i, bayes.num.length-x*length- i);
		}
	}
	
	/** 数据测试 ( 数据不可整分 )
	 * @param bayes
	 * @param k
	 * @param x
	 */
	public void testCV(Bayesian bayes , int k , int x , boolean bool){
		
		int length ;
		int i= bayes.num.length%k ;
		//判断bayes.num.length%k 与x 位置关系、 >=0则GI为长矩阵 反之短矩阵、
		//长矩阵是 默认长度为+1 无需补齐、 短矩阵时 需+i补齐 
		if(i >= x ){
			length = bayes.num.length/k+1 ;
			i=0;
		}else{
			length = bayes.num.length/k;
		}
		GI = new byte[length][bayes.num[1].length];
		YI = new double[length];
		if( x==1 ){
			System.arraycopy(bayes.num, 0, GI, 0, length);
			System.arraycopy(bayes.trait, 0, YI, 0, length);
		}else if( x==k ){
			System.arraycopy(bayes.num, bayes.num.length-length , GI, 0, length);
			System.arraycopy(bayes.trait, bayes.num.length-length , YI, 0, length);
		}else {
			System.arraycopy(bayes.num, (x-1)*length+ i , GI, 0, length);
			System.arraycopy(bayes.trait, (x-1)*length+ i , YI, 0, length);
		}
	}
	
/*
 public static void main(String[] args) {  
	  
	 byte[][] num = {{1,2,3},{2,2,3},{3,2,3},{4,2,3},{5,2,3},{6,2,3},{7,2,3},{8,2,3},{1,2,3},{2,2,3}};
	 double[] trait = {1,2,3,4,5,6,7,8,1,2};
	 Bayesian bayes = new Bayesian();
	 bayes.num = num ;
	 bayes.trait = trait ;
	 int k = 6;
	 
	 for(int x=1 ; x<=k; x++){
		 Group.group(bayes, k, x);
		 System.out.println("x---"+x);
		 System.out.println("GR--"); 
		 for(int i=0; i<GR.length; i++){
			 System.out.println(GR[i][0]+","+GR[i][1]+","+GR[x][2]+",");
		 }System.out.println();
		 System.out.println("GI--"); 
		 for(int i=0; i<GI.length; i++){
			 System.out.println(GI[i][0]+","+GI[i][1]+","+GI[i][2]+",");
			 }System.out.println(); 
			 System.out.println("YR--"); 
		 for(int i=0; i<YR.length; i++){
			 System.out.println(YR[i]);
			 }System.out.println();
			 System.out.println("YI--"); 
		 for(int i=0; i<YI.length; i++){
			 System.out.println(YI[i]);
			 }System.out.println(); 
		 
	 }
	 */
	 /*
	 GR=new byte[8][3];
	 System.arraycopy(bayes.num, (x-1)*length, GI, 0, length);
	 for(int i=0; i<GR.length; i++){
		 System.out.println(GR[i][0]+","+GR[i][1]+","+GR[i][2]+",");
	 }System.out.println(); 
	 */
}
  
	