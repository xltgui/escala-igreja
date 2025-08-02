package com.github.xltgui.escalaigreja.domain.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;

@RequiredArgsConstructor
@Getter
public enum WeekDay {
    SEGUNDA_FEIRA(DayOfWeek.MONDAY),
    TERCA_FEIRA(DayOfWeek.TUESDAY),
    QUARTA_FEIRA(DayOfWeek.WEDNESDAY),
    QUINTA_FEIRA(DayOfWeek.THURSDAY),
    SEXTA_FEIRA(DayOfWeek.FRIDAY),
    SABADO(DayOfWeek.SATURDAY),
    DOMINGO(DayOfWeek.SUNDAY);

    private final DayOfWeek dayOfWeek;

    public static WeekDay toPortuguese(DayOfWeek dayOfWeek) {
        for (WeekDay value : WeekDay.values()) {
            if (value.getDayOfWeek() == dayOfWeek) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid DayOfWeek: " + dayOfWeek);
    }

}
