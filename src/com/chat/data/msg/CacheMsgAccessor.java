package com.chat.data.msg;

import com.chat.data.IAccessor;
import com.chat.model.MsgEntity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mot on 8/10/16.
 */
public class CacheMsgAccessor implements IMsgAccessor {

    List<MsgEntity> cache = new ArrayList<>();
    IAccessor nextAccessor;
    private boolean isLoad=false;



    @Override
    public void setNextAccessor(IAccessor accessor) {
        nextAccessor = accessor;
    }

    @Override
    public void addMsgEntity(MsgEntity msgEntity) {

        if(!isLoad) {
            getAllMsgEntity();
        }

        cache.add(msgEntity);

        if(nextAccessor!=null && nextAccessor instanceof IMsgAccessor){
            ((IMsgAccessor)nextAccessor).addMsgEntity(msgEntity);
        }
    }

    @Override
    public List<MsgEntity> getAllMsgEntity() {

        if(!isLoad){
            if(nextAccessor!=null && nextAccessor instanceof IMsgAccessor){
                cache.addAll(((IMsgAccessor)nextAccessor).getAllMsgEntity());
            }
            isLoad=true;
        }

        return cache;
    }

    @Override
    public String clear() {
        int n = cache.size();
        cache.clear();
        if(nextAccessor!=null && nextAccessor instanceof IMsgAccessor){
            ((IMsgAccessor)nextAccessor).clear();
        }
        return "Clear "+String.valueOf(n)+" msgs. ";
    }
}
