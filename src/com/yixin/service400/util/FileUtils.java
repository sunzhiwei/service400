package com.yixin.service400.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>�����:FileList</p>
 * <p>����ժҪ: </p>
 * <p>�޸���: </p>
 * <p>�޸�ԭ��: </p>
 * <p>�޸ļ��: </p>
 * <p>����޸�����: </p>
 * <p>Copyright: Copyright (c) 2013-6-7</p>
 * <p>Company: ���ν���</p>
 * @author guzi
 * @version 1.0.0
 */
public class FileUtils {

      /**
       * @function:�õ�ָ��Ŀ¼�µ��ļ�
       * @process:��ȷ���ж�,�õ��ļ�
       * @param path ָ��Ŀ¼
       * @return ��Ԫ��Ϊ�ļ�����ļ���(List<File>)
       */
      public static List<File> getFileList ( String path ) {
            return getFileList(path, "");
      }

      /**
       * @function:�õ�ָ��Ŀ¼��ָ����׺����ļ�
       * @process:��ȷ���ж�,���˵õ��ļ�
       * @param path ָ��Ŀ¼
       * @param aft ָ���ļ���׺��
       * @return ��Ԫ��Ϊ�ļ�����ļ���(List<File>)
       */
      public static List<File> getFileList ( String path, String aft ) {
            String upath = path.replaceAll("\\", "/");
            File file = new File(upath);
            List<File> list = new ArrayList<File>();
            if ( !file.exists() ) {
                  return list;
            }
            if ( file.isFile() ) {
                  list.add(file);
                  return list;
            }
            final String suffix = aft;
            File [] files = file.listFiles(new FileFilter() {
                  public boolean accept ( File pathname ) {
                        if ( pathname.getName().endsWith(suffix) ) {
                              return true;
                        }
                        return false;
                  }
            });
            Collections.addAll(list, files);
            return list;
      }

      /**
       * @function:�����ļ�(��)
       * @process:��ȷ���ж�,�ļ�Ŀ¼�ж�,�ļ�ֱ�Ӹ���,Ŀ¼ѭ�����Ʋ����ļ���
       * @param src Դ�ļ�(��)
       * @param dist Ŀ��Ŀ¼
       * @return �Ƿ��Ƴɹ�
       * @throws IOException
       */
      public static boolean copyFile ( String src, String dist ) {
            File srcFile = new File(src);
            if ( !srcFile.exists() ) {
                  return false;
            }
            File distFile = new File(dist);
            if ( !distFile.exists() ) {
                  distFile.mkdirs();
            }
            if ( !distFile.isDirectory() ) {
                  return false;
            }
            if ( srcFile.isDirectory() ) {
                  return copyDir(srcFile, distFile);
            }
            else {
                  return copyFile(srcFile, distFile);
            }
      }
      
      public static File copyFile ( String src, String dist ,int flag) {
          File srcFile = new File(src);
          if ( !srcFile.exists() ) {
                return null;
          }
          File distFile = new File(dist);
          if ( !distFile.exists() ) {
                distFile.mkdirs();
          }
          if ( !distFile.isDirectory() ) {
                return null;
          }
               return copyFile(srcFile, distFile,flag);
    }

      /**
       * @function:�����ļ�
       * @process:�ȸ�����ɾ��
       * @param src Դ�ļ�
       * @param dist Ŀ��Ŀ¼
       * @return �Ƿ�ɹ�
       */
      public static boolean cutFile ( String src, String dist ) {
            boolean b = FileUtils.copyFile(src, dist);
            if ( b ) {
                  return FileUtils.deleteFile(src);
            }
            return false;
      }

      /**
       * @function:ɾ���ļ�
       * @process:�ļ�ֱ��ɾ��,Ŀ¼ѭ��ɾ��
       * @param src Ҫɾ����ļ�
       * @return �Ƿ�ɹ�
       */
      public static boolean deleteFile ( String src ) {
            File file = new File(src);
            if ( !file.exists() ) {
                  return true;
            }
            if ( file.isFile() ) {
                  return file.delete();
            }
            if ( file.isDirectory() ) {
                  return deleteDir(file);
            }
            return false;
      }

      private static boolean deleteDir ( File file ) {
            File [] files = file.listFiles();
            if ( files == null || files.length == 0 ) {
                  return file.delete();
            }
            for ( File f : files ) {
                  if ( f.isFile() ) {
                        if ( !f.delete() ) {
                              return false;
                        }
                  }
                  else {
                        if ( !deleteDir(f) ) {
                              return false;
                        }
                  }
            }
            return file.delete();
      }

