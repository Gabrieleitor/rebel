package com.starWars.rebels.domain;

import com.starWars.rebels.converter.ListStringConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@Entity(name = "informacion_satelite")
@AllArgsConstructor
@NoArgsConstructor
public class InformacionSatelite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double distancia;
    @Convert(converter = ListStringConverter.class)
    private List<String> mensaje;
    @ColumnDefault("false")
    private boolean generado;
}
