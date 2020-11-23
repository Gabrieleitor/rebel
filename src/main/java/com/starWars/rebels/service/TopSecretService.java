package com.starWars.rebels.service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.MensajeResponse;
import com.starWars.rebels.dto.Position;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.dto.Satellite;
import com.starWars.rebels.service.interfaces.ConcatenarMenssageService;
import com.starWars.rebels.service.interfaces.ConsultarSatellites;
import com.starWars.rebels.service.interfaces.ObtenerDistancias;
import com.starWars.rebels.service.interfaces.ObtenerPocicionamientos;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopSecretService {
    @Autowired
    private SatelitesService satelitesService;

    @Autowired
    private ConcatenarMenssageService concatenateMenssageService;

    @Autowired
    private ObtenerPocicionamientos obtenerPocicionamientos;

    @Autowired
    private ObtenerDistancias obtenerDistancias;

    @Autowired
    private ConsultarSatellites consultarSatellites;

    public MensajeResponse getLocation(RequestSatellites requestSatellites) {
        Assert.notNull(requestSatellites, "The list cannot be null");
        List<UbicacionSatelite> satelitesRegistrados = consultarSatellites.validarSatelitesRegistrado(requestSatellites);
        double[][] posicionamientos = obtenerPocicionamientos.posicionamientos(requestSatellites, satelitesRegistrados);
        double[] distancias = obtenerDistancias.distancias(requestSatellites, satelitesRegistrados);
        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(posicionamientos, distancias), new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();
        double[] doubles = optimum.getPoint().toArray();
        List<String[]> lista = requestSatellites.getSatellites().stream().map(Satellite::getMessage).collect(Collectors.toList());
        MensajeResponse response = MensajeResponse.builder()
                .mensaje(concatenateMenssageService.getMessage(lista))
                .position(new Position(doubles[0], doubles[1])).build();
        return response;
    }

}
