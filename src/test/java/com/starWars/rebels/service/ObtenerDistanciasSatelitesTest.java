package com.starWars.rebels.service;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.dto.Satellite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ObtenerDistanciasSatelitesTest {

    private static final double DISTACIA_SATELITE_TRES = 250;
    private static final double POSICION_SATELITE_TRES_X = 500;
    private static final double POSICION_SATELITE_TRES_Y = 800;
    private static final String NOMBRE_SATELITE_TRES = "sato";

    @InjectMocks
    private ObtenerDistanciasSatelites obtenerDistanciasSatelites;

    @Test
    void givenAListThenThrowIllegalArgumentException() {
        //given
        RequestSatellites requestSatellites = ConstruirRequestSatellites.getRequestSatellites();
        List<UbicacionSatelite> ubicacionSateliteList = ConstruirUbicacionSatelite.getUbicacionSatelites();
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            obtenerDistanciasSatelites.distancias(requestSatellites, ubicacionSateliteList);
        });
    }

    @Test
    void givenAListSatellitesThenDistance() {
        //given
        RequestSatellites requestSatellites = ConstruirRequestSatellites.getRequestSatellites();
        requestSatellites.getSatellites().add(Satellite.builder().name(NOMBRE_SATELITE_TRES).distance(DISTACIA_SATELITE_TRES).build());
        List<UbicacionSatelite> ubicacionSateliteList = new ArrayList<>();
        ubicacionSateliteList.add(UbicacionSatelite.builder()
                .x(ConstruirUbicacionSatelite.POSICION_SATELITE_UNO_X)
                .y(ConstruirUbicacionSatelite.POSICION_SATELITE_UNO_Y)
                .name(ConstruirRequestSatellites.NOMBRE_SATELITE_UNO).build());
        ubicacionSateliteList.add(UbicacionSatelite.builder()
                .x(ConstruirUbicacionSatelite.POSICION_SATELITE_DOS_X)
                .y(ConstruirUbicacionSatelite.POSICION_SATELITE_DOS_Y)
                .name(ConstruirRequestSatellites.NOMBRE_SATELITE_DOS).build());
        ubicacionSateliteList.add(UbicacionSatelite.builder()
                .x(POSICION_SATELITE_TRES_X)
                .y(POSICION_SATELITE_TRES_Y)
                .name(NOMBRE_SATELITE_TRES).build());
        //when
        double[] respuesta = obtenerDistanciasSatelites.distancias(requestSatellites, ubicacionSateliteList);
        //then
        Assertions.assertEquals(ConstruirRequestSatellites.DISTACIA_SATELITE_UNO, respuesta[0]);
        Assertions.assertEquals(ConstruirRequestSatellites.DISTACIA_SATELITE_DOS, respuesta[1]);
        Assertions.assertEquals(DISTACIA_SATELITE_TRES, respuesta[2]);
    }

    @Test
    void givenANamelessThenThrowIllegalArgumentException() {
        //given
        RequestSatellites requestSatellites = ConstruirRequestSatellites.getRequestSatellites();
        requestSatellites.getSatellites().get(0).setName(null);
        List<UbicacionSatelite> ubicacionSateliteList = ConstruirUbicacionSatelite.getUbicacionSatelites();
        ubicacionSateliteList.add(UbicacionSatelite.builder()
                .x(POSICION_SATELITE_TRES_X)
                .y(POSICION_SATELITE_TRES_Y).build());
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            obtenerDistanciasSatelites.distancias(requestSatellites, ubicacionSateliteList);
        });
    }
}