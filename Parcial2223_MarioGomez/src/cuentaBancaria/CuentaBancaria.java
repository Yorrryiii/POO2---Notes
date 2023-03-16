package cuentaBancaria;

public class CuentaBancaria {
    private String codigoEntidad;
    private String codigoOficina;
    private String digitoControl;
    private String numeroCuenta;
    private double saldo;

    public CuentaBancaria(String codigoEntidad, String codigoOficina) {
        this.codigoEntidad = codigoEntidad;
        this.codigoOficina = codigoOficina;
        this.digitoControl = calcularDigitosControl(codigoEntidad, codigoOficina, numeroCuenta);
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0;
    }

    public CuentaBancaria(String codigoEntidad, String codigoOficina, String numeroCuenta) {
        this.codigoEntidad = codigoEntidad;
        this.codigoOficina = codigoOficina;
        this.digitoControl = calcularDigitosControl(codigoEntidad, codigoOficina, numeroCuenta);
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0;
    }

    public String getCodigoEntidad() {
        int codigoEntidad = (int) (Math.random() * 9000) + 1000;
        String codigoEntidadString = String.valueOf(codigoEntidad);
        return codigoEntidadString;
    }

    public String getCodigoOficina() {
        int codigoOficina = (int) (Math.random() * 9000) + 1000;
        String codigoOficinaString = String.valueOf(codigoOficina);
        return codigoOficinaString;
    }

    public String getDigitoControl() {
        return digitoControl;
    }

    public String getNumeroCuenta() {
        int numeroCuenta = (int) (Math.random() * 900000000) + 100000000;
        String numeroCuentaString = String.valueOf(numeroCuenta);
        return numeroCuentaString;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNumeroCuentaCompleto() {
        String numeroCuentaCompleto = getCodigoEntidad() + "-" + getCodigoOficina() + "-" + getDigitoControl() + "-" + getNumeroCuenta();
        return numeroCuentaCompleto;
    }

    public static int sumarAscii(String cadena) {
        int suma = 0;
        for (int i = 0; i < cadena.length(); i++) {
            suma += cadena.charAt(i);
        }
        return suma;
    }

    public static String calcularDigitosControl(String codigoEntidad, String codigoOficina, String numeroCuenta) {
        int codigoEntidadInt = Integer.parseInt(codigoEntidad);
        int codigoOficinaInt = Integer.parseInt(codigoOficina);
        int numeroCuentaInt = Integer.parseInt(numeroCuenta);
        int resultado = (codigoEntidadInt * codigoOficinaInt) + numeroCuentaInt;
        int modulo = resultado % 99;
        String moduloString = String.valueOf(modulo);

        return moduloString;
    }

    public static void cerrarOficina(CuentaBancaria[] cuentas, String codigoEntidad, String codigoOficina, String codigoOficinaDestino) {
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i].getCodigoEntidad().equals(codigoEntidad) && cuentas[i].getCodigoOficina().equals(codigoOficina)) {
                cuentas[i].codigoOficina = codigoOficinaDestino;
                cuentas[i].digitoControl = calcularDigitosControl(cuentas[i].codigoEntidad, cuentas[i].codigoOficina, cuentas[i].numeroCuenta);
            }
        }
    }

    public void nextMovement() {
        if (saldo < 0) {
            System.out.println("No se puede realizar ningún movimiento ya que el saldo de su cuenta es inferior a 0");
        } else {
            System.out.println("El cliente puede realizar un movimiento de retirada o de ingreso");
        }
    }

    public void ingresar(double cantidad) {
        saldo += cantidad;
    }

    public void retirar(double cantidad) {
        saldo -= cantidad;
    }

    public void transferir(CuentaBancaria cuentaDestino, double cantidad) {
        saldo -= cantidad;
        cuentaDestino.ingresar(cantidad);
    }

    public void gastosMensuales() {
        double gastoFijo = Math.random() * (10 - 5) + 5;
        double penalizacion = saldo * 0.01;
        saldo -= gastoFijo + penalizacion;
    }

    public void mostrarCuenta() {
        System.out.println("Cuenta Bancaria: " + getNumeroCuentaCompleto());
        System.out.println("Saldo: " + saldo);
    }

    public void calcularIntereses(){
        double gastoFijo = Math.random() * (10 - 5) + 5;
        double penalizacion = saldo * 0.01;
        saldo -= gastoFijo + penalizacion;
    }
    
    public void simulaUnMes(){
        // Se simula un mes con 30 días.
        for (int i = 0; i < 30; i++) {
            // Se simula un día.
            // Se genera un número aleatorio entre 0 y 1.
            double numeroAleatorio = Math.random();
            // Si el número aleatorio es menor que 0.5, se realiza un movimiento de ingreso.
            if (numeroAleatorio < 0.5) {
                // Se genera un número aleatorio entre 0 y 100.
                double cantidad = Math.random() * 100;
                // Se realiza el movimiento de ingreso.
                ingresar(cantidad);
                // Se imprime por pantalla el movimiento realizado.
                System.out.println("El cliente ha realizado un ingreso de " + cantidad + "€");
            } else {
                // Se genera un número aleatorio entre 0 y 100.
                double cantidad = Math.random() * 100;
                // Se realiza el movimiento de retirada.
                retirar(cantidad);
                // Se imprime por pantalla el movimiento realizado.
                System.out.println("El cliente ha realizado una retirada de " + cantidad + "€");
            }
            // Se imprime por pantalla el saldo de la cuenta.
            System.out.println("Saldo: " + saldo);
        }

    }
}
