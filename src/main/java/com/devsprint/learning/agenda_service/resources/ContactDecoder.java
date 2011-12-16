package com.devsprint.learning.agenda_service.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.devsprint.learning.agenda.AddressBookProtos.AddressBook;
import com.devsprint.learning.agenda.AddressBookProtos.Person;
import com.devsprint.learning.agenda_service.Contact;
import com.devsprint.learning.agenda_service.ContactId;

public class ContactDecoder {

	static Contact fromPerson(InputStream inputStream) throws IOException {
		Person person = Person.parseFrom(inputStream);
		return getContact(person);

	}

	static List<Contact> fromAddressBook(InputStream inputStream)
			throws IOException {
		AddressBook addressBook = AddressBook.parseFrom(inputStream);
		List<Contact> contacts = new ArrayList<Contact>(
				addressBook.getPersonCount());
		for (Person person : addressBook.getPersonList()) {
			contacts.add(getContact(person));
		}
		return contacts;
	}

	private static Contact getContact(Person person) {
		Contact contact = new Contact(new ContactId(person.getId()));
		return contact.setFirstName(person.getFirstName())
				.setLastName(person.getLastName()).setEmail(person.getEmail())
				.setMailAddress(person.getMailAddress())
				.setPhoneNumber(person.getPhoneNumber())
				.setNotes(person.getNotes());
	}

}
