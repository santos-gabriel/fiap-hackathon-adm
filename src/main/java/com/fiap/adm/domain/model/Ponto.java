package com.fiap.adm.domain.model;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Ponto {
    private UUID id;
    private String usuario;
    private String matricula;
    private String email;
    private final Date data = new Date();
}
