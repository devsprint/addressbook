/**
 * 
 */
package com.devsprint.rest.async.server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;

import org.jboss.netty.channel.ChannelPipelineFactory;

import com.sun.jersey.api.container.ContainerFactory;
import com.sun.jersey.api.core.ResourceConfig;

/**
 * Create NettyServer instances.
 * 
 * @author gciuloaica
 * 
 */
public final class NettyServerFactory {

	public static NettyServer create(ResourceConfig resourceConfig, URI baseUri) {
		JerseyHandler jerseyHandler = ContainerFactory.createContainer(
				JerseyHandler.class, resourceConfig);

		return new NettyServer(getPipelineFactory(jerseyHandler),
				getLocalSocket(baseUri));
	}

	private static SocketAddress getLocalSocket(URI baseUri) {
		return new InetSocketAddress(baseUri.getHost(), baseUri.getPort());
	}

	private static ChannelPipelineFactory getPipelineFactory(
			JerseyHandler jerseyHandler) {
		return new JaxRsServerChannelPipelineFactory(jerseyHandler);

	}

}
