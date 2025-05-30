package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AffectDriver;
import com.example.demo.entity.Driver;
import com.example.demo.repository.AffectDriverRepository;
import com.example.demo.repository.DriverRepository;



@Service
public class AffectDriverService {
    @Autowired
    private AffectDriverRepository affectDriverRepository;
    @Autowired
    private DriverRepository driverRepository;

    private static final Logger logger = LoggerFactory.getLogger(AffectDriverService.class);

    public AffectDriver affectDriver(AffectDriver affect) throws IOException {
        if (affect == null) {
            throw new IOException("Invalid affect driver data");
        }
        return affectDriverRepository.save(affect);
    }

    public List<AffectDriver> getAllAffects() throws IOException {
        List<AffectDriver> affects = affectDriverRepository.findAll();
        if (affects.isEmpty()) {
            throw new IOException("No affects found");
        }
        return affects;
    }

    public Optional<AffectDriver> getAffectByID(String affectId) throws IOException {
        Optional<AffectDriver> affect = affectDriverRepository.findById(affectId);
        if (!affect.isPresent()) {
            throw new IOException("Affect with ID " + affectId + " not found");
        }
        return affect;
    }

    public void deleteDriverAffects(String affectId) throws IOException {
        if (!affectDriverRepository.existsById(affectId)) {
            throw new IOException("Affect with ID " + affectId + " not found");
        }
        affectDriverRepository.deleteById(affectId);
    }

    public AffectDriver updateAffectDriver(AffectDriver updatedAffect) throws IOException {
        if (updatedAffect == null || !affectDriverRepository.existsById(updatedAffect.getEnd())) {
            throw new IOException("Affect not found or invalid data");
        }
        return affectDriverRepository.save(updatedAffect);
    }


    public void assignAffectToDriver(String affectId, String driverId) throws IOException {
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        Optional<AffectDriver> affectOptional = affectDriverRepository.findById(affectId);

        if (!driverOptional.isPresent()) {
            throw new IOException("Driver with ID " + driverId + " not found");
        }
        if (!affectOptional.isPresent()) {
            throw new IOException("Affect with ID " + affectId + " not found");
        }

        AffectDriver affect = affectOptional.get();
        List<Driver> drivers = affect.getDrivers(); // Get the current list of drivers

        if (drivers == null) {
            drivers = new ArrayList<>(); // Initialize list if it's null
        }

        // Ensure the driver is not already assigned
        if (!drivers.contains(driverOptional.get())) {
            drivers.add(driverOptional.get());
            affect.setDrivers(drivers);
            affectDriverRepository.save(affect);
            logger.info("Assigned affect with ID {} to Driver with ID {}", affectId, driverId);
        } else {
            logger.warn("Driver with ID {} is already assigned to affect with ID {}", driverId, affectId);
        }
    }

    public void releaseAffectFromDriver(String affectId, String driverId) throws IOException {
        Optional<AffectDriver> affectOptional = affectDriverRepository.findById(affectId);
        Optional<Driver> driverOptional = driverRepository.findById(driverId);

        if (!affectOptional.isPresent()) {
            throw new IOException("Affect with ID " + affectId + " not found");
        }
        if (!driverOptional.isPresent()) {
            throw new IOException("Driver with ID " + driverId + " not found");
        }

        AffectDriver affect = affectOptional.get();
        List<Driver> drivers = affect.getDrivers();

        if (drivers != null && drivers.remove(driverOptional.get())) {
            affect.setDrivers(drivers);
            affectDriverRepository.save(affect);
            logger.info("Released driver with ID {} from affect with ID {}", driverId, affectId);
        } else {
            logger.warn("Driver with ID {} was not assigned to affect with ID {}", driverId, affectId);
        }
    }

}

//public void assignAffectToDriver(String affectId,String driverId) throws IOException {
//Optional<Driver> driverOptional = driverRepository.findById(driverId);
//Optional<AffectDriver> affectOptional = affectDriverRepository.findById(affectId);
//
//if (!driverOptional.isPresent()) {
//  throw new IOException("Driver with ID " + driverId + " not found");
//}
//if (!affectOptional.isPresent()) {
//  throw new IOException("Affect with ID " + affectId + " not found");
//}
//
//AffectDriver affect = affectOptional.get();
//affect.setDriver(driverOptional.get());
//affectDriverRepository.save(affect);
//
//logger.info("Assigned affect with ID {} to Driver with ID {}", affectId, driverId);
//}
//
//public void releaseAffectFromDriver(String affectId,String driverId) throws IOException {
//Optional<AffectDriver> affectOptional = affectDriverRepository.findById(affectId);
//
//if (!affectOptional.isPresent()) {
//  throw new IOException("Affect with ID " + affectId + " not found");
//}
//
//AffectDriver affect = affectOptional.get();
//affect.setDriver(null);
//affectDriverRepository.save(affect);
//
//logger.info("Released driver with ID {} from affect with ID {}", driverId, affectId);
//}


