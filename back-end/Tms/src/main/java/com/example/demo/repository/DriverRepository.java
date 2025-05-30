package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.entity.Driver;


public interface DriverRepository extends MongoRepository<Driver, String> {

	Driver findByDriverId(String driverId);
	List<Driver> findByName(String name);
	  // Custom query to search by name or license using regex for partial matching
    @Query("{ $or: [ { 'name': { $regex: ?0, $options: 'i' } }, { 'firstName': { $regex: ?0, $options: 'i' } } ] }")
    List<Driver> searchDriversByQuery(String query);

}
