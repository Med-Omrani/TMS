package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Driver;
import com.example.demo.entity.Trailer;
import com.example.demo.entity.Vehicle;
import com.example.demo.repository.DriverRepository;
import com.example.demo.repository.TrailerRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.response.*;

@Service
public class VehicleService {
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private TrailerRepository trailerRepository;
	private static final Logger logger = LoggerFactory.getLogger(DriverService.class);

	public Vehicle saveVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	public ResponseEntity<Object> getVehicleById(String id) {
		if (vehicleRepository.findById(id).isEmpty())
			return ResponseHandler.responseBuilder("Requested vehicle does not exist", HttpStatus.NOT_FOUND, null);
		return ResponseHandler.responseBuilder("vehicle's data", HttpStatus.OK,
				vehicleRepository.findById(id).get());

	}
	 public List<Vehicle>getAllVehicles(){
	    	return vehicleRepository.findAll();
	    }

	public void assignTrailerToVehicle(String vehicleId, String TrailerId) {

		Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
		Optional<Trailer> trailerOptional = trailerRepository.findById(TrailerId);
		if (!vehicleOptional.isPresent()) {
			logger.error("Vehicle with ID {} not found", vehicleId);
			return;
		}

		if (!trailerOptional.isPresent()) {
			logger.error("Trailer with ID {} not found", vehicleId);
			return;
		}

		Vehicle vehicle = vehicleOptional.get();
		Trailer trailer = trailerOptional.get();
		vehicle.setTrailer(trailer);
		vehicleRepository.save(vehicle);

		logger.info("Assigned Vehicle with ID {} to Driver with ID {}", vehicleId, TrailerId);
	}
	public void releaseTrailerFromVehicle(String vehicleId, String TrailerId) {
	    Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
	   

	    if (!vehicleOptional.isPresent()) {
	        logger.error("Vehicle with ID {} not found", vehicleId);
	        return;
	    }

	    Vehicle vehicle = vehicleOptional.get();

	    if (vehicle.getTrailer() == null) {
	        logger.warn("No trailer assigned to Vehicle with ID {}", vehicleId);
	        return;
	    }

	    vehicle.setTrailer(null); // Remove the trailer association
	    vehicleRepository.save(vehicle);

	    logger.info("Released trailer from Vehicle with ID {}", vehicleId);
	}



	public  ResponseEntity<Object> deleteVehicle(String id) {
		vehicleRepository.deleteById(id);
		List<Driver> drivers = driverRepository.findAll();
		for (Driver driver : drivers) {
			if (driver.getVehicle() != null && id.equals(driver.getVehicle().getIdVehicle())) {
				driver.setVehicle(null);
				driverRepository.save(driver);
			}
		}
		if (vehicleRepository.findById(id).isEmpty())
			return ResponseHandler.responseBuilder("data is deleted ", HttpStatus.OK, null);
	else
			return ResponseHandler.responseBuilder("the deleting failued", HttpStatus.NOT_FOUND, null);
	}

	

	public ResponseEntity<Object> updateVehicle(String id, Vehicle updatedVehicle) {
		Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
		if (existingVehicle.isPresent()) {
			Vehicle vehicle = existingVehicle.get();
			vehicle.setModel(updatedVehicle.getModel());
			vehicle.setMark(updatedVehicle.getMark());
			vehicle.setOccupationTimes(updatedVehicle.getOccupationTimes());
			vehicle.setConsumption(updatedVehicle.getConsumption());
			vehicle.setCounter(updatedVehicle.getCounter());

			vehicleRepository.save(vehicle);
		}
		if (vehicleRepository.findById(id).isEmpty())
			return ResponseHandler.responseBuilder("Requested vehicle does not exist", HttpStatus.NOT_FOUND, null);
		return ResponseHandler.responseBuilder("data is updated ", HttpStatus.OK,
				vehicleRepository.findById(id).get());
	}
	 public List<Vehicle> searchVehicles(String query) {
	        List<Vehicle> results = vehicleRepository.searchVehiclesByQuery(query);
	        logger.info("Search query: {}, Results: {}", query, results);
	        if (results.isEmpty()) {
	            logger.info("No vehicles found for query: {}", query);
	        }
	        return results;
	    }
}

