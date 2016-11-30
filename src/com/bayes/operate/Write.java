package com.bayes.operate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Write {

	public static void main(String[] args) throws IOException {
		// FileOutputStream fs = new FileOutputStream(new File("D:\\text.txt"));
		String path = "D:\\2015student\\javalassoA\\text.txt";
		FileOutputStream out = new FileOutputStream(path);
		out.write("Ê±¼ä".getBytes("utf-8"));
		
		
		out.close();

	}

	public static void outvalueToString(double[] out_value, String[] name) throws UnsupportedEncodingException, IOException {
		
		ArrayList l1=new ArrayList() ;
		String path = "D:\\2015student\\javalassoA\\text.csv";
		FileOutputStream out = new FileOutputStream(path);
		
		for (int i = 0; i < out_value.length; i++) {
			out.write(name[i].getBytes("utf-8"));
			out.write(",".getBytes("utf-8"));
			//String st = String.valueOf(out_value[i]);
			out.write( String.valueOf(out_value[i]).getBytes("utf-8"));
			out.write("\r\n".getBytes("utf-8"));
			 //+ "\t".getBytes("utf-8") + out_value[i] + "\r\n".getBytes("utf-8")
		}
		out.flush();
		out.close();
		
	}
	
}