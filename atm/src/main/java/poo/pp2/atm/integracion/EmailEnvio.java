package poo.pp2.atm.integracion;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailEnvio {
    public static void enviarCorreo(String destinatario) {
        //String to = "emilyarayainfo@gmail.com"; // correo del destinatario
        String to = destinatario;
        String from = "proyectopoo00@gmail.com"; // tu correo (remitente)
        String host = "smtp.gmail.com"; // servidor SMTP de Gmail

        // Configuración de las propiedades
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        properties.put("mail.debug", "true"); // habilitar depuración

        // Autenticación
        final String username = "proyectopoo00@gmail.com"; // tu correo
        final String password = "fwim skby tesc kpbr"; // tu contraseña o contraseña de aplicación

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("ESTADO DE CUENTA - BANCO HRM-");
            message.setText("Estimado cliente su cuenta bancaria se encuentra bloqueada, debido a ingresar su PIN de forma incorrecta 3 veces.");

            Transport.send(message);
            System.out.println("Correo enviado exitosamente!");

        } catch (MessagingException mex) {
    }
}
}