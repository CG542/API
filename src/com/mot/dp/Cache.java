package com.mot.dp;

import com.mot.dp.entities.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 6/25/16.
 */
public class Cache {

    private static boolean IsLoaded = false;

    private static List<DpEntity> dpCache = new ArrayList<>();
    private static List<UserEntity> userCache = new ArrayList<>();
    private static List<DpStatusEntity> dpStatusCache = new ArrayList<>();
    private static List<SettingEntity> settingCache = new ArrayList<>();
    private static List<SettingHistoryEntity> settingHistoryCache = new ArrayList<>();

    public static List<DpEntity> getDpCache() {
        Load();
        return dpCache;
    }

    public static List<UserEntity> getUserCache() {
        Load();
        return userCache;
    }

    public static List<DpStatusEntity> getDpStatusCache() {
        Load();
        return dpStatusCache;
    }

    public static List<SettingEntity> getSettingCache() {
        Load();
        return settingCache;
    }

    public static List<SettingHistoryEntity> getSettingHistoryCache() {
        Load();
        return settingHistoryCache;
    }



    private static void Load(){
        if(!IsLoaded){

            Session session = HibernateUtil.getSession();
            try {
                Criteria c = session.createCriteria(DpEntity.class);
                dpCache = c.list();

                c = session.createCriteria(UserEntity.class);
                userCache=c.list();

                c = session.createCriteria(DpStatusEntity.class);
                dpStatusCache=c.list();

                c= session.createCriteria(SettingEntity.class);
                settingCache=c.list();

                c=session.createCriteria(SettingHistoryEntity.class);
                settingHistoryCache=c.list();

            }
            finally {
                session.close();
            }

        }

        IsLoaded=true;
    }

    public static void ReLoad(){
        IsLoaded=false;
        Load();
    }



}
