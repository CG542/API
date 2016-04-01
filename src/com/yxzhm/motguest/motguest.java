package com.yxzhm.motguest;

import com.yxzhm.hibernate.HibernateUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by mot on 4/1/16.
 */
@Path("/motguest")
public class motguest {
    private String date;
    private String pwd;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getWifiPsw() {

        return "Hello Jersey";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void uploadWifiPsw(@FormParam("date") String date, @FormParam("pwd") String pwd) {
        this.date = date;
        this.pwd = pwd;
        HibernateUtil.getSession();

    }
}
