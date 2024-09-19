package com.porvenir.util.validation;

import com.porvenir.DummyMock;
import com.porvenir.exception.ApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import static com.porvenir.util.Constant.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeaderValidationTest {
    HeaderValidation headersValidation = new HeaderValidation();

    @Test
    void testValidateHeaders() {

        HttpHeaders headers = DummyMock.buildHttpHeaders();

        headersValidation.validateHeaders(headers);
    }

    @Test
    void testValidateHeadersBadRequest() {

        Throwable throwable = assertThrows(Exception.class, () -> {

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Channel","AHL");
            headersValidation.validateHeaders(headers);

        });
        Assertions.assertEquals(ApiException.class, throwable.getClass());
    }

    @Test
    void testValidateHeadersBadRequestNumber() {

        Throwable throwable = assertThrows(Exception.class, () -> {

            HttpHeaders headers = DummyMock.buildHttpHeaders();
            headers.set(HEADER_SERVICE_X_RQUID,"123456X");
            headersValidation.validateHeaders(headers);

        });
        Assertions.assertEquals(ApiException.class, throwable.getClass());
    }

    @Test
    void testValidateHeadersBadRequestAlphaNumber() {

        Throwable throwable = assertThrows(Exception.class, () -> {

            HttpHeaders headers = DummyMock.buildHttpHeaders();
            headers.set(HEADER_SERVICE_X_CHANNEL,"AHL*");
            headersValidation.validateHeaders(headers);

        });
        Assertions.assertEquals(ApiException.class, throwable.getClass());
    }

    @Test
    void testGetHeaderValueFound() {

        HttpHeaders headers = DummyMock.buildHttpHeaders();

        String result = headersValidation.getHeaderValue(headers, HEADER_SERVICE_X_IDENTSERIALNUM);
        Assertions.assertNotNull(result);
    }
    @Test
    void testGetHeaderValueNotFound() {

        HttpHeaders headers = DummyMock.buildHttpHeaders();

        String result = headersValidation.getHeaderValue(headers, "targetHeader");
        Assertions.assertNotNull(result);
    }
}
