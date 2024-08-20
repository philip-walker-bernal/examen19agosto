package main.java.com.migracion.models;

public class Provincia {
    private int id_prov;
    private String nombre;

    // Constructor
    public Provincia() {}

    // Getters y setters
    public int getId_prov() {
        return id_prov;
    }

    public void setId_prov(int id_prov) {
        this.id_prov = id_prov;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}