package empresa;

public class ProgramadorLadron implements Trabajador{
    public void trabaja(Empresa empresa){
        empresa.reducirDinero(2);
    }
}
