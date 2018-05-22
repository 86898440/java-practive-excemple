package com.pcj.annotationIOC;
import java.lang.annotation.*;
//注解是否将包含在JavaDoc中
@Documented
//–什么时候使用该注解
@Retention(RetentionPolicy.RUNTIME)
//–注解用于什么地方
@Target( ElementType.FIELD)
// – 是否允许子类继承该注解
@Inherited
public @interface EXTAutowried {
    String value() default "";
}
