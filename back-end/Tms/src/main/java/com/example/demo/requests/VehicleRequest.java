
package com.example.demo.requests;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Document(collection = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


 public class VehicleRequest { 
	@Id
	private String immatricule;
	private String model;
	private String mark;
	private String occupationTimes;
	private String consumption;
	private String counter;

}
