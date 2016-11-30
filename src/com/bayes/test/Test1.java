package com.bayes.test;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.bayes.Cross.Cross;
import com.bayes.domain.Bayesian;
import com.bayes.main.BayesaionGS;
import com.bayes.operate.Compare;
import com.bayes.operate.OperateNum;
import com.bayes.operate.OperateTxt;
import com.bayes.operate.Write;

public class Test1 {

	public static void main(String [] args) throws IOException{
		String file1path = "d:/2015student/javalassoA/mdp_numeric.txt";
		String file2path = "d:/2015student/javalassoA/mdp_trait.txt";
		
		String model = "Cpi" ;	//    AB , C , Cpi
		int pi=0;			// pi=0 for Bayes A and pi = (0,1)for Bayes B
		int numMHIter=1;	// set 1 for Bayes A and 5 or larger for Bayes B
		int k ;				// k�۽�����֤ 
		int iteration ; 
		double[] effect = null;
		
		OperateTxt ot = OperateNum.OperateNum(file1path);			//�����
		String[] name = ot.getName();
		byte[][] num = ot.getNum();
		String[][] str2 = OperateNum.OperateTraits(file2path);		//С����
		Bayesian bayes = Compare.Compare(name, num, str2);				//�������
		long begin = System.currentTimeMillis();
		
		k = 5;
		//scanfK(bayes.num.length);						//����kֵ 
		iteration = 200;
		//scanfIteration();						//����iterationֵ    
		//Cross.cross(bayes, k, pi, model, iteration);		//������֤
		
		effect=BayesaionGS.BayesaionGS(bayes.name,bayes.num,bayes.trait,pi,model,iteration);
		double[] out_value = info(effect, bayes);			//��bayes����������� 
		Write.outvalueToString(out_value, bayes.name);			//��� out_value ���
		long end = System.currentTimeMillis();
		System.out.println(end-begin+"---time");
		System.out.println("bayesian is over");
	}

	/**	��ʼ�ֶ�����k��ֵ
	 * @param length
	 * @return
	 */
	public static int scanfK(int length){	
		int k = 0 ;
		try{
			k =Integer.valueOf( JOptionPane.showInputDialog( "�����뽻����֤����" ) );
		if( k<=1 || k>length){
			JOptionPane.showMessageDialog(null,"kֵ�쳣");System.exit(1);
		}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null,"����ֵ����Ϊ��");
			System.exit(1);
		}
		return k ;
	}
	/**	��ʼ�ֶ�����iterationֵ
	 * @return
	 */
	public static int scanfIteration(){			
		int iteration = 0;
		try{
			iteration =Integer.valueOf( JOptionPane.showInputDialog( "������bayes�������" ) ) ;
		if( iteration < 1 ){
			JOptionPane.showMessageDialog(null,"bayes�������ֵ�쳣");System.exit(1);
		}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null,"����ֵ����Ϊ��");
			System.exit(1);
		}
		return iteration ;
	}
	/** �� bayesian ���ɽ���������㡣
	 * @param effect    bayes������
	 * @param bayes    bayes����
	 * @return
	 */
	public static double[] info(double[] effect,Bayesian bayes){
		double[] out_value = new double[bayes.num.length] ; ;
		for(int i=0; i<bayes.num.length; i++){
			   double sum = 0;
			   for(int j=0; j<bayes.num[0].length; j++){
				   sum += bayes.num[i][j] * effect[j];	
			   }out_value[i]=sum;		   
		   }
		return out_value;
	}
	/** ��� out_value ���
	 * @param out_value
	 * @param name
	 */
	public static void outvalueToString(double[] out_value, String[] name){
		for(int i = 0; i<out_value.length ; i++){
			System.out.println(name[i]+", "+out_value[i]);
		}
	}

}
