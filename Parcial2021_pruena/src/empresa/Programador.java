package Parcial2021.src.empresa;

public class Programador {
    protected int horasTrabajadas;
    protected int sueldo;

    public Programador(){
        this.horasTrabajadas = (int) (Math.random() * 6 + 5);
        this.sueldo = (int) (Math.random() * 6 + 5);
    }

    public void trabaja(Empresa empresa){
        if(empresa.trabajoPendiente > 0){
            empresa.reducirDinero(this.sueldo);
            empresa.reducirTrabajoPendiente(this.horasTrabajadas);
        }
    }
}
