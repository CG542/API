package com.mot.dp.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mot on 4/13/16.
 */
@Entity
@Table(name = "DPStatus", schema = "DP", catalog = "")
@XmlRootElement
public class DpStatusEntity {
    private int id;
    private Integer dpid;
    private String status;
    private String reporttime;
    private String type;
    private String dpname;

    @Id
    @Column(name = "id", nullable = false)
    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "dpid", nullable = true)
    @XmlElement
    public Integer getDpid() {
        return dpid;
    }

    public void setDpid(Integer dpid) {
        this.dpid = dpid;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 90)
    @XmlElement
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "reporttime", nullable = true, length = 45)
    @XmlElement
    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 20)
    @XmlElement
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public String getDPName(){return dpname;}

    public void setDPName(String dpname) { this.dpname=dpname; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DpStatusEntity that = (DpStatusEntity) o;

        if (id != that.id) return false;
        if (dpid != null ? !dpid.equals(that.dpid) : that.dpid != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (reporttime != null ? !reporttime.equals(that.reporttime) : that.reporttime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dpid != null ? dpid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (reporttime != null ? reporttime.hashCode() : 0);
        return result;
    }
}
