package com.porvenir.web;

import com.porvenir.DummyMock;
import com.porvenir.domain.dto.EmployerDto;
import com.porvenir.domain.dto.ResponseDto;
import com.porvenir.domain.service.ObjectService;
import com.porvenir.util.RequestResponseUtils;
import com.porvenir.util.validation.HeaderValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ObjectControllerTest {

    @InjectMocks
    ObjectController objectController;

    @Mock
    ObjectService objectService;

    @Mock
    RequestResponseUtils requestResponseUtils;

    @Mock
    HeaderValidation headerValidation;

    @Test
    void employer() {

        HttpHeaders headers = DummyMock.buildHttpHeaders();
        List<EmployerDto> getEmployer = new ArrayList<>();

        when(objectService.getEmployer(any(),anyLong(), anyString())).thenReturn(getEmployer);
        when(requestResponseUtils.construirRespuesta(anyString(), anyString(), any())).thenReturn(new ResponseDto());

        ResponseEntity<ResponseDto> result= objectController.employer(headers,1014269410L, "CC");
        Assertions.assertNotNull(result);
    }
}
