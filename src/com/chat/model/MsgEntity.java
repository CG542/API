package com.chat.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mot on 8/9/16.
 */
@XmlRootElement
public class MsgEntity {
    private  int id;
    private  int fromID;
    private int toID;
    private String content;
    private String time;

    @XmlElement
    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public int getFromID() {
        return fromID;
    }

    public void setFromID(int fromID) {
        this.fromID = fromID;
    }

    @XmlElement
    public int getToID() {
        return toID;
    }

    public void setToID(int toID) {
        this.toID = toID;
    }

    @XmlElement
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlElement
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
