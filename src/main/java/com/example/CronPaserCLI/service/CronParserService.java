package com.example.CronPaserCLI.service;

import com.example.CronPaserCLI.managers.ParsingManager;
import com.example.CronPaserCLI.model.ResponseModel;
import com.example.CronPaserCLI.response.CronExpressionResponse;
import com.example.CronPaserCLI.model.TimeField;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CronParserService {
    private final ParsingManager parsingManager;

    CronExpressionResponse cronExpressionResponse;


    public void parseCronExpression(String cronExpression) {
        String[] subExpressions = cronExpression.split(" ");
        
        List<Integer> minutes = parsingManager.getTimingsList(TimeField.MINUTE, subExpressions[0]);
        List<Integer> hours = parsingManager.getTimingsList(TimeField.HOUR, subExpressions[1]);
        List<Integer> daysOfMonth = parsingManager.getTimingsList(TimeField.DAY_OF_MONTH, subExpressions[2]);
        List<Integer> months = parsingManager.getTimingsList(TimeField.MONTH, subExpressions[3]);
        List<Integer> daysOfWeek = parsingManager.getTimingsList(TimeField.DAY_OF_WEEK, subExpressions[4]);
        String command = subExpressions[5];

        ResponseModel responseModel = ResponseModel.builder()
                .minute(minutes)
                .hour(hours)
                .dayOfMonth(daysOfMonth)
                .month(months)
                .dayOfWeek(daysOfWeek)
                .cronCommand(command)
                .build();


        cronExpressionResponse.printResponseByModel(responseModel);
    }
}