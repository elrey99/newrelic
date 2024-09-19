package com.porvenir.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDto {

    private String nit;
    private String razonSocial;
    private String periodoInicial;
    private String periodoFinal;
    private Integer semanas;
    private Integer años;
}
