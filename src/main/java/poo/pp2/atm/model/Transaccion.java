/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pp2.atm.model;


import java.util.Date;

public class Transaccion {
    
    // Atributos de la transacción
    private String tipoTransaccion;
    protected double montoTransaccion;
    protected Date fechaTransaccion;
    protected boolean cobroComision;
    private static final double tasaComision = 0.02; // Comisión del 2%
    private static final int cantTransaccionGratis = 5; // Las primeras 5 transacciones son gratuitas
    private static int cantTransacciones = 0; // Contador de transacciones
    
    // Atributos relacionados con la transacción
    private double comisionPorRetiro;
    private double comisionPorDeposito;
    private double totalComisiones;
    private double historialMontoDepositado;
    private double historialMontoRetirado;

    // Constructor que inicializa una transacción con tipo, monto y si aplica comisión
    public Transaccion(String tipoTransaccion, double montoTransaccion, boolean cobroComision) {
        this.tipoTransaccion = tipoTransaccion;
        this.montoTransaccion = montoTransaccion;
        this.fechaTransaccion = new Date(); // Se inicializa con la fecha actual
        this.cobroComision = cobroComision; // Indica si se aplica comisión
    }

    // Métodos getter para acceder a los atributos de la transacción
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public double getMontoTransaccion() {
        return montoTransaccion;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public boolean isCobroComision() {
        return cobroComision;
    }

    // Método para generar o no cargo por comisión basado en el número de transacciones
    private boolean generoOnoCargoComision() {
        cantTransacciones++; // Se incrementa el contador de transacciones
        return cantTransacciones > cantTransaccionGratis; // Si es mayor al número de transacciones gratuitas, cobra comisión
    }

    // Método para realizar una transacción (puede ser depósito o retiro)
    public void realizarTransaccion(String tipo, double monto) {
        this.tipoTransaccion = tipo;
        
        if (tipo.equalsIgnoreCase("Depósito")) {
            historialMontoDepositado += monto;
            if (generoOnoCargoComision()) {
                comisionPorDeposito = monto * tasaComision; // Se cobra comisión
            } else {
                comisionPorDeposito = 0; // No se cobra comisión
            }
        } else if (tipo.equalsIgnoreCase("Retiro")) {
            historialMontoRetirado += monto;
            if (generoOnoCargoComision()) {
                comisionPorRetiro = monto * tasaComision; // Se cobra comisión
            } else {
                comisionPorRetiro = 0; // No se cobra comisión
            }
        }

        // Calcular comisiones totales
        calcularComisiones();
    }

    // Método para calcular las comisiones totales (retiro + depósito)
    private void calcularComisiones() {
        totalComisiones = comisionPorRetiro + comisionPorDeposito;
    }

    // Método para consultar transacción (puedes agregar más detalles aquí según sea necesario)
    public void consultarTransaccion() {
        System.out.println(this.toString()); // Imprimir detalles de la transacción
    }

    // Método toString para mostrar la información de la transacción
    @Override
    public String toString() {
        return "Transacción{" +
                "tipoTransaccion='" + tipoTransaccion + '\'' +
                ", montoTransaccion=" + montoTransaccion +
                ", fechaTransaccion=" + fechaTransaccion +
                ", cobroComision=" + cobroComision +
                ", comisionPorRetiro=" + comisionPorRetiro +
                ", comisionPorDeposito=" + comisionPorDeposito +
                ", totalComisiones=" + totalComisiones +
                ", historialMontoDepositado=" + historialMontoDepositado +
                ", historialMontoRetirado=" + historialMontoRetirado +
                '}';
    }
}

