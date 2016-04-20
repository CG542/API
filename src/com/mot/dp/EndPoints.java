package com.mot.dp;

import com.yxzhm.hibernate.*;
import com.yxzhm.motguest.WifiEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by mot on 4/7/16.
 */
@Path("/DP")
public class EndPoints {

    @POST
    @Path("/Login")
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(@FormParam("loginname") String loginname,
                        @FormParam("password") String password){
        User u = new User(loginname,password);
        return String.valueOf(u.validateUser());
    }

    @POST
    @Path("/Binding")
    @Consumes(MediaType.APPLICATION_JSON)
    public String bindingDP(@FormParam("loginname") String loginname,
                            @FormParam("password") String password,
                            @FormParam("dpname") String dpName) {
        User u = new User(loginname,password);
        DP dp=new DP();
        return String.valueOf(dp.binding(u,dpName));
    }

    @POST
    @Path("/UnBinding")
    @Consumes(MediaType.APPLICATION_JSON)
    public String unbindingDP(@FormParam("loginname") String loginname,
                            @FormParam("password") String password,
                            @FormParam("dpname") String dpName) {
        User u = new User(loginname,password);
        DP dp=new DP();
        return String.valueOf(dp.unBinding(u,dpName));
    }

    @GET
    @Path("/CheckDPExist")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkDPExist(@QueryParam("loginname") String loginname,
                              @QueryParam("password") String password,
                              @QueryParam("dpname") String dpName) {
        User u = new User(loginname,password);
        DP dp=new DP();
        return String.valueOf(dp.DPExist(u,dpName));
    }

    @POST
    @Path("/ReportDPStatus")
    @Consumes(MediaType.APPLICATION_JSON)
    public String reportDPStatus(@FormParam("loginname") String loginname,
                               @FormParam("password") String password,
                               @FormParam("status") String status) {

        return String.valueOf(dp.DPExist(u,dpName));
    }

    @GET
    @Path("/QueryDPStatus")
    @Produces(MediaType.TEXT_PLAIN)
    public String queryDPStatus(@QueryParam("loginname") String loginname,
                                 @QueryParam("password") String password,
                                 @QueryParam("dpname") String dpName) {

        return String.valueOf(dp.DPExist(u,dpName));
    }


    @GET
    @Path("/Help")
    @Produces("text/html")
    public String help(){

        return Help.getHelpContext();
    }
}
