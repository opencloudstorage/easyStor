package com.easystor.fileop;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @author sh
 *
 */
public abstract class AbsRESTFileOprate  implements FileOprate{
	
	public Map<String,String> paraMap;

	public Map<String, String> getParaMap() {
		return paraMap;
	}

	public void setParaMap(Map<String, String> paraMap) {
		this.paraMap = paraMap;
	}
	
	public String params2String(Map<String,String> params){
		StringBuffer param = new StringBuffer();
		for(Iterator it=params.entrySet().iterator();it.hasNext();){
			Map.Entry en = (Entry) it.next();
			param.append(en.getKey());
			param.append("=");
			param.append(en.getValue());
			param.append("&");
		}
		return param.toString();
	}
}
