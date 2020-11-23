package com.starWars.rebels.service;

import com.starWars.rebels.domain.InformacionSatelite;
import com.starWars.rebels.dto.Satellite;
import com.starWars.rebels.repository.InformacionSateliteRepository;
import com.starWars.rebels.service.interfaces.RegistrarSatellite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Arrays;

@Service
public class RegistrarSatelliteWeb implements RegistrarSatellite {
    @Autowired
    private InformacionSateliteRepository informacionSateliteRepository;

    @Override
    public void registrar(Satellite satellite) {
        Assert.notNull(satellite, "The satellite cannot be null");
        Assert.notNull(satellite.getName(), "The name satellite cannot be null");
        Assert.notEmpty(satellite.getMessage(), "The message cannot be empty");
        Assert.isTrue(satellite.getDistance() > 0, "The distance cannot be cero");
        InformacionSatelite informacionSatelite = InformacionSatelite.builder()
                .distancia(satellite.getDistance())
                .mensaje(Arrays.asList(satellite.getMessage()))
                .nombre(satellite.getName()).build();
        informacionSateliteRepository.save(informacionSatelite);
    }
}
