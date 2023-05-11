package ru.neoflex.edu.calculator.domain.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class HolidayResponse {
    private Date date;
    private List<Name> name;
    private String holidayType;

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class Date {
        private int day;
        private int month;
        private int year;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class Name {
        private String lang;
        private String text;
    }
}
