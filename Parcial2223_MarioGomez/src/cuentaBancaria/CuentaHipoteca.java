package cuentaBancaria;

public class CuentaHipoteca extends CuentaBancaria{
    // el cliente tiene un patrón de operaciones muy determinado y
    // siempre realiza las mismas operaciones (no dependen del azar). Primero hace un ingreso de la
    // nómina (1000€-2000€), después paga la hipoteca (500€-1000€) y por último paga 3 facturas de
    // consumos (20-100€).

    private double nomina;
    private double hipoteca;
    private double facturas;

    public CuentaHipoteca(String codigoEntidad, String codigoOficina, String numeroCuenta, double nomina, double hipoteca, double facturas) {
        super(codigoEntidad, codigoOficina, numeroCuenta);
        this.nomina = nomina;
        this.hipoteca = hipoteca;
        this.facturas = facturas;
    }

    public double getNomina() {
        return nomina;
    }

    public void setNomina(double nomina) {
        this.nomina = nomina;
    }

    public double getHipoteca() {
        return hipoteca;
    }

    public void setHipoteca(double hipoteca) {
        this.hipoteca = hipoteca;
    }

    public double getFacturas() {
        return facturas;
    }

    public void setFacturas(double facturas) {
        this.facturas = facturas;
    }

    public void nextMovement() {
        nomina = (int) (Math.random() * 1001) + 1000;
        ingresar(nomina);
        hipoteca = (int) (Math.random() * 501) + 500;
        retirar(hipoteca);
        for (int i = 0; i < 3; i++) {
            facturas = (int) (Math.random() * 81) + 20;
            retirar(facturas);
        }
    }

    public void calcularIntereses() {
        double intereses = getSaldo() * 0.005;
        setSaldo(getSaldo() - intereses);
        if (getSaldo() < 0) {
            setSaldo(getSaldo() - getSaldo() * 0.05);
        }

    }   

    @Override
    public void simulaUnMes() {
        super.simulaUnMes();
    }
}
