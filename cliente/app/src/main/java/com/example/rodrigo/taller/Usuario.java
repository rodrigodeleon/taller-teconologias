package com.example.rodrigo.taller;

import java.io.Serializable;

/**
 * Created by Rodrigo on 8/8/16.
 */
public class Usuario implements Serializable{

    private int Id;
    private String Nombre;



    public Usuario(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
