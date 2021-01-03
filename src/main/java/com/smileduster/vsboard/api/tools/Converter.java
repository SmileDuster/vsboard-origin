package com.smileduster.vsboard.api.tools;

import java.util.Date;
import java.util.Map;

public interface Converter {

    Date parseDate(String raw);

    String formatDate(Date date);

    byte[] parseIP(String ip);

    String formatIP(byte[] ip);

    byte[] parseUUID(String uuid);

    String formatUUID(byte[] uuid);

    Map<Integer, Integer> parseDataString(String raw);

    long parseUserNo(String raw);

    String formatUserNo(long no);

}
