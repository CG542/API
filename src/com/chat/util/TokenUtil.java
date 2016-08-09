package com.chat.util;

import com.chat.controller.UserController;
import com.chat.model.UserEntity;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by mot on 8/9/16.
 */
public class TokenUtil {

    private static HashMap<String,UserEntity> tokens = new HashMap<String,UserEntity>();

    public static String getToken(UserEntity user){
        UUID uuid = UUID.randomUUID();
        tokens.put(uuid.toString(),user);
        return uuid.toString();
    }

    public static UserEntity getUser(String uuid){
        if(tokens!=null && tokens.containsKey(uuid)){
            return tokens.get(uuid);
        }
        else{
            return null;
        }
    }

    public static String clearTokens(){
        int n = tokens.size();
        tokens.clear();
        return "Clear "+String.valueOf(n)+" tokens. ";
    }
}
