package com.starWars.rebels.service;

import com.starWars.rebels.domain.InformacionSatelite;
import com.starWars.rebels.dto.MensajeResponse;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.dto.Satellite;
import com.starWars.rebels.repository.InformacionSateliteRepository;
import com.starWars.rebels.service.interfaces.RegistrarSatellite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopSecretSplitService {
    @Autowired
    private InformacionSateliteRepository informacionSateliteRepository;
    @Autowired
    private RegistrarSatellite registrarSatellite;

    @Autowired
    private TopSecretService topSecretService;

    public void registrarInformacionSatellites(Satellite satellite ) {
        registrarSatellite.registrar(satellite);
    }

    public MensajeResponse consultarInformacionSatelite() {
        List<InformacionSatelite> informacionSatelites = informacionSateliteRepository.findAllByGeneradoIsFalse();
        RequestSatellites requestSatellites = convertirInformacionSateliteToRequestSatellites(informacionSatelites);
        MensajeResponse mensajeResponse = topSecretService.getLocation(requestSatellites);
        marcarComoGenerado(informacionSatelites);
        return mensajeResponse;
    }

    private void marcarComoGenerado(List<InformacionSatelite> informacionSatelites) {
        informacionSatelites.forEach(informacionSatelite -> {
            informacionSatelite.setGenerado(true);
            informacionSateliteRepository.save(informacionSatelite);
        });
    }

    private RequestSatellites convertirInformacionSateliteToRequestSatellites(List<InformacionSatelite> informacionSatelites) {
        RequestSatellites requestSatellites = new RequestSatellites();
        List<Satellite> satellites = new ArrayList<>();
        informacionSatelites.forEach(satelite -> {
            satellites.add(Satellite.builder().name(satelite.getNombre()).message(satelite.getMensaje().toArray(new String[0])).distance(satelite.getDistancia()).build());
        });
        requestSatellites.setSatellites(satellites);
        return requestSatellites;
    }


}
