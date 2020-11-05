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


    public double[][] posicionamientos(RequestSatellites requestSatellites, List<UbicacionSatelite> filtro) {
        Assert.notNull(requestSatellites.getSatellites(), "The list cannot be null");
        Assert.notEmpty(requestSatellites.getSatellites(), "The list cannot be empty");
        double[][] array = new double[filtro.size()][];
        for (int i = 0; i < filtro.size(); i++) {
            Assert.notNull(filtro.get(i), "The satellite cannot be null");
            UbicacionSatelite row = filtro.get(i);
            array[i] = new double[]{row.getX(), row.getY()};
        }
        return array;
    }

    public double[] distancias(RequestSatellites requestSatellites, List<UbicacionSatelite> filtro) {
        Assert.isTrue(filtro.size() > 2, "There is not enough information");
        val array = new double[filtro.size()];
        for (int i = 0; i < filtro.size(); i++) {
            for (Satellite ubicacion : requestSatellites.getSatellites()) {
                Assert.notNull(requestSatellites.getSatellites().get(i).getName(), "The name satellite cannot be null");
                if (ubicacion.getName().toUpperCase().equals(filtro.get(i).getName().toUpperCase())) {
                    Assert.isTrue(ubicacion.getDistance() > 1, "Distance cannot be zero");
                    array[i] = ubicacion.getDistance();
                    break;
                }
            }

        }
        return array;
    }

    public List<UbicacionSatelite> validarSatelitesRegistrado(RequestSatellites requestSatellites) {
        List<String> namesatellite = requestSatellites.getSatellites().stream().map(Satellite::getName).collect(Collectors.toList());
       return sateliteRepository.findByNameIgnoreCaseIn(namesatellite);
    }

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
