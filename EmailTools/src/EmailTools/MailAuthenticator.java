package EmailTools;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by alexpang on 2015/4/20.
 */
public class MailAuthenticator extends Authenticator {

    private String username;

    private String password;

    public MailAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getPassword() {
        return password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

    String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
