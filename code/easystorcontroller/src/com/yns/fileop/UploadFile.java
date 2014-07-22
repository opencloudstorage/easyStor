package com.yns.fileop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.antilope.openutils.base.DateUtils;
import com.antilope.openutils.protocol.http.HttpUtils;
import com.easystor.common.EasystorConstants;

public class UploadFile extends HadoopRestOprate{
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
			
			params.put("op", "CREATE");
			String path = paraMap.get("PATH");
			String masterPutUrl = EasystorConstants.HADOOP_REST_HOME+path;
			String  datanodePutUrl= HttpUtils.doPutHeder(masterPutUrl, params,"Location");
			//请求master 获取应该上传的 datenode路劲
			String localFile = paraMap.get("LOCAL_FILE");
			datanodePutUrl = transDataNodePutUrl(datanodePutUrl)+"&user.name=hadoop&overwrite=true";
//			datanodePutUrl = datanodePutUrl.replace("slave2", "172.16.128.109").replace("localhost", "172.16.128.106").replace("slave1", "172.16.128.107").replace("slave3", "172.16.128.114")+"&user.name=hadoop&overwrite=true";
			System.out.println("["+DateUtils.sysTime+"] master 返回  dataNode 存储路径 ："+datanodePutUrl);
			response = HttpUtils.doPut(datanodePutUrl, null, localFile);
			System.out.println("["+DateUtils.sysTime+"] "+localFile+" 存储结果返回 ："+response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
