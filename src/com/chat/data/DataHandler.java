package com.chat.data;

import com.chat.data.msg.CacheMsgAccessor;
import com.chat.data.msg.DBMsgAccessor;
import com.chat.data.msg.IMsgAccessor;
import com.chat.data.user.CacheUserAccessor;
import com.chat.data.user.DBUserAccessor;
import com.chat.data.user.IUserAccessor;
import com.chat.model.MsgEntity;
import com.chat.model.UserEntity;

import java.util.List;

/**
 * Created by mot on 8/10/16.
 */
public class DataHandler{

    IMsgAccessor msgAccessor;
    IUserAccessor userAccessor;

    private DataHandler(){
        msgAccessor=new CacheMsgAccessor();
        msgAccessor.setNextAccessor(new DBMsgAccessor());

        userAccessor=new CacheUserAccessor();
        userAccessor.setNextAccessor(new DBUserAccessor());
    }

    public static DataHandler instance = new DataHandler();

    public static DataHandler getInstance(){
        return instance;
    }


    public void addMsgEntity(MsgEntity msgEntity) {
        msgAccessor.addMsgEntity(msgEntity);
    }


    public List<MsgEntity> getAllMsgEntity() {
        return msgAccessor.getAllMsgEntity();
    }


    public void addUserEntity(UserEntity userEntity) {
        userAccessor.addUserEntity(userEntity);
    }


    public List<UserEntity> getAllUserEntity() {
        return userAccessor.getAllUserEntity();
    }

    public String clear() {
        return msgAccessor.clear();
    }

}
