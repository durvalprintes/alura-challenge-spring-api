package br.com.alura.challenge.spring.api.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Util {

    private Util() {
        throw new IllegalStateException("Utility class");
    }

    public static String getText(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("bundle", new Locale("pt", "BR"));
        try {
            return MessageFormat.format(bundle.getString(key), (Object) null);
        } catch (Exception e) {
            return key;
        }
    }

}
