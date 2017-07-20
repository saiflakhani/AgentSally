package com.quicsolv.agentsally.pojo;

/**
 * Created by Saif on 7/15/17.
 */

public class MessageAttributes {
    public boolean sending; // sending or receiving
    public String message;
    public MessageAttributes(boolean sending, String message) {
        super();
        this.sending = sending;
        this.message = message;
    }

}
