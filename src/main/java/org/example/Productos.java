package org.example;

public class Productos {
    private String codigo;
    private String descripcion;
    private double valor;

    public Productos(String codigo, String descripcion, double valor){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.valor= valor;
    }

    public String getCodigo(){return codigo;}
    public void setCodigo(String codigo){this.codigo=codigo;}
    public String getDescripcion(){return descripcion;}
    public void setDescripcion(String descripcion){this.descripcion=descripcion;}
    public double getValor(){return valor;}
    public void setValor(double valor){this.valor=valor;}

    public String toFileString(){
        return codigo +";"+ descripcion+";"+valor;
    }
    @Override
    public String toString(){
        return "Codigo: "+codigo+" | Descripcion: "+descripcion+" | Valor: $"+valor;
    }
}
