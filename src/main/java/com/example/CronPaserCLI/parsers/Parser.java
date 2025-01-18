package com.example.CronPaserCLI.parsers;


import com.example.CronPaserCLI.model.TimeField;

import java.util.List;

public abstract class Parser {
    public abstract String getRegex();
    public abstract List<Integer> getTimings(TimeField timeField, String cronExpression) throws InterruptedException;
}