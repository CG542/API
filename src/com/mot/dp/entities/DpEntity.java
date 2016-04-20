package com.mot.dp.entities;

import javax.persistence.*;

/**
 * Created by mot on 4/13/16.
 */
@Entity
@Table(name = "DP", schema = "DP", catalog = "")
public class DpEntity {
    private int id;
    private String name;
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
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        DpEntity dpEntity = (DpEntity) o;


        if (name != null ? !name.equals(dpEntity.name) : dpEntity.name != null) return false;
        if (userid != null ? !userid.equals(dpEntity.userid) : dpEntity.userid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        return result;
    }
}
