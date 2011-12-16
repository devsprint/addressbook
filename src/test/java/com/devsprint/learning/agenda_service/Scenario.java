/**
 * 
 */
package com.devsprint.learning.agenda_service;

import java.net.URI;
import java.net.URISyntaxException;

import com.devsprint.learning.agenda_service.resources.ContactProviderReader;
import com.devsprint.learning.agenda_service.resources.ContactProviderWriter;
import com.devsprint.learning.agenda_service.resources.ContactsProviderReader;
import com.devsprint.learning.agenda_service.resources.ContactsProviderWriter;
import com.devsprint.learning.agenda_service.resources.ProtocolBufferMediaType;
import com.devsprint.learning.agenda_service.resources.ServiceError;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

/**
 * Create scenarios for testing.
 * 
 * @author gciuloaica
 * 
 */
public class Scenario extends JerseyTest {

	private static final String CONTACTS_PATH = "/contacts";
	private Status status;
	private static ClientConfig clientConfig;
	static {
		clientConfig = new DefaultClientConfig(ContactProviderReader.class,
				ContactProviderWriter.class, ContactsProviderReader.class,
				ContactsProviderWriter.class, ServiceError.class);
	}

	public Scenario() {
		super(new WebAppDescriptor.Builder(
				"com.devsprint.learning.agenda_service.resources")
				.clientConfig(clientConfig).build());

	}

	Scenario getContacts() throws UniformInterfaceException, URISyntaxException {
		ClientResponse response = get(CONTACTS_PATH);
		status = response.getClientResponseStatus();
		return this;
	}

	Scenario addContact(Contact contact) {
		ClientResponse response = post(CONTACTS_PATH, contact);
		status = response.getClientResponseStatus();
		return this;
	}

	Scenario removeContact(ContactId contactId) {
		ClientResponse response = delete(CONTACTS_PATH, contactId);
		status = response.getClientResponseStatus();

		return this;
	}

	Scenario getContact(ContactId contactId) throws UniformInterfaceException, URISyntaxException {
		ClientResponse response = get(CONTACTS_PATH, contactId);
		status = response.getClientResponseStatus();

		return this;
	}

	Scenario updateContact() {
		return this;
	}

	public Status getStatus() {
		return status;
	}

	private ClientResponse get(String contactsPath)
			throws UniformInterfaceException, URISyntaxException {
		WebResource webResource = resource();
		ClientResponse response = webResource.uri(new URI(contactsPath)).get(
				ClientResponse.class);
		return response;
	}

	private ClientResponse get(String contactsPath, ContactId contactId)
			throws UniformInterfaceException, URISyntaxException {
		WebResource webResource = resource();
		ClientResponse response = webResource.path(
				contactsPath + "/" + contactId.getId()).get(
				ClientResponse.class);
		return response;
	}

	private ClientResponse post(String contactsPath, Contact contact) {
		WebResource webResource = resource();
		ClientResponse response = webResource.path(contactsPath)
				.type(ProtocolBufferMediaType.APPLICATION_PROTOCOL_BUFFER)
				.post(ClientResponse.class, contact);
		return response;
	}

	private ClientResponse delete(String contactsPath, ContactId contactId) {
		WebResource webResource = resource();
		ClientResponse response = webResource.path(
				contactsPath + "/" + contactId.getId()).delete(
				ClientResponse.class);
		return response;

	}

}
