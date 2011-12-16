package com.devsprint.learning.async_server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;

import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseWriter;

/**
 * Write the response
 * 
 * @author gciuloaica
 * 
 */
public class JerseyResponseWriter implements ContainerResponseWriter {

	private final Channel channel;
	private HttpResponse response;

	public JerseyResponseWriter(Channel channel) {
		this.channel = channel;
	}

	@Override
	public OutputStream writeStatusAndHeaders(long contentLength,
			ContainerResponse containerResponse) throws IOException {
		response = new DefaultHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.valueOf(containerResponse.getStatus()));

		for (Map.Entry<String, List<Object>> e : containerResponse
				.getHttpHeaders().entrySet()) {
			List<String> values = new ArrayList<String>();
			for (Object v : e.getValue())
				values.add(ContainerResponse.getHeaderValue(v));
			response.setHeader(e.getKey(), values);
		}

		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		response.setContent(buffer);
		return new ChannelBufferOutputStream(buffer);

	}

	@Override
	public void finish() throws IOException {
		channel.write(response).addListener(ChannelFutureListener.CLOSE);

	}

}
