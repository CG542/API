package com.chat.data.user;

import com.chat.data.IAccessor;
import com.chat.model.UserEntity;

import java.util.List;

/**
 * Created by mot on 8/10/16.
 */
public interface IUserAccessor extends IAccessor {
    public void addUserEntity(UserEntity userEntity);
    public List<UserEntity> getAllUserEntity();
}
