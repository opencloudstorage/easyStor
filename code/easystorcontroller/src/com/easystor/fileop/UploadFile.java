package com.easystor.fileop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.antilope.openutils.base.DateUtils;
import com.antilope.openutils.protocol.http.HttpUtils;
import com.easystor.common.Yns3Constants;

public class UploadFile extends AbsRESTFileOprate{
/**
 * 	Step 1: 
	curl -i -X PUT "http://<HOST>:<PORT>/webhdfs/v1/<PATH>?op=CREATE
    [&overwrite=<true|false>][&blocksize=<LONG>][&replication=<SHORT>]
    [&permission=<OCTAL>][&buffersize=<INT>]"
    
	Step 2:
	curl -i -X PUT -T <LOCAL_FILE> "http://<DATANODE>:<PORT>/webhdfs/v1/<PATH>?op=CREATE..."
	*/
	@Override
	public String execute() {
		String response = "";
		try {
			Map<String,String> params = new HashMap<String, String>();
			params.putAll(paraMap);
			params.remove("PATH");
			params.remove("LOCAL_FILE");
			
			String path = paraMap.get("PATH");
			String masterPutUrl = Yns3Constants.HADOOP_REST_HOME+path;
			String  datanodePutUrl= HttpUtils.doPutHeder(masterPutUrl, params,"Location");
			System.out.println("["+DateUtils.sysTime+"] master 返回  dataNode 存储路径 ："+datanodePutUrl);
			//请求master 获取应该上传的 datenode路劲
			String localFile = paraMap.get("LOCAL_FILE");
			response = HttpUtils.doPut(datanodePutUrl, null, localFile);
			System.out.println("["+DateUtils.sysTime+"] "+localFile+" 存储结果返回 ："+response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
