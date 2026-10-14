package org.example;

import java.util.*;
import java.io.*;

public class Gestor {

    // Empleados

    public static void guardarEmpleados(ArrayList<Empleados> lista) {
        try {
            PrintWriter writer = new PrintWriter("empleados.txt");
            for (int i=0; i<lista.size(); i++ ){
                Empleados emp = lista.get(i);
                writer.println(emp.getCc()+";"+ emp.getNombre()+";"+emp.getDepartamento()+";"+emp.getSalario());
            }
            writer.close();

        }catch (Exception e){
            System.out.println("Error al guardar empleados.");
        }
    }
    public static ArrayList<Empleados> cargarEmpleados(){
        ArrayList<Empleados> lista = new ArrayList<>();
        try {
            File archivo = new File("empleados.txt");
            if (archivo.exists()){
                Scanner scr = new Scanner(archivo);
                while (scr.hasNextLine()){
                    String linea=scr.nextLine();
                    if (!linea.equals("")){
                        String[] datos = linea.split(";");
                        String cc=datos[0];
                        String nombre=datos[1];
                        String departamento=datos[2];
                        double salario = Double.parseDouble(datos[3]);

                        Empleados emp = new Empleados(cc, nombre, departamento, salario);
                        lista.add(emp);
                    }
                }
                scr.close();
            }
        }catch (Exception e){
            System.out.println("Error al registrar empleados.");
        }
        return lista;
    }

    // Clientes

    public static void guardarClientes(ArrayList<Clientes> lista){
        try {
            PrintWriter writer = new PrintWriter("clientes.txt");
            for (int i=0; i < lista.size(); i++){
                Clientes cli=lista.get(i);
                writer.println(cli.getCodigoCc()+";"+cli.getNombre()+";"+cli.getDireccion()+";"+cli.getTelefono());

            }
            writer.close();
        } catch (Exception e){
            System.out.println("Error al guaradar clientes.");
        }
    }
    public static ArrayList<Clientes> cargarClientes(){
        ArrayList<Clientes> lista= new ArrayList<>();
        try {
            File archivo = new File("clientes.txt");
            if (archivo.exists()){
                Scanner scr = new Scanner(archivo);
                while (scr.hasNextLine()){
                    String linea = scr.nextLine();
                    if (!linea.equals("")){
                        String[] datos = linea.split((";"));
                        String cc = datos[0];
                        String nombre = datos[1];
                        String direccion = datos[2];
                        String telefono = datos[3];

                        Clientes cli =  new Clientes(cc, nombre, direccion, telefono);
                        lista.add(cli);
                    }
                }
                scr.close();
            }
        }catch (Exception e){
            System.out.println("Error al cargar clientes.");
        }
        return lista;
    }

    // Productos

    public static void guardarProductos(ArrayList<Productos> lista){
        try {
            PrintWriter writer = new PrintWriter("productos.txt");
            for (int i = 0; i < lista.size(); i++) {
                Productos prod = lista.get(i);
                writer.println(prod.getCodigo() + ";" + prod.getDescripcion() + ";" + prod.getValor());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error al guardar productos.");
        }
    }

    public static ArrayList<Productos> cargarProductos() {
        ArrayList<Productos> lista = new ArrayList<>();
        try {
            File archivo = new File("productos.txt");
            if (archivo.exists()) {
                Scanner lector = new Scanner(archivo);
                while (lector.hasNextLine()) {
                    String linea = lector.nextLine();
                    if (!linea.equals("")) {
                        String[] datos = linea.split(";");
                        String codigo = datos[0];
                        String descripcion = datos[1];
                        double valor = Double.parseDouble(datos[2]);

                        Productos prod = new Productos(codigo, descripcion, valor);
                        lista.add(prod);
                    }
                }
                lector.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cargar productos.");
        }
        return lista;
    }

    //  VENDEDORES
    public static void guardarVendedores(ArrayList<Vendedores> lista) {
        try {
            PrintWriter writer = new PrintWriter("vendedores.txt");
            for (int i = 0; i < lista.size(); i++) {
                Vendedores ven = lista.get(i);

                // Armar el texto de clientes separados por coma
                String clientesTexto = "";
                ArrayList<String> listaClientes = ven.getCodigosClientesAsignados();
                for (int j = 0; j < listaClientes.size(); j++) {
                    clientesTexto = clientesTexto + listaClientes.get(j);
                    if (j < listaClientes.size() - 1) {
                        clientesTexto = clientesTexto + ",";
                    }
                }
                if (clientesTexto.equals("")) {
                    clientesTexto = "NINGUNO";
                }

                writer.println(ven.getCc() + ";" + ven.getNombre() + ";" + ven.getCodigoProducto() + ";" + clientesTexto);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error al guardar vendedores.");
        }
    }

    public static ArrayList<Vendedores> cargarVendedores() {
        ArrayList<Vendedores> lista = new ArrayList<>();
        try {
            File archivo = new File("vendedores.txt");
            if (archivo.exists()) {
                Scanner lector = new Scanner(archivo);
                while (lector.hasNextLine()) {
                    String linea = lector.nextLine();
                    if (!linea.equals("")) {
                        String[] datos = linea.split(";");
                        String cc = datos[0];
                        String nombre = datos[1];
                        String codProd = datos[2];

                        Vendedores ven = new Vendedores(cc, nombre, codProd);

                        if (datos.length > 3 && !datos[3].equals("NINGUNO")) {
                            String[] clientesArray = datos[3].split(",");
                            for (int k = 0; k < clientesArray.length; k++) {
                                ven.agregarClientes(clientesArray[k]);
                            }
                        }
                        lista.add(ven);
                    }
                }
                lector.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cargar vendedores.");
        }
        return lista;
    }
}

