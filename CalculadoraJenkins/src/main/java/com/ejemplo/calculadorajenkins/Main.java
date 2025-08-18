package com.ejemplo.calculadorajenkins;

public class Main {
    public static void main(String[] args) {
        CalculadoraJenkins calc = new CalculadoraJenkins();
        System.out.println("Suma 2+3 = " + calc.sumar(2, 3));
        System.out.println("Resta 5-2 = " + calc.restar(5, 2));
        System.out.println("Multiplica 4*6 = " + calc.multiplicar(4, 6));
        System.out.println("Divide 10/2 = " + calc.dividir(10, 2)); // mostrará 20.0 por el bug
        System.out.println("Promedio historial = " + calc.promedioHistorial());
        System.out.println("Historial = " + calc.obtenerHistorial());
    }
}
