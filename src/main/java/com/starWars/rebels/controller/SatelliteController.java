package com.starWars.rebels.controller;

import com.starWars.rebels.dto.SatelliteDto;
import com.starWars.rebels.service.SatelitesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/satellites")
public class SatelliteController {
    @Autowired
    private SatelitesService satelitesService;

    @GetMapping
    public ResponseEntity<?> getSatellites() {
        try {
            List<SatelliteDto> listasatellites = satelitesService.findAll();
            return new ResponseEntity<>(listasatellites, HttpStatus.OK);
        } catch (HttpClientErrorException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSatellite(@PathVariable Long id) {
        try {
            SatelliteDto satelliteDto = satelitesService.findById(id);
            return new ResponseEntity<>(satelliteDto, HttpStatus.CREATED);
        }catch (HttpClientErrorException | IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity<?> saveSatellite(@RequestBody SatelliteDto satelliteDto) {
        try {
            SatelliteDto save = satelitesService.save(satelliteDto);
            return new ResponseEntity<>(save, HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSatellite(@RequestBody SatelliteDto satelliteDto) {
        try {
            SatelliteDto save = satelitesService.save(satelliteDto);
            return new ResponseEntity<>(save, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteSatellite(@PathVariable Long id) {
        try {
            satelitesService.deleteById(id);
            log.warn("satellite {} was eliminated", id);
            return new ResponseEntity<>("information successfully removed", HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
