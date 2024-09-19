package com.porvenir.util;

import com.porvenir.domain.dto.EmployerDto;
import com.porvenir.domain.dto.ResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RequestResponseUtilsTest {
    RequestResponseUtils requestResponseUtils = new RequestResponseUtils();

    @Test
    void testConstruirRespuesta() {
        ResponseDto result = requestResponseUtils.construirRespuesta("codigoRespuesta", "descripcionRespuesta", List.of(new EmployerDto()));
        Assertions.assertNotNull(result);
    }
}
