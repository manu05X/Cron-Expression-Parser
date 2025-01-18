package com.example.CronPaserCLI.managers;


import com.example.CronPaserCLI.exceptions.InvalidFormatException;
import com.example.CronPaserCLI.model.TimeField;

import java.util.List;

public interface ParsingManager {
    List<Integer> getTimingsList(TimeField timeField, String cronExpression) throws InvalidFormatException;
    void registerParsers();
}