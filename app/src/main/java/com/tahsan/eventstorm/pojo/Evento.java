package com.tahsan.eventstorm.pojo;

public class Evento {

    private int id;
    private String nombreEvento;
    private String imagen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Evento(int id, String nombreEvento, String imagen) {
        this.id = id;
        this.nombreEvento = nombreEvento;
        this.imagen = imagen;
    }
}
