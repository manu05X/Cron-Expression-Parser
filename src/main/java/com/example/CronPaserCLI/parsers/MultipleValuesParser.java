package com.example.CronPaserCLI.parsers;


import com.example.CronPaserCLI.exceptions.InvalidFormatException;
import com.example.CronPaserCLI.model.TimeField;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MultipleValuesParser extends Parser {

    private boolean isValid(final int timeValue, final TimeField timeField) {
        return timeValue >= timeField.getStartValue() && timeValue <= timeField.getEndValue();
    }
    @Override
    public List<Integer> getTimings(final TimeField timeField, final String cronExpression) throws InvalidFormatException {
        Set<Integer> result = new TreeSet<>();
        String[] cronValues = cronExpression.split(",");
        try {
            for (String cronValue : cronValues) {
                int timeValue = Integer.parseInt(cronValue);
                if (isValid(timeValue, timeField)) {
                    result.add(timeValue);
                } else {
                    throw new InvalidFormatException("Invalid value: " + timeValue + ". Value must be between " +
                            timeField.getStartValue() + " and " + timeField.getEndValue());
                }
            }
        } catch (NumberFormatException ex) {
            throw new InvalidFormatException("Invalid format: " + cronExpression + ". Ensure it uses a valid comma-separated list of values.");
        }
        return new ArrayList<>(result);
    }

    @Override
    public String getRegex(){
        return "^\\d+(,\\d+)*$";
    }
}