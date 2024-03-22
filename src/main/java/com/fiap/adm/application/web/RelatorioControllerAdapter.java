package com.fiap.adm.application.web;

import com.fiap.adm.application.web.response.RelatorioResponse;
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
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<LocalDate, List<LocalTime>> espelho = gerarEspelhoPonto(retorno);
        List<RelatorioResponse> relatorio = new ArrayList<>();
        for (Map.Entry<LocalDate, List<LocalTime>> entry : espelho.entrySet()) {
            RelatorioResponse rel = RelatorioResponse.builder()
                    .dia(entry.getKey())
                    .horarios(entry.getValue())
                    .horasTrabalhadas(calcularTempoTrabalhado(entry.getValue()))
                    .build();
            relatorio.add(rel);
        }

        return ResponseEntity.ok(relatorio);
    }

    private static Map<LocalDate, List<LocalTime>> gerarEspelhoPonto(List<Ponto> registrosPonto) {
        Map<LocalDate, List<LocalTime>> espelhoPonto = new HashMap<>();

        for (Ponto registro : registrosPonto) {
            LocalDate data = registro.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime hora = registro.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

            if (!espelhoPonto.containsKey(data)) {
                espelhoPonto.put(data, new ArrayList<>());
            }

            espelhoPonto.get(data).add(hora);
        }

        return espelhoPonto;
    }

    // public static Duration calcularTempoTrabalhado(Map<LocalDate, List<LocalTime>> espelho) {
    public static Duration calcularTempoTrabalhado(List<LocalTime> horarios) {
        Duration tempoTrabalhado = Duration.ZERO;

        /*for (Map.Entry<LocalDate, List<LocalTime>> entry : espelho.entrySet()) {
            List<LocalTime> horarios = entry.getValue();*/

            if (horarios.size() % 2 == 0) { // Deve haver um número par de registros
                for (int i = 0; i < horarios.size(); i += 2) {
                    LocalTime entrada = horarios.get(i);
                    LocalTime saida = horarios.get(i + 1);

                    tempoTrabalhado = tempoTrabalhado.plus(Duration.between(entrada, saida));
                }
            } else {
                // Aqui você pode lidar com casos inválidos
                // System.out.println("Número ímpar de registros para o dia " + entry.getKey());
                System.out.println("Número ímpar de registros para o dia " + horarios);
            }
        // }

        return tempoTrabalhado;
    }

//    private static Duration calcularTempoTrabalhado(List<Ponto> registros) {
//        registros.sort((r1, r2) -> r1.getData().compareTo(r2.getData()));
//
//        Duration tempoTrabalhado = Duration.ZERO;
//        LocalDateTime ultimoRegistro = null;
//        boolean emPausa = false;
//
//        for (Ponto registro : registros) {
//            LocalTime entrada = registro.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();;
//            LocalDateTime saida = registro.getDataHoraSaida();
//
//            if (emPausa) {
//                emPausa = false;
//                ultimoRegistro = saida;
//                continue;
//            }
//
//            if (ultimoRegistro != null) {
//                tempoTrabalhado = tempoTrabalhado.plus(Duration.between(ultimoRegistro, entrada));
//            }
//
//            if (saida != null) {
//                emPausa = true;
//                ultimoRegistro = saida;
//            } else {
//                ultimoRegistro = entrada;
//            }
//        }
//
//        return tempoTrabalhado;
//    }

}
