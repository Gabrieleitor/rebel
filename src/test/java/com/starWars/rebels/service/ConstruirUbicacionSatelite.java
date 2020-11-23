package com.starWars.rebels.service;

import com.starWars.rebels.domain.UbicacionSatelite;

import java.util.ArrayList;
import java.util.List;

public class ConstruirUbicacionSatelite {

    public static final double POSICION_SATELITE_UNO_X = 200;
    public static final double POSICION_SATELITE_UNO_Y = 100;
    public static final double POSICION_SATELITE_DOS_X = 500;
    public static final double POSICION_SATELITE_DOS_Y = -200;

    public static List<UbicacionSatelite> getUbicacionSatelites() {
        List<UbicacionSatelite> ubicacionSateliteList = new ArrayList<>();
        ubicacionSateliteList.add(UbicacionSatelite.builder().x(POSICION_SATELITE_UNO_X).y(POSICION_SATELITE_UNO_Y).build());
        ubicacionSateliteList.add(UbicacionSatelite.builder().x(POSICION_SATELITE_DOS_X).y(POSICION_SATELITE_DOS_Y).build());
        return ubicacionSateliteList;
    }
}
