package com.example.CronPaserCLI.parsers;


import com.example.CronPaserCLI.exceptions.InvalidFormatException;
import com.example.CronPaserCLI.model.TimeField;

import java.util.ArrayList;
import java.util.List;

public class IncrementParser extends Parser {
    @Override
    public List<Integer> getTimings(final TimeField timeField, final String cronExpression) throws InvalidFormatException {
        List<Integer> result = new ArrayList<>();
        String expr = "*/";
        try {
            int incrementValue = Integer.parseInt(cronExpression.substring(expr.length()));
            int startValue = timeField.getStartValue();
            int endValue = timeField.getEndValue();
            if (incrementValue < 1 || incrementValue > endValue) {
                throw new InvalidFormatException("Invalid increment value: " + incrementValue + ". It must be between 1 and " + endValue);
            }
            while (startValue <= endValue) {
                result.add(startValue);
                startValue += incrementValue;
            }
        } catch (NumberFormatException ex) {
            throw new InvalidFormatException("Invalid format for increment: " + cronExpression + ". Ensure it uses the correct format (e.g., */5).");
        }
        return result;
    }

    @Override
    public String getRegex(){
        return "^\\*\\/\\d+$";
    }
}