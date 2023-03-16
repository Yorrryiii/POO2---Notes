package Parcial2021.src.empresa;

public class ProgramadorLadron {
    public void trabaja(Empresa empresa){
        if(empresa.trabajoPendiente >= 0){
            empresa.reducirDinero(2);
        }
    }
}
