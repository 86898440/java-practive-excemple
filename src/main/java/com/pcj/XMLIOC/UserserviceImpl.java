package com.pcj.XMLIOC;

import java.io.Serializable;

public class UserserviceImpl implements UserService {
    private UserserviceImpl(){
        System.out.println("初始化私有构造函数");
    }
    @Override
    public void add() {
        System.out.println("add");
    }
}
