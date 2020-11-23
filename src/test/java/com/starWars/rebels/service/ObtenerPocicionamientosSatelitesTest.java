package com.starWars.rebels.service;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.RequestSatellites;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ObtenerPocicionamientosSatelitesTest {

    private static final double[][] POSICIONES_SATELITES = new double[][]{{200, 100}, {500, -200}};


    @InjectMocks
    private ObtenerPocicionamientosSatelites obtenerPocicionamientosSatelites;

    @Test
    void givenAListSatellitesThenPosition() {
        //given
        RequestSatellites requestSatellites = ConstruirRequestSatellites.getRequestSatellites();
        List<UbicacionSatelite> ubicacionSateliteList = ConstruirUbicacionSatelite.getUbicacionSatelites();
        //when
        double[][] respuesta = obtenerPocicionamientosSatelites.posicionamientos(requestSatellites, ubicacionSateliteList);
        //then
        Assertions.assertTrue(Arrays.equals(POSICIONES_SATELITES[0], respuesta[0]));
        Assertions.assertTrue(Arrays.equals(POSICIONES_SATELITES[1], respuesta[1]));
    }

    @Test
    void givenAEmptyThenThrowIllegalArgumentException() {
        //given
        RequestSatellites requestSatellites = new RequestSatellites();
        requestSatellites.setSatellites(new ArrayList<>());
        List<UbicacionSatelite> ubicacionSateliteList = new ArrayList<>();
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            obtenerPocicionamientosSatelites.posicionamientos(requestSatellites, ubicacionSateliteList);
        });
    }

    @Test
    void givenANullThenThrowIllegalArgumentException() {
        //given
        RequestSatellites requestSatellites = new RequestSatellites();
        List<UbicacionSatelite> ubicacionSateliteList = new ArrayList<>();
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            obtenerPocicionamientosSatelites.posicionamientos(requestSatellites, ubicacionSateliteList);
        });
    }
}