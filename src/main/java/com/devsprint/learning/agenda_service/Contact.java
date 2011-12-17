package com.devsprint.learning.agenda_service;

/**
 * Contact model
 * @author gciuloaica
 *
 */
public class Contact {

	private final ContactId contactId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String mailAddress;
	private String notes;

	public Contact(final ContactId id) {
		this.contactId = id;
	}

	public Contact() {
		this.contactId = null;
	}

	public String getFirstName() {
		return firstName;
	}

	public Contact setFirstName(final String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Contact setLastName(final String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Contact setEmail(final String email) {
		this.email = email;
		return this;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Contact setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public Contact setMailAddress(final String mailAddress) {
		this.mailAddress = mailAddress;
		return this;
	}

	public String getNotes() {
		return notes;
	}

	public Contact setNotes(final String notes) {
		this.notes = notes;
		return this;
	}

	public ContactId getContactId() {
		return contactId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contactId == null) ? 0 : contactId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((mailAddress == null) ? 0 : mailAddress.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (contactId == null) {
			if (other.contactId != null)
				return false;
		} else if (!contactId.equals(other.contactId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mailAddress == null) {
			if (other.mailAddress != null)
				return false;
		} else if (!mailAddress.equals(other.mailAddress))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}
	
	

}
