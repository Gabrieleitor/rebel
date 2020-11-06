package com.starWars.rebels.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ConcatenateMenssageService {

    public String getMessage(List<String[]> messages) {
        Assert.notEmpty(messages, "The list of messages cannot be null");
        String[] mensajeA = messages.get(0);
        for (int i = 1; i < messages.size(); i++) {
            mensajeA = unifyMessage(mensajeA, messages.get(i));
        }
        return String.join(" ", mensajeA);
    }

    private String[] unifyMessage(String[] mensajeA, String[] mensajeB) {
        int tamanoA = Math.max(mensajeA.length, mensajeB.length);
        int tamanoB = Math.min(mensajeA.length, mensajeB.length);
        String[] mensajeResultado = new String[tamanoA];
        if (tamanoA == tamanoB) {
            unificarMensajeMismoTamano(mensajeA, mensajeB, tamanoB, mensajeResultado);
        } else {
            unificarMensajeDiferenteTamano(mensajeA.length > mensajeB.length ? mensajeA : mensajeB, mensajeA.length > mensajeB.length ? mensajeB : mensajeA, tamanoA, tamanoB, mensajeResultado);
        }
        return mensajeResultado;
    }

    private void unificarMensajeDiferenteTamano(String[] mensajeA, String[] mensajeB, int tamanoA, int tamanoB, String[] mensajeResultado) {
        int diferenciaTamano = Math.max(tamanoA, tamanoB) - Math.min(tamanoA, tamanoB);
        if (validarOrderAsendente(mensajeA, mensajeB, tamanoA, diferenciaTamano)) {
            unificarMensajeAsendente(mensajeA, mensajeB, tamanoA, mensajeResultado, diferenciaTamano);
        } else {
            unificarMensajedeDecendiente(mensajeA, mensajeB, tamanoA, tamanoB, mensajeResultado);
        }
    }

    private void unificarMensajedeDecendiente(String[] mensajeA, String[] mensajeB, int tamanoA, int tamanoB, String[] mensajeResultado) {
        for (int j = 0; j < tamanoA; j++) {
            if (j <= tamanoB) {
                mensajeResultado[j] = (mensajeA[j].isEmpty() && mensajeB[j].isEmpty()) ? mensajeA[j] : (getString(mensajeA, mensajeB, j, j));
            } else {
                mensajeResultado[j] = mensajeA[j];
            }

        }
    }

    private void unificarMensajeAsendente(String[] mensajeA, String[] mensajeB, int tamanoA, String[] mensajeResultado, int diferenciaTamano) {
        for (int j = 0; j < tamanoA; j++) {
            if (j >= diferenciaTamano) {
                mensajeResultado[j] = (mensajeA[j].isEmpty() && mensajeB[j - diferenciaTamano].isEmpty()) ? mensajeA[j] : (getString(mensajeA, mensajeB, j, j - diferenciaTamano));
            } else {
                mensajeResultado[j] = mensajeA[j];
            }

        }
    }

    private static boolean validarOrderAsendente(String[] nombre_a, String[] nombre_b, int i, int i2) {
        boolean k = true;
        for (int j = i2; j < i; j++) {
            if ((!nombre_a[j].isEmpty() && !nombre_b[j - i2].isEmpty()) && (!nombre_a[j].equals(nombre_b[j - i2]))) {
                k = false;
                break;
            }

        }
        return k;
    }

    private static void unificarMensajeMismoTamano(String[] nombre_a, String[] nombre_b, int i1, String[] nombre_c) {
        for (int j = 0; j < i1; j++) {
            nombre_c[j] = nombre_a[j].isEmpty() && nombre_b[j].isEmpty() ? nombre_a[j] : getString(nombre_a, nombre_b, j, j);

        }
    }

    private static String getString(String[] nombre_a, String[] nombre_b, int j, int j2) {
        return !nombre_a[j].isEmpty() ? nombre_a[j] : nombre_b[j2];
    }

}
