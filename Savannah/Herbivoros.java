package Savannah;

public class Herbivoros extends Animales {

    public Herbivoros(String nombre, int edad, String sexo) {
        super(nombre, edad, sexo);
    }

    public void comer(Plantas planta) {
        this.setSalud(this.getSalud() + 10);
        planta.morir();
    }

    public void comer(Animales animal) {
        this.setSalud(this.getSalud() + 10);
        animal.morir();
    }
}