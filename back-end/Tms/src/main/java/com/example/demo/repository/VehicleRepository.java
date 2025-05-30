package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.entity.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
	Vehicle findByImmatricule(String immatricule);
	  // Custom query to search by name or license using regex for partial matching
    @Query("{ $or: [ { 'immatricule': { $regex: ?0, $options: 'i' } }, { 'model': { $regex: ?0, $options: 'i' } } ] }")
    List<Vehicle> searchVehiclesByQuery(String query);

}
