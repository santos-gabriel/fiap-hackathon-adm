package com.fiap.adm.domain.ports.in;

import com.fiap.adm.domain.model.Ponto;

import java.util.List;
import java.util.Optional;

public interface IPontoUseCasePort {
    Optional<Ponto> registrar(String usuario, String matricula, String email);
    List<Ponto> obterRegistrosPorUsuario(String usuario);
}
