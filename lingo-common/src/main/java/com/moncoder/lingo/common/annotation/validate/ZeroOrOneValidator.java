package com.moncoder.lingo.common.annotation.validate;

import com.moncoder.lingo.common.annotation.ZeroOrOne;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Moncoder
 * @version 1.0
 * @description 0或者1字段验证器实现
 * @date 2024/3/28 14:10
 */
public class ZeroOrOneValidator implements ConstraintValidator<ZeroOrOne,Byte> {
    @Override
    public boolean isValid(Byte value, ConstraintValidatorContext context) {
        return value != null && (value == 0 || value == 1);
    }
}
