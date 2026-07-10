package com.reservas.CruzDelSur.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.sql.Timestamp;

@Entity
@Table(name = "reservas")
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reserva;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @NotNull(message = "El cliente es obligatorio")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_viaje")
    @NotNull(message = "El viaje es obligatorio")
    private Viajes viaje;

    @NotNull(message = "El numero de asiento es obligatorio")
    @Positive(message = "El numero de asiento debe ser mayor a 0")
    private Integer numero_asiento;
    @NotBlank(message = "El metodo de pago es obligatorio")
    private String metodo_pago;
    @NotBlank(message = "El estado de pago es obligatorio")
    private String estado_pago;
    @NotBlank(message = "El estado de reserva es obligatorio")
    private String estado_reserva;
    private Timestamp fecha_registro;
}
