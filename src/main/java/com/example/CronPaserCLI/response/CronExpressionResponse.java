package com.example.CronPaserCLI.response;

import com.example.CronPaserCLI.model.ResponseModel;
import com.example.CronPaserCLI.model.TimeField;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Component
public class CronExpressionResponse {

    public void printResponseByModel(ResponseModel responseModel) {
        StringBuffer b = new StringBuffer();
        b.append(String.format("%-14s%s\n", TimeField.MINUTE.getName(), printList(responseModel.getMinute())));
        b.append(format("%-14s%s\n", TimeField.HOUR.getName(), printList(responseModel.getHour())));
        b.append(format("%-14s%s\n", TimeField.DAY_OF_MONTH.getName(), printList(responseModel.getDayOfMonth())));
        b.append(format("%-14s%s\n", TimeField.MONTH.getName(), printList(responseModel.getMonth())));
        b.append(format("%-14s%s\n", TimeField.DAY_OF_WEEK.getName(), printList(responseModel.getDayOfWeek())));
        b.append(format("%-14s%s\n", "command", responseModel.getCronCommand()));
        System.out.println(b);
    }

    private String printList(final List<Integer> cronValues) {
        String result = "";
        for(Integer cronValue: cronValues) {
            result += cronValue.toString() + " ";
        }
        return result;
    }
}