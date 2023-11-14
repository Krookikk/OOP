package org.example;

import java.io.*;
import java.util.ArrayList;

public class FindStr {
    public static ArrayList<Long> find(String fileName, String subStr) throws IOException {

        var str = new RandomAccessFile(fileName, "r");
        long strLen = str.length();
        int subStrLen = subStr.length();
        var answ = new ArrayList<Long>();
        long position = 0;

        while (position < strLen) {
            str.seek(position);
            byte[] blockByte = new byte[5];
            int bytesStr = str.read(blockByte);

            String blockStr = new String(blockByte, 0, bytesStr, "UTF-8");

            int index = blockStr.indexOf(subStr);
            while (index != -1) {
                answ.add(position + index);
                index = blockStr.indexOf(subStr, index + 1);
            }

            if (bytesStr < 5) {
                position += bytesStr;
            } else {
                position += bytesStr - subStrLen + 1;
            }
        }
        return answ;

    }
}
