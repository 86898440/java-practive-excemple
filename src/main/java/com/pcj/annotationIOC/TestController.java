package com.pcj.annotationIOC;

@EXtController(value = "hhh")
public class TestController {

    @EXTAutowried(value = "")
    private AnUserService service;

    @EXTAutowried(value = "")
    private TestServiceImpl testService;

    public void add(){
        service.add();
        testService.test();
    }
}
