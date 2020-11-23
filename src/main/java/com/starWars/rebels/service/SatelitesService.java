package com.starWars.rebels.service;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.dto.Satellite;
import com.starWars.rebels.dto.SatelliteDto;
import com.starWars.rebels.repository.UbicacionSateliteRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SatelitesService {

    @Autowired
    private UbicacionSateliteRepository sateliteRepository;

    @Autowired
    private ConversionService conversionService;

    public SatelliteDto findById(Long id) {
        Assert.notNull(id, "The satellite id cannot be null");
        UbicacionSatelite ubicacionSatelite = sateliteRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Satellite not found"));
        return conversionService.convert(ubicacionSatelite, SatelliteDto.class);
    }

    public SatelliteDto save(SatelliteDto satelliteDto) {
        Assert.notNull(satelliteDto, "The satellite cannot be null");
        Assert.notNull(satelliteDto.getName(), "The satellite name cannot be null");
        UbicacionSatelite ubicacionSatelite = conversionService.convert(satelliteDto, UbicacionSatelite.class);
        Assert.notNull(ubicacionSatelite, "The satellite name cannot be null");
        ubicacionSatelite = sateliteRepository.save(ubicacionSatelite);
        return conversionService.convert(ubicacionSatelite, SatelliteDto.class);
    }

    public void deleteById(Long id) {
        Assert.notNull(id, "The satellite id cannot be null");
        sateliteRepository.deleteById(id);
    }

    public List<SatelliteDto> findAll() {
        return sateliteRepository.findAll().stream()
                .map(ubicacionSatelite -> conversionService.convert(ubicacionSatelite, SatelliteDto.class))
                .collect(Collectors.toList());
    }

}
