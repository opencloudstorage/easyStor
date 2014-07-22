package com.yns.fileop;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.easystor.common.EasystorConstants;
import com.easystor.fileop.AbsRESTFileOprate;
import com.easystor.fileop.FileOprate;

/**
 * hadoop rest �ӿ�����
 * @author         :    xushiheng
 * @date           :    
 * @version        :    1.0 20120307 
 * @since          :    1.0 20120307
 *
 */
public abstract class HadoopRestOprate extends AbsRESTFileOprate implements FileOprate{
	/**
	 * ���������еĻ�����ΪIP��ַ
	 * @param datanodePutuRL master ����ʵ�ʴ洢·��
	 * @return ʵ���ύ��ַ
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
