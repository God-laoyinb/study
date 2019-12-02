package com.yrg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import sun.net.InetAddressCachePolicy;

/**
 * NIO 服务端
 * @author 74578
 *
 */
public class NioServer {
	/**
	 * 启动
	 * @throws IOException 
	 */
	public void start() throws IOException {
		/**
		 * 1.创建selector
		 */
		Selector selector = Selector.open();
		
		/**
		 * 2.通过SersverSocketChannel通道
		 */
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		/**
		 * 3.为Channel绑定监听端口
		 */
		
		serverSocketChannel.bind(new InetSocketAddress(8000));
		
		/**
		 * 4.设置Channel为非阻塞模式
		 */
		serverSocketChannel.configureBlocking(false);
		/**
		 * 5.将Channel注册到Selector上，监听连接事件
		 */
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("服务器启动成功");
		
		/**
		 * 6.循环等待新接入的连接
		 */
		
		/**
		 *7. 根据就绪状态，调用对应方法处理业务逻辑
		 */
	}
	/**
	 * 接入事件处理器
	 */
	private void acceptHandler() {
		/**
		 * 如果是接入事件，创建socketChannel
		 */
		
		/**
		 * 将socketChannel设置为非阻塞工作模式
		 */
	}
	/**
	 * 可读事件处理器
	 */
	private void readHandler() {
		
	}
	/**
	 * 主方法
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
}
