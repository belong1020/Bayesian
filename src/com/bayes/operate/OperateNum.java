package com.bayes.operate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class OperateNum implements OperateTxt{
	
	public static String[] name;
	public static byte[][] num;
	
	public OperateNum() {}
	public OperateNum(String[] name2, byte[][] num2) {
		this.name = name2 ;
		this.num = num2 ;
	}
	public String[] getName(){
		return name;
	}
	public byte[][] getNum(){
		return num;
	}
	/** 处理num表 去掉全0全2行列 方法、
	 * @param file1
	 * @return
	 * @throws IOException
	 */
	public static OperateTxt OperateNum(String file1) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
        String line = br.readLine();
        ArrayList<String[]> liststr = new ArrayList<String[]>();
        while((line = br.readLine())!=null){
            String[] str = line.split("\t");
            StringBuffer sbs = new StringBuffer();
            for (int i = 1; i < str.length; i++) {
                sbs.append(str[i]);
            }
            String newstr = sbs.toString();
            sbs.delete(0, sbs.length()-1);		//释放buffer
            //System.out.println( sbs.toString());     del
            if( ! newstr.matches("[0,2]+")){
                liststr.add(str);
            }
        }
        String [][]  Array1 = new   String [liststr.size()][liststr.get(0).length];
        boolean[] bool = new boolean[liststr.get(0).length];
        bool[0] = true;
        for (int i = 0; i < liststr.size(); i++) {
            for (int j = 0; j < liststr.get(i).length; j++) {
                Array1[i][j] =  liststr.get(i)[j];
            }
        }
        for (int j = 1; j < liststr.get(0).length; j++) {		
            for (int i = 0; i < liststr.size(); i++) {
                //
                if(! Array1[i][j].equals("0") && !Array1[i][j].equals("2")){
                    bool[j] = true ; 
                    break;
                }
            }
        }
        int realcol=0;
        for(int i=1; i<bool.length; i++){
        	if(bool[i])
        		realcol++;
        }
        name = new String [liststr.size()];
        num = new byte [liststr.size()][realcol];
        for (int i = 0; i <  liststr.size(); i++) {
            int k = 0;
            name [i] = Array1[i][0];
            for (int j = 1; j < liststr.get(0).length; j++) {
                if(bool[j]){     //bool 列标识数
                    num[i][k] = Byte.valueOf(Array1[i][j]);
                    k++;
                }
            }
        }
        System.out.println("OperateNum   ok");
        br.close();
	    return new OperateNum(name, num);
    }
	

	/** 处理trait表 去掉NA行   方法、
	 * @param file1
	 * @return
	 * @throws IOException
	 */
	public static String[][] OperateTraits(String file2) throws IOException{
	        
	    	String[][] str2;        // 处理后带名小表
	        ArrayList<String[]> arrayList = new ArrayList<String[]>();
	        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));  //
	        String line ;  // 行数
	        while((line = in.readLine()) != null){   
	            String[] temp = line.split("\t");
	            arrayList.add(temp);
	        }
	        
	        str2 = (String [][])arrayList.toArray(new String[0][0]);
	        
	        int count=0;
	        for(int i = 0; i <  str2.length; i++){// null 不保存
	        
	            if(!(str2[i][1].equals("NA") || str2[i][1].equals("null") )){
	                System.arraycopy(str2[i], 0, str2[count], 0, str2[0].length);
	                count++;
	            }     
	        }
	        
	        System.out.println("OperateTraits   ok");
	        in.close();
	        return str2;

    }
	
	
}
