package Savannah;

public class Plantas extends serVivo{
    
        public Plantas(String nombre, int edad, String sexo) {
            super(nombre, edad, sexo);
        }
    
        public void reproducirse(Plantas planta) {
            if (this.getSexo() != planta.getSexo()) {
                System.out.println("Se han reproducido");
            } else {
                System.out.println("No se pueden reproducir");
            }
        }
    
        public void comer(Plantas planta) {
            this.setSalud(this.getSalud() + 10);
            planta.morir();
        }
    
        public void morir() {
            this.setSalud(0);
        }
    
        public void dormir() {
            this.setSalud(this.getSalud() + 10);
        }
    
        public void jugar(Plantas planta) {
            if (planta.isDefensa()) {
                planta.setSalud(planta.getSalud() - 10);
            } else {
                planta.setSalud(planta.getSalud() - 20);
            }
        }
    
        public void pelear(Plantas planta) {
            if (planta.isDefensa()) {
                planta.setSalud(planta.getSalud() - 10);
            } else {
                planta.setSalud(planta.getSalud() - 20);
            }
        }
}