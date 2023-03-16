package cuentaBancaria;

public class CuentaCredito extends CuentaBancaria{
    private double gastos;
    private double ingresos;

    public CuentaCredito(String codigoEntidad, String codigoOficina, String numeroCuenta, double gastos, double ingresos) {
        super(codigoEntidad, codigoOficina, numeroCuenta);
        this.gastos = gastos;
        this.ingresos = ingresos;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public void nextMovement() {
        int movimientos = (int) (Math.random() * 16) + 30;
        for (int i = 0; i < movimientos; i++) {
            int tipoMovimiento = (int) (Math.random() * 100);
            if (tipoMovimiento < 85) {
                double gasto = (int) (Math.random() * 501) + 500;
                retirar(gasto);
            } else if (tipoMovimiento < 99) {
                double gasto = (int) (Math.random() * 201) + 200;
                retirar(gasto);
            } else {
                double ingreso = (int) (Math.random() * 50001) + 50000;
                ingresar(ingreso);
            }
        }
    }

    public void calcularIntereses() {
        double intereses = (int) (Math.random() * 3) + 1;
        intereses = getSaldo() * intereses / 100;
        setSaldo(getSaldo() - intereses);
    }

    @Override
    public void simulaUnMes() {
        super.simulaUnMes();
    }

    // @Override
    // public String toString() {
    //     return "CuentaCredito [gastos=" + gastos + ", ingresos=" + ingresos + ", toString()=" + super.toString() + "]";
    // }
}
