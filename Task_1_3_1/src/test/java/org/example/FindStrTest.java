package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;


public class FindStrTest {
    @Test
    public void testEng() throws IOException {
        var fileName = "example.txt";
        var subStr = "abr";
        List<Long> a = Arrays.asList(0L, 7L);
        assertEquals(a, FindStr.find(fileName, subStr));
    }

    @Test
    public void testRu() throws IOException {
        var fileName = "example.txt";
        var subStr = "аб";
        List<Long> a = Arrays.asList(12L, 14L, 16L);
        assertEquals(a, FindStr.find(fileName, subStr));
    }

    @Test
    public void testJapan() throws IOException {
        var fileName = "example.txt";
        var subStr = "ツ";
        List<Long> a = Arrays.asList(22L, 27L); 
        assertEquals(a, FindStr.find(fileName, subStr));
    }

    public void testBigFile() throws IOException {
        String fileName = "src/main/resources/File.txt";
        long fileSize = (long) 1024 * 1024 * 1024 * 15;
        File file2 = new File(fileName);

        try (var file = new RandomAccessFile(fileName, "rw")) {

            Random random = new Random();
            byte[] blockByte = new byte[1024 * 1024];

            while (fileSize > 0) {
                random.nextBytes(blockByte);
                file.write(blockByte, 0, 1024 * 1024);
                fileSize -= 1024 * 1024;
            }
            String n = "dfdf";
            byte[] sub = n.getBytes();
            file.write(sub);
        }
        finally {
            file2.delete();
        }

    }
}
