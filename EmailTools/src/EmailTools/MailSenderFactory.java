package EmailTools;

import java.util.HashMap;

/**
 * Created by alexpang on 2015/4/20.
 */
public class MailSenderFactory {

    private static MailSender serviceSms = null;

    private static HashMap<String,MailSender> cache = new HashMap<String,MailSender>();

    public static MailSender getSender(String address,String passwd) {
        MailSender result = cache.get(address + "_" + passwd);
        if (result == null){
            result = new MailSender(address,passwd);
            cache.put(address + "_" + passwd,result);
        }
        return result;
    }
}
