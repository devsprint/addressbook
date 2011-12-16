/**
 * 
 */
package com.devsprint.learning.agenda_service.resources;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.devsprint.learning.agenda_service.Contact;

/**
 * Decode a message body.
 * 
 * @author gciuloaica
 * 
 */
@Provider
@Consumes(ProtocolBufferMediaType.APPLICATION_PROTOCOL_BUFFER)
public class ContactProviderReader implements MessageBodyReader<Contact> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, javax.ws.rs.core.MediaType mediaType) {
		boolean isReadable = false;
		if (Contact.class.isAssignableFrom(type)) {
			isReadable = true;
		}
		return isReadable;
	}

	@Override
	public Contact readFrom(Class<Contact> type, Type genericType,
			Annotation[] annotations, javax.ws.rs.core.MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {

		return ContactDecoder.fromPerson(entityStream);

	}

}
