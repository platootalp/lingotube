package com.moncoder.lingo.common.annotation;

import com.moncoder.lingo.common.annotation.validate.ZeroOrOneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author moncoder
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ZeroOrOneValidator.class)
public @interface ZeroOrOne {

    String message() default "字段的值只能为0或者1";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
