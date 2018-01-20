package com.sumana.practica.service;

import java.util.List;

import com.sumana.practica.model.ContactModel;

public interface ContactService {
	
	public abstract ContactModel addContact(ContactModel contactModel);
	
	public abstract List<ContactModel> listAllContacts();
	
	public abstract ContactModel findContactbyId(int id);
	
	public abstract void removeContact(int id);
}
