package com.mot.dp;

import com.mot.dp.entities.DpEntity;
import com.mot.dp.entities.DpStatusEntity;


import java.util.ArrayList;
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

        SQlUtil.save(dpStatusEntity);
        Cache.getDpStatusCache().add(dpStatusEntity);
        return true;

    }

    public List<DpStatusEntity> getStatus(User u, String time){

        DP dp =new DP();
        List<DpEntity> alldp = dp.getAllDP(u);

        List<DpStatusEntity> result = new ArrayList<>();

        for(DpStatusEntity entity : Cache.getDpStatusCache()){
            int dpId = entity.getDpid();
            boolean hasThisDP = false;
            for (DpEntity d : alldp){
                if(d.getId()==dpId){
                    entity.setDPName(d.getName());
                    hasThisDP=true;
                    break;
                }
            }
            if(hasThisDP && entity.getReporttime().compareTo(time)>=0){
                result.add(entity);
            }
        }

        return  result;

    }
}
