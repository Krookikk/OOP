package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FindStrTest {
    @Test
    public void test() throws IOException {
        var fileName = "example.txt";
        var subStr = "abc";
        List<Long> a = Arrays.asList(0L, 15L);
        assertEquals(a, FindStr.find(fileName, subStr));
    }
}
