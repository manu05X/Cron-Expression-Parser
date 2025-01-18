package com.example.CronPaserCLI.parsers;


import com.example.CronPaserCLI.exceptions.InvalidFormatException;
import com.example.CronPaserCLI.model.TimeField;

import java.util.ArrayList;
import java.util.List;

public class NumberParser extends Parser {
    @Override
    public List<Integer> getTimings(final TimeField timeField, final String cronExpression) throws InvalidFormatException {
        List<Integer> result = new ArrayList<>();
        try {
            int timeValue = Integer.parseInt(cronExpression);
            if (timeValue >= timeField.getStartValue() && timeValue <= timeField.getEndValue()) {
                result.add(timeValue);
            } else {
                throw new InvalidFormatException("Invalid value: " + timeValue + ". Value must be between " +
                        timeField.getStartValue() + " and " + timeField.getEndValue());
            }
        } catch (NumberFormatException ex) {
            throw new InvalidFormatException("Invalid format: " + cronExpression + ". Must be a valid numeric value.");
        }
        return result;
    }

    @Override
    public String getRegex(){
        return "^\\d+$";
    }
}