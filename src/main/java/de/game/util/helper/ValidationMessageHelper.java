package de.game.util.helper;

import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidationMessageHelper {

    private final MessageSource messageSource;

    public void addFieldError (ConstraintValidatorContext ctx, String field, String messageKey, Object... args) {
        ctx.disableDefaultConstraintViolation();
        String msg = messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
        ctx.buildConstraintViolationWithTemplate(msg)
                .addPropertyNode(field)
                .addConstraintViolation();
    }
}
