package chn.hib.annotation;

import chn.hib.enums.SQLOperator;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Where {

    String column();

    SQLOperator operator() default SQLOperator.EQUAL;

    boolean isFuzzy() default false;

}