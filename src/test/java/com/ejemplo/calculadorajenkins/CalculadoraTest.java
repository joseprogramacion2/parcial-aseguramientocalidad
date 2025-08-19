package com.ejemplo.calculadorajenkins;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class CalculadoraJenkinsTest {
    
    @Test
    void sumar_ok() {
        var c = new CalculadoraJenkins();
        assertEquals(5, c.sumar(2, 3));    
    }
    
    @Test
    void restar_ok() {
        var c = new CalculadoraJenkins();
        assertEquals(3, c.restar(5, 2));   
    }
    
    @Test
    void multiplicar_ok() {
        var c = new CalculadoraJenkins();
        assertEquals(24, c.multiplicar(4, 6));    
    }
    
    @Test
    void dividir_enteros_ok() {
        var c = new CalculadoraJenkins();
        assertEquals(5.0, c.dividir(10, 2), 1e-9);   
    }
    
    @Test
    void promedioHistorial() {
        var c = new CalculadoraJenkins();
        assertEquals(0.0, c.promedioHistorial(), 1e-9);  
    }
    
    @Test
    void historial_registraResultados_enOrden() {
        var c = new CalculadoraJenkins();
        c.sumar(2, 3);        // 5.0
        c.restar(7, 4);       // 3.0
        c.multiplicar(3, 4);  // 12.0
        c.dividir(10, 2);     // 5.0
        List<Double> esperado = List.of(5.0, 3.0, 12.0, 5.0);
        assertIterableEquals(esperado, c.obtenerHistorial());
    }
}


