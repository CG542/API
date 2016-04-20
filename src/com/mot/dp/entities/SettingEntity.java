package com.mot.dp.entities;

import javax.persistence.*;

/**
 * Created by mot on 4/13/16.
 */
@Entity
@Table(name = "Setting", schema = "DP", catalog = "")
public class SettingEntity {
    private int id;
    private String profilename;
    private String setting;
    private Integer userid;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "profilename", nullable = true, length = 45)
    public String getProfilename() {
        return profilename;
    }

    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }

    @Basic
    @Column(name = "setting", nullable = true, length = 90)
    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    @Basic
    @Column(name = "userid", nullable = true)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SettingEntity that = (SettingEntity) o;

        if (id != that.id) return false;
        if (profilename != null ? !profilename.equals(that.profilename) : that.profilename != null) return false;
        if (setting != null ? !setting.equals(that.setting) : that.setting != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (profilename != null ? profilename.hashCode() : 0);
        result = 31 * result + (setting != null ? setting.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        return result;
    }
}
