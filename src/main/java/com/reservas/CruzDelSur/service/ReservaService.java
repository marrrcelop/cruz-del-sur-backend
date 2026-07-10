package com.reservas.CruzDelSur.service;

import com.reservas.CruzDelSur.entity.Cliente;
import com.reservas.CruzDelSur.entity.Reserva;
import com.reservas.CruzDelSur.entity.Viajes;
import com.reservas.CruzDelSur.exception.BusinessException;
import com.reservas.CruzDelSur.exception.ResourceNotFoundException;
import com.reservas.CruzDelSur.repository.ClienteRepository;
import com.reservas.CruzDelSur.repository.ReservaRepository;
import com.reservas.CruzDelSur.repository.ViajeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    public List<Reserva> listarTodos() {
        return repository.findAll();
    }

    public Optional<Reserva> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    public Reserva guardar(Reserva reserva) {
        Cliente cliente = obtenerCliente(reserva);
        Viajes viaje = obtenerViaje(reserva);
        validarAsientoDisponible(viaje.getId_viaje(), reserva.getNumero_asiento(), null);

        reserva.setCliente(cliente);
        reserva.setViaje(viaje);

        if (reserva.getFecha_registro() == null) {
            reserva.setFecha_registro(Timestamp.from(Instant.now()));
        }

        return repository.save(reserva);
    }

    @Transactional
    public Reserva actualizar(Integer id, Reserva reservaActualizada) {
        Reserva reservaExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada"));

        Cliente cliente = obtenerCliente(reservaActualizada);
        Viajes viaje = obtenerViaje(reservaActualizada);
        validarAsientoDisponible(viaje.getId_viaje(), reservaActualizada.getNumero_asiento(), id);

        reservaExistente.setCliente(cliente);
        reservaExistente.setViaje(viaje);
        reservaExistente.setNumero_asiento(reservaActualizada.getNumero_asiento());
        reservaExistente.setMetodo_pago(reservaActualizada.getMetodo_pago());
        reservaExistente.setEstado_pago(reservaActualizada.getEstado_pago());
        reservaExistente.setEstado_reserva(reservaActualizada.getEstado_reserva());

        if (reservaActualizada.getFecha_registro() != null) {
            reservaExistente.setFecha_registro(reservaActualizada.getFecha_registro());
        }

        return repository.save(reservaExistente);
    }

    @Transactional
    public void eliminar(Integer id) {
        Reserva reserva = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada"));
        repository.delete(reserva);
    }

    private Cliente obtenerCliente(Reserva reserva) {
        if (reserva.getCliente() == null || reserva.getCliente().getId_cliente() == null) {
            throw new BusinessException("Debe indicar el id del cliente");
        }

        return clienteRepository.findById(reserva.getCliente().getId_cliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }

    private Viajes obtenerViaje(Reserva reserva) {
        if (reserva.getViaje() == null || reserva.getViaje().getId_viaje() == null) {
            throw new BusinessException("Debe indicar el id del viaje");
        }

        return viajeRepository.findById(reserva.getViaje().getId_viaje())
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado"));
    }

    private void validarAsientoDisponible(Integer idViaje, Integer numeroAsiento, Integer idReservaActual) {
        boolean asientoOcupado = idReservaActual == null
                ? repository.existsByViajeAndAsiento(idViaje, numeroAsiento)
                : repository.existsByViajeAndAsientoExcludingReserva(idViaje, numeroAsiento, idReservaActual);

        if (asientoOcupado) {
            throw new BusinessException("El asiento ya esta reservado para este viaje");
        }
    }
}
