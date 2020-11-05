package com.starWars.rebels.controller;

import com.starWars.rebels.dto.MensajeResponse;
import com.starWars.rebels.dto.Satellite;
import com.starWars.rebels.dto.SatelliteSplitDto;
import com.starWars.rebels.service.TopSecretSplitService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@Log4j2
@RestController
@RequestMapping("/topsecret_split")
public class TopSecretSplitController {

    @Autowired
    private TopSecretSplitService topSecretSplitService;

    @PostMapping("/{satellite_name}")
    public ResponseEntity<String> registrarInformacionSatelite(@PathVariable(name = "satellite_name") String nombreSatelite, @RequestBody SatelliteSplitDto satelliteSplitDto) {
        try {
            Satellite satellite = Satellite.builder()
                    .distance(satelliteSplitDto.getDistance())
                    .message(satelliteSplitDto.getMessage())
                    .name(nombreSatelite).build();
            topSecretSplitService.registrarInformacionSatellites(satellite);
            return new ResponseEntity<>("informarción guardada correctamente", HttpStatus.CREATED);
        } catch (HttpClientErrorException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> consultarInformacionSatelite() {
        try {
            MensajeResponse mensajeResponse = topSecretSplitService.consultarInformacionSatelite();
            return new ResponseEntity<>(mensajeResponse, HttpStatus.OK);
        } catch (HttpClientErrorException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
