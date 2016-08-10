package com.chat.data.msg;

import com.chat.data.IAccessor;
import com.chat.model.MsgEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mot on 8/10/16.
 */
public class DBMsgAccessor implements IMsgAccessor{

    IAccessor nextAccessor;
    List<MsgEntity> db = new ArrayList<>();

    @Override
    public void addMsgEntity(MsgEntity msgEntity) {
        db.add(msgEntity);

        if(nextAccessor!=null && nextAccessor instanceof IMsgAccessor){
            ((IMsgAccessor)nextAccessor).addMsgEntity(msgEntity);
        }
    }

    @Override
    public List<MsgEntity> getAllMsgEntity() {
        QueryFromDB();
        return db;
    }

    @Override
    public String clear() {
        int n = db.size();
        db.clear();
        if(nextAccessor!=null && nextAccessor instanceof IMsgAccessor){
            ((IMsgAccessor)nextAccessor).clear();
        }
        return "Clear "+String.valueOf(n)+" msgs. ";
    }

    @Override
    public void setNextAccessor(IAccessor accessor) {
        nextAccessor=accessor;
    }

    private void QueryFromDB(){
        //No DB here yet
    }
}
