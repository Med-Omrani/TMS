package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Trailer;
import com.example.demo.entity.Vehicle;
import com.example.demo.repository.TrailerRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.response.*;

@Service
public class TrailerService {
    @Autowired
    private VehicleRepository vehicleRepository;
	@Autowired
	private TrailerRepository trailerRepository;
	private static final Logger logger = LoggerFactory.getLogger(TrailerService.class);


	  public Trailer saveTrailer(Trailer trailer) {
	        return trailerRepository.save(trailer);
	    }


	public ResponseEntity<Object> getTrailerById(String Id) {
		if (trailerRepository.findById(Id).isEmpty())
			return ResponseHandler.responseBuilder("Requested trailer does not exist", HttpStatus.NOT_FOUND, null);
		return ResponseHandler.responseBuilder("trailer's data", HttpStatus.OK,
				trailerRepository.findById(Id).get());

	}
	 public List<Trailer>getAllTrailers(){
	    	return trailerRepository.findAll();
	    }

	public ResponseEntity<Object> deleteTrailer(String Id) {
		trailerRepository.deleteById(Id);
		List<Vehicle> vehicles = vehicleRepository.findAll();
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getTrailer() != null && Id.equals(vehicle.getTrailer().getImmatricule())) {
				vehicle.setTrailer(null);
				vehicleRepository.save(vehicle);
			}
		}
		if (trailerRepository.findById(Id).isEmpty())
			return ResponseHandler.responseBuilder("data is deleted ", HttpStatus.OK, null);
		else
			return ResponseHandler.responseBuilder("the deleting failued", HttpStatus.NOT_FOUND, null);
	}



	

	public ResponseEntity<Object> updateTrailer(String Id, Trailer updatedTrailer) {
		Optional<Trailer> existingVehicle = trailerRepository.findById(Id);
		if (existingVehicle.isPresent()) {
			Trailer trailer = existingVehicle.get();
			trailer.setModel(updatedTrailer.getModel());
			trailer.setMark(updatedTrailer.getMark());
			trailer.setCharge(updatedTrailer.getCharge());

			trailerRepository.save(trailer);
		}
		if (trailerRepository.findById(Id).isEmpty())
			return ResponseHandler.responseBuilder("Requested trailer does not exist", HttpStatus.NOT_FOUND, null);
		return ResponseHandler.responseBuilder("data is updated ", HttpStatus.OK,
				trailerRepository.findById(Id).get());
	}
	 public List<Trailer> searchTrailers(String query) {
	        List<Trailer> results = trailerRepository.searchTrailersByQuery(query);
	        logger.info("Search query: {}, Results: {}", query, results);
	        if (results.isEmpty()) {
	            logger.info("No drivers found for query: {}", query);
	        }
	        return results;
	    }

}
