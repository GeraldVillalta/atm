package poo.pp2.atm.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poo.pp2.atm.dto.*;
import poo.pp2.atm.integracion.EmailEnvio;
import poo.pp2.atm.model.Cliente;
import poo.pp2.atm.model.Cuenta;

import java.util.List;


@Controller
@RequestMapping("/cuenta")
public class CuentaController {
    Cuenta cuenta;
    CuentaDto cuentaDto;
    int cantidadIngresadaPin = 0;
    String pin;


    @GetMapping("/crear")
    public String crearCuenta(Model model) {
        cuentaDto = new CuentaDto();
        model.addAttribute("cuenta", cuentaDto);
        return "crearCuenta";
    }
    @PostMapping("/crear")
    public String crearCuenta(@ModelAttribute("cuenta") CuentaDto cuentaDto) {
        Cliente cliente = Cliente.buscarClientePorIdentificacion(cuentaDto.getNumeroCuenta());

        System.out.println(cuentaDto.getNumeroCuenta());
        cuenta = Cuenta.crearCuenta(cuentaDto.getNumeroCuenta(), cuentaDto.getSaldo(), cuentaDto.getPin(), cliente);
        System.out.println("ESTADO DE CUENTA"+cuenta.consultarEstadoCuenta());
        return "menu";
    }
    @GetMapping("/consultarSaldoActual")
    public String consultarSaldoActual(Model model) {
        ConsultaCuentaDto consultaCuentaDto = new ConsultaCuentaDto();
        model.addAttribute("cuenta", consultaCuentaDto);
        System.out.println("VALE"+ cuenta.getSaldo());
        return "consultarSaldoAct";

    }

    @PostMapping("/consultarSaldoActual")
    public String consultarSaldoActual(@ModelAttribute("cuenta") ConsultaCuentaDto consultaCuentaDto, Model model) {
        Cuenta cuenta = Cuenta.consultarCuenta(consultaCuentaDto.getNumeroCuenta());
        Cliente cliente = cuenta.getCliente();
        String validacion = validarPin(consultaCuentaDto,cuenta);
        System.out.println("PINDTO"+ consultaCuentaDto.getPin());
        System.out.println("PINCuenta"+ cuenta.getPin().toString());
        System.out.println("VALIDA"+validacion);

        if(validacion.equals("true") && cuenta.isActiva()){
            Double saldoActual = cuenta.getSaldo();  // Aquí deberías obtener el saldo de la cuenta registrada
            System.out.println(saldoActual);
            if (saldoActual != null) {
                model.addAttribute("saldoActual", saldoActual);
            } else {
                model.addAttribute("error", "Saldo no disponible.");
            }

            return "consultarSaldoAct";
        }
        else if(validacion.equals("bloquear") ){
            cuenta.bloquearCuenta();
            EmailEnvio.enviarCorreo(cliente.getCorreoElectronico());
            return "menu";
        }
        model.addAttribute("ruta", "consultarSaldoActual");
        return "errorValidacion";


    }

    @GetMapping("/consultarSaldoActualUSD")
    public String consultarSaldoActualUSD(Model model) {
        ConsultaCuentaDto consultaCuentaDto = new ConsultaCuentaDto();
        model.addAttribute("cuenta", consultaCuentaDto);
        System.out.println("VALE"+ cuenta.getSaldo());
        return "consultarSaldoActUSD";

    }
    @PostMapping("/consultarSaldoActualUSD")
    public String consultarSaldoActualUSD(@ModelAttribute("cuenta") ConsultaCuentaDto consultaCuentaDto, Model model) {
        Cuenta cuenta = Cuenta.consultarCuenta(consultaCuentaDto.getNumeroCuenta());
        Cliente cliente = cuenta.getCliente();
        String validacion = validarPin(consultaCuentaDto,cuenta);
        if(validacion.equals("true") && cuenta.isActiva()){
            Double saldoActual = cuenta.saldoEnDolares();  // Aquí deberías obtener el saldo de la cuenta registrada
            System.out.println(saldoActual);
            if (saldoActual != null) {
                model.addAttribute("saldoActual", saldoActual);
            } else {
                model.addAttribute("error", "Saldo no disponible.");
            }

            return "consultarSaldoActUSD";
        }
        else if(validacion.equals("bloquear") ){
            cuenta.bloquearCuenta();
            EmailEnvio.enviarCorreo(cliente.getCorreoElectronico());
            return "menu";
        }
        model.addAttribute("ruta", "consultarSaldoActual");
        return "errorValidacion";
    }
    @GetMapping("/consultarEstadoC")
    public String consultarEstadoC(Model model) {
        ConsultaEstadoDto consultaEstadoDto = new ConsultaEstadoDto();
        model.addAttribute("cuenta", consultaEstadoDto);
        return "consultarEstadoCuenta";
    }

