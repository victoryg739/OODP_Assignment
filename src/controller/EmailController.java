package controller;

import modal.Booking;
import modal.Customer;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static view.utilF.print;
import static view.utilF.println;


public class EmailController {
    CustomerController cc = new CustomerController();
    BookingController bc = new BookingController();
    //SETUP MAIL SERVER PROPERTIES
    //DRAFT AN EMAIL
    //SEND EMAIL

    Session newSession = null;
    MimeMessage mimeMessage = null;


    public void sendEmail() throws MessagingException {
        String fromUser = "moblimamoviebooking@gmail.com";  //Enter sender email id
        String fromUserPassword = "uaqdicnxfdsfduyg";  //Enter sender gmail password , this will be authenticated by gmail smtp server
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        println("");
        print("Email successfully sent!");
    }

    public MimeMessage draftEmail(Booking booking) throws AddressException, MessagingException, IOException {
        String username = booking.getUsername();
        Customer c = cc.readByUsername(username);
        //System.out.println("customer is " + c.getUsername());
        String emailSubject = "Booking of your Movie: " + booking.getMovie().getTitle();
        String emailBody = "test";
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

    public void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties, null);
    }
}