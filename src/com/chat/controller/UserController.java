package com.chat.controller;


import com.chat.model.UserEntity;
import com.chat.util.CacheUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by mot on 8/9/16.
 */
public class UserController {

    public static boolean isValid(String name, String password){
        return getEntity(name,password)!=null;
    }

    public static UserEntity getEntity(String name,String password){
        for(UserEntity entity: CacheUtil.getAllUserEntity()){
            if(entity.getUserName().equals(name)&&entity.getPassword().equals(password)){
                return entity;
            }
        }
        return  null;
    }

    public static UserEntity getEntity(int id){
        for(UserEntity entity: CacheUtil.getAllUserEntity()){
            if(entity.getId()==id){
                return entity;
            }
        }
        return null;
    }

    public static List<UserEntity> getAllFriends(UserEntity userEntity){
        List firends = userEntity.getFriendIDs();
        List<UserEntity> result = new ArrayList<>();

        for(UserEntity entity: CacheUtil.getAllUserEntity()){
            if(firends.contains(entity.getId()) && entity.getId()!=userEntity.getId()){
                result.add(entity);
            }
        }


        return result;
    }

    static {
        if(CacheUtil.getAllUserEntity().size()==0){
            createFakeData();
        }

    }

    private static void createFakeData(){
        UserEntity u1= new UserEntity();
        u1.setId(1);
        u1.setUserName("janny");
        u1.setPassword("123");

        UserEntity u2 = new UserEntity();
        u2.setId(2);
        u2.setUserName("yl");
        u2.setPassword("123");

        UserEntity u3= new UserEntity();
        u3.setId(3);
        u3.setUserName("lln");
        u3.setPassword("123");

        CacheUtil.addUserEntity(u1);
        CacheUtil.addUserEntity(u2);
        CacheUtil.addUserEntity(u3);

        for(UserEntity entity: CacheUtil.getAllUserEntity()){
            entity.setFriendIDs(new ArrayList<Integer>());
            entity.getFriendIDs().add(1);
            entity.getFriendIDs().add(2);
            entity.getFriendIDs().add(3);
        }
    }
}
