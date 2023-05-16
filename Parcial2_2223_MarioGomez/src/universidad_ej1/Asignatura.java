package universidad_ej1;

import java.util.Random;

public class Asignatura {
    private String nombre;
    private int id;
    private int creditosECTS;

    public Asignatura(String nombre, int creditosECTS) {
        this.nombre = nombre;
        this.id = new Random().nextInt(89999) + 10000;
        this.creditosECTS = creditosECTS;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public int getCreditosECTS() {
        return creditosECTS;
    }
}

