package com.yixin.service400.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


public class ZipFileUtil {

	private final static int BUFFER = 2048000;

	public static boolean zipFile(String zipFileName, String[] inputFiles) {
		boolean test = true;
		try {
			BufferedInputStream origin = null;
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
					new FileOutputStream(zipFileName)));
			byte data[] = new byte[BUFFER];
			String url = Conf.getValue("title.file");
			for (String path : inputFiles) {
				File file = new File(url, path);
				if (file.exists()) {
					FileInputStream fi = new FileInputStream(file);
					origin = new BufferedInputStream(fi, BUFFER);
					ZipEntry entry = new ZipEntry(file.getName());
					out.putNextEntry(entry);
					int count;
					while ((count = origin.read(data, 0, BUFFER)) != -1) {
						out.write(data, 0, count);
					}
					origin.close();
				}
			}
			out.close();
		} catch (Exception e) {
			test = false;
			e.printStackTrace();
		}
		return test;
	}
}
