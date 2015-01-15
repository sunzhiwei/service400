package com.yixin.service400.test;

/**
 * 递归函数
 */
import java.io.*;

public class Recursive {

	public static void main(String[] args) {
		File f = new File("D:/svn");
//		viewFile(f, 0);
		System.out.println(fibonacci(3));
		System.out.println(method(10));
		System.out.println(isHuiWen("abcba"));
	}

	// 打印指定文件夹下的所有文件
	// 参数是文件和文件的等级
	private static void viewFile(File f, int level) {
		String str = "";
		File[] fs = f.listFiles();
		for (int i = 0; i < fs.length; i++) {
			str = "";
			for (int j = 0; j < level; j++) {
				str = str + "    ";
			}
			System.out.println(str + fs[i].getName());
			if (fs[i].isDirectory()) {
				viewFile(fs[i], level + 1);
			}
		}
	}

//  Fibonacci,菲波拉契数列
//	斐波那契数列指的是这样一个数列 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233，377，610，987，1597，2584，4181，6765，10946，17711，28657，46368
//	特别指出：第0项是0，第1项是第一个1。
//	这个数列从第二项开始，每一项都等于前两项之和。
	private static int fibonacci(int f) {
		if (f <= 0) {
			return 0;
		}
		if (1 == f || 2 == f) {
			return 1;
		}else
		{
			return fibonacci(f - 1) + fibonacci(f - 2);
		}
		
	}

	//计算n!
	public static int method(int n) {
		if (n == 1) {
			return 1;
		} else {
			return n * (method(n - 1));
		}
	}

	//计算回文
	public static boolean isHuiWen(String s) {
		if (s == null) {
			return false;
		}
		if (s.length() == 0 || s.length() == 1) {
			return true;
		}
		char first = s.charAt(0);
		char last = s.charAt(s.length() - 1);
		if (first != last) {
			return false;
		} else {
			return isHuiWen(s.substring(1, s.length() - 1));
		}
	}
}
