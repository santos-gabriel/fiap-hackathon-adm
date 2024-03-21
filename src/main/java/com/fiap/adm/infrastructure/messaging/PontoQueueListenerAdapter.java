package com.fiap.adm.infrastructure.messaging;

import com.fiap.adm.domain.model.Ponto;
import com.fiap.adm.domain.ports.in.IPontoUseCasePort;
import com.fiap.adm.domain.ports.in.IPontoQueueListenerPort;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PontoQueueListenerAdapter implements IPontoQueueListenerPort {
    private final IPontoUseCasePort pontoUseCasePort;
    @Override
    @SqsListener(queueNames = "${queue.atualiza.registro.ponto}", factory = "defaultSqsListenerContainerFactory")
    public void listen(Ponto message) {
        System.out.println(message);
        pontoUseCasePort.salvar(message);
    }
}
