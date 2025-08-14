package de.game.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;
import java.util.Objects;

@RequiredArgsConstructor

public class WebUtils {
    public static final String MSG_SUCCESS = "MSG_SUCCESS";
    public static final String MSG_INFO = "MSG_INFO";
    public static final String MSG_ERROR = "MSG_ERROR";
    private static MessageSource messageSource;
    private static LocaleResolver localeResolver;

    public static HttpServletRequest getRequest () {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static Locale getLocale () {
        return localeResolver.resolveLocale(getRequest());
    }

}
