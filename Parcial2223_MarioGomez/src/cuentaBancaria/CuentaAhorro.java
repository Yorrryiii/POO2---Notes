package cuentaBancaria;

public class CuentaAhorro extends CuentaBancaria{

    private double interes;

    public CuentaAhorro(String codigoEntidad, String codigoOficina, String numeroCuenta, double interes) {
        super(codigoEntidad, codigoOficina, numeroCuenta);
        this.interes = interes;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public void calcularIntereses() {
        double intereses = (int) (Math.random() * 3) + 1;
        intereses = getSaldo() * intereses / 100;
        setSaldo(getSaldo() - intereses);
    }

    public void nextMovement() {
        int movimientos = (int) (Math.random() * 6) + 10;
        for (int i = 0; i < movimientos; i++) {
            int tipoMovimiento = (int) (Math.random() * 2);
            if (tipoMovimiento == 0) {
                double ingreso = (int) (Math.random() * 51) + 50;
                ingresar(ingreso);
            } else {
                double retirada = (int) (Math.random() * 101) + 100;
                retirar(retirada);
            }
        }
    }

    // @Override
    // public String toString() {
    //     return "CuentaAhorro [interes=" + interes + ", toString()=" + super.toString() + "]";
    // }

    @Override
    public void simulaUnMes() {
        super.simulaUnMes();
    }


}
