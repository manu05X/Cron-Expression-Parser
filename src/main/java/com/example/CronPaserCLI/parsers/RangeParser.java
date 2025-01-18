package com.example.CronPaserCLI.parsers;


import com.example.CronPaserCLI.exceptions.InvalidFormatException;
import com.example.CronPaserCLI.model.TimeField;

import java.util.ArrayList;
import java.util.List;

public class RangeParser extends Parser {
    private boolean isValidRange(final int startValue, final int endValue, final TimeField timeField) {
        return startValue >= timeField.getStartValue() && startValue <= endValue && endValue <= timeField.getEndValue();
    }

    @Override
    public List<Integer> getTimings(final TimeField timeField, final String cronExpression) throws InvalidFormatException {
        List<Integer> result = new ArrayList<>();
        String[] rangeValues = cronExpression.split("-");
        try {
            int startValue = Integer.parseInt(rangeValues[0]);
            int endValue = Integer.parseInt(rangeValues[1]);
            if (isValidRange(startValue, endValue, timeField)) {
                while (startValue <= endValue) {
                    result.add(startValue);
                    startValue++;
                }
            } else {
                throw new InvalidFormatException("Invalid range: " + cronExpression + ". Range must be between " +
                        timeField.getStartValue() + " and " + timeField.getEndValue());
            }
        } catch (NumberFormatException ex) {
            throw new InvalidFormatException("Invalid format for range: " + cronExpression + ". Ensure it uses a valid numeric range (e.g., 5-15).");
        }
        return result;
    }

    @Override
    public String getRegex() {
        return "^\\d+-\\d+$";
    }
}