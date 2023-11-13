import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcTexTest {

    String equation;

    @Test
    void stringToExp1() {
        equation = "(2+2)/(4)+3/4*2+2";
        CalcTex.stringToExp(equation).print();
        assertEquals(4.5,CalcTex.stringToExp(equation).eval());
    }

    @Test
    void stringToExp2() {
        equation = "(2+2)";
        assertEquals(4,CalcTex.stringToExp(equation).eval());
    }

    @Test
    void stringToExp3() {
        equation = "(2+2)/(4)";
        assertEquals(1,CalcTex.stringToExp(equation).eval());
    }
    @Test
    void stringToExp4() {
        equation = "(2+2)/(4+(4))";
        assertEquals(0.5,CalcTex.stringToExp(equation).eval());
    }

    @Test
    void stringToExp5() {
        equation = "(2+2)/(4)+3/4*(2+2)";
        CalcTex.stringToExp(equation).print();
        assertEquals(4,CalcTex.stringToExp(equation).eval());
    }

    @Test
    void stringToExp6() {
        equation = "\\frac{\\frac{4}{4}}{4}";
        assertEquals(0.25,CalcTex.stringToExp(equation).eval());
    }

    @Test
    void stringToExp7() {
        equation = "(2+2)*4+3/5*(2+3)";
        CalcTex.stringToExp(equation).print();
        assertEquals(19,CalcTex.stringToExp(equation).eval());
    }

    @Test
    void stringToExp8() {
        equation = "(((2+2))*4)+3/5*(2+3)";
        CalcTex.stringToExp(equation).print();
        assertEquals(19,CalcTex.stringToExp(equation).eval());
    }

    @Test
    void stringToExp9() {
        equation = "(((2+2))*4)";
        CalcTex.stringToExp(equation).print();
        assertEquals(16,CalcTex.stringToExp(equation).eval());
    }

    @Test
    void stringToExp10() {
        equation = "(((2+2))*(4))";
        CalcTex.stringToExp(equation).print();
        assertEquals(16,CalcTex.stringToExp(equation).eval());
    }

    @Test
    void stringToExp11() {
        equation = "(2+2*4)";
        CalcTex.stringToExp(equation).print();
        assertEquals(10,CalcTex.stringToExp(equation).eval());
    }

    @Test
    void stringToExp12() {
        equation = "(2+2)*4";
        CalcTex.stringToExp(equation).print();
        assertEquals(16,CalcTex.stringToExp(equation).eval());
    }
}