package com.sv.proye.tecaapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static Date getDateFromStringFormat(String stringDate, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
        Date d = null;
        try {
            d = simpleDateFormat.parse(stringDate);
        } catch (Exception ignore) {
        }
        return d;
    }

    public static Date getActualDate() {
        return new Date();
    }
}
