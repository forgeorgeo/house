package com.house.daoImpl;

import com.house.dao.MessageService;

/**
 * @Author Tim
 * @Date 2020/11/13 15:44
 */
public class MessageServiceImpl implements MessageService {

    @Override
    public String getMessage() {
        return "hello world";
    }

}
