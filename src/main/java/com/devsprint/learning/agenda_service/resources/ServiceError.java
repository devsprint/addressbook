/**
 * 
 */
package com.devsprint.learning.agenda_service.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.devsprint.learning.agenda_service.PersistenceException;

/**
 * Wrap a PersistenceException into a specific HTTP status.
 * 
 * @author gciuloaica
 * 
 */
@Provider
public class ServiceError implements ExceptionMapper<PersistenceException> {

	@Override
	public Response toResponse(PersistenceException exception) {
		return Response.status(Response.Status.SERVICE_UNAVAILABLE)
				.entity(exception.getMessage()).type("text/plain").build();
	}

}
