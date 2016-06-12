package com.mot.dp;

import com.mot.dp.entities.DpEntity;
import com.mot.dp.entities.DpStatusEntity;
import com.mot.dp.entities.SettingEntity;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
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
                        @FormParam("password") String password) {
        User u = new User(loginname, password);
        return String.valueOf(u.validateUser());
    }

    @POST
    @Path("/Binding")
    @Consumes(MediaType.APPLICATION_JSON)
    public String bindingDP(@FormParam("loginname") String loginname,
                            @FormParam("password") String password,
                            @FormParam("dpname") String dpName) {
        User u = new User(loginname, password);
        DP dp = new DP();
        return String.valueOf(dp.binding(u, dpName));
    }

    @POST
    @Path("/UnBinding")
    @Consumes(MediaType.APPLICATION_JSON)
    public String unbindingDP(@FormParam("loginname") String loginname,
                              @FormParam("password") String password,
                              @FormParam("dpname") String dpName) {
        User u = new User(loginname, password);
        DP dp = new DP();
        return String.valueOf(dp.unBinding(u, dpName));
    }

    @GET
    @Path("/CheckDPExist")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkDPExist(@QueryParam("loginname") String loginname,
                               @QueryParam("password") String password,
                               @QueryParam("dpname") String dpName) {
        User u = new User(loginname, password);
        DP dp = new DP();
        return String.valueOf(dp.DPExist(u, dpName));
    }

    @GET
    @Path("/GetAllDPNames")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DpEntity> getAllDPNames(@QueryParam("loginname") String loginname,
                                        @QueryParam("password") String password) {
        User u = new User(loginname, password);
        List<DpEntity> result = new DP().getAllDP(u);
        return result;

    }

    @POST
    @Path("/UploadSetting")
    @Consumes(MediaType.APPLICATION_JSON)
    public String uploadSetting(@FormParam("loginname") String loginname,
                                @FormParam("password") String password,
                                @FormParam("profilename") String profilename,
                                @FormParam("setting") String setting) {
        User u = new User(loginname, password);
        DPSetting dpsetting = new DPSetting();

        return String.valueOf(dpsetting.uploadSetting(u, profilename, setting));
    }

    @GET
    @Path("/GetAllSettings")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SettingEntity> getAllSettings(@QueryParam("loginname") String loginname,
                                              @QueryParam("password") String password) {
        User u = new User(loginname, password);
        List<SettingEntity> result = new DPSetting().getAllSettins(u);
        return result;

    }


    @POST
    @Path("/SetDPConfig")
    @Consumes(MediaType.APPLICATION_JSON)
    public String setDPConfig(@FormParam("loginname") String loginname,
                              @FormParam("password") String password,
                              @FormParam("dpname") String dpname,
                              @FormParam("profilename") String profilename) {
        User u = new User(loginname, password);
        DPSetting dpsetting = new DPSetting();
        return String.valueOf(dpsetting.setDP(u, dpname, profilename));
    }

    @GET
    @Path("/GetDPConfig")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDPConfig(@QueryParam("loginname") String loginname,
                              @QueryParam("password") String password,
                              @QueryParam("dpname") String dpname) {
        User u = new User(loginname, password);
        DPSetting dpsetting = new DPSetting();
        return String.valueOf(dpsetting.getDPSetting(u, dpname));
    }


    @POST
    @Path("/ReportDPStatus")
    @Consumes(MediaType.APPLICATION_JSON)
    public String reportDPStatus(@FormParam("loginname") String loginname,
                                 @FormParam("password") String password,
                                 @FormParam("dpname") String dpname,
                                 @FormParam("type") String type,
                                 @FormParam("status") String status) {
        User u = new User(loginname, password);
        DP dp = new DP();
        if(dp.DPExist(u,dpname)) {
            int dpID = dp.getDPEntity(u, dpname).getId();
            DPStatus dpstatus = new DPStatus();
            return String.valueOf(dpstatus.uploadStatus(u, dpID, status, type));
        }
        else{
            return "false";
        }
    }

    @GET
    @Path("/QueryDPStatus")
    @Produces(MediaType.TEXT_PLAIN)
    public List<DpStatusEntity>  queryDPStatus(@QueryParam("loginname") String loginname,
                                @QueryParam("password") String password,
                                @QueryParam("dpname") String dpname,
                                @QueryParam("time") String time) {
        User u = new User(loginname, password);
        DP dp = new DP();
        DPStatus status = new DPStatus();
        List<DpStatusEntity> result = new ArrayList<>();
        if(!dpname.isEmpty()&&dp.DPExist(u,dpname)){
            result=status.getStatus(u,dp.getDPEntity(u,dpname).getId(),time);
        }else{
            result=status.getStatus(u,0,time);
        }


        return result;
    }

    @GET
    @Path("/Help")
    @Produces("text/html")
    public String help() {

        return Help.getHelpContext();
    }

}
