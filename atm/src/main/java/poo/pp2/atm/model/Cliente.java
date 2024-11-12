/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pp2.atm.model;


import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;


@Getter
@Setter

public class Cliente {
    // Atributos
     String nombreCompleto;
    private  String identificacion;
    String numeroTelefono;
    String correoElectronico;
    String categoria;
    private int maximoCuentas;
    private LocalDate fechaNacimiento;
    private  HashMap<String, Cuenta> cuentasCliente; // Almacena las cuentas del cliente
    protected static final HashMap<String, Cliente> mapaClientes = new HashMap<>(); // Mapa de todos los clientes

    // Constructor
    public Cliente(String nombreCompleto, String identificacion, String numeroTelefono, String correoElectronico, String categoria, int maximoCuentas, LocalDate fechaNacimiento) {
        this.nombreCompleto = nombreCompleto;
        this.identificacion = identificacion;
        this.numeroTelefono = numeroTelefono;
        this.correoElectronico = correoElectronico;
        this.categoria = categoria;
        this.maximoCuentas = maximoCuentas;
        this.fechaNacimiento = fechaNacimiento;
        this.cuentasCliente = new HashMap<>(); // Inicializa el mapa de cuentas del cliente
        System.out.println("Inicializando cuentasCliente para el cliente " + nombreCompleto);

        System.out.println(nombreCompleto);
        System.out.println(identificacion);
        System.out.println(numeroTelefono);
        System.out.println(correoElectronico);
        System.out.println(categoria);
        System.out.println(maximoCuentas);
        System.out.println(fechaNacimiento);




    }



    // Inicializa la lista con un cliente predeterminado para pruebas y depuración
    static {
        Cliente clienteDefault = new Cliente("ClientePorDefaul", "12345678", "12345678", "clientepordefault@gmail.com","individual", 0, LocalDate.now());
        mapaClientes.put(clienteDefault.getIdentificacion(), clienteDefault);
        System.out.println("Cliente predeterminado agregado al mapa.");
    }

    public Cliente() {

    }


    // Método para agregar un nuevo cliente al mapa global
    public static String agregarCliente(String nombreCompleto, String identificacion, String numeroTelefono, String correoElectronico, String categoria, int maximoCuentas, LocalDate fechaNacimiento) {
        if (mapaClientes.containsKey(identificacion)) {
            return "Error: El cliente con la identificación " + identificacion + " ya existe.";
        }
        Cliente nuevoCliente = new Cliente(nombreCompleto, identificacion, numeroTelefono, correoElectronico, categoria, maximoCuentas, fechaNacimiento );
        mapaClientes.put(identificacion, nuevoCliente);
        System.out.println("HOOLLAA");
        return "Cliente " + nombreCompleto + " agregado con éxito.";
    }

    // Método para buscar un cliente por identificación en el mapa global
    public static Cliente buscarClientePorIdentificacion(String identificacion) {
        return mapaClientes.get(identificacion); // Retorna el cliente si existe, o null si no
    }

    // Método para agregar una cuenta al cliente
    public void agregarCuenta(Cuenta cuenta) {
        if (cuenta != null && cuenta.getNumeroCuenta() != null) {
            System.out.println("Agregando la cuenta con número: " + cuenta.getNumeroCuenta() + " al cliente con identificación: " + this.identificacion);
            cuentasCliente.put(cuenta.getNumeroCuenta(), cuenta); // Relaciona la cuenta con el cliente
            System.out.println("Cuenta agregada correctamente. Cuentas actuales del cliente " + this.identificacion + ": " + cuentasCliente.keySet());
        } else {
            System.out.println("Error: La cuenta o el número de cuenta es nulo.");
        }
    }

    // Método para buscar una cuenta del cliente por número de cuenta
    public Cuenta buscarCuentaPorNumero(String numeroCuenta) {
        return cuentasCliente.get(numeroCuenta); // Busca y retorna la cuenta usando el número de cuenta
    }



    // Método para obtener una lista de las cuentas del cliente y sus saldos actuales
    public HashMap<String, Cuenta> obtenerCuentasYSaldo() {
        return cuentasCliente;
    }

    // Método para cambiar el número de teléfono del cliente
    public void cambiarNumeroTelefono(String nuevoNumeroTelefono) {
        this.numeroTelefono = nuevoNumeroTelefono;
    }

    // Método para cambiar el correo electrónico del cliente
    public void cambiarCorreoElectronico(String nuevoCorreoElectronico) {
        this.correoElectronico = nuevoCorreoElectronico;
    }

    // Getters para los atributos del cliente
    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public String getIdentificacion() {
        return this.identificacion;
    }

    public String getNumeroTelefono() {
        return this.numeroTelefono;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getMaximoCuentas() {
        return maximoCuentas;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public String obtenerInformacionCliente() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return """
               Se ha creado un nuevo cliente en el sistema, los datos del nuevo Cliente F\u00edsico son:
               C\u00f3digo del cliente: """ + identificacion + "\n" +
               "Nombre completo: " + nombreCompleto + "\n" +
               "Número de teléfono: " + numeroTelefono + "\n" +
               "Dirección de correo electrónico: " + correoElectronico + "\n" +
               "Categoría: " + categoria + "\n" +
               "Identificación: " + identificacion + "\n" +
               "Cantidad máxima de cuentas que desea crear: " + maximoCuentas + "\n" +
               "Fecha de nacimiento: " + sdf.format(fechaNacimiento);
    }
    // Representación en String del cliente para facilitar la depuración
    @Override
    public String toString() {
        return "Cliente: " + nombreCompleto + ", ID: " + identificacion +
               ", Teléfono: " + numeroTelefono + ", Correo: " + correoElectronico;
    }

    public void setCorreoElectronico(String nuevoCorreoElectronico) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setNumeroTelefono(String nuevoNumeroTelefono) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
