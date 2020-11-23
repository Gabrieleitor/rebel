package com.starWars.rebels.service.interfaces;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.RequestSatellites;

import java.util.List;

public interface ConsultarSatellites {
    List<UbicacionSatelite> validarSatelitesRegistrado(RequestSatellites requestSatellites);

}
