package com.test.gradedW16.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	
	@Id
	private int id;
	private String title;
	private String shortDes;
	private String content;
	private LocalDate date = LocalDate.now();
	
	
	@Transient
	public String getDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
	    String createdDate = date.format(formatter);
	    return createdDate;
	}
	

}
