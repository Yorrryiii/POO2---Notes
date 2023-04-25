package HuertoUrbanoCompartido;

public class Cultivo {
    private String nombre;
    private String necesidadAgua;
    private int cantidadPlantas;

    public Cultivo(String nombre, String necesidadAgua, int cantidadPlantas) {
        this.nombre = nombre;
        this.necesidadAgua = necesidadAgua;
        this.cantidadPlantas = cantidadPlantas;
    }

    public Cultivo(String nombreCultivo, int cantidadPlantas2) {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNecesidadAgua() {
        return necesidadAgua;
    }

    public void setNecesidadAgua(String necesidadAgua) {
        this.necesidadAgua = necesidadAgua;
    }

    public int getCantidadPlantas() {
        return cantidadPlantas;
    }

    public void setCantidadPlantas(int cantidadPlantas) {
        this.cantidadPlantas = cantidadPlantas;
    }

    @Override
    public String toString() {
        return "Cultivo [cantidadPlantas=" + cantidadPlantas + ", nombre=" + nombre + ", necesidadAgua=" + necesidadAgua + "]";
    }
}
