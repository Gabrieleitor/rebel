package com.starWars.rebels.service;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.MensajeResponse;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.dto.Satellite;
import com.starWars.rebels.repository.UbicacionSateliteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TopSecretServiceTest {

    @InjectMocks
    private TopSecretService topSecretService;

    @Mock
    private SatelitesService satelitesService;

    @Mock
    private ConcatenateMenssageService concatenateMenssageService;

    @Mock
    private UbicacionSateliteRepository sateliteRepository;

    private static final double POSICION_X = -3.07862335999692;
    private static final double POSICION_Y = -3.004900038343084;
    private static final String MENSAJE_RESPUESTA = "este es un mensaje secreto";
    private static final String[] ARREGLO_MENSAJE_UNO = new String[]{"este", "", "", "mensaje"};
    private static final String[] ARREGLO_MENSAJE_DOS = new String[]{"", "es", "un", "", "secreto"};
    private static final double[] DISTANCIAS_SATELITES = new double[]{6.5, 9.0, 9.5, 5.7};
    private static final double[][] POSICIONES_SATELITES = new double[][]{{2.0, 1.0}, {5.0, 1.0}, {5.0, -8.0}, {1.0, 1.0}};

    @Test
    void getLocationTest() {
        //given
        List<Satellite> listaSatellite = new ArrayList<>();
        listaSatellite.add(Satellite.builder().message(ARREGLO_MENSAJE_UNO).build());
        listaSatellite.add(Satellite.builder().message(ARREGLO_MENSAJE_DOS).build());
        List<UbicacionSatelite> ubicacionSatelites = new ArrayList<>();
        RequestSatellites requestSatellites = RequestSatellites.builder().satellites(listaSatellite).build();
        when(satelitesService.validarSatelitesRegistrado(requestSatellites)).thenReturn(ubicacionSatelites);
        when(satelitesService.posicionamientos(any(), any())).thenReturn(POSICIONES_SATELITES);
        when(satelitesService.distancias(any(), any())).thenReturn(DISTANCIAS_SATELITES);
        when(concatenateMenssageService.getMessage(any())).thenReturn(MENSAJE_RESPUESTA);
        //when
        MensajeResponse mensajeResponse = topSecretService.getLocation(requestSatellites);
        //then
        Assertions.assertEquals(MENSAJE_RESPUESTA, mensajeResponse.getMensaje());
        Assertions.assertEquals(POSICION_X, mensajeResponse.getPosition().getX());
        Assertions.assertEquals(POSICION_Y, mensajeResponse.getPosition().getY());
    }


}