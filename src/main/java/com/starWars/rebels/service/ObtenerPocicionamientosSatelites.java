package com.starWars.rebels.service;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.service.interfaces.ObtenerPocicionamientos;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ObtenerPocicionamientosSatelites implements ObtenerPocicionamientos {
    @Override
    public double[][] posicionamientos(RequestSatellites requestSatellites, List<UbicacionSatelite> ubicacionSatelites) {
        Assert.notNull(requestSatellites.getSatellites(), "The list cannot be null");
        Assert.notEmpty(requestSatellites.getSatellites(), "The list cannot be empty");
        double[][] array = new double[ubicacionSatelites.size()][];
        for (int i = 0; i < ubicacionSatelites.size(); i++) {
            Assert.notNull(ubicacionSatelites.get(i), "The satellite cannot be null");
            UbicacionSatelite row = ubicacionSatelites.get(i);
            array[i] = new double[]{row.getX(), row.getY()};
        }
        return array;
    }
}
