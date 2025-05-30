package com.example.demo.requests;




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
public class TrailerRequest {
	@Id
	private String immatricule;
	private String model;
	private String mark;
	private String occupationTimes;
	private String charge;
	
	public TrailerRequest(String model, String mark, String occupationTimes, String charge) {
		super();
		this.model = model;
		this.mark = mark;
		this.occupationTimes = occupationTimes;
	    this.charge=charge;
	
	}

	
	
}
