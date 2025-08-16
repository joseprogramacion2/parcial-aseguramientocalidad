package com.mycompany.calculadora;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculadoraTest {

    @Test
    public void dividir_deberiaRetornarCuatro() {
        Calculadora c = new Calculadora();
        // Si dividir() está mal (multiplica), esto FALLA
        assertEquals(4.0, c.dividir(8, 2), 1e-9);
    }

    @Test(expected = ArithmeticException.class)
    public void dividir_porCero_deberiaLanzarExcepcion() {
        new Calculadora().dividir(5, 0);
    }
}
