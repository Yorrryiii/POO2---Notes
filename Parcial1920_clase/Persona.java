package Parcial1920_clase;

import java.util.Random;

public class Persona {
    protected static final int SANO = 0;
    protected static final int INFECTADO = 1;
    protected static final int CURADO = 2;  
    protected static final int MUERTO = 3;

    protected int salud;
    protected int numContactos;
    protected int estado;

    protected Random rand = new Random();

    // Constructor por defecto
    public Persona() {
        this.salud = rand.nextInt(21) + 80;
        this.numContactos = rand.nextInt(4);
    }

    public Persona (int estado){
        this();
        this.estado = estado;
    }

    public void unDiaMas(Poblacion pob){
        if(this.estado == Persona.MUERTO) return;
        toser();
        sufrirEnfermedad();
    }

    public void sufrirEnfermedad(){
        if(this.estado == Persona.INFECTADO){
            this.salud -= rand.nextInt(10);
        }
    }

    public void toser(){

    }

    public void recibirToses(Persona tosedora){
        if(tosedora.getEstado() == Persona.INFECTADO && this.estado == Persona.SANO){
            this.estado = Persona.INFECTADO;
        }

        if(tosedora.getEstado() == Persona.CURADO && this.estado == Persona.INFECTADO){
            this.estado = Persona.CURADO;
        }
    }

    public int getEstado(){
        return this.estado;
    }
}
