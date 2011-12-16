package com.devsprint.learning.agenda_service;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * Start an Async rest server.
 * 
 */
public class AsyncRestServer {

	/**
	 * Starts a server, bind to a local port and add the shutdown hook.
	 * 
	 * @param args
	 *            - empty
	 */
	public static void main(String[] args) {
		// create server configuration
		ChannelPipelineFactory pipelineFactory = getChannelPipelineFactory();
		// start server
		startServer(pipelineFactory);

		// stop server
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				stopServer();
			}

		});
	}

	protected static void startServer(ChannelPipelineFactory pipelineFactory) {
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));

		bootstrap.setPipelineFactory(pipelineFactory);
		bootstrap.bind(new InetSocketAddress(8080));

	}

	private static ChannelPipelineFactory getChannelPipelineFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	protected static void stopServer() {
		// TODO Auto-generated method stub

	}
}
