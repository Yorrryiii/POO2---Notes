package Parcial2021.src.empresa;

public class Manager {
    public void trabaja(Empresa empresa){
        if(empresa.trabajoPendiente > 0){
            if(Math.random() < 0.1){
                empresa.aumentarDinero(100);
                empresa.aumentarTrabajoPendiente(100);
            }
        }
    }
}
