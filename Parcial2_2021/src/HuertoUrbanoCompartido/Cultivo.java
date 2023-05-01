package huertoUrbanoCompartido;

public class Cultivo {
    private String nombre;
    private String necesidadesAgua;
    private int cantidadPlantas;
    
    public Cultivo(String nombre, String necesidadesAgua, int cantidadPlantas) {
        this.nombre = nombre;
        this.necesidadesAgua = necesidadesAgua;
        this.cantidadPlantas = cantidadPlantas;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNecesidadesAgua() {
        return necesidadesAgua;
    }
    
    public void setNecesidadesAgua(String necesidadesAgua) {
        this.necesidadesAgua = necesidadesAgua;
    }
    
    public int getCantidadPlantas() {
        return cantidadPlantas;
    }
    
    public void setCantidadPlantas(int cantidadPlantas) {
        this.cantidadPlantas = cantidadPlantas;
    }
}

