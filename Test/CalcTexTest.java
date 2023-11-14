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
        assertEquals(0.25,CalcTex.calc(equation,2));
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


    @Test
    void roundTest1(){
        assertEquals(0.269, CalcTex.round(0.268565,3));
    }

    @Test
    void calculation1(){
        equation = "\\frac{\\frac{90}{100}\\cdot \\frac{2}{100}}{\\frac{67}{1000}}";
        assertEquals(0.269,CalcTex.calc(equation, 3));
    }

    @Test
    void calculation2(){
        equation = "\\frac{18}{67}";
        assertEquals(0.269,CalcTex.calc(equation, 3));
    }
    @Test
    void calculation3(){
        equation = "\\frac{\\frac{5}{100}\\cdot\\frac{98}{100}}{\\frac{67}{1000}}";
        assertEquals(0.731,CalcTex.calc(equation, 3));
    }

    @Test
    void calculation4(){
        equation = "\\frac{\\frac{10}{100}\\cdot \\frac{2}{100}}{\\frac{933}{1000}}";
        assertEquals(0.0021,CalcTex.calc(equation, 4));
    }
    @Test
    void calculation5(){
        equation = "2^{10}";
        assertEquals(1024,CalcTex.calc(equation, 4));
    }

    @Test
    void calculation6(){
        equation = "2^{10+5}*2";
        assertEquals(65536,CalcTex.calc(equation, 4));
    }

    @Test
    void calculation7(){
        equation = "\\frac{2^{10+5}*2}{10}";
        assertEquals(6553.6,CalcTex.calc(equation, 4));
    }
}