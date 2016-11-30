package com.bayes.Cross;

import java.util.Arrays;
import java.util.Random;
import com.bayes.domain.Bayesian;

public class Upset {
	/**
	 *  打乱传入数据并重组
	 * @param bayes 
	 * @return bayes
	 */
	public static Bayesian upset(Bayesian bayes){
		/**
		 *  原理：
		 *  1、生成一个指定长度 num 数组，value 为  0到num.length-1 
		 *  2、打乱num数组
		 *  3、bayes.name[i] = bayes.name[num[i]]; num[i]值做name序号与name[i]交换
		 *   其他两表重复 第3步、
		 * @param bayes 
		 * @return bayes
		 */
		int[] num=new int[bayes.name.length];//定义一个数组用于以下操作
		for(int i=0;i<num.length;i++){
			num[i]=i;
		}//获取bayes.name长度个连续数据
		//System.out.println(Arrays.toString(num));
		//将获得的数据全部打散分布算法
		Random random=new Random();
		int index;
		for(int i=num.length-1;i>=0;i--){
			index=random.nextInt(num.length);
			int temp=num[i];
			num[i]=num[index];
			num[index]=temp;
		}
		//System.out.println(Arrays.toString(num));//显示将数组打散后的结果
		
		
		for(int i=0; i<num.length-1 ; i++){// Break.name
			String temp;
			temp = bayes.name[i];
			bayes.name[i] = bayes.name[num[i]];
			bayes.name[num[i]] = temp ;
		}
		
		for(int i=0; i<num.length ; i++){// Break.num
			byte[] temp ;
			temp = bayes.num[i];
			bayes.num[i] = bayes.num[num[i]];
			bayes.num[num[i]] = temp ;
		}
		
		for(int i=0; i<num.length ; i++){// Break.traits
			double temp;
			temp = bayes.trait[i];
			bayes.trait[i] = bayes.trait[num[i]];
			bayes.trait[num[i]] = temp ;
		}
		return bayes;
	}
}