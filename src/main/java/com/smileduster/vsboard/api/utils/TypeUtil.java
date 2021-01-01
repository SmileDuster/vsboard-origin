package com.smileduster.vsboard.api.utils;

import java.util.Calendar;
import java.util.Date;

public final class TypeUtil {

    public static Date parseDate(String raw) {
        String[] s = raw.split(" ");
        String[] dates = s[0].split("-");
        String[] times = s[1].split(":");
        Calendar c = Calendar.getInstance();
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int date = Integer.parseInt(dates[2]);
        int hour = Integer.parseInt(times[0]);
        int min = Integer.parseInt(times[1]);
        int sec = Integer.parseInt(times[2]);
        c.set(year, month-1, date, hour, min, sec);
        return c.getTime();
    }

    public static String formatDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR) + "-"
                + c.get(Calendar.MONTH) + "-"
                + c.get(Calendar.DATE) + " "
                + c.get(Calendar.HOUR_OF_DAY) + ":"
                + c.get(Calendar.MINUTE) + ":"
                + c.get(Calendar.SECOND);
    }

    public static byte[] parseIP(String raw) {
        byte[] bytes = new byte[32];
        String[] s = raw.split(":");
        for (int i = 0; i < 4; i++) {
            int n = Integer.parseInt(s[i]);
            for (int j = 0; j < 8; j++) {
                int p = 1 << (7-j);
                if (n >= p) {
                    n -= p;
                    bytes[i*8+j] = 1;
                }
            }
        }
        return bytes;
    }

    public static String formatIP(byte[] ip) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int n = 0;
            for (int j = i*8, k = 7; k >= 0; j++, k--) {
                if (ip[j] == 1) {
                    n += 1 << k;
                }
            }
            builder.append(n);
            if (i != 3) {
                builder.append(':');
            }
        }
        return builder.toString();
    }

}
