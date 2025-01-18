package com.example.CronPaserCLI.managers.impl;


import com.example.CronPaserCLI.exceptions.InvalidFormatException;
import com.example.CronPaserCLI.managers.ParsingManager;
import com.example.CronPaserCLI.model.TimeField;
import com.example.CronPaserCLI.parsers.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class ParsingManagerImpl implements ParsingManager {

    Set<Parser> parserSet = new HashSet<>();

    @Override
    public List<Integer> getTimingsList(final TimeField timeField, final String cronExpression) throws InvalidFormatException {
        for(Parser parser: parserSet) {
            if(Pattern.matches(parser.getRegex(),cronExpression)) {
                try {
                    return parser.getTimings(timeField,cronExpression);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new InvalidFormatException("Invalid cron expression entered! Cannot be parsed!");
    }

    @Override
    public void registerParsers() {
        parserSet.add(new StarParser());
        parserSet.add(new IncrementParser());
        parserSet.add(new MultipleValuesParser());
        parserSet.add(new NumberParser());
        parserSet.add(new RangeParser());
    }
}