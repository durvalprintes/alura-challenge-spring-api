package br.com.alura.challenge.spring.api.util;

import java.beans.FeatureDescriptor;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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

    public static String[] getBlankPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null
                        || wrappedSource.getPropertyValue(propertyName).toString().trim().isEmpty())
                .toArray(String[]::new);
    }

    public static <T> Page<T> convertListToPage(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

}
