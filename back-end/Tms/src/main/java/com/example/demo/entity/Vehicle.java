package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter

@Setter

public class Vehicle {
	@Id
	private String idVehicle;
	private String immatricule;
	private String model;
	private String mark;
	private String occupationTimes;
	private String consumption;
	private String counter;
	@DBRef
	private Trailer trailer;

}
