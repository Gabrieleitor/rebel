package com.starWars.rebels.service;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.repository.UbicacionSateliteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ConsultarSatellitesRegistradosTest {
    @InjectMocks
    private ConsultarSatellitesRegistrados consultarSatellitesRegistrados;

    @Mock
    private UbicacionSateliteRepository sateliteRepository;

    private static final double POSICION_SATELITE_TRES_X = 500;
    private static final double POSICION_SATELITE_TRES_Y = 800;


    @Test
    void givenAListThenfindByNameIgnoreCaseIn() {
        //given
        RequestSatellites requestSatellites =ConstruirRequestSatellites.getRequestSatellites();
        List<UbicacionSatelite> ubicacionSateliteList = ConstruirUbicacionSatelite.getUbicacionSatelites();
        ubicacionSateliteList.add(UbicacionSatelite.builder().x(POSICION_SATELITE_TRES_X).y(POSICION_SATELITE_TRES_Y).build());
        when(sateliteRepository.findByNameIgnoreCaseIn(any())).thenReturn(ubicacionSateliteList);
        //when
        List<UbicacionSatelite> ubicacionSatelites = consultarSatellitesRegistrados.validarSatelitesRegistrado(requestSatellites);
        //then
        Assertions.assertEquals(ubicacionSateliteList, ubicacionSatelites);

    }

}