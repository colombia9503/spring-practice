package com.sumana.practica.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sumana.practica.component.ContactConverter;
import com.sumana.practica.entity.Contact;
import com.sumana.practica.model.ContactModel;
import com.sumana.practica.repository.ContactRepository;
import com.sumana.practica.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;

	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact = contactRepository.save(contactConverter.convertContactModel2Contact(contactModel));
		return contactConverter.convertContact2ContactModel(contact);
	}

	@Override
	public List<ContactModel> listAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactsModel = new ArrayList<ContactModel>();
		for (Contact contact : contacts) {
			contactsModel.add(contactConverter.convertContact2ContactModel(contact));
		}
		return contactsModel;
	}

	@Override
	public ContactModel findContactbyId(int id) {
		Contact contact = contactRepository.findById(id);
		return contactConverter.convertContact2ContactModel(contact);
	}

	@Override
	public void removeContact(int id) {
		Contact contact = contactRepository.findById(id);
		if(contact != null) {
			contactRepository.delete(id);
		}
	}

}