      private static boolean copyFile ( File src, File dist ) {
            if ( !dist.isDirectory() ) {
                  return false;
            }
            BufferedInputStream in = null;
            File file = null;
            BufferedOutputStream out = null;
            try {
                  in = new BufferedInputStream(new FileInputStream(src));
                  file = new File(dist, src.getName());
                  out = new BufferedOutputStream(new FileOutputStream(file));
            }
            catch ( FileNotFoundException e ) {
                  e.printStackTrace();
                  return false;
            }
            byte [] buff = new byte [1024];
            int leng = 0;
            try {
                  while ( ( leng = in.read(buff) ) != -1 ) {
                        out.write(buff, 0, leng);
                        out.flush();
                  }
                  in.close();
                  out.close();
            }
            catch ( IOException e ) {
                  e.printStackTrace();
                  file.delete();
                  return false;
            }
            return true;
      }
      
      private static File copyFile ( File src, File dist,int flag) {
          if ( !dist.isDirectory() ) {
                return null;
          }
          BufferedInputStream in = null;
          File file = null;
          BufferedOutputStream out = null;
          try {
                in = new BufferedInputStream(new FileInputStream(src));
                // 对生成的文件进行重命名
                Date date = new Date();
        		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHHmmss");
        		String filename="";
			    if (flag == 1)
				   filename = "工作组维护申请" + format.format(date) + ".xls";
			    else if(flag==2)
				   filename = "IVR维护申请" + format.format(date) + ".xls";
			    else
				   filename = "400号码申请" + format.format(date) + ".xls";
                file = new File(dist, filename);
//                file = new File(dist, src.getName());
                out = new BufferedOutputStream(new FileOutputStream(file));
          }
          catch ( FileNotFoundException e ) {
                e.printStackTrace();
                return null;
          }
          byte [] buff = new byte [1024];
          int leng = 0;
          try {
                while ( ( leng = in.read(buff) ) != -1 ) {
                      out.write(buff, 0, leng);
                      out.flush();
                }
                in.close();
                out.close();
          }
          catch ( IOException e ) {
                e.printStackTrace();
                file.delete();
                return null;
          }
          return file;
    }
      
      @SuppressWarnings("unused")
	private static File getCopyFile ( File src, File dist,int flag) {
          if ( !dist.isDirectory() ) {
                return null;
          }
          BufferedInputStream in = null;
          File file = null;
          BufferedOutputStream out = null;
          try {
                in = new BufferedInputStream(new FileInputStream(src));
                // 对生成的文件进行重命名
                Date date = new Date();
        		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHHmmss");
        		String filename="工作组维护申请"+format.format(date);
                file = new File(dist, filename);
                out = new BufferedOutputStream(new FileOutputStream(file));

          }
          catch ( FileNotFoundException e ) {
                e.printStackTrace();
                return null;
          }
          byte [] buff = new byte [1024];
          int leng = 0;
          try {
                while ( ( leng = in.read(buff) ) != -1 ) {
                      out.write(buff, 0, leng);
                      out.flush();
                }
                in.close();
                out.close();
          }
          catch ( IOException e ) {
                e.printStackTrace();
                file.delete();
                return null;
          }
          return file;
    }

      private static boolean copyDir ( File src, File dist ) {
            if ( !dist.isDirectory() ) {
                  return false;
            }
            File file = new File(dist, src.getName());
            file.mkdirs();
            File [] files = src.listFiles();
            for ( File f : files ) {
                  if ( f.isDirectory() ) {
                        if ( !copyDir(f, file) ) {
                              return false;
                        }
                  }
                  else {
                        if ( !copyFile(f, file) ) {
                              return false;
                        }
                  }
            }
            return true;
      }
      
    public static File getDistFile(int type){
    	File file=null;
    	String srcPath=Conf.getValue("template.file");
		String filePath = Conf.getValue("title.file");
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(filePath==null){
			filePath = format.format(date); // 取得服务器路径
		}else{
//			filePath = filePath + "/"+format.format(date);
			filePath = filePath + File.separator+format.format(date);
		}
		file=FileUtils.copyFile(srcPath, filePath,type);
		//System.out.println(file.getAbsolutePath());
    	return file;
    }
    
    public static File getDistFileByType(int type){
    	File file=null;
    	String srcPath;
    	if(type==1)
    		srcPath=Conf.getValue("workgroup_template.file");
    	else if(type==2)
    		srcPath=Conf.getValue("ivr_template.file");
    	else
    		srcPath=Conf.getValue("400num_template.file");
		String filePath = Conf.getValue("title.file");
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(filePath==null){
			filePath = format.format(date); // 取得服务器路径
		}else{
//			filePath = filePath + "/"+format.format(date);
			filePath = filePath + File.separator+format.format(date);
		}
		file=FileUtils.copyFile(srcPath, filePath,type);
		//System.out.println(file.getAbsolutePath());
    	return file;
    }
}
