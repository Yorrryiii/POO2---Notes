package empresa;

public class Empresa {
    private int dinero;
    private int trabajoPendiente;
    private Trabajador[] empleados;

    public Empresa(int dinero, int trabajoPendiente, Trabajador[] empleados) {
        this.dinero = dinero;
        this.trabajoPendiente = trabajoPendiente;
        this.empleados = empleados;
    }

    public void aumentarDinero(int dinero) {
        this.dinero += dinero;
    }

    public void reducirDinero(int dinero) {
        this.dinero -= dinero;
        System.out.println("La empresa ha perdido " + dinero + "€, ahora tiene " + this.dinero + "€");
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

    public void simulaUnDia() {
        for (Trabajador empleado : this.empleados) {
            empleado.trabaja(this);
        }
    }

    public boolean bancarrota(){
        return this.dinero <= 0;
    }
}
