package com.bayes.Cross;

import javax.swing.JOptionPane;

public class KInt {
	public static void scanf(int k, int iteration, int length){

		try{
			k =Integer.valueOf( JOptionPane.showInputDialog( "�����뽻����֤����" ) );
			iteration =Integer.valueOf( JOptionPane.showInputDialog( "������bayes�������" ) ) ;
		if( iteration < 1 ){
			JOptionPane.showMessageDialog(null,"bayes�������ֵ�쳣");System.exit(1);
		}
		if( k<=1 || k>length){
			JOptionPane.showMessageDialog(null,"kֵ�쳣");System.exit(1);
		}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null,"����ֵ����Ϊ��");
			System.exit(1);
		}
		System.out.println(iteration);
		
	}
}
