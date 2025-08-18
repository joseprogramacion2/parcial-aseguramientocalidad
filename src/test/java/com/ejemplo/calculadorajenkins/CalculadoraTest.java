package com.ejemplo.calculadorajenkins;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    void dividir_debeDividirBien() {
        CalculadoraJenkins c = new CalculadoraJenkins();
        // 10 / 2 debe ser 5.0, pero tu método devuelve 20.0 → este test FALLARÁ
        assertEquals(5.0, c.dividir(10, 2), 1e-9);
    }

    @Test
    void dividir_debeLanzarExcepcionConCero() {
        CalculadoraJenkins c = new CalculadoraJenkins();
        assertThrows(ArithmeticException.class, () -> c.dividir(10, 0));
    }
}
