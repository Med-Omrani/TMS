//package com.example.demo.entity;
//import java.util.List;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;
//
////import lombok.AllArgsConstructor;
//import lombok.Data;
////import lombok.Getter;
////import lombok.NoArgsConstructor;
////import lombok.Setter;
//
//@Document(collection = "AffectDriver")
//@Data
////@NoArgsConstructor
////@AllArgsConstructor
////@Getter
////@Setter
//public class AffectDriver {
//	@Id
//    private String idAffect;
//	private String begin ;
//	private String end;
//	static int numDriver;
//	@DBRef
//	private List<Driver> drivers;
////	
//	public void addDriver(Driver driver) {
//		// TODO Auto-generated method stub
//		this.drivers.add(numDriver, driver);
//	}
//public AffectDriver(String idAffect, String begin, String end, List<Driver> drivers) {
//	super();
//	this.idAffect = idAffect;
//	this.begin = begin;
//	this.end = end;
//
//}
//}
//package com.example.demo.entity;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;
//import lombok.Data;
//
//@Document(collection = "AffectDriver")
//@Data
//public class AffectDriver {
//
//    @Id
//    private String affectId;
//    private String begin;
//    private String end;
//    @DBRef
//    private Driver driver;
//    
//    public AffectDriver(String affectId, String begin, String end, Driver driver)
//    {   
//    	
//    	this.affectId= affectId;
//        this.begin = begin;
//        this.end = end;
//        this.driver=driver;
//
//        
//    }
//}
//  
package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.List;

@Document(collection = "AffectDriver")
@Data
public class AffectDriver {

    @Id
    private String affectId;
    private String begin;
    private String end;

    @DBRef
    private List<Driver> drivers; // Change from single driver to list

    public AffectDriver(String affectId, String begin, String end, List<Driver> drivers) {
        this.affectId = affectId;
        this.begin = begin;
        this.end = end;
        this.drivers = drivers;
    }
}
