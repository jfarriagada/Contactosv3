package com.example.konarr.contactosv3.util;

import java.io.Serializable;

/**
 * Created by konarr on 28-09-14.
 */
public class Contacto implements Serializable{
    private String nombre, telefono, email, direccion, imageUri;

    public Contacto(String nombre, String telefono, String email, String direccion, String imageUri) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.imageUri = imageUri;
    }

    //<editor-fold desc="GETTERS">
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getImageUri() {
        return imageUri;
    }
    //</editor-fold>

    //<editor-fold desc="SETTERS">
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
    //</editor-fold>


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        if (!direccion.equals(contacto.direccion)) return false;
        if (!email.equals(contacto.email)) return false;
        if (!imageUri.equals(contacto.imageUri)) return false;
        if (!nombre.equals(contacto.nombre)) return false;
        if (!telefono.equals(contacto.telefono)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + telefono.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + direccion.hashCode();
        result = 31 * result + imageUri.hashCode();
        return result;
    }
}
