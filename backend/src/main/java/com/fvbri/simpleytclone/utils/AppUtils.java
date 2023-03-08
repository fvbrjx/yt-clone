package com.fvbri.simpleytclone.utils;

import java.lang.reflect.Field;

public class AppUtils {
    public static void updateEntityFields(Class c, Object request, Object entity) {
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            try {
                Field updatesField = c.getDeclaredField(fieldName);
                updatesField.setAccessible(true);
                Object fieldValue = updatesField.get(request);
                if (fieldValue != null) {
                    field.setAccessible(true);
                    field.set(entity, fieldValue);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalStateException(e.getMessage());
            }
        }
    }
}
