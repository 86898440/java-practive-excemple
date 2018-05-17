package com.pcj.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibSubjectHandle implements MethodInterceptor {

    Object target;

    public Object bind(Object target){
        this.target = target;
         Enhancer enhancer = new Enhancer();
         enhancer.setSuperclass(this.target.getClass());
         // 回调方法
         enhancer.setCallback(this);
         // 创建代理对象
         return enhancer.create();
    }




    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("实现增强");
        Object invoke = methodProxy.invokeSuper(obj,args);
        return invoke;
    }
}
