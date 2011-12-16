/**
 * 
 */
package com.devsprint.learning.agenda_service.resources;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.devsprint.learning.agenda.AddressBookProtos.AddressBook;
import com.devsprint.learning.agenda.AddressBookProtos.Person;
import com.devsprint.learning.agenda_service.Contact;

/**
 * @author gciuloaica
 * 
 */
public class ContactEncoder {

	static Person fromContact(Contact contact) {
		return getPerson(contact);
	}

	static AddressBook fromContacts(List<Contact> contacts) {
		return getPersons(contacts);
	}

	private static Person getPerson(Contact contact) {
		if (contact == null) {
			throw new WebApplicationException(Status.SERVICE_UNAVAILABLE);
		}

		Person.Builder personBuilder = Person.newBuilder();
		Person person = personBuilder.setFirstName(contact.getFirstName())
				.setLastName(contact.getLastName())
				.setEmail(contact.getEmail())
				.setPhoneNumber(contact.getPhoneNumber())
				.setMailAddress(contact.getMailAddress())
				.setNotes(contact.getNotes())
				.setId(contact.getContactId().getId()).build();
		return person;

	}

	private static AddressBook getPersons(List<Contact> contacts) {
		if (contacts == null) {
			throw new WebApplicationException(Status.SERVICE_UNAVAILABLE);
		}

		AddressBook.Builder addressBookBuilder = AddressBook.newBuilder();
		for (Contact contact : contacts) {
			addressBookBuilder.addPerson(getPerson(contact));
		}
		return addressBookBuilder.build();
	}

}
