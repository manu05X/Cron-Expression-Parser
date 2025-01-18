package com.example.CronPaserCLI.parsers;


import com.example.CronPaserCLI.exceptions.InvalidFormatException;
import com.example.CronPaserCLI.model.TimeField;

import java.util.ArrayList;
import java.util.List;

public class StarParser extends Parser {
    @Override
    public List<Integer> getTimings(final TimeField timeField, final String cronExpression) {
        if (!cronExpression.matches(getRegex())) {
            throw new InvalidFormatException("Invalid cron expression: '" + cronExpression + "'. Expected '*'.");
        }

        List<Integer> result = new ArrayList<>();
        int startValue = timeField.getStartValue();
        int endValue = timeField.getEndValue();

        if (startValue > endValue) {
            throw new InvalidFormatException("Invalid time range: start value (" + startValue + ") cannot be greater than end value (" + endValue + ").");
        }


        while (startValue <= endValue) {
            result.add(startValue);
            startValue++;
        }

        return result;
    }

    @Override
    public String getRegex(){
        return "^\\*$";
    }
}