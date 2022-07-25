package net.dg.docker.controller;

import lombok.AllArgsConstructor;
import net.dg.docker.model.ThemeParkRide;
import net.dg.docker.repository.ThemeParkRideRepository;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.context.Theme;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/ride")
public class ThemeParkRideController {

    private static final Logger logger = LoggerFactory.getLogger(ThemeParkRideController.class);


    private final ThemeParkRideRepository themeParkRideRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ThemeParkRide> getAllRides() {

        logger.info("Inside getAllRides method of ThemeParkRideController.");
        return themeParkRideRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThemeParkRide> getThemeParkRideById(@PathVariable("id") long id) {

        logger.info("Inside getThemeParkRideById method of ThemeParkRideController.");

        Optional<ThemeParkRide> themeParkRide = themeParkRideRepository.findById(id);

        if(themeParkRide.isPresent()){
            return new ResponseEntity<>(themeParkRide.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ThemeParkRide createRide(@Valid @RequestBody ThemeParkRide themeParkRide) {

        logger.info("Inside createRide method of ThemeParkRideController.");
        return themeParkRideRepository.save(themeParkRide);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThemeParkRide> updateThemeParkRideById(@PathVariable("id") long id, @RequestBody ThemeParkRide themeParkRide){

        logger.info("Inside updateThemeParkRideById method of ThemeParkRideController.");

        Optional<ThemeParkRide> themeParkToBeUpdated = themeParkRideRepository.findById(id);
        if(themeParkToBeUpdated.isPresent()){
            ThemeParkRide themeParkRideUpdated = themeParkToBeUpdated.get();
            themeParkRideUpdated.setName(themeParkRide.getName());
            themeParkRideUpdated.setDescription(themeParkRide.getDescription());
            themeParkRideUpdated.setThrillFactor(themeParkRide.getThrillFactor());

            return new ResponseEntity<>(themeParkRideRepository.save(themeParkRideUpdated), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteRideById(@PathVariable("id") long id){

        logger.info("Inside deleteRideById method of ThemeParkRideController.");

        try {
            themeParkRideRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
}
