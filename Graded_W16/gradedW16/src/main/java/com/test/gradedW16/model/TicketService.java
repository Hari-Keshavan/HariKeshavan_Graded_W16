package com.test.gradedW16.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
	
	@Autowired
	TicketRepository repo;
	
	public void create(Ticket ticket) {
		repo.save(ticket);
	}
	
	public List<Ticket> read() {
		return repo.findAll();
	}
	
	public Ticket getClassById(int id) {
		if (repo.findById(id).isEmpty()) {
			return null;
		}
		return repo.findById(id).get();
	}
	
	public void update(Ticket ticket) {
		repo.save(ticket);
	}
	
	public void delete(Ticket ticket) {
		repo.delete(ticket);
	}
	
	public List<Ticket> filterByTitle(String columnName, String searchKey) {
		//1. Create a dummy object based on the searchKey
		Ticket dummy = new Ticket();
		dummy.setTitle(searchKey);

		
		//2. Create Example JPA - where
		ExampleMatcher exMatcher = ExampleMatcher.matching().withMatcher(columnName, ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("id", "shortDes", "content", "date");
		
		//3. Combining Dummy with Where
		Example<Ticket> example = Example.of(dummy, exMatcher);
		
		return repo.findAll(example);
	}
}
