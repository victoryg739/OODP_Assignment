package controller;

import model.Booking;
import model.Customer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

import static view.utilF.print;
import static view.utilF.println;


public class EmailController {
    CustomerController cc = new CustomerController();
    Session newSession = null;
    MimeMessage mimeMessage = null;

    /**
     * Function to send email to a particular person
     */
    public void sendEmail() throws MessagingException {
        String fromUser = "moblimamoviebooking1@gmail.com";  //Enter sender email id
        String fromUserPassword = "geuzgscibzomnsls";  //Enter sender gmail password , this will be authenticated by gmail smtp server
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        println("");
        print("Email successfully sent!");
    }


    /**
     * Function to draft an email
     */
    public MimeMessage draftEmail(Booking booking) throws AddressException, MessagingException, IOException {
        String username = booking.getUsername();
        Customer c = cc.readByUsername(username);
        String emailSubject = "Booking of your Movie: " + booking.getMovie().getTitle();
        String emailBody = booking.printEmailBookingSummary();
        mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(c.getEmail()));
        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/html;charset=UTF-8");
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        mimeMessage.setContent(multiPart);
        print("Sending booking to your email...");
        return mimeMessage;
    }

    /**
     * Function to set the server properties
     */
    public void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties, null);
    }
}