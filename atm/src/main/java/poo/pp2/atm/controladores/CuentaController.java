package poo.pp2.atm.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poo.pp2.atm.dto.ConsultaCuentaDto;
import poo.pp2.atm.dto.CuentaDto;
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


    @GetMapping("/cambiarPin")
    public String cambiarPin() {
        return "/changePinForm";
    }

    @GetMapping("/consultarNumeroCuenta")
    public String consultarNumeroCuenta() {
        return "cNumeroCuenta";
    }

    @GetMapping("/consultarEstadoC")
    public String consultarEstadoC() {
        return "consultarEstadoC";
    }
    @GetMapping("/consultarEstadoCUSD")
    public String consultarEstadoCUSD() {
        return "consultarEstadoCUSD";
    }



    @GetMapping("/depositoUSD")
    public String depositoUSD() {
        return "realizarDepositoUSD";
    }
    @GetMapping("/depositoCRC")
    public String depositoCRC() {
        return "realizarDepositoCRC";
    }



    @GetMapping("/retiroCRC")
    public String retiroCRC() {
        return "realizarRetiroCRC";
    }

    @GetMapping("/retiroUSD")
    public String retiroUSD() {
        return "realizarRetiroUSD";
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





