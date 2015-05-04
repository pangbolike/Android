package EmailTools;

/**
 * Created by alexpang on 2015/4/20.
 */
public class Mail {

    private String content;

    private String subject;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getContent() {

        return content;
    }

    public String getSubject() {
        return subject;
    }
    public Mail(String subject,String content){
        this.subject = subject;
        this.content = content;
    }
}
