package net.dg.docker.controller;

import lombok.AllArgsConstructor;
import net.dg.docker.model.ThemeParkRide;
import net.dg.docker.repository.ThemeParkRideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class ThemeParkRideController {

    private static final Logger logger = LoggerFactory.getLogger(ThemeParkRideController.class);


    private final ThemeParkRideRepository themeParkRideRepository;

    @GetMapping(value = "/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ThemeParkRide> getAllRides() {

        logger.info("Inside getAllRides method of ThemeParkRideController");
        return themeParkRideRepository.findAll();
    }

    @GetMapping(value = "/ride/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ThemeParkRide getThemeParkRideById(@PathVariable("id") long id) {

        logger.info("Inside getThemeParkRideById method of ThemeParkRideController");
        return themeParkRideRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Couldn't find rid with id %s", id)));
    }

    @PostMapping(value = "/ride", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ThemeParkRide createRide(@Valid @RequestBody ThemeParkRide themeParkRide) {

        logger.info("Inside createRide method of ThemeParkRideController");
        return themeParkRideRepository.save(themeParkRide);
    }
}
