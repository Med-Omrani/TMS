package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

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
import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
		System.err.println("immatricule ::::::::: " );
		return vehicleService.saveVehicle(vehicle);
	}

	@PutMapping("/{vehicleId}/assignTrailer/{trailerId}")
	public void assignVehicleToDriver(@PathVariable String vehicleId, @PathVariable String trailerId) {
		vehicleService.assignTrailerToVehicle(vehicleId, trailerId);
	}
	@PutMapping("/{vehicleId}/releaseTrailer/{trailerId}")
	public void releaseTrailer(@PathVariable String vehicleId, @PathVariable String trailerId) {
	    vehicleService.releaseTrailerFromVehicle(vehicleId,trailerId);
	}

	@GetMapping("/getVehicles")
	public List<Vehicle> getVehicles() {
		return vehicleService.getAllVehicles();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getVehicle(@PathVariable String id) {
		return vehicleService.getVehicleById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteVehicle(@PathVariable String id) {
		System.err.println("immatricule ::::::::: " +id);
		return vehicleService.deleteVehicle(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateVehicle(@PathVariable String id, @RequestBody Vehicle vehicle) {
		System.err.println("immatricule ::::::::: " +id);
		vehicleService.updateVehicle(id, vehicle);
		return vehicleService.updateVehicle(id, vehicle);
	}
    @GetMapping("/search")
    public List<Vehicle> searchVehicles(@RequestParam("query") String query) {
        return vehicleService.searchVehicles(query);
    }

//	@GetMapping("/filter1")
//	public MappingJacksonValue getDriver() {
//		return driverService.filter1();
//	}

}
