package com.starWars.rebels.converter;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.SatelliteDto;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UbicacionSatelliteToSatelliteDTOConverter implements Converter<UbicacionSatelite,SatelliteDto> {
    @Override
    public SatelliteDto convert(UbicacionSatelite ubicacionSatelite) {
        SatelliteDto satelliteDto = new SatelliteDto();
        BeanUtils.copyProperties(ubicacionSatelite,satelliteDto);
        return satelliteDto;
    }
}
