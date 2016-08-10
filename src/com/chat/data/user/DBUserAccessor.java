package com.chat.data.user;

import com.chat.data.IAccessor;
import com.chat.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mot on 8/10/16.
 */
public class DBUserAccessor implements IUserAccessor {

    IAccessor nextAccessor;
    List<UserEntity> db=new ArrayList<>();

    @Override
    public void addUserEntity(UserEntity userEntity) {
        db.add(userEntity);

        if(nextAccessor!=null && nextAccessor instanceof IUserAccessor){
            ((IUserAccessor)nextAccessor).addUserEntity(userEntity);
        }
    }

    @Override
    public List<UserEntity> getAllUserEntity() {
        QueryFromDB();
        return db;
    }

    @Override
    public void setNextAccessor(IAccessor accessor) {
        nextAccessor=accessor;
    }

    private void QueryFromDB(){
        db.clear();
        
        UserEntity u1= new UserEntity();
        u1.setId(1);
        u1.setUserName("janny");
        u1.setPassword("123");

        UserEntity u2 = new UserEntity();
        u2.setId(2);
        u2.setUserName("yl");
        u2.setPassword("123");

        UserEntity u3= new UserEntity();
        u3.setId(3);
        u3.setUserName("lln");
        u3.setPassword("123");

        db.add(u1);
        db.add(u2);
        db.add(u3);

        for(UserEntity entity: db){
            entity.setFriendIDs(new ArrayList<Integer>());
            entity.getFriendIDs().add(1);
            entity.getFriendIDs().add(2);
            entity.getFriendIDs().add(3);
        }
    }
}
