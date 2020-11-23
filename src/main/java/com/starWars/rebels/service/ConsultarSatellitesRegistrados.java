package com.starWars.rebels.service;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.dto.Satellite;
import com.starWars.rebels.repository.UbicacionSateliteRepository;
import com.starWars.rebels.service.interfaces.ConsultarSatellites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultarSatellitesRegistrados implements ConsultarSatellites {
    @Autowired
    private UbicacionSateliteRepository sateliteRepository;
    @Override
    public List<UbicacionSatelite> validarSatelitesRegistrado(RequestSatellites requestSatellites) {
        List<String> namesatellite = requestSatellites.getSatellites().stream().map(Satellite::getName).collect(Collectors.toList());
        return sateliteRepository.findByNameIgnoreCaseIn(namesatellite);
    }
}
