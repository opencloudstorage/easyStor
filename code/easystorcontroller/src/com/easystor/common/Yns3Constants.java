package com.easystor.common;

public class Yns3Constants {
	//后续增加至系统配置文件
	public static String MASTER_IP = "master"; 
	public static int HDFS_PORT = 50070; 
	public static String HADOOP_REST_HOME = "http://"+MASTER_IP+":"+HDFS_PORT+"/webhdfs/v1/"; 
}
