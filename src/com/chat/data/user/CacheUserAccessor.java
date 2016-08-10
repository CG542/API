package com.chat.data.user;

import com.chat.data.IAccessor;
import com.chat.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mot on 8/10/16.
 */
public class CacheUserAccessor implements IUserAccessor {

    IAccessor nextAccessor;
    boolean isLoad=false;
    List<UserEntity> cache = new ArrayList<>();

    @Override
    public void addUserEntity(UserEntity userEntity) {
        if(!isLoad) {
            getAllUserEntity();
        }

        cache.add(userEntity);

        if(nextAccessor!=null && nextAccessor instanceof IUserAccessor){
            ((IUserAccessor)nextAccessor).addUserEntity(userEntity);
        }
    }

    @Override
    public List<UserEntity> getAllUserEntity() {
        if(!isLoad){
            if(nextAccessor!=null && nextAccessor instanceof IUserAccessor){
                cache.addAll(((IUserAccessor)nextAccessor).getAllUserEntity());
            }
            isLoad=true;
        }

        return cache;
    }

    @Override
    public void setNextAccessor(IAccessor accessor) {
        nextAccessor=accessor;
    }
}
