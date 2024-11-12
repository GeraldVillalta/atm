package poo.pp2.atm.controladores;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poo.pp2.atm.dto.ClienteDto;
import poo.pp2.atm.model.Cliente;

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
    public String crearClienteJuridico() {
        return "crearClienteJ";
    }

    @GetMapping("/actualizarTelefono")
    public String actulizarTelefono() {
        return "actualizarTelefono";
    }

    @GetMapping("/actualizarEmail")
    public String actulizarEmail() {
        return "actualizarEmail";
    }

    @GetMapping("/verificarExistencia")
    public String verificarExistencia() {
        return "verificar";  // cambiar, debe de enviar
    }


    @GetMapping("/clientesRegistrados")
    public String clientesRegistrados() {
        return "clientesRegistrados";
    }
}
