package com.yixin.service400.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;


/**<p>Description: </p>
 * <p>Copyright: Copyright (c) 2013-6-19</p>
 * <p>Company: ���ν���</p>
 * @author guzi
 * @version 1.0.0
 */
public class Excel {
      private HSSFWorkbook book;
      /**
       * ����Excel�ļ�/����Excel�ļ�
       */
      private String operate;
      /**
       * �ļ�·��
       */
      private String path;
      /**
       * ����Excel�ļ�
       */
      public static final String ANALYSIS = "00";
      /**
       * ����Excel�ļ�
       */
      public static final String CREATE = "01";
      /**
       * �����õ���ֵMap
       */
      public Map<Integer, Map<Integer, String>> valueMap;

      private Excel ( HSSFWorkbook book ) {
            this.book = book;
      }

      /**
       * @author guzi
       * @function �õ�����Excel��ʵ��
       * @process 
       * @param path Ҫ������excel �ļ�·��
       * @param flag �������:����Excel�ļ�/����Excel�ļ�
       * @return
       */
      public static Excel getExcelInstance ( String path, String flag ) {
            HSSFWorkbook book;
            File file = new File(path);
            if ( !file.getName().endsWith("xls") && !file.getName().endsWith("html")) {
                  return null;
            }
            if ( Excel.ANALYSIS.equalsIgnoreCase(flag) ) {
                  try {
                        if ( !file.isFile() ) {
                              return null;
                        }
                        book = new HSSFWorkbook(new FileInputStream(file));
                  }
                  catch ( IOException e ) {
                        return null;
                  }
            }
            else if ( Excel.CREATE.equalsIgnoreCase(flag) ) {
                  if ( !file.exists() ) {
                        new File(file.getParent()).mkdirs();
                        try {
                              file.createNewFile();
                        }
                        catch ( IOException e ) {
                              return null;
                        }
                  }
                  book = new HSSFWorkbook();
            }
            else {
                  return null;
            }
            Excel excel = new Excel(book);
            excel.setPath(path);
            excel.setOperate(flag);
            return excel;
      }

      /**
       * @author guzi
       * @function 
       * @process 
       * @param cell
       * @return
       */
      public boolean updateExcelCell ( Cell cell ) {
            HSSFSheet sheet = book.getSheetAt(0);
            HSSFRow row = sheet.getRow(cell.getRowIndex());
            HSSFCell ce = row.getCell(cell.getColumnIndex());
            ce.setCellValue(cell.getStringCellValue());
            try {
                  book.write(new FileOutputStream(new File(path)));
            }
            catch ( FileNotFoundException e ) {
                  return false;
            }
            catch ( IOException e ) {
                  return false;
            }
            return true;
      }

      /**
       * @author guzi
       * @function ���µ�Ԫ���ֵ
       * @process 
       * @param x �к�
       * @param y �к�
       * @param v ��Ԫ���ֵ
       * @return �Ƿ���³ɹ�
       */
      public boolean updateExcelCell ( int x, int y, Object v ) {
            HSSFSheet sheet = book.getSheetAt(0);
//            HSSFComment cellComment = sheet.getCellComment(5, 2);
//            cellComment.getColumn();
//            cellComment.getRow();
            HSSFRow row = sheet.getRow(x);
            HSSFCell cell = row.getCell(y);
            cell.setCellValue(v.toString());
            try {
                  book.write(new FileOutputStream(new File(path)));
            }
            catch ( FileNotFoundException e ) {
                  return false;
            }
            catch ( IOException e ) {
                  return false;
            }
            return true;
      }

      /**
       * @author guzi
       * @function ����Excel���õ������ϵ�ֵ
       * @process 
       * @param x �к�
       * @param y �к�
       * @return �����ϵ�ֵ
       */
      public String getValue ( int x, int y ) {
            if ( valueMap != null )
                  return valueMap.get(x).get(y);
            return null;
      }

      /**
       * @author guzi
       * @function �ڲ�����Excel�������µõ�������ϵ�ֵ
       * @process 
       * @param x �к�
       * @param y �к�
       * @return
       */
      public String getExcelValue ( int x, int y ) {
            HSSFSheet sheet = book.getSheetAt(0);
            HSSFRow row = sheet.getRow(x);
            HSSFCell cell = row.getCell(y);
            return getCellString(cell);
      }

      /**
       * @author guzi
       * @function ����Excel�ļ�
       * @process ��������һ��Sheet
       */
      public void analysisExcel () {
            analysisExcel("");
      }

      public Cell getExcelCell ( int x, int y ) {
            HSSFSheet sheet = book.getSheetAt(0);
            HSSFRow row = sheet.getRow(x);
            return row.getCell(y);
      }

      /**
       * @author guzi
       * @function ����Excel�ļ�
       * @param sheetName ����
       * @process ��������һ��Sheet
       */
      public void analysisExcel ( String sheetName ) {
            if ( operate.equalsIgnoreCase(CREATE) ) {
                  return;
            }
            valueMap = new HashMap<Integer, Map<Integer, String>>();
            HSSFSheet sheet = book.getSheet(sheetName);
            if ( sheet == null ) {
                  sheet = book.getSheetAt(0);
            }
            int rowNum = sheet.getLastRowNum();
            int count = 0;
            for ( int i = 0; i < rowNum; i++ ) {
                  HSSFRow row = sheet.getRow(i);
                  if ( row == null ) {
                        count++;
                        if ( count == 3 ) {
                              break;
                        }
                        continue;
                  }
                  short cellNum = row.getLastCellNum();
                  HashMap<Integer, String> rowMap = new HashMap<Integer, String>();
                  for ( int j = 0; j < cellNum; j++ ) {
                        HSSFCell cell = row.getCell(j);
                        rowMap.put(j, getCellString(cell));
                  }
                  valueMap.put(i, rowMap);
            }
      }

