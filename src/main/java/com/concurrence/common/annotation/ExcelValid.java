package com.concurrence.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pengli
 * @Email pengli@fiberhome.com
 * @date 2022/12/1 15:44
 * 表格导入非空校验注解
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelValid {
    String value() default "该项有未填字段";

}