    @PostMapping("/consultarEstadoC")
    public String consultarEstadoC(@ModelAttribute("cuenta") ConsultaEstadoDto consultaEstadoDto, Model model){
        Cuenta cuenta = Cuenta.consultarCuenta(consultaEstadoDto.getNumeroCuenta());
        boolean estado = cuenta.consultarEstatus();
        String activo;
        if (estado){
            activo = "Activa";
        }else{
            activo = "Inactiva";
        }
        System.out.println(activo);
        model.addAttribute("estado", activo);


        return "consultarEstadoCuenta";
    }

    @GetMapping("/cambiarPin")
    public String cambiarPin(Model model) {
        CambioPinDto cambioPinDto = new CambioPinDto();
        model.addAttribute("cuenta", cambioPinDto);
        return "changePinForm";
    }
    @PostMapping("/cambiarPin")
    public String cambiarPin(@ModelAttribute("cuenta") CambioPinDto cambioPinDto) {
        Cuenta cuenta = Cuenta.consultarCuenta(cambioPinDto.getNumeroCuenta());

        cuenta.cambiarPin(cambioPinDto.getPinNuevo());
        System.out.println("PIN NUEVO+"+ cuenta.getPin());
        return "changePinForm";
    }
    @GetMapping("/depositoCRC")
    public String depositoCRC(Model model) {
        DepositoDto depositoDto = new DepositoDto();
        model.addAttribute("cuenta", depositoDto);
        return "realizarDepositoCRC";
    }
    @PostMapping("/depositoCRC")
    public String depositoCRC(@ModelAttribute("cuenta") DepositoDto depositoDto) {
        Cuenta cuenta = Cuenta.consultarCuenta(depositoDto.getNumeroCuenta());
        cuenta.realizarDepositoColones(Double.parseDouble(depositoDto.getMontoDeposito()));
        System.out.println("SALDO NUEVO:" + cuenta.getSaldo());
        return "menu";


    }
    @GetMapping("/depositoUSD")
    public String depositoUSD(Model model) {
        DepositoDto depositoDto = new DepositoDto();
        model.addAttribute("cuenta", depositoDto);
        return "realizarDepositoUSD";
    }
    @PostMapping("/depositoUSD")
    public String depositoUSD(@ModelAttribute("cuenta") DepositoDto depositoDto) {
        Cuenta cuenta = Cuenta.consultarCuenta(depositoDto.getNumeroCuenta());
        cuenta.realizarDepositoDolares(Double.parseDouble(depositoDto.getMontoDeposito()));
        System.out.println("SALDO NUEVO:" + cuenta.getSaldo());
        return "menu";


    }

    @GetMapping("/retiroCRC")
    public String retiroCRC(Model model) {
        DepositoDto depositoDto = new DepositoDto();
        model.addAttribute("cuenta", depositoDto);

        return "realizarRetiroCRC";
    }

    @PostMapping("/retiroCRC")
    public String retiroCRC(@ModelAttribute("cuenta") DepositoDto depositoDto) {
        Cuenta cuenta = Cuenta.consultarCuenta(depositoDto.getNumeroCuenta());
        cuenta.realizarRetiroColones(Double.parseDouble(depositoDto.getMontoDeposito()));
        System.out.println("SALDO NUEVO:" + cuenta.getSaldo());
        return "menu";
    }
    @GetMapping("/retiroUSD")
    public String retiroUSD(Model model) {
        DepositoDto depositoDto = new DepositoDto();
        model.addAttribute("cuenta", depositoDto);
        return "realizarRetiroUSD";
    }
    @PostMapping("/retiroUSD")
    public String retiroUSD(@ModelAttribute("cuenta") DepositoDto depositoDto) {
        Cuenta cuenta = Cuenta.consultarCuenta(depositoDto.getNumeroCuenta());
        cuenta.realizarRetiroDolares(Double.parseDouble(depositoDto.getMontoDeposito()));
        System.out.println("SALDO NUEVO:" + cuenta.getSaldo());
        return "menu";
    }





