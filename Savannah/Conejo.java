package Savannah;

public class Conejo extends Herbivoros {

    public Conejo(String nombre, int edad, String sexo) {
        super(nombre, edad, sexo);
    }

    public void reproducirse(Conejo conejo) {
        if (this.getSexo() != conejo.getSexo()) {
            System.out.println("Se han reproducido");
        } else {
            System.out.println("No se pueden reproducir");
        }
    }

    public void dormir() {
        this.setSalud(this.getSalud() + 10);
    }

    public void jugar(Conejo conejo) {
        if (conejo.isDefensa()) {
            conejo.setSalud(conejo.getSalud() - 10);
        } else {
            conejo.setSalud(conejo.getSalud() - 20);
        }
    }

    public void pelear(Conejo conejo) {
        if (conejo.isDefensa()) {
            conejo.setSalud(conejo.getSalud() - 10);
        } else {
            conejo.setSalud(conejo.getSalud() - 20);
        }
    }
}

