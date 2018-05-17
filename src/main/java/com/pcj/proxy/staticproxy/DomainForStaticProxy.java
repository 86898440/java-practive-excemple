package com.pcj.proxy.staticproxy;

public class DomainForStaticProxy {
    public static void main(String[] args) {
        StaticSubject subject = new StaticSubjectImpl();
        StaticProxy proxy = new StaticProxy(subject);
        proxy.doSomething();
    }
}
