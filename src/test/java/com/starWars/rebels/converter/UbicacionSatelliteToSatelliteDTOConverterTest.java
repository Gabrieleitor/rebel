package com.starWars.rebels.converter;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.SatelliteDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UbicacionSatelliteToSatelliteDTOConverterTest {

    @InjectMocks
    private UbicacionSatelliteToSatelliteDTOConverter ubicacionSatelliteToSatelliteDTOConverter;

    private static final String NAME_SATELLITE = "skywalker";
    private static final double UBICACION_SATELLITE_X =100;
    private static final double UBICACION_SATELLITE_Y = 200;
    private static final long ID_SATELLITE = 1L;



    @Test
    void givenUbicacionSatelliteThenReturnSatelliteDto(){
        //given
        UbicacionSatelite ubicacionSatelite =  UbicacionSatelite.builder()
                .name(NAME_SATELLITE)
                .y(UBICACION_SATELLITE_Y)
                .x(UBICACION_SATELLITE_X)
                .id(ID_SATELLITE).build();
        //when
        SatelliteDto convert = ubicacionSatelliteToSatelliteDTOConverter.convert(ubicacionSatelite);
        //then
        Assertions.assertNotNull(convert);
        Assertions.assertEquals(ubicacionSatelite.getId(),convert.getId());
    }

}