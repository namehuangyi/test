package com.powernode.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 注解的注解叫做元注解
// 标注者Component注解可以出现在类上
@Target(ElementType.TYPE)
// RetentionPolicy.RUNTIME：标注着Component注解可以保留在Class文件中，并且可以被反射机制读取到
// RetentionPolicy.SOURCE : 标注着Component注解可以保留在java源文件中
// RetentionPolicy.CLASS : 标注着Component注解可以保留在Class文件中，并且不可以被反射机制读取到
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    // 定义注解的属性
    String value();
}
