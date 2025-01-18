package com.example.CronPaserCLI.parsers;

import com.example.CronPaserCLI.exceptions.InvalidFormatException;
import com.example.CronPaserCLI.model.TimeField;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private final TimeField minuteField = TimeField.MINUTE;
    @Test
    void testIncrementParserValid() throws InvalidFormatException {
        Parser parser = new IncrementParser();
        List<Integer> result = null;
        try {
            result = parser.getTimings(minuteField, "*/15");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(List.of(0, 15, 30, 45), result);
    }

    @Test
    void testIncrementParserInvalidValue() {
        Parser parser = new IncrementParser();
        assertThrows(InvalidFormatException.class, () -> parser.getTimings(minuteField, "*/70"));
    }

    @Test
    void testIncrementParserInvalidFormat() {
        Parser parser = new IncrementParser();
        assertThrows(InvalidFormatException.class, () -> parser.getTimings(minuteField, "*/abc"));
    }

    @Test
    void testMultipleValuesParserValid() throws InvalidFormatException {
        Parser parser = new MultipleValuesParser();
        List<Integer> result = null;
        try {
            result = parser.getTimings(minuteField, "10,20,30,40");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(List.of(10, 20, 30, 40), result);
    }

    @Test
    void testMultipleValuesParserInvalidValue() {
        Parser parser = new MultipleValuesParser();
        assertThrows(InvalidFormatException.class, () -> parser.getTimings(minuteField, "10,20,70"));
    }

    @Test
    void testMultipleValuesParserInvalidFormat() {
        Parser parser = new MultipleValuesParser();
        assertThrows(InvalidFormatException.class, () -> parser.getTimings(minuteField, "10,,20"));
    }

    @Test
    void testNumberParserValid() throws InvalidFormatException {
        Parser parser = new NumberParser();
        List<Integer> result = null;
        try {
            result = parser.getTimings(minuteField, "25");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(List.of(25), result);
    }

    @Test
    void testNumberParserInvalidValue() {
        Parser parser = new NumberParser();
        assertThrows(InvalidFormatException.class, () -> parser.getTimings(minuteField, "70"));
    }

    @Test
    void testNumberParserInvalidFormat() {
        Parser parser = new NumberParser();
        assertThrows(InvalidFormatException.class, () -> parser.getTimings(minuteField, "abc"));
    }

    @Test
    void testRangeParserValid() throws InvalidFormatException {
        Parser parser = new RangeParser();
        List<Integer> result = null;
        try {
            result = parser.getTimings(minuteField, "10-15");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(List.of(10, 11, 12, 13, 14, 15), result);
    }

    @Test
    void testRangeParserInvalidRange() {
        Parser parser = new RangeParser();
        assertThrows(InvalidFormatException.class, () -> parser.getTimings(minuteField, "15-10"));
    }

    @Test
    void testRangeParserOutOfBounds() {
        Parser parser = new RangeParser();
        assertThrows(InvalidFormatException.class, () -> parser.getTimings(minuteField, "50-70"));
    }

    @Test
    void testRangeParserInvalidFormat() {
        Parser parser = new RangeParser();
        assertThrows(InvalidFormatException.class, () -> parser.getTimings(minuteField, "10-abc"));
    }

    @Test
    void testRegexValidationForIncrementParser() {
        Parser parser = new IncrementParser();
        assertTrue("*/15".matches(parser.getRegex()));
        assertFalse("15".matches(parser.getRegex()));
    }

    @Test
    void testRegexValidationForMultipleValuesParser() {
        Parser parser = new MultipleValuesParser();
        assertTrue("10,20,30".matches(parser.getRegex()));
        assertFalse("10-20".matches(parser.getRegex()));
    }

    @Test
    void testRegexValidationForNumberParser() {
        Parser parser = new NumberParser();
        assertTrue("25".matches(parser.getRegex()));
        assertFalse("*/25".matches(parser.getRegex()));
    }

    @Test
    void testRegexValidationForRangeParser() {
        Parser parser = new RangeParser();
        assertTrue("10-20".matches(parser.getRegex()));
        assertFalse("10,20".matches(parser.getRegex()));
    }
}