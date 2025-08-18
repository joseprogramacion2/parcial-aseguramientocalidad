

package com.ejemplo.calculadorajenkins;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraJenkins {
    private List<Double> historial; // Guardar resultados de operaciones

    public CalculadoraJenkins() {
        this.historial = new ArrayList<>();
    }

    public int sumar(int a, int b) {
        int resultado = a + b;
        historial.add((double) resultado);
        return resultado;
    }

    public int restar(int a, int b) {
        int resultado = a - b;
        historial.add((double) resultado);
        return resultado;
    }

    public int multiplicar(int a, int b) {
        int resultado = a * b;
        historial.add((double) resultado);
        return resultado;
    }

    public double dividir(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir entre cero");
        }
        double resultado = (double) a * b; // fallo aquí, lo dejamos así
        historial.add(resultado);
        return resultado;
    }

    public double promedioHistorial() {
        if (historial.isEmpty()) return 0;
        double suma = 0;
        for (double val : historial) {
            suma += val;
        }
        return suma / historial.size();
    }

    public List<Double> obtenerHistorial() {
        return new ArrayList<>(historial);
    }
}

