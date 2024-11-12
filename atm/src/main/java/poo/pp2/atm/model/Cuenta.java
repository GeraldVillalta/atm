/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pp2.atm.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Cuenta {

    protected String numeroCuenta;
    private static int cantCuentas=1;
    private double saldo;
    private boolean activa;
    private String pin;
    protected ArrayList<Transaccion> transacciones;

    public Cuenta(String numeroCuenta, double saldoInicial, String pin) {
        this.saldo = saldoInicial;
        this.activa = true;
        this.pin = encriptarPin(pin);
        this.transacciones = new ArrayList<>();
        this.numeroCuenta = numeroCuenta;
    }

    // Método estático para crear una nueva cuenta
    public static Cuenta crearCuenta(String numeroCuenta, double saldoInicial, String pin) {
        System.out.println("Creando una nueva cuenta con número: " + numeroCuenta + " y saldo inicial: " + saldoInicial);
        return new Cuenta(numeroCuenta, saldoInicial, pin);
    }

    // consultarEstatus(): Retorna el estatus de la cuenta (activa o inactiva).
    public boolean consultarEstatus() {
        return activa;
    }
    
    // Método para encriptar el PIN utilizando SHA-256
    private String encriptarPin(String pin) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pin.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar el PIN", e);
        }
    }

    public boolean validarPin(String pinIngresado) {
        return this.pin.equals(encriptarPin(pinIngresado));  // Compara el PIN ingresado en su forma encriptada
    }
    
    // realizarDepositoColones(double monto): Realiza un depósito en colones.
    public void realizarDepositoColones(double monto) {
        saldo += monto;
        transacciones.add(new Transaccion("Depósito Colones", monto, false));
    }

    // realizarDepositoDolares(double montoUSD, double tipoCambioCompra): Realiza un depósito en dólares.
    public void realizarDepositoDolares(double montoUSD, double tipoCambioCompra) {
        double montoColones = montoUSD * tipoCambioCompra;
        saldo += montoColones;
        transacciones.add(new Transaccion("Depósito Dólares", montoColones, false));
    }

    // realizarRetiroColones(double monto): Realiza un retiro en colones.
    public void realizarRetiroColones(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            transacciones.add(new Transaccion("Retiro Colones", monto, false));
        }
    }

    // realizarRetiroDolares(double montoUSD, double tipoCambioVenta): Realiza un retiro en dólares.
    public void realizarRetiroDolares(double montoUSD, double tipoCambioVenta) {
        double montoColones = montoUSD * tipoCambioVenta;
        if (saldo >= montoColones) {
            saldo -= montoColones;
            transacciones.add(new Transaccion("Retiro Dólares", montoColones, false));
        }
    }

    // transferirFondos(Cuenta destino, double monto): Transfiere fondos entre dos cuentas del mismo dueño.
    public void transferirFondos(Cuenta destino, double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            destino.saldo += monto;
            transacciones.add(new Transaccion("Transferencia", monto, false));
        }
    }

    // eliminarCuenta(): Elimina la cuenta, verificando el saldo.
    public void eliminarCuenta() {
        if (saldo == 0) {
            activa = false;
        }
    }

    // obtenerTransacciones(): Retorna el historial de transacciones de la cuenta.
    public ArrayList<Transaccion> obtenerTransacciones() {
        return transacciones;
    }

    // consultarEstadoCuenta(): Muestra el estado de cuenta en colones.
    public double consultarEstadoCuenta() {
        return saldo;
    }

    // consultarEstadoCuentaDolarizado(double tipoCambioCompra): Muestra el estado de cuenta en dólares.
    public double consultarEstadoCuentaDolarizado(double tipoCambioCompra) {
        return saldo / tipoCambioCompra;
    }

    // cambiarPin(String nuevoPin): Actualiza el PIN de la cuenta.
    public void cambiarPin(String nuevoPin) {
        this.pin = nuevoPin;
    }

    // validarPin(String pinIngresado): Verifica que el PIN ingresado sea correcto.
    //public boolean validarPin(String pinIngresado) {
    //    return this.pin.equals(pinIngresado);
    //}
    
    
    // Método para obtener el PIN encriptado (solo para fines de prueba)
    public String getPinEncriptado() {
        return this.pin;
}

    // get el NumeroCuenta
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    // get el Saldo
    public double getSaldo() {
        return saldo;
    }

    public boolean estaBloqueada() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean validarPIN(String pinIngresado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void bloquearCuenta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static String generarNumeroCuenta() {
        return "CTA" + (cantCuentas++);
    }
}
