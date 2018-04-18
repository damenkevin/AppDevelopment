package sportsbuddy.sportsbuddy;

/**
 * Created by s165700 on 4/18/2018.
 */

public class Message {
    private String msgContent;
    private String sender;
    private String timeSent;

    public Message(String msgContent, String sender, String timeSent) {
        this.msgContent = msgContent;
        this.sender = sender;
        this.timeSent = timeSent;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }
}
