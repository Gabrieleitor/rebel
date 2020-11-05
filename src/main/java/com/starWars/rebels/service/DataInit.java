package com.starWars.rebels.service;

import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.repository.UbicacionSateliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInit {

    @Autowired
    private UbicacionSateliteRepository ubicacionSateliteRepository;

    @PostConstruct
    public void init() {
        UbicacionSatelite kenobi = UbicacionSatelite.builder().name("kenobi").x(-500).y(-200).build();
        UbicacionSatelite skywalker = UbicacionSatelite.builder().name("skywalker").x(100).y(-100).build();
        UbicacionSatelite sato = UbicacionSatelite.builder().name("sato").x(500).y(100).build();
        ubicacionSateliteRepository.save(kenobi);
        ubicacionSateliteRepository.save(skywalker);
        ubicacionSateliteRepository.save(sato);
    }
}
