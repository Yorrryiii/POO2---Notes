package HuertoUrbanoCompartido;

public class Cliente {
    private static int ultimoId = 0;

    private String nombre;
    private String apellido;
    private String telefono;
    private int id;

    public Cliente(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.id = ++ultimoId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cliente [Nombre = " + nombre + ", Apellido = " + apellido + ", Telefono = " + telefono + ", Id = " + id + "]";
    }
}
