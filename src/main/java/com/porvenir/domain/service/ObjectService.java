package com.porvenir.domain.service;

import com.porvenir.domain.dto.EmployerDto;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface ObjectService {
    List<EmployerDto> getEmployer(HttpHeaders headers, Long id, String tipoId);
}
