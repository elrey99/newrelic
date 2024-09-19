package com.porvenir.domain.service.impl;

import com.porvenir.domain.dto.*;
import com.porvenir.domain.service.ObjectService;
import com.porvenir.util.validation.HeaderValidation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.porvenir.util.Constant.*;

@Service
@Log4j2
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    Implement implement;

    @Autowired
    private HeaderValidation headersValidation;


    public List<EmployerDto> getEmployer(HttpHeaders headers,
                                         Long id, String tipoId) {


        PeriodDetailResponse responsePeriodDetail = implement.getPeriodDetail(
            headersValidation.getHeaderValue(headers, HEADER_SERVICE_X_RQUID),
            headersValidation.getHeaderValue(headers, HEADER_SERVICE_X_CHANNEL),
            headersValidation.getHeaderValue(headers, HEADER_SERVICE_X_IDENTSERIALNUM),
            headersValidation.getHeaderValue(headers, HEADER_SERVICE_X_GOVISSUEIDENTTYPE),
            headersValidation.getHeaderValue(headers, HEADER_SERVICE_X_IPADDR),
            headersValidation.getHeaderValue(headers, HEADER_SERVICE_X_COMPANYID),
            id,
            tipoId);

        return implement.getDetailEmployer(responsePeriodDetail);

    }
}
