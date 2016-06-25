package com.mot.dp;

import com.mot.dp.entities.UserEntity;


/**
 * Created by mot on 4/13/16.
 */
public class User {
    String name;
    String psw;
    public User(String name,String psw){
        this.name=name;
        this.psw=psw;
    }


    public UserEntity getEntity() {

        for (UserEntity e : Cache.getUserCache()) {
            if (e.getName().equals(this.name) && e.getPassword().equals(this.psw)) {
                return e;
            }
        }

        return null;
    }

    public int getUserID(){
        return getEntity().getId();
    }

    public boolean validateUser(){

        if(name ==null || psw ==null||name.isEmpty()||psw.isEmpty()){
            return false;
        }

        return getEntity()!=null;
    }

}
