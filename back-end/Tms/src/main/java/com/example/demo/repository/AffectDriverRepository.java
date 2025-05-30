//package com.example.demo.repository;
//
//import java.util.Optional;
//
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import com.example.demo.entity.AffectDriver;
//import com.example.demo.entity.Driver;
//
//
//public interface AffectDriverRepository  extends MongoRepository<AffectDriver, String> {
//
//	Optional<AffectDriver> findByDriver(Driver driver);
//	
//	
//}
package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.AffectDriver;
import com.example.demo.entity.Driver;

public interface AffectDriverRepository extends MongoRepository<AffectDriver, String> {

    Optional<AffectDriver> findByDriversContaining(Driver driver); // Fixed method

}
