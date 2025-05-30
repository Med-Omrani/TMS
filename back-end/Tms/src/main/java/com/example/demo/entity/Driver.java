package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "driver")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JsonFilter(value = "DriverDetails")
public class Driver {
	@Id
	private String driverId;
	private String name;
	private String firstName;
	private String email;
	
	@JsonIgnore
	private String password;

//	@JsonIgnore
	private int num;
	@DBRef
	private Vehicle vehicle;
	
	
}
//import org.springframework.data.mongodb.core.mapping.DocumentReference;
//import org.springframework.http.ResponseEntity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;

