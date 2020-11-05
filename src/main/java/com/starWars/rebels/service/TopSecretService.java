package com.starWars.rebels.service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.starWars.rebels.domain.UbicacionSatelite;
import com.starWars.rebels.dto.MensajeResponse;
import com.starWars.rebels.dto.Position;
import com.starWars.rebels.dto.RequestSatellites;
import com.starWars.rebels.dto.Satellite;
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
    private ConcatenateMenssageService concatenateMenssageService;

    public MensajeResponse getLocation(RequestSatellites requestSatellites) {
        Assert.notNull(requestSatellites, "The list cannot be null");
        List<UbicacionSatelite> filtro = satelitesService.validarSatelitesRegistrado(requestSatellites);
        double[][] posicionamientos = satelitesService.posicionamientos(requestSatellites, filtro);
        double[] distancias = satelitesService.distancias(requestSatellites, filtro);
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
