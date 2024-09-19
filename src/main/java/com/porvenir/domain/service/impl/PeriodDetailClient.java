package com.porvenir.domain.service.impl;

import com.porvenir.domain.dto.PeriodDetailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "PeriodDetailClient", url = "${detalle.semana.url}")
public interface PeriodDetailClient {

    @GetMapping("/api/periodDetailHl")
    PeriodDetailResponse getPeriodDetail(@RequestHeader("X-RqUID") String RqUID,
                                         @RequestHeader("X-Channel") String Channel,
                                         @RequestHeader("X-IdentSerialNum") String IdentSerialNum,
                                         @RequestHeader("X-GovIssueIdentType") String GovIssueIdentType,
                                         @RequestHeader("X-IPAddr") String IPAddr,
                                         @RequestHeader("X-CompanyId") String CompanyId,
                                         @RequestParam("numeroIdentificacion") Long numeroIdentificacion,
                                         @RequestParam("tipoIdentificacion") String tipoIdentificacion);

}


