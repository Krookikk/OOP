import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.example.Heapsort;

public class HeapsortTest {

    @Test
    public void test1() {
        int[] test = {12, 11, 13, 5, 6, 7};
        int[] answer = {5, 6, 7, 11, 12, 13};

        test = Heapsort.heapsort(test);

        assertArrayEquals(answer, test);
    }


    @Test
    public void test3() {
        int[] test = {};
        int[] answer = {};
        test = Heapsort.heapsort(test);
        assertArrayEquals(answer, test);
    }

    @Test
    public void test4() {
        int[] test = {4, 3, 2, 4};
        int[] answer = {2, 3, 4, 4};
        test = Heapsort.heapsort(test);
        assertArrayEquals(answer, test);
    }
}