package com.example.CronPaserCLI.service;

import com.example.CronPaserCLI.exceptions.InvalidFormatException;
import com.example.CronPaserCLI.managers.ParsingManager;
import com.example.CronPaserCLI.model.ResponseModel;
import com.example.CronPaserCLI.response.CronExpressionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CronParserServiceTest {

    @Mock
    private ParsingManager parsingManager;

    @Mock
    private CronExpressionResponse cronExpressionResponse;

    @InjectMocks
    private CronParserService cronParserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testParseCronExpression() throws Exception {
        String cronExpression = "*/15 0 1,15 * 1-5 /usr/bin/find";

        when(parsingManager.getTimingsList(any(), eq("*/15"))).thenReturn(List.of(0, 15, 30, 45));
        when(parsingManager.getTimingsList(any(), eq("0"))).thenReturn(List.of(0));
        when(parsingManager.getTimingsList(any(), eq("1,15"))).thenReturn(List.of(1, 15));
        when(parsingManager.getTimingsList(any(), eq("*"))).thenReturn(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        when(parsingManager.getTimingsList(any(), eq("1-5"))).thenReturn(List.of(1, 2, 3, 4, 5));

        cronParserService.parseCronExpression(cronExpression);

        verify(parsingManager, times(5)).getTimingsList(any(), any());
        verify(cronExpressionResponse, times(1)).printResponseByModel(any(ResponseModel.class));
    }


    @Test
    void cron_expression_parsing_negative() {

        String invalidCronExpression = "*/15 0 1,15 # 1-59 /usr/bin/find";
        when(parsingManager.getTimingsList(any(), eq("#"))).thenThrow(new InvalidFormatException("Invalid cron expression entered! Cannot be parsed!"));

        InvalidFormatException thrown = assertThrows(InvalidFormatException.class, () -> {
            cronParserService.parseCronExpression(invalidCronExpression);
        });

        assertEquals("Invalid cron expression entered! Cannot be parsed!", thrown.getMessage());
    }


    @Test
    void cron_expression_parsing_negative2() {
        String invalidCronExpression = "# 0 1,15 * 1-59 /usr/bin/find";
        when(parsingManager.getTimingsList(any(), eq("#"))).thenThrow(new InvalidFormatException("Invalid cron expression entered! Cannot be parsed!"));

        InvalidFormatException thrown = assertThrows(InvalidFormatException.class, () -> {
            cronParserService.parseCronExpression(invalidCronExpression);
        });

        assertEquals("Invalid cron expression entered! Cannot be parsed!", thrown.getMessage());
    }

    @Test
    void cron_expression_parsing_negative_invalid_range() {

        String invalidCronExpression = "# 0 1,15 * 1-70 /usr/bin/find";
        when(parsingManager.getTimingsList(any(), eq("1-70"))).thenThrow(new InvalidFormatException("Invalid cron expression entered! Invalid range for day-of-week!"));

        InvalidFormatException thrown = assertThrows(InvalidFormatException.class, () -> {
            cronParserService.parseCronExpression(invalidCronExpression);
        });

        assertEquals("Invalid cron expression entered! Invalid range for day-of-week!", thrown.getMessage());
    }


    @Test
    void testInvalidTimeField() {
        String cronExpression = "60 0 1,15 * 1-5 /usr/bin/find";

        when(parsingManager.getTimingsList(any(), eq("60"))).thenThrow(new IllegalArgumentException("Invalid value for minute field"));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            cronParserService.parseCronExpression(cronExpression);
        });

        assertEquals("Invalid value for minute field", thrown.getMessage());
    }


    @Test
    void testEdgeCaseCronExpression() {

        String cronExpression = "0 0 1 * * /usr/bin/find";

        when(parsingManager.getTimingsList(any(), eq("0"))).thenReturn(List.of(0));
        when(parsingManager.getTimingsList(any(), eq("0"))).thenReturn(List.of(0));
        when(parsingManager.getTimingsList(any(), eq("1"))).thenReturn(List.of(1));
        when(parsingManager.getTimingsList(any(), eq("*"))).thenReturn(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        when(parsingManager.getTimingsList(any(), eq("*"))).thenReturn(List.of(1, 2, 3, 4, 5, 6, 7));

        cronParserService.parseCronExpression(cronExpression);

        verify(parsingManager, times(5)).getTimingsList(any(), any());
        verify(cronExpressionResponse, times(1)).printResponseByModel(any(ResponseModel.class));
    }


    @Test
    void testValidCronExpressionWithCommandContainingSpaces() {

        String cronExpression = "*/15 0 1,15 * 1-5 /usr/bin/find more arguments";

        when(parsingManager.getTimingsList(any(), eq("*/15"))).thenReturn(List.of(0, 15, 30, 45));
        when(parsingManager.getTimingsList(any(), eq("0"))).thenReturn(List.of(0));
        when(parsingManager.getTimingsList(any(), eq("1,15"))).thenReturn(List.of(1, 15));
        when(parsingManager.getTimingsList(any(), eq("*"))).thenReturn(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        when(parsingManager.getTimingsList(any(), eq("1-5"))).thenReturn(List.of(1, 2, 3, 4, 5));

        cronParserService.parseCronExpression(cronExpression);

        verify(parsingManager, times(5)).getTimingsList(any(), any());
        verify(cronExpressionResponse, times(1)).printResponseByModel(any(ResponseModel.class));
    }


}