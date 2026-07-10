package com.reservas.CruzDelSur.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;
    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;
    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato valido")
    private String correo;
    @NotBlank(message = "El documento es obligatorio")
    @Size(min = 8, max = 12, message = "El documento debe tener entre 8 y 12 caracteres")
    @Pattern(regexp = "^[0-9A-Za-z]+$", message = "El documento solo debe contener letras o numeros")
    private String documento;
}

