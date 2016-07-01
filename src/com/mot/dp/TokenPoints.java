package com.mot.dp;

import com.mot.dp.entities.DpEntity;
import com.mot.dp.entities.DpStatusEntity;
import com.mot.dp.entities.SettingEntity;
import com.mot.dp.entities.UserEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by mot on 7/1/16.
 */
@Path("/TokenDP")
public class TokenPoints {
    @POST
    @Path("/Login")
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(@FormParam("loginname") String loginname,
                        @FormParam("password") String password) {

        return TokenUtil.VerifyUser(loginname,password);
    }

    @GET
    @Path("/GetAllDPNames")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DpEntity> getAllDPNames(@QueryParam("token") String uuid) {
        UserEntity u = TokenUtil.GetUserInfo(uuid);
        if(u!=null){
            EndPoints ep =new EndPoints();
            return ep.getAllDPNames(u.getName(),u.getPassword());
        }

        return null;

    }

    @POST
    @Path("/UploadSetting")
    @Consumes(MediaType.APPLICATION_JSON)
    public String uploadSetting(@FormParam("token") String uuid,
                                @FormParam("profilename") String profilename,
                                @FormParam("setting") String setting) {
        UserEntity u = TokenUtil.GetUserInfo(uuid);
        if(u!=null){
            EndPoints ep =new EndPoints();
            return ep.uploadSetting(u.getName(),u.getPassword(),profilename,setting);
        }


        return "UnAuth";
    }

    @POST
    @Path("/DelSetting")
    @Consumes(MediaType.APPLICATION_JSON)
    public String delSetting(@FormParam("token") String uuid,
                             @FormParam("profilename") String profilename){

        UserEntity u = TokenUtil.GetUserInfo(uuid);
        if(u!=null){
            EndPoints ep =new EndPoints();
            return ep.delSetting(u.getName(),u.getPassword(),profilename);
        }
        return "UnAuth";
    }

    @GET
    @Path("/GetAllSettings")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SettingEntity> getAllSettings(@FormParam("token") String uuid) {

        UserEntity u = TokenUtil.GetUserInfo(uuid);
        if(u!=null){
            EndPoints ep =new EndPoints();
            return ep.getAllSettings(u.getName(),u.getPassword());
        }
        return null;
    }


    @POST
    @Path("/SetDPConfig")
    @Consumes(MediaType.APPLICATION_JSON)
    public String setDPConfig(@FormParam("token") String uuid,
                              @FormParam("dpname") String dpname,
                              @FormParam("profilename") String profilename) {

        UserEntity u = TokenUtil.GetUserInfo(uuid);
        if(u!=null){
            EndPoints ep =new EndPoints();
            return ep.setDPConfig(u.getName(),u.getPassword(),dpname,profilename);
        }
        return "UnAuth";
    }

    @GET
    @Path("/GetDPConfig")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDPConfig(@QueryParam("token") String uuid,
                              @QueryParam("password") String password,
                              @QueryParam("dpname") String dpname) {

        UserEntity u = TokenUtil.GetUserInfo(uuid);
        if(u!=null){
            EndPoints ep =new EndPoints();
            return ep.getDPConfig(u.getName(),u.getPassword(),dpname);
        }
        return "UnAuth";

    }



    @GET
    @Path("/QueryDPStatus")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DpStatusEntity>  queryDPStatus(@QueryParam("token") String uuid,
                                               @QueryParam("time") String time) {

        UserEntity u = TokenUtil.GetUserInfo(uuid);
        if(u!=null){
            EndPoints ep =new EndPoints();
            return ep.queryDPStatus(u.getName(),u.getPassword(),time);
        }
        return null;

    }

}
