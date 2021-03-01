package com.qbo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.qbo.model.Person;

@Service
public class PersonService {
	
	public List<Person> lstPersons = new ArrayList<>();
	
	public PersonService() {
		lstPersons.add(new Person(1, "Luis Angel", 30));
		lstPersons.add(new Person(2, "Isis Priscila", 30));
		lstPersons.add(new Person(3, "Felipe Anderson", 30));
		lstPersons.add(new Person(4, "Brandon Jesus", 30));
	}
	
	public List<Person> getAllPersons(){
		return lstPersons;	
	}
	
	
	public Optional<Person> findById(Integer id) {
		for(Person person : lstPersons) {
			if(person.getId().equals(id)) {
				return Optional.of(new Person(person.getId(), person.getName(), person.getAge()));				
			}
		}
		return Optional.empty();
	}
	
	
	public Person save(Person person) {
		lstPersons.add(person);
		return person;
	}
	  
	public Person update(Person oldPerson, Person newPerson) {
		lstPersons.remove(oldPerson);
		lstPersons.add(newPerson);
		return newPerson;
	}
	
	public Person delete(Person person) {
		lstPersons.remove(person);
		return person;
	}
	
	public void deleteAll() {
		lstPersons.removeAll(lstPersons);
	}
}
