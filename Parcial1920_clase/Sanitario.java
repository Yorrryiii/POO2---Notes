package Parcial1920_clase;

public class Sanitario extends Persona{
    private int pacientesDiarios;

    public Sanitario(int pacientesDiarios){
        super();
        this.pacientesDiarios = pacientesDiarios;
    }

    @Override
    public void unDiaMas(Poblacion poblacion){
        // El super es para llamar al contructor del padre
        super.unDiaMas(poblacion);
        curar();
    }

    @Override
    public void recibirToses(Persona tosedora){
        super.recibirToses(tosedora);
    }

    public void curar(){
    }
}
