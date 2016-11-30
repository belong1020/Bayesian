package com.bayes.Cross;

import java.io.IOException;  
import java.util.ArrayList;  
import java.util.List;  


/** Cor 
 * @author Asus
 *
 */
public class Cor {
	
	/** cor 函数分子部分计算函数
	 * @param YR
	 * @param YRP
	 * @return
	 */
	public static double calcuteNumerator(double[] YR,double[] YRP){  
        double result =0.0;  
        double xAverage = 0.0;  
        double temp = 0.0;  
          
        int xSize = YR.length;  
        for(int x=0;x<xSize;x++){  
            temp += YR[x];  
        }
        xAverage = temp/xSize;  
        double yAverage = 0.0;  
        temp = 0.0;  
        int ySize = YRP.length;  
        for(int x=0;x<ySize;x++){  
            temp += YRP[x];  
        }
        yAverage = temp/ySize;  
        //double sum = 0.0;  
        for(int x=0;x<xSize;x++){  
            result+=(YR[x]-xAverage)*(YRP[x]-yAverage);  
        }  
        return result;  
    }  
	
	/** cor 函数分子部分计算函数
	 * @param YR
	 * @param YRP
	 * @return
	 */
	public static double calculateDenominator(double[] YR,double[] YRP){  
        double standardDifference = 0.0;  
        int size = YR.length;  
        double xAverage = 0.0;  
        double yAverage = 0.0;  
        double xException = 0.0;  
        double yException = 0.0;  
        double temp = 0.0;  
        for(int i=0;i<size;i++){  
            temp += YR[i];  
        }
        xAverage = temp/size;   
        temp = 0.0;
        for(int i=0;i<size;i++){  
            temp += YRP[i];  
        }
        yAverage = temp/size;   
        for(int i=0;i<size;i++){  
            xException += Math.pow(YR[i]-xAverage, 2);  
            yException += Math.pow(YRP[i]-yAverage, 2);  
        }  
        //calculate denominator of   
        return standardDifference = Math.sqrt(xException*yException);  
    } 
	
	
    /**	cor函数 ---- 数组相关性检验 
     * @param YR
     * @param YRP
     * @return
     * @throws IOException
     */
    public static double cor(double[] YR,double[] YRP) throws IOException{
    	
    	double CORR = 0.0;
        double numerator = calcuteNumerator(YR,YRP);
        double denominator = calculateDenominator(YR, YRP);    
        CORR = numerator/denominator;  
        //System.out.println("We got the result by Calculating:");  
        //System.out.printf("CORR = "+CORR/0.42640143271122083);
                
		return CORR;  
    }
    
/*
    public static void main(String [] args) throws IOException{
    	double[] a={1,1.5,2,2.5,3,3.5};
    	double[] b={1,1.5,2,2.5,3,3.5};
    	double[] a1={1,2,3,4,5};
    	double[] b1={1,3,2,5,4};
    	double[] c1={1,2,3,4,5};
    	
    	for(int i=0;i<a.length;i++){
    		System.out.print( a [i]+",");
    	}
    	System.out.println( "" );
    	for(int i=0;i<b.length;i++){
    		System.out.print( b [i]+",");
    	}
    	
    	System.out.print(Cor.cor(a, b));
    }    
*/
}  