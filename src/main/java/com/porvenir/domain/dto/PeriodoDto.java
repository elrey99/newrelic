package com.porvenir.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeriodoDto {
    private String nit;
    private String razonSocial;
    private Integer periodo;
    private Integer diasCotizados;
    private Integer salario;
    private String administradoraOrigenPeriodo;
    private String clasificacionPeriodo;
    private String indicadorValidoBono;
    private String estadoPeriodo;
    private Integer anio;
    private Integer mes;
    private String fuente;
}
