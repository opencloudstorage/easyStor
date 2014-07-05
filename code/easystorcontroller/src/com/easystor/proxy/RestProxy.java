package com.easystor.proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * Rest 
 * @author sh
 * @date 2014/6/28
 */
public abstract class RestProxy extends AbsNetProxy{
	public static Map<String,String> FileOprateMap = new HashMap(){{
		 put("UPLOAD", "com.yns.fileop.UploadFile");
		 put("GET", "com.yns.fileop.UploadFile");
		 put("OPEN", "com.yns.fileop.RestGetOprate");
		 put("DELETE", "com.yns.fileop.RestDeleteOprate");
		 put("MKDIRS", "com.yns.fileop.RestPutOprate");
		 put("RENAME", "com.yns.fileop.UploadFile");
		 put("GETFILESTATUS", "com.yns.fileop.UploadFile");
		 put("LISTSTATUS", "com.yns.fileop.UploadFile");
		 put("GETCONTENTSUMMARY", "com.yns.fileop.UploadFile");
		 put("GETFILECHECKSUM", "com.yns.fileop.UploadFile");
		 put("GETHOMEDIRECTORY", "com.yns.fileop.UploadFile");
		 put("SETPERMISSION", "com.yns.fileop.UploadFile");
		 put("SETOWNER", "com.yns.fileop.UploadFile");
		 put("SETREPLICATION", "com.yns.fileop.UploadFile");
		 put("SETTIMES", "com.yns.fileop.UploadFile");
		 put("GETDELEGATIONTOKEN", "com.yns.fileop.UploadFile");
		 put("GETDELEGATIONTOKENS", "com.yns.fileop.UploadFile");
		 put("RENEWDELEGATIONTOKEN", "com.yns.fileop.UploadFile");
		 put("CANCELDELEGATIONTOKEN", "com.yns.fileop.UploadFile");
		 put("CONCAT", "com.yns.fileop.RestPostOprate");
	}};
}
