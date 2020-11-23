package com.starWars.rebels.service;

import com.starWars.rebels.service.interfaces.ConcatenarMenssageService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ConcatenarMenssageSatellite implements ConcatenarMenssageService {

    @Override
    public String getMessage(List<String[]> messages) {
        Assert.notEmpty(messages, "The list of messages cannot be null");
        String[] primerMensaje = messages.get(0);
        for (int i = 1; i < messages.size(); i++) {
            primerMensaje = unifyMessage(primerMensaje, messages.get(i));
        }
        return String.join(" ", primerMensaje);
    }

    private String[] unifyMessage(String[] primerMensaje, String[] segundoMensaje) {
        int tamanoMayor = Math.max(primerMensaje.length, segundoMensaje.length);
        int tamanoMenor = Math.min(primerMensaje.length, segundoMensaje.length);
        String[] mensajeResultado = new String[tamanoMayor];
        if (tamanoMayor == tamanoMenor) {
            unificarMensajeMismoTamano(primerMensaje, segundoMensaje, tamanoMenor, mensajeResultado);
        } else {
            unificarMensajeDiferenteTamano(primerMensaje.length > segundoMensaje.length ? primerMensaje : segundoMensaje, primerMensaje.length > segundoMensaje.length ? segundoMensaje : primerMensaje, tamanoMayor, tamanoMenor, mensajeResultado);
        }
        return mensajeResultado;
    }

    private void unificarMensajeDiferenteTamano(String[] mensajeMayor, String[] mensajeMenor, int tamanoMensajeMayor, int tamanoMenor, String[] mensajeResultado) {
        int diferenciaTamano = Math.max(tamanoMensajeMayor, tamanoMenor) - Math.min(tamanoMensajeMayor, tamanoMenor);
        if (validarOrderAsendente(mensajeMayor, mensajeMenor, tamanoMensajeMayor, diferenciaTamano)) {
            unificarMensajeAsendente(mensajeMayor, mensajeMenor, tamanoMensajeMayor, mensajeResultado, diferenciaTamano);
        } else {
            unificarMensajedeDecendiente(mensajeMayor, mensajeMenor, tamanoMensajeMayor, tamanoMenor, mensajeResultado);
        }
    }

    private void unificarMensajedeDecendiente(String[] mensajeMayor, String[] mensajeMenor, int tamanoMayor, int tamanoMenor, String[] mensajeResultado) {
        for (int j = 0; j < tamanoMayor; j++) {
            if (j < tamanoMenor) {
                mensajeResultado[j] = (mensajeMayor[j].isEmpty() && mensajeMenor[j].isEmpty()) ? mensajeMayor[j] : (getCompararString(mensajeMayor[j], mensajeMenor[j]));
            } else {
                mensajeResultado[j] = mensajeMayor[j];
            }

        }
    }

    private void unificarMensajeAsendente(String[] mensajeMayor, String[] mensajeMenor, int tamanoMensajeMayor, String[] mensajeResultado, int diferenciaTamano) {
        for (int j = 0; j < tamanoMensajeMayor; j++) {
            if (j >= diferenciaTamano) {
                mensajeResultado[j] = (mensajeMayor[j].isEmpty() && mensajeMenor[j - diferenciaTamano].isEmpty()) ? mensajeMayor[j] : (getCompararString(mensajeMayor[j], mensajeMenor[j - diferenciaTamano]));
            } else {
                mensajeResultado[j] = mensajeMayor[j];
            }

        }
    }

    private static boolean validarOrderAsendente(String[] mensajeMayor, String[] mensajeMenor, int tamanoArrayMayor, int diferenciaDeArray) {
        boolean k = true;
        for (int j = diferenciaDeArray; j < tamanoArrayMayor; j++) {
            if ((!mensajeMayor[j].isEmpty() && !mensajeMenor[j - diferenciaDeArray].isEmpty()) && (!mensajeMayor[j].equals(mensajeMenor[j - diferenciaDeArray]))) {
                k = false;
                break;
            }

        }
        return k;
    }

    private static void unificarMensajeMismoTamano(String[] primerMensaje, String[] segundoMensaje, int tamanoArray, String[] mensajeResultado) {
        for (int j = 0; j < tamanoArray; j++) {
            mensajeResultado[j] = primerMensaje[j].isEmpty() && segundoMensaje[j].isEmpty() ? primerMensaje[j] : getCompararString(primerMensaje[j], segundoMensaje[j]);

        }
    }

    private static String getCompararString(String primerMensaje, String segundoMensaje) {
        return !primerMensaje.isEmpty() ? primerMensaje : segundoMensaje;
    }

}
