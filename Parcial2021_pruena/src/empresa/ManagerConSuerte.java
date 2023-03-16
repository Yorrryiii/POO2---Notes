package Parcial2021.src.empresa;

public class ManagerConSuerte {
    protected int suerte;

    public ManagerConSuerte(){
        this.suerte = (int) (Math.random() * 1000 + 1);
    }

    public void trabaja(Empresa empresa){
        if(empresa.trabajoPendiente > 0){
            if(Math.random() < 0.1){
                empresa.aumentarDinero(this.suerte);
                empresa.aumentarTrabajoPendiente(this.suerte / 2);
            }
        }
        empresa.reducirDinero(this.suerte / 10);
    }
}
