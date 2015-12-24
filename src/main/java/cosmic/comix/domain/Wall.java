package cosmic.comix.domain;

/**
 * Created by NSchneier on 6/17/2015.
 */
public class Wall extends AbstractDomain {
    private String receiver;
    private String sender;
    private String message;

    public Wall() {
        setId(Long.valueOf("123"));
    }
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
