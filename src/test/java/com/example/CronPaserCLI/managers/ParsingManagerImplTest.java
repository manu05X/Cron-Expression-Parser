package com.example.CronPaserCLI.managers;

import com.example.CronPaserCLI.exceptions.InvalidFormatException;
import com.example.CronPaserCLI.managers.impl.ParsingManagerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.CronPaserCLI.model.TimeField;
import com.example.CronPaserCLI.parsers.Parser;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
class ParsingManagerImplTest {

    @Mock
    private Parser mockParser;

    @InjectMocks
    private ParsingManagerImpl parsingManagerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        parsingManagerImpl.registerParsers(); // Registers the parsers (if needed)
    }


    @Test
    void testGetTimingsListWithInvalidCronExpression() {

        String cronExpression = "invalid";
        TimeField timeField = TimeField.MINUTE;

        try {
            parsingManagerImpl.getTimingsList(timeField, cronExpression);
        } catch (InvalidFormatException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}