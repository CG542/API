package com.mot.dp;

import com.mot.dp.entities.DpEntity;
import com.mot.dp.entities.DpStatusEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by mot on 4/14/16.
 */
public class DPStatus {

    public boolean uploadStatus(User u, int dpID, String status,String type) {
        DpStatusEntity dpStatusEntity = new DpStatusEntity();
        dpStatusEntity.setDpid(dpID);
        dpStatusEntity.setStatus(status);
        dpStatusEntity.setType(type);
        dpStatusEntity.setReporttime(TimeUtil.getCurrentTime());
        Session session = HibernateUtil.getSession();
        try {

            Transaction t = session.beginTransaction();
            session.save(dpStatusEntity);
            t.commit();
            return true;
        }
        finally {
            session.close();

        }

    }

    public List<DpStatusEntity> getStatus(User u, int dpID, String time){
        Session session=null;
        try {
            session = HibernateUtil.getSession();
            Criteria c = session.createCriteria(DpStatusEntity.class);
            if(dpID>0){
                c.add(Restrictions.eq("dpid", dpID));
            }
            else{
                DP dp = new DP();
                Disjunction or = Restrictions.disjunction();
                for(DpEntity dpe :dp.getAllDP(u)){
                   or.add(Restrictions.eq("dpid",dpe.getId()));
                }
                c.add(or);
            }
            //c.add(Restrictions.eq("reporttime", time));
            c.add(Restrictions.ge("reporttime", time));


            List<DpStatusEntity> queryResult = c.list();

            return queryResult;
        }
        catch (Exception ex){
            System.out.print(ex.getMessage());
            return null;
        }
        finally {
            if(session!=null)
                session.close();
        }
    }
}
