package com.pcj.XMLIOC;

import org.dom4j.DocumentException;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class XmlIOCDomain {
    public static void main(String[] args) throws DocumentException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        XMLApplication application = new XMLApplication("application.xml");
        UserService userService = (UserService)application.getBeanById("userService1");
        UserService userService2 = (UserService)application.getBeanByName("userService");
        UserService userService3 = (UserService)application.getBeanByType("com.pcj.XMLIOC.UserService");
        System.out.println(userService.equals(userService2)+"  "+userService.equals(userService3));
    }
}
