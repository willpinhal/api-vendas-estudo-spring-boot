package com.github.willpinhal.apivendas.apivendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Configuration
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Autowired
@Qualifier("cachorro")
public @interface Cachorro {
}
