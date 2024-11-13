package poo.pp2.atm.integracion;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import java.io.IOException;

public class BCCRWebScraping {
    public static String obtenerTipoCambioCompra() {
        String url = "https://gee.bccr.fi.cr/indicadoreseconomicos/Cuadros/frmVerCatCuadro.aspx?idioma=1&CodCuadro=%20400";
        String tipoCompra = "No disponible";

        try {
            System.out.println("Conectando a la URL: " + url);
            Document doc = Jsoup.connect(url).get();
            Elements cells = doc.select("td.celda400");  // Selecciona todas las celdas con la clase 'celda400'

            System.out.println("Número de celdas encontradas con la clase 'celda400': " + cells.size());

            // Imprime todas las celdas encontradas para depurar y verificar el contenido
            for (int i = 0; i < cells.size(); i++) {
                System.out.println("Celda " + (i + 1) + ": " + cells.get(i).text());
            }

            // Asegurarse de que haya suficientes celdas para acceder a la celda 60
            if (cells.size() >= 60) {
                tipoCompra = cells.get(59).text();  // Obtén el valor específico de la celda número 60 (índice 59)
                System.out.println("Tipo de cambio de compra encontrado (Celda 60): " + tipoCompra);
            } else {
                System.out.println("No se encontraron suficientes celdas con la clase 'celda400'.");
            }
        } catch (IOException e) {
            System.out.println("Error al conectar con el sitio web del BCCR: " + e.getMessage());
        }

        return tipoCompra;
    }

    public static String obtenerTipoCambioVenta() {
        String url = "https://gee.bccr.fi.cr/indicadoreseconomicos/Cuadros/frmVerCatCuadro.aspx?idioma=1&CodCuadro=%20400";
        String tipoVenta = "No disponible";

        try {
            System.out.println("Conectando a la URL: " + url);
            Document doc = Jsoup.connect(url).get();
            Elements cells = doc.select("td.celda400");  // Selecciona todas las celdas con la clase 'celda400'

            System.out.println("Número de celdas encontradas con la clase 'celda400': " + cells.size());

            // Imprime todas las celdas encontradas para depurar y verificar el contenido
            for (int i = 0; i < cells.size(); i++) {
                System.out.println("Celda " + (i + 1) + ": " + cells.get(i).text());
            }

            if (cells.size() >= 1) {
                tipoVenta = cells.get(cells.size() - 1).text();  // Obtén el último valor que corresponde al tipo de cambio de venta
                System.out.println("Tipo de cambio de venta encontrado: " + tipoVenta);
            } else {
                System.out.println("No se encontraron suficientes celdas con la clase 'celda400'.");
            }
        } catch (IOException e) {
            System.out.println("Error al conectar con el sitio web del BCCR: " + e.getMessage());
        }

        return tipoVenta;
    }
}
