package com.pcj.XMLIOC;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class XMLApplication {
    String xmlpath;
    List<Element> nodes = new ArrayList();
    Map<ClassInfo , Object> cache = new HashMap<ClassInfo , Object>();
    public XMLApplication(String xmlpath) throws DocumentException {
        this.xmlpath = xmlpath;
        readXML();
    }
    public void readXML() throws DocumentException {
       InputStream inputStream =  XMLApplication.class.getClassLoader().getResourceAsStream(xmlpath);
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element rootElement = document.getRootElement();
        getlements(rootElement);
    }

    public Object getBean(String beanId,String beanType,String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        for(ClassInfo info:cache.keySet()){
            if(info.getId().equals(beanId)){
             return cache.get(info);
            }
            if(info.getType().equals(beanType)){
                return cache.get(info);
            }
            if(info.getName().equals(beanName)){
                return cache.get(info);
            }
        }
        String beanClass = getBeanPath(beanId);
        Class clazz = Class.forName(beanClass);
        Constructor con = clazz.getDeclaredConstructor();
        con.setAccessible(true);
        Object object = con.newInstance();
        String typeName = clazz.getGenericInterfaces()[0].getTypeName();
        String name = typeName.substring(typeName.lastIndexOf(".")+1);
        ClassInfo classInfo = new ClassInfo();
        classInfo.setId(beanId);
        name = name.substring(0,1).toLowerCase()+name.substring(1);
        classInfo.setName(name);
        classInfo.setType(typeName);
        cache.put(classInfo,object);
        return object;
    }

    public Object getBeanByType(String typeName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return getBean(null,typeName,null);
    }
    public Object getBeanById(String beanId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return getBean(beanId,null,null);
    }
    public Object getBeanByName(String beanName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return getBean(null,null,beanName);
    }
    public void getlements(Element rootElement){
        Iterator<Element> elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()){
            Element element = elementIterator.next();
            nodes.add(element);
            getlements(element);
        }
    }

    public String getBeanPath(String beanId){
        for(Element element:nodes){ if("bean".equals(element.getName())){
                if(beanId.equals(element.attribute("id").getValue())){
                   return  element.attribute("class").getValue();
                }
            }
        }
        return null;
    }


}
