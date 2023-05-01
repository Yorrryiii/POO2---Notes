package huertoUrbanoCompartido;

public class Cliente {
    private static int contadorIds = 0;

    private int idCliente;
    private String nombre;
    private String apellido;
    private String telefono;

    public Cliente(String nombre, String apellido, String telefono) {
        this.idCliente = generarIdUnico();
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    private static int generarIdUnico() {
        return ++contadorIds;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

