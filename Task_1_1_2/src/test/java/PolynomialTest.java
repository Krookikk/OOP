import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Polynomial;
import org.junit.jupiter.api.Test;

public class PolynomialTest {

    @Test
    public void test1() {
        var n1 = new Polynomial(new int[] {-1, 1, 0, 3, 4});
        var n2 = new Polynomial(new int[] {3, 0, 0, 1});
        var answer = new Polynomial(new int[] {2, 1, 0, 4, 4});
        assertArrayEquals(answer.mas, n1.plus(n2).mas);
    }

    @Test
    public void test2() {
        var n1 = new Polynomial(new int[] {-1, 1, 0, 3, 4});
        var n2 = new Polynomial(new int[] {3, 0, 0, 1});
        var answer = new Polynomial(new int[] {-4, 1, 0, 2, 4});
        assertArrayEquals(answer.mas, n1.minus(n2).mas);
    }

    @Test
    public void test3() {
        var n1 = new Polynomial(new int[] {2, 5});
        var n2 = new Polynomial(new int[] {3, 0, 0, 1});
        var answer = new Polynomial(new int[] {6, 15, 0, 2, 5});
        assertArrayEquals(answer.mas, n1.mul(n2).mas);
    }

    @Test
    public void test4() {
        var n1 = new Polynomial(new int[] {5, 0, -3});
        int answer = -7;
        assertEquals(answer, n1.value(2));
    }

    @Test
    public void test5() {
        var n1 = new Polynomial(new int[] {2, 3, 4, -3});
        var answer = new Polynomial((new int[] {3, 8, -9}));
        assertArrayEquals(answer.mas, n1.diff(1).mas);
    }

    @Test
    public void test5_2() {
        var n1 = new Polynomial(new int[] {2, 3, 4, -3});
        var answer = new Polynomial(new int[] {8, -18});
        assertArrayEquals(answer.mas, n1.diff(2).mas);
    }

    @Test
    public void test6() {
        var n1 = new Polynomial(new int[] {2, 3, 4, -3});
        var answer = new Polynomial(new int[] {2, 3, 4, -3});
        assert(n1.equal(answer));
    }

    @Test
    public void test7() {
        var n1 = new Polynomial(new int[] {2, 0, 4, -3});
        String answer = "- 3x^3 + 4x^2 + 2";
        assertEquals(answer, n1.toString());
    }

    @Test
    public  void test8() {
        var p1 = new Polynomial(new int[] {4, 3, 6, 7});
        var p2 = new Polynomial(new int[] {3, 2, 8});
        String answer = "7x^3 + 6x^2 + 19x + 6";
        assertEquals(answer, p1.plus(p2.diff(1)).toString());
    }
}
