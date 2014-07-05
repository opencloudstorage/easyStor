package com.easystor.proxy;

import java.util.Map;

import com.easystor.fileop.AbsRESTFileOprate;

/**
 * hadoop REST 
 * @author sh
 * @date 2014/6/28
 */
public class HadoopRestProxy extends RestProxy{
	//输入参数字符�?
	private String params;
	//输出 http 响应
	private String response;
	
	private Map<String,String> paraMap;
	
	public String execute(){
		String op = paraMap.get("op");
		AbsRESTFileOprate restFileOp;
		try {
			restFileOp = (AbsRESTFileOprate) Class.forName(FileOprateMap.get(op)).newInstance();
			restFileOp.setParaMap(paraMap);
			response = restFileOp.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return response;
	}
	/**
	 * 参数解析
	 */
	public void anylizeParams(){
		//test=test&op=&
		String[] paramS = this.params.split("$");
		for(String s : paramS){
			String[] keyValue = s.split("=");
			paraMap.put(keyValue[0], keyValue[1]);
		}
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Map<String, String> getParaMap() {
		return paraMap;
	}
	public void setParaMap(Map<String, String> paraMap) {
		this.paraMap = paraMap;
	}
}
