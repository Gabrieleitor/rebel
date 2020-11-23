package com.starWars.rebels.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ConcatenateMenssageServiceTest {

    @InjectMocks
    private ConcatenarMenssageSatellite concatenateMenssageService;

    private static final String[] ARREGLO_MENSAJE_UNO = new String[]{"este", "", "", "mensaje","secreto"};
    private static final String[] ARREGLO_MENSAJE_DOS = new String[]{"", "es", "un", "", "secreto"};
    private static final String[] ARREGLO_MENSAJE_TRES = new String[]{"este", "es", "", "", "secreto","nuevo"};
    private static final String[] ARREGLO_MENSAJE_CUATRO = new String[]{"Hola","", "es", "un", "", "secreto"};
    private static final String MENSAJE_RESPUESTA_MISMO_TAMAÑO = "este es un mensaje secreto";
    private static final String MENSAJE_RESPUESTA_DIFERENTE_TAMAÑO_DERECHA = "este es un mensaje secreto nuevo";
    private static final String MENSAJE_RESPUESTA_DIFERENTE_TAMAÑO_IZQUIERDA = "Hola este es un mensaje secreto";


    @Test
    void cuandoListaEmpty() {
        List<String[]> messages = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            concatenateMenssageService.getMessage(messages);
        });
    }

    @Test
    void cuandoListaMismoTamano() {
        //given
        List<String[]> messages = new ArrayList<>();
        messages.add(ARREGLO_MENSAJE_UNO);
        messages.add(ARREGLO_MENSAJE_DOS);
        //when
        String respuesta = concatenateMenssageService.getMessage(messages);
        //then
        Assertions.assertEquals(MENSAJE_RESPUESTA_MISMO_TAMAÑO,respuesta);
    }

    @Test
    void cuandoListaDiferenteTamanoDerecha() {
        //given
        List<String[]> messages = new ArrayList<>();
        messages.add(ARREGLO_MENSAJE_UNO);
        messages.add(ARREGLO_MENSAJE_TRES);
        messages.add(ARREGLO_MENSAJE_DOS);
        //when
        String respuesta = concatenateMenssageService.getMessage(messages);
        //then
        Assertions.assertEquals(MENSAJE_RESPUESTA_DIFERENTE_TAMAÑO_DERECHA,respuesta);
    }

    @Test
    void cuandoListaDiferenteTamanoIzquierda() {
        //given
        List<String[]> messages = new ArrayList<>();
        messages.add(ARREGLO_MENSAJE_UNO);
        messages.add(ARREGLO_MENSAJE_CUATRO);
        messages.add(ARREGLO_MENSAJE_DOS);
        //when
        String respuesta = concatenateMenssageService.getMessage(messages);
        //then
        Assertions.assertEquals(MENSAJE_RESPUESTA_DIFERENTE_TAMAÑO_IZQUIERDA,respuesta);
    }
}