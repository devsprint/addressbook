package com.devsprint.learning.agenda_service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handle contacts.
 * 
 * @author gciuloaica
 * 
 */
public class Contacts {

	private final ContactsStorage storage;

	public Contacts(ContactsStorage storage) {
		this.storage = storage;
	}

	public void add(Contact contact) throws PersistenceException {
		storage.addContact(contact);

	}

	public long getCount() {
		return storage.getCount();
	}

	public void add(Set<Contact> contacts) throws PersistenceException {
		storage.addContacts(contacts);
	}

	public List<Contact> getContacts() throws PersistenceException {
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.addAll(storage.getContacts(100));
		return contacts;
	}

	public void remove(String contactId) throws PersistenceException {
		storage.removeContact(new ContactId(contactId));
		
	}

	public Contact getContact(String contactId) throws PersistenceException {
		return storage.getContact(new ContactId(contactId));
	}

}
