package com.starWars.rebels.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteDto {
    private Long id;
    private String name;
    private double x;
    private double y;
}
