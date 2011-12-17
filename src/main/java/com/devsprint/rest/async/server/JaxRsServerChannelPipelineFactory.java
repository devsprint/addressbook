package com.devsprint.rest.async.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import static org.jboss.netty.channel.Channels.pipeline;

/**
 * Build server pipeline factory.
 * 
 * @author gciuloaica
 * 
 */
public class JaxRsServerChannelPipelineFactory implements
		ChannelPipelineFactory {

	private final JerseyHandler jerseyHandler;

	public JaxRsServerChannelPipelineFactory(
			final JerseyHandler jerseyHandler) {
		this.jerseyHandler = jerseyHandler;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = pipeline();
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("jerseyHandler", jerseyHandler);
		return pipeline;
	}

}