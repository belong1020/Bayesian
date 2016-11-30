package com.bayes.operate;

import java.util.ArrayList;
import com.bayes.domain.Bayesian;
import com.bayes.domain.BayesList;

public class Compare {

	/** 封装变量，传入list操作
	 *  便于确定保留长度   灵活性大 
	*/	
    /** 差异化处理 保留表间同名行 方法、
     * @param str1 传入大表
     * @param str2 传入小表
     * @return
     */
    public static Bayesian Compare(String[] name1, byte[][] num1, String[][] str2){
    	
    	Bayesian bayes = new Bayesian() ;
    	//创建封装单位变量   
    	double[] trait ;		
    	String[] name ;
    	byte[][] num ;
    	ArrayList<BayesList> l1 = new ArrayList<BayesList>();
    	
    	//两表合成   大表循环
    	for(int i=0 ; i< name1.length ; i++){    
    		//小表循环
    		for(int j=0 ; j< str2.length ; j++){
    			//名相同 且 一方名 不为空时 继续
    			if(name1[i].equals(str2[j][0])&&!name1[j].equals(null) ){
    				
    				BayesList bl = new BayesList();
    				bl.lname = str2[j][0];
    				bl.ltrait = Double.valueOf(str2[j][1]);
    				//int k=0;
    				bl.lnum = new byte[num1[0].length];
    				for(int count=0; count<num1[0].length; count++){
    					bl.lnum[count] = num1[i][count] ;
    				}
    				l1.add(bl);
    				break;
                }
    		}  
    	}
    	trait = new double[l1.size()];
    	name = new String [l1.size()];
    	num = new byte [l1.size()][num1[0].length];
    	
    	for(int i = 0 ; i< l1.size() ; i++){
 
 			name[i] = l1.get(i).lname;
    		trait[i] = l1.get(i).ltrait;
 			num[i] = l1.get(i).lnum;
    		//System.out.println(name[i]+" "+trait[i]+" "+num[i][1]);
    	}
    	
    	l1.clear();
    	bayes.setInfo(name, trait, num);
    	System.out.println("Compare  ok");
   	return  bayes;
	}
}
