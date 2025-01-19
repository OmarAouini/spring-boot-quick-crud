package com.github.omaraouini.quickcrud.base.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author aouin
 * Date: 04/03/2023
 * Time: 17:07
 */
@Component
public class Translator {

    private static MessageSource messageSource;

    public Translator(@Qualifier("messageSource") MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static String toLocale(String code,Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code,args, locale);
    }

    public static String toLocale(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code,null, locale);
    }
}
