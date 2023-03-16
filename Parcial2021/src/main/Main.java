package main;

import dni.DNI;
import empresa.Empresa;
import empresa.Manager;
import empresa.ManagerConSuerte;
import empresa.Programador;
import empresa.ProgramadorLadron;
import empresa.Trabajador;

public class Main {
    public static void main(String[] args) throws Exception {
        Trabajador[] trabajadores = new Trabajador[(int) (Math.random() * 16 + 5)];
        // Se rellena el array de trabajadores con trabajadores de los cuatro tipos
        for (int i = 0; i < trabajadores.length; i++) {
            // Se crea un número aleatorio entre 0 y 3
            int tipoTrabajador = (int) (Math.random() * 4);
            // Se crea un trabajador del tipo correspondiente
            switch (tipoTrabajador) {
                case 0:
                    trabajadores[i] = new Programador();
                    break;
                case 1:
                    trabajadores[i] = new Manager();
                    break;
                case 2:
                    trabajadores[i] = new ProgramadorLadron();
                    break;
                case 3:
                    trabajadores[i] = new ManagerConSuerte();
                    break;
            }
        }
        // Se crea una empresa con 10.000 de dinero y 0 de trabajo pendiente
        Empresa empresa = new Empresa(10000, 0, trabajadores);
    }
}

// La herencia se utiliza para establecer una relación "es-un" entre dos clases,
// donde una clase (la subclase) hereda propiedades y comportamientos de otra
// clase (la superclase). La herencia es adecuada cuando una clase necesita
// ampliar o modificar una clase existente.

// Por otro lado, las interfaces se utilizan para establecer una relación
// "tiene-un" entre las clases, donde una clase implementa los métodos definidos
// en la interfaz. Las interfaces son adecuadas cuando varias clases diferentes
// necesitan implementar el mismo conjunto de métodos, pero cada una puede
// hacerlo de manera diferente.