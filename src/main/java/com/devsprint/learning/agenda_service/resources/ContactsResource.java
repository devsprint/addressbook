/**
 * 
 */
package com.devsprint.learning.agenda_service.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.devsprint.learning.agenda_service.Contact;
import com.devsprint.learning.agenda_service.Contacts;
import com.devsprint.learning.agenda_service.InMemoryContactsStorage;
import com.devsprint.learning.agenda_service.PersistenceException;

/**
 * Exposed resource.
 * 
 * @author gciuloaica
 * 
 */
@Path("/contacts")
public class ContactsResource {
	private UriInfo uriInfo;

	private final Contacts contacts = new Contacts(
			InMemoryContactsStorage.getInstance());

	@Context
	public final void setUriInfo(UriInfo info) {
		this.uriInfo = info;
	}

	@GET
	@Produces(ProtocolBufferMediaType.APPLICATION_PROTOCOL_BUFFER)
	public List<Contact> getContacts() throws PersistenceException {
		return contacts.getContacts();
	}

	@POST
	@Consumes(ProtocolBufferMediaType.APPLICATION_PROTOCOL_BUFFER)
	public Response addContact(Contact contact) throws PersistenceException {
		contacts.add(contact);
		URI createdUri = createNewResource(contact.getContactId().getId());
		Response response = Response.created(createdUri).build();
		return response;

	}

	

	private URI createNewResource(String id) {
		UriBuilder ub = uriInfo.getRequestUriBuilder();
		return ub.path(id).build();
	}

}
