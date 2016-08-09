package com.chat.controller;

import com.chat.model.MsgEntity;
import com.chat.model.UserEntity;
import com.chat.util.CacheUtil;
import com.chat.util.TimeUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mot on 8/9/16.
 */
public class MsgController {

    public static void postMsg(UserEntity fromUser, UserEntity toUser, String content){
        MsgEntity msg = new MsgEntity();
        msg.setId(new Random().nextInt());
        msg.setFromID(fromUser.getId());
        msg.setToID(toUser.getId());
        msg.setContent(content);
        msg.setTime(TimeUtil.getCurrentTime());

        CacheUtil.addMsgEntity(msg);

    }

    public  static List<MsgEntity> getAllMsgs(UserEntity userEntity, String time){

        List<MsgEntity> result = new ArrayList<>();

        for(MsgEntity msg : CacheUtil.getAllMsgEntity()){
            if((msg.getToID()==userEntity.getId() || msg.getFromID()==userEntity.getId())
                && msg.getTime().compareTo(time)>=0){
                result.add(msg);
            }
        }

        return result;
    }
}
