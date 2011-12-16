package com.devsprint.learning.agenda_service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;

public class AddressBookTest {

	private static final String CONTACT_ID = "1";
	private Scenario scenario;

	@Before
	public void setUp() throws Exception {
		scenario = new Scenario();
		scenario.setUp();
	}

	@After
	public void tearDown() throws Exception {
		scenario.tearDown();
		((InMemoryContactsStorage) InMemoryContactsStorage.getInstance())
				.clenaup();
	}

	@Test
	public void testAddContact() {
		Contact contact = createContact();
		assertEquals(ClientResponse.Status.CREATED, scenario
				.addContact(contact).getStatus());
	}

	@Test
	public void testGetContacts() throws Exception {
		assertEquals(ClientResponse.Status.OK, scenario.getContacts()
				.getStatus());
	}

	@Test
	public void testGetContactsAfterAdd() throws Exception {
		Contact contact = createContact();
		assertEquals(ClientResponse.Status.OK, scenario.addContact(contact)
				.getContacts().getStatus());
	}

	@Test
	public void testGetContactAfterAdd() throws Exception {
		Contact contact = createContact();
		assertEquals(ClientResponse.Status.OK, scenario.addContact(contact)
				.getContact(new ContactId(CONTACT_ID)).getStatus());
	}

	
	@Test
	public void testRemoveContact() throws Exception {
		Contact contact = createContact();
		assertEquals(ClientResponse.Status.OK, scenario.addContact(contact)
				.removeContact(contact.getContactId()).getStatus());
	}

	private Contact createContact() {
		Contact contact = new Contact(new ContactId(CONTACT_ID));
		contact.setFirstName("first name");
		contact.setLastName("last name");
		contact.setPhoneNumber("9023902390");
		contact.setMailAddress("street address");
		contact.setNotes("notest");
		contact.setEmail("test@test.com");
		return contact;
	}

}
