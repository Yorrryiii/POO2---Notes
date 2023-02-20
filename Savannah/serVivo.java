package Savannah;

public class serVivo{

    public String nombre;
    public int edad;
    public String sexo;
    public int salud;
    public boolean ataque;
    public boolean defensa;


    public serVivo(String nombre, int edad, String sexo) {
        // Constructor por defecto
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.salud = 100;
        this.ataque = false;
        this.defensa = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public boolean isAtaque() {
        return ataque;
    }

    public void setAtaque(boolean ataque) {
        this.ataque = ataque;
    }

    public boolean isDefensa() {
        return defensa;
    }

    public void setDefensa(boolean defensa) {
        this.defensa = defensa;
    }

}