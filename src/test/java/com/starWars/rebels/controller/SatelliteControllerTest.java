package com.starWars.rebels.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starWars.rebels.dto.SatelliteDto;
import com.starWars.rebels.service.SatelitesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SatelliteController.class)
class SatelliteControllerTest {
    private static final Long SATELLITE_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SatelitesService satelitesService;

    @Test
    void testFindById() throws Exception {
        //when
        this.mockMvc.perform(get("/satellites/" + SATELLITE_ID))
                .andExpect(status().isCreated());
        //then
        verify(satelitesService).findById(SATELLITE_ID);
    }

    @Test
    void testFindAll() throws Exception {
        //when
        this.mockMvc.perform(get("/satellites"))
                .andExpect(status().isOk());

        //then
        verify(satelitesService).findAll();
    }

    @Test
    public void testSaveSatellite() throws Exception {
        //given
        SatelliteDto satelliteDto = SatelliteDto.builder()
                .build();
        //when
        this.mockMvc.perform(post("/satellites")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(satelliteDto)))
                .andExpect(status().isAccepted());
        //then
        verify(satelitesService).save(satelliteDto);
    }


    @Test
    public void testUpdateSatellite() throws Exception {
        //given
        SatelliteDto satelliteDto = SatelliteDto.builder()
                .name("Kenobi").id(SATELLITE_ID)
                .build();

        this.mockMvc.perform(put("/satellites/" + SATELLITE_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(satelliteDto)))
                .andExpect(status().isOk());

        satelliteDto.setId(SATELLITE_ID);
        verify(satelitesService).save(satelliteDto);
    }

    @Test
    public void testDeleteSatellite() throws Exception {
        //when
        this.mockMvc.perform(delete("/satellites/" + SATELLITE_ID))
                .andExpect(status().isAccepted());
        //then
        verify(satelitesService).deleteById(SATELLITE_ID);
    }

}