
package com.example.demo.service;

import java.util.List;
import java.util.Optional;
//import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Driver;
import com.example.demo.entity.Vehicle;
import com.example.demo.repository.DriverRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.response.*;


@Service
public class DriverService {

	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	
	private static final Logger logger = LoggerFactory.getLogger(DriverService.class);


	public Driver saveDriver(Driver driver) {
		return driverRepository.save(driver);
	}

	public List<Driver> getAllDrivers() {
		return driverRepository.findAll();
	}

	public void assignVehicleToDriver(String driverId, String vehicleId) {
		Optional<Driver> driverOptional = driverRepository.findById(driverId);
		Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);

		if (!driverOptional.isPresent()) {
			logger.error("Driver with ID {} not found", driverId);
			return;
		}

		if (!vehicleOptional.isPresent()) {
			logger.error("Vehicle with ID {} not found", vehicleId);
			return;
		}

		Driver driver = driverOptional.get();
		Vehicle vehicle = vehicleOptional.get();
		driver.setVehicle(vehicle);
		driverRepository.save(driver);

		logger.info("Assigned Vehicle with ID {} to Driver with ID {}", vehicleId, driverId);
	}

	public void releaseVehicleFromDriver(String driverId, String vehicleId) {
		Optional<Driver> driverOptional = driverRepository.findById(driverId);
		//Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);

		if (!driverOptional.isPresent()) {
			logger.error("Driver with ID {} not found", driverId);
			return;
		}

		Driver driver = driverOptional.get();
		
		driver.setVehicle(null);
		driverRepository.save(driver);

		logger.info("Realesed ehicle with ID {} from Driver with ID {}", vehicleId, driverId);
	}

	public ResponseEntity<Object> getDriverByID(String driverId) {
		if (driverRepository.findById(driverId).isEmpty())
			return ResponseHandler.responseBuilder("Requested driver does not exist", HttpStatus.NOT_FOUND, null);
		return ResponseHandler.responseBuilder("dirver's data", HttpStatus.OK,
				driverRepository.findById(driverId).get());

	}

	public ResponseEntity<Object> deleteDriver(String driverId) {
		System.err.println("driverId ::::::::: " + driverId);
		driverRepository.deleteById(driverId);

		if (driverRepository.findById(driverId).isEmpty())
			return ResponseHandler.responseBuilder("data is deleted ", HttpStatus.OK, null);
		else
			return ResponseHandler.responseBuilder("the deleting failued", HttpStatus.NOT_FOUND, null);
	}

	public ResponseEntity<Object> updateDriver(String driverId, Driver updatedDriver) {
		Optional<Driver> existingDriver = driverRepository.findById(driverId);
		if (existingDriver.isPresent()) {
			Driver driver = existingDriver.get();
			driver.setName(updatedDriver.getName());
			driver.setFirstName(updatedDriver.getFirstName());
			driver.setEmail(updatedDriver.getEmail());
			driver.setPassword(updatedDriver.getPassword());
			driver.setNum(updatedDriver.getNum());
			
			driverRepository.save(driver);
			
		}
		if (driverRepository.findById(driverId).isEmpty())
			return ResponseHandler.responseBuilder("Requested driver does not exist", HttpStatus.NOT_FOUND, null);
		return ResponseHandler.responseBuilder("data is updated ", HttpStatus.OK,
				driverRepository.findById(driverId).get());
	}

	 public List<Driver> searchDrivers(String query) {
	        List<Driver> results = driverRepository.searchDriversByQuery(query);
	        logger.info("Search query: {}, Results: {}", query, results);
	        if (results.isEmpty()) {
	            logger.info("No drivers found for query: {}", query);
	        }
	        return results;
	    }
}
//private AffectDriverRepository affectDriverRepository;
//public Driver addDriver(Driver driver) {
//driver.setDriverId(UUID.randomUUID().toString().split("-")[0]);
//return driverRepository.save(new Driver(driver.getName(), driver.getFirstName(), driver.getNum(), null));
//}
// Method to search drivers by name or license
//public List<Driver> searchDrivers(String query) {
//  return driverRepository.searchDriversByQuery(query);
//  
//}
//public MappingJacksonValue filter1() {
//List<Driver> drivers = driverRepo.findAll();
//SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "firstName");
//FilterProvider filters = new SimpleFilterProvider().addFilter("DriverDetails", filter);
//MappingJacksonValue mapping = new MappingJacksonValue(drivers);
//mapping.setFilters(filters);
//return mapping;
//}
//public List<Driver> findByName(String name) {
//return driverRepo.findByName(name);
//}

