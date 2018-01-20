package com.sumana.practica.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sumana.practica.component.ContactConverter;
import com.sumana.practica.constant.ViewConstant;
import com.sumana.practica.model.ContactModel;
import com.sumana.practica.repository.ContactRepository;
import com.sumana.practica.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	public static final Log LOG = LogFactory.getLog(ContactController.class);
	
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	@PreAuthorize("hasRole('USER_ROLE')")
	@GetMapping("/contactform")
	public String redirectContactForm(Model model, @RequestParam(name="id", defaultValue="0", required=false) int id) {
		if(id !=  0) {
			ContactModel contact = contactService.findContactbyId(id);
			model.addAttribute("contactModel", contact);
		} else {
			model.addAttribute("contactModel", new ContactModel());
		}
		return ViewConstant.CONTACT_FORM;
	}
	
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showcontacts";
	}
	
	@PostMapping("/addcontact")
	public String addContact(Model model, @ModelAttribute(name="contactModel") ContactModel contactModel) {
		LOG.info("METHOD: addContact() -- PARAMS: contact='" + contactModel.toString() + "'");

		if(contactService.addContact(contactModel) != null) {
			model.addAttribute("result", 1);
		} else {
			model.addAttribute("result", 0);
		}
		return "redirect:/contacts/showcontacts";	
	}
	
	@GetMapping("/showcontacts")
	public String showContacts(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("contacts", contactService.listAllContacts());
		model.addAttribute("username", user.getUsername());
		LOG.info("user authorities: " + user.getAuthorities().toString());
		return ViewConstant.CONTACTS;
	}
	
	@GetMapping("/removecontact")
	public String removeContact(@RequestParam(name="id", required=true) int id) {
		contactService.removeContact(id);
		return "redirect:/contacts/showcontacts";
	}
}
