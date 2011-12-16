/**
 * 
 */
package com.devsprint.learning.agenda_service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * In Memory contacts Storage.
 * 
 * @author gciuloaica
 * 
 */
public class InMemoryContactsStorage implements ContactsStorage {

	private static final ContactsStorage STORAGE = new InMemoryContactsStorage();
	private static final Map<ContactId, Contact> INTERNAL_STORAGE = new ConcurrentHashMap<ContactId, Contact>();
	private static final Logger LOG = LoggerFactory
			.getLogger(InMemoryContactsStorage.class);

	public static ContactsStorage getInstance() {
		LOG.debug("Storage id: {}", STORAGE.toString());
		return STORAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.devsprint.learning.agenda_service.ContactsStorage#addContact(com.
	 * devsprint.learning.agenda_service.Contact)
	 */
	@Override
	public ContactId addContact(Contact contact) throws PersistenceException {
		validateContact(contact);
		INTERNAL_STORAGE.put(contact.getContactId(), contact);
		return contact.getContactId();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.devsprint.learning.agenda_service.ContactsStorage#getContact(java
	 * .lang.String)
	 */
	@Override
	public Contact getContact(ContactId contactId) throws PersistenceException {
		validateContactId(contactId);
		return INTERNAL_STORAGE.get(contactId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.devsprint.learning.agenda_service.ContactsStorage#addContacts(java
	 * .util.Set)
	 */
	@Override
	public void addContacts(Set<Contact> contacts) throws PersistenceException {
		validateListOfContacts(contacts);
		for (Contact contact : contacts) {
			addContact(contact);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.devsprint.learning.agenda_service.ContactsStorage#removeContact(com
	 * .devsprint.learning.agenda_service.ContactId)
	 */
	@Override
	public void removeContact(ContactId contactId) throws PersistenceException {
		validateContactId(contactId);
		INTERNAL_STORAGE.remove(contactId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.devsprint.learning.agenda_service.ContactsStorage#removeContacts(
	 * java.util.Set)
	 */
	@Override
	public void removeContacts(Set<ContactId> contactIds)
			throws PersistenceException {
		validateContactIds(contactIds);
		for (ContactId contactId : contactIds) {
			removeContact(contactId);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.devsprint.learning.agenda_service.ContactsStorage#patchContact(com
	 * .devsprint.learning.agenda_service.Contact)
	 */
	@Override
	public void patchContact(Contact contact) throws PersistenceException {
		addContact(contact);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.devsprint.learning.agenda_service.ContactsStorage#patchContacts(java
	 * .util.Set)
	 */
	@Override
	public void patchContacts(Set<Contact> contacts)
			throws PersistenceException {
		addContacts(contacts);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.devsprint.learning.agenda_service.ContactsStorage#getCount()
	 */
	@Override
	public long getCount() {
		return INTERNAL_STORAGE.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.devsprint.learning.agenda_service.ContactsStorage#getContacts(int)
	 */
	@Override
	public Set<Contact> getContacts(int count) throws PersistenceException {
		final Set<Contact> contacts = new HashSet<Contact>(count);
		Collection<Contact> contactList = INTERNAL_STORAGE.values();
		if (!contactList.isEmpty()) {
			Iterator<Contact> iterator = contactList.iterator();

			for (int i = 0; i < count; i++) {
				if (iterator.hasNext()) {
					contacts.add(iterator.next());
				} else {
					break;
				}
			}
		}
		return contacts;
	}

	public void clenaup() {
		INTERNAL_STORAGE.clear();
	}

	private InMemoryContactsStorage() {

	}

	private void validateContact(Contact contact) throws PersistenceException {
		if (contact == null) {
			throw new IllegalArgumentException("Contact could not be null.");
		}

		if (contact.getContactId() == null) {
			throw new PersistenceException("Contact do not have a unique id.");
		}

	}

	private void validateListOfContacts(Set<Contact> contacts)
			throws PersistenceException {
		if (contacts == null) {
			throw new IllegalArgumentException("Contacts could not be null.");
		}
	}

	private void validateContactId(ContactId contactId)
			throws PersistenceException {
		if (contactId == null) {
			throw new IllegalArgumentException("ContactId could not be null.");
		}

		if (!INTERNAL_STORAGE.containsKey(contactId)) {
			throw new PersistenceException("Invalid contact identifier.");
		}

	}

	private void validateContactIds(Set<ContactId> contactIds) {
		if (contactIds == null) {
			throw new IllegalArgumentException("ContactIds could not be null.");
		}

	}

}
