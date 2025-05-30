package com.example.demo.entity;

import org.springframework.data.annotation.Id;


import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "trailer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter

@Setter
public class Trailer {
	@Id
	private String idTrailer;
	private String immatricule;
	private String model;
	private String mark;
	private String type;
	private String charge;
	
}
