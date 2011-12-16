package com.devsprint.learning.agenda_service;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.devsprint.learning.async_server.HttpAsyncServerChannelPipelineFactory;
import com.devsprint.learning.async_server.JerseyHandler;
import com.sun.jersey.api.container.ContainerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

/**
 * Start an Async rest server.
 * 
 */
public class AsyncRestServer {

	private static final String RESOURCES_PACKAGE = "com.devsprint.learning.agenda_service";
	private static final String BASE_URI = "http://localhost:8080/";

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
		return new HttpAsyncServerChannelPipelineFactory(
				getJerseyContainerInstance());
	}

	private static JerseyHandler getJerseyContainerInstance() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put(PackagesResourceConfig.PROPERTY_PACKAGES, RESOURCES_PACKAGE);
		props.put(JerseyHandler.PROPERTY_BASE_URI, BASE_URI);
		ResourceConfig resourceConfig = new PackagesResourceConfig(props);

		JerseyHandler jerseyHandler = ContainerFactory.createContainer(
				JerseyHandler.class, resourceConfig);
		return jerseyHandler;
	}

	protected static void stopServer() {
		// TODO Auto-generated method stub

	}
}
