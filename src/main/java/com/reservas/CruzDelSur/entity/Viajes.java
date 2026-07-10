package com.reservas.CruzDelSur.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "viajes")
@Data
public class Viajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_viaje;
    @NotBlank(message = "El origen es obligatorio")
    private String origen;
    @NotBlank(message = "El destino es obligatorio")
    private String destino;
    @NotNull(message = "La fecha es obligatoria")
    private Date fecha;
    @NotNull(message = "La hora es obligatoria")
    private Time hora;
    @NotBlank(message = "El tipo de servicio es obligatorio")
    private String tipo_servicio;
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;
    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}
