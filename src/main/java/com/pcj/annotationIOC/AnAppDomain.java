package com.pcj.annotationIOC;

import org.dom4j.DocumentException;
import java.lang.reflect.InvocationTargetException;


public class AnAppDomain {

    public static void main(String[] args) throws DocumentException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {

        AnApplication app = new AnApplication("AnApplication.xml");
        TestController controller  = (TestController) app.getBean("TestController","","");
        controller.add();
    }


}
