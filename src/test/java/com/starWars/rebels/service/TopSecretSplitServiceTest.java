package com.starWars.rebels.service;

import com.starWars.rebels.domain.InformacionSatelite;
import com.starWars.rebels.dto.MensajeResponse;
import com.starWars.rebels.dto.Satellite;
import com.starWars.rebels.repository.InformacionSateliteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TopSecretSplitServiceTest {

    @InjectMocks
    private TopSecretSplitService topSecretSplitService;
    @Mock
    private InformacionSateliteRepository informacionSateliteRepository;
    @Mock
    private TopSecretService topSecretService;

    private static final String NOMBRE_SATELITE = "kenobi";
    private static final String[] ARREGLO_MENSAJE = new String[]{"este", "", "", "mensaje", "secreto"};
    private static final double DISTACIA_SATELITE = 100;
    private static final String MENSAJE_RESPUESTA = "este es un mensaje secreto";

    @Test
    void giveASatelliteWhenregistrarInformacionSatellitesThenOk() {
        //given
        Satellite satellite = Satellite.builder()
                .name(NOMBRE_SATELITE)
                .message(ARREGLO_MENSAJE)
                .distance(DISTACIA_SATELITE)
                .build();
        InformacionSatelite informacionSatelite = getInformacionSatelite();
        //when
        topSecretSplitService.registrarInformacionSatellites(satellite);
        //then
        verify(informacionSateliteRepository).save(informacionSatelite);
    }

    private InformacionSatelite getInformacionSatelite() {
        InformacionSatelite informacionSatelite = InformacionSatelite.builder()
                .nombre(NOMBRE_SATELITE)
                .mensaje(Arrays.asList(ARREGLO_MENSAJE))
                .distancia(DISTACIA_SATELITE)
                .build();
        return informacionSatelite;
    }

    @Test
    void testConsultarInformacionSateliteByGeneradoIsFalseThenRunOk() {
        //given
        List<InformacionSatelite> informacionSatelites = new ArrayList<>();
        informacionSatelites.add(getInformacionSatelite());
        MensajeResponse mensajePrueba = MensajeResponse.builder().mensaje(MENSAJE_RESPUESTA).build();
        when(informacionSateliteRepository.findAllByGeneradoIsFalse()).thenReturn(informacionSatelites);
        when(topSecretService.getLocation(any())).thenReturn(mensajePrueba);
        //when
        MensajeResponse mensajeResponse = topSecretSplitService.consultarInformacionSatelite();
        //then
        Assertions.assertNotNull(mensajeResponse);
        Assertions.assertEquals(MENSAJE_RESPUESTA,mensajeResponse.getMensaje());

    }


}