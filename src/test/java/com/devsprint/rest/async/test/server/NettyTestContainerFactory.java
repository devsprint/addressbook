/**
 * 
 */
package com.devsprint.rest.async.test.server;

import java.net.URI;

import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainer;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;

/**
 * Provide a TestContainerFactory implementation for Netty.
 * 
 * @author gciuloaica
 * 
 */
public class NettyTestContainerFactory implements TestContainerFactory {

	@SuppressWarnings("unchecked")
	@Override
	public Class<LowLevelAppDescriptor> supports() {
		return LowLevelAppDescriptor.class;
	}

	@Override
	public TestContainer create(final URI baseUri,
			final AppDescriptor appDescriptor) throws IllegalArgumentException {
		if (!(appDescriptor instanceof LowLevelAppDescriptor)) {
			throw new IllegalArgumentException(
					"The application descriptor must be an instance of LowLevelAppDescriptor");
		}

		return new NettyTestContainer(baseUri,
				(LowLevelAppDescriptor) appDescriptor);
	}

}
