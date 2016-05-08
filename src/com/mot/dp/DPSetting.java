package com.mot.dp;

import com.mot.dp.entities.SettingEntity;
import com.mot.dp.entities.SettingHistoryEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by mot on 4/21/16.
 */
public class DPSetting {
    public boolean uploadSetting(User u,String name,String setting){
        int userID=u.getUserID();
        SettingEntity se=new SettingEntity();
        se.setUserid(userID);
        se.setProfilename(name);
        se.setSetting(setting);

        Session session = HibernateUtil.getSession();
        Criteria c = session.createCriteria(SettingEntity.class);
        c.add(Restrictions.eq("userid", se.getUserid()));
        c.add(Restrictions.eq("profilename", se.getProfilename()));
        c.add(Restrictions.eq("setting", se.getSetting()));

        List<SettingEntity> queryResult = c.list();
        if (queryResult.size() > 0) {
            return true;
        }
        else{
            Transaction t = session.beginTransaction();
            session.save(se);
            t.commit();
        }
        return true;
    }

    public boolean setDP(User u, String dpName,String profileName){
        SettingHistoryEntity she=new SettingHistoryEntity();
        she.setDeploied(false);
        she.setDpid(new DP().getDPEntity(u,dpName).getId());
        she.setRequesttime(TimeUtil.getCurrentTime());

        return true;
    }
}


