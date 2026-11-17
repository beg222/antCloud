package com.antcloud.project.domain.vo.project.log;

import java.lang.annotation.*;

/**
 * @author beg
 * @date 2026/11/17
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ForUpdate {
    String fieldName() default "";
}
