package com.reservas.CruzDelSur.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Entity
@Table(name = "usuarios") 
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato valido")
    private String correo;
    private String contrasena_hash;
    @NotBlank(message = "El rol es obligatorio")
    @Pattern(regexp = "ADMIN|USER", message = "El rol debe ser ADMIN o USER")
    private String rol;
}
