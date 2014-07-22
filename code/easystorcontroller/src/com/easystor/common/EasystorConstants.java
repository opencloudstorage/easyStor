package com.easystor.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author sh
 *
 */
public class EasystorConstants {
	//后续增加至系统配置文件
	//HDFS 集群master地址
	public static String MASTER_IP = "master"; 
	public static int HDFS_PORT = 50070; 
	public static String HADOOP_REST_HOME = "http://"+MASTER_IP+":"+HDFS_PORT+"/webhdfs/v1/";
	public static Map<String,String> hostIPMap = new HashMap<String, String>(){{
		put("localhost", "172.16.128.106");
		put("slave1", "172.16.128.107");
		put("slave2", "172.16.128.109");
		put("slave3", "172.16.128.114");
	}};
}