      /**
       * @function �õ�Cell��ֵ
       * @process 
       * @param cell
       * @return
       */
      private String getCellString ( HSSFCell cell ) {
            Object result = null;
            if ( cell != null ) {
                  //��Ԫ�����ͣ�Numeric:0,String:1,Formula:2,Blank:3,Boolean:4,Error:5
                  int cellType = cell.getCellType();
                  switch ( cellType ) {
                        case Cell.CELL_TYPE_STRING :
                              result = cell.getRichStringCellValue().getString();
                              break;
                        case Cell.CELL_TYPE_NUMERIC :
                              result = cell.getNumericCellValue();
                              break;
                        case Cell.CELL_TYPE_FORMULA :
                              result = null;//cell.getNumericCellValue();
                              break;
                        case Cell.CELL_TYPE_BOOLEAN :
                              result = cell.getBooleanCellValue();
                              break;
                        case Cell.CELL_TYPE_BLANK :
                              result = null;
                              break;
                        case Cell.CELL_TYPE_ERROR :
                              result = null;
                              break;
                        default :
                              System.out.println("ö������������");
                              break;
                  }
            }
            return result == null ? "" : result.toString();
      }

      /**
       * @author guzi
       * @function �õ�����Excel��õ���ֵMap
       * @process 
       * @return ����Excel��õ���ֵMap
       */
      public Map<Integer, Map<Integer, String>> getValueMap () {
            return valueMap;
      }

      /**
       * @author guzi
       * @function ����Excel�ļ�
       * @process 
       * @param table ���к�(��0��ʼ)ΪKey��map,ֵ ���Ե�Ԫ���(��0��ʼ)ΪKey�ĵ�Map,ֵ��String(������չ�ɶ���)
       * @return �޸ĺ��Excel�ļ�·��
       */
      public String createExcel ( Map<Integer, Map<Integer, String>> table ) {
            if ( operate.equalsIgnoreCase(ANALYSIS) ) {
                  return null;
            }
            HSSFSheet sheet = book.createSheet();
            Set<Entry<Integer, Map<Integer, String>>> set = table.entrySet();
            for ( Entry<Integer, Map<Integer, String>> entry : set ) {
                  Map<Integer, String> rowValue = entry.getValue();
                  HSSFRow row = sheet.createRow(entry.getKey());
                  Set<Entry<Integer, String>> entryCell = rowValue.entrySet();
                  for ( Entry<Integer, String> ent : entryCell ) {
                        String cellValue = ent.getValue();
                        HSSFCell cell = row.createCell(ent.getKey());
                        cell.setCellValue(cellValue);
                  }
            }
            try {
                  book.write(new FileOutputStream(new File(path)));
            }
            catch ( FileNotFoundException e ) {
                  return null;
            }
            catch ( IOException e ) {
                  return null;
            }
            return path;
      }
      
      public String analysisExcel_new( Map<Integer, Map<Integer, String>> table ) {
//          if ( operate.equalsIgnoreCase(ANALYSIS) ) {
//                return null;
//          }
//          HSSFSheet sheet = book.createSheet();
    	  HSSFSheet sheet = book.getSheetAt(0);
    	  // 生成一个样式
    	  HSSFCellStyle cellStyle = book.createCellStyle(); 
    	  //生成一个字体
          HSSFFont font=book.createFont();
          font.setFontName("微软雅黑"); // 名称
          font.setFontHeightInPoints((short) 10);//字体大小 
          cellStyle.setFont(font);
          cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 居中 
          cellStyle.setBorderBottom(CellStyle.BORDER_THIN); //下边框    
          cellStyle.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
          cellStyle.setBorderTop(CellStyle.BORDER_THIN);//上边框    
          cellStyle.setBorderRight(CellStyle.BORDER_THIN);//右边框 
//          cellStyle.setFillForegroundColor(HSSFColor.RED.index);;// 设置背景色    
//          cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
          Set<Entry<Integer, Map<Integer, String>>> set = table.entrySet();
          for ( Entry<Integer, Map<Integer, String>> entry : set ) {
                Map<Integer, String> rowValue = entry.getValue();
                HSSFRow row = sheet.createRow(entry.getKey());
                Set<Entry<Integer, String>> entryCell = rowValue.entrySet();
                int count=0;
                for ( Entry<Integer, String> ent : entryCell ) {
                      String cellValue = ent.getValue();
                      HSSFCell cell = row.createCell(ent.getKey());
                      cell.setCellValue(cellValue);
                      cell.setCellStyle(cellStyle);
                      count++;
                }
          }
          try {
                book.write(new FileOutputStream(new File(path)));
          }
          catch ( FileNotFoundException e ) {
                return null;
          }
          catch ( IOException e ) {
                return null;
          }
          return path;
    }
      
      public void productExcel(Map<Integer,Map<Integer,String>> table){
          Excel excel = Excel.getExcelInstance("E:/date.xls", Excel.CREATE);
          excel.createExcel(table);
      }

      public HSSFWorkbook getBook () {
            return book;
      }

      public void setBook ( HSSFWorkbook book ) {
            this.book = book;
      }

      public String getOperate () {
            return operate;
      }

      public void setOperate ( String operate ) {
            this.operate = operate;
      }

      public String getPath () {
            return path;
      }

      public void setPath ( String path ) {
            this.path = path;
      }

}
