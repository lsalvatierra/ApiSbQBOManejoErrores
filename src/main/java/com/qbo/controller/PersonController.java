package com.qbo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qbo.service.PersonService;
import com.qbo.model.Person;
import com.qbo.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/apiqbo/v1")
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getAllPersons() {
		List<Person> persons = new ArrayList<Person>();
		personService.getAllPersons().forEach(persons::add);
		if (persons.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
	}

	@GetMapping("/person/{id}")
	public ResponseEntity<Person> getTutorialById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		Person person = personService
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
		return new ResponseEntity<>(person, HttpStatus.OK);
	}
	
	@PostMapping("/person")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		Person _person = personService.save(person);
		return new ResponseEntity<>(_person, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/person/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable("id") Integer id, @RequestBody Person person) throws ResourceNotFoundException {
		Person oldperson = personService.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
		return new ResponseEntity<>(personService.update(oldperson, person), HttpStatus.OK);
	  }

	  @DeleteMapping("/person/{id}")
	  public ResponseEntity<Person> deleteTutorial(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		  Person person = personService.findById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));	    
	    return new ResponseEntity<>(personService.delete(person), HttpStatus.NO_CONTENT);
	  }
	
	  
	  @DeleteMapping("/person")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
		  personService.deleteAll();	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	
}
