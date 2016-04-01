package com.yxzhm.motguest;

import com.yxzhm.hibernate.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;

/**
 * Created by mot on 4/1/16.
 */
@Path("/motguest")
public class motguest {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getWifiPsw() {
        Session session = HibernateUtil.getSession();
        Criteria c = session.createCriteria(WifiEntity.class);
        c.addOrder(Order.desc("id"));
        c.setMaxResults(1);
        List<WifiEntity> queryResult = c.list();
        if(queryResult.size()>0){
            return queryResult.get(0).getPassword();
        }
        return "";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String uploadWifiPsw(@FormParam("date") String date, @FormParam("pwd") String pwd) {

        WifiEntity entity = new WifiEntity();

        entity.setWifidates(date);
        entity.setPassword(pwd);

        try {
            Session session = HibernateUtil.getSession();
            Criteria c = session.createCriteria(WifiEntity.class);
            c.add(Restrictions.eq("wifidates", date));
            c.add(Restrictions.eq("password", pwd));

            List<WifiEntity> queryResult = c.list();
            if (queryResult.size() > 0) {
                return "Existed";
            }

            Transaction t = session.beginTransaction();
            session.save(entity);
            t.commit();
        }
        catch (Exception e){
            return e.getMessage();
        }
        return "Success";

    }
}
