package simulacion;
import java.util.Arrays;
import java.util.Random;

import cuentaBancaria.CuentaBancaria;
import cuentaBancaria.CuentaAhorro;
import cuentaBancaria.CuentaCredito;
import cuentaBancaria.CuentaHipoteca;

public class Simulacion {
    private CuentaBancaria[] cuentas;

    public Simulacion() {
        cuentas = new CuentaBancaria[10];
        Random rnd = new Random();

        // Crear 10 cuentas con saldos aleatorios
        for (int i = 0; i < cuentas.length; i++) {
            switch (i % 3) {
                case 0:
                    cuentas[i] = new CuentaAhorro();
                    break;
                case 1:
                    cuentas[i] = new CuentaCredito();
                    break;
                case 2:
                    cuentas[i] = new CuentaHipoteca();
                    break;
            }
        }
    }
}