    @GetMapping("/consultarNumeroCuenta")
    public String consultarNumeroCuenta(Model model) {
        ConsultaEstadoDto consultaEstadoDto = new ConsultaEstadoDto();
        model.addAttribute("cuenta", consultaEstadoDto);
        return "cNumeroCuenta";
    }

    @PostMapping("/consultarNumeroCuenta")
    public String consultarNumeroCuenta(@ModelAttribute("cuenta") ConsultaEstadoDto consultaEstadoDto, Model model) {
        Cuenta cuenta = Cuenta.consultarCuenta(consultaEstadoDto.getNumeroCuenta());
        String numeroCuenta = cuenta.getNumeroCuenta();
        model.addAttribute("numeroCuenta", numeroCuenta);

        return "cNumeroCuenta";
    }


    @GetMapping("/cuentasRegistradas")
    public String cuentasRegistradas(Model model) {
        List cuentas = Cuenta.obtenerCuentas();

        model.addAttribute("cuentas", cuentas);
        return "cuentasRegistradas";
    }




    @GetMapping("/realizarTransferencia")
    public String transferencia(Model model) {
        TransferenciaDto transferenciaDto = new TransferenciaDto();
        model.addAttribute("cuenta", transferenciaDto);
        return "realizarTransferencia";


    }
    @PostMapping("/realizarTransferencia")
    public String transferencia(@ModelAttribute("cuenta") TransferenciaDto transferenciaDto, Model model) {
        Cuenta cuentaOrigen = Cuenta.consultarCuenta(transferenciaDto.getNumeroCuenta());
        Cuenta cuentaDestinatario = Cuenta.consultarCuenta(transferenciaDto.getNumeroCuentaDestinatario());

        cuentaOrigen.transferirFondos(cuentaDestinatario,Double.parseDouble(transferenciaDto.getMontoDeposito()));
        System.out.println("SALDO NUEVO ORIGEN:" + cuentaOrigen.getSaldo());
        System.out.println("SALDO NUEVO DESTINATARIO:" + cuentaDestinatario.getSaldo());

        // Formatea el mensaje de confirmación
        String mensaje = String.format("La transferencia se realizó exitosamente.\n"
                        + "Nuevo saldo de la cuenta origen: %.2f colones.\n"
                        + "Nuevo saldo de la cuenta destinatario: %.2f colones.",
                cuentaOrigen.getSaldo(),
                cuentaDestinatario.getSaldo());

        // Agrega el mensaje al modelo
        model.addAttribute("mensajeConfirmacion", mensaje);

        return "realizarTransferencia";
    }


    @GetMapping("/consultarTransacciones")
    public String consultarTransacciones(Model model) {
        ConsultaCuentaDto consultaCuentaDto = new ConsultaCuentaDto();
        model.addAttribute("cuenta", consultaCuentaDto);
        return "consultarTransacciones";
    }

    @PostMapping("/consultarTransacciones")
    public String consultarTransacciones(@ModelAttribute("cuenta") ConsultaCuentaDto consultaCuentaDto, Model model){
        Cuenta cuenta = Cuenta.consultarCuenta(consultaCuentaDto.getNumeroCuenta());
        List transferencias = cuenta.getTransacciones();
        model.addAttribute("cuentas", transferencias);



        return "tablaConsultarT";
    }





    public String validarPin(ConsultaCuentaDto consultaCuentaDto, Cuenta cuenta){
        String pinIngresado = consultaCuentaDto.getPin();
        if (cuenta.validarPin(pinIngresado)) {
            return "true";
        }
        else{
            cantidadIngresadaPin++;
        }

        if (cantidadIngresadaPin>3){
            return "bloquear";
        }
        return "false";


    }

    @GetMapping("/DeleteAccountForm")
    public String DeleteAccountForm(Model model) {
        EliminarCuentaDto eliminarCuentaDto = new EliminarCuentaDto();
        model.addAttribute("cuenta", eliminarCuentaDto);
        return "DeleteAccountForm";
    }

    @PostMapping("/DeleteAccountForm")
    public String DeleteAccountForm(@ModelAttribute("cuenta") EliminarCuentaDto eliminarCuentaDto) {
        Cuenta cuenta = Cuenta.consultarCuenta(eliminarCuentaDto.getNumeroCuenta());
        cuenta.eliminarCuenta(cuenta);
        System.out.println("Se elimino correctamente!");
        return "menu";
    }
}





