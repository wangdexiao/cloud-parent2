package com.study.base.mybatisplus.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(BaseConfig.class)
public @interface EnableBase {

}
