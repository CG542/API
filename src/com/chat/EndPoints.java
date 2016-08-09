package com.chat;



import com.chat.controller.MsgController;
import com.chat.controller.UserController;
import com.chat.model.MsgEntity;
import com.chat.model.UserEntity;
import com.chat.util.CacheUtil;
import com.chat.util.TokenUtil;



import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mot on 8/9/16.
 */
@Path("/Chat")
public class EndPoints {
    @POST
    @Path("/Login")
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(@FormParam("loginname") String loginname,
                        @FormParam("password") String password) {

        if(UserController.isValid(loginname,password)) {
            return TokenUtil.getToken(UserController.getEntity(loginname,password));
        }
        else{
            return "";
        }
    }

    @GET
    @Path("/GetAllFriends")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserEntity> getAllFriends(@QueryParam("UUID") String uuid) {

        UserEntity userEntity = TokenUtil.getUser(uuid);
        if(userEntity!=null){
            return UserController.getAllFriends(userEntity);
        }

       return new ArrayList<>();
    }

    @POST
    @Path("/PostMsg")
    @Consumes(MediaType.APPLICATION_JSON)
    public void PostMsg(@FormParam("UUID") String uuid,
                        @FormParam("ToID") String toID,
                        @FormParam("Content") String content) {
        UserEntity fromUser = TokenUtil.getUser(uuid);
        UserEntity toUser = UserController.getEntity(Integer.parseInt(toID));

        if(fromUser!=null && toUser!=null && !content.isEmpty()){
            MsgController.postMsg(fromUser,toUser,content);
        }
    }

    @GET
    @Path("/GetAllMsgs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MsgEntity> GetAllMsgs(@QueryParam("UUID") String uuid,
                                      @QueryParam("QueryTime") String time) {

        UserEntity userEntity = TokenUtil.getUser(uuid);
        if(userEntity!=null){
            return MsgController.getAllMsgs(userEntity,time);
        }
        return new ArrayList<>();
    }

    @GET
    @Path("/Reload")
    @Produces("text/html")
    public String reload(){

        String result= TokenUtil.clearTokens()+ CacheUtil.clearMsg();
        return result;
    }
}
