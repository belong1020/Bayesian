package com.bayes.operate;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface OperateTxt {

	static OperateTxt OperateNum(String file1) throws  IOException {
		return null;
	}
	static String[][] OperateTraits(String file2) throws  IOException {
		return null;
	}
	String[] getName();
	byte[][] getNum();
}