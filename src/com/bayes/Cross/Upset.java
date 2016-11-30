package com.bayes.Cross;

import java.util.Arrays;
import java.util.Random;
import com.bayes.domain.Bayesian;

public class Upset {
	/**
	 *  ���Ҵ������ݲ�����
	 * @param bayes 
	 * @return bayes
	 */
	public static Bayesian upset(Bayesian bayes){
		/**
		 *  ԭ��
		 *  1������һ��ָ������ num ���飬value Ϊ  0��num.length-1 
		 *  2������num����
		 *  3��bayes.name[i] = bayes.name[num[i]]; num[i]ֵ��name�����name[i]����
		 *   ���������ظ� ��3����
		 * @param bayes 
		 * @return bayes
		 */
		int[] num=new int[bayes.name.length];//����һ�������������²���
		for(int i=0;i<num.length;i++){
			num[i]=i;
		}//��ȡbayes.name���ȸ���������
		//System.out.println(Arrays.toString(num));
		//����õ�����ȫ����ɢ�ֲ��㷨
		Random random=new Random();
		int index;
		for(int i=num.length-1;i>=0;i--){
			index=random.nextInt(num.length);
			int temp=num[i];
			num[i]=num[index];
			num[index]=temp;
		}
		//System.out.println(Arrays.toString(num));//��ʾ�������ɢ��Ľ��
		
		
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