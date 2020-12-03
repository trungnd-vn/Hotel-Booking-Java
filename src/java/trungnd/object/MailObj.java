/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.object;

import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author Asus
 */
public class MailObj {
    
    public static String sendMail(String to) {
      
        String subject = "Verify CART";
        String msg = "";
        for (int i = 0; i < 6; i++) {
            msg += new Random().nextInt(10);
        }

        final String username = "trungndse140274@fpt.edu.vn";//your email id
        final String password = "trungtyty254143";// your password

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(to));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText("Confirm with this code: "+msg+"\n");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
//    public static void main(String[] args) {
//        System.out.println(sendMail("truongquoclap3008@gmail.com"));
//    }
}
