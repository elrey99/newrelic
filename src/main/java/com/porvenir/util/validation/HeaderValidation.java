package com.porvenir.util.validation;

import com.porvenir.exception.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.porvenir.util.Constant.*;

@Component
public class HeaderValidation {

    private static final List<String> requiredHeaders = Arrays.asList(HEADER_SERVICE_X_RQUID, HEADER_SERVICE_X_CHANNEL, HEADER_SERVICE_X_COMPANYID, HEADER_SERVICE_X_IPADDR,
        HEADER_SERVICE_X_GOVISSUEIDENTTYPE, HEADER_SERVICE_X_IDENTSERIALNUM);

    private static final List<String> headersNumericos = Arrays.asList(HEADER_SERVICE_X_RQUID);

    private static final List<String> headersAlfaNumericos = Arrays.asList(HEADER_SERVICE_X_CHANNEL, HEADER_SERVICE_X_COMPANYID, HEADER_SERVICE_X_GOVISSUEIDENTTYPE, HEADER_SERVICE_X_IDENTSERIALNUM);

    /**
     * Validar Headers
     *
     * @param headers los headers a validar
     */
    public void validateHeaders(HttpHeaders headers) {

        validateExistsHeader(headers);
        validateNumber(headers);
        validateAlphaNumeric(headers);
    }

    /**
     * Validar si existe el header en los headers de la peticion
     *
     * @param headers los headers
     */
    private void validateExistsHeader(HttpHeaders headers) {

        requiredHeaders.forEach(requiredHeader -> {

            String valorHeader = headers.getFirst(requiredHeader);
            if (!headers.containsKey(requiredHeader) || StringUtils.isBlank(valorHeader) || "\"\"".equals(valorHeader)) {

                throw new ApiException(CODE_400, HttpStatus.BAD_REQUEST, RESPONSE_REQUEST_ERROR);
            }
        });
    }

    private void validateNumber(HttpHeaders headers) {

        for (String headersNumerico : HeaderValidation.headersNumericos) {
            String valorHeader = headers.getFirst(headersNumerico);
            if (!(valorHeader != null && valorHeader.matches(REGEX_NUMERIC))) {
                throw new ApiException(CODE_400, HttpStatus.BAD_REQUEST, RESPONSE_REQUEST_ERRORTYPE);
            }
        }
    }

    private void validateAlphaNumeric(HttpHeaders headers) {

        for (String headersNumerico : HeaderValidation.headersAlfaNumericos) {
            String valorHeader = headers.getFirst(headersNumerico);
            if (!(valorHeader != null && valorHeader.matches(REGEX_ALPHANUMERIC))) {
                throw new ApiException(CODE_400, HttpStatus.BAD_REQUEST, RESPONSE_REQUEST_ERRORTYPE);
            }
        }
    }

    /**
     * Obtener valor del header especifico
     * @param headers HttpHeaders de los headers
     * @param targetHeader header a evaluar
     * @return String con el valor del header, vacio si no encontro el header
     */
    public String getHeaderValue(HttpHeaders headers, String targetHeader){
        List<String> listHeader = headers.get(targetHeader);
        if (listHeader!=null){
            return listHeader.get(0);
        }else {
            return "";
        }
    }
}
