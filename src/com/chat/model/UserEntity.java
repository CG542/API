package com.chat.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Created by mot on 8/9/16.
 */
@XmlRootElement
public class UserEntity {

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement
    public List<Integer> getFriendIDs() {
        return friendIDs;
    }

    public void setFriendIDs(List<Integer> friendIDs) {
        this.friendIDs = friendIDs;
    }

    private int id;
    private String userName;
    private String password;
    private List<Integer> friendIDs;


}
