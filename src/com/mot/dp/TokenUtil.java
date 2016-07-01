package com.mot.dp;

import com.mot.dp.entities.UserEntity;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by mot on 7/1/16.
 */
public class TokenUtil {
    private static HashMap<String,UserEntity> tokens = new HashMap<String,UserEntity>();

    public static String VerifyUser(String name,String psw){
        User u = new User(name, psw);
        if(u.validateUser()){
            UUID uuid = UUID.randomUUID();
            tokens.put(uuid.toString(),u.getEntity());
            return uuid.toString();
        }else {
            return "";
        }
    }

    public static UserEntity GetUserInfo(String uuid){
        if(tokens!=null && tokens.containsKey(uuid)){
            return tokens.get(uuid);
        }
        else{
            return null;
        }
    }
}
