/**
 * 
 */
package com.devsprint.learning.agenda_service.resources;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.devsprint.learning.agenda_service.Contact;

/**
 * Convert a contact to a AddressBookProtos.Person
 * 
 * @author gciuloaica
 * 
 */
@Provider
@Produces(ProtocolBufferMediaType.APPLICATION_PROTOCOL_BUFFER)
public class ContactProviderWriter implements MessageBodyWriter<Contact> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, javax.ws.rs.core.MediaType mediaType) {

		boolean isWriteable = false;
		if (Contact.class.isAssignableFrom(type)) {
			isWriteable = true;
		}

		return isWriteable;
	}

	@Override
	public long getSize(Contact contact, Class<?> type, Type genericType,
			Annotation[] annotations, javax.ws.rs.core.MediaType mediaType) {

		return ContactEncoder.fromContact(contact).toByteArray().length;

	}

	@Override
	public void writeTo(Contact contact, Class<?> type, Type genericType,
			Annotation[] annotations, javax.ws.rs.core.MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {

		entityStream.write(ContactEncoder.fromContact(contact).toByteArray());

	}

}
