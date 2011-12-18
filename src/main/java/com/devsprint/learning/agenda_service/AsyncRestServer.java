package com.devsprint.learning.agenda_service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.devsprint.jersey.api.container.netty.NettyServer;
import com.devsprint.jersey.api.container.netty.NettyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

/**
 * Start an Async rest server.
 * 
 */
public final class AsyncRestServer {

	private static final String RESOURCES_PACKAGE = "com.devsprint.learning.agenda_service";
	private static final int PORT = 8080;
	private static final String HOSTNAME = "localhost";

	private static NettyServer server;

	private AsyncRestServer() {

	}

	/**
	 * Starts a server, bind to a local port and add the shutdown hook.
	 * 
	 * @param args
	 *            - empty
	 */
	public static void main(final String[] args) {
		// create server configuration

		final StringBuilder baseUri = new StringBuilder("http://");
		baseUri.append(HOSTNAME).append(":").append(String.valueOf(PORT))
				.append("/");
		final ResourceConfig resourceConfig = getResourceConfiguration(baseUri
				.toString());

		// start server
		startServer(resourceConfig, URI.create(baseUri.toString()));

		// stop server
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				stopServer();
			}

		});
	}

	protected static void startServer(final ResourceConfig resourceConfig,
			final URI baseUri) {
		server = NettyServerFactory.create(resourceConfig, baseUri);
		server.startServer();
	}

	/**
	 * Create an instance of <code>JerseyHandler</code>, base on class-path
	 * scanning.
	 * 
	 * @param baseUri
	 *            - base uri
	 * @return JerseyHandler instance.
	 */
	private static ResourceConfig getResourceConfiguration(final String baseUri) {
		final Map<String, Object> props = new HashMap<String, Object>();
		props.put(PackagesResourceConfig.PROPERTY_PACKAGES, RESOURCES_PACKAGE);
		props.put(NettyServer.PROPERTY_BASE_URI, baseUri);
		return new PackagesResourceConfig(props);

	}

	protected static void stopServer() {
		server.stopServer();
	}
}
