/**
 * 
 */
package com.devsprint.learning.async_server;

import com.sun.jersey.api.container.ContainerException;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.ContainerProvider;
import com.sun.jersey.spi.container.WebApplication;

/**
 * Service provider, such that Jersey could load it.
 * 
 * @author gciuloaica
 * 
 */
public class NettyContainerProvider implements ContainerProvider<JerseyHandler> {

	@Override
	public JerseyHandler createContainer(Class<JerseyHandler> type,
			ResourceConfig resourceConfig, WebApplication application)
			throws ContainerException {
		if (type != JerseyHandler.class)
			return null;

		return new JerseyHandler(application, resourceConfig);
	}

}
