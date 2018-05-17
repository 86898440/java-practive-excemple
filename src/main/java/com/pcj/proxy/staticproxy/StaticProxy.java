package com.pcj.proxy.staticproxy;

public class StaticProxy implements StaticSubject{
    StaticSubject subject ;

    public StaticProxy(StaticSubject subject){
        this.subject = subject;
    }
    @Override
    public void doSomething() {
        System.out.println("实现增强");
        subject.doSomething();
    }
}
