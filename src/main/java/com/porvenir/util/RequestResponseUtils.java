package com.porvenir.util;

import com.porvenir.domain.dto.EmployerDto;
import com.porvenir.domain.dto.ResponseDto;
import com.porvenir.domain.dto.StatusDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestResponseUtils {

    /**
     * Crea la respuesta de la petición
     * @param codigoRespuesta codigo de respuesta HTTP
     * @param descripcionRespuesta información del estado de respuesta HTTP
     * @param datosRespuesta infromación que retorna el servicio
     * @return ResponseServiceDto estructura de respuesta de la petición
     */
    public ResponseDto construirRespuesta(String codigoRespuesta, String descripcionRespuesta,
                                          List<EmployerDto> datosRespuesta){

        return ResponseDto.builder()
            .data(datosRespuesta)
            .status(StatusDto.builder()
                .statusCode(codigoRespuesta)
                .statusDescription(descripcionRespuesta)
                .build())
            .build();

    }
}
