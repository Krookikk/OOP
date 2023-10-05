import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.example.Polynomial;
import org.junit.jupiter.api.Test;

public class PolynomialTest {

    @Test
    public void test1(){
        var n1 = new Polynomial(new int[] {-1, 1, 0, 3, 4});
        var n2 = new Polynomial(new int[] {3, 0, 0, 1});
        Polynomial answer = new Polynomial(new int[] {2, 1, 0, 4, 4});
        assertArrayEquals(answer.mas, n1.plus(n2).mas);
    }

    @Test
    public void test2(){
        var n1 = new Polynomial(new int[] {-1, 1, 0, 3, 4});
        var n2 = new Polynomial(new int[] {3, 0, 0, 1});
        Polynomial answer = new Polynomial(new int[] {-4, 1, 0, 2, 4});
        assertArrayEquals(answer.mas, n1.minus(n2).mas);
    }

    @Test
    public void test3(){
        var n1 = new Polynomial(new int[] {2, 5});
        var n2 = new Polynomial(new int[] {3, 0, 0, 1});
        Polynomial answer = new Polynomial(new int[] {6, 15, 0, 2, 5});
        assertArrayEquals(answer.mas, n1.mul(n2).mas);
    }

    @Test
    public void test4(){
        var n1 = new Polynomial(new int[] {5, 0, -3});
        int answer = -7;
        assertEquals(answer, n1.value(2));
    }

}
