package com.porvenir.domain.service.impl;

import com.porvenir.domain.dto.EmployerDto;
import com.porvenir.domain.dto.PeriodDetailResponse;
import com.porvenir.domain.dto.PeriodoDto;
import com.porvenir.domain.dto.ResponseWorkHistoryDto;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
public class Implement {
    @Autowired
    PeriodDetailClient periodDetailClient;

    @SneakyThrows
    public PeriodDetailResponse getPeriodDetail(String RqUID,
                                                String Channel,
                                                String IdentSerialNum,
                                                String GovIssueIdentType,
                                                String IPAddr,
                                                String CompanyId,
                                                Long id,
                                                String tipoId) {
        PeriodDetailResponse result = periodDetailClient.getPeriodDetail(RqUID, Channel, IdentSerialNum, GovIssueIdentType, IPAddr, CompanyId, id, tipoId);
        if (!result.getStatus().getStatusCode().equals("200")) {
            throw new IllegalStateException("");
        }
        return result;
    }

    public List<EmployerDto> getChronologicalOrderDetailEmmployer(List<EmployerDto> listEmployer) {
        List<EmployerDto> response = listEmployer;
        Comparator<EmployerDto> comparator = Comparator.comparing(EmployerDto::getPeriodoInicial);
        Collections.sort(response, comparator);
        return response;
    }

    public List<EmployerDto> getDetailEmployer(PeriodDetailResponse objP) {
        List<EmployerDto> listEmployer = new ArrayList<>();
        for (ResponseWorkHistoryDto obj : objP.getData()) {
            String nit = obj.getNit();
            String nombre = getNameEmployer(obj.getPeriodo());
            List<String> period = getPeriod(obj.getPeriodo());

            for (int i = 0; i < period.size(); i = i + 2) {
                EmployerDto employer = new EmployerDto();
                employer.setNit(nit);
                employer.setRazonSocial(nombre);
                List<Map.Entry<Integer, Integer>> weekYear = getWeekYear(obj.getPeriodo(), period.get(i), period.get(i + 1));
                employer.setSemanas(weekYear.get(0).getKey());
                employer.setAños(weekYear.get(0).getValue());
                employer.setPeriodoFinal(period.get(i + 1));
                employer.setPeriodoInicial(period.get(i));
                listEmployer.add(employer);
            }
        }
        return getChronologicalOrderDetailEmmployer(listEmployer);
    }

    private List<Map.Entry<Integer, Integer>> getWeekYear(List<PeriodoDto> objP, String inicio, String fin) {
        List<Map.Entry<Integer, Integer>> weekYear = new ArrayList<>();
        Integer posIni = 0, posFin = 0, dias = 0, count = 0;
        for (int i = 0; i <= (objP.size() - 1); i++) {
            if (objP.get(i).getPeriodo().equals(Integer.parseInt(inicio))) {
                posIni = i;
                count++;
                if (count >= 1){
                    if (posIni != 0){
                        posIni = posIni - (count - 1);
                    }

                }
            }
            if (objP.get(i).getPeriodo().equals(Integer.parseInt(fin))) {
                var aux = i + 1;
                if ((i + 1) > (objP.size() - 1)){
                    aux = i - 1;
                    if (aux < 0){
                        aux = 0;
                    }
                }
                if (objP.get(i).getPeriodo().equals(objP.get(aux).getPeriodo())){
                    if (i != aux){
                        posFin = i + 1;
                        break;
                    }
                }
                posFin = i;
                break;
            }
        }


        for (int aux = posIni; aux <= posFin; aux++) {
            dias = dias + objP.get(aux).getDiasCotizados();
        }
        weekYear.add(Map.entry(dias / 7, (dias / 7)/52));

        return weekYear;
    }


    private String getNameEmployer(List<PeriodoDto> objP) {
        String nameEmployer = "";
        for (PeriodoDto obj : objP) {

            if (obj.getRazonSocial() != null) {
                nameEmployer = obj.getRazonSocial();
                break;
            }
        }
        return nameEmployer;
    }

    private List<String> getPeriod(List<PeriodoDto> objP) {
        List<String> periodos = new ArrayList<>();
        if (objP.size() != 0) periodos.add(objP.get(0).getPeriodo().toString());
        Boolean nuevo = false;
        for (int i = 0; i <= (objP.size() - 1); i++) {
            if (nuevo) {
                periodos.add(objP.get(i).getPeriodo().toString());
                if (i == (objP.size() - 1)) {
                    periodos.add(objP.get(i).getPeriodo().toString());
                } else {
                    if (objP.get(i).getPeriodo().equals(objP.get((i + 1)).getPeriodo())) {
                        nuevo = false;
                    } else {
                        if (Integer.parseInt(getNextPeriod(objP.get(i).getPeriodo())) != objP.get((i + 1)).getPeriodo()) {
                            periodos.add(objP.get(i).getPeriodo().toString());
                            nuevo = true;
                        } else {
                            nuevo = false;
                        }
                    }
                }
            } else {
                if (i == (objP.size() - 1)) {
                    periodos.add(objP.get(i).getPeriodo().toString());
                } else {
                    if (objP.get(i).getPeriodo().equals(objP.get((i + 1)).getPeriodo())){
                        nuevo = false;
                    } else {
                        if (Integer.parseInt(getNextPeriod(objP.get(i).getPeriodo())) != objP.get((i + 1)).getPeriodo()) {
                            periodos.add(objP.get(i).getPeriodo().toString());
                            nuevo = true;
                        }
                    }
                }
            }
        }

        return periodos;
    }

    private String getNextPeriod(Integer period) {

        Integer year = Integer.parseInt(period.toString().substring(0, 4));
        Integer month = Integer.parseInt(period.toString().substring(4, 6));
        String newPeriodo = "";
        if (month < 12) {
            month++;
            if (month < 10) {
                newPeriodo = year.toString() + "0" + month.toString();
            } else {
                newPeriodo = year.toString() + month.toString();
            }
        } else {
            year++;
            month = 1;
            newPeriodo = year.toString() + "01";
        }
        return newPeriodo;
    }
}
