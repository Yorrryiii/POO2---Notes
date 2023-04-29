package poblacion;

import java.util.ArrayList;
import java.util.List;

public class Provincia {
    private String nombre;
    private List<Persona> personas;

    public Provincia(String nombre) {
        this.nombre = nombre;
        this.personas = new ArrayList<>();
    }

    public void addPersona(Persona persona) {
        personas.add(persona);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "\n\tProvincia{" +
                "nombre='" + nombre + '\'' +
                ", personas=" + personas +
                '}';
    }
}

