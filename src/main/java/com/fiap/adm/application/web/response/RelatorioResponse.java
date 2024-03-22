package com.fiap.adm.application.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RelatorioResponse {
    private LocalDate dia;
    private List<LocalTime> horarios;
    @JsonSerialize(using = DurationSerializer.class)
    private Duration horasTrabalhadas;
}
