package com.pcj.annotationIOC;

@ExtService(value = "anUserService")
public class AnUserServiceImpl implements AnUserService {
    @Override
    public void add() {
        System.out.println("add");
    }
}
