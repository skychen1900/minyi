package com.skychen.minyi.batch;

import java.io.File;

public class Constant {
	
	public static final String OUTPUTFOLDER = System.getProperty("user.dir") + File.separator + "data" + File.separator;
	public static final String FILEEND = ".csv";
	public static final String CRLF = "\r\n";
	public static final String LF = "\n";
	public static final String OUTPUTFILENAME = "AdivceMinyi";

	public static final String MINYIURL = "http://minyi.runsky.com/";
	public static final String 详细页 = "http://minyi.runsky.com/?act=main";
	public static final String FROMDATE = "&dateline1=";
	public static final String TODATE = "&dateline2=";
	public static final String 分页信息 = "&page=";

}
