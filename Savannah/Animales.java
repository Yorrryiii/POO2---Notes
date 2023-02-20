package Savannah;

public class Animales extends serVivo {

    public Animales(String nombre, int edad, String sexo) {
        super(nombre, edad, sexo);
    }

    public void atacar(Animales animal) {
        if (animal.isDefensa()) {
            animal.setSalud(animal.getSalud() - 10);
        } else {
            animal.setSalud(animal.getSalud() - 20);
        }
    }

    public void defender() {
        this.setDefensa(true);
    }

    public void noDefender() {
        this.setDefensa(false);
    }

    public void curar() {
        this.setSalud(this.getSalud() + 10);
    }

    public void morir() {
        this.setSalud(0);
    }

    public void reproducirse(Animales animal) {
        if (this.getSexo() != animal.getSexo()) {
            System.out.println("Se han reproducido");
        } else {
            System.out.println("No se pueden reproducir");
        }
    }

    public void comer(Animales animal) {
        this.setSalud(this.getSalud() + 10);
        animal.morir();
    }

    public void dormir() {
        this.setSalud(this.getSalud() + 10);
    }

    public void jugar(Animales animal) {
        if (animal.isDefensa()) {
            animal.setSalud(animal.getSalud() - 10);
        } else {
            animal.setSalud(animal.getSalud() - 20);
        }
    }

    public void pelear(Animales animal) {
        if (animal.isDefensa()) {
            animal.setSalud(animal.getSalud() - 10);
        } else {
            animal.setSalud(animal.getSalud() - 20);
        }
    }

    public void huir(Animales animal) {
        if (animal.isDefensa()) {
            animal.setSalud(animal.getSalud() - 10);
        } else {
            animal.setSalud(animal.getSalud() - 20);
        }
    }

    public void correr() {
        this.setSalud(this.getSalud() - 10);
    }

    public void nadar() {
        this.setSalud(this.getSalud() - 10);
    }

    public void volar() {
        this.setSalud(this.getSalud() - 10);
    }

    public void saltar() {
        this.setSalud(this.getSalud() - 10);
    }
}
