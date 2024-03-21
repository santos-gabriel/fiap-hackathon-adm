package com.fiap.adm.domain.usecase;

import com.fiap.adm.domain.model.Ponto;
import com.fiap.adm.domain.ports.in.IPontoUseCasePort;
import com.fiap.adm.domain.ports.out.IPontoRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PontoUseCase implements IPontoUseCasePort {
    private final IPontoRepositoryPort repository;

    @Override
    public Optional<Ponto> registrar(String usuario, String matricula, String email) {
        return repository.registrar(usuario, matricula, email);
    }

    @Override
    public List<Ponto> obterRegistrosPorUsuario(String usuario) {
        return repository.obterRegistrosPorUsuario(usuario);
    }
}
