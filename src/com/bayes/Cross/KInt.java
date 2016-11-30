package com.bayes.Cross;

import javax.swing.JOptionPane;

public class KInt {
	public static void scanf(int k, int iteration, int length){

		try{
			k =Integer.valueOf( JOptionPane.showInputDialog( "请输入交叉验证折数" ) );
			iteration =Integer.valueOf( JOptionPane.showInputDialog( "请输入bayes演算次数" ) ) ;
		if( iteration < 1 ){
			JOptionPane.showMessageDialog(null,"bayes演算次数值异常");System.exit(1);
		}
		if( k<=1 || k>length){
			JOptionPane.showMessageDialog(null,"k值异常");System.exit(1);
		}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null,"输入值不能为空");
			System.exit(1);
		}
		System.out.println(iteration);
		
	}
}
