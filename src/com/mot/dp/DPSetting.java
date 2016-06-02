package com.mot.dp;

import com.mot.dp.entities.DpEntity;
import com.mot.dp.entities.SettingEntity;
import com.mot.dp.entities.SettingHistoryEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.InterbaseDialect;

import java.util.List;
import java.util.Set;

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

        Session session = HibernateUtil.getSession();
        try {
            Criteria c = session.createCriteria(SettingEntity.class);
            c.add(Restrictions.eq("userid", se.getUserid()));
            c.add(Restrictions.eq("profilename", se.getProfilename()));


            List<SettingEntity> queryResult = c.list();
            if (queryResult.size() > 0) {
                Transaction t = session.beginTransaction();
                for (SettingEntity s : queryResult) {
                    session.delete(s);
                }
                t.commit();
            }

            Transaction t = session.beginTransaction();
            session.save(se);
            t.commit();

            return true;
        }
        finally {
            session.close();
        }

    }

    public List<SettingEntity> getAllSettins(User u) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria c = session.createCriteria(SettingEntity.class);
            c.add(Restrictions.eq("userid", u.getUserID()));
            c.addOrder(Order.asc("profilename"));
            List<SettingEntity> queryResult = c.list();

            return queryResult;
        }
        finally {
            session.close();
        }
    }

    private SettingEntity querySetting(User u, String profileName) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria c = session.createCriteria(SettingEntity.class);
            c.add(Restrictions.eq("userid", u.getUserID()));
            c.add(Restrictions.eq("profilename", profileName));


            List<SettingEntity> queryResult = c.list();

            if (queryResult.size() > 0) {
                return queryResult.get(0);
            } else {
                return null;
            }
        }
        finally {
            session.close();
        }

    }

    private SettingEntity querySetting(int id) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria c = session.createCriteria(SettingEntity.class);
            c.add(Restrictions.eq("id", id));

            List<SettingEntity> queryResult = c.list();

            if (queryResult.size() > 0) {
                return queryResult.get(0);
            } else {
                return null;
            }
        }
        finally {
            session.close();
        }
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

        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();

            SettingHistoryEntity she = new SettingHistoryEntity();
            she.setDeploied(false);
            she.setDpid(dp.getId());
            she.setRequesttime(TimeUtil.getCurrentTime());
            she.setSettingid(setting.getId());

            session.save(she);
            int n = she.getId();
            session.getTransaction().commit();
            return she.getId();
        }
        finally {
            session.close();
        }
    }


    public String getDPSetting(User u, String dpName) {
        Session session = HibernateUtil.getSession();
        try {
            DpEntity dpEntity = new DP().getDPEntity(u, dpName);
            if (dpEntity != null) {
                int dpID = dpEntity.getId();

                Criteria c = session.createCriteria(SettingHistoryEntity.class);
                c.add(Restrictions.eq("dpid", dpID));
                // c.add(Restrictions.eq("deploied",false));
                c.addOrder(Order.desc("requesttime"));
                c.setMaxResults(1);

                List<SettingHistoryEntity> queryResult = c.list();
                if (queryResult.size() > 0) {

                    SettingHistoryEntity history = queryResult.get(0);
                    if (!history.getDeploied()) {
                        history.setDeploied(true);
                        Transaction t = session.beginTransaction();
                        session.save(history);
                        t.commit();

                        int setID = history.getSettingid();
                        return querySetting(setID).getSetting();
                    }
                }
            }
        }
        finally {
            session.close();
        }

        return "";
    }
}


