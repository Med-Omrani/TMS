package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AffectDriver;
//import com.example.demo.entity.Driver;
import com.example.demo.service.AffectDriverService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/affect")
//public class AffectDriverController {
//	@Autowired
//	
//	private AffectDriverService affectDriverService;
//	@PostMapping("/add")
//	@ResponseStatus(HttpStatus.CREATED)
//
//	public void assignAffectToDriver(@RequestBody AffectDriver affect ) {
//	affectDriverService.affectDriver(affect);
//
//   
//	}
//	@DeleteMapping("/{affectId}")
//	public ResponseEntity<String> deleteDriverSeccusfuly( @PathVariable String affectId) {
//		 affectDriverService.deleteDriverAffects(affectId);
//	        return ResponseEntity.ok("affect with ID: " + affectId + " is removed ");
//
//	}
//
//	@GetMapping("/getAffects")
//	public List<AffectDriver> getAffects() {
//		return affectDriverService.getAllAffects();
//	}
//	@GetMapping("/{id}")
//	public ResponseEntity<Object> getAffect(@PathVariable String id) {
//		return affectDriverService.getAffectByID(id);
//	}
//	@PutMapping("/{driverId}/assignAffect/{affectId}")
//	public void assignAffectToDriver(@PathVariable String driverId, @PathVariable String affectId) {
//		affectDriverService.assignAffectToDriver(driverId, affectId);
//	}
//	@PutMapping("/{driverId}/releaseAffect/{affectId}")
//	public void releaseAffectFromDriver(@PathVariable String driverId, @PathVariable String affectId) {
//		affectDriverService.releaseAffectFromDriver(driverId, affectId);
//	}
//}
//	@PutMapping("/{id}")
//	public void updateDriver(@PathVariable String id, @RequestBody AffectDriver driver) {
//
//		affectDriverService.updateAffectDriver(id, driver);
//	}

//	public ResponseEntity<Object>  addAffect (@RequestBody AffectDriver affectDriver) {
//		return affectDriverService.addAffect(affectDriver);
//		
//	}
//	@PutMapping("/{affectId}/assignDriver/{driverId}")
//	public ResponseEntity<String> assignVehicleToDriver(@PathVariable String affectId, @PathVariable String driverId) {
//		affectDriverService.assignAffectToDriver(affectId,driverId);
//
//        return ResponseEntity.ok("Driver with ID: " + driverId + " assigned to AffectDriver with ID: " + affectId);
//	}
//	@GetMapping("/{id}")
//	public ResponseEntity<Object> getDriver(@PathVariable String id) {
//		return affectDriverService.getDriverByID(id);
//	}
//	@GetMapping("/getAffects")
//    public List<AffectDriver> getDrivers ()
//    {
//		return affectDriverService.GetAllAffects();
//    	
//    }
//	@DeleteMapping("/{affectId}/releaseDriver/{driverId}")
//	public ResponseEntity<String> deleteDriverSeccusfuly(@PathVariable String affectId, @PathVariable String driverId) {
//		 affectDriverService.deleteDriverFromAffect(affectId,driverId);
//	        return ResponseEntity.ok("Driver with ID: " + driverId + " is released from AffectDriver with ID: " + affectId);
//
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<Object> updateDriver(@PathVariable String id, @RequestBody AffectDriver driver) {
//
//		return affectDriverService.updateAffectDriver(id, driver);
//	}
@RestController
@RequestMapping("/affect")
public class AffectDriverController {

    @Autowired
    private AffectDriverService affectDriverService;

    @GetMapping("/getAffects")
    public ResponseEntity<List<AffectDriver>> getAllAffects() throws IOException {
        List<AffectDriver> affects = affectDriverService.getAllAffects();
        return new ResponseEntity<>(affects, HttpStatus.OK);
    }

    @GetMapping("/getAffect/{id}")
    public ResponseEntity<AffectDriver> getAffectById(@PathVariable String id) throws IOException {
        Optional<AffectDriver> affect = affectDriverService.getAffectByID(id);
        return affect.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addAffect")
    public ResponseEntity<AffectDriver> createAffect(@RequestBody AffectDriver affect) throws IOException {
        AffectDriver createdAffect = affectDriverService.affectDriver(affect);
        return new ResponseEntity<>(createdAffect, HttpStatus.CREATED);
    }

    @PutMapping("/updateAffect/{id}")
    public ResponseEntity<AffectDriver> updateAffect(@PathVariable String id, @RequestBody AffectDriver affect) throws IOException {
        Optional<AffectDriver> existingAffect = affectDriverService.getAffectByID(id);
        if (!existingAffect.isPresent()) {
            return ResponseEntity.notFound().build();
        }
      
        AffectDriver updatedAffect = affectDriverService.affectDriver(affect);
        return ResponseEntity.ok(updatedAffect);
    }

    @DeleteMapping("/deleteAffect/{id}")
    public ResponseEntity<Void> deleteAffect(@PathVariable String id) throws IOException {
        Optional<AffectDriver> affect = affectDriverService.getAffectByID(id);
        if (!affect.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        affectDriverService.deleteDriverAffects(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{affectId}/assignAffect/{driverId}")
    public ResponseEntity<Void> assignAffectToDriver(@PathVariable String driverId, @PathVariable String affectId) throws IOException {
        affectDriverService.assignAffectToDriver(affectId,driverId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{affectId}/releaseAffect/{driverId}")
    public ResponseEntity<Void> releaseAffectFromDriver(@PathVariable String driverId, @PathVariable String affectId) throws IOException {
        affectDriverService.releaseAffectFromDriver(affectId,driverId);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}




