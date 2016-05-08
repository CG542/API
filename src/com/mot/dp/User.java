package com.mot.dp;

import com.mot.dp.entities.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

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

    private static List<UserEntity> userCache=new ArrayList<>();
    public UserEntity getEntity(){

        UserEntity temp=new UserEntity();
        temp.setName(name);
        temp.setPassword(psw);

        if(userCache.contains(temp)){
            return userCache.get(userCache.indexOf(temp));
        }

        Session session = HibernateUtil.getSession();
        Criteria c = session.createCriteria(UserEntity.class);
        c.add(Restrictions.eq("name", name));
        c.add(Restrictions.eq("password", psw));

        List<UserEntity> queryResult = c.list();
        if(queryResult.size()>0){
            userCache.add(queryResult.get(0));
            return queryResult.get(0);
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
