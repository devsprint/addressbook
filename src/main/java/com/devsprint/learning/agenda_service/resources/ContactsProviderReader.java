/**
 * 
 */
package com.devsprint.learning.agenda_service.resources;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.devsprint.learning.agenda_service.Contact;

/**
 * Decodes a message body
 * 
 * @author gciuloaica
 * 
 */
@Provider
@Consumes(ProtocolBufferMediaType.APPLICATION_PROTOCOL_BUFFER)
public class ContactsProviderReader implements MessageBodyReader<List<Contact>> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, javax.ws.rs.core.MediaType mediaType) {
		boolean isReadable = false;
		if (List.class.isAssignableFrom(type)
				&& genericType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			Type[] actualTypeArgs = (parameterizedType.getActualTypeArguments());
			isReadable = (actualTypeArgs.length == 1 && actualTypeArgs[0]
					.equals(Contact.class));
		} else {
			isReadable = false;
		}
		return isReadable;
	}

	@Override
	public List<Contact> readFrom(Class<List<Contact>> type, Type genericType,
			Annotation[] annotations, javax.ws.rs.core.MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		return ContactDecoder.fromAddressBook(entityStream);
	}

}
