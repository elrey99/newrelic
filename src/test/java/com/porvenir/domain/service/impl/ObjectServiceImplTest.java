package com.porvenir.domain.service.impl;

import com.porvenir.DummyMock;
import com.porvenir.domain.dto.EmployerDto;
import com.porvenir.domain.dto.PeriodDetailResponse;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ObjectServiceImplTest {

    @InjectMocks
    ObjectServiceImpl objectService;
    @Mock
    Implement implement;

    @Mock
    HeaderValidation headersValidation;


    @Test
    void getEmployer() {
        PeriodDetailResponse responsePeriodDetail=new PeriodDetailResponse();
        when(implement.getPeriodDetail(anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyLong(), anyString())).thenReturn(responsePeriodDetail);
        PeriodDetailResponse result = implement.getPeriodDetail("","","","","","",12345L, "CC");
        Assertions.assertNotNull(result);
    }
    @Test
    void getEmployer2() {
        HttpHeaders headers = DummyMock.buildHttpHeaders();

        when(headersValidation.getHeaderValue(any(), anyString())).thenReturn("");

        PeriodDetailResponse responsePeriodDetail=new PeriodDetailResponse();
        when(implement.getPeriodDetail("","","","","","",1234567890L, "CC")).thenReturn(responsePeriodDetail);
        List<EmployerDto> responseGetDetailEmployer = new ArrayList<>();
        when(implement.getDetailEmployer(responsePeriodDetail)).thenReturn(responseGetDetailEmployer);
        List<EmployerDto> result =objectService.getEmployer(headers,1234567890L,"CC");
        Assertions.assertNotNull(result);
    }

}
