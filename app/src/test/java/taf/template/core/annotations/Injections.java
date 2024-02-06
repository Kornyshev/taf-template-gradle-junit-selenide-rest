package taf.template.core.annotations;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import taf.template.tests.BaseTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public final class Injections {

    private Injections() {
    }

    public static void inject(BaseTest tests) {
        Set<Field> fields = getAnnotatedClassFields(tests.getClass(), Flow.class);
        for (Field field : fields) {
            if (!isInstantiated(field, tests)) {
                setValue(field, tests, initResource(field.getType()));
            }
        }
    }

    public static Set<Field> getAnnotatedClassFields(Class<?> targetClass, Class<? extends Annotation> annotationClass) {
        return getClassFields(targetClass).stream()
                .filter(field -> field.getAnnotation(annotationClass) != null).collect(Collectors.toSet());
    }

    private static Set<Field> getClassFields(Class<?> targetClass) {
        Set<Field> fields = new HashSet<>();
        fields.addAll(Arrays.asList(targetClass.getDeclaredFields()));
        fields.addAll(Arrays.asList(targetClass.getFields()));
        if (targetClass != Object.class) {
            fields.addAll(getClassFields(targetClass.getSuperclass()));
        }
        return fields;
    }

    private static boolean isInstantiated(Field field, Object target) {
        field.setAccessible(true);
        Object value = null;
        try {
            value = field.get(target);
        } catch (IllegalAccessException e) {
            log.error("No access to field {}", field, e);
        }
        return value != null;
    }

    private static void setValue(Field field, Object target, Object value) {
        field.setAccessible(true);
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            log.error("Cannot set value to field {}", field, e);
        }
    }

    @SneakyThrows
    private static Object initResource(Class<?> resourceClass) {
        try {
            return resourceClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Unable to create instance of {}", resourceClass, e);
            throw new IllegalStateException("Unable to create instance of " + resourceClass);
        }
    }

}
