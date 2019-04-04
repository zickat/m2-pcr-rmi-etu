package m2dl.pcr.rmi.lightslack;

import java.io.Serializable;

public class Message implements Serializable {

    private String content;

    private String sender;

    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }
}
