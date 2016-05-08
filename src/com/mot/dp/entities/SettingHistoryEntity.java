package com.mot.dp.entities;

import javax.persistence.*;

/**
 * Created by mot on 4/13/16.
 */
@Entity
@Table(name = "SettingHistory", schema = "DP", catalog = "")
public class SettingHistoryEntity {
    private int id;
    private Integer settingid;
    private Integer dpid;
    private Boolean deploied;
    private String requesttime;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "settingid", nullable = true)
    public Integer getSettingid() {
        return settingid;
    }

    public void setSettingid(Integer settingid) {
        this.settingid = settingid;
    }

    @Basic
    @Column(name = "dpid", nullable = true)
    public Integer getDpid() {
        return dpid;
    }

    public void setDpid(Integer dpid) {
        this.dpid = dpid;
    }

    @Basic
    @Column(name = "deploied", nullable = true)
    public Boolean getDeploied() {
        return deploied;
    }

    public void setDeploied(Boolean deploied) {
        this.deploied = deploied;
    }

    @Basic
    @Column(name = "requesttime", nullable = true, length = 45)
    public String getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(String requesttime) {
        this.requesttime = requesttime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SettingHistoryEntity that = (SettingHistoryEntity) o;

        if (id != that.id) return false;
        if (settingid != null ? !settingid.equals(that.settingid) : that.settingid != null) return false;
        if (dpid != null ? !dpid.equals(that.dpid) : that.dpid != null) return false;
        if (deploied != null ? !deploied.equals(that.deploied) : that.deploied != null) return false;
        if (requesttime != null ? !requesttime.equals(that.requesttime) : that.requesttime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (settingid != null ? settingid.hashCode() : 0);
        result = 31 * result + (dpid != null ? dpid.hashCode() : 0);
        result = 31 * result + (deploied != null ? deploied.hashCode() : 0);
        result = 31 * result + (requesttime != null ? requesttime.hashCode() : 0);
        return result;
    }
}
