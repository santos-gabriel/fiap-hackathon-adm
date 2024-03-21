package com.fiap.adm.domain.ports.in;

import com.fiap.adm.domain.model.Ponto;

public interface IPontoQueueListenerPort {
    void listen(Ponto message);
}
