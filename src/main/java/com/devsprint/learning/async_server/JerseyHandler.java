package com.devsprint.learning.async_server;

import java.net.URI;

import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpRequest;

import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.header.InBoundHeaders;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.WebApplication;

/**
 * Handle a client request.
 * 
 * @author gciuloaica
 * 
 */
public class JerseyHandler extends SimpleChannelUpstreamHandler {
	public static final String PROPERTY_BASE_URI = "com.devsprint.learning.async_server.baseUri";

	private final WebApplication application;
	private final String baseUri;

	public JerseyHandler(WebApplication application,
			ResourceConfig resourceConfig) {
		this.application = application;
		this.baseUri = (String) resourceConfig.getProperty(PROPERTY_BASE_URI);
	}

	@Override
	public void messageReceived(ChannelHandlerContext context, MessageEvent e)
			throws Exception {
		HttpRequest request = (HttpRequest) e.getMessage();

		String base = getBaseUri(request);
		final URI baseUri = new URI(base);
		final URI requestUri = new URI(base.substring(0, base.length() - 1)
				+ request.getUri());

		final ContainerRequest cRequest = new ContainerRequest(application,
				request.getMethod().getName(), baseUri, requestUri,
				getHeaders(request), new ChannelBufferInputStream(
						request.getContent()));

		application.handleRequest(cRequest, new JerseyResponseWriter(e.getChannel()));
	}

	private InBoundHeaders getHeaders(HttpRequest request) {
		InBoundHeaders headers = new InBoundHeaders();

		for (String name : request.getHeaderNames())
		{
			headers.put(name, request.getHeaders(name));
		}

		return headers;
	}

	private String getBaseUri(HttpRequest request) {
		if (baseUri != null) {
			return baseUri;
		}

		return "http://" + request.getHeader(HttpHeaders.Names.HOST) + "/";
	}

}
