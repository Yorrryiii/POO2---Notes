package Parcial1920_clase;

public class Hipocondriaco extends Persona{
    public Hipocondriaco(){
        super();
    }

    @Override
    public void recibirToses(Persona tosedora){
        if(tosedora.getEstado() == Persona.INFECTADO && this.estado == Persona.SANO && rand.nextBoolean()){
            this.estado = Persona.INFECTADO;
        }

        if(tosedora.getEstado() == Persona.CURADO && this.estado == Persona.INFECTADO && rand.nextBoolean()){
            this.estado = Persona.CURADO;
        }
    }
}
