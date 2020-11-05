package com.starWars.rebels.controller;

import com.starWars.rebels.dto.MensajeResponse;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.service.TopSecretService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@Log4j2
@RestController
@RequestMapping("/topsecret")
@Api(basePath = "/topsecret", description = "Triangulación de coordenadas")
public class TopSecretController {

    @Autowired
    private TopSecretService topSecretService;

    @PostMapping
    @ApiOperation(value = "Codificar información de los satelites",
            notes = "Calcula la posicion de la nave a traves de la informacion obtenida de los satelites",
            response = MensajeResponse.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readTopSecret(@RequestBody RequestSatellites satelites) {
        try {
            MensajeResponse mensajeResponse = topSecretService.getLocation(satelites);
            return new ResponseEntity<>(mensajeResponse, HttpStatus.OK);
        } catch (HttpClientErrorException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
