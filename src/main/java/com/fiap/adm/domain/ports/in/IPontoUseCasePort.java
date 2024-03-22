package com.fiap.adm.domain.ports.in;

import com.fiap.adm.domain.model.Ponto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IPontoUseCasePort {
    Optional<Ponto> salvar(Ponto ponto);
    List<Ponto> buscarPorPeriodo(Date inicio, Date fim);
}
