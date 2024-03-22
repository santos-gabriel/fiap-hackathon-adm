package com.fiap.adm.application.web;

import com.fiap.adm.domain.exception.MesInvalidoException;
import com.fiap.adm.domain.model.Ponto;
import com.fiap.adm.domain.ports.in.IPontoUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/adm/relatorios")
@RequiredArgsConstructor
public class RelatorioControllerAdapter {
    private final IPontoUseCasePort pontoUseCasePort;

    @GetMapping(value = "/pontos/{ano}/{mes}", produces = "application/json")
    public ResponseEntity<?> relatorioPorPeriodo(@PathVariable Integer ano, @PathVariable Integer mes) {
        if ((mes > 12) || (mes < 1)) {
            throw new MesInvalidoException();
        }
        LocalDate dataInicial = LocalDate.of(ano, mes, 1);
        LocalDate dataFinal = LocalDate.of(ano, mes, dataInicial.lengthOfMonth());
        List<Ponto> retorno = pontoUseCasePort.buscarPorPeriodo(Date.valueOf(dataInicial), Date.valueOf(dataFinal));
        return ResponseEntity.ok(retorno);
    }

}
