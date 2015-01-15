package com.yixin.service400.util;

import java.util.Comparator;

import com.yixin.service400.bean.TService400Privilege;


public class ComparatorImpl implements Comparator<TService400Privilege>{
	
	@Override
	public int compare(TService400Privilege o1, TService400Privilege o2) {
		if(o1.getSubsequence()>o2.getSubsequence())return 1;
		else if(o1.getSubsequence()<o2.getSubsequence())return -1;
		else if(o1.getSubsequence()==o2.getSubsequence())return 0;
		return 0;
	}

}
