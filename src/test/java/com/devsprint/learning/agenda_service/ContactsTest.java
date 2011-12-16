package com.devsprint.learning.agenda_service;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Contacts unit test suite
 */
public class ContactsTest {

	private static final InMemoryContactsStorage STORAGE = (InMemoryContactsStorage) InMemoryContactsStorage
			.getInstance();

	@Before
	public void cleanUp() {
		STORAGE.clenaup();
	}

	/**
	 * As a user of the service I would like to be able to add contacts such
	 * that I can later access them.
	 * 
	 * @throws PersistenceException
	 */
	@Test
	public void storeContact() throws PersistenceException {
		Contacts contacts = new Contacts(STORAGE);
		ContactId id = new ContactId("1");
		Contact contact = new Contact(id);

		contacts.add(contact);
		assertTrue("There should be only one item in agenda.",
				contacts.getCount() == 1);
	}

	@Test(expected = PersistenceException.class)
	public void contactWithoutContactIdCouldNotBeStored()
			throws PersistenceException {
		Contacts contacts = new Contacts(STORAGE);
		Contact contact = new Contact(null);

		contacts.add(contact);
	}

	@Test
	public void storeContacts() throws PersistenceException {
		Contacts contacts = new Contacts(STORAGE);
		ContactId id1 = new ContactId("1");
		ContactId id2 = new ContactId("2");
		Contact contact1 = new Contact(id1);
		Contact contact2 = new Contact(id2);
		Set<Contact> newContacts = new HashSet<Contact>(2);
		newContacts.add(contact1);
		newContacts.add(contact2);
		contacts.add(newContacts);
		assertTrue("There should be 2 contacts in storage.",
				contacts.getCount() == 2);
	}
}
