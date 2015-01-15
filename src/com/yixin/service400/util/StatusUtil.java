package com.yixin.service400.util;

public class StatusUtil {

	public final static String CONST_APPLICATIONFORM_STATUS_UNCHECK="未审核";
	
	public final static String CONST_APPLICATIONFORM_STATUS_CHECK="已审核";
	
	public final static String CONST_APPLICATIONFORM_STATUS_UNDONE="未完成";
	
	public final static String CONST_APPLICATIONFORM_STATUS_DONE="已完成";
	
	public final static String CONST_ISNOT_IS="是";
	
	public final static String CONST_ISNOT_NOT="否";
	
	public final static Integer CONST_WORKGROUP_STATUS_ADD=4;
	
	public final static Integer CONST_WORKGROUP_STATUS_UPDATE=5;

	public final static Integer CONST_WORKGROUP_STATUS_DELETE=6;

	public final static Integer CONST_WORKGROUP_STATUS_SENDMAIL=9;
	
	public final static Integer CONST_WORKGROUP_STATUS_CLOSELOOP=10;
	
	public final static Integer CONST_WORKGROUP_STATUS_SENDMAILDELETE=11;
	
	public final static Integer CONST_FILE_STATUS_NOBIHUAN=12;

	public final static Integer CONST_FILE_STATUS_BIHUAN=10;
	
	public static String getStatusValue(int status) throws Exception {
		String Statusvalue = "";
		if ((Integer) status != null) {
			Statusvalue = status == 0 ? "未审核"
					: status == 1 ? "已审核 "
							: status == 2 ? "未完成 "
									: status == 3 ? "已完成 "
											: status == 7 ? "是 "
													: status == 8 ? "否 "
															 : "wrong";
															
		} else
			throw new Exception("无法识别的状态值！！！");
		if ("wrong".equals(Statusvalue))
			throw new Exception("错误的状态值，请审查！！！");
		return Statusvalue;
	}
	
}
