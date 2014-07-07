package com.easystor.fileop.hdfs;

import java.util.HashMap;
import java.util.Map;

import com.antilope.openutils.base.DateUtils;
import com.antilope.openutils.protocol.http.HttpUtils;
import com.easystor.common.EasystorConstants;
import com.easystor.fileop.AbsRESTFileOprate;

public class RestGetOprate extends AbsRESTFileOprate{

	@Override
	public String execute() {
		String response = "";
		try {
			Map<String,String> params = new HashMap<String, String>();
			params.putAll(paraMap);
			params.remove("PATH");
			String path = paraMap.get("PATH");
			String getUrl = EasystorConstants.HADOOP_REST_HOME+path+"?"+params2String(params);
			response = HttpUtils.doGet(getUrl);
			System.out.println("["+DateUtils.sysTime+"]  HttpGet ·µ»Ø £º"+response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
