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


import com.example.demo.entity.Trailer;

import com.example.demo.service.TrailerService;

import org.springframework.http.HttpStatus;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/trailer")
public class TrailerController {

	@Autowired
	private TrailerService trailerService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)

	public Trailer createTrailer(@RequestBody Trailer trailer) {
		return trailerService.saveTrailer(trailer);
	}

	@GetMapping("/getTrailers")
	public List<Trailer> getTrailers() {
		return trailerService.getAllTrailers();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getTrailer(@PathVariable String id) {
		return trailerService.getTrailerById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTrailer(@PathVariable String id) {
		return trailerService.deleteTrailer(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateTrailer(@PathVariable String id, @RequestBody Trailer trailer) {

		trailerService.updateTrailer(id, trailer);
		return trailerService.updateTrailer(id, trailer);
	}
    @GetMapping("/search")
    public List<Trailer> searchTrialers(@RequestParam("query") String query) {
        return trailerService.searchTrailers(query);
    }

    }
