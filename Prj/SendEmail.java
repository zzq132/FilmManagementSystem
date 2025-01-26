package Prj;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;

public class SendEmail 
{
    public void sendEmail(String emailAddress,String password) throws GeneralSecurityException  
    {
        // 收件人电子邮箱
        String to = emailAddress;

        // 发件人电子邮箱
        String from = "3323519535@qq.com";

        String host = "smtp.qq.com";  //QQ 邮件服务器

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("3323519535@qq.com", "hkwzszbufmnjcheb"); 
            }
        });
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Reset Password");
            message.setText("Your new password is: "+password);
            Transport.send(message);
            System.out.println("Sent message successfully.");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
