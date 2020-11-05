package com.starWars.rebels.converter;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.SatelliteDto;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SatelliteDTOToUbicacionSatelliteConverter implements Converter<SatelliteDto, UbicacionSatelite> {

    @Override
    public UbicacionSatelite convert(SatelliteDto satelliteDto) {
        UbicacionSatelite ubicacionSatelite = new UbicacionSatelite();
        BeanUtils.copyProperties(satelliteDto, ubicacionSatelite);
        return ubicacionSatelite;
    }
}
