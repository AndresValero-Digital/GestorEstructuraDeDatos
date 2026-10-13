package org.example;

import java.lang.invoke.StringConcatException;
import java.util.ArrayList;
public class Vendedores {
    private String cc;
    private String nombre;
    private String codigoProducto;
    private ArrayList<String> codigosClientesAsignados;

    public Vendedores(String cc, String nombre, String codigoProducto){
        this.cc= cc;
        this. nombre = nombre;
        this.codigoProducto = codigoProducto;
        this.codigosClientesAsignados=new ArrayList<>();
    }
    public String getCc(){return cc;}
    public void setCc(String cc){this.cc = cc;}
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public String getCodigoProducto(){return codigoProducto;}
    public void setCodigoProducto(String codigoProducto){this.codigoProducto = codigoProducto;}
    public ArrayList<String> getCodigosClientesAsignados(){return codigosClientesAsignados;}

    public void agregarClientes(String codigoCliente){
        if (!codigosClientesAsignados.contains(codigoCliente)){
            codigosClientesAsignados.add(codigoCliente);
        }
    }
    public String toFileString(){
        String clienteStr= String.join(",", codigosClientesAsignados);
        return cc+","+nombre+";"+codigoProducto+";"+(clienteStr.isEmpty()?"NINGUNO":clienteStr);
    }

    @Override
    public String toString(){
        return "CC: "+cc+" | Nombre: "+nombre+" | Producto Asignado: "+ codigoProducto +
                " | Cliente Asignados: "+codigosClientesAsignados.size();
    }
}
