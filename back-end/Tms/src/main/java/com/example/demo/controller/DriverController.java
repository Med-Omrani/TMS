package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Driver;
import com.example.demo.service.DriverService;

import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Driver createDriver(@RequestBody Driver driver) {
		System.err.println("driverId ::::::::: " );
		return driverService.saveDriver(driver);
	}

	@GetMapping("/getDrivers")
	public List<Driver> getDrivers() {
		return driverService.getAllDrivers();
	}

	@PutMapping("/{driverId}/assignVehicle/{vehicleId}")
	public void assignVehicleToDriver(@PathVariable String driverId, @PathVariable String vehicleId) {
		driverService.assignVehicleToDriver(driverId, vehicleId);
	}
	@PutMapping("/{driverId}/releaseVehicle/{vehicleId}")
	public void releaseVehicleFromDriver(@PathVariable String driverId, @PathVariable String vehicleId) {
		driverService.releaseVehicleFromDriver(driverId, vehicleId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getDriver(@PathVariable String id) {
		return driverService.getDriverByID(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDriverSeccusfuly(@PathVariable String id) {
		System.err.println("driverId ::::::::: " + id);
		return driverService.deleteDriver(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateDriver(@PathVariable String id, @RequestBody Driver driver) {
		System.err.println("driverId ::::::::: " + id);
		driverService.updateDriver(id, driver);
		return driverService.updateDriver(id, driver);
	}

    // Search endpoint to find drivers by name or license
    @GetMapping("/search")
    public List<Driver> searchDrivers(@RequestParam("query") String query) {
        return driverService.searchDrivers(query);
    }
   

//	@GetMapping("/filter1")
//	public MappingJacksonValue getDriver() {
//		return driverService.filter1();
//	}

}
