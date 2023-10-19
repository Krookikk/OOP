import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.example.Heapsort;
import org.junit.jupiter.api.Test;



public class HeapsortTest {

    @Test
    public void test1() {
        int[] test = {12, 11, 13, 5, 6, 7};
        int[] answer = {5, 6, 7, 11, 12, 13};

        test = Heapsort.heapsort(test);

        assertArrayEquals(answer, test);
    }

    @Test
    public void test2() {
        int[] test = {};
        int[] answer = {};
        test = Heapsort.heapsort(test);
        assertArrayEquals(answer, test);
    }

    @Test
    public void test3() {
        int[] test = {4, 3, 2, 4};
        int[] answer = {2, 3, 4, 4};
        test = Heapsort.heapsort(test);
        assertArrayEquals(answer, test);
    }

    @Test
    public void test4(){
        int[] test = new int[100000];
        int[] answer = new int[100000];
        Random r = new Random();
        for (int i = 0; i < 100000; i ++){
            test[i] = r.nextInt(Integer.MAX_VALUE);
            answer[i] = test[i];
        }
        Arrays.sort(answer);
        test = Heapsort.heapsort(test);
        assertArrayEquals(answer, test);
    }
}