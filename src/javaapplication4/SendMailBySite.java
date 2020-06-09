package javaapplication4;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author alsorc
 */
public class SendMailBySite {
    
    public static void main(String[] args) throws MessagingException {

  String host="smtp.gmail.com";
  final String user="exkapp@gmail.com";//change accordingly
  final String password="tuyirlzhxcpwjkkp";//change accordingly
  String to="also.erc@gmail.com";//change accordingly

   //Get the session object
   Properties props = new Properties();
   props.put("mail.smtp.host",host);
   props.put("mail.smtp.port", 465);
   props.put("mail.smtp.auth", "true");
   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
   
   
   Session session = Session.getDefaultInstance(props,
    new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication(user,password);
      }
    });

        //Compose the message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
        try {
            message.setSubject("Págame prra");
        } catch (MessagingException ex) {
            Logger.getLogger(SendMailBySite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          // message contains HTML markups
        String msg = "<h1>Mi pedido</h1>";
        msg += "<h2><b>Recibo #</b> 123456</h2>";
        msg += "<h3><b>Fecha</b> 2020/06/08</h3>";
        msg += "<h3><b>Cliente:</b> Elva Boso</h3>";
        msg += "<h3><b>Origen:</b> Oxxo av.2 Calle 17 Córdoba Veracruz 94696</h3>";
        msg += "<h3><b>Destino:</b> Avenida Felipe de la Cueva N°5 94940</h3>";
        msg += "<table>";
        msg += "<tr>";
            msg += "<th><b>Subtotal</b></th>";
            msg += "<th><b>Impuesto</b></th>";
            msg += "<th><b>Descuento</b></th>";
            msg += "<th><b>Total</b></th>";
        msg += "</tr>";
        msg += "<tr>";
            msg += "<td>$1200.00</td>";
            msg += "<td>$200.00</td>";
            msg += "<td>$400.00</td>";
            msg += "<td>$1000.00</td>";
        msg += "</tr>";
        
        msg += "</table>";
        message.setContent(msg, "text/html");
        
        
        
        //send the message
        Transport.send(message);
        System.out.println("message sent successfully...");
 }
    
}
