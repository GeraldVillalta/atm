package poo.pp2.atm.dto;

import java.time.LocalDate;
import java.util.Date;

public class ClienteDto {
    private String nombreCompleto;
    private String identificacion ;
    private String numeroTelefono;
    private String correoElectronico;
    private String categoria;
    private int maximoCuentas;
    private LocalDate fechaNacimiento;


    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getMaximoCuentas() {
        return maximoCuentas;
    }

    public void setMaximoCuentas(int maximoCuentas) {
        this.maximoCuentas = maximoCuentas;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
