package com.mot.dp;


import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * Created by Andy on 6/25/16.
 */
public class SQlUtil {


    public static void save(java.lang.Object entity){
        Session session =null;
        try {
            session = HibernateUtil.getSession();

            Transaction t = session.beginTransaction();
            session.save(entity);
            t.commit();
        }
        finally {
            if(session!=null)
                session.close();
        }
    }

    public static void delete(java.lang.Object entity){
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Transaction t = session.beginTransaction();
            session.delete(entity);
            t.commit();
        }
        finally {
            if(session!=null)
                session.close();
        }
    }
}
