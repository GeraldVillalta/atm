package poo.pp2.atm.controladores;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poo.pp2.atm.dto.CambioPinDto;
import poo.pp2.atm.dto.CambioTelefonoDto;
import poo.pp2.atm.dto.ClienteDto;
import poo.pp2.atm.dto.ClienteDtoJuridico;
import poo.pp2.atm.model.Cliente;
import poo.pp2.atm.model.ClienteJuridico;
import poo.pp2.atm.model.Cuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/client")
public class ClientController {


    @GetMapping("/crear")
    public String crearCliente(Model model) {
        ClienteDto clienteDto = new ClienteDto();
        model.addAttribute("cliente", clienteDto);

        return "crearCliente"; // O puedes redirigir a otra vista si es necesario
    }

    @PostMapping("/crear")
    public String crearCliente(@ModelAttribute("cliente") ClienteDto clienteDto) {
        Cliente.agregarCliente(clienteDto.getNombreCompleto(),clienteDto.getIdentificacion(),clienteDto.getNumeroTelefono(),clienteDto.getCorreoElectronico(),clienteDto.getCategoria(),clienteDto.getMaximoCuentas(),clienteDto.getFechaNacimiento() );

        return "menu";
    }







    @GetMapping("/crearJ")
    public String crearClienteJuridico(Model model) {
        ClienteDtoJuridico clienteDtoJuridico = new ClienteDtoJuridico();
        model.addAttribute("cliente", clienteDtoJuridico);
        return "crearClienteJ";
    }
    @PostMapping("/crearJ")
    public String crearClienteJuridico(@ModelAttribute("cliente") ClienteDtoJuridico clienteDtoJuridico) {
        ClienteJuridico.agregarCliente(clienteDtoJuridico.getNombreCompleto(),clienteDtoJuridico.getIdentificacion(),clienteDtoJuridico.getNumeroTelefono(),clienteDtoJuridico.getCorreoElectronico(),clienteDtoJuridico.getCategoria(), clienteDtoJuridico.getRazonSocial());
        return "menu";
}


    @PostMapping("/cambiarPin")
    public String cambiarPin(@ModelAttribute("cuenta") CambioPinDto cambioPinDto) {
        Cuenta cuenta = Cuenta.consultarCuenta(cambioPinDto.getNumeroCuenta());

        cuenta.cambiarPin(cambioPinDto.getPinNuevo());
        System.out.println("PIN NUEVO+"+ cuenta.getPin());
        return "changePinForm";
    }

    @GetMapping("/actualizarTelefono")
    public String actualizarTelefono(Model model) {
        CambioTelefonoDto cambioTelefonoDto = new CambioTelefonoDto();
        model.addAttribute("cliente", cambioTelefonoDto);

        return "actualizarTelefono";
    }

    @PostMapping("/actualizarTelefono")
    public String actualizarTelefono(@ModelAttribute("cliente") CambioTelefonoDto cambioTelefonoDto) {
        Cliente cliente = Cliente.buscarClientePorIdentificacion(cambioTelefonoDto.getIdentificacion());
        cliente.setNumeroTelefono(cambioTelefonoDto.getTelefono());
        System.out.println("TELEFONO"+cliente.getNumeroTelefono());
        return "menu";
    }

    @GetMapping("/actualizarEmail")
    public String actulizarEmail(Model model) {
        CambioTelefonoDto cambioTelefonoDto = new CambioTelefonoDto();
        model.addAttribute("cliente", cambioTelefonoDto);
        return "actualizarEmail";
    }

    @PostMapping("/actualizarEmail")
    public String actulizarEmail(@ModelAttribute("cliente") CambioTelefonoDto cambioTelefonoDto) {
        Cliente cliente = Cliente.buscarClientePorIdentificacion(cambioTelefonoDto.getIdentificacion());
        cliente.setCorreoElectronico(cambioTelefonoDto.getTelefono());
        System.out.println("TELEFONO"+cliente.getCorreoElectronico());
        return "menu";
    }



    @GetMapping("/verificarExistencia")
    public String verificarExistencia() {
        return "verificar";  // cambiar, debe de enviar
    }


    @GetMapping("/clientesRegistrados")
    public String clientesRegistrados(Model model) {
        List clientes = Cliente.obtenerClientes();
        model.addAttribute("clientes", clientes);
        return "clientesRegistrados";
    }




}
