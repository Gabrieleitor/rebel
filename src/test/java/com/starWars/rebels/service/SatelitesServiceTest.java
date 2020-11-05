package com.starWars.rebels.service;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.dto.Satellite;
import com.starWars.rebels.dto.SatelliteDto;
import com.starWars.rebels.repository.UbicacionSateliteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SatelitesServiceTest {

    @InjectMocks
    private SatelitesService satelitesService;

    @Mock
    private UbicacionSateliteRepository sateliteRepository;

    @Mock
    private ConversionService conversionService;

    private static final double[][] POSICIONES_SATELITES = new double[][]{{200, 100}, {500, -200}};
    private static final double DISTACIA_SATELITE_UNO = 100;
    private static final double DISTACIA_SATELITE_DOS = 150;
    private static final double DISTACIA_SATELITE_TRES = 250;
    private static final double POSICION_SATELITE_UNO_X = 200;
    private static final double POSICION_SATELITE_UNO_Y = 100;
    private static final double POSICION_SATELITE_DOS_X = 500;
    private static final double POSICION_SATELITE_DOS_Y = -200;
    private static final double POSICION_SATELITE_TRES_X = 500;
    private static final double POSICION_SATELITE_TRES_Y = 800;
    private static final long ID_SATELITE_UNO = 1L;
    private static final int TAMANO_LISTA = 1;
    private static final String NOMBRE_SATELITE_UNO = "kenobi";
    private static final String NOMBRE_SATELITE_DOS = "skywalker";
    private static final String NOMBRE_SATELITE_TRES = "sato";

    @Test
    void givenANullThenThrowIllegalArgumentException() {
        //given
        RequestSatellites requestSatellites = new RequestSatellites();
        List<UbicacionSatelite> ubicacionSateliteList = new ArrayList<>();
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            satelitesService.posicionamientos(requestSatellites, ubicacionSateliteList);
        });
    }

    @Test
    void givenAEmptyThenThrowIllegalArgumentException() {
        //given
        RequestSatellites requestSatellites = new RequestSatellites();
        requestSatellites.setSatellites(new ArrayList<>());
        List<UbicacionSatelite> ubicacionSateliteList = new ArrayList<>();
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            satelitesService.posicionamientos(requestSatellites, ubicacionSateliteList);
        });
    }

    @Test
    void givenAListSatellitesThenPosition() {
        //given
        RequestSatellites requestSatellites = getRequestSatellites();
        List<UbicacionSatelite> ubicacionSateliteList = getUbicacionSatelites();
        //when
        double[][] respuesta = satelitesService.posicionamientos(requestSatellites, ubicacionSateliteList);
        //then
        Assertions.assertTrue(Arrays.equals(POSICIONES_SATELITES[0], respuesta[0]));
        Assertions.assertTrue(Arrays.equals(POSICIONES_SATELITES[1], respuesta[1]));
    }

    @Test
    void givenAListSatellitesThenDistance() {
        //given
        RequestSatellites requestSatellites = getRequestSatellites();
        requestSatellites.getSatellites().add(Satellite.builder().name(NOMBRE_SATELITE_TRES).distance(DISTACIA_SATELITE_TRES).build());
        List<UbicacionSatelite> ubicacionSateliteList = new ArrayList<>();
        ubicacionSateliteList.add(UbicacionSatelite.builder().x(POSICION_SATELITE_UNO_X).y(POSICION_SATELITE_UNO_Y).name(NOMBRE_SATELITE_UNO).build());
        ubicacionSateliteList.add(UbicacionSatelite.builder().x(POSICION_SATELITE_DOS_X).y(POSICION_SATELITE_DOS_Y).name(NOMBRE_SATELITE_DOS).build());
        ubicacionSateliteList.add(UbicacionSatelite.builder().x(POSICION_SATELITE_TRES_X).y(POSICION_SATELITE_TRES_Y).name(NOMBRE_SATELITE_TRES).build());
        //when
        double[] respuesta = satelitesService.distancias(requestSatellites, ubicacionSateliteList);
        //then
        Assertions.assertEquals(DISTACIA_SATELITE_UNO, respuesta[0]);
        Assertions.assertEquals(DISTACIA_SATELITE_DOS, respuesta[1]);
        Assertions.assertEquals(DISTACIA_SATELITE_TRES, respuesta[2]);
    }

    @Test
    void givenAListThenThrowIllegalArgumentException() {
        //given
        RequestSatellites requestSatellites = getRequestSatellites();
        List<UbicacionSatelite> ubicacionSateliteList = getUbicacionSatelites();
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            satelitesService.distancias(requestSatellites, ubicacionSateliteList);
        });
    }

    @Test
    void givenANamelessThenThrowIllegalArgumentException() {
        //given
        RequestSatellites requestSatellites = getRequestSatellites();
        requestSatellites.getSatellites().get(0).setName(null);
        List<UbicacionSatelite> ubicacionSateliteList = getUbicacionSatelites();
        ubicacionSateliteList.add(UbicacionSatelite.builder().x(POSICION_SATELITE_TRES_X).y(POSICION_SATELITE_TRES_Y).build());
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            satelitesService.distancias(requestSatellites, ubicacionSateliteList);
        });
    }

    @Test
    void givenAListThenfindByNameIgnoreCaseIn() {
        //given
        RequestSatellites requestSatellites = getRequestSatellites();
        List<UbicacionSatelite> ubicacionSateliteList = getUbicacionSatelites();
        ubicacionSateliteList.add(UbicacionSatelite.builder().x(POSICION_SATELITE_TRES_X).y(POSICION_SATELITE_TRES_Y).build());
        when(sateliteRepository.findByNameIgnoreCaseIn(any())).thenReturn(ubicacionSateliteList);
        //when
        List<UbicacionSatelite> ubicacionSatelites = satelitesService.validarSatelitesRegistrado(requestSatellites);
        //then
        Assertions.assertEquals(ubicacionSateliteList, ubicacionSatelites);

    }

    @Test
    void testFindAllShouldRunOk() {
        //given
        UbicacionSatelite ubicacionSatelite = UbicacionSatelite.builder().id(ID_SATELITE_UNO).name(NOMBRE_SATELITE_UNO).x(POSICION_SATELITE_UNO_X).y(POSICION_SATELITE_UNO_Y).build();
        List<UbicacionSatelite> arrayList = Arrays.asList(ubicacionSatelite);
        when(sateliteRepository.findAll()).thenReturn(arrayList);
        //when
        List<SatelliteDto> respuestaFindAll = satelitesService.findAll();
        //then
        Assertions.assertNotNull(respuestaFindAll);
        Assertions.assertEquals(TAMANO_LISTA, respuestaFindAll.size());
    }

    @Test
    void givenAUbicacionSateliteWhenSaveThenSatellteDto() {
        //given
        UbicacionSatelite ubicacionSatelite = UbicacionSatelite.builder().id(ID_SATELITE_UNO).name(NOMBRE_SATELITE_UNO).x(POSICION_SATELITE_UNO_X).y(POSICION_SATELITE_UNO_Y).build();
        SatelliteDto satelliteDto = SatelliteDto.builder().id(ID_SATELITE_UNO).name(NOMBRE_SATELITE_UNO).x(POSICION_SATELITE_UNO_X).y(POSICION_SATELITE_UNO_Y).build();
        doReturn(ubicacionSatelite).when(conversionService).convert(satelliteDto, UbicacionSatelite.class);
        doReturn(satelliteDto).when(conversionService).convert(ubicacionSatelite, SatelliteDto.class);
        when(sateliteRepository.save(any())).thenReturn(ubicacionSatelite);
        //when
        SatelliteDto satelliteDto1 = satelitesService.save(satelliteDto);
        //then
        Assertions.assertNotNull(satelliteDto1);
        Assertions.assertEquals(ubicacionSatelite.getId(), satelliteDto1.getId());
    }

    @Test
    void givenAIdWhenFindIdThenUbicacionSatelite() {
        //given
        UbicacionSatelite ubicacionSatelite = UbicacionSatelite.builder().id(ID_SATELITE_UNO).name(NOMBRE_SATELITE_UNO).x(POSICION_SATELITE_UNO_X).y(POSICION_SATELITE_UNO_Y).build();
        SatelliteDto satelliteDto = SatelliteDto.builder().id(ID_SATELITE_UNO).name(NOMBRE_SATELITE_UNO).x(POSICION_SATELITE_UNO_X).y(POSICION_SATELITE_UNO_Y).build();
        doReturn(satelliteDto).when(conversionService).convert(ubicacionSatelite, SatelliteDto.class);
        when(sateliteRepository.findById(any())).thenReturn(Optional.of(ubicacionSatelite));
        //when
        SatelliteDto satelliteDto1 = satelitesService.findById(ID_SATELITE_UNO);
        //then
        Assertions.assertNotNull(satelliteDto1);
        Assertions.assertEquals(ubicacionSatelite.getId(), satelliteDto1.getId());
    }

    @Test
    void givenAIdWhenDeleteByIdThenOk() {
        //when
        satelitesService.deleteById(ID_SATELITE_UNO);
        //then
        verify(sateliteRepository).deleteById(ID_SATELITE_UNO);
    }

    @Test
    void givenANullWhenDeleteByIdThenOk() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                satelitesService.deleteById(null));
    }

    private List<UbicacionSatelite> getUbicacionSatelites() {
        List<UbicacionSatelite> ubicacionSateliteList = new ArrayList<>();
        ubicacionSateliteList.add(UbicacionSatelite.builder().x(POSICION_SATELITE_UNO_X).y(POSICION_SATELITE_UNO_Y).build());
        ubicacionSateliteList.add(UbicacionSatelite.builder().x(POSICION_SATELITE_DOS_X).y(POSICION_SATELITE_DOS_Y).build());
        return ubicacionSateliteList;
    }

    private RequestSatellites getRequestSatellites() {
        RequestSatellites requestSatellites = new RequestSatellites();
        List<Satellite> satellites = new ArrayList<>();
        satellites.add(Satellite.builder().name(NOMBRE_SATELITE_UNO).distance(DISTACIA_SATELITE_UNO).build());
        satellites.add(Satellite.builder().name(NOMBRE_SATELITE_DOS).distance(DISTACIA_SATELITE_DOS).build());
        requestSatellites.setSatellites(satellites);
        return requestSatellites;
    }


}



