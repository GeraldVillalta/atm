package poo.pp2.atm.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poo.pp2.atm.dto.CuentaDto;
import poo.pp2.atm.model.Cuenta;


@Controller
@RequestMapping("/cuenta")
public class CuentaController {

    @GetMapping("/crear")
    public String crearCuenta(Model model) {
        CuentaDto cuentaDto = new CuentaDto();
        model.addAttribute("cuenta", cuentaDto);
        return "/crearCuenta";
    }
    @PostMapping("/crear")
    public String crearCuenta(@ModelAttribute("cuenta") CuentaDto cuentaDto) {


        return "/menu";
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

    @GetMapping("/consultarSaldoActual")
    public String consultarSaldoActual() {
        return "consultarSaldoAct";

    }

    @GetMapping("/depositoUSD")
    public String depositoUSD() {
        return "realizarDepositoUSD";
    }
    @GetMapping("/depositoCRC")
    public String depositoCRC() {
        return "realizarDepositoCRC";
    }

    @GetMapping("/consultarSaldoActualUSD")
    public String consultarSaldoActualUSD() {
        return "consultarSaldoActUSD";

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





