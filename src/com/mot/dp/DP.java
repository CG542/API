package com.mot.dp;

import com.mot.dp.entities.DpEntity;

import java.util.*;

/**
 * Created by mot on 4/14/16.
 */
public class DP {


    public DpEntity getDPEntity(User u,String dpName){
        int userID=u.getUserID();

        for(DpEntity entity : Cache.getDpCache()) {
            if(entity.getUserid().equals(userID) && entity.getName().equals(dpName)){
                return entity;
            }
        }

        return null;

    }

    public List<DpEntity> getAllDP(User u){
        List<DpEntity> result = new ArrayList<>();

        for(DpEntity entity : Cache.getDpCache()){
            if(entity.getUserid().equals(u.getUserID())){
                result.add(entity);
            }
        }
        Collections.sort(result,new Comparator<DpEntity>(){
            @Override
            public int compare(DpEntity o1, DpEntity o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return result;

    }

    public boolean binding(User u, String dpName){
        if(DPExist(u,dpName))
            return false;

        int userID=u.getUserID();
        DpEntity entity=new DpEntity();
        entity.setUserid(userID);
        entity.setName(dpName);

        SQlUtil.save(entity);
        Cache.getDpCache().add(entity);
        return true;
    }

    public boolean unBinding(User u, String dpName){
        if(!DPExist(u,dpName))
            return false;

        DpEntity entity = getDPEntity(u,dpName);
        if(entity!=null){
            Cache.getDpCache().remove(entity);
            SQlUtil.delete(entity);
        }

        return true;
    }


    public boolean DPExist(User u,String dpName){

        return getDPEntity(u,dpName)!=null;
    }
}
