package com.pcj.annotationIOC;

@ExtService(value = "testServiceImpl")
public class TestServiceImpl {
    public void test(){
        System.out.println("test");
    }
}
