package com.mot.dp;

import com.mot.dp.entities.DpEntity;
import com.mot.dp.entities.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mot on 4/14/16.
 */
public class DP {


    private static List<DpEntity> dpCache=new ArrayList<>();

    public DpEntity getDPEntity(User u,String dpName){
        int userID=u.getUserID();

        DpEntity temp=new DpEntity();
        temp.setUserid(userID);
        temp.setName(dpName);
        if(dpCache.contains(temp)){
            return dpCache.get(dpCache.indexOf(temp));
        }

        Session session = HibernateUtil.getSession();
        Criteria c = session.createCriteria(DpEntity.class);
        c.add(Restrictions.eq("userid", userID));
        c.add(Restrictions.eq("name", dpName));

        List<DpEntity> queryResult = c.list();
        if(queryResult.size()>0){
            dpCache.add(queryResult.get(0));
            return queryResult.get(0);
        }
        return null;
    }

    public List<DpEntity> getAllDP(User u){
        Session session = HibernateUtil.getSession();
        Criteria c = session.createCriteria(DpEntity.class);
        c.add(Restrictions.eq("userid", u.getUserID()));

        List<DpEntity> queryResult = c.list();

        for(DpEntity dp :queryResult){
            if(!dpCache.contains(dp)){
                dpCache.add(dp);
            }
        }

        return queryResult;
    }

    public boolean binding(User u, String dpName){
        if(DPExist(u,dpName))
            return false;

        int userID=u.getUserID();
        DpEntity entity=new DpEntity();
        entity.setUserid(userID);
        entity.setName(dpName);

        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        session.save(entity);
        t.commit();
        return true;
    }

    public boolean unBinding(User u, String dpName){
        if(!DPExist(u,dpName))
            return false;
        DpEntity entity=getDPEntity(u,dpName);
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        session.delete(entity);
        t.commit();
        return true;
    }


    public boolean DPExist(User u,String dpName){

        return getDPEntity(u,dpName)!=null;
    }
}
