package com.porvenir.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeriodDetailResponse {
    private StatusDto status;
    private List<ResponseWorkHistoryDto> data;
}
