package com.bayes.Cross;

import java.io.IOException;
import java.util.Arrays;

import com.bayes.domain.Bayesian;
import com.bayes.main.BayesaionGS;

public class Cross {

	/**  皮尔森交叉验证  
	 * @param bayes		Bayesian 将所需数组打包传入 -包括name[]，num[][]，trait[]
	 * @param k			k折验证   - 将数组分成k组
	 * @param pi， model，iteration，IOException		传入bayesian算法 所需值。
	 */
	public static void cross(Bayesian bayes ,int k,double pi,String model, int iteration) throws IOException{
		
		GroupImp gro = new GroupImp();
		int rel = 1;
		//int[][] allstorage_ref = new int [rel][1];
		//int[][] allstorage_inf = new int [rel][1];
		double[][] storage_ref = new double [rel][k];
		double[][] storage_inf = new double [rel][k];
		//Storage storage = new Storage();	处理相似度  均值  最大值  最小值
		double[] YRP ;
		double[] YIP ;
		double[] myBayes = null;
		double modelFit ;
		double accuracy ;
		
		for(int i=0; i<rel; i++){// 循环验证次数		
		//打乱
		bayes = Upset.upset(bayes);		
		//分组  处理第x组
		for(int x=1; x<=k; x++){	
			gro.group(bayes,k,x);
			YRP = new double[gro.GR.length] ;
			YIP = new double[gro.GI.length] ;
			myBayes = BayesaionGS.BayesaionGS(bayes.name,gro.GR,gro.YR,pi,model,iteration);  
			double sum = 0.0;
			for(int p=0; p<gro.GR.length; p++){
				for(int q=0; q<gro.GR[1].length; q++){
					sum += gro.GR[p][q]* myBayes[p];
					
				}YRP[p] = sum;
				sum=0.0;
			}sum=0.0;			//清零
			for(int p=0; p<gro.GI.length; p++){
				for(int q=0; q<gro.GI[1].length; q++){
					sum += gro.GI[p][q]* myBayes[q];
				}YIP[p] = sum;
				sum=0.0;
			}
			modelFit = Cor.cor(gro.YR,YRP);
			accuracy = Cor.cor(gro.YI,YIP);
			
			storage_ref[i][x-1] = modelFit ;
			storage_inf[i][x-1] = accuracy ;
		}
		/*
		for(int count=0; count<storage_ref[0].length; count++ ){
			System.out.println( storage_ref[0][count]  +"####");
		}*/
		double temp=0;
		for(int count=0; count<storage_inf.length; count++){
		for(int count1=0; count1<storage_inf[0].length; count1++ ){
			temp+=storage_inf[count][count1];
			System.out.print( storage_inf[count][count1] +", " );
		}System.out.println();
		}
		System.out.println( "mean-storage_inf:"+temp/storage_inf[0].length );
		
		
		}
	}
	
}
