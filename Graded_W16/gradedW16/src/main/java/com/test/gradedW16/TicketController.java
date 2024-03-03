package com.test.gradedW16;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.gradedW16.model.Ticket;
import com.test.gradedW16.model.TicketService;

@Controller
public class TicketController {
	
	@Autowired
	TicketService service;
	
	@RequestMapping("/")
	public String homePageLink(Model data) {
		List<Ticket> ticketlist = service.read();
		data.addAttribute("ticketlist", ticketlist);
		return "home";
	}
	
	@RequestMapping("/home-con")
	public String homePageLink2(Model data) {
		List<Ticket> ticketlist = service.read();
		data.addAttribute("ticketlist", ticketlist);
		return "home";
	}
	
	@RequestMapping("/add-ticket-con")
	public String addTicketPageLink() {
		return "add-ticket";
	}
	
	@PostMapping("/added-ticket-con")
	public String addedTicket(@RequestParam int id, @RequestParam String title, @RequestParam String description, @RequestParam String content, Model data) {
		LocalDate date = LocalDate.now();
		Ticket ticket = new Ticket(id, title, description, content, date);
		service.create(ticket);
		List<Ticket> ticketlist = service.read();
		data.addAttribute("ticketlist", ticketlist);
		return "home";	
	}
	
	@GetMapping("/update-ticket-con")
	public String updateTicketPageLink(@RequestParam int id, Model data) {
		Ticket updateTicket = service.getClassById(id);
		if (updateTicket != null) {
			data.addAttribute("upticket", updateTicket);
			return "update-ticket";
		}
		List<Ticket> ticketList = service.read();
		data.addAttribute("ticketlist", ticketList);
		return "home";
	}
	
	@PostMapping("updated-ticket-con")
	public String updatedTicket(@RequestParam int id, @RequestParam String title, @RequestParam String description, @RequestParam String content, Model data) {
		LocalDate date = LocalDate.now();
		Ticket ticket = new Ticket(id, title, description, content, date);
		service.update(ticket);
		List<Ticket> ticketList = service.read();
		data.addAttribute("ticketlist", ticketList);
		return "home";	
		
	}
	
	@RequestMapping("/show-tickets-con")
	public String showTicketPageLink(Model data) {
		List<Ticket> ticketlist = service.read();
		data.addAttribute("ticketlist", ticketlist);
		return "show-tickets";
	}
	
	@RequestMapping("/view-ticket-con")
	public String ViewTicketPageLink(@RequestParam int id, Model data) {
		Ticket viewTicket = service.getClassById(id);
		if (viewTicket != null) {
			data.addAttribute("viewticket", viewTicket);
			return "view-ticket";
		}
		List<Ticket> ticketList = service.read();
		data.addAttribute("ticketlist", ticketList);
		return "home";
	}
	
	@GetMapping("/delete-ticket-con")
	public String deletedTicket(@RequestParam int id, Model data) {
		Ticket ticket = new Ticket(id, "", "", "", null);
		service.delete(ticket);
		List<Ticket> ticketList = service.read();
		data.addAttribute("ticketlist", ticketList);
		return "home";
	}
	
	@PostMapping("/search-tickets-con")
	public String searchTicketsPageLink(@RequestParam String search, Model data) {
		List<Ticket> searchtickets = service.filterByTitle("title", search);
		data.addAttribute("ticketlist", searchtickets);
		return "search-tickets";
	}
	
}
