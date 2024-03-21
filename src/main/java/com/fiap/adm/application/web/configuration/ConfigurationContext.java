package com.fiap.adm.application.web.configuration;

import com.fiap.adm.domain.ports.in.IPontoUseCasePort;
import com.fiap.adm.domain.ports.out.IPontoRepositoryPort;
import com.fiap.adm.domain.usecase.PontoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationContext {
    @Bean
    public IPontoUseCasePort pontoUseCasePort(IPontoRepositoryPort pontoRepositoryPort) {
        return new PontoUseCase(pontoRepositoryPort);
    }
}