package com.chat.data.msg;

import com.chat.data.IAccessor;
import com.chat.model.MsgEntity;

import java.util.List;

/**
 * Created by mot on 8/10/16.
 */
public interface IMsgAccessor extends IAccessor {
    public void addMsgEntity(MsgEntity msgEntity);
    public List<MsgEntity> getAllMsgEntity();
    public String clear();
}
