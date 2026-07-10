package com.reservas.CruzDelSur.service;

import com.reservas.CruzDelSur.entity.Usuario;
import com.reservas.CruzDelSur.exception.BusinessException;
import com.reservas.CruzDelSur.exception.ResourceNotFoundException;
import com.reservas.CruzDelSur.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return repository.findByCorreo(correo);
    }

    @Transactional
    public Usuario guardar(Usuario usuario) {
        validarContrasena(usuario.getContrasena_hash(), true);
        validarCorreoDisponible(usuario.getCorreo(), null);

        String hash = passwordEncoder.encode(usuario.getContrasena_hash());
        usuario.setContrasena_hash(hash);

        return repository.save(usuario);
    }

    @Transactional
    public Usuario actualizar(Integer id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        validarCorreoDisponible(usuarioActualizado.getCorreo(), id);

        usuarioExistente.setNombres(usuarioActualizado.getNombres());
        usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
        usuarioExistente.setRol(usuarioActualizado.getRol());

        if (usuarioActualizado.getContrasena_hash() != null && !usuarioActualizado.getContrasena_hash().isBlank()) {
            validarContrasena(usuarioActualizado.getContrasena_hash(), false);
            String hash = passwordEncoder.encode(usuarioActualizado.getContrasena_hash());
            usuarioExistente.setContrasena_hash(hash);
        }

        return repository.save(usuarioExistente);
    }

    @Transactional
    public void eliminar(Integer id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        repository.delete(usuario);
    }

    private void validarCorreoDisponible(String correo, Integer idUsuarioActual) {
        repository.findByCorreo(correo).ifPresent(usuario -> {
            if (idUsuarioActual == null || !usuario.getId_usuario().equals(idUsuarioActual)) {
                throw new BusinessException("Ya existe un usuario con ese correo");
            }
        });
    }

    private void validarContrasena(String contrasena, boolean requerida) {
        if (contrasena == null || contrasena.isBlank()) {
            if (requerida) {
                throw new BusinessException("La contrasena es obligatoria");
            }
            return;
        }

        if (contrasena.length() < 6) {
            throw new BusinessException("La contrasena debe tener al menos 6 caracteres");
        }
    }
}
