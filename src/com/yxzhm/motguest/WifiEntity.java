package com.yxzhm.motguest;

import javax.persistence.*;

/**
 * Created by mot on 3/31/16.
 */
@Entity
@Table(name = "Wifi", schema = "MotGuest", catalog = "")
public class WifiEntity {
    private int id;
    private String wifidates;
    private String password;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "wifidates", nullable = true, length = 45)
    public String getWifidates() {
        return wifidates;
    }

    public void setWifidates(String wifidates) {
        this.wifidates = wifidates;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WifiEntity that = (WifiEntity) o;


        if (wifidates != null ? !wifidates.equals(that.wifidates) : that.wifidates != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (wifidates != null ? wifidates.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
