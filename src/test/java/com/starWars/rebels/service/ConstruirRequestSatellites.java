package com.starWars.rebels.service;

import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.dto.Satellite;

import java.util.ArrayList;
import java.util.List;

public class ConstruirRequestSatellites {
    public static final String NOMBRE_SATELITE_UNO = "kenobi";
    public static final String NOMBRE_SATELITE_DOS = "skywalker";
    public static final double DISTACIA_SATELITE_UNO = 100;
    public static final double DISTACIA_SATELITE_DOS = 150;

    public static RequestSatellites getRequestSatellites() {
        RequestSatellites requestSatellites = new RequestSatellites();
        List<Satellite> satellites = new ArrayList<>();
        satellites.add(Satellite.builder().name(NOMBRE_SATELITE_UNO).distance(DISTACIA_SATELITE_UNO).build());
        satellites.add(Satellite.builder().name(NOMBRE_SATELITE_DOS).distance(DISTACIA_SATELITE_DOS).build());
        requestSatellites.setSatellites(satellites);
        return requestSatellites;
    }
}
