package com.devsprint.learning.agenda_service.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.devsprint.learning.agenda_service.Contacts;
import com.devsprint.learning.agenda_service.InMemoryContactsStorage;
import com.devsprint.learning.agenda_service.PersistenceException;

@Path("/contacts/{contactId}")
public class ContactResoource {
	private final Contacts contacts = new Contacts(
			InMemoryContactsStorage.getInstance());
	@DELETE
	public Response deleteContact(@PathParam("contactId") String contactId)
			throws PersistenceException {
		contacts.remove(contactId);
		return Response.ok().build();
	}

	@GET
	@Produces(ProtocolBufferMediaType.APPLICATION_PROTOCOL_BUFFER)
	public Response getContact(@PathParam("contactId") String contactId)
			throws PersistenceException {
		return Response.ok(contacts.getContact(contactId)).build();
	}
}
