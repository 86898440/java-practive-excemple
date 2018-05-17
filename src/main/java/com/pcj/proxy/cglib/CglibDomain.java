package com.pcj.proxy.cglib;

public class CglibDomain {
    public static void main(String[] args){
        CglibSubject subject = new CglibSubject();
        CglibSubjectHandle handle = new CglibSubjectHandle();
        CglibSubject proxy = (CglibSubject)handle.bind(subject);
        proxy.doSomething();
    }
}
