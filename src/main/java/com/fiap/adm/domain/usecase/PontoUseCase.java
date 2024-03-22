package com.fiap.adm.domain.usecase;

import com.fiap.adm.domain.model.Ponto;
import com.fiap.adm.domain.ports.in.IPontoUseCasePort;
import com.fiap.adm.domain.ports.out.IPontoRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PontoUseCase implements IPontoUseCasePort {
    private final IPontoRepositoryPort repository;

    @Override
    public Optional<Ponto> salvar(Ponto ponto) {
        return repository.salvar(ponto);
    }

    @Override
    public List<Ponto> buscarPorPeriodo(Date inicio, Date fim) {
        return repository.buscarPorPeriodo(inicio, fim);
    }

}
