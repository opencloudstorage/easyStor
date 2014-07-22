package com.yns.fileop;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.easystor.common.EasystorConstants;
import com.easystor.fileop.AbsRESTFileOprate;
import com.easystor.fileop.FileOprate;

/**
 * hadoop rest 接口配置
 * @author         :    xushiheng
 * @date           :    
 * @version        :    1.0 20120307 
 * @since          :    1.0 20120307
 *
 */
public abstract class HadoopRestOprate extends AbsRESTFileOprate implements FileOprate{
	/**
	 * 翻译连接中的机器名为IP地址
	 * @param datanodePutuRL master 返回实际存储路径
	 * @return 实际提交地址
	 */
	public String transDataNodePutUrl(String datanodePutuRL){
		for(Iterator it = EasystorConstants.hostIPMap.entrySet().iterator(); it.hasNext();){
			Map.Entry<String, String> en = (Entry<String, String>) it.next();
			String hostName = en.getKey();
			String ip = en.getValue();
			datanodePutuRL = datanodePutuRL.replace(hostName, ip);
		}
		return datanodePutuRL;
	}
}
