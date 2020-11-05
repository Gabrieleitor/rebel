package com.starWars.rebels.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starWars.rebels.dto.Satellite;
import com.starWars.rebels.dto.SatelliteSplitDto;
import com.starWars.rebels.service.TopSecretSplitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TopSecretSplitController.class)
class TopSecretSplitControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopSecretSplitService topSecretSplitService;

    private static final String NOMBRE_SATELITE = "The Actor name";


    @Test
    void registrarInformacionSatellitesTest() throws Exception {
        //given
        Satellite satellite = Satellite.builder().name(NOMBRE_SATELITE).build();
        SatelliteSplitDto SatelliteSplitDto = com.starWars.rebels.dto.SatelliteSplitDto.builder().build();
        //when
        this.mockMvc.perform(post("/topsecret_split/"+NOMBRE_SATELITE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(SatelliteSplitDto)))
                .andExpect(status().isCreated());
        //then
        verify(topSecretSplitService).registrarInformacionSatellites(satellite);
    }
    @Test
    void consultarInformacionSateliteTest() throws Exception {
        this.mockMvc.perform(get("/topsecret_split/"))
                .andExpect(status().isOk());
        //then
        verify(topSecretSplitService).consultarInformacionSatelite();
    }



}