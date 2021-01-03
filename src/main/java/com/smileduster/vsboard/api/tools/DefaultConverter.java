package com.smileduster.vsboard.api.tools;

import com.fasterxml.uuid.impl.UUIDUtil;

import java.util.*;

public final class DefaultConverter implements Converter {

    @Override
    public Date parseDate(String raw) {
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

    @Override
    public String formatDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR) + "-"
                + c.get(Calendar.MONTH) + "-"
                + c.get(Calendar.DATE) + " "
                + c.get(Calendar.HOUR_OF_DAY) + ":"
                + c.get(Calendar.MINUTE) + ":"
                + c.get(Calendar.SECOND);
    }

    @Override
    public byte[] parseIP(String ip) {
        byte[] bytes = new byte[32];
        String[] s = ip.split(":");
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

    @Override
    public String formatIP(byte[] ip) {
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

    @Override
    public byte[] parseUUID(String uuid) {
        return UUIDUtil.asByteArray(UUIDUtil.uuid(uuid));
    }

    @Override
    public String formatUUID(byte[] uuid) {
        return UUIDUtil.uuid(uuid).toString();
    }

    @Override
    public Map<Integer, Integer> parseDataString(String raw) {
        Map<Integer, Integer> map = new HashMap<>();
        String[] c = raw.split(";");
        for (String s : c) {
            String[] ss = s.split(":");
            int number = Integer.parseInt(ss[0]);
            int data = Integer.parseInt(ss[1]);
            map.put(number, data);
        }
        return map;
    }

    @Override
    public long parseUserNo(String raw) throws IllegalArgumentException {
        if (raw.length() != 9) {
            throw new IllegalArgumentException("UserNo length should be 9");
        }
        char[] s = raw.substring(1).toUpperCase().toCharArray();
        long l = 0;
        for (int i = 0, j = 7; i < 8; i++, j--) {
            char c = s[i];
            int n;
            if (c >= 'A' && c <= 'F') {
                n = c-'A'+10;
            } else if (c >= '0' && c <= '9') {
                n = c-'0';
            } else {
                throw new IllegalArgumentException("UserNo has illegal char: " + c);
            }
            l += n * (1L << (4*j));
        }
        return l;
    }

    @Override
    public String formatUserNo(long no) throws IllegalArgumentException {
        if (no < 0 || no > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("Illegal UserNo value");
        }
        return new Formatter().format("#%08x", no).toString().toUpperCase();
    }

}
