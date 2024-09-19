package com.porvenir.domain.service.impl;

import com.porvenir.domain.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ImplementTest {

    @InjectMocks
    Implement implement;

    @Mock
    PeriodDetailClient periodDetailClient;

    @Test
    void getPeriodDetail0() {
        PeriodDetailResponse  periodDetailResponse;
        ResponseWorkHistoryDto responseWorkHistoryDto;
        PeriodoDto periodoDto = new PeriodoDto("1","prueba",202201,30,700000,"PORVEIR","cotizado",null,"cotizado",2022,01,"RPM");
        List<PeriodoDto> listPeriodoDto = new ArrayList<>();
        listPeriodoDto.add(periodoDto);
        responseWorkHistoryDto = new ResponseWorkHistoryDto("1",listPeriodoDto);
        List<ResponseWorkHistoryDto> listResponseWorkHistoryDto= new ArrayList<>();
        listResponseWorkHistoryDto.add(responseWorkHistoryDto);
        StatusDto statusDto = new StatusDto();
        statusDto.setStatusCode("200");
        statusDto.setStatusDescription("");
        periodDetailResponse = new PeriodDetailResponse(statusDto, listResponseWorkHistoryDto);
        Mockito.when(periodDetailClient.getPeriodDetail(anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyLong(), anyString())).thenReturn(periodDetailResponse);
        PeriodDetailResponse result = implement.getPeriodDetail("","","","","","",123L,"CC");
        Assertions.assertNotNull(result);
    }

    @Test
    void getPeriodDetail1() {
        PeriodDetailResponse  periodDetailResponse;
        ResponseWorkHistoryDto responseWorkHistoryDto;
        PeriodoDto periodoDto = new PeriodoDto("1","prueba",202201,30,700000,"PORVEIR","cotizado",null,"cotizado",2022,01,"RPM");
        List<PeriodoDto> listPeriodoDto = new ArrayList<>();
        listPeriodoDto.add(periodoDto);
        responseWorkHistoryDto = new ResponseWorkHistoryDto("1",listPeriodoDto);
        List<ResponseWorkHistoryDto> listResponseWorkHistoryDto= new ArrayList<>();
        listResponseWorkHistoryDto.add(responseWorkHistoryDto);
        StatusDto statusDto = new StatusDto();
        statusDto.setStatusCode("400");
        statusDto.setStatusDescription("");
        periodDetailResponse = new PeriodDetailResponse(statusDto, listResponseWorkHistoryDto);
        Mockito.when(periodDetailClient.getPeriodDetail(anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyLong(), anyString())).thenReturn(periodDetailResponse);
        PeriodDetailResponse result;
        try {
            result = implement.getPeriodDetail("","","","","","",123L, "CC");
        }
        catch (IllegalStateException e){
            Assertions.assertNotNull(e.getMessage());
        }
    }
    @Test
    void getDetailEmployer() {
        PeriodDetailResponse periodDetailResponse;
        PeriodoDto periodoDto = new PeriodoDto("1", "prueba", 202201, 30, 700000, "PORVEIR", "cotizado", null, "cotizado", 2022, 01, "RPM");
        PeriodoDto periodoDto2 = new PeriodoDto("1", "prueba", 202202, 30, 700000, "PORVEIR", "cotizado", null, "cotizado", 2022, 01, "RPM");
        PeriodoDto periodoDto3 = new PeriodoDto("1", "prueba", 202203, 30, 700000, "PORVEIR", "cotizado", null, "cotizado", 2022, 01, "RPM");
        PeriodoDto periodoDto4 = new PeriodoDto("1", "prueba", 202204, 30, 700000, "PORVEIR", "cotizado", null, "cotizado", 2022, 01, "RPM");
        PeriodoDto periodoDto5 = new PeriodoDto("1", "prueba", 202205, 30, 700000, "PORVEIR", "cotizado", null, "cotizado", 2022, 01, "RPM");
        PeriodoDto periodoDto6 = new PeriodoDto("1", "prueba", 202209, 30, 700000, "PORVEIR", "cotizado", null, "cotizado", 2022, 01, "RPM");
        PeriodoDto periodoDto7 = new PeriodoDto("1", "prueba", 202210, 30, 700000, "PORVEIR", "cotizado", null, "cotizado", 2022, 01, "RPM");
        PeriodoDto periodoDto8 = new PeriodoDto("1", "prueba", 202211, 30, 700000, "PORVEIR", "cotizado", null, "cotizado", 2022, 01, "RPM");
        PeriodoDto periodoDto9 = new PeriodoDto("1", "prueba", 202212, 30, 700000, "PORVEIR", "cotizado", null, "cotizado", 2022, 01, "RPM");
        PeriodoDto periodoDto10 = new PeriodoDto("1", "prueba", 202301, 30, 700000, "PORVEIR", "cotizado", null, "cotizado", 2022, 01, "RPM");
        List<PeriodoDto> listPeriodoDto = new ArrayList<>();
        listPeriodoDto.add(periodoDto);
        listPeriodoDto.add(periodoDto2);
        listPeriodoDto.add(periodoDto3);
        listPeriodoDto.add(periodoDto4);
        listPeriodoDto.add(periodoDto5);
        listPeriodoDto.add(periodoDto6);
        listPeriodoDto.add(periodoDto7);
        listPeriodoDto.add(periodoDto8);
        listPeriodoDto.add(periodoDto9);
        listPeriodoDto.add(periodoDto10);
        ResponseWorkHistoryDto responseWorkHistoryDto = new ResponseWorkHistoryDto("1", listPeriodoDto);
        PeriodoDto periodoDto11 = new PeriodoDto("2", "prueba", 199501, 30, 700000, "PORVEIR", "cotizado", null, "cotizado", 2022, 01, "RPM");
        List<PeriodoDto> listPeriodoDto2 = new ArrayList<>();
        listPeriodoDto2.add(periodoDto11);
        ResponseWorkHistoryDto responseWorkHistoryDto2 = new ResponseWorkHistoryDto("2", listPeriodoDto2);
        List<ResponseWorkHistoryDto> listResponseWorkHistoryDto = new ArrayList<>();
        listResponseWorkHistoryDto.add(responseWorkHistoryDto);
        listResponseWorkHistoryDto.add(responseWorkHistoryDto2);
        StatusDto statusDto = new StatusDto();
        statusDto.setStatusCode("200");
        statusDto.setStatusDescription("");
        periodDetailResponse = new PeriodDetailResponse(statusDto, listResponseWorkHistoryDto);
        List<EmployerDto> result = implement.getDetailEmployer(periodDetailResponse);
        Assertions.assertNotNull(result);
    }
}
