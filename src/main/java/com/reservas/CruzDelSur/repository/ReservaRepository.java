package com.reservas.CruzDelSur.repository;

import com.reservas.CruzDelSur.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    @Query("""
            SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
            FROM Reserva r
            WHERE r.viaje.id_viaje = :idViaje
              AND r.numero_asiento = :numeroAsiento
            """)
    boolean existsByViajeAndAsiento(@Param("idViaje") Integer idViaje,
                                    @Param("numeroAsiento") Integer numeroAsiento);

    @Query("""
            SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
            FROM Reserva r
            WHERE r.viaje.id_viaje = :idViaje
              AND r.numero_asiento = :numeroAsiento
              AND r.id_reserva <> :idReserva
            """)
    boolean existsByViajeAndAsientoExcludingReserva(@Param("idViaje") Integer idViaje,
                                                    @Param("numeroAsiento") Integer numeroAsiento,
                                                    @Param("idReserva") Integer idReserva);
}
