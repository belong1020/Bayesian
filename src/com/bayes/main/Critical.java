package com.bayes.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
import java.lang.Math;
public class Critical {
    public static double critical(double k, double alpha)
	{
		double kMinus1 = k;
		double oneMinusAlpha = 1 - alpha;
		double temp1 = (double)2/(9 * kMinus1);
		double temp2 = Math.sqrt(temp1);
		// To estimate our critical value we need to use the inverse normal function.
		// This takes a probability (in the CDF for N(0,1)) and returns the z value
		// (i.e. the number of standard deviations that produces that probability).  I
		// got the code from the Web (see CDF_Normal.java for more details).
		double zOneMinusAlpha = CDF_normal.xnormi(oneMinusAlpha);
		temp2 = temp2 * zOneMinusAlpha;
		temp1 = 1 - temp1 + temp2;
		temp1 = Math.pow(temp1,3);
		temp1 = temp1 * kMinus1;
		return temp1;
	}
    public static double rchisq(int i,double df){
        return critical(df,Math.random());
        
    }
    /*
    public static void main(String [] args){
              
        double y;
        
        y=Math.random();
        System.out.println(y);
        y=Math.random();
        System.out.println( critical(1,y)  );
        y=Math.random();
        System.out.println( critical(1,y)  );
        System.out.println( critical(7,0.995)  );
        System.out.println( critical(8,0.995)  );
        System.out.println( critical(9,0.995)  );
        System.out.println( critical(10,0.995)  );
        System.out.println( critical(11,0.995)  );
        System.out.println( rchisq(1,15)  );
        
    }
     */  
    
}