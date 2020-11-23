package com.starWars.rebels.service;

import com.starWars.rebels.domain.UbicacionSatelite;
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

    private static final long ID_SATELITE_UNO = 1L;
    private static final int TAMANO_LISTA = 1;


    @Test
    void testFindAllShouldRunOk() {
        //given
        UbicacionSatelite ubicacionSatelite = UbicacionSatelite.builder()
                .id(ID_SATELITE_UNO)
                .name(ConstruirRequestSatellites.NOMBRE_SATELITE_UNO)
                .x(ConstruirUbicacionSatelite.POSICION_SATELITE_UNO_X)
                .y(ConstruirUbicacionSatelite.POSICION_SATELITE_UNO_Y).build();
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
        UbicacionSatelite ubicacionSatelite = UbicacionSatelite.builder()
                .id(ID_SATELITE_UNO)
                .name(ConstruirRequestSatellites.NOMBRE_SATELITE_UNO)
                .x(ConstruirUbicacionSatelite.POSICION_SATELITE_UNO_X)
                .y(ConstruirUbicacionSatelite.POSICION_SATELITE_UNO_Y).build();
        SatelliteDto satelliteDto = SatelliteDto.builder()
                .id(ID_SATELITE_UNO)
                .name(ConstruirRequestSatellites.NOMBRE_SATELITE_UNO)
                .x(ConstruirUbicacionSatelite.POSICION_SATELITE_UNO_X)
                .y(ConstruirUbicacionSatelite.POSICION_SATELITE_UNO_Y).build();
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
        UbicacionSatelite ubicacionSatelite = UbicacionSatelite.builder()
                .id(ID_SATELITE_UNO)
                .name(ConstruirRequestSatellites.NOMBRE_SATELITE_UNO)
                .x(ConstruirUbicacionSatelite.POSICION_SATELITE_UNO_X)
                .y(ConstruirUbicacionSatelite.POSICION_SATELITE_UNO_Y).build();
        SatelliteDto satelliteDto = SatelliteDto.builder()
                .id(ID_SATELITE_UNO)
                .name(ConstruirRequestSatellites.NOMBRE_SATELITE_UNO)
                .x(ConstruirUbicacionSatelite.POSICION_SATELITE_UNO_X)
                .y(ConstruirUbicacionSatelite.POSICION_SATELITE_UNO_Y).build();
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
}



