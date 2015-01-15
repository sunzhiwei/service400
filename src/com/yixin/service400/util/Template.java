package com.yixin.service400.util;

import java.util.Map;


/**<p>Description: </p>
 * <p>Copyright: Copyright (c) 2013-7-22</p>
 * <p>Company: ���ν���</p>
 * @author guzi
 * @version 1.0.0
 */
public class Template {
      public static void main ( String [] args ) {
//            Excel excel = Excel.getExcelInstance("E:/date.xls", Excel.CREATE);
//            //excel.analysisExcel();
//            Map<Integer,String> head=new HashMap<Integer,String>();
//            head.put(0, "序号");
//            head.put(1, "组名");
//            head.put(2, "开始工作时间");
//            head.put(3, "结束工作时间");
//            head.put(4, "坐席溢出");
//            head.put(5, "非工作时溢出");
//            head.put(6, "非工作时语音");
//            head.put(7, "待操作");
//            Map<Integer,String> map=new HashMap<Integer,String>();
//            map.put(0, "1");
//            map.put(1, "第一列值");
//            map.put(2, "第二列值");
//            Map<Integer,Map<Integer,String>> table=new HashMap<Integer,Map<Integer,String>>();
//            table.put(0, head);
//            table.put(1, map);
//            table.put(2, map);
//            excel.createExcel(table);
            /*Cell cell = excel.getExcelCell(1, 1);
            if ( HSSFDateUtil.isCellDateFormatted(cell) ) {
                  Date value = cell.getDateCellValue();
                  System.out.println(value);
            }*/
          String path="D:/workgroup_template.xls";
    	  exceltest(path);
      }
      
      public static void exceltest(String path){
    	  Excel excel = Excel.getExcelInstance(path, Excel.ANALYSIS);
    	  excel.updateExcelCell(1, 1,"test");
      }
      
      public void excel(Map<Integer,Map<Integer,String>> table){
    	  Excel excel = Excel.getExcelInstance("D:/temp/workgroup/workgroupapp.xls", Excel.CREATE);
          excel.createExcel(table);
      }
      
      public void excelIVR(Map<Integer,Map<Integer,String>> table){
    	  Excel excel = Excel.getExcelInstance("D:/temp/ivr/ivrapp.xls", Excel.CREATE);
          excel.createExcel(table);
      }
      
      public void excel(Map<Integer,Map<Integer,String>> table,String path){
    	  Excel excel = Excel.getExcelInstance(path, Excel.CREATE);
          excel.createExcel(table);
      }
      
      public void excel_400num(Map<Integer,Map<Integer,String>> table,String path){
    	  Excel excel = Excel.getExcelInstance(path, Excel.ANALYSIS);
          excel.analysisExcel_new(table);
      }
}
