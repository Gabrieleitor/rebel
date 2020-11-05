package com.starWars.rebels.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.service.SatelitesService;
import com.starWars.rebels.service.TopSecretService;
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
@WebMvcTest(controllers = TopSecretController.class)
class TopSecretControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopSecretService topSecretService;

    @Test
    void readTopSecretTest() throws Exception {
        //given
        RequestSatellites satelites = RequestSatellites.builder()
                .build();
        //when
        this.mockMvc.perform(post("/topsecret" )
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(satelites)))
                .andExpect(status().isOk());
        //then
        verify(topSecretService).getLocation(satelites);
    }

}