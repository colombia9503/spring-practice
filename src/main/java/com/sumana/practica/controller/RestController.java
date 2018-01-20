package com.sumana.practica.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sumana.practica.entity.Contact;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {
	@GetMapping("/checkrest")
	public ResponseEntity<Contact> checkRest() {
		Contact contact = new Contact("Santiago", "umana", "3333", "22222");
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}
}
