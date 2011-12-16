package com.devsprint.learning.agenda_service;

import java.util.Set;

/**
 * Define a persistence management for Contacts.
 * 
 * @author gciuloaica
 * 
 */
public interface ContactsStorage {

	/**
	 * Store a new Contact.
	 * 
	 * @param contact
	 * @throws PersistenceException
	 *             - if there is any error during persistence process.
	 */
	ContactId addContact(Contact contact) throws PersistenceException;

	/**
	 * Get a contact identified through its unique id.
	 * 
	 * @param contactId
	 *            - unique contact identifier
	 * @return contact instance
	 * @throws PersistenceException
	 *             -if there is any error during persistence process.
	 */
	Contact getContact(ContactId contactId) throws PersistenceException;

	/**
	 * Remove a contact from storage
	 * 
	 * @param contactId
	 *            - unique id of the contact.
	 * @throws PersistenceException
	 *             - if there is any error during persistence process.
	 */
	void removeContact(ContactId contactId) throws PersistenceException;

	/**
	 * Update an existing contact.
	 * 
	 * @param contact
	 *            - the new updated contact.
	 * @throws PersistenceException
	 *             -if there is any error during persistence process.
	 */
	void patchContact(Contact contact) throws PersistenceException;

	/**
	 * Store a collection of Contacts.
	 * 
	 * @param contacts
	 * @throws PersistenceException
	 *             - if there is any error during persistence process.
	 */
	void addContacts(Set<Contact> contacts) throws PersistenceException;

	/**
	 * Remove a collection of contacts.
	 * 
	 * @param contactIds
	 * @throws PersistenceException
	 *             -if there is any error during persistence process.
	 */
	void removeContacts(Set<ContactId> contactIds) throws PersistenceException;

	/**
	 * Update a collection of contacts.
	 * 
	 * @param contacts
	 *            - a set of contacts.
	 * @throws PersistenceException
	 *             -if there is any error during persistence process.
	 */
	void patchContacts(Set<Contact> contacts) throws PersistenceException;

	/**
	 * Get the number of contacts stored.
	 * 
	 * @return number of contacts stored.
	 */
	long getCount();

	/**
	 * Get a collection of contacts containing at least <code>count</code>
	 * contacts.
	 * 
	 * @param count
	 *            - number of the items in batch.
	 * @return
	 * @throws PersistenceException
	 *             -if there is any error during persistence process.
	 */
	Set<Contact> getContacts(int count) throws PersistenceException;

}
