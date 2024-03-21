package com.fiap.adm.domain.ports.out;

import com.fiap.adm.domain.model.Ponto;

import java.util.List;
import java.util.Optional;

public interface IPontoRepositoryPort {
    Optional<Ponto> registrar(String usuario, String matricula, String email);
    List<Ponto> obterRegistrosPorUsuario(String usuario);
}
