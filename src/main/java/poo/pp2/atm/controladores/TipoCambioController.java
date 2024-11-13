package poo.pp2.atm.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poo.pp2.atm.integracion.BCCRWebScraping;

@Controller
@RequestMapping("/tipoCambio")
public class TipoCambioController {

    @GetMapping("/compra")
    public String compra(Model model) {
        // Llamar al método para obtener el tipo de cambio de compra
        String tipoCambio = BCCRWebScraping.obtenerTipoCambioCompra();

        // Pasar el tipo de cambio a la vista
        model.addAttribute("tipoCambio", tipoCambio);

        return "consultarTipoCambioC";  // Retorna la vista 'consultarTipoCambioC'
    }

    @GetMapping("/venta")
    public String venta(Model model) {
        // Llamar al método para obtener el tipo de cambio de compra
        String tipoCambio = BCCRWebScraping.obtenerTipoCambioVenta();

        // Pasar el tipo de cambio a la vista
        model.addAttribute("tipoCambio", tipoCambio);

        return "consultarTipoCambioV";
    }
}
