/**
 * 
 */
package com.devsprint.rest.async.server;

import java.net.SocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Netty based server.
 * 
 * @author gciuloaica
 * 
 */
public final class NettyServer {

	private static final Logger LOG = LoggerFactory
			.getLogger(NettyServer.class);

	static final ChannelGroup ALL_CHANNELS = new DefaultChannelGroup(
			"jersey_netty_server");

	private final ChannelPipelineFactory pipelineFactory;
	private final ServerBootstrap bootstrap;
	private final SocketAddress localSocket;

	NettyServer(ChannelPipelineFactory pipelineFactory,
			SocketAddress localSocket) {
		this.pipelineFactory = pipelineFactory;
		this.localSocket = localSocket;
		this.bootstrap = buildBootstrap();

	}

	public void startServer() {
		LOG.info("Starting server....");
		Channel serverChannel = bootstrap.bind(localSocket);
		ALL_CHANNELS.add(serverChannel);
	}

	public void stopServer() {
		LOG.info("Stopping server....");
		ChannelGroupFuture future = ALL_CHANNELS.close();
		future.awaitUninterruptibly();
		bootstrap.getFactory().releaseExternalResources();
		ALL_CHANNELS.clear();

	}

	private ServerBootstrap buildBootstrap() {
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));

		bootstrap.setPipelineFactory(pipelineFactory);
		return bootstrap;
	}

}
