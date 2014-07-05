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
    //�������񣬼������Կͻ��˵�����
	public static void httpserverService() throws IOException {
		HttpServerProvider provider = HttpServerProvider.provider();
		HttpServer httpserver =provider.createHttpServer(new InetSocketAddress(6666), 100);//�����˿�6666,��ͬʱ�� ��100������
		httpserver.createContext("/yns3_hdfs", new MyHttpHandler()); 
		httpserver.setExecutor(null);
		httpserver.start();
		System.out.println("server started");
	}
	
	//Http��������
	public static class MyHttpHandler implements HttpHandler {
		public void handle(HttpExchange httpExchange) throws IOException {
			
			InputStream in = httpExchange.getRequestBody(); //���������
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String temp = null;
			while((temp = reader.readLine()) != null) {
				System.out.println("client request:"+temp);
			}
			//����ת��
			HadoopRestProxy proxy = new HadoopRestProxy();
			proxy.setParams(temp);
			proxy.execute(); 
			String responseMsg = proxy.getResponse();
			httpExchange.sendResponseHeaders(200, responseMsg.length()); //������Ӧͷ���Լ���Ӧ��Ϣ�ĳ���
			OutputStream out = httpExchange.getResponseBody();  //��������
			out.write(responseMsg.getBytes());
			out.flush();
			httpExchange.close();                               
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		httpserverService();
	}
}
