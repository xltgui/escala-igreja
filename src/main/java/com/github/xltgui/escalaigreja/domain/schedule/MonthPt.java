package com.github.xltgui.escalaigreja.domain.schedule;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Month;
@Getter
@RequiredArgsConstructor
public enum MonthPt {
    JANEIRO(Month.JANUARY),
    FEVEREIRO(Month.FEBRUARY),
    MARCO(Month.MARCH),
    ABRIL(Month.APRIL),
    MAIO(Month.MAY),
    JUNHO(Month.JUNE),
    JULHO(Month.JULY),
    AGOSTO(Month.AUGUST),
    SETEMBRO(Month.SEPTEMBER),
    OUTUBRO(Month.OCTOBER),
    NOVEMBRO(Month.NOVEMBER),
    DEZEMBRO(Month.DECEMBER);

    private final Month month;

    public static MonthPt toPortuguese(Month month){
        for(MonthPt value : MonthPt.values()){
            if(value.getMonth() ==  month){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Month " +  month);
    }
}
