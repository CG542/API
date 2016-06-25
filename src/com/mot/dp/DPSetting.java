package com.mot.dp;

import com.mot.dp.entities.DpEntity;
import com.mot.dp.entities.SettingEntity;
import com.mot.dp.entities.SettingHistoryEntity;

import java.util.*;

/**
 * Created by mot on 4/21/16.
 */
public class DPSetting {

    public boolean uploadSetting(User u, String profilename, String setting) {
        int userID = u.getUserID();
        SettingEntity se = new SettingEntity();
        se.setUserid(userID);
        se.setProfilename(profilename);
        se.setSetting(setting);

        SettingEntity temp = null;
        for (SettingEntity entity : Cache.getSettingCache()){
            if(entity.getUserid().equals(se.getId())&&entity.getProfilename().equals(se.getProfilename())){
                temp=entity;
                break;
            }
        }

        if(temp!=null){
            Cache.getSettingCache().remove(temp);
            SQlUtil.delete(temp);
        }

        SQlUtil.save(se);
        Cache.getSettingCache().add(se);

        return true;

    }

    public List<SettingEntity> getAllSettins(User u) {

        List<SettingEntity> result = new ArrayList<>();

        for(SettingEntity entity : Cache.getSettingCache()){
            if(entity.getUserid().equals(u.getUserID())){
                result.add(entity);
            }
        }
        Collections.sort(result,new Comparator<SettingEntity>(){
            @Override
            public int compare(SettingEntity o1, SettingEntity o2) {
                return o1.getProfilename().compareTo(o2.getProfilename());
            }
        });
        return result;

    }

    private SettingEntity querySetting(User u, String profileName) {
        for(SettingEntity entity : Cache.getSettingCache()){
            if(entity.getUserid().equals(u.getUserID())&&entity.getProfilename().equals(profileName)){
                return entity;
            }
        }
        return null;

    }

    private SettingEntity querySetting(int id) {
        for(SettingEntity entity : Cache.getSettingCache()){
            if(entity.getId()==id){
                return entity;
            }
        }
        return null;

    }

    public int setDP(User u, String dpName, String profileName) {

        SettingEntity setting = querySetting(u, profileName);
        if (setting == null) {
            return 0;
        }

        DpEntity dp = new DP().getDPEntity(u, dpName);
        if (dp == null) {
            return 0;
        }

        SettingHistoryEntity she = new SettingHistoryEntity();
        she.setDeploied(false);
        she.setDpid(dp.getId());
        she.setRequesttime(TimeUtil.getCurrentTime());
        she.setSettingid(setting.getId());

        SQlUtil.save(she);
        Cache.getSettingHistoryCache().add(she);
        return she.getId();

    }


    public String getDPSetting(User u, String dpName) {
        DpEntity dpEntity = new DP().getDPEntity(u, dpName);
        if (dpEntity != null) {
            int dpID = dpEntity.getId();

            SettingHistoryEntity history = null;
            for (SettingHistoryEntity entity : Cache.getSettingHistoryCache()) {
                if (entity.getDpid().equals(dpID)) {
                    if (history == null) {
                        history = entity;
                    } else {
                        if (entity.getRequesttime().compareTo(history.getRequesttime()) > 0) {
                            history = entity;
                        }
                    }
                }
            }

            if (history != null) {
                if (!history.getDeploied()) {
                    history.setDeploied(true);
                    SQlUtil.save(history);

                    int setID = history.getSettingid();
                    return querySetting(setID).getSetting();
                }
            }

        }
        return "";
    }
}


