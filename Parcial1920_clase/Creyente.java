package Parcial1920_clase;

public class Creyente extends Persona{
    public Creyente(){
        super();
        this.estado = Persona.INFECTADO;
    }

    @Override
    public void unDiaMas(Poblacion pob){
        super.unDiaMas(pob);
        rezar();
    }

    public void rezar(){
        if(this.estado == Persona.INFECTADO){
            if(rand.nextInt(5) == 2) this.estado = Persona.CURADO;
        }
    }
}
