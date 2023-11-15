import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacExpTest {
    FacExp facExp;
    @Test
    void testEval() {
        facExp = new FacExp(new NumExp(7));
        assertEquals(5040, facExp.eval());
    }
}