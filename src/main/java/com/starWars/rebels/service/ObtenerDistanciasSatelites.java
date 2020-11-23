package com.starWars.rebels.service;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.dto.Satellite;
import com.starWars.rebels.service.interfaces.ObtenerDistancias;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ObtenerDistanciasSatelites implements ObtenerDistancias {
    @Override
    public double[] distancias(RequestSatellites requestSatellites, List<UbicacionSatelite> ubicacionSatelites) {
        Assert.isTrue(ubicacionSatelites.size() > 2, "There is not enough information");
        val array = new double[ubicacionSatelites.size()];
        for (int i = 0; i < ubicacionSatelites.size(); i++) {
            for (Satellite ubicacion : requestSatellites.getSatellites()) {
                Assert.notNull(requestSatellites.getSatellites().get(i).getName(), "The name satellite cannot be null");
                if (ubicacion.getName().toUpperCase().equals(ubicacionSatelites.get(i).getName().toUpperCase())) {
                    Assert.isTrue(ubicacion.getDistance() > 1, "Distance cannot be zero");
                    array[i] = ubicacion.getDistance();
                    break;
                }
            }

        }
        return array;
    }
}
