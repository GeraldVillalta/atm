package poo.pp2.atm.dto;

public class TransferenciaDto {
    private String numeroCuenta;
    private String pin;
    private String numeroCuentaDestinatario;
    private String montoDeposito;

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getNumeroCuentaDestinatario() {
        return numeroCuentaDestinatario;
    }

    public void setNumeroCuentaDestinatario(String numeroCuentaDestinatario) {
        this.numeroCuentaDestinatario = numeroCuentaDestinatario;
    }

    public String getMontoDeposito() {
        return montoDeposito;
    }

    public void setMontoDeposito(String montoDeposito) {
        this.montoDeposito = montoDeposito;
    }
}
