package Parcial2021;

public class Empresa {
    private int dinero;
    private int trabajoPendiente;
    private DNI[] empleados;

    public Empresa(int dinero, int trabajoPendiente, DNI[] empleados) {
        this.dinero = dinero;
        this.trabajoPendiente = trabajoPendiente;
        this.empleados = empleados;
    }

    public void aumentarDinero(int dinero) {
        this.dinero += dinero;
    }

    public void reducirDinero(int dinero) {
        this.dinero -= dinero;
        if (this.dinero < 0) {
            System.out.println("La empresa esta en números rojos");
        }
    }

    public void aumentarTrabajoPendiente(int trabajoPendiente) {
        this.trabajoPendiente += trabajoPendiente;
    }

    public void reducirTrabajoPendiente(int trabajoPendiente) {
        this.trabajoPendiente -= trabajoPendiente;
    }

}
