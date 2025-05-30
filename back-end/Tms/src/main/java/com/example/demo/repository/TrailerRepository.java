package com.example.demo.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.entity.Trailer;

public interface TrailerRepository extends MongoRepository<Trailer, String> {
	Trailer findTrailerByImmatricule(String immatricule);
	  // Custom query to search by name or license using regex for partial matching
    @Query("{ $or: [ { 'immatricule': { $regex: ?0, $options: 'i' } }, { 'model': { $regex: ?0, $options: 'i' } } ] }")
    
	List<Trailer> searchTrailersByQuery(String query);


}
