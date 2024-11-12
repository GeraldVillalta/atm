package poo.pp2.atm.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poo.pp2.atm.dto.*;
import poo.pp2.atm.model.Cuenta;


@Controller
@RequestMapping("/cuenta")
public class CuentaController {
    Cuenta cuenta;
    CuentaDto cuentaDto;


    @GetMapping("/crear")
    public String crearCuenta(Model model) {
        cuentaDto = new CuentaDto();
        model.addAttribute("cuenta", cuentaDto);
        return "/crearCuenta";
    }
    @PostMapping("/crear")
    public String crearCuenta(@ModelAttribute("cuenta") CuentaDto cuentaDto) {
        cuenta = new Cuenta(cuentaDto.getNumeroCuenta(), cuentaDto.getSaldo(), cuentaDto.getPin());
        System.out.println(cuentaDto.getNumeroCuenta());
        Cuenta.crearCuenta(cuentaDto.getNumeroCuenta(), cuentaDto.getSaldo(), cuentaDto.getPin());
        System.out.println("ESTADO DE CUENTA"+cuenta.consultarEstadoCuenta());
        return "/menu";
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

        Double saldoActual = cuenta.getSaldo();  // Aquí deberías obtener el saldo de la cuenta registrada
        System.out.println(saldoActual);
        if (saldoActual != null) {
            model.addAttribute("saldoActual", saldoActual);
        } else {
            model.addAttribute("error", "Saldo no disponible.");
        }

        return "consultarSaldoAct";
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

        Double saldoActual = cuenta.saldoEnDolares();  // Aquí deberías obtener el saldo de la cuenta registrada
        System.out.println(saldoActual);
        if (saldoActual != null) {
            model.addAttribute("saldoActual", saldoActual);
        } else {
            model.addAttribute("error", "Saldo no disponible.");
        }

        return "consultarSaldoActUSD";
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
        return "/changePinForm";
    }
    @PostMapping("/cambiarPin")
    public String cambiarPin(@ModelAttribute("cuenta") CambioPinDto cambioPinDto) {
        Cuenta cuenta = Cuenta.consultarCuenta(cambioPinDto.getNumeroCuenta());

        cuenta.cambiarPin(cambioPinDto.getPinNuevo());
        System.out.println("PIN NUEVO+"+ cuenta.getPin());
        return "/changePinForm";
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
        return "/menu";


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
        return "/menu";


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
        return "/menu";
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
        return "/menu";
    }





    @GetMapping("/consultarNumeroCuenta")
    public String consultarNumeroCuenta() {
        return "cNumeroCuenta";
    }


    @GetMapping("/consultarEstadoCUSD")
    public String consultarEstadoCUSD() {
        return "consultarEstadoCUSD";
    }












    @GetMapping("/realizarTransferencia")
    public String transferencia() {
        return "realizarTransferencia";
    }

    @GetMapping("/consultarTransacciones")
    public String consultarTransacciones() {
        return "consultarTransacciones";
    }


}





