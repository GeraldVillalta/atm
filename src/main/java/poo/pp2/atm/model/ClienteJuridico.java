/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pp2.atm.model;

import java.time.LocalDate;

/**
 *
 * @author rache
 */
public class ClienteJuridico extends Cliente {
    private String nombreCompleto;
    private String identificacion ;
    private String numeroTelefono;
    private String correoElectronico;
    private String categoria;
    private String razonSocial;

    public static String agregarCliente(String nombreCompleto, String identificacion, String numeroTelefono, String correoElectronico, String categoria, String razonSocial) {
        Cliente.agregarCliente(nombreCompleto,identificacion,numeroTelefono,correoElectronico,categoria, razonSocial);
        return nombreCompleto;
    }


}
