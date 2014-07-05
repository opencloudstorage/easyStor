package com.easystor.server.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.easystor.proxy.HadoopRestProxy;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

public class YNSHttpServer {
    //启动服务，监听来自客户端的请求
	public static void httpserverService() throws IOException {
		HttpServerProvider provider = HttpServerProvider.provider();
		HttpServer httpserver =provider.createHttpServer(new InetSocketAddress(6666), 100);//监听端口6666,能同时接 受100个请求
		httpserver.createContext("/yns3_hdfs", new MyHttpHandler()); 
		httpserver.setExecutor(null);
		httpserver.start();
		System.out.println("server started");
	}
	
	//Http请求处理类
	public static class MyHttpHandler implements HttpHandler {
		public void handle(HttpExchange httpExchange) throws IOException {
			
			InputStream in = httpExchange.getRequestBody(); //获得输入流
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String temp = null;
			while((temp = reader.readLine()) != null) {
				System.out.println("client request:"+temp);
			}
			//参数转发
			HadoopRestProxy proxy = new HadoopRestProxy();
			proxy.setParams(temp);
			proxy.execute(); 
			String responseMsg = proxy.getResponse();
			httpExchange.sendResponseHeaders(200, responseMsg.length()); //设置响应头属性及响应信息的长度
			OutputStream out = httpExchange.getResponseBody();  //获得输出流
			out.write(responseMsg.getBytes());
			out.flush();
			httpExchange.close();                               
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		httpserverService();
	}
}
