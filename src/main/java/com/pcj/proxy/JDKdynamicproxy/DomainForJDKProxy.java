package com.pcj.proxy.JDKdynamicproxy;

public class DomainForJDKProxy {
    public static void main(String[] args) {
        JDKSubject subject = new JDKSubjectImlpl();
        ProxyHandle proxyHandle = new ProxyHandle();
        JDKSubject proxy = (JDKSubject)proxyHandle.bind(subject);
        proxy.doSomething();
    }
}
