/**
 * 
 */
package com.devsprint.learning.agenda_service.resources;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.devsprint.learning.agenda_service.Contact;

/**
 * Encode internal data to protobuf.
 * @author gciuloaica
 * 
 */
@Provider
@Produces(ProtocolBufferMediaType.APPLICATION_PROTOCOL_BUFFER)
public class ContactsProviderWriter implements MessageBodyWriter<List<Contact>> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, javax.ws.rs.core.MediaType mediaType) {

		boolean isWritable = false;

		if (List.class.isAssignableFrom(type)
				&& genericType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			Type[] actualTypeArgs = (parameterizedType.getActualTypeArguments());
			isWritable = (actualTypeArgs.length == 1 && actualTypeArgs[0]
					.equals(Contact.class));
			
		} else {
			isWritable = false;
		}

		return isWritable;
	}

	@Override
	public long getSize(List<Contact> contacts, Class<?> type,
			Type genericType, Annotation[] annotations,
			javax.ws.rs.core.MediaType mediaType) {

		if (!contacts.isEmpty()) {
			int length = ContactEncoder.fromContacts(contacts).toByteArray().length;
			return length;
		}

		return 0;
	}

	@Override
	public void writeTo(List<Contact> contacts, Class<?> type,
			Type genericType, Annotation[] annotations,
			javax.ws.rs.core.MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {

		byte[] content = ContactEncoder.fromContacts(contacts).toByteArray();
		if (content.length > 0) {
			entityStream.write(content);
		}

	}
}
