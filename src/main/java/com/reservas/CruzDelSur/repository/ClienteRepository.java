package com.reservas.CruzDelSur.repository;

import com.reservas.CruzDelSur.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c WHERE c.nombres LIKE %:nombre%")
    List<Cliente> buscarPorNombre(String nombre);

    boolean existsByCorreo(String correo);
    boolean existsByDocumento(String documento);
    Optional<Cliente> findByCorreo(String correo);
    Optional<Cliente> findByDocumento(String documento);
}
