package com.pcj.annotationIOC;

import com.pcj.XMLIOC.ClassInfo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class AnApplication {
    String xmlPath;
    List<Element> nodes = new ArrayList();
    List<Class> classes ;
    static Map<ClassInfo ,Object> beanFactory = new HashMap();

    public AnApplication(String xmlPath) throws DocumentException, IllegalAccessException, NoSuchFieldException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        this.xmlPath =xmlPath;
        init();
    }

    public void init() throws DocumentException, IllegalAccessException, NoSuchFieldException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //读取xml
        readXml();
        //扫包
        scanPackage();
        //EXTAutowried注入
        injection();
    }
    public void injection() throws InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        List<Class> annotationClasses = getAnnotationClasses(classes, ExtService.class);
        putObj(annotationClasses,ExtService.class,beanFactory);

        List<Class> annotationClasses2 = getAnnotationClasses(classes, EXtController.class);
        putObj(annotationClasses2,EXtController.class,beanFactory);

        //注入
        for(Object o :beanFactory.values()){
            Field[] fields = o.getClass().getDeclaredFields();
            for(Field filed :fields){
                EXTAutowried annotation = filed.getAnnotation(EXTAutowried.class);
                if(annotation!=null){
//                System.out.println(filed.getName());
//                System.out.println( filed.getType().getName());
                    String typeName = filed.getType().getName();
                    for(ClassInfo info : beanFactory.keySet()){
                        if(info.getType().equals(typeName)||info.getType().equals(typeName+"Impl")){
                            setProperty(o,filed.getName(),beanFactory.get(info));
                        }
                    }

                }
            }
        }

    }
    public void scanPackage(){
        String packageName = getPackage();
        classes = ClassUtils.scanPackage(packageName);
    }
    public void readXml() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(AnApplication.class.getClassLoader().getResourceAsStream(xmlPath));
        getlements(document.getRootElement());
    }
    public String getPackage(){
        for(Element e:nodes){
            if(e.getName().equals("component-scan")){
                return e.attribute("base-package").getValue();
            }
        }
        return null;
    }
    public void getlements(Element rootElement){
        Iterator<Element> elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()){
            Element element = elementIterator.next();
            nodes.add(element);
            getlements(element);
        }
    }

    public  Object getBean(String beanId,String beanType,String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        for(ClassInfo info:beanFactory.keySet()){
            if(info.getId().equals(beanId)){
                return beanFactory.get(info);
            }
            if(info.getType().equals(beanType)){
                return beanFactory.get(info);
            }
            if(info.getName().equals(beanName)){
                return beanFactory.get(info);
            }
        }
        return null;
    }

    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //假如知道类型的情况下
        Class c1 = Class.forName("com.pcj.annotationIOC.AnUserserviceImpl");
        Class c2 = Class.forName("com.pcj.annotationIOC.TestController");
        Object o1 = c1.newInstance();
        Object o2 = c2.newInstance();
        Field[] fields = c2.getDeclaredFields();
        for(Field filed :fields){
            EXTAutowried annotation = filed.getAnnotation(EXTAutowried.class);
            if(annotation!=null){
//                System.out.println(filed.getName());
//                System.out.println( filed.getType().getName());
                setProperty(o2,filed.getName(),o1);
            }
        }
        ((TestController) o2).add();
    }
    // 该方法的参数列表是一个类的 类名、成员变量、给变量的赋值
    public static void setProperty(Object obj, String PropertyName, Object value)
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {

        // 获取obj类的字节文件对象
        Class c = obj.getClass();

        // 获取该类的成员变量
        Field f = c.getDeclaredField(PropertyName);

        // 取消语言访问检查
        f.setAccessible(true);

        // 给变量赋值
        f.set(obj, value);
    }

    public static Map putObj(List<Class> annotationClasses, Class annotation, Map map) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        for(Class c:annotationClasses){
            ClassInfo info = new ClassInfo();
            Annotation anno = c.getAnnotation(annotation);
            info.setName(getAnnotationFile(anno).get("value"));
            info.setId(c.getName().substring(c.getName().lastIndexOf(".")+1));
            info.setType(c.getTypeName());
            Constructor constructor = c.getConstructor();
            constructor.setAccessible(true);
            map.put(info,constructor.newInstance());
        }
        return map;
    }
    /**
     *
     * @param classes 所有class
     * @param annotation  注解class
     * @return 找出包含注解的class
     */
    public static List<Class> getAnnotationClasses(List<Class> classes, Class annotation){
        List list = new ArrayList();
        for(Class c :classes){
            Annotation anno = c.getAnnotation(annotation);
            if(anno != null){
                list.add(c);
            }
        }
        return list;
    }

    /**
     *
     * @param anno
     * @return 获取注解的属性和值
     */
    public static Map<String,String> getAnnotationFile(Annotation anno){
        Map map = new HashMap<>();
        Method[] met = anno.annotationType().getDeclaredMethods();
        for(Method me : met ){
            if(!me.isAccessible()){
                me.setAccessible(true);
            }
            try {
                map.put(me.getName(),me.invoke(anno));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
