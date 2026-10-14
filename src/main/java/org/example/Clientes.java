package org.example;

public class Clientes {
    private String codigoCc;
    private String nombre;
    private String direccion;
    private String telefono;

    public Clientes(String codigoCc, String nombre, String direccion, String telefono){
        this.codigoCc = codigoCc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getCodigoCc() {return codigoCc;}
    public void setCodigoCc(String codigoCc){this.codigoCc=codigoCc;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono){this.telefono = telefono;}

    public String toFileString(){
        return codigoCc+";"+nombre+";"+direccion+";"+telefono;

    }

    @Override
    public String toString(){
        return "Codigo CC: "+codigoCc+" | Nombre: "+nombre+" | Direccion "+direccion+" | Telefono: "+telefono;
    }
}