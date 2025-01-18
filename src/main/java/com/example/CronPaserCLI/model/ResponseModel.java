package com.example.CronPaserCLI.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseModel {
    List<Integer> minute;
    List<Integer> hour;
    List<Integer> dayOfMonth;
    List<Integer> month;
    List<Integer> dayOfWeek;
    String cronCommand;
}
