package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Listas principales en memoria
    static ArrayList<Empleados> listaEmpleados = new ArrayList<>();
    static ArrayList<Clientes> listaClientes = new ArrayList<>();
    static ArrayList<Productos> listaProductos = new ArrayList<>();
    static ArrayList<Vendedores> listaVendedores = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //  Cargar datos desde los archivos .txt al iniciar el programa
        listaEmpleados = Gestor.cargarEmpleados();
        listaClientes = Gestor.cargarClientes();
        listaProductos = Gestor.cargarProductos();
        listaVendedores = Gestor.cargarVendedores();

        int opcion = 0;

        do {
            System.out.println("\n==========================================");
            System.out.println("     SISTEMA DE GESTIÓN COMERCIAL        ");
            System.out.println("==========================================");
            System.out.println("1. Gestión de empleados");
            System.out.println("2. Gestión de clientes");
            System.out.println("3. Gestión de vendedores");
            System.out.println("4. Gestión de productos");
            System.out.println("5. Consultas");
            System.out.println("6. Informes");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    menuEmpleados();
                    break;
                case 2:
                    menuClientes();
                    break;
                case 3:
                    menuVendedores();
                    break;
                case 4:
                    menuProductos();
                    break;
                case 5:
                    menuConsultas();
                    break;
                case 6:
                    System.out.println("En desarrollo...");
                    break;
                case 7:
                    System.out.println("¡Gracias por usar el sistema! Guardando y saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 7);
    }


    // GESTIÓN DE EMPLEADOS

    public static void menuEmpleados() {
        int op = 0;
        do {
            System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Consultar empleado");
            System.out.println("3. Actualizar empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Listar empleados");
            System.out.println("6. Regresar");
            System.out.print("Seleccione una opción: ");

            try {
                op = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                op = 0;
            }

            switch (op) {
                case 1: registrarEmpleado(); break;
                case 2: consultarEmpleado(); break;
                case 3: actualizarEmpleado(); break;
                case 4: eliminarEmpleado(); break;
                case 5: listarEmpleados(); break;
                case 6: System.out.println("Regresando al menú principal..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (op != 6);
    }

    // =======================================================
    // MÓDULO 5: CONSULTAS Y CONSULTAS COMBINADAS
    // =======================================================
    public static void menuConsultas() {
        int op = 0;
        do {
            System.out.println("\n--- MÓDULO DE CONSULTAS ---");
            System.out.println("1. Consulta Combinada 1 (Vendedor -> Producto y Clientes)");
            System.out.println("2. Consulta Combinada 2 (Producto -> Vendedor)");
            System.out.println("3. Consultar Empleados por Departamento");
            System.out.println("4. Consultar Empleados por Salario Mayor a X");
            System.out.println("5. Consultar Productos por Rango de Valor");
            System.out.println("6. Regresar");
            System.out.print("Seleccione una opción: ");

            try {
                op = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                op = 0;
            }

            switch (op) {
                case 1: consultaCombinada1(); break;
                case 2: consultaCombinada2(); break;
                case 3: consultarEmpleadosPorDepartamento(); break;
                case 4: consultarEmpleadosPorSalario(); break;
                case 5: consultarProductosPorRangoValor(); break;
                case 6: System.out.println("Regresando al menú principal..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (op != 6);
    }

    public static void consultaCombinada1() {
        System.out.print("Ingresar la CC del vendedor: ");
        String cc = scanner.nextLine().trim();

        Vendedores ven = null;
        for (int i = 0; i < listaVendedores.size(); i++) {
            if (listaVendedores.get(i).getCc().equalsIgnoreCase(cc)) {
                ven = listaVendedores.get(i);
                break;
            }
        }

        if (ven == null) {
            System.out.println("Vendedor no encontrado.");
            return;
        }

        System.out.println("\nVendedor: " + ven.getNombre());
        System.out.println("CC: " + ven.getCc());

        // Buscar el producto que representa
        String desProd = "No encontrado";
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigo().equalsIgnoreCase(ven.getCodigoProducto())) {
                desProd = listaProductos.get(i).getDescripcion();
                break;
            }
        }
        System.out.println("Producto que representa: " + desProd);

        // Buscar los clientes asignados
        System.out.println("Clientes asignados:");
        ArrayList<String> codsClientes = ven.getCodigosClientesAsignados();
        int contador = 0;

        for (int i = 0; i < codsClientes.size(); i++) {
            String codC = codsClientes.get(i);
            for (int j = 0; j < listaClientes.size(); j++) {
                if (listaClientes.get(j).getCodigoCc().equalsIgnoreCase(codC)) {
                    contador++;
                    System.out.println("  " + contador + ". " + listaClientes.get(j).getNombre() + " (CC/Cód: " + codC + ")");
                }
            }
        }
        if (contador == 0) {
            System.out.println("  (Ningún cliente asignado)");
        }
        System.out.println("Total de clientes: " + contador);
    }

    public static void consultaCombinada2() {
        System.out.print("Ingresar el código del producto: ");
        String cod = scanner.nextLine().trim();

        Productos prod = null;
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigo().equalsIgnoreCase(cod)) {
                prod = listaProductos.get(i);
                break;
            }
        }

        if (prod == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("\nProducto: " + prod.getCodigo());
        System.out.println("Descripción: " + prod.getDescripcion());
        System.out.println("Valor: $" + prod.getValor());

        // Buscar qué vendedor representa este producto
        String vendedorNombre = "Ninguno asignado";
        for (int i = 0; i < listaVendedores.size(); i++) {
            if (listaVendedores.get(i).getCodigoProducto().equalsIgnoreCase(cod)) {
                vendedorNombre = listaVendedores.get(i).getNombre() + " (CC: " + listaVendedores.get(i).getCc() + ")";
                break;
            }
        }
        System.out.println("Vendedor que lo representa: " + vendedorNombre);
    }

    public static void consultarEmpleadosPorDepartamento() {
        System.out.print("Ingrese el departamento a buscar: ");
        String depto = scanner.nextLine().trim();
        int hallados = 0;

        for (int i = 0; i < listaEmpleados.size(); i++) {
            Empleados emp = listaEmpleados.get(i);
            if (emp.getDepartamento().equalsIgnoreCase(depto)) {
                System.out.println(emp.toString());
                hallados++;
            }
        }
        if (hallados == 0) System.out.println("No hay empleados en ese departamento.");
    }

    public static void consultarEmpleadosPorSalario() {
        System.out.print("Ingrese el salario mínimo a filtrar: ");
        try {
            double min = Double.parseDouble(scanner.nextLine());
            int hallados = 0;
            for (int i = 0; i < listaEmpleados.size(); i++) {
                Empleados emp = listaEmpleados.get(i);
                if (emp.getSalario() > min) {
                    System.out.println(emp.toString());
                    hallados++;
                }
            }
            if (hallados == 0) System.out.println("No hay empleados con salario superior a $" + min);
        } catch (Exception e) {
            System.out.println("Valor numérico inválido.");
        }
    }

    public static void consultarProductosPorRangoValor() {
        try {
            System.out.print("Ingrese el valor mínimo: ");
            double min = Double.parseDouble(scanner.nextLine());
            System.out.print("Ingrese el valor máximo: ");
            double max = Double.parseDouble(scanner.nextLine());

            int hallados = 0;
            for (int i = 0; i < listaProductos.size(); i++) {
                Productos prod = listaProductos.get(i);
                if (prod.getValor() >= min && prod.getValor() <= max) {
                    System.out.println(prod.toString());
                    hallados++;
                }
            }
            if (hallados == 0) System.out.println("No se encontraron productos en ese rango de valor.");
        } catch (Exception e) {
            System.out.println("Rango inválido.");
        }
    }

    //GESTIÓN DE VENDEDORES Y MATRIZ DE RELACIONES

    public static void menuVendedores() {
        int op = 0;
        do {
            System.out.println("\n--- GESTIÓN DE VENDEDORES ---");
            System.out.println("1. Registrar vendedor");
            System.out.println("2. Asignar cliente a vendedor");
            System.out.println("3. Consultar vendedor");
            System.out.println("4. Actualizar vendedor");
            System.out.println("5. Eliminar vendedor");
            System.out.println("6. Listar vendedores");
            System.out.println("7. Ver Matriz de Relación (Vendedores vs Clientes)");
            System.out.println("8. Regresar");
            System.out.print("Seleccione una opción: ");

            try {
                op = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                op = 0;
            }

            switch (op) {
                case 1: registrarVendedor(); break;
                case 2: asignarClienteAVendedor(); break;
                case 3: consultarVendedor(); break;
                case 4: actualizarVendedor(); break;
                case 5: eliminarVendedor(); break;
                case 6: listarVendedores(); break;
                case 7: mostrarMatrizVendedorCliente(); break;
                case 8: System.out.println("Regresando al menú principal..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (op != 8);
    }

    public static void registrarVendedor() {
        System.out.print("Ingrese la CC del vendedor: ");
        String cc = scanner.nextLine().trim();

        if (cc.isEmpty()) {
            System.out.println("Error: La CC no puede estar vacía.");
            return;
        }

        // Validación: No permitir CC repetida
        for (int i = 0; i < listaVendedores.size(); i++) {
            if (listaVendedores.get(i).getCc().equalsIgnoreCase(cc)) {
                System.out.println("Error: Ya existe un vendedor registrado con esa CC.");
                return;
            }
        }

        System.out.print("Ingrese el nombre del vendedor: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Ingrese el código del producto que representa: ");
        String codProd = scanner.nextLine().trim();

        // Validación: El producto DEBE existir previamente en el sistema
        boolean existeProducto = false;
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigo().equalsIgnoreCase(codProd)) {
                existeProducto = true;
                break;
            }
        }

        if (!existeProducto) {
            System.out.println("Error: El producto con código '" + codProd + "' no existe. Debe registrarlo primero.");
            return;
        }

        Vendedores nuevo = new Vendedores(cc, nombre, codProd);
        listaVendedores.add(nuevo);
        Gestor.guardarVendedores(listaVendedores);
        System.out.println("¡Vendedor registrado con éxito!");
    }

    public static void asignarClienteAVendedor() {
        if (listaVendedores.isEmpty() || listaClientes.isEmpty()) {
            System.out.println("Debe tener al menos un vendedor y un cliente registrados.");
            return;
        }

        System.out.print("Ingrese la CC del vendedor: ");
        String ccVendedor = scanner.nextLine().trim();

        Vendedores venEncontrado = null;
        for (int i = 0; i < listaVendedores.size(); i++) {
            if (listaVendedores.get(i).getCc().equalsIgnoreCase(ccVendedor)) {
                venEncontrado = listaVendedores.get(i);
                break;
            }
        }

        if (venEncontrado == null) {
            System.out.println("Error: Vendedor no encontrado.");
            return;
        }

        System.out.print("Ingrese el código/CC del cliente a asignarle: ");
        String ccCliente = scanner.nextLine().trim();

        // Validación: El cliente DEBE existir
        boolean existeCliente = false;
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getCodigoCc().equalsIgnoreCase(ccCliente)) {
                existeCliente = true;
                break;
            }
        }

        if (!existeCliente) {
            System.out.println("Error: El cliente no existe en la base de datos.");
            return;
        }

        venEncontrado.agregarClientes(ccCliente);
        Gestor.guardarVendedores(listaVendedores);
        System.out.println("¡Cliente asignado exitosamente al vendedor!");
    }

    public static void consultarVendedor() {
        System.out.print("Ingrese la CC del vendedor a buscar: ");
        String cc = scanner.nextLine().trim();

        for (int i = 0; i < listaVendedores.size(); i++) {
            Vendedores ven = listaVendedores.get(i);
            if (ven.getCc().equalsIgnoreCase(cc)) {
                System.out.println("\n--- DATOS DEL VENDEDOR ---");
                System.out.println(ven.toString());
                System.out.println("Clientes asignados (CCs): " + ven.getCodigosClientesAsignados());
                return;
            }
        }
        System.out.println("Vendedor no encontrado.");
    }

    public static void actualizarVendedor() {
        System.out.print("Ingrese la CC del vendedor a actualizar: ");
        String cc = scanner.nextLine().trim();

        for (int i = 0; i < listaVendedores.size(); i++) {
            Vendedores ven = listaVendedores.get(i);
            if (ven.getCc().equalsIgnoreCase(cc)) {
                System.out.print("Ingrese el nuevo nombre (" + ven.getNombre() + "): ");
                String nom = scanner.nextLine().trim();
                if (!nom.isEmpty()) ven.setNombre(nom);

                System.out.print("Ingrese el nuevo código de producto (" + ven.getCodigoProducto() + "): ");
                String codP = scanner.nextLine().trim();
                if (!codP.isEmpty()) {
                    boolean existeP = false;
                    for (int j = 0; j < listaProductos.size(); j++) {
                        if (listaProductos.get(j).getCodigo().equalsIgnoreCase(codP)) {
                            existeP = true;
                            break;
                        }
                    }
                    if (existeP) {
                        ven.setCodigoProducto(codP);
                    } else {
                        System.out.println("Producto inválido. Se conserva el código anterior.");
                    }
                }

                Gestor.guardarVendedores(listaVendedores);
                System.out.println("¡Vendedor actualizado correctamente!");
                return;
            }
        }
        System.out.println("Vendedor no encontrado.");
    }

    public static void eliminarVendedor() {
        System.out.print("Ingrese la CC del vendedor a eliminar: ");
        String cc = scanner.nextLine().trim();

        for (int i = 0; i < listaVendedores.size(); i++) {
            if (listaVendedores.get(i).getCc().equalsIgnoreCase(cc)) {
                listaVendedores.remove(i);
                Gestor.guardarVendedores(listaVendedores);
                System.out.println("¡Vendedor eliminado con éxito!");
                return;
            }
        }
        System.out.println("Vendedor no encontrado.");
    }

    public static void listarVendedores() {
        if (listaVendedores.isEmpty()) {
            System.out.println("No hay vendedores registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE VENDEDORES ---");
        for (int i = 0; i < listaVendedores.size(); i++) {
            System.out.println((i + 1) + ". " + listaVendedores.get(i).toString());
        }
    }

    // USO DE MATRICES: RELACIÓN VENDEDORES VS CLIENTES

    public static void mostrarMatrizVendedorCliente() {
        if (listaVendedores.isEmpty() || listaClientes.isEmpty()) {
            System.out.println("Se requieren vendedores y clientes registrados para generar la matriz.");
            return;
        }

        // Crear la matriz de enteros: Filas (Vendedores) x Columnas (Clientes)
        int[][] matriz = new int[listaVendedores.size()][listaClientes.size()];

        // Llenar la matriz con 1 si existe relación, o 0 si no existe
        for (int i = 0; i < listaVendedores.size(); i++) {
            Vendedores ven = listaVendedores.get(i);
            ArrayList<String> clientesDelVen = ven.getCodigosClientesAsignados();

            for (int j = 0; j < listaClientes.size(); j++) {
                Clientes cli = listaClientes.get(j);
                if (clientesDelVen.contains(cli.getCodigoCc())) {
                    matriz[i][j] = 1; // Existe relación
                } else {
                    matriz[i][j] = 0; // No existe relación
                }
            }
        }

        // Imprimir la Matriz en Consola
        System.out.println("\n========================================================");
        System.out.println("       MATRIZ DE RELACIÓN (VENDEDORES VS CLIENTES)");
        System.out.println("========================================================");

        // Imprimir encabezados de clientes
        System.out.print("VENDEDOR / CLIENTE\t");
        for (int j = 0; j < listaClientes.size(); j++) {
            System.out.print("[" + listaClientes.get(j).getCodigoCc() + "]\t");
        }
        System.out.println();

        // Imprimir filas de vendedores con sus valores (0 o 1)
        for (int i = 0; i < listaVendedores.size(); i++) {
            System.out.print(listaVendedores.get(i).getNombre() + "\t\t");
            for (int j = 0; j < listaClientes.size(); j++) {
                System.out.print("  " + matriz[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("========================================================\n");
    }

    //  GESTIÓN DE CLIENTES

    public static void menuClientes() {
        int op = 0;
        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Consultar cliente");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Listar clientes");
            System.out.println("6. Regresar");
            System.out.print("Seleccione una opción: ");

            try {
                op = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                op = 0;
            }

            switch (op) {
                case 1: registrarCliente(); break;
                case 2: consultarCliente(); break;
                case 3: actualizarCliente(); break;
                case 4: eliminarCliente(); break;
                case 5: listarClientes(); break;
                case 6: System.out.println("Regresando al menú principal..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (op != 6);
    }

    public static void registrarCliente() {
        System.out.print("Ingrese el código o CC del cliente: ");
        String cc = scanner.nextLine().trim();

        if (cc.isEmpty()) {
            System.out.println("Error: El código/CC no puede estar vacío.");
            return;
        }

        // Validación: No permitir CC repetida
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getCodigoCc().equalsIgnoreCase(cc)) {
                System.out.println("Error: Ya existe un cliente con ese código/CC.");
                return;
            }
        }

        System.out.print("Ingrese el nombre completo: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Ingrese la dirección: ");
        String direccion = scanner.nextLine().trim();
        if (direccion.isEmpty()) {
            System.out.println("Error: La dirección no puede estar vacía.");
            return;
        }

        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine().trim();
        if (telefono.isEmpty()) {
            System.out.println("Error: El teléfono no puede estar vacío.");
            return;
        }

        Clientes nuevo = new Clientes(cc, nombre, direccion, telefono);
        listaClientes.add(nuevo);
        Gestor.guardarClientes(listaClientes);
        System.out.println("¡Cliente registrado con éxito!");
    }

    public static void consultarCliente() {
        System.out.print("Ingrese el código o CC del cliente a buscar: ");
        String cc = scanner.nextLine().trim();

        for (int i = 0; i < listaClientes.size(); i++) {
            Clientes cli = listaClientes.get(i);
            if (cli.getCodigoCc().equalsIgnoreCase(cc)) {
                System.out.println("\n--- DATOS ENCONTRADOS ---");
                System.out.println(cli.toString());
                return;
            }
        }
        System.out.println("No se encontró ningún cliente con ese código/CC.");
    }

    public static void actualizarCliente() {
        System.out.print("Ingrese el código/CC del cliente a actualizar: ");
        String cc = scanner.nextLine().trim();

        for (int i = 0; i < listaClientes.size(); i++) {
            Clientes cli = listaClientes.get(i);
            if (cli.getCodigoCc().equalsIgnoreCase(cc)) {
                System.out.print("Ingrese el nuevo nombre (" + cli.getNombre() + "): ");
                String nom = scanner.nextLine().trim();
                if (!nom.isEmpty()) cli.setNombre(nom);

                System.out.print("Ingrese la nueva dirección (" + cli.getDireccion() + "): ");
                String dir = scanner.nextLine().trim();
                if (!dir.isEmpty()) cli.setDireccion(dir);

                System.out.print("Ingrese el nuevo teléfono (" + cli.getTelefono() + "): ");
                String tel = scanner.nextLine().trim();
                if (!tel.isEmpty()) cli.setTelefono(tel);

                Gestor.guardarClientes(listaClientes);
                System.out.println("¡Cliente actualizado correctamente!");
                return;
            }
        }
        System.out.println("Cliente no encontrado.");
    }

    public static void eliminarCliente() {
        System.out.print("Ingrese el código/CC del cliente a eliminar: ");
        String cc = scanner.nextLine().trim();

        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getCodigoCc().equalsIgnoreCase(cc)) {
                listaClientes.remove(i);
                Gestor.guardarClientes(listaClientes);
                System.out.println("¡Cliente eliminado con éxito!");
                return;
            }
        }
        System.out.println("Cliente no encontrado.");
    }

    public static void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE CLIENTES ---");
        for (int i = 0; i < listaClientes.size(); i++) {
            System.out.println((i + 1) + ". " + listaClientes.get(i).toString());
        }
    }


    //GESTIÓN DE PRODUCTOS

    public static void menuProductos() {
        int op = 0;
        do {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Registrar producto");
            System.out.println("2. Consultar producto");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Listar productos");
            System.out.println("6. Regresar");
            System.out.print("Seleccione una opción: ");

            try {
                op = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                op = 0;
            }

            switch (op) {
                case 1: registrarProducto(); break;
                case 2: consultarProducto(); break;
                case 3: actualizarProducto(); break;
                case 4: eliminarProducto(); break;
                case 5: listarProductos(); break;
                case 6: System.out.println("Regresando al menú principal..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (op != 6);
    }

    public static void registrarProducto() {
        System.out.print("Ingrese el código del producto: ");
        String cod = scanner.nextLine().trim();

        if (cod.isEmpty()) {
            System.out.println("Error: El código no puede estar vacío.");
            return;
        }

        // Validación: No permitir código de producto repetido
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigo().equalsIgnoreCase(cod)) {
                System.out.println("Error: Ya existe un producto con ese código.");
                return;
            }
        }

        System.out.print("Ingrese la descripción del producto: ");
        String des = scanner.nextLine().trim();
        if (des.isEmpty()) {
            System.out.println("Error: La descripción no puede estar vacía.");
            return;
        }

        System.out.print("Ingrese el valor del producto: ");
        double valor = 0;
        try {
            valor = Double.parseDouble(scanner.nextLine());
            if (valor <= 0) {
                System.out.println("Error: El valor del producto debe ser mayor que cero.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Error: Valor numérico inválido.");
            return;
        }

        Productos nuevo = new Productos(cod, des, valor);
        listaProductos.add(nuevo);
        Gestor.guardarProductos(listaProductos);
        System.out.println("¡Producto registrado con éxito!");
    }

    public static void consultarProducto() {
        System.out.print("Ingrese el código del producto a buscar: ");
        String cod = scanner.nextLine().trim();

        for (int i = 0; i < listaProductos.size(); i++) {
            Productos prod = listaProductos.get(i);
            if (prod.getCodigo().equalsIgnoreCase(cod)) {
                System.out.println("\n--- DATOS ENCONTRADOS ---");
                System.out.println(prod.toString());
                return;
            }
        }
        System.out.println("No se encontró ningún producto con ese código.");
    }

    public static void actualizarProducto() {
        System.out.print("Ingrese el código del producto a actualizar: ");
        String cod = scanner.nextLine().trim();

        for (int i = 0; i < listaProductos.size(); i++) {
            Productos prod = listaProductos.get(i);
            if (prod.getCodigo().equalsIgnoreCase(cod)) {
                System.out.print("Ingrese la nueva descripción (" + prod.getDescripcion() + "): ");
                String des = scanner.nextLine().trim();
                if (!des.isEmpty()) prod.setDescripcion(des);

                System.out.print("Ingrese el nuevo valor (" + prod.getValor() + "): ");
                String valStr = scanner.nextLine().trim();
                if (!valStr.isEmpty()) {
                    try {
                        double val = Double.parseDouble(valStr);
                        if (val > 0) prod.setValor(val);
                    } catch (Exception e) {
                        System.out.println("Valor no válido, se conserva el anterior.");
                    }
                }

                Gestor.guardarProductos(listaProductos);
                System.out.println("¡Producto actualizado correctamente!");
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    public static void eliminarProducto() {
        System.out.print("Ingrese el código del producto a eliminar: ");
        String cod = scanner.nextLine().trim();

        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigo().equalsIgnoreCase(cod)) {
                listaProductos.remove(i);
                Gestor.guardarProductos(listaProductos);
                System.out.println("¡Producto eliminado con éxito!");
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    public static void listarProductos() {
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        for (int i = 0; i < listaProductos.size(); i++) {
            System.out.println((i + 1) + ". " + listaProductos.get(i).toString());
        }
    }

    public static void registrarEmpleado() {
        System.out.print("Ingrese la CC del empleado: ");
        String cc = scanner.nextLine().trim();

        if (cc.isEmpty()) {
            System.out.println("Error: La CC no puede estar vacía.");
            return;
        }

        // Validación: No permitir CC repetida
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getCc().equalsIgnoreCase(cc)) {
                System.out.println("Error: Ya existe un empleado registrado con esa CC.");
                return;
            }
        }

        System.out.print("Ingrese el nombre completo: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Ingrese el departamento: ");
        String depto = scanner.nextLine().trim();
        if (depto.isEmpty()) {
            System.out.println("Error: El departamento no puede estar vacío.");
            return;
        }

        System.out.print("Ingrese el salario: ");
        double salario = 0;
        try {
            salario = Double.parseDouble(scanner.nextLine());
            if (salario <= 0) {
                System.out.println("Error: El salario debe ser mayor que cero.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Error: Salario inválido.");
            return;
        }

        // Crear objeto y guardar en lista y archivo
        Empleados nuevo = new Empleados(cc, nombre, depto, salario);
        listaEmpleados.add(nuevo);
        Gestor.guardarEmpleados(listaEmpleados);
        System.out.println("¡Empleado registrado con éxito!");
    }

    public static void consultarEmpleado() {
        System.out.print("Ingrese la CC a buscar: ");
        String cc = scanner.nextLine().trim();

        for (int i = 0; i < listaEmpleados.size(); i++) {
            Empleados emp = listaEmpleados.get(i);
            if (emp.getCc().equalsIgnoreCase(cc)) {
                System.out.println("\n--- DATOS ENCONTRADOS ---");
                System.out.println(emp.toString());
                return;
            }
        }
        System.out.println("No se encontró ningún empleado con la CC proporcionada.");
    }

    public static void actualizarEmpleado() {
        System.out.print("Ingrese la CC del empleado a actualizar: ");
        String cc = scanner.nextLine().trim();

        for (int i = 0; i < listaEmpleados.size(); i++) {
            Empleados emp = listaEmpleados.get(i);
            if (emp.getCc().equalsIgnoreCase(cc)) {
                System.out.print("Ingrese el nuevo nombre (" + emp.getNombre() + "): ");
                String nom = scanner.nextLine().trim();
                if (!nom.isEmpty()) emp.setNombre(nom);

                System.out.print("Ingrese el nuevo departamento (" + emp.getDepartamento() + "): ");
                String dep = scanner.nextLine().trim();
                if (!dep.isEmpty()) emp.setDepartamento(dep);

                System.out.print("Ingrese el nuevo salario (" + emp.getSalario() + "): ");
                String salStr = scanner.nextLine().trim();
                if (!salStr.isEmpty()) {
                    try {
                        double sal = Double.parseDouble(salStr);
                        if (sal > 0) emp.setSalario(sal);
                    } catch (Exception e) {
                        System.out.println("Salario no válido, se conserva el anterior.");
                    }
                }

                Gestor.guardarEmpleados(listaEmpleados);
                System.out.println("¡Empleado actualizado correctamente!");
                return;
            }
        }
        System.out.println("Empleado no encontrado.");
    }

    public static void eliminarEmpleado() {
        System.out.print("Ingrese la CC del empleado a eliminar: ");
        String cc = scanner.nextLine().trim();

        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getCc().equalsIgnoreCase(cc)) {
                listaEmpleados.remove(i);
                Gestor.guardarEmpleados(listaEmpleados);
                System.out.println("¡Empleado eliminado con éxito!");
                return;
            }
        }
        System.out.println("Empleado no encontrado.");
    }

    public static void listarEmpleados() {
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE EMPLEADOS ---");
        for (int i = 0; i < listaEmpleados.size(); i++) {
            System.out.println((i + 1) + ". " + listaEmpleados.get(i).toString());
        }


    }
}