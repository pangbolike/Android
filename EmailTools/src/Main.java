import EmailTools.Mail;
import EmailTools.MailSender;
import EmailTools.MailSenderFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexpang on 2015/4/20.
 */
public class Main {
    public static void main(String args[]){
        MailSender sms = MailSenderFactory.getSender("address","passwd");
        List<String> recv = new ArrayList<String>();
        recv.add("address");
        try{
            sms.send(recv,new Mail("test email","test content"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
