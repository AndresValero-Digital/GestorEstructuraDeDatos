package org.example;

public class Empleados {
    private String cc;
    private String nombre;
    private String departamento;
    private double salario;

    public Empleados(String cc, String nombre, String departamento, double salario){
        this.cc = cc;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

    public String getCc() {return cc;}
    public void setCc(String cc) { this.cc=cc;}
    public String getNombre() {return nombre;}
    public void setNombre (String nombre) {this.nombre=nombre;}
    public String getDepartamento() {return departamento;}
    public void setDepartamento(String departamento) {this.departamento = departamento;}
    public double getSalario() {return salario;}
    public void setSalario (double salario) {this.salario =salario;}


    public String toFileString() {
        return cc + ";" + nombre + ";" + departamento + ";" + salario;
    }


    @Override
    public String toString(){
        return "CC: "+cc+" | Nombre: "+nombre+" | Departamento: "+ departamento+ " | Salario : $"+salario;

    }
}
