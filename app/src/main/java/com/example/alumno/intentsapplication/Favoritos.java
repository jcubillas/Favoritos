package com.example.alumno.intentsapplication;

import com.orm.SugarRecord;

public class Favoritos extends SugarRecord<Favoritos> {

    String nombre;
    String url;

    public Favoritos(){ }

    public Favoritos(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

