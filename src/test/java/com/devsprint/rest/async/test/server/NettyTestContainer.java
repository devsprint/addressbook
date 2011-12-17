/**
 * 
 */
package com.devsprint.rest.async.test.server;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devsprint.rest.async.server.NettyServer;
import com.devsprint.rest.async.server.NettyServerFactory;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainer;

/**
 * @author gciuloaica
 * 
 */
public class NettyTestContainer implements TestContainer {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(NettyTestContainer.class.getName());

	private final URI baseUri;

	private final ResourceConfig resourceConfig;

	private final NettyServer nettyServer;

	public NettyTestContainer(URI baseUri, LowLevelAppDescriptor ad) {
		this.baseUri = baseUri;
		LOGGER.info("Creating low level http container configured at the base URI "
				+ this.baseUri);
		resourceConfig = ad.getResourceConfig();
		nettyServer = NettyServerFactory.create(resourceConfig, this.baseUri);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sun.jersey.test.framework.spi.container.TestContainer#getClient()
	 */
	@Override
	public Client getClient() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sun.jersey.test.framework.spi.container.TestContainer#getBaseUri()
	 */
	@Override
	public URI getBaseUri() {
		return this.baseUri;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sun.jersey.test.framework.spi.container.TestContainer#start()
	 */
	@Override
	public void start() {
		nettyServer.startServer();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sun.jersey.test.framework.spi.container.TestContainer#stop()
	 */
	@Override
	public void stop() {
		nettyServer.stopServer();

	}

}
