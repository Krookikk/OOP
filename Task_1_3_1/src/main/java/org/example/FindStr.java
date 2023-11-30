package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FindStr {

    public static ArrayList<Long> find(String fileName, String nameSubStr) throws IOException {
        try (InputStream inputStream = FindStr.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("file not found");
            }

            try (var str = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                byte[] sub = nameSubStr.getBytes();
                String subStr = new String(sub, StandardCharsets.UTF_8);

                int subStrLen = subStr.length();
                ArrayList<Long> answ = new ArrayList<>();
                long position = 0;

                int blockByteSize = 1024;
                char[] blockByte = new char[blockByteSize];

                String line = "";

                int len = 0;
                int charRead = -1;
                while (true) {
                    while (len < blockByteSize && (charRead = str.read()) != - 1){
                        blockByte[len] = ((char) charRead);
                        len ++;
                    }

                    String blockStr = new String(blockByte, 0, len);
                    blockStr = line + blockStr;

                    int index = blockStr.indexOf(subStr);
                    while (index != -1) {
                        answ.add(position + index);
                        index = blockStr.indexOf(subStr, index + 1);
                    }

                    if (charRead == -1){
                        return answ;
                    }

                    position += len - subStrLen + 1;
                    line = blockStr.substring(len - subStrLen + 1 + line.length());
                    len = 0;


                }
            }
        }
    }
}
